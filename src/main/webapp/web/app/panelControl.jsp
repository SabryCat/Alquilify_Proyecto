<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Panel de control</title>
	<jsp:include page="componentesApp/libreriasPanel.jsp"/>
</head>
<body>
<main class="d-flex flex-nowrap">
	<!-- sidebar con parámetro -->
	<jsp:include page="componentesApp/sidebar.jsp">
		<jsp:param name="modulo" value="home" />
	</jsp:include>
	
	HOLA aca van cosas 
	
</main>					
</body>
</html>