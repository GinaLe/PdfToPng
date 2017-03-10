import java.util.HashMap;

import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */

public class OpticalMarkReader {
	public enum Response {
		A, B, C, D, E, NULL;
	}

	int threshold = 50;
	int[] r;
	int[] c;
	int[][] reference;

	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		image.filter(PImage.GRAY);
		findIndexes(image, 26);
		// threshold(image);
		for (int x = 0; x < c.length; x++) {
			// System.out.println("("+r[x]+", "+c[x]+")");
		}

		AnswerSheet answers = new AnswerSheet(26);
		for (int i = 0; i < r.length; i++) {
			answers.fillAnswer(i, determineBubble(r[i], c[i], 195, 15, 5, image));

		}
		return answers;

	}

	public void findIndexes(PImage image, int questions) {
		reference = new int[15][15];
		for (int y = 0; y < 15; y++) {
			for (int x = 0; x < 15; x++) {
				reference[y][x] = 0;
			}
		}
		r = new int[questions];
		c = new int[questions];
		int index = 0;
		for (int y = 450; y < image.height - reference.length; y++) {
			for (int x = 0; x < 100; x++) {
				int[][] section = new int[reference.length][reference[0].length];
				for (int i = y; i < y + 15; i++) {
					for (int j = x; j < x + 15; j++) {
						section[i - y][j - x] = getPixelAt(i, j, image);
					}
				}
				if (compare(reference, section)) {
					r[index] = y;
					c[index] = x + 130;
					if (index + 19 < questions) {
						r[index + 19] = y;
						c[index + 19] = x + 710;
					}
					index++;
					y += 15;
				}
			}
		}
	}

	public boolean compare(int[][] reference, int[][] tester) {
		int totalDiff = 0;
		int threshold = reference.length * reference[0].length / 10;
		for (int r = 0; r < reference.length; r++) {
			for (int c = 0; c < reference[0].length; c++) {
				totalDiff += Math.abs(reference[r][c] - tester[r][c]);
			}
		}
		totalDiff /= 255;
		return totalDiff < threshold;
	}

	public void threshold(PImage image) {
		for (int i = 0; i < image.width * image.height; i++) {
			if (image.pixels[i] < threshold) {
				image.pixels[i] = 0;
			} else {
				image.pixels[i] = 255;
			}
		}
	}

	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();

		int index = row * image.width + col;

		return image.pixels[index] & 255;
	}

	public int determineBubble(int r, int c, int width, int height, int numBubbles, PImage image) {
		int sectionWidth = width / numBubbles;
		int darkestBox = 0;
		int darkestAverage = Integer.MAX_VALUE;
		for (int i = 0; i < numBubbles; i++) {
			int averageDarkness = 0;
			for (int x = sectionWidth * i; x < sectionWidth * (i + 1); x++) {
				for (int y = 0; y < height; y++) {

					averageDarkness += getPixelAt(y + r, x + c, image);
				}

			}
			if (averageDarkness < darkestAverage) {
				darkestBox = i;
				darkestAverage = averageDarkness;
			}

		}
		
		return darkestBox;
	}
}
