package com.arthurspirke.cvcreator.entity.business;

public class LinksToFiles {
	private final String id;
	private final String personId;
	private final String pdfFile;
	private final String docFile;
	private final String htmlFile;


	public LinksToFiles(String id, String personId, String pdfFile,
			String docFile, String htmlFile) {
		this.id = id;
		this.personId = personId;
		this.pdfFile = pdfFile;
		this.docFile = docFile;
		this.htmlFile = htmlFile;
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
