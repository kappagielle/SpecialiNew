package com.cerved.specialinew.dto

import com.cerved.specialinew.execution.PluginDataProducer
import com.cerved.specialinew.format.ConsumerFormat
import com.cerved.specialinew.format.ProducerFormat
import groovy.util.logging.Log4j
import org.quartz.JobDataMap
import org.quartz.Scheduler

@Log4j
class JobData {

    String jobName
    String jobGroup
    String utenteCreazione
    String utenteModifica


    boolean isNew = true
    boolean singleInstance = false

    Date dataCreazione
    Date dataModifica

    Scheduler scheduler

    ProducerFormat producerFormat
    ConsumerFormat consumerFormat

    List<PluginDataProducer> dataProducerList



    public JobData() {
        //
    }

    public JobData(String jobName) {
        List params = jobName.split(".")
        this.jobGroup = params[0]
        this.jobName = params[1]

    }

    public JobData(String jobName, String jobGroup, JobDataMap dataMap) {
        this.jobGroup = jobGroup
        this.jobName = jobName
        setDataMap(dataMap)
    }

    public JobData(Map dataMap) {

        this.jobName = dataMap?.jobName
        this.jobGroup = dataMap?.jobGroup
        this.utenteCreazione = dataMap?.utenteCreazione
        this.utenteModifica = dataMap?.utenteModifica
        this.dataCreazione = dataMap?.dataCreazione
        this.dataModifica = dataMap?.dataModifica



    }

    public void setScheduler(Scheduler s) {
        this.scheduler = s
    }


    public String getJobName() {
        return "${this.jobGroup}.${this.jobName}"
    }

    public Map getDataMap() {
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
