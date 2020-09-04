
package Business;

import java.sql.*;
/** ******************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      Appointment.java
 ******************************************************************* */
public class Appointment {

    //props
    private String patId, dentId, procCode, apptDateTime;

    //constructors
    public Appointment() {
        this.apptDateTime = "";
        this.patId = "";
        this.dentId = "";
        this.procCode = "";
    }

    public Appointment(String apptDateTime, String patId, String dentId, String procCode) {
        this.apptDateTime = apptDateTime;
        this.patId = patId;
        this.dentId = dentId;
        this.procCode = procCode;
    }

    //setters
    public void setPatId(String patId) {
        this.patId = patId;
    }

    public void setDentId(String dentId) {
        this.dentId = dentId;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setApptDateTime(String apptDateTime) {
        this.apptDateTime = apptDateTime;
    }

    //getters
    public String getPatId() {
        return patId;
    }

    public String getDentId() {
        return dentId;
    }

    public String getProcCode() {
        return procCode;
    }

    public String getApptDateTime() {
        return apptDateTime;
    }

    //methods
    public void display() { //displays all info from class. good for testing
        System.out.println("apptDtTm " + getApptDateTime());
        System.out.println("PatId " + getPatId());
        System.out.println("DentId " + getDentId());
        System.out.println("procCode " + getProcCode());
    }

    public void selectDB(String id) {//pulls info from Database
        
        //can be used to pull info for Dentist or Patients
        String type = "dentId";

        if (id.substring(0, 1).equals("A")) {
            type = "patId";
        }

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select apptDateTime, patId, dentId, procCode from Appointments where " + type + "='" + id + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            setApptDateTime(rs.getString(1));
            setPatId(rs.getString(2));
            setDentId(rs.getString(3));
            setProcCode(rs.getString(4));

            con1.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }//end selectDB()

    public void insertDB(String apptDateTime, String patId, String dentId, String procCode) { //Used to insert new Appointments.

        this.apptDateTime = apptDateTime;
        this.patId = patId;
        this.dentId = dentId;
        this.procCode = procCode;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Insert into Appointments (apptDateTime, patId, dentID, procCode) values('" + getApptDateTime() + "',"
                    + "'" + getPatId() + "',"
                    + "'" + getDentId() + "',"
                    + "'" + getProcCode() + "');";
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
    }

    public void updateDB(String apptDateTime, String patId, String dentId, String procCode) { //Used to Update exsisting Appointments
        this.apptDateTime = apptDateTime;
        this.patId = patId;
        this.dentId = dentId;
        this.procCode = procCode;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Update Appointments SET apptDateTime='" + apptDateTime + "', patId='" + patId + "', dentId='" + dentId + "', procCode='" + procCode + "' WHERE patId='" + patId + "';";
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
    }

    public void deleteDB() { //Deletes Appointment

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Delete from Appointments where patId='" + getPatId() + "'";
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
        Appointment a1 = new Appointment();

        a1.selectDB("A923");
        a1.display();

    }//end main

}
