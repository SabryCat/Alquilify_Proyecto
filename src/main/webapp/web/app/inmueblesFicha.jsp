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
		<jsp:param name="modulo" value="inmuebles" />
	</jsp:include>
	<!-- cuerpo -->
 		<div class="container px-4 px-lg-5 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">
			  <div class="col-sm-12 mx-auto">
				  <!-- Formulario Modificar -->
						<h5 class="card-title text-center mb-2">Modificar datos de inmueble</h5>
						<form action="/inmuebles/editarInmueble/${inmueble.idInmueble}" method="post" class="col-sm-8 mx-auto">							
							<div class="form-floating mb-2">
								<select name="propietario" class="form-select" required>
									<option>Seleccionar Propietario</option>
									<c:forEach var="ele" items="${propietarios}">
										<c:if test="${ele.idUsuario eq inmueble.usuario.idUsuario}">
											<option value="${ele.idUsuario}" selected>${ele.nombre}, ${ele.apellidos}</option>
										</c:if>	
										<option value="${ele.idUsuario}">${ele.nombre}, ${ele.apellidos}</option>
									</c:forEach>	
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="numeroCatastral" class="form-control form-control-lg" value="${inmueble.numeroCatastral}" required/>
							  <label class="form-label" for="numeroCatastral">Nº catastral</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="text" name="anioContruccion" class="form-control form-control-lg" value="${inmueble.anioContruccion}" required/>
							  <label class="form-label" for="anioContruccion">Año de construcción</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="tipoFinca" class="form-control form-control-lg" value="${inmueble.tipoFinca}" required/>
							  <label class="form-label" for="tipoFinca">Tipo de finca</label>
							</div>
		
							<div class="form-floating mb-2">
							  <input type="text" name="direccion" class="form-control form-control-lg" value="${inmueble.direccion}" required/>
							  <label class="form-label" for="direccion">Dirección</label>
							</div>
							
							<div class="form-floating mb-2">
								<p class="mb-0" style="margin-left: 10px;">Provincia</p>
								<select name="provincia" class="form-select" > 									
									<c:forEach var="ele" items="${provincias}">
										<c:if test="${ele.idProvincia eq inmueble.provincia.idProvincia}">
											<option value="${ele.idProvincia}" selected>${ele.nombre}</option>
										</c:if>									
											<option value="${ele.idProvincia}">${ele.nombre}</option>									
									</c:forEach>
								</select>
							</div>		
							
							<div class="form-floating mb-2">
							  <input type="text" name="metros" class="form-control form-control-lg" value="${inmueble.metros}" required/>
							  <label class="form-label" for="metros">Metros</label>
							</div>
												
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Ascensor</p>
							    <select name="ascensor" class="form-select" required>
							        <option value="SI" ${inmueble.ascensor == 'SI' ? 'selected' : ''}>SI</option>
							        <option value="NO" ${inmueble.ascensor == 'NO' ? 'selected' : ''}>NO</option>
							    </select>
							</div>	
							
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Habitaciones</p>
							    <select name="habitaciones" class="form-select" required>
								        <option value="1" ${inmueble.habitaciones == 1 ? 'selected' : ''}>1</option>
								        <option value="2" ${inmueble.habitaciones == 2 ? 'selected' : ''}>2</option>
								        <option value="3" ${inmueble.habitaciones == 3 ? 'selected' : ''}>3</option>
								        <option value="4" ${inmueble.habitaciones == 4 ? 'selected' : ''}>4</option>
								        <option value="5" ${inmueble.habitaciones == 5 ? 'selected' : ''}>5</option>
								        
							    </select>
							</div>	
																			
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Baños</p>
							    <select name="banios" class="form-select" required>
							        <option value="1" ${inmueble.habitaciones == 1 ? 'selected' : ''}>1</option>
							        <option value="2" ${inmueble.habitaciones == 2 ? 'selected' : ''}>2</option>
							        <option value="3" ${inmueble.habitaciones == 3 ? 'selected' : ''}>3</option>
							        <option value="4" ${inmueble.habitaciones == 4 ? 'selected' : ''}>4</option>
							    </select>
							</div>
							
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Garage</p>
								<select name="plazaGarage" class="form-select" required>
									<option value="SI" ${inmueble.plazaGarage == 'SI' ? 'selected' : ''}>SI</option>
							        <option value="NO" ${inmueble.plazaGarage == 'NO' ? 'selected' : ''}>NO</option>
								</select>
							</div>	
							
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Zona exterior</p>
								<select name="zonaExterior" class="form-select" required>
									<option value="SI" ${inmueble.zonaExterior == 'SI' ? 'selected' : ''}>SI</option>
							        <option value="NO" ${inmueble.zonaExterior == 'NO' ? 'selected' : ''}>NO</option>
								</select>
							</div>
															
							<div class="form-floating mb-2">
							  <input type="text" name="observaciones" class="form-control form-control-lg" value="${inmueble.observaciones}" required/>
							  <label class="form-label" for="observaciones">Observaciones</label>
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