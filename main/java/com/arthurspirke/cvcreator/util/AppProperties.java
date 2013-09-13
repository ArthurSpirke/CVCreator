package com.arthurspirke.cvcreator.util;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AppProperties {
	private static ResourceBundle res = null;

	static {
		res = ResourceBundle.getBundle("com.arthurspirke.cvcreator.properties.prop");
	}

	public static String[] getPersonKeys() {return res.getString("person_keys").split(",");}
	public static String[] getAddressKeys(){return res.getString("person_address").split(",");}
	public static String[] getPersonalInfoKeys(){return res.getString("person_info").split(",");}
	public static String[] getPersonTemplatesKeys(){return res.getString("person_templates").split(",");}
	public static String[] getPhoneNumbersKeys() {return res.getString("person_phones_keys").split(",");}
	public static String[] getSkillsKeys() {return res.getString("skills_keys").split(",");}
	public static String[] getEducationKeys() {return res.getString("education_keys").split(",");}
	public static String[] getEmploymentKeys() {return res.getString("employment_keys").split(",");}
	public static String[] getProjectKeys() {return res.getString("project_keys").split(",");}
	public static String[] getCertificateKeys() {return res.getString("certificate_keys").split(",");}
	public static String[] getTemplatesKeys() {return res.getString("templates_keys").split(",");}
	public static String[] getPersonLinksKeys() {return res.getString("person_links_keys").split(",");}
	public static String getPathToSaveImage() {return res.getString("pathToSaveImage");}
	public static String getPathToHTMLTemplates() {return res.getString("pathToHTMLTemplates");}
	public static String getPathToSaveFinalDocs() {return res.getString("pathToSaveFinalDocs");}
	public static String getCuttingPath() {return res.getString("forCuttingPath");}
	public static String getSiteUrl() {return res.getString("siteURL");}
	public static String getNoImage() {return res.getString("noImage");}
	public static String getPathToSaveTempDocs() {return res.getString("pathToSaveTempDocs");}
	public static String getEmailAdminUsername() {return res.getString("eMailAdminUsername");}
	public static String getEmailAdminPassword() {return res.getString("eMailAdminPassword");}
	public static String getEduTypes() {return res.getString("eduTypes");}
	public static String getCertSizes() {return res.getString("imageSize");}
	public static String getPdfLinkName(){return res.getString("pdf_link");}
	public static String getHtmlLinkName(){return res.getString("html_link");}
	public static String getDocLinkName(){return res.getString("doc_link");}	
	public static String getPreviewTempLinkName(){return res.getString("path_to_temp_review");}	
	public static String getStatusName(){return res.getString("response_status");}	
	public static String getSendedEMailTitle(){return res.getString("sended_e_mail_title");}	
	public static List<String> getStaticFieldsNames() {return Arrays.asList(Utils.replaceDollarSignToSharp(res.getString("html_fields").split(",")));}
	public static List<String> getStaticFieldsArraysNames(){return Arrays.asList(Utils.replaceDollarSignToSharp(res.getString("html_arrays_fields").split(",")));}
	public static List<String> getErrorStaticFields(){return Arrays.asList(Utils.replaceDollarSignToSharp(res.getString("errorStaticFields").split(",")));}
	public static String[] getPlacesIdNames(){return res.getString("places_id").split(",");}
	public static String pathToMyLinkIcon(){return res.getString("my_link");}
	public static String pathToLinkedinIcon(){return res.getString("linkedin");}	
	public static String pathToFacebookIcon(){return res.getString("facebook");}	
	public static String pathToGooglePlusIcon(){return res.getString("google_plus");}
	public static String pathToHomePhoneIcon(){return res.getString("home_phone");}	
	public static String pathToMobilePhoneIcon(){return res.getString("mobile_phone");}	
	public static String pathToSkypeIcon(){return res.getString("skype");}	
	public static String pathToGoogleHahgOutsIcon(){return res.getString("google_hangouts");}
	public static String getFontTimes(){return res.getString("times");}
	
	public static String[] tempEmpMainInfoKeys(){
		return res.getString("emp_main_info").split(",");
	}
	
	public static String[] tempEmpAddressKeys(){
		return res.getString("emp_address").split(",");
	}
	
	public static String[] tempProjectsKeys(){
		return res.getString("emp_projects").split(",");
	}

	
	public static String getStorageType(){
		return res.getString("storage_type");
	}

}
