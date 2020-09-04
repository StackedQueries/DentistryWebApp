<%-- 
    Document   : ClientUpdate
    Created on : Jul 25, 2020, 12:48:18 PM
    Author     : Tyler Richards
--%>
<%@page import="Business.Patient"%>
<%@page import="Business.Dentist"%>

<%
    String id = (String) session.getAttribute("id");
    String type = id.substring(0, 1); // pull id from session
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="styles.css"> 
        <title>Client Update</title>
    </head>
    <body>
        <div id="content">
        <nav>
            <a href="WelcomeMenu.jsp">Go Back</a>
        </nav>
        <h1>Update/View your Information</h1>
        <form name="data" method="POST" action=ClientUpdateServlet>

            <%
                if (type.equals("A")) { //forms for patients
                    Patient pat = new Patient();
                    pat.selectDB(id);

            %>

            <label for="textfield">ID: </label>
            <input type="text" name="UserName" id="id" value='<%=pat.getId()%>' disabled><br>

            <label for="textfield">Password:</label>
            <input type="text" name="password" id="password" value='<%=pat.getPass()%>'><br>

            <label for="textfield">First Name:</label>
            <input type="text" name="firstName" id="firstName" value='<%=pat.getfName()%>'><br>

            <label for="textfield">Last Name:</label>
            <input type="text" name="lastName" id="lastName" value='<%=pat.getlName()%>'><br>

            <label for="textfield">Address:</label>
            <input type="text" name="address" id="address" value='<%=pat.getAddress()%>'><br>

            <label for="textfield">Email:</label>
            <input type="text" name="email" id="email" value='<%=pat.getEmail()%>'><br>

            <label for="textfield">Insurance Company:</label>
            <input type="text" name="insCo" id="insCo" value='<%=pat.getInsco()%>'><br>


            <input type="submit" name="submit" id="submit" value="Submit">
        </form>
        <%
        } else if (type.equals("D")) { //form for dentists
            Dentist dent = new Dentist();
            dent.selectDB(id);
        %>

        <label for="textfield">ID: </label>
        <input type="text" name="UserName" id="id" value='<%=dent.getId()%>' disabled><br>

        <label for="textfield">Password:</label>
        <input type="text" name="password" id="password" value='<%=dent.getPass()%>'><br>

        <label for="textfield">First Name:</label>
        <input type="text" name="firstName" id="firstName" value='<%=dent.getfName()%>'><br>

        <label for="textfield">Last Name:</label>
        <input type="text" name="lastName" id="lastName" value='<%=dent.getlName()%>'><br>

        <label for="textfield">Email:</label>
        <input type="text" name="email" id="email" value='<%=dent.getEmail()%>'><br>

        <label for="textfield">Office:</label>
        <input type="text" name="office" id="office" value='<%=dent.getOffice()%>'><br>


        <input type="submit" name="submit" id="submit" value="Submit">
    </form>
    <%}%>
        </div>
</body>
</html>
