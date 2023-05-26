
<jsp:include page="svgIcons.jsp"/>
<%-- Acceder al parámetro para marcar el item selected --%>
<% String itemMenu = request.getParameter("modulo"); %>
  <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 200px;">
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <span class="fs-4">Panel Alquilify </span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
      <li>
        <a href="/app/panelControl" class="nav-link text-white <% if(itemMenu.equals("home")) { %>active<% } %>" aria-current="page">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"/></svg>
          Home
        </a>
      </li>
      <li>
        <a href="/usuarios/modulo" class="nav-link text-white <% if(itemMenu.equals("usuarios")) { %>active<% } %>">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
          Usuarios
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white <% if(itemMenu.equals("Inmuebles")) { %>active<% } %>">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
          Inmuebles
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
          Dashboard
        </a>
      </li>
      <li>
        <a href="#" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"/></svg>
          Orders
        </a>
      </li>

    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <strong>NOMBRE</strong>
      </a>
      <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
        <li><a class="dropdown-item" href="#">New project...</a></li>
        <li><a class="dropdown-item" href="#">Settings</a></li>
        <li><a class="dropdown-item" href="#">Profile</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="#">Sign out</a></li>
      </ul>
    </div>
  </div>

  <div class="b-example-divider b-example-vr"></div>


<script src="/web/app/js/sidebars.js"></script>