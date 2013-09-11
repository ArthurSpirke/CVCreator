/*
#
#
# File name - theOne.js
# Create Date - 2013/09/11 12:38:16
#
# Created by JSPack (https://github.com/ArthurSpirke/JSPack)
#
#
*/



//----------- add_blocks.js -----------



function addPhoneNumberStructure(){
    document.getElementById('phoneNumbersBlock').appendChild(getPhoneNumberStructure());
    startResumeFormPage();
}

function getPhoneNumberStructure(){
    var newStructure = document.createElement('div');
    var blockId = parseInt(getRandomInteger());
    newStructure.id = 'phones' + blockId;
    newStructure.innerHTML = '<select class="personPhonesType" ></select> '
        + '<input type="text" name="phoneNumbers" value="" class="input-large" data-entity-id="0" data-state="new">'
        + '<button onclick="removeSimpleBlock(\'phones' + blockId + '\')">Delete</button>';
    return newStructure;
}

function addPersonLinkStructure(){
   document.getElementById('personLinksBlock').appendChild(getPersonLinkStructure());
    startResumeFormPage();
}

function getPersonLinkStructure(){
    var newStructure = document.createElement('div');
    var blockId = parseInt(getRandomInteger());
    newStructure.id = 'links' + blockId;
    newStructure.innerHTML = '<select class="personLinksType"></select> '
        + '<input type="text" name="personLinks" value="" class="input-large" data-entity-id="0" data-state="new">'
        + '<button onclick="removeSimpleBlock(\'links' + blockId + '\')">Delete</button>';
    return newStructure;
}

function addSkillsStructure(){
   document.getElementById('skills_input').appendChild(getSkillsStructure());
}

function getSkillsStructure(){
    var newStructure = document.createElement('div');
    var blockId = parseInt(getRandomInteger());
    newStructure.id = 'skills' + blockId;
    newStructure.innerHTML = '<div class="row-fluid">'
        + '<div class="span8">'
        + '<div class="span2"></div>'
        + '<div class="span4">'
        + '<span class="exampleInfo" id="skillsNameE"></span><br>'
        + '<input type="text" name="skillsName" size="10" value="" data-entity-id="0" data-state="new">'
        + '</div>'
        + '<div class="span6">'
        + '<span class="exampleInfo" id="skillsValueE"></span><br>'
        + '<input type="text" name="skillsValue" size="25" value="" class="input-large">'
        + '<button onclick="removeSimpleBlock(\'skills' + blockId + '\')">Delete</button>'
        + '</div>'
        + '</div>'
        + '</div>';
    return newStructure;
}


function addSkillsBlock(){
   $('#skillsBlock').slideDown('slow', function(){
       //add visible to skills block and add education block
       var educationBlock = document.createElement('div');
       educationBlock.id = 'educationBlock';
       educationBlock.style['display'] = 'none';
       educationBlock.innerHTML = '<h2><span id="educationTitle"></span></h2>';


       educationBlock.appendChild(getEducationBlockStructure());


       var addButton = newAddStructureButton('eduAddButton');
       addButton.onclick = function(){addEduStructure()};
       educationBlock.appendChild(addButton);
       document.getElementById('all').appendChild(educationBlock);

       //add new button;


       var mainButton = document.getElementById('stepBlock');
       mainButton.removeChild(document.getElementById('addSkillsBlock'));
       var newButton = newNextButton('addEducationBlock');
       newButton.onclick = function(){addEducationBlock();};
       mainButton.appendChild(newButton);


       startResumeFormPage();
   });
}


function addEduStructure(){
    var addButton = document.getElementById('eduAddButton');

    var educationBlock = document.getElementById('educationBlock');
    var newStructure = getEducationBlockStructure();
    educationBlock.appendChild(newStructure);
    addButton.parentNode.removeChild(addButton);
    educationBlock.appendChild(addButton);


    var deleteButton = newDeleteStructureButton();
    deleteButton.onclick = function(){removeBlock(newStructure, deleteButton)};
    educationBlock.appendChild(deleteButton);

    startResumeFormPage();
}

function getEducationBlockStructure(){
    var eduStructure = document.createElement('div');
    var blockId = parseInt(getRandomInteger());
    eduStructure.id = 'edu' + blockId;
    eduStructure.innerHTML = '<div class="row-fluid">'
        + '<div class="span12">'
        + '<div id="edu' + blockId + '">'
        + '<div class="row-fluid">'
        + '<div class="span4">'
        + '<div class="span4">'
        + '<span class="eduTypeT br-fields"></span>:<br>'
        + '<span class="eduTitleT br-fields"></span>:<br>'
        + '<span class="allYearsT br-fields"></span>:<br>'
        + '<span class="eduDegreeT br-fields"></span>:<br>'
        + '</div>'
        + '<div class="span5">'
        + '<span class="exampleInfo eduTypeE"></span><br>'
        + '<select class="eduType" size="1"></select><br>'
        + '<span class="exampleInfo eduTitleE"></span><br>'
        + '<input type="text" name="eduTitle" size="30" value="" data-entity-id="0" data-state="new"><br>'
        + '<span class="exampleInfo allYears"></span><br>'
        + '<input type="text" name="eduYears" size="15" value=""><br>'
        + '<span class="exampleInfo eduDegreeE"></span><br>'
        + '<input type="text" name="eduDegree" size="30" value=""><br>'
        + '</div>'
        + '</div>'
        + '<div class="span4">'
        + '<div class="span4">'
        + '<span class="CountryT br-fields"></span>:<br>'
        + '<span class="RegionT br-fields"></span>:<br>'
        + '<span class="CityT br-fields"></span>:<br>'
        + '</div>'
        + '<div class="selectBlock" id="eduSelectBlock' + blockId + '">'
        + '<div class="span5">'
        + '<span class="exampleInfo countryExampleInfo"></span><br>'
        + '<select size="1" name="educationCountryName" class="educationCo selectCountryClass countrySelect" id="eduCountry' + blockId + '" onchange="change(\'eduCountry' + blockId + '\', \'eduRegion' + blockId + '\', \'region_c\')" >'
        + '</select><br>'
        + '<span class="exampleInfo regionExampleInfo"></span><br>'
        + '<select size="1" class="placesSelect regionSelect" name="educationRegionName" id="eduRegion' + blockId + '" onchange="change(\'eduRegion' + blockId + '\', \'eduCity' + blockId + '\', \'city_c\')">'
        + '</select><br>'
        + '<span class="exampleInfo cityExampleInfo"></span><br>'
        + '<select size="1" class="placesSelect citySelect" name="educationCityName" id="eduCity' + blockId + '">'
        + '</select><br>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="row-fluid">'
        + '<div class="span8">'
        + '<div class="span2">'
        + '<span class="allDescriptionT"></span>:<br>'
        + '</div>'
        + '<div class="span7">'
        + '<span class="exampleInfo eduDescriptionE"></span><br>'
        + '<textarea class="span11" name="eduDescription" rows="10"></textarea><br>'
        + '<span class="futureRemoveButton"></span>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div id="education_input">'
        + '</div>';


    return eduStructure;
}


