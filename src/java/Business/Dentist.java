
package Business;

import java.sql.*;
/** ******************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      Dentist.java
 ******************************************************************* */
public class Dentist {

    //props
    private String id, fName, lName, pass, office, email;
    public AppList aList = new AppList();

    ;

    //constructors
    public Dentist() {
        this.id = "";
        this.fName = "";
        this.lName = "";
        this.pass = "";
        this.office = "";
        this.email = "";
    }

    public Dentist(String id, String fName, String lName, String pass, String office, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.office = office;
        this.email = email;
    }

    //getters
    public String getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPass() {
        return pass;
    }

    public String getOffice() {
        return office;
    }

    public String getEmail() {
        return email;
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void display() { //displays all info from class. good for testing
        System.out.println("id " + getId());
        System.out.println("FirstName " + getfName());
        System.out.println("LastName " + getlName());
        System.out.println("Password " + getPass());
        System.out.println("Office " + getOffice());
        System.out.println("Email " + getEmail());
        for (Appointment a : aList.appArr) {
            if (a != null) {
                System.out.println("Appointment on: " + a.getApptDateTime());
            }
        }

    }

    public void selectDB(String id) { //pulls info from Database
        this.id = id;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select id, passwd, firstName, lastName, email, office from Dentists where id='" + getId() + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            setId(rs.getString(1));
            setPass(rs.getString(2));
            setfName(rs.getString(3));
            setlName(rs.getString(4));
            setEmail(rs.getString(5));
            setOffice(rs.getString(6));

            con1.close();

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con2 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt2 = con2.createStatement();
            sql = "Select apptDateTime, patId, dentId, procCode from Appointments where dentId='" + getId() + "'";
            System.out.println(sql);
            ResultSet rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                Appointment a1 = new Appointment(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4));
                aList.addAppointment(a1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }//end selectDB()

    public void insertDB(String id,
            String pass, String fName, String lName, String email, String office) { //inserts new Dentist into DB

        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.office = office;
        this.email = email;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Insert into Dentists (id, passwd, firstName, lastName, email, office) values('" + getId() + "',"
                    + "'" + getPass() + "',"
                    + "'" + getfName() + "',"
                    + "'" + getlName() + "',"
                    + "'" + getEmail() + "',"
                    + "'" + getOffice() + "');";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1 == 1) {
                System.out.println("INSERT Successful!!!");
            } else {
                System.out.println("INSERT FAILED***********");
            }
            con1.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//end insertDB

    public void updateDB(String id,
            String pass, String fName, String lName, String email, String office) { //updates exsisting Dentist Record

        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.office = office;
        this.email = email;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Update Dentists SET id='" + id + "', passwd='" + pass + "', firstName='" + fName + "', lastName='" + lName + "', email='" + email + "', office='" + office + "' WHERE id='" + id + "';";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1 == 1) {
                System.out.println("Update Successful!!!");
            } else {
                System.out.println("Update FAILED***********");
            }
            con1.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//end updateDB

    public void deleteDB() { //deletes Dentist

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Delete from Dentists where id='" + getId() + "'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n == 1) {
                System.out.println("DELETE Successful!!!");
            } else {
                System.out.println("DELETE FAILED***********");
            }
            con1.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }

    }//end deleteDB()

    public static void main(String args[]) {
        Dentist d1 = new Dentist();

        d1.selectDB("D22");
        d1.display();

    }//end main

}
