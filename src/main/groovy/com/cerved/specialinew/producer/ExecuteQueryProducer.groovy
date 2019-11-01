package com.cerved.specialinew.producer

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.execution.DataProducer
import com.cerved.specialinew.format.ProducerFormat

public class ExecuteQueryProducer extends DataProducer {


    @Override
    boolean canManageFormat(ProducerFormat format) {
        return format == ProducerFormat.EXECUTE_QUERY;
    }

    @Override
    void eachRow(JobData job, Closure c) throws Exception {
        return
    }

    @Override
    String getDefaultFieldNames() {
        return "Il produttore restituisce i campi della query"
    }

    @Override
    String getDefaultLabelNames() {
        return ""
    }
}
