<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/proyecto.js"></script>
<style type="text/css">
table, th, td {
   border: 1px solid black;
}
</style>
<title>Listar personas</title>
</head>

<body>
      <form id="formListar" method="POST" action="ListarPersonas">
      	<input id="idPersona" name="idPersona" value="" type="hidden"><br>
      	<input type="button" onclick="javascript:agregar()" value ="Agregar nueva persona"><br>
      	<br>
      	La b&uacute;queda se aplica a cualquiera de los tres campos: Nombre, Apellido1, Apellido2, y no es Case Sensitive.
      	<br>
      	B&uacute;squeda: <input name="criterio" value="${criterio}"/><input type="button" onclick="javascript:busqueda()" value="Buscar"/>
      	<br>
      	<br>
      	<table style="border-style: solid;">
      	<thead>
      		<tr>
      			<th>Id</th>
      			<th>Nombre</th>
      			<th>Apellido1</th>
      			<th>Apellido2</th>
      			<th>Correo</th>
      			<th>Cantidad de Tels</th>
      			<th>Ver Detalle</th>
      			<th>Actualizar</th>
      			<th>Elimar</th>
      		</tr>
      	</thead>
      	<tbody>
      		<c:forEach items="${personas}" var="persona" varStatus="status">
      			<tr>
      			<td><c:out value="${persona.id}"/></td>
      			<td><c:out value="${persona.nombre}"/></td>
      			<td><c:out value="${persona.apellido1}"/></td>
      			<td><c:out value="${persona.apellido2}"/></td>
      			<td><c:out value="${persona.correo}"/></td>
      			<td><c:out value="${persona.telSize}"/></td>
      			<td><a href="javascript:verDetalle(${persona.id})">Detalle</a></td>
      			<td><a href="javascript:actualizar(${persona.id})">Actualizar</a></td>
      			<td><a href="javascript:eliminar(${persona.id})">Eliminar</a></td>      				
      			</tr>
      		</c:forEach>
      	</tbody>
      	</table>
      	<input name="nuevaBusqueda" id="nuevaBusqueda" value="${nuevaBusqueda}" type="hidden" />
      	<input id="pagina" name="pagina" value="${pagina}" type="hidden"/>
      	<input name="paginas" value="${paginas}" type="hidden"/>
      	<input name="regPorPagina" value="${regPorPagina}" type="hidden"/>
      	<input name="registros" value="${registros}" type="hidden"/>
      	<br>
      	Pagina: <c:out value="${pagina}"/> de: <c:out value="${paginas}"/> Páginas <br>
      	Registros por página <c:out value="${regPorPagina}"/>, total registros: <c:out value="${registros}"/>
      	<br>
      	<br>
      	<c:if test="${pagina ne 1}">
      		<input type="button" value="&lt; Atras" onclick="javascript:atras()">
      	</c:if>
      	- Navegacion -
      	<c:if test="${pagina ne paginas}">
      		<input type="button" value="Siguiente &gt;" onclick="javascript:siguiente()"/>
      	</c:if>
      	<br>
      	<br>
      	Para cargar datos de manera automática:
      	Genera 100 registros con datos "random" para pruebas.<br>
      	<input type="button" value="Cargar Personas (Auto)" onclick="javascript:cargar()"/><br>
                
     </form>
 </body>
</html>