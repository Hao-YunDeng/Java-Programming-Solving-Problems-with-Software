
/**
 * Write a description of MiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class MiniProject {
        public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0; 
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames ++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames ++;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        
        System.out.println("total names = " + totalNames);
        System.out.println("total girl names = " + totalGirlNames);
        System.out.println("total boy names = " + totalBoyNames);       
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                rank ++;
            }            

            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                return rank;
            }
        }
        return -1;
    }
    
    public void testGetRank(){
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println(getRank(year, name, gender));
        
        //year = 2012;
        //name = "Mason";
        //gender = "F";
        //System.out.println(getRank(year, name, gender));
    }
    
    public String getName(int year, int rank, String gender){
        int count = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        
        if(rank == 0){
            return "NO NAME";
        }
        
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                count ++;
            }            

            if(rank == count ){
                return record.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println(getName(year, rank, gender));
        
        //year = 2012;
        // rank = 0;
        //gender = "M";
        //System.out.println(getName(year, rank, gender));
    }  
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if he/she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender){
        int year = -1;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            if(rank == 0){
                rank = getRank(currentYear, name, gender);
                year = currentYear;
                if(rank == -1){
                    rank = 0;
                    year = -1;
                }   
            }
            else{
                if(getRank(currentYear, name, gender) < rank && getRank(currentYear, name, gender) != -1){
                    rank = getRank(currentYear, name, gender);
                    year = currentYear;
                }
            }            
        }
        return year;
    }
    
    public void testYearOfHighestRank(){
        String name = "Mich";
        String gender = "M";
        //String gender = "F";
        System.out.println(yearOfHighestRank(name, gender));
    }
    
    public double getAverageRank(String name, String gender){
        int count = 0;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            if(getRank(currentYear, name, gender) != -1){
                rank += getRank(currentYear, name, gender);
                count ++;
            }

        }
        if(count == 0){
            return -1;
        }   
        return (double)rank/count;        
    }
    
    public void testGetAverageRank(){
        //String name = "Mason";
        String name = "Robert";
        String gender = "M";
        //String gender = "F";
        System.out.println(getAverageRank(name, gender));        
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int totalBirths = 0;
        int currentRank =0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){ 
            if(record.get(1).equals(gender)){
                currentRank ++;
                if(currentRank < rank){
                    totalBirths += Integer.parseInt(record.get(2));
                }
                else{
                    break;
                }
            }
                       
        } 
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        //String name = "Mason";
        String name = "Drew";
        //String gender = "M";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(1990, name, gender));          
    }
}
