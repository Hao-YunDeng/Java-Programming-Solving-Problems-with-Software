
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int occurr = 0;
        int currpos = stringb.indexOf(stringa);
        while(true){
            if(currpos == -1){
                break;
            }
            occurr = occurr + 1;
            currpos = stringb.indexOf(stringa, currpos + stringa.length());
        }    
        return occurr;
    }    
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        
    }    
}