function addEducationBlock(){
    $('#educationBlock').slideDown('slow', function(){
        //add visible to education block and add employment history block

        var employmentBlock = document.createElement('div');
        employmentBlock.id = 'employmentBlock';
        employmentBlock.style['display'] = 'none';

        employmentBlock.innerHTML = '<h2><span id="employmentTitle"></span></h2>';

        employmentBlock.appendChild(getEmploymentBlockStructure());


        var addButton = newAddStructureButton('empAddButton');
        addButton.onclick = function(){addEmpStructure()};
        employmentBlock.appendChild(addButton);
        document.getElementById('all').appendChild(employmentBlock);


        var mainButton = document.getElementById('stepBlock');
        mainButton.removeChild(document.getElementById('addEducationBlock'));
        var newButton = newNextButton('addEmploymentBlock');
        newButton.onclick = function(){addEmploymentBlock();};
        mainButton.appendChild(newButton);

        startResumeFormPage();
    });
}

function addEmpStructure(){
    var addButton = document.getElementById('empAddButton');

    var employmentBlock = document.getElementById('employmentBlock');
    var newStructure = getEmploymentBlockStructure();
    employmentBlock.appendChild(newStructure);
    addButton.parentNode.removeChild(addButton);
    employmentBlock.appendChild(addButton);

    var deleteButton = newDeleteStructureButton();
    deleteButton.onclick = function(){removeBlock(newStructure, deleteButton)};
    employmentBlock.appendChild(deleteButton);

    startResumeFormPage();
}

function getEmploymentBlockStructure(){
   var empStructure = document.createElement('div');
   var blockId = parseInt(getRandomInteger());
   empStructure.id = 'emp' + blockId;
   empStructure.innerHTML = '<div class="row-fluid"><div class="span12">'
       + '<div id="emp' + blockId + '">'
       + '<div class="row-fluid">'
       + '<div class="span4">'
       + '<div class="span4">'
       + '<span class="empTitleT br-fields"></span>:<br>'
       + '<span class="allYearsT br-fields"></span>:<br>'
       + '<span class="empPositionT br-fields"></span>:<br>'
       + '</div>'
       + '<div class="span5">'
       + '<span class="exampleInfo empTitleE"></span><br>'
       + '<input type="text" name="empTitle" size="30" value="" data-block-id="' + blockId + '" data-entity-id="0" data-state="new"><br>'
       + '<span class="exampleInfo allYears"></span><br>'
       + '<input type="text" name="empYears" size="25" value=""><br>'
       + '<span class="exampleInfo empPositionE"></span><br>'
       + '<input type="text" name="empPosition" size="25" value=""><br>'
       + '</div></div>'
       + '<div class="span4">'
       + '<div class="span4">'
       + '<span class="CountryT br-fields"></span>:<br>'
       + '<span class="RegionT br-fields"></span>:<br>'
       + '<span class="CityT br-fields"></span>:<br>'
       + '</div>'
       + '<div class="selectBlock" id="empSelectBlock' + blockId + '">'
       + '<div class="span5">'
       + '<span class="exampleInfo countryExampleInfo">Example</span><br>'
       + '<select size="1" name="employmentCountryName" class="employmentCo selectCountryClass countrySelect" id="empCountry' + blockId + '" onchange="change(\'empCountry' + blockId + '\', \'empRegion' + blockId + '\', \'region_c\')">'
       + '</select><br>'
       + '<span class="exampleInfo regionExampleInfo">Example</span><br>'
       + '<select size="1" class="placesSelect regionSelect" name="employmentRegionName" id="empRegion' + blockId + '" onchange="change(\'empRegion' + blockId + '\', \'empCity' + blockId + '\', \'city_c\')">'
       + '</select><br>'
       + '<span class="exampleInfo cityExampleInfo">Example</span><br>'
       + '<select size="1" class="placesSelect citySelect" id="empCity' + blockId + '" name="employmentCityName">'
       + '</select><br>'
       + '</div>'
       + '</div></div></div>'
       + '<div class="row-fluid">'
       + '<div id="projectBlock' + blockId + '" class="projectBlock">'
       + '</div>'
       + '</div>'
       + '<button class="btn btn-small btn-info addProjectButton" type="button" onclick="addProjectsStructure(\'projectBlock' + blockId + '\', \'' + blockId + '\')">Add project</button>'
       + '<div class="row-fluid"><div class="span8">'
       + '<div class="span2">'
       + '<span class="allDescriptionT"></span>:<br>'
       + '</div>'
       + '<div class="span8">'
       + '<span class="exampleInfo empDescriptionE"></span><br>'
       + '<textarea class="span10" name="empDescription" rows="10"></textarea><br>'
       + '<span class="futureRemoveButton"></span>'
       + '</div>'
       + '</div>'
       + '</div>'
       + '</div>'
       + '</div>'
       + '</div>'
       + '<div id="employment_input">'
       + '</div>';

    return empStructure;
}

function addEmploymentBlock(){
    $('#employmentBlock').slideDown('slow', function(){
      //add visible to employment and add certificate block

        var certificateBlock = document.createElement('div');
        certificateBlock.id = 'certificateBlock';
        certificateBlock.style['display'] = 'none';

        certificateBlock.innerHTML = '<h2><span id="certificateTitle"></span></h2>'
            + '<div id="bord">'
            + '<p></p>'
            + '</div>'
            + '<div id="bad">'
            + '<p></p>'
            + '</div>'
            + '<form id="fileUploader">'
            + '<input type="file" name="newFile"id="input-file" required multiple onchange="uploadFile()" />'
            + '</form>';

        document.getElementById('all').appendChild(certificateBlock);

        var mainButton = document.getElementById('stepBlock');
        mainButton.removeChild(document.getElementById('addEmploymentBlock'));
        var newButton = document.createElement('button');
        newButton.id = 'addCertificateBlock';
        newButton.className = 'btn btn-large btn-primary';
        newButton.onclick = function(){addCertificateBlock();};
        mainButton.appendChild(newButton);

        startResumeFormPage();
    });
}


