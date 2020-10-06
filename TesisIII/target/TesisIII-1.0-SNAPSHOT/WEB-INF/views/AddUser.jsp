
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
                <input type ="button" value="List ALL User"
                onclick = "location.href = 'listAllUser'"
                class ="btn btn-primary" />
                <br/>
                <hr>
                <form method="get" action="http://localhost:90/TesisIII/admin">
                    <input type="submit" value="Back"/>
                </form>
            </div>
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:300px; float: left">
                    <h3>Add New User</h3>
                    <hr>
                    <form:form method="POST"  modelAttribute="u" action="http://localhost:90/TesisIII/admin/AddUser">
                        <label>User's Login :</label><form:input path="login"/>
                        <span style ="color:red"><form:errors path="login"/></span>
                        <br>
                        <label>User's Password :</label><form:input path="password"/>
                        <span style ="color:red"><form:errors path="password"/></span>
                        <br>
                        <label for="status">User's Status :</label>
                        <form:select path ="status">
                            <form:option value="-">Select</form:option>
                            <form:option value="Y">Active</form:option>
                            <form:option value="N">Deactivate</form:option>
                        </form:select>
                        <br>
                        <label>User's First Name :</label><form:input path="firstName"/>
                        <span style ="color:red"><form:errors path="firstName"/></span>
                        <br>
                        <label>User's Last Name :</label><form:input path="lastName"/>
                        <span style ="color:red"><form:errors path="lastName"/></span>
                        <br>
                        <label>User's Email :</label><form:input path="email"/>
                        <span style ="color:red"><form:errors path="email"/></span>
                        <br>
                        <label for="profile">User's Profile :</label>
                        <form:select path ="profile">
                            <form:option value="-">Select</form:option>
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="USER">USER</form:option>
                            <form:option value="PM">PM</form:option>
                        </form:select>
                        <br>
                        <label for="managerId">User's Manager :</label>
                        <form:select path ="managerId">
                            <form:option value="0">Select</form:option>
                            <form:options items="${managerList}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                       <br>
                        <input type="submit" value="Add User">
                    </form:form>
                </div>
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
                        <c:forEach items="${requestScope.listUser}" var="usuario">
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

