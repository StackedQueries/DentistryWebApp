<%-- 
    Document   : MakeAppointment
    Created on : Jul 21, 2020, 9:28:35 PM
    Author     : Tyler Richards
--%>
<%@page import="Business.getInfo"%>
<%@page import="Business.Dentist"%>
<%@page import="Business.Appointment"%>

<%@page import="Business.Patient"%>
<%@page import="Business.Procedure"%>
<%@page import="Business.getInfo.*"%>


<%

    String apt = "May 1, 2018, 9am";
    String id = (String) session.getAttribute("id"); //pulls id

    Patient pat = new Patient();
    pat.selectDB(id);

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="styles.css"> 
        <title>Make An Appointment</title>
    </head>
    <body>
        <div id="content">
        <nav>
            <a href="WelcomeMenu.jsp">Go Back</a>
        </nav>
        <h1>Make an Appointment</h1>
        <table>
            <tr>
                <th>Filled Time Slots</th>
                <th>Dentists</th>

            </tr>
            <%                //fills appointment list
                getInfo get = new getInfo();
                String[][] apps = get.getAppointments();
                int count = get.getCount();
                String currApp="";
                for (int i = 0; i < count; i++) {

                    Dentist dent = new Dentist();
                    dent.selectDB(apps[i][2]);
                    if (apps[i][1].equals(id)){
                        currApp ="Your Current Appointment";
                    }

            %>
            <tr>
                <td><%=apps[i][0]%></td>
                <td><%=dent.getfName() + " " + dent.getlName()%></td>
                
                <td><%=currApp%></td>
            </tr>
            <%currApp="";}%>
        </table>


        <form name="data" method="POST" action=AppointmentUpdateServlet>
            <label for="textfield">Appointment Date and Time: </label>
            <input type="text" name="apptDateTime" id="apptDateTime" value='<%=apt%>'><br>
            <label for="textfield">Patient: </label>
            <input type="text" name="Patient" id="Patient" value='<%=pat.getfName()%> <%=pat.getlName()%>' disabled><br>
            <label for="dentist">Dentist: </label>
            <select name="dentist" id="dentist" value=" ">
                <%
                    //fills dentist list

                    for (String dentcode : getInfo.getDentists()) {
                        Dentist dent = new Dentist();
                        dent.selectDB(dentcode);
                        String dentNameOptions = dent.getfName() + " " + dent.getlName();
                %>
                <option value="<%=dentcode%>"><%=dentNameOptions%></option>
                <%}%>
            </select><br>
            <label for="procedure">Procedure: </label>
            <select name="procedure" id="procedure" value=" ">
                <%
                    //fills procedure list

                    for (String proccode : getInfo.getProcedures()) {
                        Procedure p = new Procedure();
                        p.selectDB(proccode);
                        String procNameOption = p.getProcName();
                %>
                <option value="<%=proccode%>"><%=procNameOption%></option>
                <%}%>   
            </select><br>
            <input type="submit" name="submit" id="submit" value="Submit">
        </form>
        </div>
    </body>
</html>
