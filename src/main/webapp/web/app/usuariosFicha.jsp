<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
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
		<jsp:param name="modulo" value="usuarios" />
	</jsp:include>
	<!-- cuerpo -->
 		<div class="container px-4 px-lg-5 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">
			  <div class="col-sm-12 mx-auto">
				  <!-- Formulario Modificar -->
						<h5 class="card-title text-center mb-2">Modificar datos de usuario</h5>
						<form action="/usuarios/editarUsuario/${usuario.idUsuario}" method="post" class="col-sm-8 mx-auto">

							<div class="form-floating mb-2">
							  <input type="text" name="nombre" class="form-control form-control-lg" value="${usuario.nombre}" required/>
							  <label class="form-label" for="nombre">Nombre</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="apellidos" class="form-control form-control-lg" value="${usuario.apellidos}" required/>
							  <label class="form-label" for="apellidos">Apellidos</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="nif" class="form-control form-control-lg" value="${usuario.nif}" required/>
							  <label class="form-label" for="nif">DNI</label>
							</div>
	
							<div class="form-floating mb-2">
							  <input type="email" name="email" class="form-control form-control-lg" value="${usuario.email}" required/>
							  <label class="form-label" for="email">Email</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="telefono" class="form-control form-control-lg" value="${usuario.telefono}" required/>
							  <label class="form-label" for="telefono">Teléfono</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="domicilio" class="form-control form-control-lg" value="${usuario.domicilio}" required/>
							  <label class="form-label" for="domicilio">Domicilio</label>
							</div>		
		
							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Guardar cambios
								</button>
							</div>
						</form>			  
				</div>
				<!-- fin tabs -->
      		</div>
     	 </div>
  	<!-- fin cuerpo -->

</main>					

</body>
</html>