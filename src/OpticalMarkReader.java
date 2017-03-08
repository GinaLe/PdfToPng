import java.util.HashMap;

import AnswerSheetSet.AnswerSheet;
import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */

public class OpticalMarkReader {
	public enum Response {
		A,B,C,D,E,NULL;
	}
	int threshold = 50;
	int[]r;
	int[]c;
	int[][] reference;


	/***
	 * Method to do optical mark reading on page image.  Return an AnswerSheet object representing the page answers.
	 * @param image
	 * @return
	 */
	public AnswerSheetSet.AnswerSheet processPageImage(PImage image) {
	image.filter(PImage.GRAY);
	threshold(image);
	
	AnswerSheetSet.AnswerSheet answers= new AnswerSheetSet.AnswerSheet();
	for(int i=0;i<r.length;i++){
		answers.fillAnswer(i,determineBubble(r[i],c[i],200,15,5,image));
	}
	return answers;
	
	}
	
	public void findIndexes(PImage image,  int questions){
		reference= new int[15][15];
		for(int y=0;y<15;y++){
			for(int x=0;x<15;x++){
				reference[y][x]=0;
			}
		}
		int[]rIndexes= new int[questions];
		int[]cIndexes= new int[questions];
		int index=0;
		for(int r=0;r<image.height-reference.length;r++){
			for(int c=0;c<image.width-reference[0].length;c++){
				int[][]section= new int[reference.length][reference[0].length];
				for(int i=r;i<r+10;i++){
					for(int j=c;j<c+40;j++){
						section[i-r][j-c]=getPixelAt(i,j,image);
					}
				}
				if(compare(reference,section)){
					rIndexes[index]=r;
					cIndexes[index]=c+130;
					rIndexes[index+19]=r;
					cIndexes[index+19]=c+710;
					index++;
				}
			}
		}
	}
	public boolean compare(int[][]reference, int[][]tester){
		int totalDiff=0;
		int threshold = reference.length*reference[0].length/10;
		for(int r=0;r<reference.length;r++){
			for(int c=0;c<reference[0].length;c++){
				totalDiff+=Math.abs(reference[r][c]-tester[r][c]);
			}
		}
		totalDiff/=255;
		return totalDiff<threshold;
	}
	public void threshold(PImage image) {
		for(int i = 0; i < image.width * image.height; i++) {
			if(image.pixels[i]<threshold){
				image.pixels[i]=0;
			}else{
				image.pixels[i]=255;
			}
		}
	}
	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		
		int index = row*image.width + col;
		
		return image.pixels[index]&255;
	}
	
	
	public int determineBubble(int r, int c, int width, int height, int numBubbles, PImage image){
		int sectionWidth= width/numBubbles;
		int darkestBox = 0;
		int darkestAverage=255;
		for(int i=0;i<  numBubbles;i++){
			int averageDarkness=0;
			for(int x = sectionWidth*i;x<  sectionWidth*(i+1);x++){
				for(int y = 0;y<  height;x++){
					averageDarkness+=getPixelAt(y+r,x+c,image);
				}
				averageDarkness/=(sectionWidth*height);
				if(averageDarkness<  darkestAverage){
					darkestBox=i;
					darkestAverage=averageDarkness;
				}
			}
			
		}
		return darkestBox;
	}
}