function addProjectsStructure(nameOfParentElem, companyId){
    var projectBlock = document.getElementById(nameOfParentElem);
    var newStructure = getProjectBlockStructure(companyId);
    projectBlock.appendChild(newStructure);

    var deleteButton = newDeleteStructureButton();
    deleteButton.onclick = function(){removeBlock(newStructure, deleteButton)};
    projectBlock.appendChild(deleteButton);

    startResumeFormPage();
}

function getProjectBlockStructure(companyId){
    var rand = getRandomInteger();
    var projectStructure = document.createElement('div');
    projectStructure.dataset.blockId = parseInt(rand);
    projectStructure.id = 'project' + parseInt(rand);
    projectStructure.style['backgroundColor'] = '#bada55';
    projectStructure.innerHTML = '<div class="span10">'
        + '<div class="row-fluid">'
        + '<div class="span12">'
        + '<div class="span2">'
        + '<span class="projTitleT br-fields"></span>:<br>'
        + '<span class="allYearsT br-fields"></span>:<br>'
        + '<span class="projPositionT br-fields"></span>:<br>'
        + '</div>'
        + '<div class="span5">'
        + '<span class="exampleInfo projTitleE"></span><br>'
        + '<input type="text" id="projTitle' + parseInt(rand) + '" name="projTitle" size="30" value="" data-company-id="0" data-companylink="compLink' + companyId + '" data-entity-id="0" data-state="new"><br>'
        + '<span class="exampleInfo allYearsE"></span><br>'
        + '<input type="text" id="projYears' + parseInt(rand) + '" name="projYears" size="30" value=""><span class="checkInfo"></span><br>'
        + '<span class="exampleInfo projPositionE"></span><br>'
        + '<input type="text" id="projPosition' + parseInt(rand) + '" name="projPosition" size="30" value=""><span class="checkInfo"></span><br>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="row-fluid">'
        + '<div class="span12">'
        + '<div class="span2">'
        + '<span class="allDescriptionT br-fields"></span>:<br>'
        + '</div>'
        + '<div class="span8">'
        + '<span class="exampleInfo projDescriptionE"></span><br>'
        + '<textarea id="projDescription' + parseInt(rand) + '" name="projDescription" class="span8" rows="10"></textarea><span class="checkInfo"></span><br>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>';
         return projectStructure;


    /*
    jQuery("#" + new_input.id).animate({
        backgroundColor: "#FFFFFF"
    }, 1500 );
    */
}


function addCertificateBlock(){
    $('#certificateBlock').slideDown('slow');

    var mainButton = document.getElementById('stepBlock');
    mainButton.removeChild(document.getElementById('addCertificateBlock'));
    mainButton.parentNode.className = 'span4 offset2';
    var newButton = document.createElement('button');
    newButton.id = "generateResume";
    newButton.className = 'btn btn-large btn-block btn-success';
    newButton.onclick = function(){generateResume()};
    mainButton.appendChild(newButton);

    startResumeFormPage();
}



//----------- ajax_f.js -----------



function changeStaticInfoFromServer(lang, type){
    var url = "http://cvcreator-service.com:8080/CVCreator/FieldsTitlesLangController?lang=" + lang + "&type=" + type;
    var XHR = window.XMLHttpRequest || window.XDomainRequest;
    var xhr = new XHR();

    xhr.open("GET", url, true);
    xhr.onload = function(){

        var json = JSON.parse(xhr.responseText);

        var staticFieldsNames = json.staticFieldsName;
        var staticFieldsInfo = json.staticFieldsInfo;

        var staticFieldsArrayNames = json.staticFieldsArrayName;
        var staticFieldsArrayInfo = json.staticFieldsArrayInfo;


        for(var i = 0; i < staticFieldsNames.length; ++i){
            changeLocaleStaticSpans(staticFieldsNames[i], staticFieldsInfo[staticFieldsNames[i]]);
        }


        for(var j = 0; j < staticFieldsArrayNames.length; ++j){
            changeLocalStaticSelects(createOptionsList(staticFieldsArrayInfo[staticFieldsArrayNames[j]]), staticFieldsArrayNames[j]);
        }


        var staticCountryList = json[".selectCountryClass"];
        changeLocalStaticSelects(createOptionListWithId(staticCountryList, json["placeOptionFirstElement"]), '.selectCountryClass');
        /*
        if(Modernizr.localstorage){
            addStaticInfoToWebStorage(staticFieldsNames, staticFieldsInfo, lang);
            addStaticArrayInfoToWebStorage(staticFieldsArrayNames, staticFieldsArrayInfo, lang);
            addStaticArrayInfoToWebStorage(".selectCountryClass", staticCountryList, lang);
        }
        */


    };
    xhr.error = function(){

    };

    xhr.send();

}


//generate resume with final data in fields.
function generateResume(){
    /*
    if(checkDataExistingInWebStorageByLang(getPrefLang())){
        validateAll(localStorage.getItem('.errorEmptyField'), localStorage.getItem('.errorNotValidEMail'), localStorage.getItem('.errorNotValidInteger'));
    } else {
        getErrorsInfoFromServer();
    }
   */

	if(true)
    {
	var json = createDataToServer("GENERATE");
    var url = "http://cvcreator-service.com:8080/CVCreator/GenerateResumeController";
    var XHR = window.XMLHttpRequest || window.XDomainRequest;
    var xhr = new XHR();
   
    xhr.open("POST", url, true);

    xhr.onload = function(){
        var resp = JSON.parse(xhr.responseText);
    	var a = '<div class="row-fluid">'
            + '<div class="span12">'
            + '<div class="span4"></div>'
            + '<div class="span6">'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<div class="span4">'
            + '<a href=\"http://' + resp.pdfLink + '\" target=\"_blank\"><img src="http://ser.optivisions.ru/styleimg/pdf_pic.jpg"></a>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<div class="span4">'
            + '<a href="#" class="blackLink"><span class="blackLink">PDF LINK</span></a>'
            + '</div>'
            + '<div class="span4">'
            + '<a href="#" class="blackLink"><span class="blackLink">HTML LINK</span></a>'
            + '</div>'
            + '<div class="span4">'
            + '<a href="#" class="blackLink"><span class="blackLink">DOC LINK</span></a> '
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<!-- try add CENTER attribute in CSS to this text\block -->'
            + 'LINK TO DOWNLOAD ALL RESUMES IN ZIP'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<!-- try add CENTER attribute in CSS to this text\block -->'
            + 'You user page for update - <a href=\"example.com/UserPage?id=' + resp.personId + '\"><span class="blackLink">example.com/UserPage?id=' + resp.personId + '</span></a>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<!-- try add CENTER attribute in CSS to this text\block -->'
            + 'TEXT'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="span2"></div>'
            + '</div>'
            + '</div>';
        document.getElementById("main-page").innerHTML = a;
    };

    xhr.error = function(){
        alert("Error");
    };

    xhr.send(json);
    document.getElementById("main-page").innerHTML = '<center><img src="http://ser.optivisions.ru/styleimg/loading.gif"></center>';
    
    } else {
		 alert('Input you data!')
    }
    

}



