<%-- 
    Document   : deleteProduct
    Created on : Nov 4, 2015, 11:20:47 AM
    Author     : William
--%>

<%@page import="com.sun.xml.internal.ws.util.StringUtils"%>
<%@page import="music.color.ColorPalette"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <c:import url='/includes/header.jsp' />
        
        <%
           ColorPalette palette = new ColorPalette();
        %>
        
        <meta name="theme-color" content="<%= palette.defaultPrimary500 %>" />
        <style>
            .bar:before, .bar:after {
                background-color: <%= palette.defaultPrimary500 %>;z-index: 10;
            }
            .errorBar:before, .errorBar:after {
                    background: red; 
                    width:50%;
                    z-index: 100;
            }
            
            .group input:focus ~ label		{
                color:<%= palette.defaultPrimary500 %>;
            }
            @-webkit-keyframes inputHighlighter {
                from { background:<%= palette.defaultPrimary500 %>; }
                to   { width:0; background:transparent; }
            }
            @-moz-keyframes inputHighlighter {
                from { background:<%= palette.defaultPrimary500 %>; }
                to   { width:0; background:transparent; }
            }
            @keyframes inputHighlighter {
                from { background:<%= palette.defaultPrimary500 %>; }
                to   { width:0; background:transparent; }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card withTitle">
                <table class="noBorder noColor">
                    <tr class="cardTitle" style="background-color: <%= palette.defaultPrimary500 %>;">
                        <td colspan="5">
                            <div class="title">    
                                <h1>Product Manager</h1>
                                <h2>What do you want to do, <%=StringUtils.capitalize(request.getRemoteUser())%>?</h2>
                            </div>
                        </td>
                    </tr>
                    <tr class="actionBar">
                        <td>
                            <div class="space50"></div>
                            <a href="<c:url value='/loadProducts?action=displayProducts'/>" class="button neutral" >View All Products</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>