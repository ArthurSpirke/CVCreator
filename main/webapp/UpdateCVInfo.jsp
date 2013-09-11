<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.arthurspirke.cvcreator.entity.business.*, com.arthurspirke.cvcreator.entity.enums.*, com.arthurspirke.cvcreator.util.*, java.util.List,com.arthurspirke.cvcreator.dblayer.*, java.util.ResourceBundle" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update user</title>
    <link type="text/css" href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/cvstyle.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-2.0.2.js"></script>
    <script type="text/javascript" src="js/jquery.color-2.1.2.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="js/ext-core.js"></script>
    <script type="text/javascript" src="js/theOne.js"></script>
    <script>
        $(document).ready(function(){
            $('#top-of-page').load('top.html');
        });
    </script>
    <script>
        $(document).ready(function(){
            $('#bottom-of-page').load('bottom.html');
        });
    </script>
</head>
<body>

<%
ResourceBundle res = (ResourceBundle) request.getAttribute("res");
Object obj = request.getAttribute("person");
Person person = (Person) obj;
Language lang = (Language) request.getAttribute("lang");
%>



<div id="top-of-page"></div>



<div id="main-page">
<div class="row-fluid">
    <div class="span12">
        <!-- First main block -->
        <div class="span3"></div>
        <!-- Second main block !!! -->
        <div class="span7">
            <!-- Personal Info -->
            <%
            out.println("<h2 id=\"personInfoTitle\">" + res.getString("$personInfoTitle") + "</h2>");
            %>           
            <div class="row-fluid">
                <!-- Main block of Personal Info -->
              <div class="span12">

                   <div class="row-fluid">
                       <div class="span12">
                           <!-- Left block of some inputs-->
                           <div class="span5">
                              <!-- Titles -->
                               <div class="span4">
                                 <%
                                 out.println("<span id=\"firstNameT\" class=\"br-fields\">" + res.getString("$firstNameT") + "</span>:<br>");
                                 out.println("<span id=\"lastNameT\" class=\"br-fields\">" + res.getString("$lastNameT") + "</span>:<br>");
                                 out.println("<span id=\"eMailT\" class=\"br-fields\">" + res.getString("$eMailT") + "</span>:<br>");
                                 %>
                               </div>
                               <!-- Inputs -->
                               <div class="span3">
                                 <%
                                 out.println("<span class=\"exampleInfo\" id=\"firstNameE\">" + res.getString("$firstNameE") + "</span><br>");
                                 out.println("<input type=\"text\" id=\"firstName\" size=\"25\" value=\"" + person.getPersonalInfo().getFirstName() + "\" data-person-id=\"" + person.getId() + "\"><br>");
                                 out.println("<span class=\"exampleInfo\" id=\"lastNameE\">" + res.getString("$lastNameE") + "</span><br>");
                                 out.println("<input type=\"text\" id=\"lastName\" size=\"25\" value=\"" + person.getPersonalInfo().getSecondName() + "\"><br>");
                                 out.println("<span class=\"exampleInfo\" id=\"eMailE\">" + res.getString("$eMailE") + "</span><br>");
                                 out.println("<input type=\"text\" id=\"eMail\" size=\"50\" value=\"" + person.getPersonalInfo().geteMail() + "\"><br>");
                                 %>
                               </div>
                           </div>
                           <!-- Right block of some inputs -->
                           <div class="span6">
                               <!-- Titles -->
                               <div class="span3">
                               <% 
                                out.println("<span class=\"CountryT br-fields\">" + res.getString(".CountryT") + "</span>:<br>");
                                out.println("<span class=\"regionT br-fields\">" + res.getString(".RegionT") + "</span>:<br>");
                                out.println("<span class=\"cityT br-fields\">" + res.getString(".CityT") + "</span>:<br>");
                                out.println("<span id=\"addressT\" class=\"br-fields\">" + res.getString("$addressT") + "</span>:<br>");
                                out.println("<span id=\"zipCodeT\" class=\"br-fields\">" + res.getString("$profileT") + "</span>:<br>");
                                %>
                               </div>
                               <!-- Inputs -->
                               <div class="span3">
                                <%
                                out.println("<div class=\"selectBlock\" id=\"mainSelectBlock\">");
                                out.println("<span class=\"exampleInfo countryExampleInfo\">" + res.getString(".countryExampleInfo") + "</span><br>");
                                out.println("<select class=\"selectCountryClass countrySelect\" id=\"personCountry\" onchange=\"change('personCountry', 'personRegion', 'region_c')\">" + HTMLUtils.getOptionsCountriesList(lang, person.getAddress().getCountryId()) + "</select>");
                                out.println("<span class=\"exampleInfo regionExampleInfo\">" + res.getString(".regionExampleInfo") + "</span><br>");
                                out.println("<select class=\"placesSelect regionSelect\" id=\"personRegion\" onchange=\"change('personRegion', 'personCity', 'city_c')\">" + HTMLUtils.getOptionsRegionsList(lang, person.getAddress().getRegionId(), person.getAddress().getCountryId()) + "</select>");
                                out.println("<span class=\"exampleInfo cityExampleInfo\">" + res.getString(".cityExampleInfo") + "</span><br>");
                                out.println("<select class=\"placesSelect citySelect\" id=\"personCity\">" + HTMLUtils.getOptionsCitiesList(lang, person.getAddress().getCityId(), person.getAddress().getRegionId()) + "</select>"); 
                                out.println("</div>");
                                out.println("<span class=\"exampleInfo\" id=\"addressE\">" + res.getString("$addressE") + "</span><br>");
                                out.println("<input type=\"text\" id=\"address\" size=\"50\" value=\"" + person.getAddress().getStreet() + "\">");
                                out.println("<span class=\"exampleInfo\" id=\"zipCode\">" + res.getString("$zipCode") + "</span><br>");
                                out.println("<input type=\"text\" id=\"zipCode\" size=\"50\" value=\"" + person.getAddress().getPostalCode() + "\">");            
                                %>
                               </div>
                           </div>

                       </div>

                   </div>


