package com.arthurspirke.cvcreator.entity.domain;

public class LinksToFiles {
	private final int id;
	private final int personId;
	private final String pdfFile;
	private final String docFile;
	private final String htmlFile;

	public LinksToFiles(int personId, String pdfFile,
			String docFile, String htmlFile) {
           this(0, personId, pdfFile, docFile, htmlFile);
	}
	
	public LinksToFiles(int id, int personId, String pdfFile,
			String docFile, String htmlFile) {
		this.id = id;
		this.personId = personId;
		this.pdfFile = pdfFile;
		this.docFile = docFile;
		this.htmlFile = htmlFile;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
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
