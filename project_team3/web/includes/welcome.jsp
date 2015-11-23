<%@page import="com.sun.xml.internal.ws.util.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <div class="user">Welcome, <%=StringUtils.capitalize(request.getRemoteUser())%>!</div>
                                <a style="opacity:100;" href="<c:url value='/AdminController/logout'/>" class="fRight button neutral flat" >Log out</a>        