<%
List<PhoneNumbers> phoneNumbers = person.getPhoneNumbers();
%>

<div class="row-fluid">
 <div class="span10">
   <div class="span2">
   <%
   out.println("<span id=\"phoneNumbersT\">" + res.getString("$phoneNumbersT") + "</span>:");
   %> 
   </div> 
   <div class="span8">
   <div id="phoneNumbersBlock">
      <%
      for(PhoneNumbers ph : phoneNumbers){
    	  out.println("<div id=\"phones" + ph.getId() + "\">");
          out.println("<select class=\"personPhonesType\">" + HTMLUtils.getOptionsListHTMLByType(res.getString(".personPhonesType").split(","), ph.getPhoneType()) + "</select>  ");
          out.println("<input type=\"text\" name=\"phoneNumbers\" value=\"" + ph.getPhoneNumber() + "\" class=\"input-large\" data-entity-id=\"" + ph.getId() + "\" data-state=\"upd\"><img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"disabledAll(phones" + ph.getId() + ")\"><br>");
          out.println("</div>");
      }
      %>
      </div>
   </div>
 </div>
</div>
                      <div class="row-fluid">
                      <div class="span10">
                      <div class="span2"></div>
                      <div class="span8">
                       <button class="btn btn-small btn-info addMore" type="button" onclick="addPhoneNumberStructure()">Add more</button>
                      </div>
                      </div>
                      </div>

               


<%
List<PersonLinks> personLinks = person.getPersonLinks();
%>

<div class="row-fluid">
 <div class="span10">
 <div class="span2">
    <%
   out.println("<span id=\"personLinksT\">" + res.getString("$personLinksT") + "</span>:<br>");
   %> 
 </div>
 <div class="span8">
 <div id="personLinksBlock">
 <%
 for(PersonLinks link : personLinks){
	 out.println("<div id=\"links" + link.getId() + "\">");
	 out.println("<span class=\"exampleInfo personLinksE\"></span>");
	 out.println("<select class=\"personLinksType\">" + HTMLUtils.getOptionsListHTMLByType(res.getString(".personLinksType").split(","), link.getLinkType()) + "</select>");
	 out.println("<input type=\"text\" name=\"personLinks\" value=\"" + link.getLinkName() + "\" class=\"input-large\" data-entity-id=\"" + link.getId() + "\" data-state=\"upd\"><img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"disabledAll(first_links_element)\"><br>");   
     out.println("</div>");
 }                        
 %>
 </div>                     
 </div>
 </div>
