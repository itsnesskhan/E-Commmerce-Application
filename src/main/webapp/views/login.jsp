<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${title}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href='<c:url value="/css/mystyle.css" />'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">




</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="card">
			<div class="top-div">
				<h3>Welcome To My Site</h3>
				<p>
					New here? <a href="#">Create an account</a>
				</p>
			</div>
			<c:if test="${param.error != null }">
				<div class="alert alert-danger">${param.error }</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">${param.logout }</div>
			</c:if>

			<form action='<c:url value="/login" />' method="post">
				<div class="input-text">
					<input type="text" name="username" required="required"
						autofocus="autofocus"> <span>Email</span> <i
						class="fa fa-envelope-o"></i>
				</div>
				<div class="input-text">
					<input type="password" name="password" required="required"
						id="password_input"> <span>Password</span> <i
						class="fa fa-eye-slash passcode"></i> <a href="#">Forgot your
						password?</a>
				</div>

				<div class="remember">
					<div>
						<input type="checkbox" name="remember-me" id="r1"> <label
							for="r1">Remember Me</label>
					</div>
				</div>
				<div class="sign-in">
					<button type="submit">Sign in</button>
				</div>
			</form>

		</div>

	</div>
	<script src='<c:url value="/js/mystyle.js" />'></script>
</body>
</html>