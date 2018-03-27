<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>消息提示</title>
		<%@ include file="/WEB-INF/views/common/header_js.jsp" %>
	</head>
	
	<body style="text-align:left;">
		<script>
			alert('${notice}');
			history.back();
		</script>
	</body>

</html>
