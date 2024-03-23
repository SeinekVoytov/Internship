<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        form {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-align: center;
        }

        input[type="text"] {
            width: 100%;
            box-sizing: border-box;
            padding: 10px;
            margin-bottom: 10px;
            font-size: 16px;
        }

        input[type="submit"] {
            width: 100%;
            box-sizing: border-box;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Welcome to Online shop</h2>
    <form action="shop.jsp" method="get">
        <input type="text" name="name" placeholder="Enter your name" required>
        <br>
        <input type="submit" value="Enter">
    </form>
</body>
</html>
