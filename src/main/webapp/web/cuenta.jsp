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
				<div class="card card border-1 rounded-3">
				  <div class="card-body p-5 text-center">
					<h5 class="card-title text-center mb-5">Registrarse como nuevo usuario</h5>
					<form action="/altaUsuario" method="post">
					
					
					
					
					
						<div class="form-floating mb-4">
							<select name="tipoUser" class="form-select">
								<option>Seleccionar Tipo de Usuario</option>
								<c:forEach var="ele" items="${tipousuario}">
									<option value="${ele.idTipoUsuario}"> ${ele.tipo}</option>
								</c:forEach>
							</select>
						</div>
	
	
	
						<div class="form-floating mb-4">
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
						</div>	
	
	
	
	
	
						<div class="form-floating mb-4">
						  <input type="text" id="nombre" class="form-control form-control-lg" />
						  <label class="form-label" for="nombre">Nombre</label>
						</div>
	
						<div class="form-floating mb-4">
						  <input type="text" id="apellidos" class="form-control form-control-lg" />
						  <label class="form-label" for="apellidos">Apellidos</label>
						</div>
						
						<div class="form-floating mb-4">
						  <input type="text" id="nif" class="form-control form-control-lg" />
						  <label class="form-label" for="nif">DNI</label>
						</div>
	
						<div class="form-floating mb-4">
						  <input type="email" id="email" class="form-control form-control-lg" />
						  <label class="form-label" for="email">Email</label>
						</div>
	
						<div class="form-floating mb-4">
						  <input type="text" id="telefono" class="form-control form-control-lg" />
						  <label class="form-label" for="telefono">Teléfono</label>
						</div>
	
						<div class="form-floating mb-4">
						  <input type="text" id="domicilio" class="form-control form-control-lg" />
						  <label class="form-label" for="domicilio">Domicilio</label>
						</div>
	
						<div class="form-floating mb-4">
						  <input type="text" id="clave" class="form-control form-control-lg" />
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