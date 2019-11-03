package com.cerved.specialinew.consumer

import com.cerved.specialinew.constants.AllignmentField
import com.cerved.specialinew.constants.RowDelimiter
import com.cerved.specialinew.constants.TypeField
import com.cerved.specialinew.dto.Field
import com.cerved.specialinew.dto.JobData
import com.cerved.specialinew.execution.DataConsumer
import com.cerved.specialinew.execution.DataProducer
import com.cerved.specialinew.format.ConsumerFormat

import java.text.SimpleDateFormat

class TextConsumer extends DataConsumer{

    @Override
    String getTemplate() {
        return "textConsumerTemplate"
    }

    @Override
    boolean canManageFormat(ConsumerFormat format) {
        return format == ConsumerFormat.TEXT_FILE
    }

    @Override
    Map consume(DataProducer producer, JobData job) {

        File tmpDir = new File (System.getProperty("user.home"), "tmp/" + UUID.randomUUID().toString());
        tmpDir.mkdirs()
        File outputFile = new File(tmpDir, "${job.jobFile.fileName}.${job.jobFile.fileExtension}")
        outputFile.withWriter { writer ->
            int rowCount = 0
            String intestazione
            producer.eachRow(job) { row ->
                if(rowCount == 0) {
                    job.fieldList.each {
                        intestazione = intestazione != null ? job.jobFile.separatorFields + filterValue(it.label, it) : it.label
                    }
                    intestazione += job.jobFile.rowDelimiter == RowDelimiter.DOS ? '\r\n' : '\n'
                    writer.write(intestazione)
                }
                String textRow
                job.fieldList.each { field ->
                    def object = row[field.fieldName]
                    textRow += textRow != null ? job.jobFile.separatorFields + filterValue(object, field) : filterValue(object, field)
                }
                textRow += job.jobFile.rowDelimiter == RowDelimiter.DOS ? '\r\n' : '\n'
                writer.write(textRow)
            }
        }

        return outputFile
    }


    def filterValue(def fieldValue, Field field) {

        String value

        if(fieldValue.class in [java.lang.Integer, java.lang.Float, java.lang.Double, java.lang.Long, java.math.BigDecimal, java.math.BigInteger]) {
            if(value != null) {
                value = field.pattern ? String.format(field.pattern, fieldValue) : ""
            } else {
                value = ""
            }
        } else if(fieldValue.class in [java.util.Date, java.sql.Date, java.sql.Timestamp]) {
            if(fieldValue) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(field.pattern)
                value = field.pattern ? simpleDateFormat.parse(fieldValue) : fieldValue.toString()
            } else {
                value = ""
            }
        }

        if(field.size > 0) {
            if(field.charPadding) {
                if(field.allignmentField == AllignmentField.SINISTRA) {
                    value = value.padLeft(field.size, field.charPadding)
                } else {
                    value = value.padRight(field.size, field.charPadding)
                }
            } else {
                value = value.padLeft(field.size)
            }
        }

        return value
    }

}
