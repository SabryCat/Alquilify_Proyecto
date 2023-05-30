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
		<jsp:param name="modulo" value="alquileres" />
	</jsp:include>
	<!-- cuerpo -->
 		<div class="container px-4 px-lg-5 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">
			  <div class="col-sm-12 mx-auto">
				  <!-- Formulario Modificar -->
						<h5 class="card-title text-center mb-2"><sec:authorize access="hasAuthority('Administrador')">Modificar</sec:authorize> Datos del contrato #${contrato.idAlquiler}</h5>
						<form action="/alquileres/editarContrato/${contrato.idAlquiler}" method="post" class="col-sm-8 mx-auto">							
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Contrato</p>
								<select name="tipoContrato" class="form-select" required>
									<c:forEach var="ele" items="${tiposcontratos}">
										<c:if test="${ele.idTipoContrato eq contrato.tipoContrato.idTipoContrato}">
											<option value="${ele.idTipoContrato}" selected>${ele.tipo }</option>
										</c:if>	
											<option value="${ele.idTipoContrato}"> ${ele.tipo}</option>
									</c:forEach>								
								</select>
							</div>
							<div class="form-floating mb-2">
								<p class="mb-0" style="margin-left: 10px;">Inquilino</p>
								<select name="usuarioInquilino" class="form-select" required>
									<c:forEach var="ele" items="${inquilinos}">
										<c:if test="${ele.idUsuario eq contrato.usuario.idUsuario}">
											<option value="${ele.idUsuario}" selected>${ele.nombre}, ${ele.apellidos} - DNI: ${ele.nif }</option>
										</c:if>	
											<option value="${ele.idUsuario}">${ele.nombre}, ${ele.apellidos} - DNI: ${ele.nif }</option>
									</c:forEach>								
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input value="${contrato.importe}" type="number" step="0.00" name="importe" class="form-control form-control-lg" required/>
							  <label class="form-label" for="importe">Importe</label>
							</div>

							<div class="form-floating mb-2">
							  <input value="${contrato.fianza}" type="number" min="0" max="12" name="fianza" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fianza">Meses de fianza</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input value="${contrato.importeFianza}" type="number" step="0.00" name="importeFianza" class="form-control form-control-lg" required/>
							  <label class="form-label" for="importeFianza">Importe Fianza</label>
							</div>
							
							<div class="form-floating mb-2">
							  <input value="${contrato.fechaComienzo}" type="date" name="fechaComienzo" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaComienzo">Fecha comienzo</label>
							</div>

							<div class="form-floating mb-2">
							  <input value="${contrato.fechaFin}" type="date" name="fechaFin" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaFin">Fecha fin</label>
							</div>
							
							<div class="form-floating mb-2">
								<p class="mb-0" style="margin-left: 10px;">Renovaci&oacute;n</p>
								<select name="renovacion" class="form-select" required>
							        <option value="SI" ${contrato.renovacion == 'SI' ? 'selected' : ''}>SI</option>
							        <option value="NO" ${contrato.renovacion == 'NO' ? 'selected' : ''}>NO</option>
							    </select>
								
							</div>	
								
							<div class="form-floating mb-2">
							  <input value="${contrato.observaciones}"type="text" name="observaciones" class="form-control form-control-lg" required/>
							  <label class="form-label" for="observaciones">Observaciones</label>
							</div>
							<sec:authorize access="hasAuthority('Administrador')">
								<div class="d-grid">
									<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
									Guardar cambios
									</button>
								</div>
							</sec:authorize>
						</form>		  
				</div>
				<!-- fin tabs -->
      		</div>
     	 </div>
  	<!-- fin cuerpo -->

</main>					

</body>
</html>