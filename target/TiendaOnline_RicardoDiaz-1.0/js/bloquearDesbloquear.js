/* 
 En este fichero js vamos a bloquear y desbloquear usuarios, así como a descatalogar y poner en catalogo 
 los productos
 */

/* Usuarios */
function bloquearUsuario(contexto, idUsuario) {
    accBloqueoDesbloqueo(contexto, "usuario", idUsuario, "bloquear", contexto);
}

function desbloquearUsuario(contexto, idUsuario) {
    accBloqueoDesbloqueo(contexto, "usuario", idUsuario, "desbloquear", contexto);
}

/* Productos */
function descatalogarProducto(contexto, idProducto) {
    accBloqueoDesbloqueo(contexto, "producto", idProducto, "descatalogar", contexto);
}

function catalogarProducto(contexto, idProducto) {
    accBloqueoDesbloqueo(contexto, "producto", idProducto, "catalogar", contexto);
}

//Función puede ser bloquear y desbloquear usuarios y productos
function accBloqueoDesbloqueo(contexto, entidad, id, accion, contexto) {
    var url = contexto + "/bloqDesbloqAjax?entidad=" + entidad + "&id=" + id + "&accion=" + accion;
    var peticion = false;
    peticion = new XMLHttpRequest();
    peticion.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (entidad == "usuario") {
                cambiarFilaUsuario(id, accion, contexto);
            } else {
                cambiarFilaProducto(id, accion, contexto, this.responseText);
            }
        }
    };

    peticion.open("GET", url, true);
    peticion.send();
}


//Funcion que actualiza los datos en el jsp, para que si hemos bloqueado, aparezca como bloqueado usuarios
function cambiarFilaUsuario(id, accion, contexto) {
    var cadena;

    if (accion == "bloquear") {
        cadena = "\<button class=\"btn btn-danger btn-sm\"  title=\"Desbloquear\" onclick=\"desbloquearUsuario('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ban-circle\">\</span>\</button>";
    } else {
        cadena = "\<button class=\"btn btn-success btn-sm\" title=\"Bloquear\" onclick=\"bloquearUsuario('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ok-sign\">\</span>\</button>";
    }
    document.getElementById("usuario" + id).innerHTML = cadena;
}

//Funcion que actualiza los datos en el jsp, para que si hemos descatalogado, aparezca como descatalogado productos
function cambiarFilaProducto(id, accion, contexto, mensaje) {
    var cadena;
    if (mensaje == "") {
        if (accion == "descatalogar") {
            cadena = "\<button class=\"btn btn-danger btn-sm\"  title=\"Catalogar\" onclick=\"catalogarProducto('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ban-circle\">\</span>\</button>";
        } else {
            cadena = "\<button class=\"btn btn-success btn-sm\" title=\"Descatalogar\" onclick=\"descatalogarProducto('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ok-sign\">\</span>\</button>";
        }
        document.getElementById("producto" + id).innerHTML = cadena;
    } else {
        $("#alerta-producto").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>" + mensaje + "</strong></div>");
        $("#alerta-producto").fadeIn(1000);
        $("#alerta-producto").fadeOut(6000);
    }

}

