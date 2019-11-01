<html>
    <head>
        <g:if test="${job.isNew}">
            <title>Job: nuovo</title>
        </g:if>
        <g:else>
            <title>Job: modifica</title>
        </g:else>
    </head>
    <body>
        <div class="body" style="width:98% ">
            <g:if test="${job.isNew}">
                <h1>Job: nuovo</h1>
            </g:if>
            <g:else>
                    <h1>Job: modifica</h1>
            </g:else>
            <g:form name="job_form">
                <div>
                    <g:if test="${!job.isNew}">
                        <span class="button"><g:submitButton class="save" name="_action_Update" value="Aggiorna" /></span>
                    </g:if>
                    <g:else>
                        <span class="button"><g:submitButton class="save" name="_action_Save" value="Crea" /></span>
                    </g:else>
                </div>
                <div>
                    <g:if test="${job.isNew}">
                        <g:field type="input" name="jobName" value="${job.jobName}" />
                    </g:if>
                    <g:else>
                        <g:field type="input" style="border:0px" name="jobName" value="${job.jobName}" readonly="readonly" />
                    </g:else>
                </div>
                <div>
                    <g:if test="${job.isNew}">
                        <g:field type="input" name="jobGroup" value="${job.jobGroup}" />
                    </g:if>
                    <g:else>
                        <g:field type="input" style="border:0px" name="jobGroup" value="${job.jobGroup}" readonly="readonly" />
                    </g:else>
                </div>

                <div><label for="utenteCreazione">Creato da:</label></div>
                <div>
                    <g:field type="input" style="border:0px" name="utenteCreazione" value="${job.utenteCreazione}" />
                </div>

                <div style="clear:both"></div>

                <div><label for="utenteModifica">Modificato da:</label></div>
                <div>
                    <g:field type="input" style="border:0px" name="utenteModifica" value="${job.utenteModifica}" />
                </div>
            </g:form>
        </div>
    </body>
</html>