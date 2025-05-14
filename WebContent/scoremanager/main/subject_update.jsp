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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				<div class="container2">
<form action="SubjectUpdateExecute.action" method="post">
<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">
科目コード<br>

<p>${subject.cd}</p><br>
				科目名<br>
<input type="text" name="name" class="form-input"  value="${subject.name}" required="required">

<br>
</div>
<input type="submit" value="変更"><br>
<a href="../main/subject_list.jsp">戻る</a>
</form>
</div>
</section>
</c:param>
</c:import>