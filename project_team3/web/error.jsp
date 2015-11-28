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
        <c:import url='//includes/header.jsp' />
    </head>
    <body style=" background:url('${pageContext.request.contextPath}/images/HQQdE9e.jpg');background-repeat: no-repeat;background-position: right top;background-color: #070707;">
        <div class="container">
            <div class="card withTitle">
                <table class="noBorder noColor">
                    <tr class="cardTitle" style="background-color:<%= palette.errorPrimary500 %>;">
                        <td colspan="5">
<!--                            <div class="actionBar">
                                <%--<c:import url="/includes/welcome.jsp"/>--%>
                            </div>-->
                            <div class="title">    
                                <h1>404 <span style="font-size:.9em; color:rgba(255,255,255,.87);">The <span style="font-weight:bold;color: white;text-transform: uppercase;">page</span> is <span style="font-weight:bold;color: white;text-transform: uppercase;">wrong</span> with this one!</span></h1>
                                <h2>The requested URL <span style="font-weight:bold;color:white;">${pageContext.errorData.requestURI}</span> was not found on this server.</h2>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="padding:0;">
                            <div class="actionBar">
                                <a style="background-color: <%= palette.errorPrimary500 %>; color:white;" class="button fLeft" href="<c:url value='/AdminController/dashboard'/>">Back to the death star</a>
                            </div>
                        </td>                           
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
