
quartz {
    autoStartup = true
    jdbcStore = true
}
environments {

    development {
        quartz {
            org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreCMT
            org.quartz.dataSource.myDS.driver = com.mysql.jdbc.Driver
            tablePrefix = "QRTZ_"
        }
    }

    test {
        quartz {
            autoStartup = false
        }
    }

    production {
        quartz {

        }
    }
}