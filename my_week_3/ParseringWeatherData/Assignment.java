
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Assignment {
    public CSVRecord getColdestOfTwo(CSVRecord coldestSoFar, CSVRecord currentRow){
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));        
        if( !(coldestSoFar == null && currentTemp == -9999) ){                
            if(coldestSoFar == null && currentTemp != -9999){
                coldestSoFar = currentRow;
            }    
            else{            
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp != -9999 && currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }    
            }             
        } 
        return  coldestSoFar;
    }    
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser){
            coldestSoFar = getColdestOfTwo(coldestSoFar, currentRow);
        }    
        return coldestSoFar;
    }    
    
    public void testcoldestHourInFile(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
    CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
    System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("TimeEST"));
    }  
    
    public String fileWithColdestTemperature(){
        File coldestFile = null;
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());           
            if(coldestSoFar == null){
                coldestSoFar = currentRow;
                coldestFile = f;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                    coldestFile = f;
                }    
            } 
        }
        return coldestFile.getAbsolutePath();      
    }    
    
    public void testfileWithColdestTemperature(){
        String filePath = fileWithColdestTemperature();                       
        System.out.println("Coldest day was in file " + filePath);        
        
        FileResource fr = new FileResource(filePath); 
        CSVParser parser =fr.getCSVParser(); 
        CSVRecord coldest = coldestHourInFile(parser);        
        System.out.println("coldest temperature on that day was " + coldest.get("TemperatureF"));
        
        System.out.println("All the temperatures on that day were ");
        
        parser =fr.getCSVParser(); 
        for(CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        
        for(CSVRecord currentRow : parser){
            String currentHumStr = currentRow.get("Humidity");  
            if( !(lowestSoFar == null && currentHumStr.equals("N/A")) ){
                if(lowestSoFar == null){
                    lowestSoFar = currentRow;
                }
                else{             
                    int lowestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
                    if(!currentHumStr.equals("N/A")){
                        int currentHum = Integer.parseInt(currentRow.get("Humidity"));
                        if(currentHum < lowestHum){
                            lowestSoFar = currentRow;
                        } 
                    }                                                                
                } 
            }   
        }       
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + csv.get("Humidity") +  " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()){
		FileResource fr = new FileResource(f);
		CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
		if (lowestSoFar == null){
		    lowestSoFar = currentRow;
		}
		else{
		    int currentHum = Integer.parseInt(currentRow.get("Humidity"));
		    int largestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
		    if(currentHum < largestHum){
			lowestSoFar = currentRow;
                    }
		}
	}
	return lowestSoFar;
    }
    
    public void testlowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
	System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
		           " at " + lowest.get("DateUTC"));
    } 
    
    public double averageTemperatureInFile(CSVParser parser){
        double ave = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            ave += Double.parseDouble(record.get("TemperatureF"));
            count ++;
        }
        return ave/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,  int value){
        double ave = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            if(Integer.parseInt(record.get("Humidity")) >= value){
                ave += Double.parseDouble(record.get("TemperatureF"));
                count ++;
            }    
        }
        if(count == 0){
            return -999.9;
        }    
        return ave/count;        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();  
        double ave = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(ave == -999.9){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average tempwith when high Humidity is " + ave);
        }   
    }    
}
