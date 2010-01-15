
<%@ page import="org.synergis.Review" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Review</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Review List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Review</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${reviewInstance}">
            <div class="errors">
                <g:renderErrors bean="${reviewInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rating">Rating:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:reviewInstance,field:'rating','errors')}">
                                    <g:select from="${1..10}" id="rating" name="rating" value="${reviewInstance?.rating}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:reviewInstance,field:'title','errors')}">
                                    <input type="text" maxlength="50" id="title" name="title" value="${fieldValue(bean:reviewInstance,field:'title')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text">Text:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:reviewInstance,field:'text','errors')}">
                                    <textarea rows="5" cols="40" name="text">${fieldValue(bean:reviewInstance, field:'text')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="restaurant">Restaurant:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:reviewInstance,field:'restaurant','errors')}">
                                    <g:select optionKey="id" from="${org.synergis.Restaurant.list()}" name="restaurant.id" value="${reviewInstance?.restaurant?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
