<%@ page import="me.ntab.core.AlbumsEntity" %><%--
  Created by IntelliJ IDEA.
  User: Надежда
  Date: 30.05.2018
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MusicApp</title>
    <link href="site.css" rel="stylesheet" type="text/css" />
    <jsp:useBean id="data" class="me.ntab.core.DataProvider" scope="session" />
</head>
<body>
<%
    data.fetchAlbum(request.getParameter("id"));
%>
    <div class="widerow" style="padding: 12px 0">
        <div class="column title">${data.currentAlbum().getTitle()}</div>
        <div class="column title tright">${data.currentAlbum().getArtist().getName()}</div>
    </div>
    <div class="widerow" style="padding: 12px 0">
        <div class="column w20 wideheader">Трек</div>
        <div class="column w60 wideheader">Композиция</div>
        <div class="column w20 tcenter wideheader">Длительность</div>
    </div>
<c:forEach items="${data.currentAlbum().orderedSongs()}" var="item">
    <div class="widerow" style="padding: 12px 0">
        <div class="column w20">${item.getTrackNum()}</div>
        <div class="column w60">${item.getTitle()}</div>
        <div class="column w20 tcenter">${item.durationString()}</div>
    </div>
</c:forEach>
</body>
</html>
