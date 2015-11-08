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
        <c:import url='includes/header.jsp' />
    </head>
    <body>
        <div class="container">
<!--            <div class="header">
                <div class="block"><h1>Are you sure you want to delete this product?</h1></div>
            </div>-->
            <div class="card withTitle w50">
                <table class="noBorder noColor">
                    <tr class="cardTitle" style="background-color: ${pageColor};">
                        <td colspan="5">
                            <div class="title">    
                                <h1>Product deletion</h1>
                                <h2>Are you sure you want to delete this product?</h2>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th class="colRes center">Code</th>
                        <th></th>
                        <th data-name="album">Album</th>
                        <th class="right" data-name="price"><span class="iconWord">Price</span><i class="fa fa-usd wordIcon"></i></th>
                        <th class="mobHide colRes"></th>
                    </tr>
                    <tr>
                        <td class="code center colRes">${product.code}</td>
                        <td class="colRes" data-name="album">
                            <img class="coverArt" src="${product.getImageURL()}"/>
                        </td>
                        <td class="description">
                            <div class="artist">${product.getArtistName()}</div>
                            <div class="album">${product.getAlbumName()}</div>
                        </td>
                        <td class="price right" data-name="price">${product.getPriceCurrencyFormat()}</td>
                        <td class="mobHide colRes"></td>
                    </tr>
                    <tr>
                        <td colspan="5"><div class="separator"></div></td>
                    </tr>
                    <td colspan="5">
                        <form action="deleteProduct" method="post">
                            <input type="hidden" name="productCode" value="${product.code}"/>
                            <input type="hidden" name="productDesc" value="${product.description}"/>
                            <input type="hidden" name="productPrice" value="${product.price}"/>
                            <input type="hidden" name="action" value="confirmDeletion"/>
                            <input style="background-color: ${pageAccentColor};" type="submit" class="mL10 fLeft" value="Yes"/>
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
