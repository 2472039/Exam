<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
			得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<h5 class="h6 mb-3 fw-norma bg-success bg-opacity-10 py-2 px-4 text-center" >変更が完了しました</h5>
			<br>
			<a href="/system/scoremanager/main/SubjectList.action">科目一覧</a>
		</section>
	</c:param>
</c:import>