READ ME

Christine Kim (ck2980)

                         SCHEDULE TEST
PROGRAM DESCRIPTION:
Imagine you are a conference organizer and you are tasked with scheduling the largest possible subset from a set of talks all in the same room. Each talk in the set has a given start time and end time. These times cannot change. No talks that have times that overlap can be scheduled in the same room. For the sake of this assignment assume that one talk can begin instantly upon completion of the previous talk. Consider the example below with three talks in the set:

Talks:

1. Fred Flinstone 9:00AM-11AM
2. Barney Rubble 10:30AM-4PM
3. Bam Bam Rubble 1PM-1:15PM

The optimal schedule (the one that schedules the most talks) in this example would be to schedule Fred’s talk and Bam Bam’s talk. Note: Optimal does NOT mean the room is used for the longest time, it means the greatest possible number of talks are scheduled.
                         
                         
INSTRUCTIONS

    When the user calls this program, they are also expected to provide
    a file that must follow the following rules:
    - a file that can be located/found
    - cannot be empty
    - three parameters per line: a guest name, start time, and end time
    - start time must be later than end time
    - start time and end time must be integers between 0000 and 2400
    If the file does not adhere to those rules, then the program will
    terminate gracefully and tell the user which of those violations must
    be fixed in order for them to use it properly. Only then, it will
    produce a list of talks that will maximize the number of talks in one
    schedule. 

DESIGN
    
    In my Scheduler class, I constructed an ArrayList of Talks where each
    line constituted a "slot" which must have three parameters or else the
    program will throw an IO Exception. Additionally, I included cases
    where the file could not be found, which would throw an FileNotFound
    Exception and an empty file which would throw another IO Exception.
    Each slot is an array of three elements of Strings which the Talk class
    will examine the contents.
    
    In the Talk class, the first parameter is the name of the guest, the
    second and third are string-converted integers that are the start time
    and end time, respectively. If the second and third parameters are not
    numbers, they cannot be parsed into integers which would throw an IO
    Exception. Additionally, if they don't fall within the bounds 0 and 2400
    or if the start time is greater than or equal to the end time, it would
    also throw an IO Exception. I created a toString method to present the
    schedule in an organized format. I also made a compareTo method that
    implements a sorting algorithm based on the end times of the talks. I
    also put an isCompatible method that returns a boolean whether or not
    two talks overlap.
    
    Back in the Scheduler class, the scheduleTalks method returns a list
    of talks. Taking the ArrayList extracted from the file, the method first
    takes the talk that ends earliest and makes it the first element of the
    schedule ArrayList. It then creates a copy of the talk list, temp, which
    makes it easier to manipulate the elements and copy over talks. Then in
    a while loop, a separate method, makeCompatible, compares talks based on
    their compatibility. In the while loop, it will take the first element
    that ends earliest and is compatible so that there are no overlaps. It
    copies it onto the official schedule and then removes that first element 
    from the compatible ArrayList and repeats until the compatible ArrayList
    is empty. The product of the scheduleTalks method should be a schedule
    ArrayList that has the optimal number of talks in a day.
    
    
