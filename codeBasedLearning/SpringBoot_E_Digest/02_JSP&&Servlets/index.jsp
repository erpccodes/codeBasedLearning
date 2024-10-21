<!-- index.jsp: Simple JSP page to take user input -->
<html>
<head>
    <title>Enter Name</title>
</head>
<body>
    <!-- The form submits to the 'GreetingServlet' via POST method -->
    <form action="GreetingServlet" method="POST">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="username" required />
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
