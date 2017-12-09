<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="helpful.CookieUtils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Главная страничка</title>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<link rel="shortcut icon" type="image/gif" href="images/icon.gif" />
</head>

<!-- //here i had an awful mistake: if my checkbox isnt ticked, i have an
	exception here - he is not even in request -->


<c:if test="${remember}">
	<%
		CookieUtils.demoUserCookie(request, response, out);
			System.out.println("I was asked to remember that user");
	%>
</c:if>
<body>
	<div id="page">
		<%@ include file="header.jsp"%>
		<div class="content-text">
			<ul id="pages_list">
				<li><a href="Песочница.html" target="_blank"> Песочница </a>-
					Самая первая страница, где были попробованы разные элементы и есть
					нерабочая опросная форма.</li>
				<li><a href="статья о животных.html" target="_blank">В мире
						животных</a> - Страница с текстом википедии, на которой я училась
					плавающим элементам и подобному.</li>
				<li><a href="Кошка.html" target="_blank">О котиках</a> -
					Разбиение на колонки (изначально это плавающие элементы, потом я
					заменила их на колонки bootstrap). Википедия. Ничего интересного.</li>
				<li><a href="scripts" target="_blank">Скрипты</a> - Первая
					реально интересная страница, содержащая в себе таймер с кнопочками
					управления и тест с удивительными фактами (основан на форме с
					вопросами radiobuttons).</li>
				<li><a href="Animation.html" target="_blank">Анимация</a> -
					Изначально я хотела поставить ее на другие страницы так, чтобы
					бублики запускались при отсутствии движения мышью, но потом поняла,
					что это будет отвлекать от очень интересного информативного
					контента. Может, я сделаю запуск бубликов только при отсутствии
					движения на анимационной странице чуть позже, будем посмотреть.</li>
				<li><a href="Flexpage.html" target="_blank">Flex</a> -
					Страничка, сверстанная с помощью flex. Наполнение- логические
					парадоксы)</li>
				<li><a href="Bootstrappage.html" target="_blank">Bootstrap</a>
					- Страничка, на которой я поигралась в Bootstrap. Да, он немного
					применен и в прочих вышеприведенных страницах, но здесь его куда
					больше. Эта страничка адаптирована под разные экраны и содержит
					текст о миграции морских котиков)</li>
				<li><a href="currencies" target="_blank"> Справочник валют</a>
					- Страница с валютами, запрашиваемыми у центробанка</li>
			</ul>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>