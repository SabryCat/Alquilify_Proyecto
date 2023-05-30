	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				    <strong>Inmueble</strong>: ${inmueble.direccion}, ${inmueble.provincia.nombre}
				  </div>
				  <div class="card-body p-3">
				    <blockquote class="blockquote m-1">
				      <footer class="blockquote-footer">
				      	Tipo finca: ${inmueble.tipoFinca}, Construcci&oacute;n: ${inmueble.anioContruccion}, Nº Catastral: ${inmueble.numeroCatastral}
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
				  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1" data-bs-toggle="tab">CONTRATOS</a></li>			
				</ul>
				
				<div class="tab-content">
				<!-- Listado Propietarios -->
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
						    </tr>
						  </thead>
						  <tbody>
							 <c:forEach var="ele" items="${contratos}">
								<tr>
								 	<th scope="row">${ele.idAlquiler}</th>
								 	<td> ${ele.tipoContrato.tipo}</td>
								 	<td><fmt:formatDate value="${ele.fechaComienzo}" pattern="dd-MM-yyyy" /></td>
									<td><fmt:formatDate value="${ele.fechaFin}" pattern="dd-MM-yyyy" /></td>
									<td> ${ele.inmueble.tipoFinca} - ${ele.inmueble.direccion}</td>
									<td> ${ele.inmueble.usuario.nombre}, ${ele.inmueble.usuario.apellidos}</td>
									<td> ${ele.usuario.nombre}, ${ele.usuario.apellidos}</td>
									<td>
										<div class="btn-group btn-group-sm" role="group" aria-label="acciones">
											<a href="/alquileres/verContrato/${ele.idAlquiler}"  type="button" class="btn btn-outline-primary">
												
												<sec:authorize access="hasAuthority('Administrador')">
													Editar
												</sec:authorize>
												
												<sec:authorize access="hasAuthority('Propietario') or hasAuthority('Inquilino')">
													ver
												</sec:authorize>
												
											</a>									
										</div>
									</td>
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