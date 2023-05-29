<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Alquilify - Contacto</title>
    <jsp:include page="componentes/librerias.jsp"/>
</head>
<body>
	
	<jsp:include page="componentes/navbar.jsp"/>
	

               <!-- Page Content-->
        <div class="container px-4 px-lg-5">
					
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
			    <div class="col-lg-12 alquilify-mt">
                    <h1 class="font-weight-light">Envia tus datos nosotros te llamamos</h1>		
				</div>
            </div>
			
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-9">
				
					<form id="contactForm" data-sb-form-api-token="API_TOKEN">
						<!-- Nombre -->
						<div class="mb-3">
							<label class="form-label" for="nombre">Nombre</label>
							<input class="form-control" id="nombre" type="text" placeholder="Nombre"  />
						</div>

						<!-- Email -->
						<div class="mb-3">
							<label class="form-label" for="email">Email</label>
							<input class="form-control" id="email" type="email" placeholder="Email" />
						</div>

						<!-- Asunto -->
						<div class="mb-3">
							<label class="form-label" for="asunto">Asunto</label>
							<input class="form-control" id="asunto" type="text" placeholder="Asunto"/>
						</div>
						
						<!-- Mensaje -->
						<div class="mb-3">
							<label class="form-label" for="mensaje">Mensaje</label>
							<textarea class="form-control" id="mensaje" type="text" placeholder="Mensaje" style="height: 10rem;" ></textarea>
						</div>

						<!-- Form submit button -->
						<div class="d-grid">
							<button class="btn alquilify-bg text-white btn-lg disabled" id="submitButton" type="submit">Contactar</button>
						</div>
					</form> 
				</div>
				<div class="col-lg-3" style="margin-top: -200px;">
					<h1 class="font-weight-light">Alquilify</h1>
					<p>Estamos aquí para responder a todas tus preguntas, atender tus inquietudes y proporcionarte el mejor servicio posible. 
					Por favor, no dudes en comunicarte con nosotros.</p>
				</div>
			</div>

			<div class="card bg-light alquilify-mt alquilify-mb py-4 text-center">
                <div class="card-body">
					<img class="img-fluid mb-4 mb-lg-0" src="/web/images/contacto.PNG" alt="..." />
				</div>
            </div>
		</div>  
	
	<jsp:include page="componentes/footer.jsp"/>
		
</body>
</html>