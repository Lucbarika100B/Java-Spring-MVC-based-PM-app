
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
        <h1>BARIKA's PROJECT MANAGER Page</h1>
        <div>
            <div style ="width:100px; float: left; margin: 10px">
                <form method="get" action="http://localhost:90/TesisIII/pm/${login}">            
                    <button class="btn btn-block" type="submit">back</button>
                </form>
            </div>
            <div style ="width:1100px; float: left; height:100px;  margin: 5px">
                <div style ="width:300px; float: left">
                    <h3>Edit project</h3>
                    <hr>      
                       <form:form method="POST"  modelAttribute="p">
                           <form:hidden path ="id" value= "${p.getId()}"/>
                           <form:hidden path ="pooPId" value= "${p.getPooPId()}"/> 
                        <label>Project Name : ${p.getName()}</label>
                        <form:hidden path ="Name" value= "${p.getName()}"/>
                        <br>
                        <label>Project Description :</label><form:input path="description" value="${p.getDescription()}"/>
                        <span style ="color:red"><form:errors path="description"/></span>
                        <br>
                        <label>Project Start Date (mm/dd/yy) :</label><form:input path="startDate" value="${p.getStartDate()}"/>
                        <span style ="color:red"><form:errors path="startDate"/></span>
                        <br>
                        <label>Project End Date (mm/dd/yy) :</label><form:input path="endDate" value="${p.getEndDate()}"/>
                        <span style ="color:red"><form:errors path="endDate"/></span>
                        <br>
                        <label for="managerId">Project Manager : ${p.getManager()}</label>
                        <form:hidden path ="manager" value= "${p.getManager()}"/>
                        <br>
                        <label for="pStatus">Project Status  :</label>
                        <form:select path ="pStatus" value="${p.getpStatus()}">
                            <form:option value="0">Select</form:option>
                            <form:option value="IN PROGRESS">IN PROGRESS</form:option>
                            <form:option value="COMPLETED">COMPLETED</form:option>
                        </form:select>
                       <br>
                       <br>
                        <input type="submit" value="Update Project">
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>

