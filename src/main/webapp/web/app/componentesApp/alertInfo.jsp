<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%-- Acceder al parámetro para marcar el item selected --%>
<% String info = request.getParameter("alertInfo"); %>
<% String tipo = request.getParameter("alertTipo"); %>

<% if(info != null && !info.isEmpty()) { %>						
	<!-- alert-success o alert-danger -->
	<div class="alert alert-<%=tipo%> alert-dismissible fade show" role="alert">
		<%= info %>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
	</div>
<% } %>