
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
        <h1>BARIKA's ADMIN Page</h1>
        <div>
            <div style ="width:100px; float: left; margin: 10px">
                <form method="get" action="http://localhost:90/TesisIII/admin/AddUser">
                    <input type="submit" value="Users"/>
                </form>
                <br>
                
                <form method="get" action="http://localhost:90/TesisIII/admin">
                    <input type="submit" value="Back"/>
                </form>
            </div>
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:1000px; float: left">
                    <h3>Users List</h3>
                    <hr>
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
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
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.listAllUser}" var="usuario">
                            <tr>
                                <td>${usuario.id}</td>
                                <td>${usuario.login}</td>
                                <td>${usuario.password}</td>
                                <td>${usuario.firstName}</td>
                                <td>${usuario.lastName}</td>
                                <td>${usuario.email}</td>
                                <td>${usuario.profile}</td>
                                <td>${usuario.status}</td>
                                <td>${usuario.managerId}</td>
                                <td>
                                    <form:form method="get" modelAttribute="usuario" action="http://localhost:90/TesisIII/admin/editUsuario">
                                        <input type="hidden" name="idselected" value="${usuario.id}">
                                        <input type="submit" value="Edit"/>
                                    </form:form>
                                    <form:form method="get" action="http://localhost:90/TesisIII/admin/removeUsuario">
                                       <input type="hidden" name="idselected" value="${usuario.id}"> 
                                       <input type="submit" value="Remove"/>
                                     </form:form>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                </div>
            </div>
        </div>
    </body>
</html>

