<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/cvstyle.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.0.2.js"></script>
<title>Insert title here</title>
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
<div id="top-of-page"></div>



<div class="row-fluid">
<div class="span12">
<div class="span4"></div>
<div class="span6">
<div class="row-fluid">
<div class="span12">
<div class="span4">
<% out.print("<a href=\"http://" + request.getAttribute("pdf") + "\" target=\"_blank\"><img src=\"http://ser.optivisions.ru/styleimg/pdf_pic.jpg\"></a>"); %>
</div>
</div>
</div>
<div class="row-fluid">
<div class="span12">
<div class="span4">
<a href="#" class="blackLink"><span class="blackLink">PDF LINK</span></a>
</div>
</div>
</div>
<div class="row-fluid">
<div class="span12">
<!-- try add CENTER attribute in CSS to this text\block
LINK TO DOWNLOAD ALL RESUMES IN ZIP -->
</div>
</div>
<div class="row-fluid">
<div class="span12">
<!-- try add CENTER attribute in CSS to this text\block -->
<% out.print("Update CV - <a href=\"http://ser.optivisions.ru:8080/CVMaker/UpdateUserCV?id=" + request.getAttribute("id") + "\"><span class=\"blackLink\">Update Info</span></a><br>"); %>
</div>
</div>
<div class="row-fluid">
<div class="span12">
<!-- try add CENTER attribute in CSS to this text\block -->
TEXT
</div>
</div>
</div>
<div class="span2"></div>
</div>
</div>



<div id="bottom-of-page"></div>
</body>
</html>