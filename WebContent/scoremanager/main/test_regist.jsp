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
<form method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<div class="col-2">
<label class="form-label" for="student-f1-select">入学年度</label>
<select class="form-select" id="student-f1-select" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>


		<div class="col-2">
			<label class="form-label" for="student-f2-select">クラス</label>
			<select class="form-select" id="student-f2-select" name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==2}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>
			<div class="col-4">
			<label class="form-label" for="student-f3-select">科目</label>
			<select class="form-select" id="student-f3-select" name="f3">
				<option value="${f3}">${f3}</option>
				<c:forEach var="subject" items="${class_subject_set}">
					<option value="${subject}">${subject}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-2">
			<label class="form-label" for="student-f4-select">回数</label>
			<select class="form-select" id="student-f4-select" name="f4">
				<option value="${f4}">${f4}</option>
				<c:forEach var="count" items="${class_count_set}">
					<option value="${num}" <c:if test="${count==2}">selected</c:if>>${count}</option>
				</c:forEach>
			</select>
		</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f8")}</div>
				</div>
			</form>
			<c:choose>
				<c:when test="${students.size()>0}">
					<div>科目：${param.subject}(${param.count}回)</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="test" items="${test}">
							<tr>
								<td>${test.entYear}</td>
								<td>${test.class_num}</td>
								<td>${test.student_no}</td>
								<td>${test.name}</td>
								<td><input type="text" name="name" placeholder="0～100の範囲で入力してください" value="${test.point}"></td>
								<td class="text-center">
									<c:choose>
										<c:when test="${test.isAttend()}">
											〇
										</c:when>
										<c:otherwise>
											×
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>