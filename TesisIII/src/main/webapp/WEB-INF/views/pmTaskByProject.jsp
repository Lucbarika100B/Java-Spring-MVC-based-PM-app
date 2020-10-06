
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
        <input type="hidden" name="Projectid" value="${p.id}"> 
        <h1>BARIKA's Project Manager Page Page</h1>
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
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:400px; float: left">
                    <h3>Add New Task</h3>
                    <hr>
                    <form:form method="post"  modelAttribute="t" action="http://localhost:90/TesisIII/pm/${login}/pmTaskByProject">
                        <label>Task TITLE</label><form:input path="title"/>
                        <span style ="color:red"><form:errors path="title"/></span>
                        <br>
                        <br>
                        <label>Task DESCRIPTION:</label><form:input path="description"/>
                        <span style ="color:red"><form:errors path="description"/></span>
                        <br>
                        <br>
                        <label>Task DEADLINE (mm/dd/yy):</label><form:input path="deadLine"/>
                        <span style ="color:red"><form:errors path="deadLine"/></span>
                        <br>
                        <br>
                        <label for="status">Task STATUS:</label>
                        <form:select path ="status">
                            <form:option value="UNASSIGN">Unassign</form:option>
                            <form:option value="IN PROGRESS">In Progress</form:option>
                            <form:option value="COMPLETE">Complete</form:option>
                        </form:select>
                        <br>
                        <br>
                        <label for="userTask">Task's Manager :</label>
                         <form:select path ="userTask">
                            <form:option value="0">Select</form:option>
                            <form:options items="${listUserAssigned}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                       <br>
                       <br>
                       <form:hidden path ="taskProject" value= "${requestScope.Projectid}"/>
                       <input type="submit" value="Add Task">
                    </form:form>
                </div>
                <div style ="width:1000px; float: left">
                    <h3>Tasks List</h3>
                    <hr>
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>TASK_ID</th>
                                <th>TITLE</th>
                                <th>DESCRIPTION</th>
                                <th>DEADLINE</th>
                                <th>STATUS</th>
                                <th>USER_ID</th>
                                <th>Options</th>
                            </tr>
                        <c:forEach items="${requestScope.listTasksByProyect}" var="t">
                            <tr>
                                <td>${t.id}</td>
                                <td>${t.title}</td>
                                <td>${t.description}</td>
                                <td>${t.deadLine}</td>
                                <td>${t.status}</td>
                                <td>${t.userTask}</td>
                                <td>
                                    <form:form method="get" modelAttribute="usuario" action="http://localhost:90/TesisIII/pm/${login}/pmEditTask">
                                        <input type="hidden" name="taskProject" value="${t.taskProject}">
                                        <input type="hidden" name="taskId" value="${t.id}">
                                        <input type="hidden" name="login" value="${username}">
                                        <input type="submit" value="Edit"/>
                                    </form:form>
                                    <form:form method="get" action="http://localhost:90/TesisIII/pm/${login}/pmRemoveTask">
                                       <input type="hidden" name="idselected" value="${t.id}">
                                       <input type="hidden" name="login" value="${username}">
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

