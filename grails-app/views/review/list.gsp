
<%@ page import="org.synergis.Review" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Review List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Review</g:link></span>
        </div>
        <div class="body">
            <h1>Review List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="rating" title="Rating" />
                        
                   	        <g:sortableColumn property="title" title="Title" />
                        
                   	        <g:sortableColumn property="text" title="Text" />
                        
                   	        <th>Restaurant</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${reviewInstanceList}" status="i" var="reviewInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${reviewInstance.id}">${fieldValue(bean:reviewInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:reviewInstance, field:'rating')}</td>
                        
                            <td>${fieldValue(bean:reviewInstance, field:'title')}</td>
                        
                            <td>${fieldValue(bean:reviewInstance, field:'text')}</td>
                        
                            <td>${fieldValue(bean:reviewInstance, field:'restaurant')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${reviewInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
