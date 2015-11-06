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
        <div class="container">
            <div class="header">
                <div class="block"><h1>Products</h1></div>
                <div class="block" style="text-align: right;">
                    <form action="loadProducts">
                        <input type="hidden" name="productCode" value=""/>
                        <input type="hidden" name="action" value="addProduct"/>
                        <input type="submit" value="Add Product"/>
                    </form>
                </div>
            </div>
            <div class="card">
                <table class="">
                    <tr>
                        <th class="center">Code</th>
                        <th>Description</th>
                        <th class="right">Price</th>
                        <th></th>
                        <th class="mobHide"></th>
                    </tr>
                    <%@ taglib uri="/WEB-INF/murach.tld" prefix="mma" %>
                    <mma:product>        
                        <tr>
                            <td class="code center colRes">${productCode}</td>
                            <td class="description">
                                <div class="artist">${productArtist}</div>
                                <div class="album">${productAlbum}</div></td>
                            <td class="price right">${productPrice}</td>
                            <td class="center colRes"><a href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='addProduct'/>
                                       <c:param name='productCode' value='${productCode}'/>
                                   </c:url>">Edit</a></td>
                            <td class="center colRes mobHide">
                                <a href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='removeProduct'/>
                                       <c:param name='productCode' value='${productCode}'/>
                                   </c:url>">Delete</a></td>
                        </tr>
                    </mma:product>
                </table>
            </div>
            <br/>
        </div>
    </body>
</html>
