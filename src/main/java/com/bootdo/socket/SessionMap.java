package com.bootdo.socket;

import com.bootdo.common.config.ApplicationContextRegister;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.CRC16;
import com.bootdo.common.utils.TEA;
import com.bootdo.common.utils.TeaUtils;
import com.bootdo.monitoring.domain.SetMealDO;
import com.bootdo.monitoring.service.SetMealService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionMap {

    private final static Log log = LogFactory.getLog(SessionMap.class);

    private static SessionMap sessionMap = null;
    public static  int num=0;

    private static Map<String, IoSession> map = new HashMap<String, IoSession>();


    //构造私有化 单例
    private SessionMap(){}


    /**
     * @Description: 获取唯一实例
     * @author whl
     * @date 2014-9-29 下午1:29:33
     */
    public static SessionMap newInstance(){
        log.debug("SessionMap单例获取---");
        if(sessionMap == null){
            //System.out.println("sessionMap is null");
            sessionMap = new SessionMap();
        }
        return sessionMap;
    }


    /**
     * @Description: 保存session会话
     * @author whl
     * @date 2014-9-29 下午1:31:05
     */
    public void addSession(String key, IoSession session){
        log.debug("saveSessionMapSingle---key=" + key);
        //System.out.println("saveSessionMapSingle---key=" + key + "map-size==" + map.size());
        map.put(key, session);
        System.out.println(map);
    }

    /**
     * @Description: 根据key查找缓存的session
     * @author whl
     * @date 2014-9-29 下午1:31:55
     */
    public IoSession getSession(String key){
        log.debug("getSessionMapSingle---key=" + key);
        System.out.println("map-size==" + map.size()+map.get(key));
        return map.get(key);

    }

    /**
     * 更改设备名称 新风
     * @param session
     */
    public void changeName(IoSession session){
        String device = System.currentTimeMillis()+"                   ";//设备id
        device = ByteUtils.str2HexStr(device)+" ";
        //连续发送三次命令
        for(int i = 0;i<3;i++) {
            if (SessionMap.num < 65535) {
                SessionMap.num++;
            } else {
                SessionMap.num = 0;
            }
            String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
            num1 = num1.substring(0, 2) + " " + num1.substring(2, 4);
            String start = "43 ";//起始
            String length = "25 ";//数据长度
            String commondWord = "0E ";//命令字

            System.out.println(device + " ");
            String empty = "00 ";//保留两个字节
            String empty1 = "00 ";
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
            session.write(resultByte);
            this.num++;
        }
        //发送更改名字之后 进行开机操作
        if (SessionMap.num < 65535){
            SessionMap.num++;
        }else{
            SessionMap.num=0;
        }
        String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
        num1 = num1.substring(0,2)+" "+num1.substring(2,4);
        String start = "43 ";//起始
        String length = "0D ";//数据长度
        String commondWord = "01 ";//命令字
        String xinfengStatus = "";//工作模式
        String fangearposition = "";//风机档位等
        xinfengStatus = "01 ";//工作模式
        fangearposition = "11 ";//风机档位等
        String closeHour = "00 ";//定时关机时间
        String closeMinute = "00 ";
        String empty = "00 ";
        String empty1 = "00 ";

        String jiange = " 10 ";
        String empty2 = "00 ";
        String empty3 = "00 ";
        String empty4 = "00";
        String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
        System.out.println("check==="+check);
        //校验
        String checkSum = ByteUtils.checksum(check);
        System.out.println("JAVA校验checkSum="+checkSum);
        System.out.println(checkSum);
        String result1 = check+" "+checkSum.substring(2,4);
        //byte[] resultByte = teaUtils.encryptByTea(messageByte);
        TEA tea = new TEA();
        //信息 转数组
        byte[] messageByte = ByteUtils.HexStringToBinary(result1.replace(" ",""));
        byte[] newData = Arrays.copyOfRange(messageByte, 0, 8);
        newData = tea.encrypt_group(newData);
        byte[] newData1 = Arrays.copyOfRange(messageByte, 8, 16);
        newData1 = tea.encrypt_group(newData1);
        byte[] resultByte = new byte[newData.length + newData1.length+2];
        System.arraycopy(newData, 0, resultByte, 0, newData.length);
        System.arraycopy(newData1, 0, resultByte, newData.length, newData1.length);
        resultByte[16] = 13;
        resultByte[17] = 10;
        session.write(resultByte);
        this.num++;
    }


    /**
     * @Description: 发送消息到客户端
     * @author whl
     * @date 2014-9-29 下午1:57:51
     */
    public void sendMessage(String keys, String message){

        IoSession session = getSession(keys);

        log.debug("sendMsgToClientSession---key=" + keys + "----------msg=" + message);
//        if(session == null){
//            log.debug("sendMsgToClientSession is null" + keys);
//            System.out.println("sendMsgToClientSession is null, key=" + keys + "----------msg=" + message );
//            return;
//        }

        System.out.println("msg===" + message);
        // TEA加密
      //  byte[] messageByte = ByteUtils.HexStringToBinary(message.replace(" ",""));
       // TeaUtils teaUtils = new TeaUtils();
        //byte[] resultByte = teaUtils.encryptByTea(messageByte);
       TEA tea = new TEA();
        //信息 转数组
         byte[] messageByte = ByteUtils.HexStringToBinary(message.replace(" ",""));
         byte[] newData = Arrays.copyOfRange(messageByte, 0, 8);
        newData = tea.encrypt_group(newData);
        byte[] newData1 = Arrays.copyOfRange(messageByte, 8, 16);
        newData1 = tea.encrypt_group(newData1);
        byte[] resultByte = new byte[newData.length + newData1.length+2];
        System.arraycopy(newData, 0, resultByte, 0, newData.length);
        System.arraycopy(newData1, 0, resultByte, newData.length, newData1.length);
        resultByte[16] = 13;
        resultByte[17] = 10;


       //开机加密后
        byte[] sendOpen = {
                (byte)0xfd, 0x2c, (byte)0x88, 0x3a, 0x5a, (byte)0xdd, 0x17, (byte)0x80, (byte)0xaa, 0x25,
                0x5c, 0x70, 0x13, 0x71, 0x68, 0x33, 0x0d, 0x0a
        };
        byte[] sendClose = {
                0x5a, 0x47, 0x76, 0x5b, 0x06, 0x57, 0x6d, 0x2f, (byte)0xb9, (byte)0xdc,
                (byte)0xf3, (byte)0x90, 0x51, (byte)0x99, (byte)0xce, 0x7b, 0x0d, 0x0a
        };
        System.out.println("目标:"+ByteUtils.BinaryToHexString(resultByte));
        //关机加密后
        session.write(resultByte);
        this.num++;
    }


    /**
     * @Description: 发送消息到客户端
     * @author whl
     * @date 2014-9-29 下午1:57:51
     */
    public void sendWaterMessage(String keys, String message, String status){

        IoSession session = getSession(keys);

        log.debug("sendMsgToClientSession---key=" + keys + "----------msg=" + message);

        System.out.println("msg===" + message);
        byte[] resultByte = new byte[12];
        byte[] checkCrc = new byte[10];
        String iccid = message; // 设备iccid，取过来的是字节转为十六进制，需要将十六进制转成字节
        byte[] result = ByteUtils.toBytes(iccid);
        System.arraycopy(result, 0, checkCrc, 0, result.length);
        if (status.equals("1")) { // 关机
            checkCrc[6] = 0x05;
        } else { // 开机
            checkCrc[6] = 0x06;
        }
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x01;
        checkCrc[9] = 0x00;
        String crcStr = CRC16.crc16(checkCrc);
        System.arraycopy(checkCrc, 0, resultByte, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        resultByte[10]=crc[0];
        resultByte[11]=crc[1];
        System.out.println("开关机:"+ByteUtils.byteToHexStr(resultByte));
        session.write(resultByte);

    }

    /**
     * @Description: 绑定套餐
     * @author whl
     * @date 2014-9-29 下午1:57:51
     */
    public void bindingWaterMessage( String iccidStr,Long mealId){
        IoSession session = getSession(iccidStr);
        SetMealService setMealService = ApplicationContextRegister.getBean(SetMealService.class);

        log.debug("sendMsgToClientSession---key=" + iccidStr );
       // byte[] bytes1 = ByteUtils.intToByteArray(1000);
       // System.out.println(ByteUtils.byteToHexStr(bytes1));
        System.out.println("msg===" + iccidStr);
        //绑定套餐
        byte[] t = new byte[22];
        byte[] checkCrc = new byte[20];
        byte[] resultByte = ByteUtils.toBytes(iccidStr);
        checkCrc[0] = resultByte[0];
        checkCrc[1] = resultByte[1];
        checkCrc[2] = resultByte[2];
        checkCrc[3] = resultByte[3];
        checkCrc[4] = resultByte[4];
        checkCrc[5] = resultByte[5];
        checkCrc[6] = 0x02;
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x0B;
        checkCrc[9] = 0x00;
        SetMealDO mealDO = setMealService.get(mealId);
        if(mealDO !=null){
            String flow1 =  mealDO.getFlow1();
            byte[] bytes1 = ByteUtils.intToByteArray(Integer.parseInt(flow1));
            checkCrc[10] = bytes1[0];
            checkCrc[11] = bytes1[1];
            String flow2 =  mealDO.getFlow2();
            byte[] bytes2 = ByteUtils.intToByteArray(Integer.parseInt(flow2));
            checkCrc[12] = bytes2[0];
            checkCrc[13] = bytes2[1];
            String flow3 =  mealDO.getFlow3();
            byte[] bytes3 = ByteUtils.intToByteArray(Integer.parseInt(flow3));
            checkCrc[14] = bytes3[0];
            checkCrc[15] = bytes3[1];
            String flow4 =  mealDO.getFlow4();
            byte[] bytes4 = ByteUtils.intToByteArray(Integer.parseInt(flow4));
            checkCrc[16] = bytes4[0];
            checkCrc[17] = bytes4[1];
            String flow5 =  mealDO.getFlow5();
            byte[] bytes5 = ByteUtils.intToByteArray(Integer.parseInt(flow5));
            checkCrc[18] = bytes5[0];
            checkCrc[19] = bytes5[1];

        }else{
            checkCrc[10] = 0x03;
            checkCrc[11] = (byte) 0xE8;
            checkCrc[12] = 0x03;
            checkCrc[13] = (byte) 0xE8;
            checkCrc[14] = 0x07;
            checkCrc[15] = (byte) 0xD0;
            checkCrc[16] = 0x07;
            checkCrc[17] = (byte) 0xD0;
            checkCrc[18] = 0x13;
            checkCrc[19] = (byte) 0x88;
        }
        String crcStr = CRC16.crc16(checkCrc);
        System.out.println(crcStr);
        System.arraycopy(checkCrc, 0, t, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        t[20] = crc[0];
        t[21] = crc[1];
        System.out.println(ByteUtils.byteToHexStr(t));
        log.debug("绑定套餐");
        session.write(t);
    }

    /**
     * 恢复滤芯值
     * @param type 恢复哪个滤芯
     * @param iccidStr id
     */
    public void reset(int type,String iccidStr){
        IoSession session = getSession(iccidStr);

        byte[] t = new byte[14];
        byte[] checkCrc = new byte[12];
        byte[] resultByte = ByteUtils.toBytes(iccidStr);
        checkCrc[0] = resultByte[0];
        checkCrc[1] = resultByte[1];
        checkCrc[2] = resultByte[2];
        checkCrc[3] = resultByte[3];
        checkCrc[4] = resultByte[4];
        checkCrc[5] = resultByte[5];
        checkCrc[6] = 0x0E;
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x03;
        if(type == 1){
            checkCrc[9] = 0x01;
        }else if(type == 2){
            checkCrc[9] = 0x02;
        }else if(type == 3){
            checkCrc[9] = 0x03;
        }else if(type == 4){
            checkCrc[9] = 0x04;
        }else if(type == 5){
            checkCrc[9] = 0x05;
        }else if(type == 6){
            checkCrc[9] = 0x06;
        }
        checkCrc[10] = (byte)0xff;
        checkCrc[11] = (byte)0xff;
        String crcStr = CRC16.crc16(checkCrc);
        System.out.println(crcStr);
        System.arraycopy(checkCrc, 0, t, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        t[12] = crc[0];
        t[13] = crc[1];
        System.out.println(ByteUtils.byteToHexStr(t));
        log.debug("恢复滤芯值");
        session.write(t);
    }

    /**
     * 充值
     * @param iccidStr id
     */
    public void Recharge(String iccidStr){
        IoSession session = getSession(iccidStr);

        byte[] t = new byte[13];
        byte[] checkCrc = new byte[11];
        byte[] resultByte = ByteUtils.toBytes(iccidStr);
        checkCrc[0] = resultByte[0];
        checkCrc[1] = resultByte[1];
        checkCrc[2] = resultByte[2];
        checkCrc[3] = resultByte[3];
        checkCrc[4] = resultByte[4];
        checkCrc[5] = resultByte[5];
        checkCrc[6] = 0x08;
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x02;
        checkCrc[9] = 0x00;
        checkCrc[10] = (byte)0x64;
        String crcStr = CRC16.crc16(checkCrc);
        System.out.println(crcStr);
        System.arraycopy(checkCrc, 0, t, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        t[11] = crc[0];
        t[12] = crc[1];
        System.out.println(ByteUtils.byteToHexStr(t));
        log.debug("充值");
        session.write(t);
    }


    /**
     * 恢复出厂设置
     * @param iccidStr id
     */
    public void hf(String iccidStr){
        IoSession session = getSession(iccidStr);

        byte[] t = new byte[12];
        byte[] checkCrc = new byte[10];
        byte[] resultByte = ByteUtils.toBytes(iccidStr);
        checkCrc[0] = resultByte[0];
        checkCrc[1] = resultByte[1];
        checkCrc[2] = resultByte[2];
        checkCrc[3] = resultByte[3];
        checkCrc[4] = resultByte[4];
        checkCrc[5] = resultByte[5];
        checkCrc[6] = 0x10;
        checkCrc[7] = 0x00;
        checkCrc[8] = 0x01;
        checkCrc[9] = 0x00;
        String crcStr = CRC16.crc16(checkCrc);
        System.out.println(crcStr);
        System.arraycopy(checkCrc, 0, t, 0, checkCrc.length);
        byte[] crc = ByteUtils.toBytes(crcStr);
        t[10] = crc[0];
        t[11] = crc[1];
        System.out.println(ByteUtils.byteToHexStr(t));
        log.debug("恢复出厂设置");
        session.write(t);
    }


}
