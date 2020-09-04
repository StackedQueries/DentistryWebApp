
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/** ******************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      getInfo.java
 ******************************************************************* */
public class getInfo {// used to pull various information for menus.
    
    //props
    private int count = 0;

    //getters
    public int getCount() {
        return count;
    }
    
    public static String[] getDentists() { //used to get all dentist out of Database

        String[] dentists = new String[4];
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select id from Dentists";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                dentists[count] = (rs.getString(1));
                count++;
            }

            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return dentists; // returns array of dentist
    }

    public String[][] getAppointments() { // used to get all Appointments from Database

        String[][] Appointments = new String[30][4];
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select apptDateTime, patId, dentId, procCode from Appointments ORDER BY dentId";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            count = 0;
            while (rs.next()) {
                Appointments[count][0] = (rs.getString(1));
                Appointments[count][1] = (rs.getString(2));
                Appointments[count][2] = (rs.getString(3));
                Appointments[count][3] = (rs.getString(4));
                count++;
            }
            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return Appointments; // returns array of appointments
    }

    public getInfo() {
    }

    public static String[] getProcedures() { //Used to get all procedures

        String[] procCodes = new String[8];
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"
                    + "DATABASE LOCATION HERE");

            Statement stmt = con1.createStatement();
            String sql = "Select procCode from Procedures";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                procCodes[count] = (rs.getString(1));
                count++;
            }

            con1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return procCodes; //returns array of procedure codes
    }

    public static void main(String[] args) {

    }
}
