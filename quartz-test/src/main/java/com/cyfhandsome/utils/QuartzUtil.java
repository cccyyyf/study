package com.cyfhandsome.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author cyf
 * @date 2021/11/8 16:53
 */
@Slf4j
@Component
public class QuartzUtil {


    /**
     * 任务
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 任务名前缀
     */
    private static final String JOB_NAME_PREFIX = "job_";

    /**
     * 指定时间后执行任务(只会执行一次)
     *
     * @param triggerStartTime 指定时间
     */
    @SneakyThrows
    public void addJob(Class<? extends Job> jobClass, String triggerName, Date triggerStartTime, Map<String, Object> params) {
        // 使用job类名作为组名
        String groupName = jobClass.getSimpleName();

        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, groupName).startAt(triggerStartTime).build();

        // 将触发器与任务绑定到调度器内
        this.scheduleJob(jobClass, groupName, triggerName, params, trigger);
    }

    /**
     * 带触发器的任务(执行多次)
     *
     * @param cronExpression 定时任务表达式
     */
    @SneakyThrows
    public void addJobWithCron(Class<? extends Job> jobClass, String triggerName, String cronExpression, Map<String, Object> params) {
        // 使用job类名作为组名
        String groupName = jobClass.getSimpleName();

        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName, groupName).withSchedule(cronScheduleBuilder).build();

        // 将触发器与任务绑定到调度器内
        this.scheduleJob(jobClass, groupName, triggerName, params, cronTrigger);
    }

    /**
     * 带触发器的任务，同时指定时间段(立马执行)
     *
     * @param timeoutSeconds 超时时间(秒)
     * @param cronExpression 定时任务表达式
     */
    @SneakyThrows
    public void addJobWithCron(Class<? extends Job> jobClass, String triggerName, String cronExpression, long timeoutSeconds, Map<String, Object> params) {
        // 使用job类名作为组名
        String groupName = jobClass.getSimpleName();

        // 计算结束时间
        Date endDate = DateUtil.offsetSecond(new Date(), (int) timeoutSeconds);

        // 基于表达式构建触发器，同时指定时间段
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName, groupName)
                .startNow().endAt(endDate).withSchedule(cronScheduleBuilder).build();

        // 将触发器与任务绑定到调度器内
        this.scheduleJob(jobClass, groupName, triggerName, params, cronTrigger);
    }

    /**
     * 创建任务
     * @param jobClass 任务类型
     * @param groupName 组名
     * @param triggerName trigger名称
     * @param params 参数
     * @param trigger 触发器
     */
    @SneakyThrows
    private void scheduleJob(Class<? extends Job> jobClass, String groupName, String triggerName, Map<String, Object> params, Trigger trigger) {
        String  jobName = JOB_NAME_PREFIX.concat(triggerName);
        log.info("创建任务，任务名称：{}", jobName);

        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, groupName).build();

        // 添加参数
        jobDetail.getJobDataMap().putAll(params);

        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 删除某个任务
     */
    @SneakyThrows
    public boolean deleteJob(String jobName, String group) {
        JobKey jobKey = new JobKey(jobName, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            throw new RuntimeException("任务不存在");
        }
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 修改某个任务的执行时间
     * @param group 组名
     * @param triggerName triggerName
     * @param time cron表达式
     */
    @SneakyThrows
    public boolean modifyJob(String triggerName, String group, String time) {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(triggerName, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, group).withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 获取任务状态
     *  @param group 组名
     *  @param triggerName triggerName
     */
    @SneakyThrows
    public Trigger.TriggerState getJobState(String triggerName, String group) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, group);

        return scheduler.getTriggerState(triggerKey);
    }

    /**
     * 获取任务状态
     */
    @SneakyThrows
    public Trigger.TriggerState getJobState(TriggerKey triggerKey) {
        return scheduler.getTriggerState(triggerKey);
    }

    /**
     * 暂停所有任务
     */
    @SneakyThrows
    public void pauseAllJob() {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     * @param jobName 任务名称
     * @param group 组
     */
    @SneakyThrows
    public void pauseJob(String jobName, String group) {
        JobKey jobKey = new JobKey(jobName, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            throw new RuntimeException("任务不存在");
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     */
    @SneakyThrows
    public void resumeAllJob() {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     */
    @SneakyThrows
    public void resumeJob(String jobName, String group) {
        JobKey jobKey = new JobKey(jobName, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            throw new RuntimeException("任务不存在");
        }
        scheduler.resumeJob(jobKey);
    }

    /**
     * 通过group查询有多少个运行的任务
     */
    @SneakyThrows
    public long getRunningJobCountByGroup(Class<? extends Job> jobClass) {
        String groupName = jobClass.getSimpleName();
        GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupEquals(groupName);
        Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
        if (CollUtil.isNotEmpty(jobKeySet)) {
            jobKeySet.forEach(System.out::println);
            return jobKeySet.stream().filter(d -> d.getGroup().equals(groupName)).count();
        }
        return 0;
    }

}
