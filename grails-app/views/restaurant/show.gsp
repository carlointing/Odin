
<%@ page import="org.synergis.Restaurant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Restaurant</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Restaurant List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Restaurant</g:link></span>
        </div>
        <div class="body">
            <h1>Show Restaurant</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'address')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">City:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'city')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Opening Hours:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'openingHours')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'phoneNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'email')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Website:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'website')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Additional Comments:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:restaurantInstance, field:'additionalComments')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${restaurantInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
