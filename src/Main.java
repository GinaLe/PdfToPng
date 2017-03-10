import java.util.ArrayList;

import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest3.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();
	public static AnswerSheetSet sheets;
	
	public static void main(String[] args) {
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);

		System.out.println("Scoring all pages...");
		scoreAllPages(images);
		sheets.calcResults();
		
		CSVFile.writeDataToFile("E:/Gina APCS/PdfToPng/data1.csv",sheets.getData1());
		CSVFile.writeDataToFile("E:/Gina APCS/PdfToPng/data2.csv",sheets.getData2());
		System.out.println("Complete!");
		
		// Optional:  add a saveResults() method to save answers to a csv file
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE:  YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D LIKE
	 * 
	 * @param images List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
	

		// Score the first page as the key
		AnswerSheet key = markReader.processPageImage(images.get(0));
		sheets= new AnswerSheetSet(key);
		
		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.processPageImage(image);
			sheets.addSheet(answers);
			// do something with answers
		}
		
	}
	
}