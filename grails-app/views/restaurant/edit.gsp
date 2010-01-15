
<%@ page import="org.synergis.Restaurant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Restaurant</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Restaurant List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Restaurant</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Restaurant</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${restaurantInstance}">
            <div class="errors">
                <g:renderErrors bean="${restaurantInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${restaurantInstance?.id}" />
                <input type="hidden" name="version" value="${restaurantInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:restaurantInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'address','errors')}">
                                    <textarea rows="5" cols="40" name="address">${fieldValue(bean:restaurantInstance, field:'address')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'city','errors')}">
                                    <input type="text" maxlength="50" id="city" name="city" value="${fieldValue(bean:restaurantInstance,field:'city')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="openingHours">Opening Hours:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'openingHours','errors')}">
                                    <input type="text" maxlength="200" id="openingHours" name="openingHours" value="${fieldValue(bean:restaurantInstance,field:'openingHours')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Phone Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'phoneNumber','errors')}">
                                    <input type="text" maxlength="15" id="phoneNumber" name="phoneNumber" value="${fieldValue(bean:restaurantInstance,field:'phoneNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:restaurantInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="website">Website:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'website','errors')}">
                                    <input type="text" id="website" name="website" value="${fieldValue(bean:restaurantInstance,field:'website')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="additionalComments">Additional Comments:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:restaurantInstance,field:'additionalComments','errors')}">
                                    <textarea rows="5" cols="40" name="additionalComments">${fieldValue(bean:restaurantInstance, field:'additionalComments')}</textarea>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
