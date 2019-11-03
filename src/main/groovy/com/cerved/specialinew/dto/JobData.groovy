package com.cerved.specialinew.dto

import com.cerved.specialinew.execution.DataConsumer
import com.cerved.specialinew.execution.DataProducer
import com.cerved.specialinew.plugin.PluginDataProducer
import com.cerved.specialinew.sender.JobFtp
import com.cerved.specialinew.sender.JobMail
import groovy.util.logging.Log4j
import org.quartz.JobDataMap
import org.quartz.Scheduler

@Log4j
class JobData {

    String jobName
    String jobGroup
    String utenteCreazione
    String utenteModifica
    boolean singleInstance = true
    boolean notArchive = false

    JobFile jobFile

    Date dataCreazione
    Date dataModifica

    String urlGrip
    String urlWiki

    Scheduler scheduler

    Map jobParams
    Map customizeParams

    DataProducer dataProducer
    DataConsumer dataConsumer

    List<PluginDataProducer> postInPlugins
    List<PluginDataProducer> executionPlugins
    List<PluginDataProducer> postOutPlugins

    List<Field> fieldList


    JobFtp jobFtp
    JobMail jobMail

    List warningList
    List errorList

    JobMail jobMailWarning
    JobMail jobMailError

    JobData() {
        //
    }

    JobData(String jobName) {
        List params = jobName.split(".")
        this.jobGroup = params[0]
        this.jobName = params[1]

    }

    JobData(String jobName, String jobGroup, JobDataMap dataMap) {
        this.jobGroup = jobGroup
        this.jobName = jobName
        setDataMap(dataMap)
    }

    JobData(Map dataMap) {

        this.jobName = dataMap?.jobName
        this.jobGroup = dataMap?.jobGroup
        this.utenteCreazione = dataMap?.utenteCreazione
        this.utenteModifica = dataMap?.utenteModifica
        this.dataCreazione = dataMap?.dataCreazione
        this.dataModifica = dataMap?.dataModifica
        this.singleInstance = dataMap?.singleInstance
        this.notArchive = dataMap?.notArchive

        this.fileName = dataMap?.fileName

        this.dataProducer = dataMap?.dataProducer
        this.dataConsumer = dataMap?.dataConsumer

        this.jobParams = dataMap?.jobParams
        this.customizeParams = dataMap?.customizeParams

        this.fieldList = dataMap?.fieldList

        this.postInPlugins = dataMap?.postInPlugins
        this.executionPlugins = dataMap?.executionPlugins
        this.postOutPlugins = dataMap?.postOutPlugins

        this.jobFtp = dataMap?.jobFtp
        this.jobMail = dataMap?.jobMail
        this.urlWiki = dataMap?.urlWiki
        this.urlGrip = dataMap?.urlGrip

    }

    void setScheduler(Scheduler s) {
        this.scheduler = s
    }

    String getJobName() {
        return "${this.jobGroup}.${this.jobName}"
    }

    Map getDataMap() {
        return ['utenteCreazione' : this.utenteCreazione, 'utenteModifica' : this.utenteModifica]
    }

    protected void setDataMap(JobDataMap map) {
        this.utenteCreazione = map?.utenteCreazione
        this.utenteModifica = map?.utenteModifica
        //this.dataCreazione = map?.dataCreazione
        //this.dataModifica = map?.dataModifica
    }

    String toString() {
        log.info("Job lanciato $jobName $jobGroup")
    }

}
