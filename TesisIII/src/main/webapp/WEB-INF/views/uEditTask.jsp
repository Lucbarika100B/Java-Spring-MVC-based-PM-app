
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
        <input type="hidden" name="login" value="${username}">
        <h1>BARIKA's Project Manager Page Page</h1>
        <div>
             <div style ="width:100px; float: left; margin: 5px">
            <hr>
            <br/>
            <br/>
            <br/>
                <form method="get" action="http://localhost:90/TesisIII/user/${login}">            
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
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:400px; float: left">
                    <h3>Edit Task</h3>
                    <hr>
                    <form:form method="post"  modelAttribute="t">
                        <form:hidden path ="id" value= "${t.getId()}"/>
                        <form:hidden path ="taskProject" value= "${t.getTaskProject()}"/>
                        <label>Task TITLE : ${t.getTitle()}</label>
                        <br>
                        <br>
                        <label>Task DESCRIPTION : ${t.getDescription()}
                        <br>
                        <br>
                        <label>Task DEADLINE (mm/dd/yy): ${t.getDeadLine()}</label>
                        <br>
                        <br>
                        <label for="status">Task STATUS:</label>
                        <form:select path ="status" value="${t.getStatus()}">
                            <form:option value="UNASSIGN">Unassign</form:option>
                            <form:option value="IN PROGRESS">In Progress</form:option>
                            <form:option value="COMPLETE">Complete</form:option>
                        </form:select>
                        <br>
                        <br>
                        <form:hidden path ="userTask" value= "${t.getUserTask()}"/>
                       <br>
                       <br>
                       <input type="submit" value="Update Task">
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>


