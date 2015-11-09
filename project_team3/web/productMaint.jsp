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
        <c:import url='includes/header.jsp' />
    </head>
    <body>
        <div class="container">
            <div class="card withTitle">
                <table cellspacing="0">
                    <tr class="cardTitle" style="background-color: ${pageColor};">
                        <td colspan="6">
                            <div class="title">    
                                <h1>Product Manager</h1>
                                <h2>A list of artists' albums</h2>
                                <form action="loadProducts">
                                    <input type="hidden" name="productCode" value=""/>
                                    <input type="hidden" name="action" value="addProduct"/>
                                    <input style="background-color: ${pageAccentColor};" type="submit" value="Add a product"/>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th class="mobHide center">Code</th>
                        <th></th>
                        <th colspan="1" data-name="album">Album</th>
                        <th class="right" data-name="price"><span class="iconWord">Price</span><i class="fa fa-usd wordIcon"></i></th>
                        <th class="center mobHide"><span class="iconWord"></span><i class="fa fa-pencil wordIcon"></i></th>
                        <th class="center mobHide"><span class="iconWord"></span><i class="fa fa-ban wordIcon"></i></th>
                    </tr>
                    <%@ taglib uri="/WEB-INF/murach.tld" prefix="mma" %>
                    <mma:product>        
                        <tr>
                            <td class="code center colRes mobHide">${productCode}</td>
                            <td class="colRes" data-name="album">
                                <a class="coverArtLink" href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='addProduct'/>
                                       <c:param name='productCode' value='${productCode}'/>
                                                         </c:url>">
                                    <img class="coverArt" src="${productCover}"/>
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>
                            </td>
                            <td class="description">
                                <div class="artist">${productArtist}</div>
                                <div class="album">${productAlbum}</div></td>
                            <td class="price right" data-name="price">${productPrice}</td>
                            <td class="center colRes mobHide"><a href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='addProduct'/>
                                       <c:param name='productCode' value='${productCode}'/>
                                                         </c:url>"><span class="iconWord">Edit</span><i class="fa fa-ellipsis-v wordIcon" title="Edit or delete this item"></i></a></td>
                            <td class="center colRes mobHide">
                                <a href="<c:url value='/loadProducts' >
                                       <c:param name='action' value='removeProduct'/>
                                       <c:param name='productCode' value='${productCode}'/>
                                   </c:url>">Delete</a></td>
                        </tr>
                    </mma:product>
                </table>
                    
                <div class="actionButton">
                    <form action="loadProducts">
                        <input type="hidden" name="productCode" value=""/>
                        <input type="hidden" name="action" value="addProduct"/>
                        <input style="background-color: ${pageAccentColor};" type="submit" value="+"/>
                    </form>
                </div>
            </div>
            <br/>
        </div>
    </body>
</html>
