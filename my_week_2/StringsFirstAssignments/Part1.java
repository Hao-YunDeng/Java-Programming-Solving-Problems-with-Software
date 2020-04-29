
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
        public String findSimpleGene(String dna) {
                int start = dna.indexOf("ATG");
                if (start == -1){
                        return "";
                } 
                int stop = dna.indexOf("TAA", start+3);
                if (stop == -1){
                        return "";
                }
                if ((stop - start) % 3 ==0){
                        return dna.substring(start, stop + 3);
                }   
                else {
                        return "";
                }    
        }  
        
        public void testSimpleGene(){
                String a = "AAATGCCCTAACTAGATTAAGAAACC";
		String ap = "CCAATGCAGCGATAC";
		String apa = "CTAATCCGGATCCGA";
		String app = "CCAGCATGCCAGTCAGCTAACAG";
		String appa = "CCAGCATGCCAGTAGCTAACAG";
		System.out.print("The string is " + a + ", and the gene is " +findSimpleGene(a) +".\n");
		System.out.print("The string is " + ap + ", and the gene is " +findSimpleGene(ap) +".\n");
		System.out.print("The string is " + apa + ", and the gene is " +findSimpleGene(apa) +".\n");
		System.out.print("The string is " + app + ", and the gene is " +findSimpleGene(app) +".\n");
		System.out.print("The string is " + appa + ", and the gene is " +findSimpleGene(appa) +".\n");
        }    
           
}
