package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.util.AppProperties;

public class LinksToFiles {
	
	private enum FileExtension{
		PDF("pdf"),
		DOC("doc"),
		HTML("html");
		
		private String lowerCaseName;
		
		private FileExtension(String lowerCaseName){
			this.lowerCaseName = lowerCaseName;
		}
		
		public String getName(){
			return lowerCaseName;
		}
		
	}
	
	private final String id;
	private final String personId;
	private final String pdfFile;
	private final String docFile;
	private final String htmlFile;


	public LinksToFiles(String id, String personId) {
		this.id = id;
		this.personId = personId;
		this.pdfFile = getFullPathToFile(FileExtension.PDF.getName());
		this.docFile = getFullPathToFile(FileExtension.DOC.getName());
		this.htmlFile = getFullPathToFile(FileExtension.HTML.getName());
	}

	private String getFullPathToFile(String extension) {
		String path = AppProperties.getPathToSaveFinalDocs();
		return path + personId + "/" + personId + "." + extension;
	}
	
	public String getId() {
		return id;
	}

	public String getPersonId() {
		return personId;
	}

	public String getPdfFile() {
		return pdfFile;
	}

	public String getDocFile() {
		return docFile;
	}

	public String getHtmlFile() {
		return htmlFile;
	}

}
