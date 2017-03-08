
public class methods {



public static int determineBubble(int r, int c, int width, int height, int numBubbles, int[][] pixels) {
	int subRegionWidth = (width - c) / numBubbles;
	int darkestSubRegion = 0;
	int colorDensity = 255;
	for(int i = 1; i < numBubbles + 1; i++) {
		int subRegionColorCount = 0;
		for(int row = r; row < height; row++) {
			for(int col = c; col < i*subRegionWidth; col++) {
				subRegionColorCount += pixels[row][col]; 
			}
		}
		if(colorDensity > subRegionColorCount){
			darkestSubRegion = i;
			colorDensity = subRegionColorCount;
	}
	
}
	return darkestSubRegion;
}
}
