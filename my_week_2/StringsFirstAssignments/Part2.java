
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
       public String findSimpleGene(String dna, String startCodon, String stopCodon) {
                if (Character.isUpperCase(dna.charAt(0))){
                        startCodon = startCodon.toUpperCase();
                        stopCodon = stopCodon.toUpperCase();
                }
                else{
                        startCodon = startCodon.toLowerCase();
                        stopCodon = stopCodon.toLowerCase();
                }    
                int start = dna.indexOf(startCodon);
                if (start == -1){
                        return "";
                } 
                int stop = dna.indexOf(stopCodon, start+3);
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
        String a = "CCATCAATAACATGA";
        String ap = "CCAATGCAGCGATAC";
        String apa = "CTAATCCGGATCCGA";
        String app = "ccagcatgccagtcagctaacag";
        String appa = "CCAGCATGCCAGTAGCTAACAG";
        System.out.print("The string is " + a + ", and the gene is " +findSimpleGene(a, "ATG", "TAA") +".\n");
        System.out.print("The string is " + ap + ", and the gene is " +findSimpleGene(ap, "ATG", "TAA") +".\n");
        System.out.print("The string is " + apa + ", and the gene is " +findSimpleGene(apa, "ATG", "TAA") +".\n");
        System.out.print("The string is " + app + ", and the gene is " +findSimpleGene(app, "ATG", "TAA") +".\n");
        System.out.print("The string is " + appa + ", and the gene is " +findSimpleGene(appa, "ATG", "TAA") +".\n");
        } 

}
