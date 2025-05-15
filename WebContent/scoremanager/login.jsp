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


<form action="/system/scoremanager/LoginExecute.action" method="post">
  <div class="row border mx-3 mb-3 pb-4 align-items-center rounded text-center" id="filter">
    <h2 class="h3 mb-3 mt-0 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-center rounded">ログイン</h2>
    <div class="col-12 text-center">
      <p>${errors.get("f2")}</p>
      <div class="form-floating mb-3">
      <input type="text" name="id" class="form-control mb-3 w-100" id="floatingID" value="${id}" placeholder="IDを入力してください">
      <label for="floatingID">ID</label>
	</div>
	<div class="form-floating mb-3">
      <input type="password" name="password" id="password floatingPassword" class="form-control mb-3 w-100" placeholder="パスワードを入力してください">
      <label for="floatingPassword">パスワード</label>
	</div>
    </div>
    <div class="col-12 text-center">
        <input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
        <label for="showPassword">パスワードを表示</label>
    </div>
    <script>
		function togglePasswordVisibility() {
			let passwordInput = document.getElementById("password");
			let showPasswordCheckbox = document.getElementById("showPassword");

			if (showPasswordCheckbox.checked) {
				passwordInput.type = "text";
			} else {
				passwordInput.type = "password";
			}
		}
	</script>
    <div class="col-12 text-center mt-3">
      <button class="btn btn-secondary bg-primary" id="filter-button">ログイン</button>
    </div>
  </div>
</form>
		</section>
	</c:param>
</c:import>