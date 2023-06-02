<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Alquilify - Servicios</title>
    <jsp:include page="componentes/librerias.jsp"/>
</head>
<body>
	
	<jsp:include page="componentes/navbar.jsp"/>
	
	     <!-- Page Content-->
        <div class="container px-4 px-lg-5">
		
			<div class="card bg-light alquilify-mt alquilify-mb py-4 text-center">
                <div class="card-body">
				<h3>Bienvenido a Alquilify: Tu Solución en Alquiler de Propiedades</h3>
				<p class=" m-0">Encuentra tu hogar perfecto con Alquilify, la plataforma líder en el alquiler de propiedades. Nuestra 
				misión es facilitar y agilizar el proceso de alquiler, brindándote una experiencia sin complicaciones y garantizando
				 la satisfacción tanto de propietarios como de inquilinos.</p>
				<p class=" m-0">This call to action card is a great place to showcase some important information or display a clever tagline!</p>
				</div>
            </div>
		
            <!-- Rows -->
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-uno.PNG" alt="..." /></div>
                <div class="col-lg-4">
                    <h1 class="font-weight-light">Controla los gastos de tus inmuebles</h1>
					<p class=" m-0">Cuando posees uno o varios inmuebles, es fundamental llevar un control preciso de los gastos asociados a ellos. Esto te permitirá optimizar tus finanzas y mantener una gestión efectiva de tus propiedades. </p>
                	<a class="btn btn-light alquilify-btn" href="/cuenta">Crear usuario en Aquilify!</a>
                </div>
            </div>
			
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
			    <div class="col-lg-4">
                    <h1 class="font-weight-light">Accede a tus datos desde distintos dispositivos</h1>
                    <p>
En la era digital en la que vivimos, la capacidad de acceder a tus datos desde diferentes dispositivos se ha convertido en una necesidad fundamental. Ya sea que estés trabajando, estudiando o simplemente organizando tu vida personal, poder acceder a tu información de manera rápida y sencilla 
                    </p>
                </div>
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-dos.PNG" alt="..." /></div>
            </div>
			
	        <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-8"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/servicio-tres.PNG" alt="..." /></div>
                <div class="col-lg-4">
                    <h1 class="font-weight-light">Supervisa los servicios de tus inmuebles </h1>
                    <p>
                    Realiza comparativas de precios y calidad: Asegúrate de obtener el mejor valor por tu dinero al contratar servicios para tus inmuebles. Realiza comparativas de precios y calidad entre diferentes proveedores, solicitando cotizaciones y referencias
                     </p>
                </div>
            </div>					
						
		</div>
	
	<jsp:include page="componentes/footer.jsp"/>
		
</body>
</html>