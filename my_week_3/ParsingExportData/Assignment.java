
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Assignment {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Germany"));
        //System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //listExportersTwoProducts(parser, "fish", "nuts");
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        //System.out.println(numberOfExporters(parser, "gold"));
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }    
    
    public String countryInfo(CSVParser parser, String country){
        String info = "NOT FOUND";
        for(CSVRecord record : parser){
            String countryItem = record.get("Country");
            //if(countryItem == country){        //this one returns not found
            //if(countryItem.contains(country)){ //this one works 
            if(countryItem.equalsIgnoreCase(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                info = country + ": " + exports + ": " + value;
                //System.out.println(country + ": " exports + ": "+ value);
                //System.out.print(country + ": ");
                //System.out.print(record.get("Exports") + ": ");
                //System.out.println(record.get("Value(dollars)"));
            }   
        } 
        return info;
    }  
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }    
        }    
    } 
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                count ++;
            }    
        } 
        return count;
    }    
    
    public void bigExporters(CSVParser parser, String dollars){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            if(value.length() > dollars.length()){
                System.out.println(country + " " + value);
            }    
        }    
    }    
    
}
