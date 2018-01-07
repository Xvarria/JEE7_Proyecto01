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
<title>Actualizar persona</title>
</head>

<body>
      <form method="POST" action="ActualizarPersona">
            <input name="id" id="id" value="${persona.id}" type="hidden"/><br>
            Nombre: <input name="nombre" id="nombre" value="${persona.nombre}"/><br>
            Primer Apellido: <input name="apellido1" id="apellido1" value="${persona.apellido1}"/><br>
            Segundo Apellido: <input name="apellido2" id="apellido2" value="${persona.apellido2}"/><br>
            Correo Electrónico: <input name="email" id="email" value="${persona.correo}"/><br>
            <br>
            <br>
            Teléfonos:<br>
            <div id="telefonosDelModelo">
            	<c:forEach items="${persona.telefonos}" var="telefono" varStatus="status">
            		<div id="divTelefono_${status.count}">
            			#<c:out value="${status.count}"/>&nbsp;:
            			&nbsp;<input name="telefono_${status.count}" id="telefono_${status.count}" value="${telefono.telefono}"/>
            			&nbsp;<input type="button" id="eliminar_${status.count}" value="Eliminar" onclick="javascript:eliminarCampo('${status.count}')"/>
            			<input name="telefonoId_${status.count}" id="telefonoId_${status.count}" value="${telefono.id}" type="hidden"/>
            			<br>
            		</div>
            	</c:forEach>
            </div>
            <div id="telefonos">
            </div>
            
            <input type="button" value="Añadir otro telefono" onclick="javascript:addTelefono()"/>
            <br> 
            <br>
            <input type="submit" value="Actualizar persona" />
        </form>
 </body>
</html>