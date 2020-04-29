
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }          
        } 
        
        return dna.length();
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public StorageResource getAllGenes(String dna){
        int where = 0;
        StorageResource geneList = new StorageResource();
        while(true){
            String currString = findGene(dna, where);
            if(currString.isEmpty()){
                break;
            }   
            geneList.add(currString);
            where = dna.indexOf(currString, where) + currString.length();
        }  
        return geneList;
    }
    
    public double cgRatio(String dna){
        double cgCount = 0.0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G' ||
            dna.charAt(i) == 'c' ||
            dna.charAt(i) == 'g'){
                cgCount++;
            }   
        }  
        return cgCount/dna.length();
    }
    
    public int countCTG(String dna){
        int count = 0;
        int where = 0;
        dna = dna.toUpperCase();
        while(true){
            if(dna.indexOf("CTG", where) == -1){
                break;
            }    
            count++;
            where = where + 3;
        }  
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int lengthCount = 0;
        double ratioCount = 0.0;
        int maxLength = 0;
        
        System.out.println("===Genes longer than 60 are===");
        for(String s: sr.data()){
            if(s.length() > 60){
                System.out.println(s);
                lengthCount++;
            }
            
        } 
        
        System.out.println("===Genes with CG ratio higher than 0.35 are===");
        for(String s: sr.data()){
            if(cgRatio(s) > 0.35){
                System.out.println(s);
                ratioCount++;
            }
            
        } 
        
        for(String s: sr.data()){
            if(s.length() > maxLength){
                maxLength = s.length();
            }            
        }
        
        System.out.println("total number of genes is " + sr.size());
        System.out.println("number of genes longer than 60 is " + lengthCount);
        System.out.println("number of genes with CG ratio>0.35 is " + (int)ratioCount);
        System.out.println("largest length is " + maxLength);
    }    
    
    public void testProcessGenes(){
        //FileResource fr = new FileResource("brca1line.fa");
        //String dna = fr.asString();
        //dna = dna.toUpperCase();
        //StorageResource geneList = getAllGenes(dna);
        //processGenes(geneList);
               
        
        FileResource fr1 = new FileResource("GRch38dnapart.fa");
        String dna1 = fr1.asString();
        dna1 = dna1.toUpperCase();
        System.out.println("CTG appears " +  countCTG(dna1) + " times.");
        StorageResource geneList1 = getAllGenes(dna1);
        processGenes(geneList1);
    }    
}
