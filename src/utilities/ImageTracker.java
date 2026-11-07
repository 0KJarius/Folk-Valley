package utilities;

import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageTracker {
	private static ImageTracker TRACKER = new ImageTracker();
	private static HashMap<ImageIcon, String> ImageMap = new HashMap<ImageIcon, String>();
	
	
	private ImageTracker() {	
	}


	public static ImageTracker getInstance() {
		return TRACKER;
	}


	public static HashMap<ImageIcon, String> getImageMap() {
		return ImageMap;
	}
}
