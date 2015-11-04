<%-- 
    Document   : addProduct
    Created on : Nov 3, 2015, 2:31:43 PM
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
        <h1>Product</h1>
        <form action="loadProducts" method="post">
            <table class="noBorder">
                <tr>
                    <td><b>Code:</b></td>
                    <c:if test="${productCode == null}">
                        <td><input type="text" name="productCode" value=""/></td>
                    </c:if>
                    <c:if test="${productCode != null}">
                        <td>
                            <input type="hidden" name="productCode" value="${productCode}"/>
                            ${productCode}
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td><b>Description</b></td>
                    <td><input type="text" name="productDesc" value="${productDesc}"/></td>
                </tr>
                <tr>
                    <td><b>Price:</b></td>
                    <td><input type="text" name="productPrice" value="${productPrice}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <c:if test="${productCode == null}">
                        <td>
                            <input type="hidden" name="action" value="updateProduct"/>
                            <input type="submit" value="Add Product"/>
                        </td>
                    </c:if>
                    <c:if test="${productCode != null}">
                        <td>
                            <input type="hidden" name="action" value="updateProduct"/>
                            <input type="submit" value="Update Product"/>
                        </td>
                    </c:if>
                </tr>
            </table>
        </form>
        <br/>
        <form action="loadProducts">
            <input type="hidden" name="action" value="displayProducts"/>
            <input type="submit" value="View Products"/>
        </form>
    </body>
</html>
