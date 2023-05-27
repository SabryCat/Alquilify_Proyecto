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
<main class="d-flex flex-nowrap">
	<!-- sidebar con parámetro -->
	<jsp:include page="componentesApp/sidebar.jsp">
		<jsp:param name="modulo" value="alquileres" />
	</jsp:include>
		<!-- cuerpo -->
 		<div class="container px-2 px-lg-2 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">			
			  <div class="col-sm-12 mx-auto">
			  	<!-- Alert info -->
				<jsp:include page="componentesApp/alertInfo.jsp">
					<jsp:param name="alertInfo" value="${info}" />
				</jsp:include>
	  	
				<!-- tabs -->
				<ul id="myTab" class="nav nav-tabs" role="tablist">
				  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">CONTRATOS</a></li>
				  <li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">ALTAS</a></li>		  
				</ul>
				
				<div class="tab-content">
				<!-- Listado Propietarios -->
				 <div class="p-1 tab-pane fade show active etab-p1">
		    		<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Propietario</th>
						      <th scope="col">Nº catastral</th>
						      <th scope="col">Año</th>
						      <th scope="col">Provincia</th>
						      <th scope="col">Dirección</th>
						      <th scope="col">Metros</th>
						      <th scope="col">Servicios</th>
						    </tr>
						  </thead>
						  <tbody>
							 <!--<c:forEach var="ele" items="${inmuebles}">
								<tr>
								 	<th scope="row">${ele.idInmueble}</th>
									<td> ${ele.usuario.nombre}, ${ele.usuario.apellidos}</td>
									<td> ${ele.numeroCatastral}</td>
									<td> ${ele.anioContruccion}</td>
									<td> ${ele.provincia.nombre}</td>
									<td> ${ele.direccion}</td>
									<td> ${ele.metros}</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="propiedades" style="margin-left: 20%;">
											<a href="/inmuebles/servicios/${ele.idInmueble}"  type="button" class="btn btn-outline-primary">ver</a>
										</div>
									</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/inmuebles/verficha/${ele.idInmueble}"  type="button" class="btn btn-outline-primary">Editar</a>
											<a href="/inmuebles/eliminar/${ele.idInmueble}"  type="button" class="btn btn-outline-warning">Eliminar</a>
										</div>
									</td>
								</tr>	 
							</c:forEach>-->
						 </tbody>
					</table>
				  </div>
				  <!-- Formulario Alta -->
				  <div class="p-2 tab-pane fade etab-p2">
						<h5 class="card-title text-center mb-2">Registrar nuevo contrato de alquiler</h5>
						<form action="/alquileres/altaNuevoContrato" method="post" class="col-sm-8 mx-auto">
						
							<div class="form-floating mb-2">
								<select name="idInmueble" class="form-select" required>
									<option>Seleccionar Inmueble</option>
									<c:forEach var="ele" items="${inmuebles}">
										<option value="${ele.idInmueble}">${ele.tipoFinca}, ${ele.direccion} - PROPIETARIO: ${ele.usuario.nombre}, ${ele.usuario.apellidos}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-2">
								<select name="usuarioInquilino" class="form-select" required>
									<option>Seleccionar Inquilino</option>
									<c:forEach var="ele" items="${inquilinos}">
										<option value="${ele.idUsuario}">${ele.nombre}, ${ele.apellidos} - DNI: ${ele.nif }</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-4">
								<select name="tipoContrato" class="form-select">
									<option>Seleccionar Tipo de contrato</option>
									<c:forEach var="ele" items="${tiposcontratos}">
										<option value="${ele.idTipoContrato}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="number" step="0.00" name="importe" class="form-control form-control-lg" required/>
							  <label class="form-label" for="importe">Importe</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="number" min="0" max="12" name="fianza" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fianza">Meses de fianza</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="number" step="0.00" name="importeFianza" class="form-control form-control-lg" required/>
							  <label class="form-label" for="importeFianza">Importe Fianza</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="date" name="fechaComienzo" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaComienzo">Fecha comienzo</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="date" name="fechaFin" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaFin">Fecha fin</label>
							</div>
								
							<div class="form-floating mb-2">
								<select name="renovacion" class="form-select" required>
									<option>Renovación</option>
									<option value="SI">SI</option>
									<option value="NO">NO</option>
								</select>
							</div>	
								
							<div class="form-floating mb-2">
							  <input type="text" name="observaciones" class="form-control form-control-lg" required/>
							  <label class="form-label" for="observaciones">Observaciones</label>
							</div>
																																			
							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Registrar contrato
								</button>
							</div>
						</form>
				  </div>
				  
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