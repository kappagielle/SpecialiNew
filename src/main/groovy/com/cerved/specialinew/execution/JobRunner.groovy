package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData
import groovy.util.logging.Log4j
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.Scheduler


@Log4j
class JobRunner implements Job {

    @Override
    void execute(JobExecutionContext context) throws JobExecutionException {

        String jName = context.getJobDetail().getKey().getName()
        String jGroup = context.getJobDetail().getKey().getGroup()
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap()

        log.info("Avvio Job")

        JobData jobData = new JobData(jName, jGroup, jobDataMap)

        Scheduler scheduler = context.getScheduler()

        jobData.setScheduler(scheduler)

        jobData.toString()

        log.info("Fine Job")
    }

}
