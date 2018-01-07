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
<title><c:out value="${accion}"/></title>
</head>

<body>
      <form id="formMsg" method="POST" action="./">
      	Operaci&oacute;n exitosa:<br>
      	<c:out value="${mensaje}"/><br>
      	<a href="javascript:listar()">Regresar a Lista de Personas</a>
      </form>
 </body>
</html>