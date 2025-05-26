<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<h5 class="h6 mb-3 fw-norma bg-success bg-opacity-10 py-2 px-4 text-center">登録が完了されました</h5>
			<div class="container2">
				<br>
				<br>
				<br>
				<a href="/system/scoremanager/main/TestRegist.action">戻る</a>
				<a href="/system/scoremanager/main/TestList.action">成績参照</a>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
			</div>
		</section>
	</c:param>
</c:import>