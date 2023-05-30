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