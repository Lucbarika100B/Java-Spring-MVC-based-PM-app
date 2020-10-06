
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
                    <h2 style = "text-align:center;color:red">Do you want to remove this PROJECT?.Are you sure?!</h2>
                    <hr>      
                    <form:form action="http://localhost:90/TesisIII/admin/removeProject" method="post"  modelAttribute="p">                        
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central">
                            <tr>
                                <th>PROJECT_ID</th>
                                <th>MANAGER_ID</th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>START_DATE</th>
                                <th>END_DATE</th>
                            </tr>
                                <td>${p.id}</td>
                                <form:hidden path ="id" value= "${p.getId()}"/>    
                                <td>${p.manager}</td>
                                <form:hidden path ="manager" value= "${p.getManager()}"/>
                                <td>${p.name}</td>
                                <form:hidden path ="name" value= "${p.getName()}"/>
                                <td>${p.description}</td>
                                <form:hidden path ="description" value= "${p.getDescription()}"/>
                                <td>${p.startDate}</td>
                                <form:hidden path ="startDate" value= "${p.getStartDate()}"/>
                                <td>${p.endDate}</td>  
                                <form:hidden path ="endDate" value= "${p.getEndDate()}"/>
                                <form:hidden path ="pooPId" value= "${p.getPooPId()}"/>
                        </table>
                        <input style = "text-align:center" type="submit" value="YES">
                    </form:form>
                    <form method="get" action="http://localhost:90/TesisIII/admin/AProject">
                        <input type="submit" value="NO"/>
                    </form>    
    </body>
</html>


