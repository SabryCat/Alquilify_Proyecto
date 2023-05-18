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
                    <h1 class="font-weight-light">Business Name or Tagline</h1>		
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
							<label class="form-label" for="mensaje">Message</label>
							<textarea class="form-control" id="mensaje" type="text" placeholder="Mensaje" style="height: 10rem;" ></textarea>
						</div>

						<!-- Form submit button -->
						<div class="d-grid">
							<button class="btn alquilify-bg text-white btn-lg disabled" id="submitButton" type="submit">Contactar</button>
						</div>
					</form> 
				</div>
				<div class="col-lg-3">
					<h1 class="font-weight-light">Business Name or Tagline</h1>
					<p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
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