package com.cyfhandsome.service;

import cn.hutool.core.date.DateUtil;
import com.cyfhandsome.utils.QuartzUtil;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cyf
 * @date 2021/11/9 10:02
 */
@Service
public class QuartzTaskTestService {

    @Resource
    private QuartzUtil quartzUtil;

    public void createTask() {
        Map<String, Object> quartzMap = new HashMap<>();
        quartzMap.put("1", "test1");
        quartzMap.put("2", "test2");
        quartzUtil.addJob(JobTestService.class, "test2", DateUtil.offsetMinute(new Date(), 1), quartzMap);
    }

    public void createTaskAsCron(String jobName) {
        Map<String, Object> quartzMap = new HashMap<>();
        quartzMap.put("1", "test1cron");
        quartzMap.put("2", "test2cron");
        quartzUtil.addJobWithCron(JobTestService.class, jobName, "0 0/1 * * * ?", quartzMap);
    }

    public boolean modifyJob(String jobName, String groupName, String cronTime) {
        return quartzUtil.modifyJob(jobName, groupName, cronTime);
    }

    public Trigger.TriggerState getJobState(String jobName, String groupName) {
        return quartzUtil.getJobState(jobName, groupName);
    }

    public void pauseJob(String name, String group) {
        quartzUtil.pauseJob(name, group);
    }

    public void resumeJob(String name, String group) {
        quartzUtil.resumeJob(name, group);
    }

    public boolean deleteJob(String name, String group) {
        return quartzUtil.deleteJob(name, group);
    }

    public void getRunningJobCountByGroup() {
        System.out.printf(String.valueOf(quartzUtil.getRunningJobCountByGroup(JobTestService.class)));
    }


}
