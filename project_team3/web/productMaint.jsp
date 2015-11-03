<%-- 
    Document   : productMaint
    Created on : Nov 3, 2015, 1:17:59 PM
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
        <h1>Products</h1>
        
        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <%@ taglib uri="/WEB-INF/murach.tld" prefix="mma" %>
            <mma:product>        
                <tr>
                    <td>${productCode}</td>
                    <td>${productDescription}</td>
                    <td class="right">${productPrice}</td>
                    <td><a href="<c:url value='/loadProducts' >
                               <c:param name='action' value='addProduct'/>
                               <c:param name='productCode' value='${productCode}'/>
                           </c:url>">Edit</a></td>
                    <td><a href="<c:url value='/loadProducts' >
                               <c:param name='action' value='removeProduct'/>
                               <c:param name='productCode' value='${productCode}'/>
                           </c:url>">Delete</a></td>
                </tr>
            </mma:product>
        </table>
    </body>
</html>