</div>                    
                         <!-- Add button -->

                      <div class="row-fluid">
                      <div class="span10">
                      <div class="span2"></div>
                      <div class="span8">
                       <button class="btn btn-small btn-info addMore" type="button" onclick="addPersonLinkStructure()">Add more</button>
                      </div>
                      </div>
                      </div>




                  <!-- Block of claim position -->
                  <div class="row-fluid">
                      <div class="span10">
                        <!-- Title -->
                        <div class="span2">
                        <%
                          out.println("<span id=\"claimPositionT\" class=\"br-fields\">" + res.getString("$claimPositionT") + "</span>:");
                        %>
                        </div>
                        <!-- Input -->
                        <div class="span8">
                        <%
                        out.println("<span class=\"exampleInfo\" id=\"$claimPositionE\">" + res.getString("$claimPositionE") + "</span><br>");
                        out.println("<input type=\"text\" id=\"claimPosition\" size=\"50\" value=\"" + person.getPersonalInfo().getClaimPosition() + "\" class=\"input-large\">");
                        %>
                        </div>
                      </div>
                  </div>

                  <!-- Block of profile -->
                  <div class="row-fluid">
                      <div class="span10">
                          <!-- Title -->
                         <div class="span2">
                          <%
                          out.println("<span id=\"profileT\">" + res.getString("$profileT") + "</span>:");
                          %>
                         </div>
                          <!-- Textarea -->
                          <div class="span8">
                           <%
                           out.println("<span class=\"exampleInfo\" id=\"$profileE\">" + res.getString("$profileE") + "</span><br>");
                           out.println("<textarea id=\"profile\" rows=\"10\" class=\"span10\">" + person.getPersonalInfo().getProfile() + "</textarea>");
                           %>
                          </div>
                      </div>
                  </div>


                  <!-- Block of hobbies -->
                  <div class="row-fluid">
                      <div class="span10">
                          <!-- Title -->
                          <div class="span2">
                           <%
                           out.println("<span id=\"hobbiesT\">" + res.getString("$hobbiesT") + "</span>:");
                           %>
                          </div>
                          <!-- Textarea -->
                          <div class="span8">
                           <%
                           out.println("<span class=\"exampleInfo\" id=\"hobbiesE\">" + res.getString("$hobbiesE") + "</span><br>");
                           out.println("<textarea id=\"hobbies\" rows=\"10\" class=\"span10\">" + person.getPersonalInfo().getHobbies() + "</textarea>");
                           %>
                          </div>
                      </div>
                  </div>


              </div>


            </div>



<%
List<Skills> skillsList = person.getSkills();
out.println("<h2><span id=\"skillsTitle\">" + res.getString("$skillsTitle") + "</span></h2>");
%>


<div class="row-fluid">
 <div class="span8">
   

   <% 
   for(Skills skills : skillsList){
	   out.println("<div id=\"skills" + skills.getId() + "\">");
	   out.println("<div class=\"span2\"></div>");
	   out.println("<div class=\"span4\">");
       out.println("<span class=\"exampleInfo\" id=\"skillsNameE\">" + res.getString("$skillsNameE") + "</span><br>");
       out.println("<input type=\"text\" name=\"skillsName\" size=\"10\" value=\"" + skills.getSkillsName() + "\" data-entity-id=\"" + skills.getId() + "\" data-state=\"upd\">");
	   out.println("</div>");
	   out.println("<div class=\"span4\">");	   
       out.println("<span class=\"exampleInfo\" id=\"skillsValueE\">" + res.getString("$skillsValueE") + "</span><br>");
       out.println("<input type=\"text\" class=\"input-large\" name=\"skillsValue\" size=\"10\" value=\"" + skills.getSkillsValue() + "\"><img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"disabledAll(first-skills-element)\"><br>");  
	   out.println("</div>");
	   out.println("</div>");
   }

   %>

 </div>
</div>

      <div id="skills_input"></div>

                <!-- Add button -->
                <div class="row-fluid">
                    <div class="span10">
                      <div class="span2"></div>
                      <div class="span8">
                      <button class="btn btn-small btn-info addMore" type="button" onclick="addSkillsStructure()">Add more</button>
                      </div>                     
                    </div>
                </div>

         


