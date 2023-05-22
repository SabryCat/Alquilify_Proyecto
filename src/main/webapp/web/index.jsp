<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Alquilify - Gesti�n �gil</title>
    <jsp:include page="componentes/librerias.jsp"/>
</head>
<body>

	<jsp:include page="componentes/navbar.jsp"/>

        <!-- Page Content-->
        <div class="container px-4 px-lg-5">
            <!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-12"><img class="img-fluid mb-4 mb-lg-0" src="/web/images/gestion-alquileres.png" alt="..." /></div>
                <div class="col-lg-12">
                    <h1 class="font-weight-light">Alquilify Gesti�n �gil</h1>
                    <h5>
	                   Nuestro equipo de expertos en alquileres est� comprometido a brindar soluciones integrales que se adapten a tus necesidades 
	                   espec�ficas. Ya sea que seas un propietario que busca maximizar tus inversiones
	                    o un inquilino en busca del hogar perfecto, Alquilify est� aqu� para ayudarte.
                    </h5>
                    <a class="btn btn-secondary alquilify-btn pt-3 " href="/contacto"> 
                    	<p>Cont�ctanos</p>
                    </a>                    
                </div>
            </div>
            <!-- Card-->
            <div class="card text-white bg-secondary alquilify-mb p-4 text-center">
                <div class="card-body">
					<h2 class="font-weight-light">
						Tu satisfacci�n es nuestra prioridad n�mero uno y trabajaremos incansablemente
	                 	para superar tus expectativas en cada interacci�n, algunos de nuestros servicios son:
                 	</h2>
                 </div>
            </div>
            <!-- Content Row-->
            <div class="row gx-4 gx-lg-5">
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Gestiones b�sicas</h2>
                            <p class="card-text">
                            <ul>
                            <li>Gesti�n de Usuarios </li>
							<li>Gesti�n de Inquilinos</li>
							<li>Gesti�n de Inmuebles</li>
                            </ul>
                            </p>
                        </div>
                        <div class="card-footer"><a class="btn btn-light alquilify-btn btn-sm"  href="/servicios">M�s Informaci�n</a></div>
                        
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Gestiones avanzadas</h2> 
                            <p class="card-text">
                           <ul>
                            <li>Actualizaciones de Renta </li>
							<li>Gesti�n de Cobro</li>
							<li>Gesti�n de Seguros</li>
                            </ul>
                            </p>
                        </div>
                        <div class="card-footer"><a class="btn btn-light alquilify-btn btn-sm"  href="/servicios">M�s Informaci�n</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Otras gestiones</h2>
                            <p class="card-text">
                            <ul>
                            <li>Actualizaciones de Renta </li>
							<li>Gesti�n de Fianzas</li>
							<li> Rescisiones de contrato</li>
                            </ul>
                            </p>
                        </div>
                        <div class="card-footer"><a class="btn btn-light alquilify-btn btn-sm" href="/servicios">M�s Informaci�n</a></div>
                    </div>
                </div>
            </div>
        
		    <!-- More content Row-->
            <div class="row gx-4 gx-lg-5 align-items-center alquilify-mb">
                <div class="col-lg-7"><img class="img-fluid mb-4 mb-lg-0 alquilify-publicidad" src="/web/images/home-publicidad.PNG" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">En Alquilify</h1>
                    <p>Entendemos lo importante que es para los propietarios y arrendatarios tener una experiencia fluida y
                     exitosa en el proceso de alquiler. Es por eso que nos enorgullece ofrecer servicios de gesti�n de alquileres de primera
                      clase, dise�ados para simplificar y optimizar cada paso del camino.</p>
                    <a class="btn btn-light alquilify-btn" href="/nosotros">Con�cenos</a>
                </div>
            </div>
		
		</div>

	<jsp:include page="componentes/footer.jsp"/>

</body>
</html>