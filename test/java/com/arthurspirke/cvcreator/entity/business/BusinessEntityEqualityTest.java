package com.arthurspirke.cvcreator.entity.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.arthurspirke.cvcreator.entity.enums.EducationType;
import com.arthurspirke.cvcreator.entity.enums.ImageSize;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.enums.LinkIcon;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;
import com.arthurspirke.cvcreator.entity.enums.TemplateDOC;
import com.arthurspirke.cvcreator.entity.enums.TemplateHTML;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.util.Utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class BusinessEntityEqualityTest {

	private static PersonalInfo personalInfo1 = null;
	private static PersonalInfo personalInfo2 = null;
	
	private static Address address1 = null;
	private static Address address2 = null;
	
	private static PersonalTemplates templates1 = null;
	private static PersonalTemplates templates2 = null;
	
	private static PhoneNumbers phoneNumber1 = null;
	private static PhoneNumbers phoneNumber2 = null;
	
	private static PersonLinks personLink1 = null;
	private static PersonLinks personLink2 = null;
	
    private static Skills skills1 = null;
    private static Skills skills2 = null;
    
    private static Education education1 = null;
    private static Education education2 = null;
    
    private static Project project1 = null;
    private static Project project2 = null;
    
    private static EmploymentHistory empHistory1 = null;
    private static EmploymentHistory empHistory2 = null;
    
    private static Certificate certificate1 = null;
    private static Certificate certificate2 = null;
    
    private static Person person1 = null;
    private static Person person2 = null;
	
    
    @BeforeClass
    public static void init(){
    	String personId = Utils.getUniqueId();
    	
    	String personalInfoId = Utils.getUniqueId();
    	personalInfo1 = new PersonalInfo(personalInfoId, personId, "John", "Smith", "Java Programmer", "myemail@gmail.com", "My Profile", "My Hobbies", "new");
        personalInfo2 = new PersonalInfo(personalInfoId, personId, "John", "Smith", "Java Programmer", "myemail@gmail.com", "My Profile", "My Hobbies", "new");
        
        String addressId = Utils.getUniqueId();
        address1 = new Address(addressId, "11", personId, 333, 222, 111, 123456, "Main Street", Language.ENGLISH, "new");
        address2 = new Address(addressId, "11", personId, 333, 222, 111, 123456, "Main Street", Language.ENGLISH, "new");
    
        String templatesId = Utils.getUniqueId();
        templates1 = new PersonalTemplates(templatesId, personId, TemplatePDF.SIMPLE, TemplateHTML.EXTENDED, TemplateDOC.SIMPLE, "new");
        templates2 = new PersonalTemplates(templatesId, personId, TemplatePDF.SIMPLE, TemplateHTML.EXTENDED, TemplateDOC.SIMPLE, "new");
        
        String personLinkId = Utils.getUniqueId();
        personLink1 = new PersonLinks(personLinkId, personId, LinkIcon.GOOGLE_PLUS, "http://google.com/google_plus", "new");
        personLink2 = new PersonLinks(personLinkId, personId, LinkIcon.GOOGLE_PLUS, "http://google.com/google_plus", "new");
        List<PersonLinks> personLinks = Arrays.asList(personLink1, personLink2);
        
        String phoneNumberId = Utils.getUniqueId();
        phoneNumber1 = new PhoneNumbers(phoneNumberId, personId, PhoneIcon.GOOGLE_HANGOUTS, "+19853463", "new");
        phoneNumber2 = new PhoneNumbers(phoneNumberId, personId, PhoneIcon.GOOGLE_HANGOUTS, "+19853463", "new");
        List<PhoneNumbers> phoneNumbers = Arrays.asList(phoneNumber1, phoneNumber2);
        
        String skillsId = Utils.getUniqueId();
	    skills1 = new Skills(skillsId, personId, "Lang", "Java, JavaScript, Scala", "new");
	    skills2 = new Skills(skillsId, personId, "Lang", "Java, JavaScript, Scala", "new");
	    List<Skills> skills = Arrays.asList(skills1, skills2);
	    		
	    String eduId = Utils.getUniqueId();
	    education1 = new Education(eduId, personId, EducationType.getType("University"), "MIT", "2008-2013", "Master of CS", address1, "My Desc", "new");
	    education2 = new Education(eduId, personId, EducationType.getType("University"), "MIT", "2008-2013", "Master of CS", address1, "My Desc", "new");   
        List<Education> educations = Arrays.asList(education1, education2);
        
	    String projId = Utils.getUniqueId();
	    String companyId = Utils.getUniqueId();
	    
   	    project1 = new Project(projId, companyId, personId, "Project", "Java Programmer", "2008-2013", "My desc", "", "new");
   	    project2 = new Project(projId, companyId, personId, "Project", "Java Programmer", "2008-2013", "My desc", "", "new");
   	    List<Project> projects = Arrays.asList(project1, project2);
   	    
   	    empHistory1 = new EmploymentHistory(companyId, personId, "Google", "2008-2013", "Java Programmer", "My Desc", address1, projects, "new");
   	    empHistory2 = new EmploymentHistory(companyId, personId, "Google", "2008-2013", "Java Programmer", "My Desc", address1, projects, "new");
        List<EmploymentHistory> empHistory = Arrays.asList(empHistory1, empHistory2);
        
   	    String certId = Utils.getUniqueId();   
   	    certificate1 = new Certificate(certId, personId, ImageSize._300x400, "http://site.com/image.jpg", "Small desc", "Full desc", "new");
   	    certificate2 = new Certificate(certId, personId, ImageSize._300x400, "http://site.com/image.jpg", "Small desc", "Full desc", "new");
        List<Certificate> certificates = Arrays.asList(certificate1, certificate2);
   	    
   	    person1 = new Person(personId, personalInfo1, address1, templates1, Language.ENGLISH, "new");
   	    person1.setPersonLinks(personLinks);
   	    person1.setPhoneNumbers(phoneNumbers);
   	    person1.setSkills(skills);
   	    person1.setEducation(educations);
   	    person1.setEmploymentHistory(empHistory);
   	    person1.setCertificate(certificates);
   	    
   	    person2 = new Person(personId, personalInfo1, address1, templates1, Language.ENGLISH, "new");
   	    person2.setPersonLinks(personLinks);
   	    person2.setPhoneNumbers(phoneNumbers);
   	    person2.setSkills(skills);
   	    person2.setEducation(educations);
   	    person2.setEmploymentHistory(empHistory);
   	    person2.setCertificate(certificates);
    }
    
    @Test
    public void testAddressEquals(){
    	assertThat(address1.equals(address2), is(equalTo(true)));
    }
    
    @Test
    public void testAddressHashCode(){
    	assertThat(address1.hashCode()== address2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testPersonalInfoEquals(){
    	assertThat(personalInfo1.equals(personalInfo2), is(equalTo(true)));
    }
    
    @Test
    public void testPersonalInfoHashCode(){
    	assertThat(personalInfo1.hashCode() == personalInfo2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testPersonalTemplatesEquals(){
    	assertThat(templates1.equals(templates2), is(equalTo(true)));
    }
    
    @Test
    public void testPersonalTemplatesHashCode(){
    	assertThat(templates1.hashCode() == templates2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testPhoneNumbersEquals(){
    	assertThat(phoneNumber1.equals(phoneNumber2), is(equalTo(true)));
    }
    
    @Test
    public void testPhoneNumbersHashCode(){
    	assertThat(phoneNumber1.hashCode() == phoneNumber2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testPersonLinksEquals(){
    	assertThat(personLink1.equals(personLink2), is(equalTo(true)));
    }
    
    @Test
    public void testPersonLinksHashCode(){
    	assertThat(personLink1.hashCode() == personLink2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testSkillsEquals(){
    	assertThat(skills1.equals(skills2), is(equalTo(true)));
    }
    
    @Test
    public void testSkillsHashCode(){
    	assertThat(skills1.hashCode() == skills2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testEducationEquals(){
    	assertThat(education1.equals(education2), is(equalTo(true)));
    }
    
    @Test
    public void testEducationHashCode(){
    	assertThat(education1.hashCode() == education2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testProjectEquals(){
    	assertThat(project1.equals(project2), is(equalTo(true)));
    }
    
    @Test
    public void testProjectHashCode(){
    	assertThat(project1.hashCode() == project2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testEmploymentHistoryEquals(){
    	assertThat(empHistory1.equals(empHistory2), is(equalTo(true)));
    }
    
    @Test
    public void testEmploymentHistoryHashCode(){
    	assertThat(empHistory1.hashCode() == empHistory2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testCertificateEquals(){
    	assertThat(certificate1.equals(certificate2), is(equalTo(true)));
    }
    
    @Test
    public void testCertificateHashCode(){
    	assertThat(certificate1.hashCode() == certificate2.hashCode(), is(equalTo(true)));
    }
    
    @Test
    public void testPersonEquals(){
    	assertThat(person1.equals(person2), is(equalTo(true)));
    }
    
    @Test
    public void testPersonHashCode(){
    	assertThat(person1.hashCode() == person2.hashCode(), is(equalTo(true)));
    }
}
