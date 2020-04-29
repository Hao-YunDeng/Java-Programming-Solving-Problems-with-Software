
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
    
    public void testcgRatio() {
        String dna = "ATGCCATAG";
        //String dna ="oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAA";
        System.out.println("cgRatio on " + dna + " = " + cgRatio(dna));
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
    
    public void testCountCTG() {
        String dna = "CTGCCTGGCTGCTG";
        System.out.println("CTG occurences on " + dna + " = " + countCTG(dna));
    }
    
}
