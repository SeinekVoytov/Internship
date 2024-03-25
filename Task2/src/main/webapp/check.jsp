<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Product" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.example.dao.ProductDao" %>
<%@ page import="org.example.dao.Dao" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Check</title>
</head>
<body>
<h2>Dear <%= session.getAttribute("name")%>, your order:</h2>

    <%

        if (request.getMethod().equals("GET")) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            return;
        }

        String[] selectedProducts = request.getParameterValues("selectedProducts");

        List<Integer> ids = Arrays.stream(selectedProducts)
                .map(Integer::parseInt)
                .toList();

        ApplicationContext context = (ApplicationContext) session.getAttribute("context");
        Dao<Product> dao = (ProductDao) context.getBean("productDao");

        List<Product> products = dao.getAll().stream()
                .filter(product -> ids.contains(product.getId()))
                .toList();

        float sum = (float) products.stream().mapToDouble(Product::getPrice).sum();

        request.setAttribute("products", products);
        request.setAttribute("total", sum);
    %>

    <ol type="1">
        <c:forEach var="product" items="${products}">
            <li>${product.name} ${product.price}$</li>
        </c:forEach>
    </ol>

    <p>Total: $ ${total}</p>

</body>
</html>
