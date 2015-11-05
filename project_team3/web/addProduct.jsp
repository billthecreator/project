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
                            <td class="right"><b>Code:</b></td>
                            <td>
                                <c:if test="${product.code == null}">
                                    <input required="yes" placeholder="ab01" type="text" name="productCode" value=""/>
                                </c:if>
                                <c:if test="${product.code != null}">
                                    <input type="hidden" name="productCode" value="${product.code}"/>
                                    <div class="noInput">${product.code}</div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="right"><b>Artist:</b></td>
                            <td><input required="yes" placeholder="86 (the band)" type="text" name="productArtist" value="${product.getArtistName()}"/></td>
                        </tr>
                        <tr>
                            <td class="right"><b>Album:</b></td>
                            <td><input required="yes" placeholder="True Life Songs and Pictures" type="text" name="productAlbum" value="${product.getAlbumName()}"/></td>
                        </tr>
                        <tr>
                            <td class="right"><b>Price:</b></td>
                            <td><input required="yes" placeholder="10.00" type="text" name="productPrice" value="${product.price}"/></td>
                        </tr>
                        <tr>
                            <td>
    <!--                          ): -->
                            </td>
                            <td>

                                <c:if test="${e !=null}">
                                    <div class="errorMessage">Price must be numeric.</div>
                                </c:if>
                            </td>

                        </tr>

                        <tr>
                            <td></td>
                            <td>
                                <input type="hidden" name="action" value="updateProduct"/>
                                <c:if test="${product.code == null}">
                                <input type="submit" value="Add Product"/>
                                </c:if>
                                <c:if test="${product.code != null}">
                                <input type="submit" value="Update Product"/>
                                <a href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='removeProduct'/>
                                       <c:param name='productCode' value='${product.code}'/>
                                   </c:url>" class="button neutral">Delete Product</a>
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
