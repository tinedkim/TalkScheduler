/*****************************************
/
/    Talk.java
/    @author Christine Kim
/    ck2980
/
/*****************************************/

import java.util.ArrayList;
import java.io.IOException;

public class Talk implements Comparable<Talk> {
    private String guestName;
    private int startTime;
    private int endTime;
    
    public Talk(ArrayList<String> slot) throws IOException {
        guestName = slot.get(0);
        
        //start time and end time should be integers
        try{
            startTime = Integer.parseInt(slot.get(1).trim());
        }
        catch (NumberFormatException e) {
            System.out.println("Start time is not a valid integer."
                               + " Please enter a valid integer.");
            throw new IOException();
        }
        try{
            endTime = Integer.parseInt(slot.get(2).trim());
        }
        catch (NumberFormatException e) {
            System.out.println("End time is not a valid integer."
                               + " Please enter a valid integer.");
            throw new IOException();
        }
        
        //start time should be earlier than end time
        if (startTime >= endTime){
            System.out.println("Start time should be earlier and " +
                                "not at the same time as end time.");
            throw new IOException();
        }
        
        //start and end time should be integers between 0000 and 2400
        if (startTime < 0 || startTime > 2400 || endTime < 0 || endTime >2400){
            System.out.println("Start time and end time should be" +
                                  " integers between 0000 and 2400.");
            throw new IOException();
        }
        
    }
    
    //This method returns a boolean if the endtime of one talk is earlier
    //than the other talk. This determines if two talks are compatible.
    public boolean isCompatible(Talk other){
        if (this.startTime >= other.endTime){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int compareTo(Talk other){
        if (this.endTime < other.endTime){
            return -1;
        }
        else if (this.endTime > other.endTime){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public String toString(){
        String slotString = "";
        slotString += String.format("%-15s", guestName);
        slotString += String.format("%10d", startTime);
        slotString += String.format("%10d", endTime);
        return slotString;
    }
    
    public int getStart(){
        return startTime;
    }
    
    public int getEnd(){
        return endTime;
    }
}