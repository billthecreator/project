<%-- 
    Document   : index
    Created on : Oct 26, 2015, 4:50:53 PM
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
                <div class="block"><h1>Product Maintenance</h1></div>
                
                <div class="block">
                    <form action="loadProducts">
                        <input type="hidden" name="action" value="displayProducts"/>
                        <input type="submit" value="View Products"/>
                    </form>
                </div>
            </div>
            <br>
            
        </div>
    </body>
</html>
<%--
Adding this comment to test commit
--%>