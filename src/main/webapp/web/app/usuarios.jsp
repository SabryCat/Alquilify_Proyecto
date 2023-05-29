<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
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
 		<div class="container px-2 px-lg-2 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">			
			  <div class="col-sm-12 mx-auto">
			  	<!-- Alert info -->
				<jsp:include page="componentesApp/alertInfo.jsp">
					<jsp:param name="alertInfo" value="${info}" />
					<jsp:param name="alertTipo" value="${tipo}" />
				</jsp:include>
	  	
	  	
				<!-- tabs -->
				<ul id="myTab" class="nav nav-tabs" role="tablist">
					<sec:authorize access="hasAuthority('Administrador')">
				  		<li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">PROPIETARIOS</a></li>
				 	</sec:authorize>
				 	
				  		<li class="nav-item"><a class="nav-link <sec:authorize access="hasAuthority('Propietario')">active show</sec:authorize>" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">INQUILINOS</a></li>
				  	
				  	<sec:authorize access="hasAuthority('Administrador')">
				  		<li class="nav-item"><a class="nav-link" href="#tab-3" data-bs-target=".etab-p3" data-bs-toggle="tab">ALTAS</a></li>			  
					</sec:authorize>
				</ul>
				
				<div class="tab-content">
				${usuario}
				  <!-- Listado Propietarios -->
				  <sec:authorize access="hasAuthority('Administrador')">
				  <div class="p-3 tab-pane fade show active etab-p1">
		    		<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Nombre</th>
						      <th scope="col">Apellidos</th>
						      <th scope="col">DNI</th>
						      <th scope="col">Email</th>
						      <th scope="col">Teléfono</th>
						      <th scope="col">Domicilio</th>
						      <th scope="col">Propiedades</th>
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${propietarios}">
								<tr>
								 	<th scope="row">${ele.idUsuario}</th>
									<td> ${ele.nombre}</td>
									<td> ${ele.apellidos}</td>
									<td> ${ele.nif}</td>
									<td> ${ele.email}</td>
									<td> ${ele.telefono}</td>
									<td> ${ele.domicilio}</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="propiedades" style="margin-left: 30%;">
											<a href="/usuarios/verPropiedades/${ele.idUsuario}"  type="button" class="btn btn-outline-primary">ver</a>
										</div>
									</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/usuarios/verficha/${ele.idUsuario}"  type="button" class="btn btn-outline-primary">Editar</a>
											<a href="/usuarios/eliminar/${ele.idUsuario}"  type="button" class="btn btn-outline-warning">Eliminar</a>
										</div>
									</td>
								</tr>	 
							</c:forEach>
						 </tbody>
					</table>
				  </div>
				  </sec:authorize>
				  
				  <!-- Listado Inquilinos -->
				  <div class="p-3 tab-pane fade etab-p2 <sec:authorize access="hasAuthority('Propietario')">show active</sec:authorize>">
		    		<table class="table table-striped">
						  <thead>
						    <tr>
								<th scope="col">#</th>
								<th scope="col">Nombre</th>
								<th scope="col">Apellidos</th>
								<th scope="col">DNI</th>
								<th scope="col">Email</th>
								<th scope="col">Teléfono</th>
								<th scope="col">Domicilio</th>
								<th scope="col">Alquileres</th>
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${inquilinos}">
								<tr>
								 	<th scope="row">${ele.idUsuario}</th>
									<td> ${ele.nombre}</td>
									<td> ${ele.apellidos}</td>
									<td> ${ele.nif}</td>
									<td> ${ele.email}</td>
									<td> ${ele.telefono}</td>
									<td> ${ele.domicilio}</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="propiedades" style="margin-left: 30%;">
											<a href="/usuarios/verAlquileres/${ele.idUsuario}"  type="button" class="btn btn-outline-primary">ver</a>
										</div>
									</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/usuarios/verficha/${ele.idUsuario}"  type="button" class="btn btn-outline-primary">Editar</a>
											<a href="/usuarios/eliminar/${ele.idUsuario}"  type="button" class="btn btn-outline-warning">Eliminar</a>
										</div>
									</td>
								</tr>	 
							</c:forEach>
						</tbody>
					</table>
				  </div>
				  
				  <!-- Formulario Alta -->
				  <sec:authorize access="hasAuthority('Administrador')">
				  <div class="p-3 tab-pane fade etab-p3">
						<h5 class="card-title text-center mb-2">Registrar nuevo usuario</h5>
						<form action="/usuarios/altaNuevosUsuarios" method="post" class="col-sm-8 mx-auto">
							<div class="form-floating mb-2">
								<select name="tipoDeUsuario" class="form-select" required>
									<option>Seleccionar Tipo de Usuario</option>
									<c:forEach var="ele" items="${tipousuario}">
										<option value="${ele.idTipoUsuario}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-floating mb-2">
							  <input type="text" name="nombre" class="form-control form-control-lg" required/>
							  <label class="form-label" for="nombre">Nombre</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="apellidos" class="form-control form-control-lg" required/>
							  <label class="form-label" for="apellidos">Apellidos</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="nif" class="form-control form-control-lg" required/>
							  <label class="form-label" for="nif">DNI</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="email" name="email" class="form-control form-control-lg" required/>
							  <label class="form-label" for="email">Email</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="telefono" class="form-control form-control-lg" required/>
							  <label class="form-label" for="telefono">Teléfono</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="domicilio" class="form-control form-control-lg" required/>
							  <label class="form-label" for="domicilio">Domicilio</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="password" name="clave" class="form-control form-control-lg" required/>
							  <label class="form-label" for="clave">Clave/Password</label>
							</div>
		
							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Registrar nuevo usuario
								</button>
							</div>
						</form>
				  </div>
				  </sec:authorize>
				  
				</div>
				<!-- fin tabs -->
      		</div>
     	 </div>
      </div>
  	<!-- fin cuerpo -->

  
  
  

        <script>
        $('#myTab a[data-bs-toggle="tab"]').on('show.bs.tab', function(e) {
        	  let target = $(e.target).data('bs-target')
        	  $(target)
        	    .addClass('active show')
        	    .siblings('.tab-pane.active')
        	    .removeClass('active show')
        	})
        </script>
  
</main>					
</body>
</html>