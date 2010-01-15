
<%@ page import="org.synergis.Restaurant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Restaurant List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Restaurant</g:link></span>
        </div>
        <div class="body">
            <h1>Restaurant List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="address" title="Address" />
                        
                   	        <g:sortableColumn property="city" title="City" />
                        
                   	        <g:sortableColumn property="openingHours" title="Opening Hours" />
                        
                   	        <g:sortableColumn property="phoneNumber" title="Phone Number" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${restaurantInstanceList}" status="i" var="restaurantInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${restaurantInstance.id}">${fieldValue(bean:restaurantInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:restaurantInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:restaurantInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:restaurantInstance, field:'city')}</td>
                        
                            <td>${fieldValue(bean:restaurantInstance, field:'openingHours')}</td>
                        
                            <td>${fieldValue(bean:restaurantInstance, field:'phoneNumber')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${restaurantInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
