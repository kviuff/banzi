package com.bootdo.socket;

import com.bootdo.common.config.ApplicationContextRegister;
import com.bootdo.common.utils.CRC16;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.utils.TEA;
import com.bootdo.monitoring.domain.HistoryDO;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.HistoryService;
import com.bootdo.monitoring.service.MonitoringService;
import com.sun.tools.javac.util.Convert;
import org.apache.commons.lang.SystemUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

/**
 * 自定义解码器(确保能读取到完整的包)
 *
 * @author kviuff
 */
public class ByteArrayDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
                               ProtocolDecoderOutput out) throws Exception {
        byte[] bytes;
        byte[] tempBytes;
        String clientaddress = "";
        String strReceivedData = "";

        //判断是否有消息进来
        if(in.hasRemaining()) {
            int pakelength = in.remaining();    //消息长度
            System.out.println("消息长度"+pakelength);
            if (pakelength == 94) { // 新风板子
                //获取94位全数据包
                bytes = new byte[94];
                in.get(bytes);
                strReceivedData = ByteUtils.byteToHexStr(bytes);

                  //  System.out.println(new String(bytes));


                //客户端ip 端口
                clientaddress = session.getRemoteAddress().toString();
                //System.out.println(clientaddress);
                decoding(strReceivedData, session,clientaddress);
            } else { // 水板子
                if(pakelength==47){
                    decodingWater(in, session,pakelength);
                }else{
                    bytes = new byte[pakelength];
                    in.get(bytes);
                   // System.out.println(ByteUtils.byteToHexStr(bytes));
//                    bytes = new byte[6];
//                    in.get(bytes);
//                    String id = ByteUtils.byteToHexStr(bytes);
//                    bytes = new byte[1];
//                    in.get(bytes);
//                    String sta= ByteUtils.byteToHexStr(bytes);
//                    if("02".equals(sta)) System.out.println("设备id"+id+"套餐绑定成功"+sta);
//                    if("05".equals(sta)) System.out.println("设备id"+id+"关机成功"+sta);
//                    if("06".equals(sta)) System.out.println("设备id"+id+"开机成功"+sta);
//                    bytes = new byte[pakelength-7];
//                    in.get(bytes);
                }

            }
            //如果消息长度大于94 多消息合并 去除之前所有保存过的信息 保留最后94位
            /*if (pakelength >= 94) {
                bytes = new byte[pakelength - 94];
                in.get(bytes);
            }*/

        }
        return false;//处理成功，让父类进行接收下个包*/
    }

    /**
     * 解析新风板子
     *
     * @param strReceivedData
     */
    private Map<String, Object> decoding(String strReceivedData, IoSession session,String port) {
        String mac = "";
        String deviceid = "";
        int workMode = 0;
        int workLevel = 0;
        int Shutdown_HH = 0;
        int Shutdown_MM = 0;
        int pm25Value = 0;
        String pm10Value = "";
        String co2Value = "";
        String tvocValue = "";
        String tempValue = "";
        String humidityValue = "";
        String warnTip = "";
        String timeLong = "";
        String dataUploadInterval = "";
        String alarm = "";
        String worktime = "";
        String check = "";
        SessionMap sessionMap = SessionMap.newInstance();
        if (strReceivedData.length() >= 188){
            String strASCIIData = strReceivedData.substring(0, 125);
            String strHEXData = strReceivedData.substring(126, 188);

            String[] charAscii = new String[62];
            for (int i = 0; i < 62; i++){
                int AsciiValue = Convert.string2int(strASCIIData.substring(2 * i, 2 * i+2), 16);
                charAscii[i] = ByteUtils.ConvertFromUtf32(AsciiValue);
            }
            int iBegin = 6;
            String strProv = charAscii[iBegin + 1] + charAscii[iBegin + 2];
            String strCity = charAscii[iBegin + 3] + charAscii[iBegin + 4];
            String machineType = charAscii[iBegin + 5];
            String strUnit = charAscii[iBegin + 6] + charAscii[iBegin + 7] + charAscii[iBegin + 8] + charAscii[iBegin + 9];
            String strMachineIDDate = charAscii[iBegin + 10] + charAscii[iBegin + 11] + charAscii[iBegin + 12] + charAscii[iBegin + 13] + charAscii[iBegin + 14]+charAscii[iBegin + 15];
            String machineID = charAscii[iBegin + 16] + charAscii[iBegin + 17] + charAscii[iBegin + 18] + charAscii[iBegin + 19];
            String machineUse = charAscii[iBegin + 20] + charAscii[iBegin + 21];
            mac = strASCIIData.substring(66, 78);
            System.out.println("解析结束");
            deviceid = strProv + strCity + machineType + strUnit + strMachineIDDate + machineID + machineUse;
            //如果名字是重复的 则执行重新命名
            if(deviceid.trim().equals("test 161126")){
                sessionMap.changeName(session);
            }
            System.out.println("deviceid +++++"+deviceid.trim());
            String[] hexDataValuesSplit = new String[31];

            for (int i = 0; i < 31; i++){
                hexDataValuesSplit[i] = strHEXData.substring(2 * i, 2 * i+2);
            }
            workMode = Convert.string2int(hexDataValuesSplit[3], 16);
            workLevel = Convert.string2int(hexDataValuesSplit[4], 16);
            pm25Value = Convert.string2int(hexDataValuesSplit[9] + hexDataValuesSplit[10], 16);
            pm10Value = Convert.string2int(hexDataValuesSplit[11] + hexDataValuesSplit[12], 16)+"";
            co2Value = Convert.string2int(hexDataValuesSplit[13] + hexDataValuesSplit[14], 16)+"";
            tvocValue = Convert.string2int(hexDataValuesSplit[15] + hexDataValuesSplit[16], 16)+"";
            tempValue = Convert.string2int(hexDataValuesSplit[17], 16)+"";
            humidityValue = Convert.string2int(hexDataValuesSplit[18], 16)+"";
            warnTip = Convert.string2int(hexDataValuesSplit[20], 16)+"";
            timeLong = Convert.string2int(hexDataValuesSplit[21] + hexDataValuesSplit[22], 16)+"";

            // 保存数据库
            StringBuffer paramJson = new StringBuffer();
            paramJson.append("{");
            paramJson.append("\"pm\":\"" + pm25Value + "\",");
            paramJson.append("\"co2\":\"" + co2Value + "\",");
            paramJson.append("\"voc\":\"" + tvocValue + "\",");
            paramJson.append("\"temperature\":\"" + tempValue + "\",");
            paramJson.append("\"humidity\":\"" + humidityValue + "\"");
            paramJson.append("}");

            MonitoringService monitoringService = ApplicationContextRegister.getBean(MonitoringService.class);
            HistoryService historyService = ApplicationContextRegister.getBean(HistoryService.class);

            MonitoringDO monitoringDO1 = monitoringService.getByCode(deviceid.trim());

            MonitoringDO monitoringDO = new MonitoringDO();
            monitoringDO.setMonitorCode(deviceid.trim()); // 机器编码
            monitoringDO.setStatus(String.valueOf(workMode)); // 运行情况
            monitoringDO.setLength(timeLong); // 时长
            monitoringDO.setParamJson(paramJson.toString()); // 保存参数
            monitoringDO.setCreateTime(System.currentTimeMillis());
            monitoringDO.setMachineType("1");
            monitoringDO.setStall(String.valueOf(workLevel).substring(0,1));
            //客户端ip 端口
            String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
            monitoringDO.setProp10(port.substring(1));

            if (null == monitoringDO1) {
//                String name = System.currentTimeMillis()+"";
//                TEA tea = new TEA();
//                //信息 转数组
//                byte[] messageByte = ByteUtils.HexStringToBinary(changeName(name).replace(" ",""));
//                byte[] resultByte = new byte[42];
//                byte[] reByte = tea.encryptGroup(messageByte);
//                System.arraycopy(reByte, 0, resultByte, 0, reByte.length);
//                resultByte[40] = 13;
//                resultByte[41] = 10;
//                System.out.println(resultByte.length);
//                session.write(resultByte);
                monitoringDO.setMonitorCode(deviceid.trim());

                String str = "";
                if (pm25Value > 150) {
                    str += "\"pm\":\"" + pm25Value + "\",";
                }

                if (Integer.valueOf(co2Value) > 3000) {
                    str += "\"co2\":\"" + co2Value + "\",";
                }

                if (StringUtils.isNotEmpty(str)) {
                    str = str.substring(0, str.length() - 1);
                    StringBuffer abnormalCondition = new StringBuffer();
                    abnormalCondition.append("{");
                    abnormalCondition.append(str);
                    abnormalCondition.append("}");

                    monitoringDO.setAbnormalCondition(abnormalCondition.toString());
                }
                System.out.println("插入内容");
                monitoringService.save(monitoringDO);
            } else {
                // 如果数据库已存在机器信息，说明需要存储已有信息为历史信息
                HistoryDO historyDO = new HistoryDO();
                historyDO.setMonitorId(monitoringDO1.getId() + "");
                historyDO.setMonitorCode(monitoringDO1.getMonitorCode());
                historyDO.setMonitorModeCode(monitoringDO1.getMonitorModeCode());
                historyDO.setMonitorDetailJson(monitoringDO1.getParamJson());
                historyDO.setCreateTime(System.currentTimeMillis());
                historyDO.setProp1(timeLong);
                historyDO.setProp4(String.valueOf(pm25Value));
                historyService.save(historyDO);
                System.out.println("更新内容");
                monitoringService.updateByMonitorCode(monitoringDO);

            }
            //保存客户端的会话session

            sessionMap.addSession(monitoringDO.getMonitorCode(), session);
        }

//        System.out.println("设备编号："+deviceid+"MAC地址："+mac+" 工作模式："+workMode +" 风机档位："+workLevel
//        +" 定时关机时间："+Shutdown_HH+":"+Shutdown_MM+"  PM2.5浓度:"+pm25Value + "ug/m3  PM10浓度:"+pm10Value + "  CO2浓度:"+co2Value + "  TVOC:"+tvocValue
//        +"  温度："+tempValue + "  湿度："+humidityValue);

        return null;
    }

    /**
     * 水板子接受数据
     * @param in
     * @param session
     * @return
     */
    private Map<String, Object> decodingWater (IoBuffer in, IoSession session,int leng) {
        byte[] bytes;
        String iccidStr = "";
        String commandWord = "";
        int dataLength = 0;
        String dataDetail = "";

        int deviceStatus = 0; // 设备状态
        int status =0;//开关机状态
        int workModel = 0;    // 工作模式
        int residualFlow = 0; // 剩余流量
        int resideuaDate = 0; // 剩余天数
        int useFlow = 0;      // 已用流量
        int useDate = 0;      // 已用天数
        int TDS = 0;          // 净水TDS
        int rawWaterTDS = 0;  // 原水TDS
        int flow1 = 0;
        int flow2 = 0;
        int flow3 = 0;
        int flow4 = 0;
        int flow5 = 0;

        bytes = new byte[6];
        in.get(bytes);
        iccidStr = ByteUtils.byteToHexStr(bytes);
        System.out.println("设备id：" + iccidStr);

        bytes = new byte[1];
        in.get(bytes);
        commandWord = ByteUtils.byteToHexStr(bytes);
        System.out.println("命令：" + commandWord);
        bytes = new byte[2];
        in.get(bytes);
        String lengStr = ByteUtils.byteToHexStr(bytes);
        byte[] a = new byte[4];
        a[0] =0x00;
        a[1] =0x00;
        a[2] =bytes[0];
        a[3] =bytes[1];
        dataLength =ByteUtils.bytes2int(a);
        System.out.println("数据长度：" + dataLength);
        byte[] data;

        //如果命令是1则为常规数据传递
        if("01".equals(commandWord)) {
            if (dataLength > 14) {
                bytes = new byte[dataLength];
                in.get(bytes);
                System.out.println(ByteUtils.byteToHexStr(bytes));
                data = new byte[4];
                data[0] = 0x00;
                data[1] = 0x00;
                data[2] = 0x00;
                data[3] = bytes[0];
                deviceStatus = ByteUtils.bytes2int(data);//设备状态
                System.out.println("水机设备状态:设备id:"+iccidStr+"=============="+translateMessage(deviceStatus)+deviceStatus);
                if(deviceStatus == 6){//开关机状态
                    status=1;
                }else{
                    status=0;
                }
                data[3] = bytes[2];
                workModel = ByteUtils.bytes2int(data); // 工作模式
                data[2] = bytes[3];
                data[3] = bytes[4];
                residualFlow = ByteUtils.bytes2int(data); // 剩余流量
                data[2] = bytes[5];
                data[3] = bytes[6];
                resideuaDate = ByteUtils.bytes2int(data);//剩余天数
                data[2] = bytes[7];
                data[3] = bytes[8];
                useFlow = ByteUtils.bytes2int(data);//已用流量
                data[2] = bytes[9];
                data[3] = bytes[10];
                useDate = ByteUtils.bytes2int(data);//已用天数
                data[2] = bytes[11];
                data[3] = bytes[12];
                TDS = ByteUtils.bytes2int(data);//净水TDS
                data[2] = bytes[13];
                data[3] = bytes[14];
                rawWaterTDS = ByteUtils.bytes2int(data);//原水TDS
                data[2] = bytes[15];
                data[3] = bytes[16];
                flow1 = ByteUtils.bytes2int(data);//剩余流量1
                data[2] = bytes[17];
                data[3] = bytes[18];
                flow2 = ByteUtils.bytes2int(data);//剩余流量1
                data[2] = bytes[19];
                data[3] = bytes[20];
                flow3 = ByteUtils.bytes2int(data);//剩余流量1
                data[2] = bytes[21];
                data[3] = bytes[22];
                flow4 = ByteUtils.bytes2int(data);//剩余流量1
                data[2] = bytes[23];
                data[3] = bytes[24];
                flow5 = ByteUtils.bytes2int(data);//剩余流量1

                //接受信息之后 给个应答
                byte[] answer = new byte[13];
                byte[] checkCrc = new byte[11];
                byte[] resultByte = ByteUtils.toBytes(iccidStr);
                checkCrc[0] = resultByte[0];
                checkCrc[1] = resultByte[1];
                checkCrc[2] = resultByte[2];
                checkCrc[3] = resultByte[3];
                checkCrc[4] = resultByte[4];
                checkCrc[5] = resultByte[5];
                checkCrc[6] = 0x01;
                checkCrc[7] = 0x00;
                checkCrc[8] = 0x02;
                checkCrc[9] = 0x4F;
                checkCrc[10] = 0x4B;
                String crcStr = CRC16.crc16(checkCrc);
              //  System.out.println(crcStr);
                System.arraycopy(checkCrc, 0, answer, 0, checkCrc.length);
                byte[] crc = ByteUtils.toBytes(crcStr);
                answer[11] = crc[0];
                answer[12] = crc[1];
              //  System.out.println(ByteUtils.byteToHexStr(answer));
                session.write(answer);
            }
            byte[] crc1 = new byte[2];
            in.get(crc1);

//        }else{
//            byte[] resultByte = new byte[leng];
//            //之前解析的数据
//            byte[] rec = ByteUtils.toBytes(iccidStr+commandWord+lengStr);
//            System.arraycopy(rec, 0, resultByte, 0, rec.length);
//            bytes = new byte[leng-9];
//            in.get(bytes);
//            System.arraycopy(bytes, 0, resultByte, rec.length, bytes.length);
//            System.out.println(ByteUtils.byteToHexStr(resultByte));
//          //  session.write(resultByte);
//        }


            // 保存数据库
            StringBuffer paramJson = new StringBuffer();
            paramJson.append("{");
            paramJson.append("\"raw\":\"" + rawWaterTDS + "\",");
            paramJson.append("\"purification\":\"" + TDS + "\",");
            paramJson.append("\"flow\":\"" + useFlow + "\"");
            paramJson.append("}");

            MonitoringService monitoringService = ApplicationContextRegister.getBean(MonitoringService.class);
            HistoryService historyService = ApplicationContextRegister.getBean(HistoryService.class);

            MonitoringDO monitoringDO1 = monitoringService.getByCode(iccidStr);

            MonitoringDO monitoringDO = new MonitoringDO();
            monitoringDO.setMonitorCode(iccidStr); // 机器编码
            monitoringDO.setStatus(String.valueOf(status));  // 运行情况
            monitoringDO.setLength(String.valueOf(useDate));//已用天数
            monitoringDO.setCumulativeFlow(String.valueOf(useFlow));//已用流量
            monitoringDO.setParamJson(paramJson.toString());
            monitoringDO.setCreateTime(System.currentTimeMillis());
            monitoringDO.setFlow1(String.valueOf(flow1));
            monitoringDO.setFlow2(String.valueOf(flow2));
            monitoringDO.setFlow3(String.valueOf(flow3));
            monitoringDO.setFlow4(String.valueOf(flow4));
            monitoringDO.setFlow5(String.valueOf(flow5));
            monitoringDO.setMachineType("2");
            //客户端ip 端口
            //String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
            String clientIP = session.getRemoteAddress().toString();
            monitoringDO.setProp10(clientIP.substring(1));

            if (null == monitoringDO1) {
//                SessionMap sessionMap = SessionMap.newInstance();
//                //绑定套餐
//                byte[] t = sessionMap.bindingWaterMessage(iccidStr);
//                session.write(t);

                String str = "";
                if (rawWaterTDS > 550) {
                    str += "\"rawWaterTDS\":\"" + rawWaterTDS + "\",";
                }

                if (TDS > 80) {
                    str += "\"TDS\":\"" + TDS + "\",";
                }

                if (StringUtils.isNotEmpty(str)) {
                    str = str.substring(0, str.length() - 1);
                    StringBuffer abnormalCondition = new StringBuffer();
                    abnormalCondition.append("{");
                    abnormalCondition.append(str);
                    abnormalCondition.append("}");

                    monitoringDO.setAbnormalCondition(abnormalCondition.toString());
                }

                monitoringService.save(monitoringDO);
            } else {
                // 如果数据库已存在机器信息，说明需要存储已有信息为历史信息
                HistoryDO historyDO = new HistoryDO();
                historyDO.setMonitorId(monitoringDO1.getId() + "");
                historyDO.setMonitorCode(monitoringDO1.getMonitorCode());
                historyDO.setMonitorModeCode(monitoringDO1.getMonitorModeCode());
                historyDO.setMonitorDetailJson(monitoringDO1.getParamJson());
                historyDO.setCreateTime(System.currentTimeMillis());
                historyDO.setProp2(String.valueOf(rawWaterTDS));
                historyDO.setProp3(String.valueOf(TDS));
                historyService.save(historyDO);

                monitoringService.updateByMonitorCode(monitoringDO);
            }

            System.out.println("设备状态：" + deviceStatus +"开关机:" + status + "工作模式：" + workModel + " 剩余流量：" + residualFlow + " 剩余天数：" + resideuaDate
                    + " 已用流量：" + useFlow + " 已用天数:" + useDate + " 净水TDS:" + TDS + " 原水TDS:" + rawWaterTDS);

            //保存客户端的会话session
            SessionMap sessionMap = SessionMap.newInstance();
            sessionMap.addSession(iccidStr, session);
        }
        return null;
    }

    /**
     * 更改名字
     * @return
     */
    public String changeName (String name){

        name = ByteUtils.BinaryToHexString(name.getBytes());
       // System.out.println(name);
        name += "00000000000000000000000000000000000000";
       // System.out.println(name.length());
        String regex = "(.{2})";
        name = name.replaceAll (regex, "$1 ");
      //  System.out.println(name);
      //  System.out.println(name.length());
        if (SessionMap.num < 65535){
            SessionMap.num++;
        }else{
            SessionMap.num=0;
        }
        String num = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
        num = num.substring(0,2)+" "+num.substring(2,4);
        String check ="43 25 0E "+name+"00 00 "+num;
      //  System.out.println(check);
        //校验
        String checkSum = ByteUtils.checksum(check);
      //  System.out.println(checkSum);
        String result = check+" "+checkSum.substring(2,4);
      //  System.out.println(result);
        return result;
    }

    /**
     * 打印机器状态
     * @param code
     * @return
     */
    public String translateMessage(int code){
        String message="";
        if(0 == code){
            message="备用";
        }else if(1 == code){
            message="待激活";
        }else if(2 == code){
            message="出厂测试状态";
        }else if(3 == code){
            message="正常制水";
        }else if(4 == code){
            message="欠费";
        }else if(5 == code){
            message="故障";
        }else if(6 == code){
            message="关机";
        }else if(7 == code){
            message="水满";
        }else if(8 == code){
            message="缺水";
        }else if(9 == code){
            message="漏水";
        }else if(10 == code){
            message="滤芯待复位";
        }else if(11 == code){
            message="硬件测试";
        }
        return message;
    }

    public static void main(String[] arg){
        String check= "4E 57 01 01 01 BC 11 00 11 03 7D 00 80 02 01 00 00 00 00 00 00 00 00 00 00 00 00";
        String checkSum = ByteUtils.checksum(check);
        System.out.println("JAVA校验checkSum=" + checkSum);
        /*SessionMap sessionMap = SessionMap.newInstance();
       // sessionMap.changeName();
        String device = "test 161126                     ";//设备id
        device = ByteUtils.str2HexStr(device)+" ";
        //连续发送三次命令

            if (SessionMap.num < 65535) {
                SessionMap.num++;
            } else {
                SessionMap.num = 0;
            }
            String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
            num1 = num1.substring(0, 2) + " " + num1.substring(2, 4);
            //num1="0005";
            String start = "43 ";//起始
            String length = "25 ";//数据长度
            String commondWord = "0E ";//命令字

            System.out.println(device + " ");
            String empty = "00 ";//保留两个字节
            String empty1 = "00";
            //序号
            //校验字符串
            String check = start + length + commondWord + device + empty + empty1 + num1;
            System.out.println("check===" + check);
            //校验
            String checkSum = ByteUtils.checksum(check);
            System.out.println("JAVA校验checkSum=" + checkSum);
            System.out.println(checkSum);
            String result = check + " " + checkSum.substring(2, 4);
            System.out.println(result);

            TEA tea = new TEA();
            //信息 转数组
            byte[] messageByte = ByteUtils.HexStringToBinary(result.replace(" ", ""));
            byte[] newData = Arrays.copyOfRange(messageByte, 0, 8);
            newData = tea.encrypt_group(newData);
            byte[] newData1 = Arrays.copyOfRange(messageByte, 8, 16);
            newData1 = tea.encrypt_group(newData1);
            byte[] newData2 = Arrays.copyOfRange(messageByte, 16, 24);
            newData2 = tea.encrypt_group(newData2);
            byte[] newData3 = Arrays.copyOfRange(messageByte, 24, 32);
            newData3 = tea.encrypt_group(newData3);
            byte[] newData4 = Arrays.copyOfRange(messageByte, 32, 40);
            newData4 = tea.encrypt_group(newData4);
            byte[] resultByte = new byte[newData.length + newData1.length + newData2.length + newData3.length + newData4.length + 2];
            System.arraycopy(newData, 0, resultByte, 0, newData.length);
            System.arraycopy(newData1, 0, resultByte, newData.length, newData1.length);
            System.arraycopy(newData2, 0, resultByte, newData.length+newData1.length, newData2.length);
            System.arraycopy(newData3, 0, resultByte, newData.length+newData1.length+newData2.length, newData3.length);
            System.arraycopy(newData4, 0, resultByte, newData.length+newData1.length+newData2.length+newData3.length, newData4.length);
            resultByte[40] = 13;
            resultByte[41] = 10;
            System.out.println(ByteUtils.byteToHexStr(resultByte));
*/
       // String a ="5ccf7fb2370a0100240100000000000000000000000000000002000200020002000200020002000200020002b432c1";
        String str = "4465766963653a7465737420313631313236202020200000000000000000000000a020a6149dbf424a424c2041502056313730323134547d5e0fae0195789c4315810000000000000000000000000000161010000000ef001f0021d60d0a";
        /*try {
            //创建一个Socket,跟本机的8080端口连接
            Socket socket = new Socket("47.93.156.129", 8000);
            //使用Socket创建PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            dos.write(ByteUtils.toBytes(str));
            dos.flush();
            System.out.println("发送：" + ByteUtils.toBytes(str).length);
            //接收数据
            String line = is.readLine();
            System.out.println("===:" + line);
            //关闭资源
            pw.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
