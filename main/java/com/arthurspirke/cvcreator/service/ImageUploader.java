package com.arthurspirke.cvcreator.service;

import static com.arthurspirke.cvcreator.util.ImageUtils.getUniqueImageId;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.arthurspirke.cvcreator.util.AppProperties;
import com.arthurspirke.cvcreator.util.Utils;

public class ImageUploader {
	private final Collection<Part> parts;
	private final int imageId;
	
	public ImageUploader(Collection<Part> parts){
		this.parts = parts;
		this.imageId = getUniqueImageId();
	}
	
	
	
	
	public Map<String, List<String>> uploadImages(){
		
		List<String> good = new ArrayList<>();
		List<String> bad = new ArrayList<>();
		Map<String, List<String>> returnMap = new HashMap<>();
		
		String pathToSave = AppProperties.getPathToSaveImage();
		new File(pathToSave + imageId).mkdir();
		
		for(Part part : parts){
			if(isRealImage(part)){
				good.add(Utils.getCutPath(uploadAndGetPath(part, pathToSave)));
			} else {
				bad.add(getFileName(part));
			}
		}
		
		returnMap.put("goodImages", good);
		returnMap.put("badImages", bad);
		return returnMap;
	}
	
	
	
	
	public String uploadAndGetPath(Part part, String path) {
		OutputStream out = null;
		InputStream in = null;
	    String pathName = "";

		try {
				if (part.getName().equals("newFile")) {
					String imageName = getFileName(part);
					out = new FileOutputStream(new File(path + imageId + "/" + imageName));
					in = part.getInputStream();

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = in.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					out.close();
					
					pathName = path + imageId + "/" + imageName;
				}
		} catch (Exception ex) {
			//NOP
		}
		
		return pathName;
	}
	
	
	
	private boolean isRealImage(Part part) {
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
	
	
	private String getFileName(Part part) {
		String fileName = "";
		for (String s : part.getHeader("content-disposition").split(";")) {
			if (s.trim().startsWith("filename")) {
				fileName = s.split("=")[1].replaceAll("\"", "");
			}
		}
		return fileName;
	}
	
}
