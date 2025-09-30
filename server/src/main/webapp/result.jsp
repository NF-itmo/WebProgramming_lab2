<%@ page import="org.web2.model.ResultBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/img/logo.ico" type="image/x-icon"/>
    <title>Web lab2</title>
    
    <link rel="stylesheet" type="text/css" href="assets/css/themes/default.css">

    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <link rel="stylesheet" type="text/css" href="assets/css/error.css">
    <link rel="stylesheet" type="text/css" href="assets/css/history.css">
</head>
<body>
    <!-- Config section -->
    <c:set var="result" value="${sessionScope.lastResult}"/>    

    <div class="container"> 
        <!-- Шапка с ФИО, номером группы и вариантом-->
        <header class="header">
            <h1>Решетников Сергей Евгеньевич</h1>
            <h2>Группа: P3208</h2>
            <h2>Вариант: 583167</h2>
        </header>

        <!-- Таблица с историей данных -->
        <div class="history-section">
            <h3 class="history-title">Результат теста</h3>
            <div class="history-table-container">
                <table class="history-table">
                    <thead>
                        <tr>
                            <th>Дата и время</th>
                            <th>Затраченное время</th>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Результат</th>
                        </tr>
                    </thead>
                        <tbody id="historyTableBody">
                            <tr>
                                <td><fmt:formatDate type="both" value="${ result.time }"/></td>
                                <td>${ result.elapsedTimeNs } ns</td>
                                <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ result.x }"/></td>
                                <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ result.y }"/></td>
                                <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ result.r }"/></td>
                                <td>${ result.isHit() ? "Hit" : "Miss" }</td>
                            </tr>
                        </tbody>
                </table>
            </div>

            <div class="action-buttons">
                <a href="/">
                    <button class="clear-btn">Назад</button>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
