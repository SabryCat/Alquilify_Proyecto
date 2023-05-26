<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
    <title>Alquilify - Mi cuenta</title>
    <jsp:include page="componentes/librerias.jsp"/>
</head>
<body>

	<jsp:include page="componentes/navbar.jsp"/>
	
	<!-- Form registrarse -->
		<div class="container px-4 px-lg-5 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-center h-100">
			  <div class="col-sm-12 col-md-8 col-lg-8 mx-auto">
				<div class="card card border-1 rounded-3 p-3">							
					<p class="text-center m-5">
					  <a class="btn alquilify-bg text-white" href="app/panelControl" role="button">
					    Ir a mi área de usuario
					  </a><br>
					  	<c:choose>
					       <c:when test="${info == null}">
					       </c:when>
					       <c:otherwise>
					             	<div class="alert alert-danger">
										${info}
									</div>
					       </c:otherwise>
					  	</c:choose>
					  <button class="btn btn-secondary mt-4 " type="button" data-bs-toggle="collapse" data-bs-target="#formularioNuevoUsuario" aria-expanded="false" aria-controls="formularioNuevoUsuario">
					    Registrame como nuevo usuario
					  </button>
					</p>
					<div class="collapse" id="formularioNuevoUsuario">
					  <div class="card-body pt-0 p-5 text-center">
						<h5 class="card-title text-center mb-5">Registrarse como nuevo usuario</h5>

						<form action="/usuarios/altaUsuario" method="post">
							<div class="form-floating mb-4">
								<select name="tipoDeUsuario" class="form-select" required>
									<option>Seleccionar Tipo de Usuario</option>
									<c:forEach var="ele" items="${tipousuario}">
										<option value="${ele.idTipoUsuario}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>
		
		
		
							<!--<div class="form-floating mb-4">
								<select name="tipoUser" class="form-select">
									<option>Seleccionar Tipo de contrato de alquiler</option>
									<c:forEach var="ele" items="${tiposcontratos}">
										<option value="${ele.idTipoContrato}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>
		
							<div class="form-floating mb-4">
								<select name="tipoUser" class="form-select">
									<option>Seleccionar Provincia</option>
									<c:forEach var="ele" items="${provincias}">
										<option value="${ele.idProvincia}"> ${ele.nombre}</option>
									</c:forEach>
								</select>
							</div>	
		
							<div class="form-floating mb-4">
								<select name="tipoUser" class="form-select">
									<option>Seleccionar tipo de Servicio</option>
									<c:forEach var="ele" items="${tiposservicios}">
										<option value="${ele.idTipoServicio}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>		
		
							<div class="form-floating mb-4">
								<select name="tipoUser" class="form-select">
									<option>Seleccionar proveedor</option>
									<c:forEach var="ele" items="${proveedores}">
										<option value="${ele.idProveedor}"> ${ele.nombre}</option>
									</c:forEach>
								</select>
							</div>-->	
		
		
		
		
		
							<div class="form-floating mb-4">
							  <input type="text" name="nombre" class="form-control form-control-lg" required/>
							  <label class="form-label" for="nombre">Nombre</label>
							</div>
		
							<div class="form-floating mb-4">
							  <input type="text" name="apellidos" class="form-control form-control-lg" required/>
							  <label class="form-label" for="apellidos">Apellidos</label>
							</div>
							
							<div class="form-floating mb-4">
							  <input type="text" name="nif" class="form-control form-control-lg" required/>
							  <label class="form-label" for="nif">DNI</label>
							</div>
		
							<div class="form-floating mb-4">
							  <input type="email" name="email" class="form-control form-control-lg" required/>
							  <label class="form-label" for="email">Email</label>
							</div>
		
							<div class="form-floating mb-4">
							  <input type="text" name="telefono" class="form-control form-control-lg" required/>
							  <label class="form-label" for="telefono">Teléfono</label>
							</div>
		
							<div class="form-floating mb-4">
							  <input type="text" name="domicilio" class="form-control form-control-lg" required/>
							  <label class="form-label" for="domicilio">Domicilio</label>
							</div>
		
							<div class="form-floating mb-4">
							  <input type="password" name="clave" class="form-control form-control-lg" required/>
							  <label class="form-label" for="clave">Clave/Password</label>
							</div>
		
							<div class="d-grid">
								<button class="p-3 btn alquilify-bg text-white btn-login text-uppercase fw-bold" type="submit">
								Registrarse
								</button>
							</div>
						</form>
					  </div>
				
					</div>							
				</div>
			  </div>
			</div>
		</div>
	
	<jsp:include page="componentes/footer.jsp"/>
		<!-- LOGIN ANTIGUO
        <div class="container px-4 px-lg-5 alquilify-mt">			
			<div class="row d-flex justify-content-center align-items-center h-100">
			  <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card border-0 shadow rounded-3">
				  <div class="card-body p-5 text-center">
					<h5 class="card-title text-center mb-5">Mi cuenta</h5>

					<div class="form-floating mb-4">
					  <input type="email" id="email" class="form-control form-control-lg" />
					  <label class="form-label" for="email">Email</label>
					</div>

					<div class="form-floating mb-4">
					  <input type="password" id="password" class="form-control form-control-lg" />
					  <label class="form-label" for="password">Password</label>
					</div>

					<div class="d-grid">
						<button class="btn alquilify-bg text-white btn-login text-uppercase fw-bold" type="submit">
						Acceder
						</button>
					</div>
				  </div>
				</div>
			  </div>
			</div>
		</div>-->
	

		
</body>
</html>