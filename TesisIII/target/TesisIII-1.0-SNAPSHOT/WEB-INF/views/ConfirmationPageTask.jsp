
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
        <style>
             body {
                 background-color: #3e3e3e;
                 color :white;
             }
         </style>
    </head>
    <body>
        <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <h1>BARIKA's Confirmation Page</h1>
                    <h2 style = "text-align:center;color:red">Do you want to remove this TASK?.Are you sure?!</h2>
                    <hr>      
                    <form:form action="http://localhost:90/TesisIII/pm/${login}/pmRemoveTask" method="post"  modelAttribute="t">                        
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central">
                            <tr>
                                <th>TASK_ID</th>
                                <th>TITLE</th>
                                <th>DESCRIPTION</th>
                                <th>DEADLINE</th>
                                <th>STATUS</th>
                            </tr>
                                <td>${t.id}</td>
                                <form:hidden path ="id" value= "${t.getId()}"/>    
                                <td>${t.title}</td>
                                <form:hidden path ="title" value= "${t.getTitle()}"/>
                                <td>${t.description}</td>
                                <form:hidden path ="description" value= "${t.getDescription()}"/>
                                <td>${t.deadLine}</td>
                                <form:hidden path ="deadLine" value= "${t.getDeadLine()}"/>
                                <td>${t.status}</td>
                                <form:hidden path ="status" value= "${t.getStatus()}"/> 
                                <form:hidden path ="taskProject" value= "${t.getTaskProject()}"/>
                                <form:hidden path ="userTask" value= "${t.getUserTask()}"/>
                        </table>
                        <input style = "text-align:center" type="submit" value="YES">
                    </form:form>
                    <form method="get" action="http://localhost:90/TesisIII/pm/${login}">
                        <input type="hidden" name="login" value="${username}">
                        <button class="btn btn-block" type="submit">NO</button>
                    </form>   
    </body>
</html>


