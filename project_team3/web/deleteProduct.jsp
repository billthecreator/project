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
        <div class="container">
            <div class="header">
                <div class="block"><h1>Are you sure you want to delete this product?</h1></div>
            </div>
            <div class="card">
                <table class="noBorder noColor">
                    <tr>
                        <td class="right"><b>Code:</b></td>
                        <td class="price">${product.code}</td>
                    </tr>
                    <tr>
                        <td class="right"><b>Description:</b></td>
                        <td class="price">${product.description}</td>
                    </tr>
                    <tr>
                        <td class="right"><b>Price:</b></td>
                        <td class="price">${product.getPriceCurrencyFormat() }</td>
                    </tr>
                    <td></td>
                    <td>
                        <form action="deleteProduct" method="post">
                            <input type="hidden" name="productCode" value="${product.code}"/>
                            <input type="hidden" name="productDesc" value="${product.description}"/>
                            <input type="hidden" name="productPrice" value="${product.price}"/>
                            <input type="hidden" name="action" value="confirmDeletion"/>
                            <input type="submit" class="fLeft" value="Yes"/>
                        </form>
                        <form action="loadProducts" method="get">
                            <input type="hidden" name="action" value="displayProducts"/>
                            <input type="submit" class="neutral fRight" value="No" />
                        </form>
                    </td>

                </table>
                        
            </div>
        </div>
    </body>
</html>
