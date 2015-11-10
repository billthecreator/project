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
        <c:import url='includes/header.jsp' />
        
        <%
           ColorPalette palette = new ColorPalette();
        %>
        
        <meta name="theme-color" content="<%= palette.defaultPrimary500 %>" />
        <style>
            .bar:before, .bar:after {
                background-color: <%= palette.defaultSecondary500 %>;z-index: 10;
            }
            .errorBar:before, .errorBar:after {
                    background: red; 
                    width:50%;
                    z-index: 100;
            }
            @-webkit-keyframes inputHighlighter {
                from { background:<%= palette.defaultSecondary500 %>; }
                to   { width:0; background:transparent; }
            }
            @-moz-keyframes inputHighlighter {
                from { background:<%= palette.defaultSecondary500 %>; }
                to   { width:0; background:transparent; }
            }
            @keyframes inputHighlighter {
                from { background:<%= palette.defaultSecondary500 %>; }
                to   { width:0; background:transparent; }
            }
        </style>
    </head>
    <body>
        <div class="container">
                
                
                <div class="card withTitle">
                    <table class="noBorder noColor"cellspacing="0">
                        <tr class="cardTitle" style="background-color: <%= palette.defaultPrimary500 %>;">
                            <td colspan="1">
                                <div class="title">    
                                    <h1>Product Maintenance</h1>
                                    <h2>Log in to the Product Manager</h2>
                                    
                                    
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group short noLabel">    
                                    <input id="materialInput" type="text" name="userName" value="admin" placeholder="Username">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <!--<label>Username</label>-->
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="group short noLabel">    
                                    <input id="materialInput" type="password" name="password" value="" placeholder="Password">
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <!--<label>Password</label>-->
                                </div>
                            </td>
                        </tr>
                        <tr class="actionBar">
                            <th>
                                <form action="loadProducts">
                                    <input type="hidden" name="action" value="displayProducts"/>
                                    <input style="background-color:<%= palette.defaultSecondary500 %>;" class="mL10" type="submit" value="Log in"/>
                                </form>
                            </th>
                        </tr>
                     </table>
                </div>
                
            
        </div>
    </body>
</html>