package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.format.ProducerFormat

abstract class DataProducer {

    abstract boolean canManageFormat(ProducerFormat format)
    abstract void eachRow(JobData job, Closure c) throws Exception
    abstract String getDefaultFieldNames()
    abstract String getDefaultLabelNames()
    abstract Map postIn(JobData job, Map params)
    abstract Map postOut(JobData job, Map params)
    abstract String getDescription()
}
