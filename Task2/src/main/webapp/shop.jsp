<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.example.config.SpringConfig" %>
<%@ page import="org.example.dao.Dao" %>
<%@ page import="org.example.model.Product" %>
<%@ page import="org.example.dao.ProductDao" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Order</title>
    <script>
        function toggleRequiredCheckboxes() {
            const requiredCheckboxes = $('.browsers :checkbox[aria-required]');
            requiredCheckboxes.change(function(){
                if(requiredCheckboxes.is(':checked')) {
                    requiredCheckboxes.removeAttr('required');
                } else {
                    requiredCheckboxes.attr('required', 'required');
                }
            });
        }
    </script>
</head>
<body>
<h2>Hello <%= request.getParameter("name")%>!</h2>

    <%
        session.setAttribute("name", request.getParameter("name"));

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Dao<Product> dao = (ProductDao) context.getBean("productDao");
        request.setAttribute("products", dao.getAll());
    %>

    <p>Make your order</p>
    <form action="check.jsp" method="post">
        <c:forEach items="${products}" var="product">
            <input type="checkbox" id="product_${product.id}" name="selectedProducts" value="${product.id}" aria-required="true" onchange="toggleRequiredCheckboxes()">
            <label for="product_${product.id}" id="quantity_${product.id}">${product.name}</label>
            <br>
        </c:forEach>

        <br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>