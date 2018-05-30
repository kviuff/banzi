package com.bootdo.common.task;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.monitoring.domain.HistoryDO;
import com.bootdo.monitoring.service.HistoryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时删除机器历史数据
 */
@Component
public class DeleteMonitorHistoryJob implements Job {


    @Autowired
    private HistoryService historyService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("定时删除机器历史数据开始");
        Map<String, Object> map = new HashMap<>();
        List<HistoryDO> historyDOList = historyService.list(map);

        for (HistoryDO historyDO : historyDOList) {
            System.out.println("有数据");
            long createTime = historyDO.getCreateTime(); // 创建时间
            long nowTime = System.currentTimeMillis(); // 当前时间

            int result = DateUtils.differentDays(createTime, nowTime);

            if (result > 30) {
                System.out.println("删除30天以前的数据");
                historyService.remove(historyDO.getId());
            }
        }
        System.out.println("定时删除机器历史数据结束");

    }
}
