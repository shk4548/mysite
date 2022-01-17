<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="updateform" method="post" action="<%=request.getContextPath()%>/user"> <%-- parameter 랑은 다름 url 포함 --%>
					<input type = 'hidden' name="a" value="update"/>
					<label class="block-label" for="name">이름</label> <!-- vo.getName -->
					<input id="name" name="name" type="text" value="${userVo.name }">
					<label class="block-label" for="email">이메일 (변경 불가 )</label>
					<h4>${userVo.email }</h4> <!-- vo.getEmail -->
					
					<label class="block-label">비밀번호</label>
					<input name="password" type="password" value="${userVo.password }">

					
					<fieldset>
						<legend>성별</legend>
						
				<c:choose>
					<c:when test ='${userVo.gender == "female" }'>
						<label>여</label> <input type="radio" name="gender" value="female"  checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male" >
					</c:when>
					<c:when test = '${userVo.gender == "male" }'>
						<label>여</label> <input type="radio" name="gender" value="female" >
						<label>남</label> <input type="radio" name="gender" value="male"  checked="checked">
					</c:when>
				</c:choose>
						<!-- userVo.gender == male -->
					</fieldset>			
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>