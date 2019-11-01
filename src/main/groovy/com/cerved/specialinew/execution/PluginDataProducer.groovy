package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData

abstract class PluginDataProducer {

    abstract Map execute(JobData job, Map map)
    abstract String getDescription()

}
