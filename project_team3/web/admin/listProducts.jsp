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
        <c:import url='/includes/header.jsp' />
    </head>
    <body>
        <div class="container">
            <div class="card withTitle">
                <table cellspacing="0">
                    <tr class="cardTitle" style="background-color: ${pageColor};">
                        <td colspan="6">
                            
                            <div class="actionBar">
                                <c:import url="/includes/welcome.jsp"/>
                            </div>
                            <div class="title">
                                <h1>Product Manager</h1>
                                <h2>A list of artists' albums</h2>
                            </div>
                        </td>
                    </tr>
                    <c:if test="${products != null && products.size() > 0}">
                    <tr>
                        <th class="mobHide center">Code</th>
                        <th></th>
                        <th colspan="1" data-name="album">Album</th>
                        
                        <th class="center mobHide"></span><i class="fa fa-pencil "></i></th>
                        <th class="center mobHide"></span><i class="fa fa-ban "></i></th>
                        <th class="right" data-name="price"><span class="iconWord">Price</span><i class="fa fa-usd wordIcon"></i></th>
                    </tr>
                    <c:forEach var="items" items="${products}">  
                    <tr class="productList">
                        <td class="code center colRes mobHide">${items.code}</td>
                        <td class="colRes" data-name="album">
                            <a class="coverArtLink" href="<c:url value='/AdminController/updateProduct'><c:param name='productCode' value='${items.code}'/></c:url>">
                                <img class="coverArt" src="${items.getImageURL()}"/>
                                <i class="fa fa-ellipsis-v"></i>
                            </a>
                        </td>
                        <td class="description">
                            <div class="artist">${items.getArtistName()}</div>
                            <div class="album">${items.getAlbumName()}</div></td>
                        <td class="center colRes mobHide"><a class="button flat" href="<c:url value='/AdminController/updateProduct'><c:param name='productCode' value='${items.code}'/></c:url>"><span class="iconWord">Edit</span><i class="fa fa-ellipsis-v wordIcon" title="Edit or delete this item"></i></a></td>
                        <td class="center colRes mobHide">
                            <a class="button flat" href="<c:url value='/AdminController/deleteProduct'><c:param name='productCode' value='${items.code}'/></c:url>">Delete</a>
                        </td>
                        <td class="price right" data-name="price">${items.getPriceCurrencyFormat()}</td>
                    </tr>
                    </c:forEach>
                    </c:if>
                    <tr>
                        <td colspan="6" style="padding:0;">
                            <div class="actionBar">
                                <a class="button neutral fLeft" href="<c:url value='/AdminController/updateProduct'><c:param name='productCode' value=''/></c:url>">Add Product</a>
                            </div>
                        </td>
                    </tr>
                </table>
                   
            </div>
            <br/>
        </div>
    </body>
</html>
