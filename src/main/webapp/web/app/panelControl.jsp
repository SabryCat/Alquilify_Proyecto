<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<jsp:param name="modulo" value="home" />
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
				  	<li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">VENCIMIENTOS CONTRATOS ALQUILER</a></li>
				  	<li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2" data-bs-toggle="tab">VENCIMIENTOS SERVICIOS</a></li>		  	 	
				</ul>
				
				<div class="tab-content">
				<!-- Listado Vencimientos alquileres-->
				 <div class="p-1 tab-pane fade show active etab-p1">

					<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Tipo</th>
						      <th scope="col" style="width: 100px;">Inicio</th>
						      <th scope="col" style="width: 100px;">Fin</th>						      
						      <th scope="col">Propiedad</th>
						      <th scope="col">Propietario</th>
						      <th scope="col">Inquilino</th>
						      <th scope="col">DIAS RESTANTES</th>

						    </tr>
						  </thead>
						  <tbody>
							<c:forEach var="ele" items="${contratos}">
							    					    
						         <c:choose>
					                <c:when test="${ele.dias_restantes < 0}">
					                    <tr class="alert alert-danger">
					                </c:when>
					                <c:when test="${ele.dias_restantes <= 15}">
					                    <tr class="alert alert-warning">
					                </c:when>
					                <c:when test="${ele.dias_restantes <= 30}">
					                    <tr class="alert alert-info">
					                </c:when>
					            </c:choose>						    	
												    							    							    
							        <th scope="row">${ele.alquilere.idAlquiler}</th>
							        <td>${ele.alquilere.tipoContrato.tipo}</td>
							        <td><fmt:formatDate value="${ele.alquilere.fechaComienzo}" pattern="dd-MM-yyyy" /></td>
							        <td><fmt:formatDate value="${ele.alquilere.fechaFin}" pattern="dd-MM-yyyy" /></td>
							        <td>${ele.alquilere.inmueble.tipoFinca} - ${ele.alquilere.inmueble.direccion}</td>
							        <td>${ele.alquilere.inmueble.usuario.nombre}, ${ele.alquilere.inmueble.usuario.apellidos}</td>
							        <td>${ele.alquilere.usuario.nombre}, ${ele.alquilere.usuario.apellidos}</td>
							        <td class="text-center">${ele.dias_restantes}</td>
							    </tr>
							</c:forEach>
						 </tbody>
					</table>


				  </div>
				  <!--  Listado Vencimientos servicios -->
				  <div class="p-2 tab-pane fade etab-p2">

		    		<table class="table table-striped">
						  <thead>
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Tipo</th>
						      <th scope="col">Nº contrato</th>
						      <th scope="col">Servicio</th>
						      <th scope="col">Fecha Inicio</th>
						      <th scope="col">Fecha Fin</th>
						      <th scope="col">DIAS RESTANTANTES</th>
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${servicios}">
						         <c:choose>
					                <c:when test="${ele.dias_restantes < 0}">
					                    <tr class="alert alert-danger">
					                </c:when>
					                <c:when test="${ele.dias_restantes <= 15}">
					                    <tr class="alert alert-warning">
					                </c:when>
					                <c:when test="${ele.dias_restantes <= 30}">
					                    <tr class="alert alert-info">
					                </c:when>
					            </c:choose>		
								 	<td scope="row">${ele.alquilerServicios.idAlquilerServicio}</td>
								 	<td scope="row">${ele.alquilerServicios.proveedoresServicio.tiposServicio.tipo}</td>
								 	<td scope="row">${ele.alquilerServicios.numeroContratoServicio}</td>
									<td scope="row">${ele.alquilerServicios.proveedoresServicio.nombre}</td>
									<td scope="row">${ele.alquilerServicios.fechaContratacion}</td>
									<td scope="row">${ele.alquilerServicios.fechaFinalizacion}</td>
									<td class="text-center">${ele.dias_restantes}</td>
								</tr>	 
							</c:forEach>
						 </tbody>
					</table>


				  </div>

				</div>
				<!-- fin tabs -->
      		</div>
     	 </div>
      </div>
  	<!-- fin cuerpo -->

  
  
  
	 	<!-- Script para los tabs -->
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











