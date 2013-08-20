package com.arthurspirke.cvcreator.entity.domain;

import java.io.IOException;
import java.net.URL;

import com.arthurspirke.cvcreator.entity.enums.ImageSize;
import com.arthurspirke.cvcreator.util.ImageUtils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class Certificate {
	private final int id;
	private final int personId;
    private ImageSize sizeOfImage;
	private Image image;
	private final String smallDescription;
	private final String fullDescription;
	private final String state;

	public Certificate(int id, int personId, ImageSize sizeOfImage, String pathToImage, String smallDescription, String fullDescription, String state) {
		this.id = id;
		this.personId = personId;
		this.smallDescription = smallDescription;
		this.fullDescription = fullDescription;
		this.state = state;
		this.image = getImageFromPath(pathToImage);
        this.sizeOfImage = sizeOfImage;   
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

    public ImageSize getImageSize(){
    	return sizeOfImage;
    }
	
	public Image getImages() {
		return image;
	}

	public String getSmallDescription() {
		return smallDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public String getState() {
		return state;
	}
	
	
	private Image getImageFromPath(String path){
		if(path == null || path.equals("")) return ImageUtils.getFakeImage();
		Image image = null;		
		try {
			image = Image.getInstance(new URL(path));
			//TODO: work with height/width
			//image.scaleToFit(width, height);
		} catch (BadElementException | IOException e) {
			//NOP
		}
		return image;
	}

}
