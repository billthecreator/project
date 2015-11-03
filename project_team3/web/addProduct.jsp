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
        <form action="" method="post">
            <table>
                <tr>
                    <td><b>Code:</b></td>
                    <td><input type="text" name="productCode" value="${productCode}"/></td>
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
                    <td>
                        <input type="submit" value="Update Product"/> 
                        <input type="button" value="View Products"/> 
                    
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
