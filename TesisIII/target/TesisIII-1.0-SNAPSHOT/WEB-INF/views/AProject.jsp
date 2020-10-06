
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
<!--                <input type ="button" value="List ALL User"
                onclick = "location.href = 'listAllUser'"
                class ="btn btn-primary" />
                <br/>
                <hr>-->
                <form method="post" action="http://localhost:90/TesisIII/admin/AListAllProject">
                    <input type="submit" value="List of Projects"/>
                </form>
                <br/>
                <form method="get" action="http://localhost:90/TesisIII/admin">
                    <input type="submit" value="Back"/>
                </form>
            </div>
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:500px; float: left">
                    <h3>Add New Project</h3>
                    <hr>
                    <form:form method="POST"  modelAttribute="p">
                        <form:hidden path ="pooPId" value= "0"/>
                        <label>Project Name :</label><form:input path="name"/>
                        <span style ="color:red"><form:errors path="name"/></span>
                        <br>
                        <label>Project Description :</label><form:input path="description"/>
                        <span style ="color:red"><form:errors path="description"/></span>
                        <br>
                        <label>Project Start Date (mm/dd/yy) :</label><form:input path="startDate"/>
                        <span style ="color:red"><form:errors path="startDate"/></span>
                        <br>
                        <label>Project End Date (mm/dd/yy) :</label><form:input path="endDate"/>
                        <span style ="color:red"><form:errors path="endDate"/></span>
                        <br>
                        <label for="managerId">Project Manager :</label>
                        <form:select path ="manager">
                            <form:option value="0">Select</form:option>
                            <form:options items="${managerList}" itemValue="id" itemLabel="firstName"/>
                        </form:select>
                        <br>
                        <br>
<!--                        <label for="poolP">Project Pool  :</label>
                        <form:select path ="pooPId">
                            <form:option value="0">Select</form:option>
                            <form:options items="${listPool}" itemValue="id" itemLabel="id"/>
                        </form:select>
                       <br>-->
                        <input type="submit" value="Add Project">
                    </form:form>
                </div>
                <div style ="width:1000px; float: left">
                    <h3>Projects List</h3>
                    <hr>
                        <table style = "margin-left:auto; margin-right:auto; alignment-adjust:central;">
                            <tr>
                                <th>PROJECT_ID</th>
                                <th>MANAGER_ID</th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>START_DATE</th>
                                <th>END_DATE</th>
                                <th>PROJECT_STATUS</th>
                                <th>Option</th>
                            </tr>
                        <c:forEach items="${requestScope.listAllProject}" var="p">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.manager}</td>
                                <td>${p.name}</td>
                                <td>${p.description}</td>
                                <td>${p.startDate}</td>
                                <td>${p.endDate}</td>
                                <td>${p.pStatus}</td>
                                <td>
                                    <form:form method="get" modelAttribute="usuario" action="http://localhost:90/TesisIII/admin/editProject">
                                        <input type="hidden" name="idselected" value="${p.id}">
                                        <input type="submit" value="Edit"/>
                                    </form:form>
                                    <form:form method="get" action="http://localhost:90/TesisIII/admin/removeProject">
                                       <input type="hidden" name="idselected" value="${p.id}">
                                       <input type="hidden" name="poolID" value="${p.pooPId}">
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

