package com.arthurspirke.cvcreator.util;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class ImageUtils {
	private static Logger log = Logger.getLogger(ImageUtils.class);
	
	public static int getUniqueImageId() {    
		long time = System.currentTimeMillis();
		long result = (time / 1000) - 100_000_000;

		return (int) result;
	}

	
	public static List<String> getPathToDownloadImages(
			List<Certificate> certificate) {
		List<String> pathToImages = new ArrayList<>();
		for (Certificate cert : certificate) {
			log.debug("PathToImages - " + cert.getImages().getUrl().getPath());
			pathToImages.add(cert.getImages().getUrl().getPath());
		}

		return pathToImages;
	}
	
	public static boolean isRealImage(Part part) {
		boolean flag = false;
		String fileName = getFileName(part);
		String[] formats = { ".jpg", ".jpeg", ".png", ".gif" };

		for (String f : formats) {
			if (fileName.contains(f)) {
				flag = true;
				break;
			}
		}

		return flag;

	}
	
	public static Image getFakeImage() {
		Image images = null;
		try {
			images = Image.getInstance(new URL(AppProperties.getNoImage()));
		} catch (BadElementException | IOException e) {
			log.error("Error - " + e);
		}

		return images;
	}
	

	public static List<Integer> getHeigthAndWidthToImage(String source){
		List<Integer> returnList = new ArrayList<>();
		
		if(source.equals("")){
			returnList.add(0);
			returnList.add(0);			
		} else {
			String[] tempArray = source.split("x");
			returnList.add(Integer.parseInt(tempArray[0]));
			returnList.add(Integer.parseInt(tempArray[1]));
		}
		
		
		return returnList;
	}
	
	
	
	public static Image[] getImagesToFromDir(ArrayList<String> list) {
		Image[] images = new Image[list.size()];
		for (int i = 0; i < list.size(); i++) {
			try {
				images[i] = Image
						.getInstance(new URL("http://" + list.get(i)));
			} catch (BadElementException e) {
				log.error("Error - " + e);
			} catch (MalformedURLException e) {
				log.error("Error - " + e);
			} catch (IOException e) {
				log.error("Error - " + e);
			}
		}

		return images;
	}


	public static String getFileName(Part part) {
		String fileName = "";
		for (String s : part.getHeader("content-disposition").split(";")) {
			if (s.trim().startsWith("filename")) {
				fileName = s.split("=")[1].replaceAll("\"", "");
			}
		}
		return fileName;
	}
	
	
	

}
