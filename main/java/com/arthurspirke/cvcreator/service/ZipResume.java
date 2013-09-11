package com.arthurspirke.cvcreator.service;

public class ZipResume {
	
	  private final static String NAME_ZIP_ALL_FILES = "allResume";
      private final static String OUTPUT_FOLDER = "";
      private final String inputFolder;
      private final String namePdfFile;
      private final String nameHtmlFile;
      private final String nameDocFile;
      private String pathToZipPdf;
      private String pathToZipHtml;
      private String pathToZipDoc;
      private String pathToAllZipFiles;
      
      
     public ZipResume(String inputFolder, String namePdfFile){
    	 this(inputFolder, namePdfFile, "");
     }
     
     public ZipResume(String inputFolder, String namePdfFile, String nameHtmlFile){
    	 this(inputFolder, namePdfFile, nameHtmlFile, "");
     }
     
     public ZipResume(String inputFolder, String namePdfFile, String nameHtmlFile, String nameDocFile){
    	 this.inputFolder = inputFolder;
    	 this.namePdfFile = namePdfFile;
    	 this.nameHtmlFile = nameHtmlFile;
    	 this.nameDocFile = nameDocFile;
     }
     
     
    public void zipFiles(){
    	
    }

	public String getPathToZipPdf() {
		return pathToZipPdf;
	}

	public String getPathToZipHtml() {
		return pathToZipHtml;
	}

	public String getPathToZipDoc() {
		return pathToZipDoc;
	}

	public String getPathToAllZipFiles() {
		return pathToAllZipFiles;
	}
     
     
     
     
      
}
