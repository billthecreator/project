<%-- 
    Document   : deleteProduct
    Created on : Nov 4, 2015, 11:20:47 AM
    Author     : William
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url='includes/header.html' />
    </head>
    <body>
        <h1>Are you sure you want to delete this product?</h1>
        <table class="noBorder">
            <tr>
                <td><b>Code:</b></td>
                <td>${productCode}</td>
            </tr>
            <tr>
                <td><b>Description</b></td>
                <td>${productDesc}</td>
            </tr>
            <tr>
                <td><b>Price:</b></td>
                <td>${productPrice}</td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <form action="deleteProduct" method="post">
                        <input type="hidden" name="productCode" value="${productCode}"/>
                        <input type="hidden" name="productDesc" value="${productDesc}"/>
                        <input type="hidden" name="productPrice" value="${productPrice}"/>
                        <input type="hidden" name="action" value="confirmDeletion"/>
                        <input type="submit" value="Yes"/>
                    </form>
                    <form action="loadProducts" method="get">
                        <input type="hidden" name="action" value="displayProducts"/>
                        <input type="submit" value="No" />
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
