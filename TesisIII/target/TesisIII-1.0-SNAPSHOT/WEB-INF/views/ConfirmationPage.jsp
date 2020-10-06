
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
                    <h2 style = "text-align:center;color:red">Do you want to remove this User?.Are you sure?!</h2>
                    <hr>      
                    <form:form action="http://localhost:90/TesisIII/admin/removeUsuario" method="POST"  modelAttribute="u">                        
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central">
                            <tr>
                                    <th>USUARIO_ID</th>
                                    <th>LOGIN</th>
                                    <th>PASSWORD</th>
                                    <th>FIRST_NAME</th>
                                    <th>LAST_NAME</th>
                                    <th>EMAIL</th>
                                    <th>PROFILE</th>
                                    <th>STATUS</th>
                                    <th>MANAGER_ID</th>
                            </tr>
                                <td>${u.id}</td>
                                <form:hidden path ="id" value= "${u.getId()}"/>
                                <td>${u.login}</td>
                                <form:hidden path ="login" value= "${u.getLogin()}"/>
                                <td>${u.password}</td>
                                <form:hidden path ="password" value= "${u.getPassword()}"/>
                                <td>${u.firstName}</td>
                                <form:hidden path ="firstName" value= "${u.getFirstName()}"/>
                                <td>${u.lastName}</td>
                                <form:hidden path ="lastName" value= "${u.getLastName()}"/>
                                <td>${u.email}</td>
                                <form:hidden path ="email" value= "${u.getEmail()}"/>
                                <td>${u.profile}</td>
                                <form:hidden path ="profile" value= "${u.getProfile()}"/>
                                <td>${u.status}</td>
                                <form:hidden path ="status" value= "${u.getStatus()}"/>
                                <td>${u.managerId}</td>
                                <form:hidden path ="managerId" value= "${u.getManagerId()}"/>
                        </table>
                        <input style = "text-align:center" type="submit" value="YES">
                    </form:form>
                    <form method="get" action="http://localhost:90/TesisIII/admin/AddUser">
                        <input type="submit" value="NO"/>
                    </form>    
    </body>
</html>


