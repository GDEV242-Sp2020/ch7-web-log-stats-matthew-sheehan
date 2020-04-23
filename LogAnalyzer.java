/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author: Marcelle Tamegnon
 * @authorOriginal: David J. Barnes and Michael Kölling.
 * @version   2020 04.23
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    private int[] yearCounts;
    
    private LogfileReader reader;
    
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    {
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[12];
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
        dayCounts = new int[31];
        monthCounts = new int[12];
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
       int counts = 0;
        
        for(int num_hours : hourCounts) {

            if(num_hours  > 0)
            counts = counts + num_hours; 
            
        }
        return counts;
    }
    
    /**
     * Excercise 7.15 add a method busiestHour that returns the busiest hour
     * @return int busiest hour  
     *
     *7.17) the busiestHour returns earliest of the busiestHours if multiple.
     */
    public int busiestHour()
    {
        int busiestHour = -1;
        int data =0;
        int i;
        analyzeHourlyData();
       for(i = 0; i < hourCounts.length; i++) {
           if(hourCounts[i] > data){
            data = hourCounts[i];
            busiestHour = i;
        }
        }
        System.out.println("The busiest is " +busiestHour 
                                + "at that time there are" +data);
        return busiestHour;
    }
    
    /**
     * Excercise 7.18 add method busiestTwoHours returns the busiest 2 hours
     * 
     *prints the busiest two hours
     */
    public void busiestTwoHours()
    {
        int busiest_day = -1;
        int lastHour = -1;
        int counts = 0;
        int data  =0;
        analyzeHourlyData();
         for(int i = 0; i < hourCounts.length; i++) {
            counts = hourCounts[i]+lastHour;
             if(counts > data){
                
            data =counts;
            busiest_day = i;
        }
        lastHour = hourCounts[i];
      }
        System.out.println("The busiest times are " +(busiest_day - 1) + " and " 
                            + busiest_day +", with " +data);

    }
    
    /**
     * Excercise 7.16 add a method quietestHour that returns the quietest hour
     * @return int quietest hour
     */
    public int quietestHour()
    {
        int quietestHour = -1; 
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
                                +", with " +compareHour);
  
        return quietestHour;
        
    }
    
    /**
     * Excercisse 7.19 add further methods that compare days month and year data.
     */
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    } 
    
    /**
     * Add a method to compare busiest Day
     *
     *@returns int busiestDay.
     */
    public int busiestDay()
    {
        int busiestDay = -1;
        int data = 0;
        int i;
        analyzeDailyData();
       for(i = 0; i < dayCounts.length; i++) {

            if(dayCounts[i] > data){
            data = dayCounts[i];
            busiestDay = i;
        }
        }
        System.out.println("The busiest day is " +busiestDay 
                                +", with " +data);
        return busiestDay;
    }
        
        
    /**
     * Analyze the Monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    } 
    
    /**
     *A method to compare busiest Month
     *
     *@returns int busiestMonth.
     */
    public int busiestMonth()
    {
        int busiestMonth = -1;
        int data =0;
        analyzeMonthlyData();
       for(int i = 0; i < monthCounts.length; i++) {

            if(monthCounts[i]>data){
            data = monthCounts[i];
            busiestMonth = i;
        }
        }
        System.out.println("The busiest day is " +busiestMonth
                                +", with " +data);
        return busiestMonth;
    }
}
