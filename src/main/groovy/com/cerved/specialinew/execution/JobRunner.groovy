package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData
import groovy.util.logging.Log4j
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.Scheduler
import org.quartz.Trigger


@Log4j
class JobRunner implements Job {

    @Override
    void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Avvio Job")

        String jName = context.getJobDetail().getKey().getName()
        String jGroup = context.getJobDetail().getKey().getGroup()
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap()
        Trigger trigger = context.getTrigger()

        JobData jobData = new JobData(jName, jGroup, jobDataMap)

        Scheduler scheduler = context.getScheduler()

        jobData.setScheduler(scheduler)


        DataProducer dataProducer = jobData.dataProducer
        DataConsumer dataConsumer = jobData.dataConsumer
        Map params = dataProducer.postIn(jobData, [:])
        jobData.jobParams = params

        params = dataConsumer.consume(dataProducer, jobData)
        params = dataProducer.postOut(jobData, params)

        jobData.toString()

        log.info("Fine Job")
    }

}
