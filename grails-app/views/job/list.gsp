<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Job: lista</title>
    </head>

    <body>
        <div id="leftMenu">
            <span class="menuButton"><g:link class="addJob" action="create">Job: Nuovo</g:link></span>
        </div>

        <div class="body" style="width:98% ">
            <h1>Elenco JObs</h1>

            <div class="list">
                <table>
                    <thead>
                    <tr>
                        <g:sortableColumn property="Nome" title="Nome" params="['filter' : params?.filter, 'max' : params?.max]" />
                        <g:sortableColumn property="job.group" title="Gruppo" params="['filter' : params?.filter, 'max' : params?.max]" />
                    </tr>
                    </thead>
                    <tbody>
                        <g:each in="${jobsList}" status="i" var="job">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>
                                    <g:link action="show" id="${[job.name, job.group]}">${fieldValue(bean:job, field:'name')}</g:link>
                                </td>
                                <td>${fieldValue(bean:job, field:'group')}</td>
                                <td>
                                    <g:link onClick="return confirm('Attivare:${job.name} ?')" action="activate" params="['name': job.name, 'group': job.group]"></g:link>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>