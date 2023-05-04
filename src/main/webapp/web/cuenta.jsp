<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Web proyecto Alquilify" />
    <meta name="author" content="Sabrina" />
    <title>Alquilify - Mi cuenta</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/web/images/favicon.ico" />
    <link href="/web/css/miestilo.css" rel="stylesheet" type="text/css">
    <!-- Core theme CSS (includes Bootstrap)-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">		
</head>
<body>
	
	<jsp:include page="componentes/navbar.jsp"/>
	
		<!-- Page Content-->
        <div class="container px-4 px-lg-5 alquilify-mt">			
			<div class="row d-flex justify-content-center align-items-center h-100">
			  <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card border-0 shadow rounded-3">
				  <div class="card-body p-5 text-center">
					<h5 class="card-title text-center mb-5">Mi cuenta</h5>

					<div class="form-floating mb-4">
					  <input type="email" id="email" class="form-control form-control-lg" />
					  <label class="form-label" for="email">Email</label>
					</div>

					<div class="form-floating mb-4">
					  <input type="password" id="password" class="form-control form-control-lg" />
					  <label class="form-label" for="password">Password</label>
					</div>

					<div class="d-grid">
						<button class="btn alquilify-bg text-white btn-login text-uppercase fw-bold" type="submit">
						Acceder
						</button>
					</div>
				  </div>
				</div>
			  </div>
			</div>
		</div>
	

		
</body>
</html>