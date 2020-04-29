
/**
 * Write a description of Assignement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Assignement {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    //public void doSave(ImageResource inImage) {
        //ImageResource outImage = inImage;
        //String fname = inImage.getFileName();
	//String newName = "copy-" + fname;
	//outImage.setFileName(newName);
	//outImage.draw();
	//outImage.save();
    //}
    
    //public void selectConvtSave(){
        //DirectoryResource dr = new DirectoryResource();
        //for(File f : dr.selectedFiles()){
            //ImageResource image = new ImageResource(f);
            //doSave(makeGray(image));
        //}
    //}
    //public void doSave() {
	//DirectoryResource dr = new DirectoryResource();
	//for (File f : dr.selectedFiles()) {
		//ImageResource image = new ImageResource(f);
		//String fname = image.getFileName();
		//String newName = "copy-" + fname;
		//image.setFileName(newName);
		//image.draw();
		//image.save();
	//}
    //}   
    // note: doSave can save the copied file to same folder as original file;
    // selectConvrtSace will save the converted file to same folder as .class.
    
    public void selectConvtSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            //note: you can't get the correct name from a converted image. Do it now.
            ImageResource grayImage = makeGray(image);
            String grayName = "gray-" + fname;
            grayImage.setFileName(grayName);
            grayImage.draw();
            grayImage.save();
        }
    }
}
