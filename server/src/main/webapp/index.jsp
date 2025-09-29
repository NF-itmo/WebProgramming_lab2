<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="utils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="assets/img/logo.ico" type="image/x-icon"/>
    <title>Web lab2</title>
    
    <link rel="stylesheet" type="text/css" href="assets/css/themes/default.css">
    <link rel="stylesheet" type="text/css" href="assets/css/plot.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <link rel="stylesheet" type="text/css" href="assets/css/error.css">
    <link rel="stylesheet" type="text/css" href="assets/css/history.css">

    <script src="./js/plot.js"></script>
    <script src="./js/error.js"></script>
    <script src="./js/form.js"></script>
</head>
<body>
    <div class="container">
        <!-- Config section -->
        <c:set var="results" value="${sessionScope.results}"/>

        <!-- Шапка с ФИО, номером группы и вариантом-->
        <header class="header">
            <h1>Решетников Сергей Евгеньевич</h1>
            <h2>Группа: P3208</h2>
            <h2>Вариант: 583167</h2>
        </header>

        <div class="graph-container">
            <svg
                xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" 
                height="200px" width="200px"
                id="graph-svg"
                onclick="handleSvgClick(event)"
            >
                <!-- fill -->     

                <polygon class="svg-fill-area" points="100,100 180,100 100,20"></polygon> <!-- 1st quoter -->
                <path class="svg-fill-area" d="M 100 100 L 140 100 A40,40 0 0,1 100,140 Z"></path> <!-- 2nd quoter -->
                <rect x="60" y="20" width="40" height="80" class="svg-fill-area"></rect> <!-- 4th quoter -->

                <!-- axes -->     

                <g> <!-- X axis -->
                     <line class="svg-cord-line" x1="0" x2="200" y1="100" y2="100"></line>
                     <polygon class="svg-cord-arrow" points="200,100 190,104 190,96"></polygon>
                </g>
                <g> <!-- Y axis -->
                     <line class="svg-cord-line" x1="100" x2="100" y1="0" y2="200"></line>
                     <polygon class="svg-cord-arrow" points="100,0 96,10 104,10"></polygon>
                </g>     

                <!-- captions -->     

                <g> <!-- X axis -->
                     <text class="svg-caption" x="175" y="90">R</text>
                     <text class="svg-caption" x="132" y="90">R/2</text>
                     <text class="svg-caption" x="48" y="90">-R/2</text>
                     <text class="svg-caption" x="15" y="90">-R</text>     

                     <text class="svg-caption" x="190" y="120">X</text>
                </g>
                <g> <!-- Y axis -->
                     <text class="svg-caption" x="110" y="25">R</text>
                     <text class="svg-caption" x="110" y="65">R/2</text>
                     <text class="svg-caption" x="110" y="143">-R/2</text>
                     <text class="svg-caption" x="110" y="183">-R</text>     

                     <text class="svg-caption" x="85" y="10">Y</text>
                </g>     

                <!-- lines on cords --> 

                <g> <!-- X axis -->
                     <line class="svg-cord-line" x1="180" x2="180" y1="105" y2="95"></line>
                     <line class="svg-cord-line" x1="140" x2="140" y1="105" y2="95"></line>
                     <line class="svg-cord-line" x1="60" x2="60" y1="105" y2="95"></line>
                     <line class="svg-cord-line" x1="20" x2="20" y1="105" y2="95"></line>
                </g>
                <g> <!-- Y axis -->
                     <line class="svg-cord-line" x1="105" x2="95" y1="180" y2="180"></line>
                     <line class="svg-cord-line" x1="105" x2="95" y1="140" y2="140"></line>
                     <line class="svg-cord-line" x1="105" x2="95" y1="60" y2="60"></line>
                     <line class="svg-cord-line" x1="105" x2="95" y1="20" y2="20"></line>
                </g>
                
                <c:if test="${not empty results}">
                    <c:forEach var="res" items="${results}">
                            <circle data-x="${res.x}" data-y="${res.y}" data-r="${res.r}" class="point-on-plot"></circle>
                    </c:forEach>
                </c:if>
            </svg>
        </div>

        <!-- Форма с Grid вёрсткой -->
        <div class="form-container">
            <form id="graph-test-form" method="get" action="/lab" onsubmit="handleSubmit(event)">
                <div class="form-grid">
                    <!-- Первая строка -->
                    <div class="form-row">
                        <div class="row-label">
                            <p>Выберите X:</p>
                        </div>
                        <div class="row-input">
                            <input type="number" id="X" name="X" min="-5" max="3" value="0" step="any" 
                                   placeholder="Введите X" required>
                        </div>
                    </div>

                    <!-- Вторая строка -->
                    <div class="form-row">
                        <div class="row-label">
                            <p>Выберете Y:</p>
                        </div>
                        <div class="row-input">
                            <c:forEach var="idx" begin="0" end="8" step="1">
                                <c:set var="yVal" value="${idx/2 - 2}"/>
                                <div class="input-group">
                                    <input type="radio" id="Y${yVal}" name="Y" value="${yVal}" ${yVal==0 ? "checked" : ""}>
                                    <label for="Y${yVal}">${yVal}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Третья строка -->
                    <div class="form-row">
                        <div class="row-label">
                            <p>Выберете R:</p>
                        </div>
                        <div class="row-input">
                            <c:forEach var="rVal" begin="1" end="5" step="1">
                                <div class="input-group">
                                    <input type="radio" id="R${rVal}" name="R" value="${rVal}">
                                    <label for="R${rVal}">${rVal}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="submit-container">
                    <button type="submit" class="submit-btn">Отправить данные</button>
                </div>
            </form>
        </div>

        <!-- Таблица с историей данных -->
        <div class="history-section">
            <h3 class="history-title">История введенных данных</h3>
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
                        <c:choose>
                            <c:when test="${not empty results}">
                                <c:forEach var="res" items="${u:reverse(results)}">
                                    <tr>
                                        <td><fmt:formatDate type="both" value="${ res.time }"/></td>
                                        <td>${ res.elapsedTimeNs } ns</td>
                                        <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ res.x }"/></td>
                                        <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ res.y }"/></td>
                                        <td><fmt:formatNumber type="number"  maxFractionDigits="2" value="${ res.r }"/></td>
                                        <td>${ res.isHit() ? "Hit" : "Miss" }</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <td id="emptyHistory" class="empty-history" colspan="6">
                                    История пуста. Заполните форму, чтобы добавить данные.
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <div class="action-buttons">
                <!-- <button class="clear-btn" onclick="clearHistory()">Очистить историю</button> -->
            </div>
        </div>
    </div>

    <script>
        const error = new ErrorFactory("error-message");
        const form = new Form("graph-test-form")
        
        document.addEventListener("DOMContentLoaded", () => {
            const circles = document.getElementById("graph-svg").querySelectorAll("circle").forEach(c => {
                const x = parseFloat(c.dataset.x);
                const y = parseFloat(c.dataset.y);
                const r = parseFloat(c.dataset.r);

                const [cx, cy] = convertCheckerCordsToSvgCords(x, y, r);

                c.setAttribute("cx", cx);
                c.setAttribute("cy", cy);
                c.setAttribute("r", 3);
            });
        });

        const checkIfFormContainsAllValues = (X,Y,R) => {
            return new Promise((resolve, reject) => {
                if (isNaN(X)) reject("X отсутствует или имеет неврный формат")
                if (isNaN(Y)) reject("Y отсутствует или имеет неврный формат")
                if (isNaN(R)) reject("R отсутствует или имеет неврный формат")

                resolve([X,Y,R]);
            })
        };

        const validateCords = (X,Y,R) => {
            return new Promise((resolve, reject) => {
                if (X>3 || X<-5) reject("X не попадает в диапазон [-5;3]");
                if (Y>2 || Y<-2) reject("Y не попадает в диапазон [-2;2]");
                if (R>5 || R<1)  reject("R не попадает в диапазон [1;5]");

                if (Y%0.5 !== 0) reject("Y не соответствует шагу 0.5");
                if (R%1 !== 0)   reject("R не соответствует шагу 1");

                resolve([X,Y,R]);
            })
        };

        const handleSubmit = (event) => {
            event.preventDefault();

            const formElem = document.forms["graph-test-form"];

            const X = Number(formElem["X"].value);
            const Y = Number(formElem["Y"].value);
            const R = Number(formElem["R"].value);

            checkIfFormContainsAllValues(X,Y,R).then(
                result => validateCords(...result).then(
                    result => formElem.submit(),
                    errorMsg => error.showError(errorMsg)
                ),
                errorMsg => error.showError(errorMsg)
            )
        }

        const handleSvgClick = (event) => {
            handlePlotClick(event).then(
                result => {
                    validateCords(...result).then(
                        result => form.send(...result),
                        errorMsg => error.showError(errorMsg)
                    )
                },
                errorMsg => error.showError(errorMsg)
            )
        }
    </script>
</body>
</html>