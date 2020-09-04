
package Business;

import java.sql.*;
/** ******************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      Procedure.java
 ******************************************************************* */
public class Procedure {

    //props
    private String procCode, procName, procDesc, cost;

    //constructors
    public Procedure() {
        this.procCode = "";
        this.procName = "";
        this.procDesc = "";
        this.cost = "";
    }
    
    public Procedure(String procCode, String procName, String procDesc, String cost) {
        this.procCode = procCode;
        this.procName = procName;
        this.procDesc = procDesc;
        this.cost = cost;
    }
    
    //setters
    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    //getters
    public String getProcCode() {
        return procCode;
    }

    public String getProcName() {
        return procName;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public String getCost() {
        return cost;
    }

    public void display() { //displays all info from class. good for testing
        System.out.println("code " + getProcCode());
        System.out.println("Name " + getProcName());
        System.out.println("Desc " + getProcDesc());
        System.out.println("Cost " + getCost());

    }


    public void selectDB(String procCode) {//pull info from Database
        this.procCode = procCode;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select procCode, procName, procDesc, cost from Procedures where procCode='" + getProcCode() + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            setProcCode(rs.getString(1));
            setProcName(rs.getString(2));
            setProcDesc(rs.getString(3));
            setCost(rs.getString(4));

            con1.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }//end selectDB()

    public void insertDB(String procCode,
            String procName, String procDesc, String cost) {//inserts new procedure

        this.procCode = procCode;
        this.procName = procName;
        this.procDesc = procDesc;
        this.cost = cost;

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Insert into Procedures (procCode, procName, procDesc, cost) values('" + getProcCode() + "',"
                    + "'" + getProcName() + "',"
                    + "'" + getProcDesc() + "',"
                    + "'" + getCost() + "');";
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

    public void deleteDB() {//deletes procedure

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Delete from Procedures where procCode='" + getProcCode() + "'";
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
        Procedure p1 = new Procedure();

        p1.selectDB("P114");
        p1.display();

    }
}
