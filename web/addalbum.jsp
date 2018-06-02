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
    <jsp:useBean id="date" class="java.util.Date" />
    <fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<body>
<div class="row" style="padding: 12px 0">
    <div class="column title">Добавить альбом</div>
</div>
<form action="newalbum" method="POST" name="AddAlbumForm">
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Название:</label></div>
        <div class="column w60"><input type="text" name="title" size="50" required/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Исполнитель:</label></div>
        <div class="column w60"><input type="text" name="artist" size="50"/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Жанр:</label></div>
        <div class="column w60"><input type="text" name="genre" size="50"/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <div class="column w40"><label>Год:</label></div>
        <div class="column w60"><input type="number" name="year" size="20" min="1900" max="${currentYear}"/></div>
    </div>
    <div class="row" style="padding: 12px 0">
        <input type="submit" class="button" value="Добавить">
    </div>
</form>
</body>
</html>
