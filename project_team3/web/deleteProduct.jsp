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
                        <td class="code right"><b>Code:</b></td>
                        <td class="description">${product.code}</td>
                    </tr>
                    <tr>
                        <td class="code right"><b>Description:</b></td>
                        <td class="description">${product.description}</td>
                    </tr>
                    <tr>
                        <td class="code right"><b>Price:</b></td>
                        <td class="description">${product.getPriceCurrencyFormat() }</td>
                    </tr>
                    <td colspan="2">
                        <form action="deleteProduct" method="post">
                            <input type="hidden" name="productCode" value="${product.code}"/>
                            <input type="hidden" name="productDesc" value="${product.description}"/>
                            <input type="hidden" name="productPrice" value="${product.price}"/>
                            <input type="hidden" name="action" value="confirmDeletion"/>
                            <input type="submit" class="mL10 fLeft" value="Yes"/>
                        </form>
                        <form action="loadProducts" method="get">
                            <input type="hidden" name="action" value="displayProducts"/>
                            <input type="submit" class="neutral fLeft" value="No" />
                        </form>
                    </td>

                </table>
                        
            </div>
        </div>
    </body>
</html>
