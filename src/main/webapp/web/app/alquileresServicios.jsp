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
<main class="d-flex flex-nowrap">
	<!-- sidebar con parámetro -->
	<jsp:include page="componentesApp/sidebar.jsp">
		<jsp:param name="modulo" value="${linkmenu}" />
	</jsp:include>
		<!-- cuerpo -->
 		<div class="container px-2 px-lg-2 mt-4 mb-4">			
			<div class="row d-flex justify-content-center align-items-start">			
			  <div class="col-sm-12 mx-auto">
			  	<div class="card mb-2">
				  <div class="card-header">
				    <strong>Alquiler contrato</strong> #${contrato.idAlquiler} - <strong>Inicio</strong>: ${contrato.fechaComienzo}, <strong>Fin</strong>: ${contrato.fechaFin}
				  </div>
				  <div class="card-body p-3">
				    <blockquote class="blockquote m-1">
				      <footer class="blockquote-footer">
				      	Inquilino: ${contrato.usuario.nombre}, ${contrato.usuario.nombre} - Tipo contrato: ${contrato.tipoContrato.tipo} - Renovaci&oacute;n: ${contrato.renovacion}
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
				  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">SERVICIOS</a></li>
				  	<sec:authorize access="hasAuthority('Administrador')">
				  		<li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">ALTAS</a></li>	  
					</sec:authorize>
				</ul>
				
				<div class="tab-content">
				<!-- Listado Servicios -->
				 <div class="p-1 tab-pane fade show active etab-p1">
		    		<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Tipo</th>
						      <th scope="col">Nº contrato</th>
						      <th scope="col">Servicio</th>
						      <th scope="col">Fecha Inicio</th>
						      <th scope="col">Fecha Fin</th>
						      <th scope="col">Baja</th>
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${servicios}">
								<tr>
								 	<td scope="row">${ele.idAlquilerServicio}</td>
									<td scope="row">${ele.proveedoresServicio.tiposServicio.tipo}</td>
									<td scope="row">${ele.numeroContratoServicio}</td>
									<td scope="row">${ele.proveedoresServicio.nombre}</td>
									<td scope="row">${ele.fechaContratacion}</td>
									<td scope="row">${ele.fechaFinalizacion}</td>
									<td scope="row">${ele.baja}</td>
								</tr>	 
							</c:forEach>
						 </tbody>
					</table>
				  </div>

				  <div class="p-2 tab-pane fade etab-p2">
						<h5 class="card-title text-center mb-2">Registrar Servicio</h5>
						<form action="/alquileres/altaNuevosServicios/${contrato.idAlquiler}" method="post" class="col-sm-8 mx-auto">
							
							<div class="form-floating mb-2">
								<select name="proveedoresServicio" class="form-select">
									<option>Seleccionar proveedor</option>
									<c:forEach var="ele" items="${proveedores}">
										<option value="${ele.idProveedor}"> ${ele.nombre}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="numeroContratoServicio" class="form-control form-control-lg" required/>
							  <label class="form-label" for="numeroContratoServicio">Nº Contrato servicio</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="date" name="fechaContratacion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaContratacion">Fecha comienzo</label>
							</div>

							<div class="form-floating mb-2">
							  <input type="date" name="fechaFinalizacion" class="form-control form-control-lg" required/>
							  <label class="form-label" for="fechaFinalizacion">Fecha fin</label>
							</div>
			
							<div class="form-floating mb-2">
								<select name="baja" class="form-select" required>
									<option>Estado de baja</option>
									<option value="SI">SI</option>
									<option value="NO">NO</option>
								</select>
							</div>	

							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Registrar servicio
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