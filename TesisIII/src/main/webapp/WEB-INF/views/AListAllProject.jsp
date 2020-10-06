
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
            <div style ="width:200px; float: left; margin: 10px">
<!--                <input type ="button" value="List ALL User"
                onclick = "location.href = 'listAllUser'"
                class ="btn btn-primary" />
                <br/>
                <hr>-->
                <form method="post" action="http://localhost:90/TesisIII/admin/AListProjectCompleted">
                    <input type="submit" value="Projects Completed"/>
                </form>
                <br/>
                <form method="post" action="http://localhost:90/TesisIII/admin/AListProjectDeleted">
                    <input type="submit" value="Projects Deleted"/>
                </form>
                <br/>
                <form method="get" action="http://localhost:90/TesisIII/admin/AProject">
                    <input type="submit" value="Back"/>
                </form>
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

