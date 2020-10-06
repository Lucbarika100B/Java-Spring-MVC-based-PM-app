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
         <input type="hidden" name="id" value="${u.id}">
        <div>
            <div style ="width:100px; float: left; margin: 5px">
            <hr>
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
                <h3> Tasks  for User ${requestScope.userName} : </h3>
            </div>
            <br/>
            <c:choose>
            <c:when test="${(fn:length(ListTasksByAUser) >= 1)}">
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>TITLE</th>
                                <th>DESCRIPTION</th>
                                <th>DEADLINE</th>
                                <th>STATUS</th>
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.ListTasksByAUser}" var="t">
                            <tr>
                                <td>${t.title}</td>
                                <td>${t.description}</td>
                                <td>${t.deadLine}</td>
                                <td>${t.status}</td>
                                <td>
                                    <form:form method="get" action="http://localhost:90/TesisIII/user/${login}/uEditTask">
                                        <input type="hidden" name="taskProject" value="${t.taskProject}">
                                        <input type="hidden" name="taskId" value="${t.id}">
                                        <input type="hidden" name="login" value="${username}">
                                        <input type="submit" value="Edit Taks"/>
                                    </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
                <br/>
                <br/>
                </div>
            </c:when>
                <c:otherwise>
                    <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                    <h3> No project task assigned yet to you </h3>
                    </div>
                </c:otherwise> 
            </c:choose>
        </div>
    </body>
</html>
