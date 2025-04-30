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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
<form action="/system/scoremanager/main/TestRegist.action" method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<div class="col-2">
<label class="form-label" for="test-f1-select">入学年度</label>
<select class="form-select" id="test-f1-select" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
<option value="${year}" <c:if test="${year==entYear}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>


		<div class="col-2">
			<label class="form-label" for="test-f2-select">クラス</label>
			<select class="form-select" id="test-f2-select" name="f2">
				<option value="0">--------</option>
				<c:forEach var="num" items="${class_num_set}">
					<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
				</c:forEach>
			</select>
		</div>
			<div class="col-4">
			<label class="form-label" for="test-f3-select">科目</label>
			<select class="form-select" id="test-f3-select" name="f3">
				<option value="0">-------</option>
				<c:forEach var="subject" items="${subject_list}">
					<option value="${subject.cd}" <c:if test="${subject.cd == subject_cd}">selected</c:if>>${subject.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-2">
			<label class="form-label" for="test-f4-select">回数</label>
			<select class="form-select" id="test-f4-select" name="f4">
				<option value="0" <c:if test="${f4==no}">selected</c:if>>-------</option>
					<option value="1">1</option>
					<option value="2">2</option>
			</select>
		</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>
			</form>

			<c:choose>
				<c:when test="${test.size()>0}">
				<form action="/system/scoremanager/main/TestRegistExecute.action" method="get">
					<div>科目：${entYear}(${no}回)</div>
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
						<c:forEach var="t" items="${test}">
    						<c:set var="studentNo" value="${t.student.no}" scope="session"/>
							<tr>
								<td>${t.student.ent_year}</td>
								<td>${t.class_num}</td>
								<td>${t.student.no}</td>
								<td>${t.student.name}</td>
								<td><input type="text" name="point"  value="${t.point}">
									<br><div class="mt-2 text-warning">${errors.get("f2")}</div></td>
							</tr>
						</c:forEach>
					</table>
					<input type="submit" value="登録して終了">
					</form>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>