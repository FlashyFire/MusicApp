<%--
  Created by IntelliJ IDEA.
  User: Надежда
  Date: 01.06.2018
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MusicApp</title>
    <link href="site.css" rel="stylesheet" type="text/css"/>
    <jsp:useBean id="data" class="me.ntab.music.AlbumsDataProvider" scope="session" />
<body>
<div class="row" style="padding: 12px 0">
    <div class="column title">Добавить трек</div>
</div>
<form action="newsong" method="POST" name="AddSongForm">
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Название:</label></div>
        <div class="column w60"><input type="text" name="title" size="50" required/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Исполнитель:</label></div>
        <c:choose>
            <c:when test="${not empty data.currentAlbum().getArtist()}">
                <div class="column w60">
                    <input type="text" name="artist" size="50" value="${data.currentAlbum().getArtist().getName()}" readonly/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="column w60"><input type="text" name="artist" size="50"/></div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Номер трека:</label></div>
        <div class="column w60"><input type="number" name="trackNum" size="20" min="1"/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Длительность:</label></div>
        <div class="column w60"><input type="text" name="duration" size="20"/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <input type="submit" class="button" value="Добавить">
        <input type="hidden" name="albumId" value="${pageContext.request.getParameter('id')}" />
    </div>
</form>
</body>
</html>
