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
<title>Ver persona</title>
</head>

<body>
      <form method="POST" action="ActualizarPersona">
            <input name="id" id="id" value="${persona.id}" type="hidden"/><br>
            Nombre: <c:out value="${persona.nombre}"/><br>
            Primer Apellido: <c:out value="${persona.apellido1}"/><br>
            Segundo Apellido: <c:out value="${persona.apellido2}"/><br>
            Correo Electrónico: <c:out value="${persona.correo}"/><br>
            <br>
            <br>
            Teléfonos:<br>
            <div id="telefonosDelModelo">
            	<c:forEach items="${persona.telefonos}" var="telefono" varStatus="status">
            		<div id="divTelefono_${status.count}">
            			#<c:out value="${status.count}"/>&nbsp;:
            			&nbsp;<c:out  value="${telefono.telefono}"/>
            			<br>
            		</div>
            	</c:forEach>
            </div>            
        </form>
 </body>
</html>