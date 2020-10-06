<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Manager Page</title>
   <style>
        body {
            background-color: #3e3e3e;
            color :white;
        }
    </style>
    </head>
    <body>
        <h1>BARIKA's Project Manager  Page</h1>
         <input type="hidden" name="login" value="${username}">
         <input type="hidden" name="ProjectName" value="${p.name}">
        <div>
            <div style ="width:100px; float: left; margin: 5px">
            <hr>
            <br/>
            <br/>
            <br/>
                <form method="get" action="http://localhost:90/TesisIII/pm/${login}">            
                    <button class="btn btn-block" type="submit">back</button>
                </form>
            <br/>
            <br/>
            <br/>
                <form method="post" action="http://localhost:90/TesisIII/login">            
                    <button class="btn btn-block" type="submit">Logout</button>
                </form>
            <br/>
            <br/>
            <br/>
            <hr>
            </div>
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <h3> Users for Project ${requestScope.ProjectName} : </h3>
            </div>
            <br/>
                <c:if test="${(fn:length(listUserIdByManager) == 1) or ((fn:length(listUserIdByManager) > 1))}">
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <h3> Available Users: </h3>
                <hr>
                <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>FIRST_NAME</th>
                                <th>LAST_NAME</th>
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.listUserIdByManager}" var="u">
                            <tr>
                                <td>${u.firstName}</td>
                                <td>${u.lastName}</td>
                                <td>
                                    <form:form method="post" action="http://localhost:90/TesisIII/pm/${login}/PmAssigneUsersToProject">
                                        <input type="hidden" name="Projectid" value="${requestScope.Projectid}">   
                                        <input type="hidden" name="id" value="${u.id}">                                       
                                        <input type="submit" value="Assign"/>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                <br/>
                <br/>
            </div>
            </c:if>
            <c:if test="${fn:length(listUserAssigned) >= 1}">       
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <h3> Assigned Users: </h3>
                <hr>
                <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>FIRST_NAME</th>
                                <th>LAST_NAME</th>
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.listUserAssigned}" var="u">
                            <tr>
                                <td>${u.firstName}</td>
                                <td>${u.lastName}</td>
                                <td>
                                    <form:form method="post" action="http://localhost:90/TesisIII/pm/${login}/PmUnassaingUsersToProject">
                                        <input type="hidden" name="id" value="${u.id}">
                                        <input type="submit" value="Unassign"/>
                                    </form:form>
                                    <form:form method="get" action="http://localhost:90/TesisIII/pm/${login}/pmTasksUsersToProject">
                                        <input type="hidden" name="id" value="${u.id}">
                                        <input type="hidden" name="login" value="${username}">
                                        <input type="hidden" name="ProjectName" value="${p.name}">
                                        <input type="hidden" name="Projectid" value="${requestScope.Projectid}">
                                        <input type="submit" value="Tasks"/>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </body>
</html>
