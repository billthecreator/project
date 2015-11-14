<%@page import="com.sun.xml.internal.ws.util.StringUtils"%>
<div class="user">Welcome, <%=StringUtils.capitalize(request.getRemoteUser())%>!</div>
<a style="opacity:100;" href="admin/logout.jsp" class="fRight button neutral flat" >Log out</a>
                               