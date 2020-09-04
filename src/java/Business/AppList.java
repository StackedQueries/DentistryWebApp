/** ******************************************************************
 *	Java III - Final Project
 * 	Tyler Richards - Summer 2020
 *      AppList.java
 ******************************************************************* */
package Business;

public class AppList {

    //props
    public int count;
    public Appointment appArr[] = new Appointment[10];

    //methods
    public void addAppointment(Appointment a1) {

        appArr[count] = a1;
        count++;

    } //end addAppointment

    public void displayList() {

        for (int x = 0; x < count; x++) {
            appArr[x].display();
        } //end for

    }//end displayList()
}
