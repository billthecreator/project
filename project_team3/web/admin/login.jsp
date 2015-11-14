<%-- 
    Document   : index
    Created on : Oct 26, 2015, 4:50:53 PM
    Author     : William
--%>

<%@page import="music.color.ColorPalette"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <c:import url='../includes/header_nojs.jsp' />
        
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
            <form action="j_security_check" method="get" autocomplete="off">                
                <div class="card withTitle w50">
                    <table class="noBorder noColor"cellspacing="0">
                        <tr class="cardTitle" style="background-color: <%= palette.defaultPrimary500 %>;">
                            <td colspan="1">
                                <div class="title">    
                                    <h1>Log in</h1>
                                    <h2>Please enter a username and password to continue</h2>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="space50"></div>
                                <div class="group noLabel">    
                                    <input id="materialInput" type="text" name="j_username" value="" placeholder="Username">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group noLabel">    
                                    <input id="materialInput" type="password" name="j_password" value="" placeholder="Password">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="actionBar">
                                <input class="button neutral" type="submit" value="Log in"/>
                            </td>
                        </tr>
                     </table>
                </div>
            </form>
        </div>
    </body>
</html>