//Preview in modal window PDF CV with intermediate data.
function generatePreview(){
	var json = createDataToServer("PREVIEW");

    var url = "http://cvcreator-service.com:8080/CVCreator/GenerateResumeController";
    var XHR = window.XMLHttpRequest || window.XDomainRequest;
    var xhr = new XHR();

   
    xhr.open("POST", url, true);

    xhr.onload = function(){

    	var resp = JSON.parse(xhr.responseText);

    	var mainD = document.getElementById('dialog');
    	var newDiv = document.createElement('div');
        newDiv.id = 'previewContainer';

    	var newIframe = document.createElement('iframe');

    	newIframe.src = 'http://' + resp.PathToPreview;
    	newIframe.height = 800;
    	newIframe.width = 1000;
    	mainD.appendChild(newDiv);
    	newDiv.appendChild(newIframe);

    	$("#dialog").dialog({ height: 900, width: 1050, modal: true, resizable: false, closeOnEscape: true, close : function() {$('#previewContainer').remove();}});

    };

    xhr.error = function(){
        alert("Error");
    };

    xhr.send(json);

}

//update resume with final data in fields.
function generateUpdateResume() {
	alert('Start');
	// checkFields()
	// var res = checkBadResults();
	// alert(res);
	if (true) {

		var json = createDataToServer("UPDATE");
		var url = "http://cvcreator-service.com:8080/CVCreator/GenerateResumeController";
		var XHR = window.XMLHttpRequest || window.XDomainRequest;
		var xhr = new XHR();

		xhr.open("POST", url, true);

		xhr.onload = function() {

			var resp = JSON.parse(xhr.responseText);

			var a = '<a href=\"http://'
					+ resp.pdfLink
					+ '\" target=\"_blank\">PDF Resume</a><br><a href=\"http://'
					+ '\" target=\"_blank\">HTML Resume</a><br><a href=\"example.com/UserPage?id='
					+ resp.personId
					+ '\">example.com/UserPage?id='
					+ resp.personId + '</a>';
			document.getElementById("main-page").innerHTML = a;
		};

		xhr.error = function() {
			alert("Error");
		};

		xhr.send(json);
	} else {

	}

}



function loadPlacesByNewLang(json){
    var url = "http://cvcreator-service.com:8080/CVCreator/OptionsLangChangesController";
    var XHR = window.XMLHttpRequest || window.XDomainRequest;
    var xhr = new XHR();

    xhr.open("POST", url, true);
    xhr.onload = function(){
        var array = JSON.parse(xhr.responseText);

        for(var i = 0; i < array.length; ++i){
            var object = array[i];

            var selectBlock = document.getElementById(object["selectBlockId"]);

            selectBlock.getElementsByClassName('countrySelect')[0].innerHTML = getOptionsList(object["countryList"], object["countryId"]);
            selectBlock.getElementsByClassName('regionSelect')[0].innerHTML = getOptionsList(object["regionList"], object["regionId"]);
            selectBlock.getElementsByClassName('citySelect')[0].innerHTML = getOptionsList(object["cityList"], object["cityId"]);

        }
    };
    xhr.error = function(){

    };
    xhr.send(json);
}




function loadRegionOrCity(selectCountryId, changeSelect, typeOfChangingElement){
    var url = "http://cvcreator-service.com:8080/CVCreator/PlacesController?type=" + typeOfChangingElement + "&id=" + selectCountryId + "&selectLang=" + getPrefLang();
    var XHR = window.XMLHttpRequest || window.XDomainRequest;
    var xhr = new XHR();

    xhr.open("GET", url, true);
    xhr.onload = function(){
        var json = JSON.parse(xhr.responseText);
        changeSelect.innerHTML = createOptionListWithId(json.placesList, json["placeOptionFirstElement"]);
    };
    xhr.error = function(){
        alert('Error');
    };
    xhr.send();
}



//----------- cookies.js -----------


function readCookie(){
 var returnLang = [];
 var cookieArray = [];
     cookieArray = document.cookie.split('; ');
      for(var i = 0; i < cookieArray.length; ++i){
    	   var innerArray = [];
    	       innerArray = cookieArray[i].split('=');
    	       if(innerArray[0] == 'Lang'){
    	    	  returnLang[0] = innerArray[1];
    	       }
    	       if(innerArray[0] == 'selectLang'){
    	    	 returnLang[1] = innerArray[1];   
    	       }
    	       
    	       
      }
      
      return returnLang;
}