<%
List<Education> education = person.getEducation();
out.println("<h2><span id=\"educationTitle\">" + res.getString("$educationTitle") + "</span></h2>");
%>



<%
for(Education edu : education){
	    String eduId = edu.getId();
	
        	    
        out.println("<!-- Block of Education-->");
        out.println("<div id=\"edu" + eduId + "\">");
		out.println("<div class=\"row-fluid\">");
		out.println("<div class=\"span12\">");
	    out.println("<!-- First level info -->");
	    out.println("<div class=\"row-fluid\">");
	    out.println("<div class=\"span12\">");
	    out.println("<!-- Left info -->");
	    out.println("<div class=\"span5\">");
	    out.println("<!-- Titles -->");
	    out.println("<div class=\"span4\">");

	    out.println("<span class=\"eduTypeT br-fields\">" + res.getString(".eduTypeT") + "</span>:<br>");
	    out.println("<span class=\"eduTitleT br-fields\">" + res.getString(".eduTitleT") + "</span>:<br>");
	    out.println("<span class=\"eduYearsT br-fields\">" + res.getString(".allYearsT") + "</span>:<br>");
	    out.println("<span class=\"eduDegreeT br-fields\">" + res.getString(".eduDegreeT") + "</span>:<br>");
	    
	    out.println("</div>");
	    out.println("<!-- Inputs -->");
	    out.println("<div class=\"span3\">");


	    out.println("<span class=\"exampleInfo eduTypeE\"></span><br>");	    
	    out.println("<select class=\"eduType\" size=\"1\">" + HTMLUtils.getOptionsListHTMLByType(res.getString(".eduType").split(","), edu.getType()) + "</select><br>");
	    out.println("<span class=\"exampleInfo eduTitleE\">" + res.getString(".eduTitleE") + "</span><br>");
        out.println("<input type=\"text\" name=\"eduTitle\" size=\"30\" value=\""+ edu.getTitle() +"\" data-entity-id=\"" + edu.getId() + "\" data-state=\"upd\"><br>");
	    out.println("<span class=\"exampleInfo allYearsE\">" + res.getString(".allYears") + "</span><br>");
	    out.println("<input type=\"text\" name=\"eduYears\" size=\"15\" value=\"" + edu.getYears() + "\"><br>");    
        out.println("<span class=\"exampleInfo eduDegreeE\"></span><br>");
        out.println("<input type=\"text\" name=\"eduDegree\" size=\"30\" value=\"" + edu.getDegree() + "\"><br>");
	    
	    out.println("</div>");
	    out.println("</div>");
	    out.println("<!-- Right info-->");
	    out.println("<div class=\"span6\">");
	    out.println("<!-- Titles -->");
	    out.println("<div class=\"span3\">");

	    out.println("<span class=\"CountryT br-fields\">" + res.getString(".CountryT") + "</span>:<br>");
	    out.println("<span class=\"RegionT br-fields\">" + res.getString(".RegionT") + "</span>:<br>");
	    out.println("<span class=\"CityT br-fields\">" + res.getString(".CityT") + "</span>:<br>");
	    
	    out.println("</div>");
	    out.println("<!-- Inputs -->");
	    out.println("<div class=\"span3\">");

	    out.println("<div class=\"selectBlock\" id=\"eduSelectBlock" + eduId + "\">");
	    out.println("<span class=\"exampleInfo countryExampleInfo\">" + res.getString(".countryExampleInfo") + "</span><br>");
	    out.println("<select size=\"1\" name=\"educationCountryName\" class=\"educationCo selectCountryClass countrySelect\" id=\"eduCountry" + eduId + "\" onchange=\"change('eduCountry'" + eduId + "', 'eduRegion" + eduId + "', 'region_c')\" >" + HTMLUtils.getOptionsCountriesList(lang, edu.getAddress().getCountryId()) + "</select><br>");
	    out.println("<span class=\"exampleInfo regionExampleInfo\">" + res.getString(".regionExampleInfo") + "</span><br>");
	    out.println("<select size=\"1\" name=\"educationRegionName\" class=\"placesSelect regionSelect\" id=\"eduRegion" + eduId + "\" onchange=\"change('eduRegion" + eduId + "', 'eduCity" + eduId + "', 'city_c')\">" + HTMLUtils.getOptionsRegionsList(lang, edu.getAddress().getRegionId(), edu.getAddress().getCountryId()) + "</select><br>");
	    out.println("<span class=\"exampleInfo cityExampleInfo\">" + res.getString(".cityExampleInfo") + "</span><br>");
	    out.println("<select size=\"1\" name=\"educationCityName\" class=\"placesSelect citySelect\" id=\"eduCity" + eduId + "\">" + HTMLUtils.getOptionsCitiesList(lang, edu.getAddress().getCityId(), edu.getAddress().getRegionId()) + "</select><br>");
	    out.println("</div>");
	    
	    out.println("</div>");
	    out.println("</div>");
	    out.println("</div>");
	    out.println("</div>");
	    out.println("<!-- Description info -->");
	    out.println("<div class=\"row-fluid\">");
	    out.println("<div class=\"span10\">");
	    out.println("<!-- Title -->");
	    out.println("<div class=\"span2\">");

	    out.println("<span class=\"allDescriptionT\">" + res.getString(".allDescriptionT") + "</span>:<br>");
	    
	    out.println("</div>");
	    out.println("<!-- Textarea -->");
	    out.println("<div class=\"span8\">");

	    out.println("<span class=\"exampleInfo eduDescriptionE\">" + res.getString(".eduDescriptionE") + "</span><br>");
	    out.println("<textarea class=\"span8\" name=\"eduDescription\" rows=\"10\">" + edu.getDescription() + "</textarea>");

	    out.println("</div>");
	    out.println("</div>");
	    out.println("</div>");
	    out.println("</div>");
	    out.println("<img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"disabledAll(edu" + edu.getId() + ")\">");
	    out.println("</div>");
	    out.println("</div>");
	    
	    
}



