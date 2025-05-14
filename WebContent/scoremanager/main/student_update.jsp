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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
			<%@page contentType="text/html; charset=UTF-8" %>
				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				<div class="container2">
				<form action="/system/scoremanager/main/StudentUpdateExecute.action" method="post">
				<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				入学年度<br>
				<p>${ent_year}</p><br>
				学生番号<br>
				<p>${no}</p><br>
				氏名　<br>
				<input type="text" name="inputName" value="${student.name}" required="required"><br>
				クラス<br>
				<select name="inputClassNum">
				<c:forEach var="p" items="${class_num_set}">
					<option value="${p}">${p}</option>
				</c:forEach>
				</select><br>
				<label>
				<input type="checkbox" name="isAttend" value="在学中">
				在学中
				</label><br>
				</div>
				<input type="submit" value="変更"><br>
				<a href="../main/student_list.jsp">戻る</a>
				</form>
				</div>
		</section>
	</c:param>
</c:import>