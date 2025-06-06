<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
			得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<div class="row border mx-3 mb-3 py-2 align-items-center rounded d-flex" id="filter">
				<!-- 1つ目のフォーム -->
				<form action="/system/scoremanager/main/TestListSubjectExecute.action" method="get" class="d-flex flex-wrap w-100 justify-content-between align-items-center">
					<div class="col-2">
						<label class="form-label" for="student-f1-select">科目情報</label>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="form-select" id="student-f1-select" name="ent_year">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==entYearStr}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="class_num">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==classNum}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-3">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select" id="student-f3-select" name="subject">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_list}">
								<option value="${subject.cd}" <c:if test="${subject.cd==subjectCd}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f5")}</div>
				</form>
				<div class="align-items-center">
					<hr class="my-3 ">
				</div>

				<!-- 2つ目のフォーム -->
				<form action="/system/scoremanager/main/TestListStudentExecute.action" method="get" class="d-flex flex-wrap w-100 justify-content-between align-items-center">
					<div class="col-3">
						<label class="form-label" for="student-f1-select">学生情報</label>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f4-select">学生番号</label>
						<input type="text" name="student_no" placeholder="学生番号を入力してください" required="required" class="form-control">
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</form>
			</div>
			<p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
		</section>
	</c:param>
</c:import>