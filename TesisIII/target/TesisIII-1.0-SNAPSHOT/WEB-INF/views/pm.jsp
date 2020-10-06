<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <input type="hidden" name="login" value="${username}">
        <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <h1>BARIKA's Project Manager  Page</h1>
        <div>
            <div style ="width:100px; float: left; margin: 5px">
            <hr>
            <br/>
                <form method="get" action="http://localhost:90/TesisIII/login">            
                    <button class="btn btn-block" type="submit">Logout</button>
                </form>
            <br/>
            <hr>
            </div>
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <h3>Projects List : </h3>
                <hr>
                <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>PROJECT NAME</th>
                                <th>PROJECT DESCRIPTION</th>
                                <th>PROJECT_STATUS</th>
                                <th>START_DATE</th>
                                <th>END_DATE</th>
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.projectListByManager}" var="p">
                            <tr>
                                <td>${p.name}</td>
                                <td>${p.description}</td>
                                <td>${p.pStatus}</td>
                                <td>${p.startDate}</td>
                                <td>${p.endDate}</td>
                                <td>
                                    <form:form method="post" action="http://localhost:90/TesisIII/pm/${login}/PmUsersByProject">
                                        <input type="hidden" name="ProjectName" value="${p.name}">
                                        <input type="hidden" name="ProjectId" value="${p.id}">
                                        <input type="submit" value="Users"/>
                                    </form:form>
                                    <form:form method="get" action="http://localhost:90/TesisIII/pm/${login}/pmTaskByProject">
                                       <input type="hidden" name="Projectid" value="${p.id}"> 
                                       <input type="submit" value="Taks"/>
                                     </form:form>
                                       <form:form method="get" action="http://localhost:90/TesisIII/pm/${login}/pmEditProject">
                                       <input type="hidden" name="Projectid" value="${p.id}">
                                       <input type="submit" value="Edit"/>
                                     </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
            </div>
        </div>
    </body>
</html>