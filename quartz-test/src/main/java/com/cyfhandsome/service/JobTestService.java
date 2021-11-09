package com.cyfhandsome.service;

import cn.hutool.core.date.DateUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author cyf
 * @date 2021/11/8 17:36
 */
public class JobTestService implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("---------------------------执行任务-------------------".concat(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")));
        jobDataMap.values().forEach(System.out::println);
    }
}
