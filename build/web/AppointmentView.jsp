<%-- 
    Document   : AppointmentView
    Created on : Jul 21, 2020, 9:28:16 PM
    Author     : Tyler Richards
--%>
<%@page import="Business.Dentist"%>
<%@page import="Business.Appointment"%>

<%@page import="Business.Patient"%>
<%@page import="Business.Procedure"%>


<%
    String id = (String) session.getAttribute("id"); //pull user id
    String type = id.substring(0, 1);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"> 
        <title>Appointment View</title>
    </head>
    <body>
        <div id="content">

        <h1>Appointments</h1>

        <nav>
            <a href="WelcomeMenu.jsp">Go Back</a>
        </nav>
        <table>
            <%
                Appointment[] apps = new Appointment[10]; //lists all Appointments scheduled
                int count = 0;
                if (type.equals("A")) {
                    Appointment a = new Appointment();
                    a.selectDB(id);
                    if (a.getApptDateTime().isEmpty() != true) {

                        apps[0] = a;
                        count++;
                    }

                }
                if (type.equals("D")) {
                    Dentist dent = new Dentist();
                    dent.selectDB(id);
                    apps = dent.aList.appArr;
                    count = dent.aList.count;
                }

                if (count > 0) {
            %>
            <tr>
                <th>Appointment Date & Time</th>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Procedure</th>
                <th>Desc</th>
                <th>Cost</th>
            </tr>
            <%for (int i = 0; i < count; i++) { // lists info for appointments

                    Patient pat = new Patient();
                    Dentist dent = new Dentist();
                    Procedure proc = new Procedure();

                    pat.selectDB(apps[i].getPatId());
                    dent.selectDB(apps[i].getDentId());
                    proc.selectDB(apps[i].getProcCode());

            %>
            <tr>
                <td><%=apps[i].getApptDateTime()%></td>
                <td><%=pat.getfName()%> <%=pat.getlName()%></td>
                <td><%=dent.getfName()%> <%=dent.getlName()%></td>
                <td><%=proc.getProcName()%></td>
                <td><%=proc.getProcDesc()%></td>
                <td><%=proc.getCost()%></td>
            </tr>
            <%}
            } else {%>
            <h1>You have no Appointments.</h1>
            <% }
            %>

        </table>
        <%
            if (type.equals("A")) { //if user is patient, allow to change appointment.

        %>

        <Button onclick="window.location.href = 'MakeAppointment.jsp';">Change Appointment</Button>
            <%    }
            %>
        </div>
    </body>
</html>
