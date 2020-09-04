<%-- 
    Document   : WelcomeMenu
    Created on : Jul 21, 2020, 9:28:01 PM
    Author     : Tyler Richards
--%>

<%
    String id = (String) session.getAttribute("id"); //pulls id from session
    String type = id.substring(0, 1);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"> 
        <title>Welcome</title>
    </head>
    <body>
        <div id="content">
            <nav>
                <a href="ClientUpdate.jsp">Update Your Information</a>
                <a href="AppointmentView.jsp">View Appointment</a>
                <%
                    if (type.equals("A")) { //allows to make appointment if Patient

                %>
                <a href="MakeAppointment.jsp">Make an Appointment</a>
                <%                                }
                %>
            </nav>
            <h1>Welcome to X's Dentistry</h1>
            <h2>Choose an Action from the NavBar</h2>
        </div>
    </body>
</html>
