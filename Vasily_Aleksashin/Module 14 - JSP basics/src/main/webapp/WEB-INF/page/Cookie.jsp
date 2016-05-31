<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en" >
<head >
	<meta charset="UTF-8" >
	<title >Cookies</title >
	<link rel="icon" href="${pageContext.request.contextPath}/view/res/cookies-icon.png" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/main.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/css/admin.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/lib/jquery-2.2.3.min.js" ></script >
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/lib/jquery.cookie.js" ></script >
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/script/admin.js" ></script >
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/script/general.js" ></script >
</head >
<body >
<br />
<br />
<br />
<div class="container table-admin" >
	<div class="select table-admin" >
		<h1 class="title" >Admin console<c:if test="${not empty cookie.name}" >, ${cookie["name"].value}</c:if ></h1 >
		<c:choose >
			<c:when test="${not empty cookie.name}" >
				<div class="button-left button-margin spain-block" >
					<button class="add-button" >Add row</button >
				</div >
				<div class="button-right button-margin spain-back" >
					<button class="back-button" >Back</button >
				</div >
				<p class="alert text" ></p >
				<br />
				<table class="table" >
					<tr >
						<th class="cookie-msg" >Cookie</th >
						<th class="lucky-number" >Lucky number</th >
						<th ></th >
						<th ></th >
					</tr >
					<c:forEach items="${cookieList}" var="element" >
						<tr class="added" >
							<td class="text" >
								<c:out value="${element.cookieMessage}" />
							</td >
							<td class="text" >
								<c:out value="${element.luckyNumber}" />
							</td >
							<td class="edit" >
								<button class="edit-button" >Edit</button >
							</td >
							<td class="del" >
								<button class="delete-button" >Delete</button >
							</td >
						</tr >
					</c:forEach >
				</table >
				<c:if test="${count > limit}" >
					<div class="pagination" >
						<c:if test="${offset > 0}" >
							<form class="spain-block" method="get" action="form_admin" >
								<input type="hidden" name="offset" value="${offset}" />
								<input type="hidden" name="move" value="prev" />
								<input type="submit" value="Prev" />
							</form >
						</c:if >
						<c:set var="pageInDouble" value="${(offset) / limit + 1}" />
						<c:set var="page" value="${pageInDouble - pageInDouble % 1}" />
						<c:set var="allPageInDouble" value="${(count - 1) / limit + 1}" />
						<c:set var="allPage" value="${allPageInDouble - allPageInDouble % 1}" />
						<p class="page-count spain-block" >
							<fmt:formatNumber value="${page}" pattern="#" />
							<c:out value="/" />
							<fmt:formatNumber value="${allPage}" pattern="0" />
						</p >
						<c:if test="${offset < (count - limit)}" >
							<form class="spain-block" method="get" action="form_admin" >
								<input type="hidden" name="offset" value="${offset}" />
								<input type="hidden" name="move" value="next" />
								<input type="submit" value="Next" />
							</form >
						</c:if >

					</div >
				</c:if >
			</c:when >
			<c:otherwise >
				<div >
					<p class="text spain-block" >
						<c:out value="Only authorized users can work here" />
					</p >
					<div class="button-right spain-back" >
						<button class="back-button" >Back</button >
					</div >
				</div >
			</c:otherwise >
		</c:choose >
		<br />
		<br />
	</div >
</div >
</body >
</html >