%>
       <div class="row-fluid">
       <div class="span12">
       <div id="educationBlock"></div>
       </div>
       </div>
       
    
    	<!-- Add button -->
	    <div class="row-fluid">
	    <div class="span12">
	     <button class="btn btn-small btn-info addMore" type="button" id="eduAddButton" onclick="addEduStructure()">Add more</button>
	    </div>
	    </div>	    

<%
List<EmploymentHistory> employment = person.getEmploymentHistory();
out.println("<h2><span id=\"employmentTitle\">" + res.getString("$employmentTitle") + "</span></h2>");
%>


<%
	for(EmploymentHistory emp : employment){
String empId = emp.getId();

out.println("<!-- Block of Employment -->");
out.println("<div id=\"emp" + empId + "\">");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- First level info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Left info -->");
out.println("<div class=\"span5\">");
out.println("<!-- Titles -->");
out.println("<div class=\"span4\">");

out.println("<span class=\"empTitleT br-fields\">" + res.getString(".empTitleT") + "</span>:<br>");
out.println("<span class=\"allYearsT br-fields\">" + res.getString(".allYearsT") + "</span>:<br>");
out.println("<span class=\"empPositionT br-fields\">" + res.getString(".empPositionT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Inputs -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"exampleInfo empTitleE\">" + res.getString(".empTitleE") + "</span><br>");
out.println("<input type=\"text\" name=\"empTitle\" size=\"30\" value=\"" + emp.getTitle() + "\" data-companylink=\"compLink" + empId + "\" data-entity-id=\"" + emp.getId() + "\" data-state=\"upd\"><br>");
out.println("<span class=\"exampleInfo allYearsE\">" + res.getString(".allYears") + "</span><br>");
out.println("<input type=\"text\" name=\"empYears\" size=\"25\" value=\"" + emp.getYears() + "\"><br>");
out.println("<span class=\"exampleInfo empPositionE\">" + res.getString(".empPositionE") + "</span><br>");
out.println("<input type=\"text\" name=\"empPosition\" size=\"25\" value=\"" + emp.getPosition() + "\"><br>");


out.println("</div>");
out.println("</div>");
out.println("<!-- Right info -->");
out.println("<div class=\"span6\">");
out.println("<!-- Titles -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"CountryT br-fields\">" + res.getString(".CountryT") + "</span>:<br>");
out.println("<span class=\"RegionT br-fields\">" + res.getString(".RegionT") + "</span>:<br>");
out.println("<span class=\"CityT br-fields\">" + res.getString(".CityT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Inputs -->");
out.println("<div class=\"span3\">");

out.println("<div class=\"selectBlock\" id=\"empSelectBlock" + empId + "\">");
out.println("<span class=\"exampleInfo countryExampleInfo\">" + res.getString(".countryExampleInfo") + "</span><br>");
out.println("<select size=\"1\" name=\"employmentCountryName\" class=\"employmentCo selectCountryClass countrySelect\" id=\"empCountry" + empId + "\" onchange=\"change('empCountry" + empId + "', 'empRegion" + empId + "', 'region_c')\">" + HTMLUtils.getOptionsCountriesList(lang, emp.getAddress().getCountryId()) + "</select><br>");
out.println("<span class=\"exampleInfo regionExampleInfo\">" + res.getString(".regionExampleInfo") + "</span><br>");
out.println("<select size=\"1\" name=\"employmentRegionName\" class=\"placesSelect regionSelect\" id=\"empRegion" + empId + "\" onchange=\"change('empRegion" + empId + "', 'empCity" + empId + "', \'city_c\')\">" + HTMLUtils.getOptionsRegionsList(lang, emp.getAddress().getRegionId(), emp.getAddress().getCountryId()) + "</select><br>");
out.println("<span class=\"exampleInfo cityExampleInfo\">" + res.getString(".cityExampleInfo") + "</span><br>");
out.println("<select size=\"1\" id=\"empCity" + empId + "\" class=\"placesSelect citySelect\" name=\"employmentCityName\">" + HTMLUtils.getOptionsCitiesList(lang, emp.getAddress().getCityId(), emp.getAddress().getRegionId()) + "</select><br>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");


List<Project> project = emp.getProjects();

out.println("<h3><span class=\"projectTitle\">" + res.getString(".projectTitle") + "</span></h3>");
out.println("<div id=\"addition-projects" + empId + "\" class=\"addProjects\">");
for(Project proj : project){

out.println("<!-- Block of Project to Employment History Block (if in this EH we have more then zero projects :) )-->");
out.println("<div id=\"proj" + proj.getId() + "\">");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span10\">");
out.println("<!-- First level Project info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Titles -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"projTitleT br-fields\">" + res.getString(".projTitleT") + "</span>:<br>");
out.println("<span class=\"allYearsT br-fields\">" + res.getString(".allYearsT") + "</span>:<br>");
out.println("<span class=\"projPositionT br-fields\">" + res.getString(".projPositionT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Inputs -->");
out.println("<div class=\"span7\">");

out.println("<span class=\"exampleInfo projTitleE\">" + res.getString(".projTitleT") + "</span><br>");
out.println("<input type=\"text\" name=\"projTitle\" size=\"30\" value=\"" + proj.getTitle() + "\" placeholder=\"Web Mail\" data-companylink=\"compLink" + empId + "\" data-entity-id=\"" + proj.getId() + "\" data-state=\"upd\"><br>");
out.println("<span class=\"exampleInfo allYearsE\">" + res.getString(".allYears") + "</span><br>");
out.println("<input type=\"text\" name=\"projYears\" size=\"30\" value=\"" + proj.getYears() + "\" placeholder=\"2009 - 2011\"><br>");
out.println("<span class=\"exampleInfo projPositionE\">" + res.getString(".projPositionE") + "</span><br>");
out.println("<input type=\"text\" name=\"projPosition\" size=\"30\" value=\"" + proj.getPosition() + "\" placeholder=\"Team Lead\"><br>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<!-- Description Project info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Title-->");
out.println("<div class=\"span3\">");

out.println("<span class=\"allDescriptionT\">" + res.getString(".allDescriptionT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Text area -->");
out.println("<div class=\"span8\">");

out.println("<span class=\"exampleInfo projDescriptionE\">" + res.getString(".projDescriptionE") + "</span><br>");
out.println("<textarea name=\"projDescription\" class=\"span8\" rows=\"10\" placeholder=\"About the project and my role in it\">" + proj.getDescription() + "</textarea><br>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"remove_partial_projects_upd(proj" + proj.getId() + ")\">");
out.println("</div>");
out.println("</div>");

}
out.println("</div>");
out.println("<div id=\"new_projects_input" + empId + "\">");
out.println("</div>");


out.println("<!-- Add button -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<button class=\"btn btn-small btn-info addProjectButton\" type=\"button\" onclick=\"addProjectsStructure(\'new_projects_input" + empId + "\', \'" + empId + "\')\">Add more</button>");
out.println("</div>");
out.println("</div>");


out.println("<!-- Description info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span10\">");
out.println("<!-- Title -->");
out.println("<div class=\"span2\">");

out.println("<span class=\"allDescriptionT\">" + res.getString(".allDescriptionT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Textarea -->");
out.println("<div class=\"span8\">");

out.println("<span class=\"exampleInfo empDescriptionE\">" + res.getString(".empDescriptionE") + "</span><br>");
out.println("<textarea class=\"span8\" name=\"empDescription\" rows=\"10\">" + emp.getDescription() + "</textarea>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<img src=\"http://ser.optivisions.ru/styleimg/iconforcancel.jpg\" class=\"remButton\" onclick=\"disabledAll(emp" + emp.getId() + ")\">");
out.println("</div>");
out.println("</div>");

}
%>

<div id="employmentBlock"></div>

<div class="row-fluid">
<div class="span12">
<button class="btn btn-small btn-info addMore" type="button" id="empAddButton" onclick="addEmpStructure()">Add more</button>
</div>
</div>


<%
List<Certificate> certificate = person.getCertificate();

out.println("<h2 id=\"certificateTitle\">" + res.getString("$certificateTitle") + "</h2>");

for(Certificate cert : certificate){
out.println("<!-- Certificate info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Size info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Title -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"certSizeT\">" + res.getString(".certSizeT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Select -->");
out.println("<div class=\"span4\">");

out.println("<select class=\"certificateImagesType\">" + HTMLUtils.getOptionsListHTMLByType(res.getString(".certificateImagesType").split(","), cert.getImageSize()) + "</select>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<!-- Image info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");

out.println("<img src=\"http://ser.optivisions.ru" + cert.getImages().getUrl().getPath() + "\" class=\"pathToImages img-polaroid\" name=\"pathToImage\">");

out.println("</div>");
out.println("</div>");
out.println("<!-- Short description info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Title -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"certShortDescT\">" + res.getString(".certShortDescT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Textarea -->");
out.println("<div class=\"span8\">");

out.println("<span class=\"exampleInfo certShortDescE\">" + res.getString(".certShortDescE") + "</span>:<br>");
out.println("<input type=\"text\" size=\"70\" name=\"certSmallDesc\" placeholder=\"Not\" value=\"" + cert.getSmallDescription() + "\" data-entity-id=\"" + cert.getId() + "\" data-state=\"upd\"><br>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<!-- Full description info -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");
out.println("<!-- Title -->");
out.println("<div class=\"span3\">");

out.println("<span class=\"certFullDescT\">" + res.getString(".certFullDescT") + "</span>:<br>");

out.println("</div>");
out.println("<!-- Textarea -->");
out.println("<div class=\"span8\">");

out.println("<span class=\"exampleInfo certFullDescE\">" + res.getString(".certFullDescE") + "</span><br>");
out.println("<textarea class=\"span8\" name=\"certFullDesc\" rows=\"10\" placeholder=\"" + cert.getImages().getUrl().getPath() + "\">" + cert.getFullDescription() + "</textarea>");

out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("</div>");
out.println("<!-- Add button -->");
out.println("<div class=\"row-fluid\">");
out.println("<div class=\"span12\">");

out.println("</div>");
out.println("</div>");
out.println("</div>");
}
        %>


</div>







        <!-- Third main block -->
        <div class="span2"></div>
    </div>
<!-- Update Button -->
<div class="row-fluid">
<div class="span4"></div>
    <div class="span2">
       <button class="btn btn-large btn-block btn-success" id="updateInfo" type="button" onclick="generateUpdateResume()">Update Info</button>
    </div>
</div>
<!-- Preview link -->
    <div class="row-fluid">
        <div class="span12">

        </div>
    </div>
</div>
</div>

<div id="bottom-of-page"></div>



</body>
</html>