<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Web proyecto Alquilify" />
    <meta name="author" content="Sabrina" />
    <title>Alquilify - Servicios</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/web/images/favicon.ico" />
    <link href="/web/css/miestilo.css" rel="stylesheet" type="text/css">
    <!-- Core theme CSS (includes Bootstrap)-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">			
</head>
<body>
	
	<jsp:include page="componentes/navbar.jsp"/>
	
	     <!-- Page Content-->
        <div class="container px-4 px-lg-5">
		
			<div class="card bg-light alquilify-mt alquilify-mb py-4 text-center">
                <div class="card-body">
				<h3>This call to action card is a great place to showcase some important information or display a clever tagline!</h3>
				<p class=" m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p>
				<p class=" m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p>
				</div>
            </div>
		
            <!-- Rows -->
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-uno.PNG" alt="..." /></div>
                <div class="col-lg-4">
                    <h1 class="font-weight-light">Business Name or Tagline</h1>
                    <p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
                    <a class="btn btn-light alquilify-btn" href="#!">Call to Action!</a>
                </div>
            </div>
			
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
			    <div class="col-lg-4">
                    <h1 class="font-weight-light">Business Name or Tagline</h1>
                    <p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
                    <a class="btn btn-light alquilify-btn" href="#!">Call to Action!</a>
                </div>
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-dos.PNG" alt="..." /></div>
            </div>
			
	        <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-tres.PNG" alt="..." /></div>
                <div class="col-lg-4">
                    <h1 class="font-weight-light">Business Name or Tagline</h1>
                    <p>This is a template that is great for small businesses. It doesn't have too much fancy flare to it, but it makes a great use of the standard Bootstrap core components. Feel free to use this template for any project you want!</p>
                    <a class="btn btn-light alquilify-btn" href="#!">Call to Action!</a>
                </div>
            </div>					
						
		</div>
	
	<jsp:include page="componentes/footer.jsp"/>
		
</body>
</html>