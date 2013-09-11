package com.arthurspirke.cvcreator.entity.business;

import java.io.IOException;
import java.net.URL;

import com.arthurspirke.cvcreator.entity.enums.ImageSize;
import com.arthurspirke.cvcreator.util.ImageUtils;
import com.arthurspirke.cvcreator.util.Utils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class Certificate extends Component{
    private ImageSize sizeOfImage;
	private Image image;
	private final String smallDescription;
	private final String fullDescription;

	
	public Certificate(String id, String personId, ImageSize sizeOfImage, String pathToImage, String smallDescription, String fullDescription, String state) {
		super(id, personId, state);
		this.smallDescription = smallDescription;
		this.fullDescription = fullDescription;
		this.state = state;
		this.image = getImageFromPath(pathToImage);
        this.sizeOfImage = sizeOfImage;   
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
	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Certificate cert = (Certificate) o;
		
		boolean idCheck = id == cert.id || (id != null && id.equals(cert.getId()));
		boolean personIdCheck = personId == cert.personId || (personId != null && personId.equals(cert.getPersonId()));
		boolean sizeOfImageCheck = sizeOfImage == cert.sizeOfImage || (sizeOfImage != null && sizeOfImage.equals(cert.getImageSize()));
		boolean smallDescriptionCheck = smallDescription == cert.smallDescription || (smallDescription != null && smallDescription.equals(cert.getSmallDescription()));
		boolean fullDescriptionCheck = fullDescription == cert.fullDescription || (fullDescription != null && fullDescription.equals(cert.fullDescription));

		return idCheck && personIdCheck && sizeOfImageCheck && smallDescriptionCheck && fullDescriptionCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (sizeOfImage == null ? 0 : sizeOfImage.hashCode());
		hash = hash * 31 + (smallDescription == null ? 0 : smallDescription.hashCode());
		hash = hash * 31 + (fullDescription == null ? 0 : fullDescription.hashCode());
		return hash;
	}
}
