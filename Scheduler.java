/*****************************************
/
/    Scheduler.java
/    @author Christine Kim
/    ck2980
/
/*****************************************/

// This is your Scheduler class
// This class should store static methods

import java.util.*;
import java.io.*;

public class Scheduler{
    
    private static final int SLOT_NUM = 3; 
    
    // this static method should take in a file name and return an ArrayList of Talk objects
    // notice that it might throw an IOException
    public static ArrayList<Talk> makeTalks(String fileName)throws 
                                                        IOException, FileNotFoundException
                                                            
    {
        // Construct ArrayList of talk shows
        ArrayList<Talk> talkList = new ArrayList<Talk>();
        File talkFile = new File(fileName);
        Scanner in;
        
        try {
            in = new Scanner(talkFile);
        }
        catch (FileNotFoundException e) {
            System.out.println("The file you entered does not exist."
                                  + " Please enter a valid file.");
            throw new IOException();
        }
        
        if (!in.hasNext()){
            System.out.println("The file you entered is empty.");
            throw new IOException();
        }
        
        while (in.hasNextLine()){
            //each line is a new slot
            String line = in.nextLine();
            
            //ArrayList<String> of name, start, and end times
            Scanner lineScanner = new Scanner(line);
            ArrayList<String> slot = new ArrayList<String>();
            while (lineScanner.hasNext()){
                slot.add(lineScanner.next());
            }
            
            if (slot.size() != SLOT_NUM){
                System.out.println("Each slot should have" +
                                   " only three parameters.");
                throw new IOException();
            }
            
            //Add each slot into 
            Talk next = new Talk(slot);
            talkList.add(next);            
        }
        
        Collections.sort(talkList);
        return talkList;
    }
    
    // this static method should take in an ArrayList of Talk objects and return a maximum 
    // size subset of those talks that may be scheduled together in a single room.
    public static ArrayList<Talk> scheduleTalks(ArrayList<Talk> talks) 
    {
        // your code here
        // create official schedule ArrayList
        ArrayList<Talk> schedule = new ArrayList<Talk>();
        ArrayList<Talk> temp = new ArrayList<Talk>();
        for(int i=0; i<talks.size(); i++){
            temp.add(talks.get(i));
        }
        //talk that ends first is first on schedule
        schedule.add(temp.get(0));
        temp.remove(0);
        
        boolean compatibleLeft = true;
        int index =0;
        
        //This adds the talk that ends the earliest, which is the
        //first talk on the compatible list. It then removes the talk
        //from the temp list, and the loop calls an updated compatible
        //list in respect to the updated temp list.
        while (compatibleLeft){
            ArrayList<Talk> compatibleList = 
                makeCompatible(temp, schedule.get(index));
            if (compatibleList.size() > 0){
                schedule.add(compatibleList.get(0));
                temp.remove(compatibleList.get(0));
                index++;
            }
            else{
                compatibleLeft = false;
            }
            
        }
        return schedule;
    }
   
    //This static method checks if the talks are compatible and if it
    //is, then it adds to the compatible list
    public static ArrayList<Talk> makeCompatible(ArrayList<Talk> temp,
                                                Talk compareEnd){
        ArrayList<Talk> compatibleList = new ArrayList<Talk>();
        int index = 0;
        for(int i=0; i<temp.size(); i++){
            if (temp.get(i).isCompatible(compareEnd)){
                compatibleList.add(temp.get(i));
            }
        }
        Collections.sort(compatibleList);
        return compatibleList;
    } 
}