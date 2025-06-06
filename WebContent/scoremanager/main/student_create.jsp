<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
				<div class="container2">
					<form action="/system/scoremanager/main/StudentCreateExecute.action" method="post">
						<div class="row mx-3 mb-3 py-2 align-items-center rounded" id="filter">
							入学年度<br>
							<select name="inputEntYear" class="form-select">
								<option value="0">--------</option>
								<c:forEach var="r" items="${ent_year_set}">
									<option value="${r}">${r}</option>
								</c:forEach>
							</select><br>
							<div class="mt-2 text-warning">${errors.get("f3")}</div>
							<br>
							学生番号<br>
							<input type="text" name="inputNo" class="form-input" required="required"  placeholder="学生番号を入力してください。" value="${inputNo}">
							<br><div class="mt-2 text-warning">${errors.get("f4")}</div>
							<br>
							氏名　<br>
							<input type="text" name="inputName" class="form-input" required="required" placeholder="氏名を入力してください。" value="${inputName}"><br>
							クラス<br>
							<select name="inputClassNum" class="form-select">
								<c:forEach var="p" items="${class_num_set}">
									<option value="${p}">${p}</option>
								</c:forEach>
							</select><br>
						</div>
						<input type="submit" value="登録して終了"><br>
						<a href="../main/student_list.jsp">戻る</a>
					</form>
				</div>
		</section>
	</c:param>
</c:import>