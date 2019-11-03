package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.format.ProducerFormat
import com.cerved.specialinew.plugin.PluginDataProducer

abstract class DataProducer {

    abstract boolean canManageFormat(ProducerFormat format)
    abstract List getDefaultFieldNames()
    abstract List getDefaultLabelNames()
    abstract String getTemplate()
    abstract Map postIn(JobData job, Map params)
    abstract void eachRow(JobData job, Closure c) throws Exception
    abstract Map postOut(JobData job, Map params)
    abstract String getDescription()

}
