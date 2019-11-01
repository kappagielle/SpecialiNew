import com.cerved.specialinew.QuartzService
import com.cerved.specialinew.dto.JobData
import org.quartz.JobDetail
import org.quartz.impl.JobDetailImpl

class JobController {

    QuartzService quartzService

    def index() {
        redirect(action: list)
    }

    def list = {
        List jobsList = quartzService.getAllJobs()
        ["jobsList": jobsList, "totalList": jobsList.size()]
    }

    def create = {
        render(view: 'edit', model: [job : new JobData()])
    }

    def save = {

        params.utenteCreazione = "Gian"
        params.utenteModifica = "Luigi"
        JobData jobData = new JobData(params)

        JobDetailImpl jobDetail = new JobDetailImpl(params.jobName, params.jobGroup, com.cerved.specialinew.execution.JobRunner.class)
        jobDetail.jobDataMap = jobData.getDataMap()
        quartzService.addJob(jobDetail, true)
        jobData.isNew = false
        redirect(action:list, id: "[${params.jobName}, ${params.jobGroup}]")


    }
}
