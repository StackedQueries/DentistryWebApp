
package Business;

import java.sql.*;
/********************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      Patient.java
 ********************************************************************/
public class Patient {

    private String id, fName, lName, pass,
            address, email, insco;

    public Patient() {
        this.id = "";
        this.fName = "";
        this.lName = "";
        this.pass = "";
        this.address = "";
        this.email = "";
        this.insco = "";

    }

    public Patient(String id, String fName, String lName, String pass, String address, String email, String insco) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.address = address;
        this.email = email;
        this.insco = insco;
    }

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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInsco(String insco) {
        this.insco = insco;
    }

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

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getInsco() {
        return insco;
    }

    public void display() { //displays all info from class. good for testing
        System.out.println("id " + getId());
        System.out.println("FirstName " + getfName());
        System.out.println("LastName " + getlName());
        System.out.println("Password " + getPass());
        System.out.println("Address " + getAddress());
        System.out.println("Email " + getEmail());
        System.out.println("Ins Co " + getInsco());

    }

    public void selectDB(String id) { // pulls info on the Patient
        this.id = id;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select patId, passwd, firstName, lastName, addr, email, insCo"
                    + " from Patients where patId='" + getId() + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            setId(rs.getString(1));
            setPass(rs.getString(2));
            setfName(rs.getString(3));
            setlName(rs.getString(4));
            setAddress(rs.getString(5));
            setEmail(rs.getString(6));
            setInsco(rs.getString(7));

            con1.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }//end selectDB()

    public void insertDB(String id,
            String pass, String fName, String lName, String address, String email, String insco) { //inserts new Patient

        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.address = address;
        this.email = email;
        this.insco = insco;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Insert into Patients (patId, passwd, firstName, lastName, addr, email, insCo"
                    + ")values('" + getId() + "',"
                    + "'" + getPass() + "',"
                    + "'" + getfName() + "',"
                    + "'" + getlName() + "',"
                    + "'" + getAddress() + "',"
                    + "'" + getEmail() + "',"
                    + "'" + getInsco() + "');";
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
            String pass, String fName, String lName, String address, String email, String insco) { //updates current Patient

        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.address = address;
        this.email = email;
        this.insco = insco;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Update Patients SET patId='"+id+"', passwd='"+pass+"', firstName='"+fName+"', lastName='"+lName+"', addr='"+address+"', email='"+email+"', insCo='"+insco+"' WHERE patId='"+id+"';";
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

    public void deleteDB() { //deletes Patient from Database

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Delete from Patients where patId='" + getId() + "'";
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
        Patient p1 = new Patient();

        //p1.selectDB("A900");
        p1.selectDB("A913");
        //p1.deleteDB();
        p1.updateDB("A900", "c1","23","s","test","yaboi@goomail.com","Caresource");
        p1.display();

    }//end main

}
