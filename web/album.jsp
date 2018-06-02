<%--
  Created by IntelliJ IDEA.
  User: Надежда
  Date: 30.05.2018
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="me.ntab.music.entites.AlbumsEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MusicApp</title>
    <link href="site.css" rel="stylesheet" type="text/css" />
    <jsp:useBean id="data" class="me.ntab.music.AlbumsDataProvider" scope="session" />
</head>
<body>
    <% data.fetchAlbum(request.getParameter("id")); %>
    <c:set var="albumId" value="${pageContext.request.getParameter('id')}" />
    <c:set var="artistName" value="${data.currentAlbum().getArtist().getName()}" />
    <div class="widerow" style="padding: 12px 0">
        <div class="column w80 title">${data.currentAlbum().getTitle()}
            <c:if test="${not empty artistName}">
                &nbsp;/&nbsp;${artistName}
            </c:if>
        </div>
        <div class="column w20">
            <button class="button right" onclick="window.location='addsong.jsp?id=${albumId}';">
                Добавить трек
            </button>
        </div>
    </div>
    <div class="widerow" style="padding: 12px 0">
        <div class="column w20 wideheader">Трек</div>
        <div class="column w60 wideheader">Композиция</div>
        <div class="column w20 tcenter wideheader">Длительность</div>
    </div>
<c:forEach items="${data.currentAlbum().orderedSongs()}" var="item">
    <div class="widerow" style="padding: 8px 0">
        <c:choose>
            <c:when test="${not empty item.getTrackNum()}">
                <div class="column w20">${item.getTrackNum()}</div>
            </c:when>
            <c:otherwise>
                <div class="column w20">-</div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty artistName or empty item.getArtist()}">
                <div class="column w60">${item.getTitle()}</div>
            </c:when>
            <c:otherwise>
                <div class="column w60">${item.getTitle()}&nbsp;/&nbsp;${item.getArtist().getName()}</div>
            </c:otherwise>
        </c:choose>
        <div class="column w20 tcenter">${item.durationString()}</div>
    </div>
</c:forEach>
</body>
</html>
