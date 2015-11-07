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
        <c:import url='includes/header.html' />
    </head>
    <body>
        <div class="container">
            <div class="header">
                <div class="block"><h1>Product</h1></div>
                <div class="block">
                    <form action="loadProducts">
                        <input type="hidden" name="action" value="displayProducts"/>
                        <input type="submit" value="View Products"/>
                    </form>
                </div>
            </div>
            
            <div class="card">
                <form action="updateProduct" method="post">
                    <table class="noBorder noColor">
                        <tr>
                            <td colspan="2">                                    
                                <c:if test="${product.code == null || product.getCode().length() == 0 || product.price <= 0 || product.price == null || product.getArtistName().length() == 0 || product.getAlbumName().length() == 0}">
                                    <div class="message info"><i class="fa fa-info-circle"></i><mma:ifEmptyMark  field=""/> Marks required fields.</div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="colRes right" width="20"><b>Code:</b></td>
                            <td>
                                <c:if test="${product.code == null}">
                                    <input placeholder="ab01" type="text" name="productCode" value=""/>
                                    <mma:ifEmptyMark  field=""/>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <c:if test="${product.getCode().length() == 0}">
                                        <input placeholder="ab01" type="text" name="productCode" value=""/>
                                        <mma:ifEmptyMark  field=""/>
                                    </c:if>
                                    <c:if test="${product.getCode().length() > 0}">
                                        <input type="hidden" name="productCode" value="${product.code}"/>
                                        <div class="noInput">${product.code}</div>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="colRes right"><b>Artist:</b></td>
                            <td>
                                <input placeholder="86 (the band)" type="text" name="productArtist" value="${product.getArtistName()}"/>
                                <mma:ifEmptyMark  field="${product.getArtistName()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="colRes right"><b>Album:</b></td>
                            <td>
                                <input placeholder="True Life Songs and Pictures" type="text" name="productAlbum" value="${product.getAlbumName()}"/>
                                <mma:ifEmptyMark  field="${product.getAlbumName()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="colRes right"><b>Price:</b></td>
                            <td>
                                <c:if test="${product.price <= 0 || product.price == null}">
                                    <input placeholder="10.00" type="text" name="productPrice" value=""/>
                                    <mma:ifEmptyMark  field=""/>
                                </c:if>
                                <c:if test="${product.price > 0}">
                                    <input placeholder="10.00" type="text" name="productPrice" value="${product.price}"/>
                                    <mma:ifEmptyMark  field="${product.price}"/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">

                                <c:if test="${e != null}">
                                    <div class="error message">${message}</div>
                                </c:if>
                            </td>

                        </tr>

                        <tr>
                            <td colspan="2">
                                <input type="hidden" name="action" value="updateProduct"/>
                                <c:if test="${product.code == null}">
                                    <input class="mL10" type="submit" value="Add"/>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <input class="mL10" type="submit" value="Update"/><c:if test="${e == null}"><a href="<c:url value='/loadProducts?action=removeProduct&productCode=${product.code}' />" class="button neutral" >Delete</a></c:if>
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
