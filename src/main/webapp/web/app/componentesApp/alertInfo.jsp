<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%-- Acceder al parámetro para marcar el item selected --%>
<% String info = request.getParameter("alertInfo"); %>

<% if(info != null && !info.isEmpty()) { %>
	<div class="alert alert-info alert-dismissible fade show" role="alert">
		<%= info %>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
	</div>
<% } %>