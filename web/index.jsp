<%--
  Created by IntelliJ IDEA.
  User: Надежда
  Date: 27.05.2018
  Time: 19:05
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
    <div class="widerow" style="padding: 12px 0">
      <div class="column title">Альбомы</div>
    </div>
    <div class="widerow" style="padding: 12px 0">
      <div class="column w40 wideheader">Альбом</div>
      <div class="column w40 wideheader">Исполнитель</div>
      <div class="column w20 tcenter wideheader">Год</div>
    </div>
    <c:forEach items="${data.getAlbumList()}" var="item">
      <div class="widerow" style="padding: 12px 0">
        <div class="column w40">
            <a href="album.jsp?id=${item.getAlbumId()}">${item.getTitle()}</a>
        </div>
        <c:choose>
            <c:when test="${not empty item.getArtist()}">
                <div class="column w40">${item.getArtist().getName()}</div>
            </c:when>
            <c:otherwise>
                <div class="column w40">-</div>
            </c:otherwise>
        </c:choose>
        <div class="column w20 tcenter">${item.getYear()}</div>
      </div>
    </c:forEach>
  </body>
</html>
