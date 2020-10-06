
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
<!--                <input type ="button" value="Edit User"
                onclick = "location.href = 'EditUser'"
                class ="btn btn-primary" />
                <br/>
                <input type ="button" value="Delete User"
                onclick = "location.href = 'DeleteUser'"
                class ="btn btn-primary" />
                <br/>-->
<!--                <input type ="button" value="List ALL User"
                onclick = "location.href = 'ListUser'"
                class ="btn btn-primary" />
                <br/>-->
                <form method="get" action="http://localhost:90/TesisIII/admin/AddUser">
                    <input type="submit" value="Back"/>
                </form>
            </div>
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:300px; float: left">
                    <h3>Edit User</h3>
                    <hr>      
                    <form:form method="POST"  modelAttribute="u">
                        <form:hidden path ="id" value= "${u.getId()}"/>   
                        <label>User's Login :</label><form:input path="login" value="${u.getLogin()}"/>
                        <span style ="color:red"><form:errors path="login"/></span>
                        <br>
                        <label>User's Password :</label><form:input path="password" value="${u.getPassword()}"/>
                        <span style ="color:red"><form:errors path="password"/></span>
                        <br>
                        <label for="status">User's Status :</label>
                        <form:select path ="status" value="${u.getStatus()}">
                            <form:option value="-">Select</form:option>
                            <form:option value="Y">Active</form:option>
                            <form:option value="N">Deactivate</form:option>
                        </form:select>
                        <br>
                        <label>User's First Name :</label><form:input path="firstName" value="${u.getFirstName()}"/>
                        <span style ="color:red"><form:errors path="firstName"/></span>
                        <br>
                        <label>User's Last Name :</label><form:input path="lastName" value="${u.getLastName()}"/>
                        <span style ="color:red"><form:errors path="lastName"/></span>
                        <br>
                        <label>User's Email :</label><form:input path="email" value="${u.getLastName()}"/>
                        <span style ="color:red"><form:errors path="email"/></span>
                        <br>
                        <label for="profile">User's Profile :</label>
                        <form:select path ="profile" value="${u.getProfile()}">
                            <form:option value="-">Select</form:option>
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="USER">USER</form:option>
                            <form:option value="PM">PM</form:option>
                        </form:select>
                        <br>
                        <label for="managerId">User's Manager :</label>
                        <form:select path ="managerId" value="$u.getManagerId">
                            <form:option value="0">Select</form:option>
                            <form:options items="${managerList}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                       <br>
                        <input type="submit" value="Update User">
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>

