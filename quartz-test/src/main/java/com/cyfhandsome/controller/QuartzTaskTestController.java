package com.cyfhandsome.controller;

import com.cyfhandsome.service.QuartzTaskTestService;
import org.quartz.Trigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cyf
 * @date 2021/11/9 10:14
 */
@RestController
@RequestMapping("/quartzTask")
public class QuartzTaskTestController {

    @Resource
    private QuartzTaskTestService quartzTaskTestService;

    @GetMapping("/addTask")
    public void addTask()  {
        quartzTaskTestService.createTask();
    }

    @GetMapping("/addTaskCron")
    public void addTaskCron(@RequestParam String jobName) {
        quartzTaskTestService.createTaskAsCron(jobName);
    }

    @GetMapping("/getRunningJobCountByGroup")
    public void getRunningJobCountByGroup()  {
        quartzTaskTestService.getRunningJobCountByGroup();
    }
    @GetMapping("/modifyJob")
    public void modifyJob(@RequestParam String jobName, @RequestParam String groupName, @RequestParam String cronTime)  {
        quartzTaskTestService.modifyJob(jobName, groupName, cronTime);
    }
    @GetMapping("/getJobState")
    public Trigger.TriggerState getJobState(@RequestParam String jobName, @RequestParam String groupName)  {
       return quartzTaskTestService.getJobState(jobName, groupName);
    }
    @GetMapping("/pauseJob")
    public void pauseJob(@RequestParam String name, @RequestParam String group)  {
        quartzTaskTestService.pauseJob(name, group);
    }
    @GetMapping("/resumeJob")
    public void resumeJob(@RequestParam String name, @RequestParam String group) {
        quartzTaskTestService.resumeJob(name, group);
    }
    @GetMapping("/deleteJob")
    public boolean deleteJob(@RequestParam String name, @RequestParam String group)  {
      return   quartzTaskTestService.deleteJob(name, group);
    }

}
