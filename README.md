#CVCreator - generate your resume online!

In front-end part I work with:

1. AJAX. This project is single page in a greater degree. Internationalization, fill out input fields, fill out <select></select> tags with cities, countries, regions, send user information to server, etc.
2. JSON. I use JSON for request and response. It is one of the best way to exchange information between front-end and back-end in my opinion;
3. Object-Oriented style. I try use object-way in JavaScript. Create some objects like in Java, Ruby, C++;

P.S. I write code in several JavaScript files when I develop front-end part. I divide them by functionality. But I use single JavaScript file for best performance in real use on HTML page where I assemble all of my simple JavaScript files. Link to my assembly program - https://github.com/ArthurSpirke/JSPack


I work with standard web Java stack in the back-end part :
1. Servlets. Work with request and response from the web. This is something like Controller in MVC model
2. JDBC. This is like ActiveRecords in Ruby on Rails. It is used to persist data in database.
3. iText. This is the library for generating PDF documents in Java
4. Freemarker. This is the library for generating HTML templates in Java.






Some moments about development state of this project:

This is not a finished project. I'm working on it.

I wanted use WebStorage in front-end and I tried to implement this on pure JavaScript in my project. It isn't easy  in general and in my project too. Because of this, I commented some code, which is present WebStorage functionality in my code.
I have some validation functionality in my code under the comment for this moment. For example:



     // checkFields()
     // var res = checkBadResults();
     // alert(res);
     if (true) {

               //some code

       }

In if-statement I actually use 'res' variable, where I get boolean. If true - we continue work, if false - we send some alert message to user, and fill out some fields on html page to inform user.
