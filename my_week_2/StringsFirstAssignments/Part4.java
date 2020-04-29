
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    public void printUrls(String url){
        URLResource ur = new URLResource(url);
        for(String word : ur.words()){
            String lowerCaseWord = word.toLowerCase();
            int index = lowerCaseWord.indexOf("youtube.com");
            if(index != -1){
                int leftQuoteIndex = word.indexOf("\"");
                int rightQuoteIndex = word.indexOf("\"", leftQuoteIndex + 1);
                System.out.println(word.substring(leftQuoteIndex, rightQuoteIndex + 1));
            }    
        }    
    }    
    public void test(){
        printUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        System.out.println("----End----");
    }    
}
