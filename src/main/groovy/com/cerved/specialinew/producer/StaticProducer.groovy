package com.cerved.specialinew.producer

import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.execution.DataProducer
import com.cerved.specialinew.format.ProducerFormat

class StaticProducer extends DataProducer {

    @Override
    boolean canManageFormat(ProducerFormat format) {
        return format == ProducerFormat.STATIC_PRODUCER
    }

    @Override
    List getDefaultFieldNames() {
        return ["STATIC_ID_SOGGETTO", "STATIC_ID_RICHIESTA", "STATIC_CD_PRODOTTO"]
    }

    @Override
    List getDefaultLabelNames() {
        return ["static_id_soggetto", "static_id_richiesta", "static_cd_prodotto"]
    }

    @Override
    String getTemplate() {
        return "staticTemplate"
    }

    @Override
    Map postIn(JobData job, Map params) {
        job.postInPlugins.each {plugin ->
            params = plugin.execute()
        }
        return params
    }

    @Override
    void eachRow(JobData job, Closure c) throws Exception {

        List fields = []
        [1,2,3,4,5,6,7,8].each {
            Map values = [STATIC_ID_SOGGETTO : it, STATIC_CD_PRODOTTO : it +10, STATIC_ID_RICHIESTA : it * 123]
            fields << values
        }
        // qui faccio qualcosa
        fields.each {
            c(it)
        }
        return
    }

    @Override
    Map postOut(JobData job, Map params) {
        job.postOutPlugins.each {plugin ->
            params = plugin.execute()
        }
        return params
    }

    @Override
    String getDescription() {
        return "Il produttore dati restituisce dei dati statici di test"
    }
}
