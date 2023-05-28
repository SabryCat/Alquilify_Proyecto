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
		<jsp:param name="modulo" value="servicios" />
	</jsp:include>
	<!-- cuerpo -->
 		<div class="container px-4 px-lg-5 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">
			  <div class="col-sm-12 mx-auto">
				  <!-- Formulario Modificar -->
						<h5 class="card-title text-center mb-2">Modificar datos del servicio  ${servicio.proveedoresServicio.tiposServicio.tipo} - ${servicio.proveedoresServicio.nombre}</h5>
						<form action="/servicios/editarServicios/${servicio.idAlquilerServicio}" method="post" class="col-sm-8 mx-auto">
						
							<div class="form-floating mb-2">
							  <input value="${servicio.numeroContratoServicio}" type="text" name="numeroContratoServicio" class="form-control form-control-lg" required/>
							  <label class="form-label" for="numeroContratoServicio">Nº Contrato servicio</label>
							</div>

							<div class="form-floating mb-2">
							  <input value="${servicio.fechaContratacion}" type="date" name="fechaContratacion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaContratacion">Fecha comienzo</label>
							</div>

							<div class="form-floating mb-2">
							  <input  value="${servicio.fechaFinalizacion}"type="date" name="fechaFinalizacion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaFinalizacion">Fecha fin</label>
							</div>
			
							<div class="form-floating mb-2">
							<p class="mb-0" style="margin-left: 10px;">Baja</p>
								<select name="baja" class="form-select" required>
							        <option value="SI" ${servicio.baja == 'SI' ? 'selected' : ''}>SI</option>
							        <option value="NO" ${servicio.baja == 'NO' ? 'selected' : ''}>NO</option>
							    </select>
							</div>	

							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Guardar servicio
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