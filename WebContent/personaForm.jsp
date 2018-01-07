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
<title>Agergar persona</title>
</head>

<body>
      <form method="POST" action="AgregarPersona">
            
            Nombre: <input name="nombre" id="nombre"/><br>
            Primer Apellido: <input name="apellido1" id="apellido1"/><br>
            Segundo Apellido: <input name="apellido2" id="apellido2"/><br>
            Correo Electrónico: <input name="email" id="email"/><br>
            <br>
            <br>
            Teléfonos:<br>
            <div id="telefonos">
            </div>
            
            <input type="button" value="Añadir otro telefono" onclick="javascript:addTelefono()"/>
            <br> 
            <br>
            <input type="submit" value="Agregar persona" />
        </form>
 </body>
</html>