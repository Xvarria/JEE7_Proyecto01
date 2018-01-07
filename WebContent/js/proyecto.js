function addTelefono(){
	var isOkToAdd = true;
	var count = 1;
	for(index = 1; index <= 10; index++){
		var inputId = '#telefono_' + index;
		if( $(inputId).length ){
			if ($(inputId).val() === ""){
				alert("Para agregar otro teléfono, los campos actuales deben tener información...");
				isOkToAdd = false;
			}
			count++;
		}
	}
	if (count > 10){
		alert("10 teléfonos es el máximo por persona...");
		isOkToAdd = false;		
	}
	if (isOkToAdd){
		$("#telefonos").append('<div id="divTelefono_'+count+'"> #'+count+'&nbsp;:&nbsp;<input name="telefono_'+count+'" id="telefono_'+count+'"/> &nbsp;<input type="button" id="eliminar_'+count+'" value="Eliminar" onclick="javascript:eliminarCampo('+count+')"/><br></div>');
	}
}

function eliminarCampo(id){
	$("#divTelefono_"+id).remove();
}

function verDetalle(id){
	$("#idPersona").val(id);
	var form = $("#formListar");
	form.attr("action", "./VerPersona"); 
	form.attr("method", "get");
	form.submit();
}

function actualizar(id){
	$("#idPersona").val(id);
	var form = $("#formListar");
	form.attr("action", "./ActualizarPersona"); 
	form.attr("method", "get");
	form.submit();
}

function eliminar(id){
	if (confirm('Esta seguro que desea eliminar la persona y todos sus teléfonos?')) {
		$("#idPersona").val(id);
		var form = $("#formListar");
		form.attr("action", "./EliminarPersona"); 
		form.attr("method", "post");
		form.submit();
	}
}

function agregar(){
	var form = $("#formListar");
	form.attr("action", "./AgregarPersona"); 
	form.attr("method", "get");
	form.submit();
}

function cargar(){
	var form = $("#formListar");
	form.attr("action", "./CargarPersonas"); 
	form.attr("method", "post");
	form.submit();
}

function listar(){
	var form = $("#formMsg");
	form.attr("action", "./ListarPersonas"); 
	form.attr("method", "get");
	form.submit();
}

function atras(){
	var form = $("#formListar");
	$("#nuevaBusqueda").val("NO");
	$("#pagina").val(parseInt($("#pagina").val())-1);
	form.submit();
}

function siguiente(){
	var form = $("#formListar");
	$("#nuevaBusqueda").val("NO");
	$("#pagina").val(parseInt($("#pagina").val())+1);
	form.submit();
}

function busqueda(){
	var form = $("#formListar");
	$("#nuevaBusqueda").val("SI");
	form.submit();
}

