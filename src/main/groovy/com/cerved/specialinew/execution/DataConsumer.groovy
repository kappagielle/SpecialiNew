package com.cerved.specialinew.execution

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.format.ConsumerFormat
import sun.misc.Service

abstract class DataConsumer {

    abstract boolean canManageFormat(ConsumerFormat format)
    abstract File consume(DataProducer producer, JobData job)
    abstract String getTemplate()

    static DataConsumer createInstanceForFormat(ConsumerFormat format) {
        for (DataConsumer c in Service.providers(DataConsumer.class)) {
            if (c.canManageFormat(format)) {
                return c;
            }
        }
        return null;
    }
}
