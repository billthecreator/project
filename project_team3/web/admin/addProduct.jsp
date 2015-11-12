<%-- 
    Document   : addProduct
    Created on : Nov 3, 2015, 2:31:43 PM
    Author     : William
--%>

<%@page import="music.data.ProductDB"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="mma" uri="/WEB-INF/murach.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <c:import url='/includes/header.jsp' />
        <style>
            .bar:before, .bar:after {
                background-color: ${pageColor};z-index: 10;
            }
            .errorBar:before, .errorBar:after {
                    background: red; 
                    width:50%;
                    z-index: 100;
            }
            
            .group input:focus ~ label		{
                color:${pageColor};
            }
            @-webkit-keyframes inputHighlighter {
                from { background:${pageColor}; }
                to   { width:0; background:transparent; }
            }
            @-moz-keyframes inputHighlighter {
                from { background:${pageColor}; }
                to   { width:0; background:transparent; }
            }
            @keyframes inputHighlighter {
                from { background:${pageColor}; }
                to   { width:0; background:transparent; }
            }
        </style>
    </head>
    <body>
        <div class="container">
            
            <div class="card withTitle">
                <form action="updateProduct" method="post" autocomplete="off">
                    <input type="hidden" name="productId" value="${product.getId()}">
                    <table class="noBorder noColor">
                        <tr class="cardTitle" style="background-color: ${pageColor};">
                            <td>
                                <div class="topActionBar">
                                    <!--<a style="background-color: ${pageAccentColor};" href="<c:url value='/loadProducts?action=displayProducts'/>" class="fLeft button" >View All Products</a>-->
                                    <a style="opacity:100;" href="admin/logout.jsp" class="fRight button neutral" >Log out</a>
                                </div>
                                <div class="title withImage"> 
                                    <h1>Product Editor</h1>
                                    <h2>Add or update an existing product</h2>
                                </div>
                                <c:if test="${product.getId() > 0 }">
                                <div class="albumCoverArt">
                                    <img class="coverArt" src="${product.getImageURL()}"/>
                                </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>                            
                            <td>
                                <div class="space50"><c:if test="${product.getId() > 0 && !prodError.anyErrors()}"><a href="<c:url value='/loadProducts?action=removeProduct&productCode=${product.code}' />" class="button neutral" >Delete</a></c:if></div>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${product.code == null || product.getCode().length() == 0}">
                                    <div class="group short">      
                                        <input id="materialInput" type="text" name="productCode" value="">
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Code</label>
                                        <c:if test="${prodError.codeError}">
                                            <span class="bar errorBar"></span>
                                            <span class="errorLabel">Code is required</span>
                                        </c:if>
                                        <c:if test="${prodError.codeError2}">
                                            <span class="bar errorBar"></span>
                                            <span class="errorLabel">This code already exists for another product/span>
                                        </c:if>
                                    </div>
                                    
                                    <%--<mma:ifEmptyMark  field=""/>--%>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <c:if test="${product.getCode().length() > 0}">
                                        
                                        <div class="group short">      
                                            <input id="materialInput" type="text" name="productCode" value="${product.code}" >
                                            <span class="highlight"></span>
                                            <span class="bar"></span>
                                            <label>Code</label>
                                            <c:if test="${prodError.codeError}">
                                                <span class="bar errorBar"></span>
                                                <span class="errorLabel">Code is required</span>
                                            </c:if>
                                            <c:if test="${prodError.codeError2}">
                                                <span class="bar errorBar"></span>
                                                <span class="errorLabel">This code already exists for another product</span>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group short">      
                                    <input id="materialInput" type="text" name="productArtist" value="${product.getArtistName()}">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Artist</label>
                                    <c:if test="${prodError.artistError}">
                                        <span class="bar errorBar"></span>
                                        <span class="errorLabel">An Artist name is required</span>
                                    </c:if>
                                </div>
                                    
                                <div class="group short">      
                                    <input id="materialInput" type="text" name="productAlbum" value="${product.getAlbumName()}">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Album</label>
                                    <c:if test="${prodError.albumError}">
                                        <span class="bar errorBar"></span>
                                        <span class="errorLabel">An Album name is required</span>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group">      
                                    <input id="materialInput" type="text" name="productCoverURL" value="${product.coverURL}">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Cover Art URL</label>
                                    
                                    <c:if test="${prodError.coverURLError}">
                                        <span class="bar errorBar"></span>
                                        <span class="errorLabel">Unable to download image</span>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group short">      
                                    <input id="materialInput" type="number" name="productPrice" value="${product.price}">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Price</label>
                                    <c:if test="${prodError.priceError}">
                                    <span class="bar errorBar"></span>
                                        <span class="errorLabel">A Price amount is required</span>
                                    </c:if>

                                    <c:if test="${prodError.priceError2}">
                                        <span class="bar errorBar"></span>
                                        <span class="errorLabel">A price amount must be numeric and can include 1 decimal</span>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                        <tr class="actionBar">
                            <td>
                                <input type="hidden" name="action" value="updateProduct"/>
                                <c:if test="${product.getId() == 0 || product.getId() == null}">
                                    <input style="background-color: ${pageColor};" class="mL10" type="submit" value="Add"/>
                                </c:if>
                                <c:if test="${product.getId() > 0}">
                                    <input style="background-color: ${pageColor};" class="mL10" type="submit" value="Update Product"/>
                                </c:if>
                                <a href="<c:url value='/loadProducts' />" class="button neutral" >Cancel</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <br/>
        </div>
    </body>
</html>
