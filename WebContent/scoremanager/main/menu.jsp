<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
			得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー</h2>
			<form method="get">
				<div class="row">
					<div class="row border mx-3 mb-3 py-2 fw-norma bg-danger bg-opacity-10 align-items-center rounded col-3" id="filter">
						<div class="text-center">
							<a href="/system/scoremanager/main/StudentList.action">学生管理</a>
						</div>
					</div>
					<div class="row border mx-3 mb-3 py-2 fw-norma bg-success bg-opacity-10 align-items-center rounded col-3" id="filter">
						<div class="text-center">
							<p>成績管理</p>
						</div>
						<div class="text-center">
							<a href="/system/scoremanager/main/TestRegist.action">成績登録</a>
						</div>
						<div class="text-center">
							<a href="/system/scoremanager/main/TestList.action">成績参照</a>
						</div>
					</div>
					<div class="row border mx-3 mb-3 py-2 fw-norma bg-primary bg-opacity-10 align-items-center rounded col-3" id="filter">
						<div class="text-center">
							<a href="/system/scoremanager/main/SubjectList.action">科目管理</a>
						</div>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>