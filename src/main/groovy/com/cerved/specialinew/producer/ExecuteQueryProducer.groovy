package com.cerved.specialinew.producer

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.execution.DataProducer
import com.cerved.specialinew.format.ProducerFormat
import com.cerved.specialinew.plugin.PluginDataProducer

class ExecuteQueryProducer extends DataProducer {

    @Override
    boolean canManageFormat(ProducerFormat format) {
        return format == ProducerFormat.EXECUTE_QUERY;
    }

    @Override
    List getDefaultFieldNames() {
        return null
    }

    @Override
    List getDefaultLabelNames() {
        return null
    }

    @Override
    String getTemplate() {
        return "executeQueryTemplate"
    }

    @Override
    void eachRow(JobData job, Closure c) throws Exception {


        String istanza = job.customizeParams.producer_nome_istanza
        String query = job.customizeParams.producer_query



    }



    @Override
    Map postIn(JobData job, Map params) {
        job.postInPlugins.each { plugin ->
            params = plugin.execute(job, params)
        }
        return params
    }

    @Override
    Map postOut(JobData job, Map params) {

        job.postOutPlugins.each { plugin ->
            params = plugin.execute(job, params)
        }
        return params
    }

    @Override
    String getDescription() {
        return "Il produttore permette di eseguire una query e di portare il risultato in un file"
    }
}
