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
		<jsp:param name="modulo" value="usuarios" />
	</jsp:include>
		<!-- cuerpo -->
 		<div class="container px-2 px-lg-2 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">			
			  <div class="col-sm-12 mx-auto">
			  	<div class="card mb-2">
				  <div class="card-header">
				    <strong>Usuario Propietario</strong>: ${usuario.nombre}, ${usuario.apellidos}
				  </div>
				  <div class="card-body p-3">
				    <blockquote class="blockquote m-1">
				      <footer class="blockquote-footer">
				      	DNI: ${usuario.nif}, EMAIL: ${usuario.email}, TEL&Eacute;FONO: ${usuario.telefono}
				      </footer>
				    </blockquote>
				  </div>
				</div>
			  
			  	<!-- Alert info -->
				<jsp:include page="componentesApp/alertInfo.jsp">
					<jsp:param name="alertInfo" value="${info}" />
					<jsp:param name="alertTipo" value="${tipo}" />
				</jsp:include>
	  	
				<!-- tabs -->
				<ul id="myTab" class="nav nav-tabs" role="tablist">
				  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">PROPIEDADES</a></li>
				  <!--<li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">ALTAS</a></li>-->		  
				</ul>
				
				<div class="tab-content">
				<!-- Listado Inmuebles -->
				 <div class="p-1 tab-pane fade show active etab-p1">
		    		<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Nº catastral</th>
						      <th scope="col">Año</th>
						      <th scope="col">Provincia</th>
						      <th scope="col">Dirección</th>
						      <th scope="col">Metros</th>
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${inmuebles}">
								<tr>
								 	<th scope="row">${ele.idInmueble}</th>
									<td> ${ele.numeroCatastral}</td>
									<td> ${ele.anioContruccion}</td>
									<td> ${ele.provincia.nombre}</td>
									<td> ${ele.direccion}</td>
									<td> ${ele.metros}</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/inmuebles/verficha/${ele.idInmueble}"  type="button" class="btn btn-outline-primary">Editar</a>						
										</div>
									</td>
								</tr>	 
							</c:forEach>
						 </tbody>
					</table>
				  </div>
				  <!-- Formulario Alta
				  <div class="p-2 tab-pane fade etab-p2">
						<h5 class="card-title text-center mb-2">Registrar nuevo inmueble</h5>
						<form action="/inmuebles/altaNuevosInmueble" method="post" class="col-sm-8 mx-auto">
							
							<div class="form-floating mb-2">
								<select name="propietario" class="form-select" required>
									<option>Seleccionar Propietario</option>
									<c:forEach var="ele" items="${propietarios}">
										<option value="${ele.idUsuario}">${ele.nombre}, ${ele.apellidos}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="numeroCatastral" class="form-control form-control-lg" required/>
							  <label class="form-label" for="numeroCatastral">Nº catastral</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="text" name="anioContruccion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="anioContruccion">Año de construcción</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="tipoFinca" class="form-control form-control-lg" required/>
							  <label class="form-label" for="tipoFinca">Tipo de finca</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="direccion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="direccion">Dirección</label>
							</div>
							
							<div class="form-floating mb-4">
								<select name="provincia" class="form-select">
									<option>Seleccionar Provincia</option>
									<c:forEach var="ele" items="${provincias}">
										<option value="${ele.idProvincia}"> ${ele.nombre}</option>
									</c:forEach>
								</select>
							</div>	
							
							<div class="form-floating mb-2">
							  <input type="text" name="metros" class="form-control form-control-lg" required/>
							  <label class="form-label" for="metros">Metros</label>
							</div>
												
							<div class="form-floating mb-2">
								<select name="ascensor" class="form-select" required>
									<option>Ascensor</option>
									<option value="SI">SI</option>
									<option value="NO">NO</option>
								</select>
							</div>	
							
							<div class="form-floating mb-2">
								<select name="habitaciones" class="form-select" required>
									<option>Habitaciones</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</div>	
																			
							<div class="form-floating mb-2">
								<select name="banios" class="form-select" required>
									<option>Baños</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
							</div>
							
							<div class="form-floating mb-2">
								<select name="plazaGarage" class="form-select" required>
									<option>Garage</option>
									<option value="SI">SI</option>
									<option value="NO">NO</option>
								</select>
							</div>	
							
							<div class="form-floating mb-2">
								<select name="zonaExterior" class="form-select" required>
									<option>Zona exterior</option>
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
								Registrar inmueble
								</button>
							</div>
						</form>
				  </div>
				  
				 -->
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