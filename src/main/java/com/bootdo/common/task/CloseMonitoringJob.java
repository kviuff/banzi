package com.bootdo.common.task;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.lamp.LampImpl;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.socket.ByteUtils;
import com.bootdo.socket.SessionMap;
import net.sf.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 定时关机任务
 */
@Component
public class CloseMonitoringJob implements Job {

    @Autowired
    private MonitoringService monitoringService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时关机任务执行开始");
        Map<String, Object> map = new HashMap<>();
        map.put("closeTime", DateUtils.formatLongToStr(System.currentTimeMillis()));
        List<MonitoringDO> monitoringDOList = monitoringService.list(map);
        for (MonitoringDO monitoringDO : monitoringDOList) {
            System.out.println("有数据");
            String closeTime = monitoringDO.getCloseTime();
            String nowTime = DateUtils.formatLongToStr(System.currentTimeMillis());
            if (DateUtils.compareDate(closeTime, nowTime) == -1) {
                System.out.println("执行开关机");
                String type = monitoringDO.getMachineType();
                String monitorCode = monitoringDO.getMonitorCode();
                if ("1".equals(type)) { // 新风
                    if ("1".equals(monitoringDO.getStatus())) { // 开机状态下再去执行关机
                        SessionMap sessionMap = SessionMap.newInstance();
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
                        // 关闭
                        monitoringDO.setStatus("0");
                        xinfengStatus = "00 ";//工作模式
                        fangearposition = "00 ";//风机档位等
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
                        String result = check+" "+checkSum.substring(2,4);
                        sessionMap.sendMessage(monitorCode, result);
                        System.out.println(result);

                        MonitoringDO monitoringDO1 = new MonitoringDO();
                        monitoringDO1.setId(monitoringDO.getId());
                        monitoringDO1.setStatus("0");
                        monitoringService.update(monitoringDO1);
                    }
                } else if ("2".equals(type)) { // 饮水机
                    if ("0".equals(monitoringDO.getStatus())) { // 开机状态下再去执行关机
                        SessionMap sessionMap = SessionMap.newInstance();
                        // 发送开关机指令
                        sessionMap.sendWaterMessage(monitorCode, monitorCode, "1");
                        MonitoringDO monitoringDO1 = new MonitoringDO();
                        monitoringDO1.setId(monitoringDO.getId());
                        monitoringDO1.setStatus("0");
                        monitoringService.update(monitoringDO1);
                    }
                } else { // 灯
                    if ("1".equals(monitoringDO.getStatus())) { // 开机状态下再去执行关机
                        String lanerUserName = monitoringDO.getLanerUserName();
                        String lanerPassword = monitoringDO.getLanerPassword();
                        String contollerAddr = monitoringDO.getContollerAddr();

                        MonitoringDO monitoringDO1 = new MonitoringDO();
                        monitoringDO1.setId(monitoringDO.getId());

                        Map params1 = new HashMap();
                        params1.put("userName", lanerUserName);
                        params1.put("userPwd", lanerPassword);
                        String result = LampImpl.loginToLamp(params1);
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        String tokenId = jsonObject.get("tokenId").toString();

                        Map params5 = new HashMap();
                        params5.put("tokenId", tokenId);
                        params5.put("toAddr", contollerAddr);
                        params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
                        params5.put("pwd", "172168");
                        params5.put("realID", monitoringDO.getReturnid());
                        params5.put("status", "00");
                        LampImpl.lampOpenOrCloseTurn(params5);
                    }
                }
            }
        }
        System.out.println("定时关机任务执行开始");
    }
}
