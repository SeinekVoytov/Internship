<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            margin: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 600px;
        }
        h1 {
            color: #e74c3c;
            font-size: 24px;
            margin-bottom: 10px;
        }
        p {
            margin-bottom: 10px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Something went wrong</h1>
    <%
        out.println("<p>Status Code: " + response.getStatus() + "</p>");
        out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
    %>
</div>
</body>
</html>

