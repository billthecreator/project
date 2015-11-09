<%-- 
    Document   : addProduct
    Created on : Nov 3, 2015, 2:31:43 PM
    Author     : William
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="mma" uri="/WEB-INF/murach.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <c:import url='includes/header.jsp' />
        <style>
            .bar:before, .bar:after {
                background-color: ${pageAccentColor};z-index: 10;
            }
            .errorBar:before, .errorBar:after {
                    background: red; 
                    width:50%;
                    z-index: 100;
            }
            @-webkit-keyframes inputHighlighter {
                from { background:${pageAccentColor}; }
                to   { width:0; background:transparent; }
            }
            @-moz-keyframes inputHighlighter {
                from { background:${pageAccentColor}; }
                to   { width:0; background:transparent; }
            }
            @keyframes inputHighlighter {
                from { background:${pageAccentColor}; }
                to   { width:0; background:transparent; }
            }
        </style>
    </head>
    <body>
        <div class="container">
            
            <div class="card withTitle">
                <form action="updateProduct" method="post">
                    <table class="noBorder noColor">
                        <tr class="cardTitle" style="background-color: ${pageColor};">
                            <td colspan="1">
                                <div class="title">    
                                    <h1>Product Editor</h1>
                                    <h2>Add or update an existing product</h2>
                                    <a style="background-color: ${pageAccentColor};" href="<c:url value='/loadProducts?action=displayProducts'/>" class="button" >View All Products</a><c:if test="${product.code != null && !prodError.anyErrors()}"><a href="<c:url value='/loadProducts?action=removeProduct&productCode=${product.code}' />" class="button neutral" >Delete</a></c:if>
                                </div>
                                <div class="albumCoverArt">
                                    <img class="coverArt" src="${product.getImageURL()}"/>
                                </div>
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
                                    </div>
                                    
                                    <%--<mma:ifEmptyMark  field=""/>--%>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <c:if test="${product.getCode().length() > 0}">
                                        <input type="hidden" name="productCode" value="${product.code}"/>
                                        
                                        <div class="group short">      
                                            <input id="materialInput" type="text" name="productCode" value="${product.code}" disabled="true">
                                            <span class="highlight"></span>
                                            <span class="bar"></span>
                                            <label>Code</label>
                                            <c:if test="${prodError.codeError}">
                                                <span class="bar errorBar"></span>
                                                <span class="errorLabel">Code is required</span>
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
                                <c:if test="${product.price <= 0 || product.price == null}">
                                    <div class="group short">      
                                        <input id="materialInput" type="text" name="productPrice" value="">
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
                                </c:if>
                                <c:if test="${product.price > 0}">
                                    <div class="group short">      
                                        <input id="materialInput" type="text" name="productPrice" value="${product.price}">
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Price</label>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr class="actionBar">
                            <td colspan="2">
                                <input type="hidden" name="action" value="updateProduct"/>
                                <c:if test="${product.code == null}">
                                    <input style="background-color: ${pageColor};" class="mL10" type="submit" value="Add"/>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <input style="background-color: ${pageColor};" class="mL10" type="submit" value="Update Product"/>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <br/>
        </div>
    </body>
</html>
