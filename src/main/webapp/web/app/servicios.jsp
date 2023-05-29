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
		<jsp:param name="modulo" value="servicios" />
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
				  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">TOTAL SERVICIOS</a></li>
				  <li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">ALTAS PROVEEDORES</a></li>	  
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
									<!--<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="propiedades" style="margin-left: 20%;">
											<a href="/alquileres/verServicios/${ele.idAlquilerServicio}"  type="button" class="btn btn-outline-primary">ver</a>
										</div>
									</td>-->
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/servicios/verficha/${ele.idAlquilerServicio}"  type="button" class="btn btn-outline-primary">Editar</a>																		<!-- btn-outline-warning -->
										</div>
									</td>
								</tr>	 
							</c:forEach>
						 </tbody>
					</table>
				  </div>

				  <div class="p-2 tab-pane fade etab-p2">
						<h5 class="card-title text-center mb-2">Registrar Nuevo Proveedor de Servicio</h5>
						<form action="/proveedores/altaNuevo" method="post" class="col-sm-8 mx-auto">
							
							<div class="form-floating mb-4">
								<select name="idServicio" class="form-select">
									<option>Seleccionar tipo de Servicio</option>
									<c:forEach var="ele" items="${tiposservicios}">
										<option value="${ele.idTipoServicio}"> ${ele.tipo}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="form-floating mb-2">
							  <input type="text" name="nombre" class="form-control form-control-lg" required/>
							  <label class="form-label" for="nombre">Nombre proveedor</label>
							</div>

							<div class="d-grid">
								<button class="p-3 btn text-white btn-primary text-uppercase fw-bold" type="submit">
								Registrar Proveedor
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