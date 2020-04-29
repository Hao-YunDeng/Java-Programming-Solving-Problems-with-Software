
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
    
    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0,"TAA");
        System.out.println(dex);
        
        dex = findStopCodon(dna, 1,"TAA");
        System.out.println(dex);
        
        dex = findStopCodon(dna, 3,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 9,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 0,"TAG");
        System.out.println(dex);       
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
    
    public void testFindGene(){
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "GTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TTTCAGTGAGCTTACACATGCAAGTATCCGCGCGCCAGTGAAAATGCCCT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TCAAATCATTACTGACCATAAAGGAGCGGGTATCAAGCACACACCTATGT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "AGCTCACAACACCTTGCTTAGCCACACCCCCACGGGATACAGCAGTGATA";
        System.out.println("Gene: " + findGene(dna, 0));
    }  
    
    public void printAllGenes(String dna){
        int where = 0;
        while(true){
            String currString = findGene(dna, where);
            if(currString.isEmpty()){
                break;
            }   
            System.out.println(currString);
            where = dna.indexOf(currString, where) + currString.length();
        }    
    } 
    
    public void testOn(String dna){
        System.out.println("The test DNA is " + dna + ", and the genes are ");
        printAllGenes(dna);
    }  
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
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
    
    public void testOnGetAllGenes(String dna){
        System.out.println("The test DNA is " + dna);
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()){
            System.out.println(g);
        } 
    }    
    
    public void testGetAllGenes(){
       //testOnGetAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGAaaaATGTAAATGTAA");
       //testOnGetAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGAaaaATGATCTAATTTATGCTGCAACGGTGAAGA");
       //testOnGetAllGenes("oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAA");  
       testOnGetAllGenes("oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAGC");
    }
}
