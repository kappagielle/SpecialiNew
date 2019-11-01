package com.cerved.specialinew

import grails.gorm.transactions.Transactional
import groovy.util.logging.Log4j
import org.quartz.JobDetail
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerKey
import org.quartz.impl.JobDetailImpl
import org.quartz.impl.matchers.GroupMatcher

@Log4j
@Transactional
class QuartzService {

    Scheduler quartzScheduler


    def addJob(JobDetailImpl jobDetail, boolean replace) {
        jobDetail.setDurability(true)
        quartzScheduler.addJob(jobDetail, replace)
    }

    def pauseJob(def name, def group) {
        JobKey jobKey = new JobKey(name, group)
        quartzScheduler.pauseJob(jobKey)
    }

    def deleteJob(def name, def group) {
        JobKey jobKey = new JobKey(name, group)
        quartzScheduler.deleteJob(jobKey)
    }

    def addTrigger(Trigger trigger) {
        quartzScheduler.scheduleJob(trigger)
    }

    def deleteTrigger(String name, String group) {
        TriggerKey triggerKey = new TriggerKey()
        quartzScheduler.unscheduleJob(name, group)
    }

    def pauseTrigger(String name, String group) {
        TriggerKey triggerKey = TriggerKey(name, group)
        quartzScheduler.pauseTrigger(triggerKey)
    }

    def resumeTrigger(String name, String group) {
        TriggerKey triggerKey = new TriggerKey(name, group)
        quartzScheduler.resumeTrigger()
    }

    Scheduler getQuartzScheduler() {
        return quartzScheduler
    }

    def triggerWithNameInGroup(def name, def group) {
        TriggerKey triggerKey = new TriggerKey(name, group)
        return quartzScheduler.getTrigger(triggerKey)
    }

    def getAllJobs() {
        def jobList = []
        quartzScheduler.getJobGroupNames().each { groupName ->
            quartzScheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupName)).each { jobKey ->
                jobList.add(quartzScheduler.getJobDetail(jobKey))
            }
        }
        return jobList
    }

    def getAllTriggers() {
        def triggerList = []
        quartzScheduler.getTriggerGroupNames().each { groupName ->
            quartzScheduler.getTriggerKeys(GroupMatcher.<JobKey>groupEquals(groupName)).each { triggerKey ->
                triggerList.add(quartzScheduler.getTrigger(triggerKey))
            }
        }
        return triggerList
    }

}
