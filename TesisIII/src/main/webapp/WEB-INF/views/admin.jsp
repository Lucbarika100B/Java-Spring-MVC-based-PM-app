
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <br/>
                <form method="get" action="http://localhost:90/TesisIII/admin/AProject">
                    <input type="submit" value="Projects"/>
                </form>   
                <br/>
                <br/>
                <form method="post" action="logout">
                    <button class="btn btn-block" type="submit">Logout</button>
                </form>
            </div>
            <div style ="width:1000px; float: left; height:100px;  margin: 5px">
                <p>Estas en la página  <b>ADMIN Page</b></p>
            </div>
        </div>
    </body>
</html>
