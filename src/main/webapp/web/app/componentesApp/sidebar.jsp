
<jsp:include page="svgIcons.jsp"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%-- Acceder al par�metro para marcar el item selected --%>
<% String itemMenu = request.getParameter("modulo"); %>
  <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 200px;">
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <span class="fs-4">Panel Alquilify </span>
    </a>
     	<p class="mt-3">
        <sec:authentication property="name"/><br>
        <span class="badge bg-warning text-dark">${sessionScope.tipoUsuarioSession}</span>      
       </p>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
      <li>
        <a href="/app/panelControl" class="nav-link text-white <% if(itemMenu.equals("home")) { %>alquilify-bg<% } %>" aria-current="page">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"/></svg>
          Home
        </a>
      </li>
      
      <sec:authorize access="hasAuthority('Administrador') or hasAuthority('Propietario')">
      <li>
        <a href="/usuarios/modulo" class="nav-link text-white <% if(itemMenu.equals("usuarios")) { %>alquilify-bg<% } %>">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
          Usuarios
        </a>
      </li>      
      </sec:authorize>
      
      <li>
        <a href="/inmuebles/modulo" class="nav-link text-white <% if(itemMenu.equals("inmuebles")) { %>alquilify-bg<% } %>">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
          Inmuebles
        </a>
      </li>
      
      <li>
        <a href="/alquileres/modulo" class="nav-link text-white <% if(itemMenu.equals("alquileres")) { %>alquilify-bg<% } %>">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"/></svg>
          Alquileres
        </a>
      </li>
      
       <sec:authorize access="hasAuthority('Administrador') or hasAuthority('Inquilino')">
      <li>
        <a href="/servicios/modulo" class="nav-link text-white <% if(itemMenu.equals("servicios")) { %>alquilify-bg<% } %>"">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
          Servicios
        </a>
      </li>
	 </sec:authorize>

    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <strong>${nombreUsuarioSession}</strong>
      </a>
      <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
        <!--<li><a class="dropdown-item" href="#">New project...</a></li>
        <li><a class="dropdown-item" href="#">Settings</a></li>-->
        <li><a class="dropdown-item" href="#">Perfil usuario</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="/logout">Cerrar Sesi�n</a></li>
      </ul>
    </div>
  </div>

  <div class="b-example-divider b-example-vr"></div>


<script src="/web/app/js/sidebars.js"></script>