function createCookie(newLang, newSelectLang) {

        var date = new Date();
        date.setTime(date.getTime()+(5*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
        document.cookie = "Lang="+newLang+expires+"; path=/";
        document.cookie = "selectLang="+newSelectLang+expires+"; path=/";
}



//----------- generate_resume_entity.js -----------


var phoneNumbersCount = 0;
var personLinksCount = 0;
var skillsCount = 0;
var educationCount = 0;
var employmentCount = 0;
var projectsCount = 0;
var certificateCount = 0;


function createDataToServer(operationType){
    var json = {
        "address" : getPersonAddress(),
        "personalInfo": getPersonalInfo(),
        "personalTemplates": getPersonTemplates(),
        "personLinks": getPersonLinks(),
        "phoneNumbers": getPhonesNumber(),
        "skills" : getSkills(),
        "education" : getEducation(),
        "employmentHistory" : getEmployment(),
        "certificate": getCertificate(),
        "person" : new Person(),
        "operationType": operationType
    };

    return JSON.stringify(json);

}

function Person(){
    this.id = document.getElementById('firstName').dataset.personId + '';
    this.prefLang = getPrefLang();
    this.phoneNumbersCount = phoneNumbersCount + '';
    this.personLinksCount = personLinksCount + '';
    this.skillsCount = skillsCount + '';
    this.eduCount = educationCount + '';
    this.empCount = employmentCount + '';
    this.certeCount = certificateCount + '';

}

function PersonalInfo(){
    this.id = document.getElementById('firstName').dataset.personId + '';
    this.firstName = document.getElementById("firstName").value;
    this.lastName = document.getElementById("lastName").value;
    this.claimPosition = document.getElementById("claimPosition").value;
    this.eMail = document.getElementById("eMail").value;
    this.profile = document.getElementById("profile").value;
    this.hobbies = document.getElementById("hobbies").value;
    this.state = 'new';
}

function getPersonalInfo(){
    var simpleArray = [];
    simpleArray.push(new PersonalInfo());
    return simpleArray;
}

function Address(id, countryId, regionId, cityId, postalCode, address){
    this.id = '0'; //for time
    this.countryId = countryId;
    this.regionId = regionId;
    this.cityId = cityId;
    this.postalCode = postalCode;
    this.streetAddress = address;
    this.prefLang = getPrefLang();
    this.state = 'new';

}

function getPersonAddress(){
    var id = document.getElementById('firstName').dataset.personId + '';
    var countryId = createOptionsIdToServer(document.getElementById('personCountry').options);
    var regionId = createOptionsIdToServer(document.getElementById('personRegion').options);
    var cityId = createOptionsIdToServer(document.getElementById('personCity').options);
    var streetAddress = document.getElementById('address').value;
    var postalCode = document.getElementById("zipCode").value;

    var simpleArray = [];
    simpleArray.push(new Address(id, countryId, regionId, cityId, postalCode, streetAddress));
    return simpleArray;
}

function PersonTemplates(){
    this.id = document.getElementById('firstName').dataset.personId + '';
    this.templatePDF = 'SIMPLE_PDF';
    this.templateHTML = 'SIMPLE_HTML';
    this.templateDOC = 'SIMPLE_DOC';
    this.state = 'new';
}

function getPersonTemplates(){
    var simpleArray = [];
    simpleArray.push(new PersonTemplates());
    return simpleArray;
}

function PhoneNumbers(type, number, entityId, stat){
    this.phId = entityId;
    this.phState = stat;
    this.phoneType = type;
    this.number = number;
}

function PersonLinks(type, link, entityId, stat){
    this.linkId = entityId;
    this.linkState = stat;
    this.linkType = type;
    this.link = link;
}

function Skills(name, value, entityId, stat){
    this.skillsId = entityId;
    this.skillsState = stat;
    this.skillsName = name;
    this.skillsValue = value;
}

function Education(type, title, years, degree, country, region, city, desc, entityId, stat){
    this.eduId = entityId;
    this.eduState = stat;
    this.eduType = type;
    this.eduTitle = title;
    this.eduYears = years;
    this.eduDegree = degree;
    this.id = '0';//for time
    this.countryId = country;
    this.regionId = region;
    this.cityId = city;
    this.state = 'new';//for time
    this.eduDescription = desc;
    this.prefLang = getPrefLang();
}

function EmploymentHistory(id, title, years, position, addressId, country, region, city, desc, stat, projectsBlockId){
    this.projects = getProjects(projectsBlockId);
    var projectCount = projectsCount;
    this.empMainInfo = new MainEmpInfo(id, stat, title, years, position, desc, projectCount);
    projectsCount = 0;
    this.empAddress = new Address(addressId, country, region, city,'', '');



}

function MainEmpInfo(entityId, stat, title, years, position, desc, projectCount){
    this.empId = entityId;
    this.empState = stat;
    this.empTitle = title;
    this.empYears = years;
    this.empPosition = position;
    this.empDescription = desc;
    this.projectCount = projectCount + '';
}

function Projects(entityId, companyId,title, position, years, desc, stat){
    this.companyId = companyId;
    this.projId = entityId;
    this.projState = stat;
    this.projTitle = title;
    this.projPosition = position;
    this.projYears = years;
    this.projDescription = desc;
}

function Certificate(size, pathToImages, small, full, entityId, stat){
    this.certId = entityId;
    this.certState = stat;
    this.certSize = size;
    this.pathToImages = pathToImages;
    this.certSmallDesc = small;
    this.certFullDesc = full;
}



function getPhonesNumber(){
    var phoneType = document.getElementsByClassName('personPhonesType');
    var number = document.getElementsByName('phoneNumbers');
    var returnPhonesNumbers = [];

    if(phoneType.length != 0){
    for(var i = 0; i < phoneType.length; ++i){
        returnPhonesNumbers.push(new PhoneNumbers(phoneType[i].value, number[i].value, number[i].dataset.entityId, number[i].dataset.state));
        phoneNumbersCount++;
    }
    } else {
        returnPhonesNumbers.push(new PhoneNumbers('', '', '', ''));
    }

    return returnPhonesNumbers;
}

function getPersonLinks(){
    var linkType = document.getElementsByClassName('personLinksType');
    var link = document.getElementsByName('personLinks');
    var returnPersonalLinks = [];

    if(linkType.length != 0){
    for(var i = 0; i < linkType.length; ++i){
        returnPersonalLinks.push(new PersonLinks(linkType[i].value, link[i].value, link[i].dataset.entityId, link[i].dataset.state));
        personLinksCount++;
    }
    } else {
        returnPersonalLinks.push(new PersonLinks('', '', '', ''));
    }

    return returnPersonalLinks;
}

function getSkills(){
    var names = document.getElementsByName('skillsName');
    var value = document.getElementsByName('skillsValue');
    var returnSkills = [];

    if(names.length != 0){
    for(var i = 0; i < names.length; ++i){
        returnSkills.push(new Skills(names[i].value, value[i].value, names[i].dataset.entityId, names[i].dataset.state));
        skillsCount++;
    }
    } else {
        returnSkills.push(new Skills('', '', '', ''));
    }

    return returnSkills;
}

function getEducation(){
    var type = document.getElementsByClassName('eduType');
    var title = document.getElementsByName('eduTitle');
    var years = document.getElementsByName('eduYears');
    var degree = document.getElementsByName('eduDegree');
    var country = createArrayOfOptionsIdToServer(document.getElementsByName('educationCountryName'));
    var region = createArrayOfOptionsIdToServer(document.getElementsByName('educationRegionName'));
    var city = createArrayOfOptionsIdToServer(document.getElementsByName('educationCityName'));
    var desc = document.getElementsByName('eduDescription');
    var returnEducation = [];

    if(type.length != 0){
    for(var i = 0; i < type.length; ++i){
        returnEducation.push(new Education(type[i].value, title[i].value, years[i].value, degree[i].value, country[i], region[i], city[i], desc[i].value, title[i].dataset.entityId, title[i].dataset.state));
        educationCount++;
    }
    } else {
        returnEducation.push(new Education('', '', '', '', '', '', '', '', '', ''));
    }
    return returnEducation;
}

function getEmployment(){

    var id = document.getElementsByName('empTitle');
    var title = document.getElementsByName('empTitle');
    var years = document.getElementsByName('empYears');
    var position = document.getElementsByName('empPosition');
    var country = createArrayOfOptionsIdToServer(document.getElementsByName('employmentCountryName'));
    var region = createArrayOfOptionsIdToServer(document.getElementsByName('employmentRegionName'));
    var city = createArrayOfOptionsIdToServer(document.getElementsByName('employmentCityName'));
    var desc = document.getElementsByName('empDescription');
    var returnEmployment = [];

    if(title.length != 0){
    for(var i = 0; i < title.length; ++i){
        var projectBlockId = 'projectBlock' + id[i].dataset.blockId;
        returnEmployment.push(new EmploymentHistory(id[i].dataset.entityId, title[i].value, years[i].value, position[i].value, id[i].dataset.entityId, country[i], region[i], city[i], desc[i].value, title[i].dataset.state, projectBlockId));
        employmentCount++;
    }
    } else {
        returnEmployment.push(new EmploymentHistory('', '', '', '', '', '', '', '', '', ''));
    }

    return returnEmployment;
}

function getProjects(projectBlockId ){
    var projectBlock = document.getElementById(projectBlockId).childNodes;
    var returnProjects = [];

    if(projectBlock.length == 0){
        returnProjects.push(new Projects('', '', '', '', '', '', ''));
    }

    for(var i = 0; i < projectBlock.length; ++i){

        if(projectBlock[i].tagName === 'DIV'){
            var concreteProjectBlockId = projectBlock[i].dataset.blockId;



            var companyId = document.getElementById('projTitle' + concreteProjectBlockId);
            var title = document.getElementById('projTitle' + concreteProjectBlockId);
            var years = document.getElementById('projYears' + concreteProjectBlockId);
            var position = document.getElementById('projPosition' + concreteProjectBlockId);
            var desc = document.getElementById('projDescription' + concreteProjectBlockId);

            returnProjects.push(new Projects(title.dataset.entityId, companyId.dataset.companyId, title.value, years.value, position.value, desc.value, title.dataset.state));
            projectsCount++;
        }

    }

    return returnProjects;
}



// PROBLEM in this function!
function getCertificate(){
    var size = document.getElementsByClassName('certificateImagesType');
    var pathToImage = document.getElementsByName('pathToImage');
    var small = document.getElementsByName('certSmallDesc');
    var full = document.getElementsByName('certFullDesc');
    var returnCertificate = [];

    if(size.length != 0){
        for(var i = 0; i < size.length; ++i){
            returnCertificate.push(new Certificate(size[i].value, pathToImage[i].src, small[i].value, full[i].value, small[i].dataset.entityId, small[i].dataset.state));
            certificateCount++;
        }
    } else {
        returnCertificate.push(new Certificate('', '', '', '', '', ''));
    }

    return returnCertificate;
}




//----------- local.js -----------



function getOptionsList(list, selectedId){
    var optionList = '<option id="0" value=""></option>';

    for(var i = 0; i < list.length; ++i){
        var object = list[i];
      if(selectedId != object["placeId"]){
          optionList += '<option id="' + object["placeId"] + '" value="' + object["placeName"] + '">' + object["placeName"] + '</option>';
      } else {
          optionList += '<option id="' + object["placeId"] + '" value="' + object["placeName"] + '" selected>' + object["placeName"] + '</option>';
      }
    }

    return optionList;
}




function changeLangOnStartPage(){
    changeStaticInfoFromServer(getPrefLang(), "simple_static");
    loadPlacesByNewLang(getJsonSelectBlockObjectArray(getPrefLang()));
}


function getJsonSelectBlockObjectArray(lang){
    var json = {
        list : createSelectBlockObjectArray(),
        lang : lang
    };

    return JSON.stringify(json);
}


function createSelectBlockObjectArray(){
    var arrayOfObject = [];
    var selectBlocks = document.getElementsByClassName('selectBlock');

    for(var i = 0; i < selectBlocks.length; ++i){
         var concreteBlock = selectBlocks[i];
         var countryId = getIdOfSelectedOption(concreteBlock.getElementsByClassName('countrySelect')[0]);
         var regionId = getIdOfSelectedOption(concreteBlock.getElementsByClassName('regionSelect')[0]);
         var cityId = getIdOfSelectedOption(concreteBlock.getElementsByClassName('citySelect')[0]);

         arrayOfObject.push(new SelectBlockObject(countryId + '', regionId + '', cityId + '', concreteBlock.id));
    }
   return arrayOfObject;
}


function SelectBlockObject(countryId, regionId, cityId, selectBlockId){
    this.countryId = countryId;
    this.regionId = regionId;
    this.cityId = cityId;
    this.selectBlockId = selectBlockId;
}



function getIdOfSelectedOption(selectElement){
    if(selectElement.tagName != 'SELECT')return 0;
    if(selectElement.textContent.length < 10) return 0;
    if(selectElement.value == 'Select country' || selectElement.value == 'Select region') return 0;
    var options = selectElement.options;
    return options[options.selectedIndex].id;
}



function startResumeFormPage(){

    var currentLang = getCurrentLangByLocalStorage();
    /*
    if(checkDataExistingInWebStorageByLang(currentLang, FIELD_DATA)){
        alert('If');
        changeStaticInfoFromWebStorage(currentLang);
    } else {
        alert('Else');
        */
        changeStaticInfoFromServer(currentLang, 'simple_static');
    //}

}





$.fn.exists = function () {
    return this.length !== 0;
};

function testtt(fieldName){
    return $("" + fieldName + "").exists();
}

function changeLocaleStaticSpans(fieldName, newLangValue){
    if(testtt(fieldName)){
        $("" + fieldName + "").html(newLangValue);
    }
}


//bad implementation
function changeLocalStaticSelects(newLangValue, selectTypeName){
    var selectNodes = document.getElementsByClassName(selectTypeName.replace('.',''));
    for(var i = 0; i < selectNodes.length; ++i){
        if(selectNodes[i].textContent < 30){
            selectNodes[i].innerHTML = newLangValue;
        }
    }

}



//----------- select_options.js -----------




function change(mainSelect, changeSelect, typeOfChangingElement){
    loadRegionOrCity(getSelectedOptionId(document.getElementById(mainSelect)), document.getElementById(changeSelect), typeOfChangingElement);
}






//----------- upload_images.js -----------



function uploadFile()
{
    var ajax = {
        'url'   : 'http://cvcreator-service.com:8080/CVCreator/FileUploadController',
        'form'   : 'fileUploader', 
        'isUpload' : true, 
        'success' : uploadSuccess, 
        'filure'  : requestFilure
    };
    Ext.Ajax.request(ajax); 
} 

function uploadSuccess (response, options)
{
    

    var result = Ext.util.JSON.decode(response.responseText);
    var goodImages = result.goodImages;
    var badImages = result.badImages;


    for(var i = 0; i < goodImages.length; ++i){
        var random1 = getRandomInteger();
        var tbody = Ext.get('bord').first();
        var newElement = document.createElement('div');
        newElement.id = 'field' + parseInt(random1);
        newElement.style['backgroundColor'] = '#bada55';
        newElement.innerHTML = '<div class="row-fluid">'
            + '<div class="span12">'
            + '<div class="row-fluid">'
            + '<div class="span2">'
            + '<span class="certSizeT"></span>:<br>'
            + '</div>'
            + '<div class="span6">'
            + '<span class="exampleInfo certSizeE"></span><br>'
            + '<select size="1" class="certificateImagesType"></select><br>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span12">'
            + '<img src="http://' + goodImages[i] + '" class="pathToImages img-polaroid" name="pathToImage"><br>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span2">'
            + '<span class="certShortDescT"></span>:<br>'
            + '</div>'
            + '<div class="span6">'
            + '<span class="exampleInfo certShortDescE"></span>:<br>'
            + '<input type="text" size="70" name="certSmallDesc" placeholder="Not" value=""><br>'
            + '</div>'
            + '</div>'
            + '<div class="row-fluid">'
            + '<div class="span2">'
            + '<span class="certFullDescT"></span>:<br>'
            + '</div>'
            + '<div class="span6">'
            + '<span class="exampleInfo certFullDescE"></span><br>'
            + '<textarea class="span8" name="certFullDesc" rows="10" placeholder="Not"></textarea>'
            + '</div>'
            + '</div>'
            + '<img src="http://ser.optivisions.ru/styleimg/iconforcancel.jpg" class="remButton" onclick="removeF(' + newElement.id + ')">'
            + '</div>'
            + '</div>';
            tbody.appendChild(newElement);

           startResumeFormPage();
        jQuery("#" + newElement.id).animate({
            backgroundColor: "#FFFFFF"
        }, 1500 );

    }

    for(var i = 0; i < badImages.length; ++i){
            var badbody = Ext.get('bad').first();
            var random3 = getRandomInteger();
            var badElement = document.createElement('div');
            badElement.id = 'badE' + parseInt(random3);
            badElement.style['backgroundColor'] = '#a62a2a';
            badElement.innerHTML = '<p>' + badImages[i] + '</p>';
            badbody.appendChild(badElement);
                  jQuery("#" + badElement.id).animate({
                      backgroundColor: "#FFFFFF"
                  }, 1500 );

    }
    
    


} 


function requestFilure(response, options)
{
    alert ('error');
} 






//----------- utils.js -----------


function getRandomInteger(){
    var rand = Math.random() * 35;
    return Math.random() * (100 * rand);
}



function imagesLoop(images){
    var imagesPath = [];
    for(var i = 0; i < images.length; ++i){
        imagesPath.push(images[i].src);
    }

    return imagesPath;
}



function getFakeArray() {
    var fakeArray = [];
    fakeArray.push("");
    return fakeArray;
}


function removeSimpleBlock(element){
    var elem = document.getElementById(element);
    elem.parentNode.removeChild(elem);
}

function removeBlock(elem, deleteButton){
    elem.parentNode.removeChild(elem);
    deleteButton.parentNode.removeChild(deleteButton);
}



function loop(elems) {
    var names = [];
    for ( var i = 0; i < elems.length; ++i) {
        if (typeof elems[i].value == 'undefined') {
            names.push('');
        } else {
            names.push(elems[i].value);
        }
    }
    return names;
}

function getConcreteTemplate(elems){
    var template;

    for(var i = 0; i < elems.length; i++){
        if(elems[i].name == 'onTemplate'){
            template = elems[i].id;
        }
    }

    return template;
}


function loopPlacesId(placesElems, flag){
   var returnIds = [];

    for(var i = 0; i < placesElems.length; ++i){
        var options = placesElems[i].options;
        returnIds.push(options[options.selectedIndex].id.replace(flag, ''));
    }

    return returnIds;
}


function loopImagesSize(imagesElems){
    var returnValues = [];

    for(var i = 0; i < imagesElems.length; ++i){
        var options = imagesElems[i].options;
        alert(options[options.selectedIndex]);
        alert(options[options.selectedIndex].value);
        returnValues.push(options[options.selectedIndex].value);
    }

    return returnValues;
}


function getPrefLang(){
  return document.getElementById('selectLang').value;
}



function mergeJsonArrays(main, addition){
    var returnJson = [];
    for(var i = 0; i < main.length; ++i){
        returnJson.push(main[i]);
    }

    for(var i = 0; i < addition.length; ++i){
        returnJson.push(addition[i]);
    }

    return JSON.stringify(returnJson);
}

function getSelectedOptionId(select){
    var options = select.options;
    return options[options.selectedIndex].id;
}


function createOptionsIdToServer(options){
    if(options.length < 1){
        return '';
    } else {
        return options[options.selectedIndex].id;
    }

}

function createArrayOfOptionsIdToServer(elementsList){
    var clearId = [];
    for(var i = 0; i < elementsList.length; ++i){
        var option = elementsList[i].options;
        clearId.push(createOptionsIdToServer(option));
    }
    return clearId;
}


function createOptionsList(values){
    var optionList = '';
    for(var i = 0; i < values.length; ++i){
        optionList += '<option value="' + values[i] + '">' + values[i] + '</option>';
    }

    return optionList;
}

function createOptionListWithId(placesList, titleOfList){
    var optionList = '<option id="0" value="' + titleOfList + '" selected>' + titleOfList + '</option>';
    for(var i = 0; i < placesList.length; ++i){
        optionList += '<option id="' + placesList[i].placeId + '" value="' + placesList[i].placeName + '">' + placesList[i].placeName + '</option>';
    }

    return optionList;
}



//----------- validator.js -----------


function validateAll(emptyField, notEMail, notInteger){

    var elems = document.body.getElementsByTagName("*");
    for(var i = 0; i < elems.length; ++i){
        if(elems[i].tagName == 'INPUT' || elems[i].tagName == 'TEXTAREA'){
            validatePlainInput(elems[i], emptyField);
        }
    }

    validateEmail(document.getElementById('eMail'), notEMail);
    validateInteger(document.getElementById('zipCode'), notInteger);
}


function validatePlainInput(validateElement, message){
   //for time

    if(validateElement.name == 'newFile'){
        return;
    }

  if(validateElement.value == ''){
      validateElement.textContext = 'Bad';
      validateElement.placeholder = message;
  } else {
     validateElement.textContext = '';
     validateElement.placeholder = '';
  }

}


function validateEmail(email, message) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var isEmail = re.test(email.value);
        if(!isEmail){
            email.textContext = 'Bad';
            email.placeholder = message;
        } else {
            email.textContext = '';
            email.placeholder = '';
        }
}


function validateInteger(integerElement, message){
    var intRegex = /^\d+$/;
    var isInteger = intRegex.test(integerElement.value);
        if(!isInteger){
            integerElement.textContext = 'Bad';
            integerElement.placeholder = message;
        } else {
            integerElement.textContext = '';
            integerElement.placeholder = '';
        }

}


function checkBadResults() {
    alert('Start check');
    var elem = document.getElementsByTagName('*');
    for ( var i = 0; i < elem.length; ++i) {
        if (elem[i].textContext == 'Bad') {
            return false;
        }

    }
    return true;
}



//----------- visual.js -----------



function onClickEffect(image, nodesName){

    var allTempElem = document.getElementsByClassName(nodesName);
    for(var i = 0; i < allTempElem.length; ++i){
        if(allTempElem[i].name == 'onTemplate'){
            allTempElem[i].src = 'http://ser.optivisions.ru/styleimg/offClick.png';
            allTempElem[i].name = 'offTemplate';
        }
      }

    image.src = 'http://ser.optivisions.ru/styleimg/onClick.png';
    image.name = 'onTemplate';

}

function viewTemplate(clickNode, activeNode){

    var mainD = document.getElementById('modalTemplate');
    var newDiv = document.createElement('div');
    var newIframe = document.createElement('iframe');
    var imageNode = document.getElementById(activeNode);
    newIframe.src = imageNode.src;
    newIframe.height = 800;
    newIframe.width = 700;
    mainD.appendChild(newDiv);
    newDiv.appendChild(newIframe);
    $("#modalTemplate").dialog({ height: 900, width: 750, modal: true, resizable: false, closeOnEscape: true, draggable: true});
}



//----------- web-storage-items.js -----------


const FIELD_DATA = 'haveFieldsData';
const ERROR_DATA = 'haveErrorData';

function checkDataExistingInWebStorageByLang(lang, typeData){
    if (!Modernizr.localstorage){return false;}
    if (localStorage.getItem(typeData) != 'true') {return false;}
    var existingLangs = JSON.parse(localStorage['existingLanguages']);
    for(var existLang in existingLangs){
        if(existLang == lang) return true;
    }
    return false;
}

function changeStaticInfoFromWebStorage(lang){
    var staticFields = localStorage.staticFields;
    for(var i = 0; i < staticFields.length; ++i){
        changeLocaleStaticSpans($('' + staticFields[i] + ''), (localStorage.getItem(staticFields[i] + lang)));
    }
}


function addStaticInfoToWebStorage(staticFields, json, lang){
    localStorage.setItem("staticInfo", JSON.stringify(json));
    localStorage["staticFields"] = saveFieldList(staticFields);
    alert('Sec');
    addNewLangToArray(lang);
}


function addStaticArrayInfoToWebStorage(staticFields, json, lang){


     if(staticFields.length != 0){
         for(var i = 0; i < staticFields.length; ++i){
             localStorage.setItem('' + staticFields[i] + '' + lang + '', JSON.stringify(json[staticFields[i]]));
         }
     } else {
         localStorage.setItem('' + staticFields + '', JSON.stringify(json));
     }

    localStorage["staticFields"] = saveFieldList(staticFields);
    addNewLangToArray(lang);
}


function saveFieldList(data){
    var staticFields = localStorage["staticFields"];
    if(staticFields == undefined || staticFields.length == 0){
        return JSON.stringify(data);
    } else {
        return mergeJsonArrays(staticFields, JSON.stringify(data));
    }
}


function addNewLangToArray(newLang){
    var temp = localStorage['existingLanguages'];
    var array = (temp != undefined) ? JSON.parse(localStorage['existingLanguages']) : undefined;
    var tempData = [];
    alert('Array length - ' + array);
    if(array == undefined || array.length == 0){
        tempData.push(newLang);
        localStorage['existingLanguages'] = JSON.stringify(tempData);
    } else {

        var flag = false;
        for(var lang in array){
            if(lang == newLang){
                flag = true;
            }
        }

        if(!flag){
            array.push(newLang);
            localStorage['existingLanguages'] = JSON.stringify(array);
        }
    }

    localStorage.currentLang = newLang;
}

function getCurrentLangByLocalStorage(){
    /*
    var currentLang = localStorage.currentLang;
    alert('In get method - ' + currentLang);
    if(currentLang !== undefined){
        return currentLang;
    } else {
    */
        return getPrefLang();
    //}
}




//----------- dom_objects_factory.js -----------


function newNextButton(newObjectId){
    var newButton = document.createElement('button');
    newButton.id = newObjectId;
    newButton.className = 'btn btn-large btn-primary';
    return newButton;
}

function newAddStructureButton(buttonId){
    var addButton = document.createElement('button');
    addButton.id = buttonId;
    addButton.textContent = 'AddMore';
    addButton.className = 'btn btn-small btn-info addMore';
    return addButton;
}

function newDeleteStructureButton(){
    var deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    return deleteButton;
}



