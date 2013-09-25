package com.arthurspirke.cvcreator.templates;

import java.util.ResourceBundle;

import com.arthurspirke.cvcreator.entity.enums.Icons;
import com.itextpdf.text.Image;

public interface PDFFormat extends MainFormat{

      public void init(String path, ResourceBundle lang);
      public void close();
      public void staticTitle();
      public void templatePersonalData(String firstName, String secondName);//add telephone, email etc.
      public void templatePosition(String claimPosition);
      public void templateContactInfo(String eMail, String country, String region, String city, String address, String zipCode);
      public void templateProfile(String profile);
      public void templateHobbies(String hobbies);
      public void templatePersonLinks(Icons linkType, String linkName);
      public void templatePhoneNumbers(Icons phoneType, String phoneNumber);
      public void staticSkillsTitle();
      public void templateSkills(String type, String concrete);
      public void staticEducationTitle();
      public void templateEducation(String eduType, String eduTitle, String eduYears, String eduDegree, String eduCountry, String eduRegion, String eduCity, String eduDescription);
      public void staticEmploymentTitle();
      public void templateEmploymentHistory(String empTitle, String empYears, String empPosition, String empCountry, String empRegion, String empCity, String empDescription);
      public void templateProjectTitle();
      public void templateProject(String projTitle, String projPosition, String projYears, String projDescription);
      public void staticCertificateTitle();
      public void templateCertificate(Image image, Integer height, Integer width, String small, String full);

}
