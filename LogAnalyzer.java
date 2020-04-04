/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author: Matthew Sheehan
 * @authorOriginal: David J. Barnes and Michael Kölling.
 * @version   2020 04.02
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    
    /*
    *7.2
    private Person[] people;
    *7.3
    private boolean[] vacant;
    */
   
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    {
        hourCounts = new int[24];
        // Create the reader with specific File Name to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Create an object to analyze hourly web accesses from specified file.
     * Excercise 7.12 Create a constructor that can take the name of the log file to be analyzed.
     * have this constructor pass the file name to the constructor of the LogFile Reader class. 
     * Use the LogFileCreator class to create your own file of random log entries, and analyze the date.
     */
    public LogAnalyzer(String logFileName)
    {
        hourCounts = new int[24];
        // Create the reader with specific File Name to obtain the data.
        reader = new LogfileReader(logFileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        analyzeHourlyData();
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file
     * Excercise 7.13 -7.14 Copmlete the numberOfAccesses method to count the total
     * number of accesses recorded in the log file. Complete it by using a for
     * loop to iterate over hourCounts
     * @return int number total log entries
     */
    public int numberOfAccessess()
    {
       int total = 0;
        
       for(int value : hourCounts) {

            if(value > 0)
            total+= value; //add total of entries counted
            //entries = hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Excercise 7.15 add a method busiestHour that returns the busiest hour
     * @return int busiest hour  
     *
     *7.17) the busiestHour returns earliest of the busiestHours if multiple.
     */
    public int busiestHour()
    {
        analyzeHourlyData();
        
        int busiestHour = -1;
        int entries =0;
        
       for(int i = 0; i < hourCounts.length; i++) {

            if(hourCounts[i]>entries){
            entries = hourCounts[i];
            busiestHour = i;
        }
        }
        System.out.println("The busiest time is during hour " +busiestHour 
                                +", with " +entries+ " entries.");
        return busiestHour;
    }
    
    /**
     * Excercise 7.18 add method busiestTwoHours returns the busiest 2 hours
     * @return int busiest 2 hour  chunk
     *
     */
    public void busiestTwoHours()
    {
        analyzeHourlyData();
        
        int busiest1st = -1;
        int lastHour = -1;
        int total =0;
        int entries =0;
        
       for(int i = 0; i < hourCounts.length; i++) {
           total = hourCounts[i]+lastHour;
            if(total>entries){
                
            entries =total;
            busiest1st = i;
        }
        lastHour = hourCounts[i];
        }
        System.out.println("The busiest time during hours " +(busiest1st - 1) + " and " 
                            + busiest1st +", with " +entries+ " entries.");

    }
    
    /**
     * Excercise 7.16 add a method quietestHour that returns the quietest hour
     * @return int quietest hour
     */
    public int quietestHour()
    {
        int quietestHour = -1; // give it a maximum to be reduced to
        int compareHour = hourCounts[busiestHour()];
        analyzeHourlyData();
        for(int i = 0; i < hourCounts.length; i++) {

           if(hourCounts[i]<compareHour){
               compareHour=hourCounts[i];
               quietestHour=hourCounts[i];
               quietestHour = i;
            }      
        }
           System.out.println("The quietest time is during hour " +quietestHour 
                                +", with " +compareHour+ " entries.");
  
        return quietestHour;
        
    }
}
