<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
		得点管理システム
</c:param>

<c:param name="scripts"></c:param>

<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

<div class="container2">
<form action="/system/scoremanager/main/SubjectDeleteExecute.action" method="post">
<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<br><p>${subject.name}を削除してもよろしいですか</p>
</div>

<button type="submit" class="btn btn-danger">削除</button><br>
<a href="../main/subject_list.jsp">戻る</a>
</form>
</div>
</section>
</c:param>
</c:import>