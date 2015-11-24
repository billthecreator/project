<%-- 
    Document   : deleteProduct
    Created on : Nov 4, 2015, 11:20:47 AM
    Author     : William
--%>

<%@page import="music.color.ColorPalette"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <%
        ColorPalette palette = new ColorPalette();
     %>
    <head>
        <c:import url='/includes/header.jsp' />
    </head>
    <body>
        <div class="container">
            <div class="card withTitle">
                <table class="noBorder noColor">
                    <tr class="cardTitle" style="background-color:<%= palette.errorPrimary500 %>;">
                        <td colspan="5">
<!--                            <div class="actionBar">
                                <%--<c:import url="/includes/welcome.jsp"/>--%>
                            </div>-->
                            <div class="title">    
                                <h1>404</h1>
                                <h2>The requested URL ${pageContext.errorData.requestURI} was not found on this server.</h2>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
