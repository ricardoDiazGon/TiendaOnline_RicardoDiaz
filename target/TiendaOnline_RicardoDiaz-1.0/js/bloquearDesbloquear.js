function bloquearUsuario(contexto, idUsuario) {
    accBloqueoDesbloqueo(contexto, "usuario", idUsuario, "bloquear", contexto);
}

function desbloquearUsuario(contexto, idUsuario) {
    accBloqueoDesbloqueo(contexto, "usuario", idUsuario, "desbloquear", contexto);
}

//Entidad puede ser usuario o producto, y acción bloqueo o desbloqueo
function accBloqueoDesbloqueo(contexto, entidad, id, accion, contexto) {
    var url = contexto + "/bloqDesbloqAjax?entidad=" + entidad + "&id=" + id + "&accion=" + accion;
    var peticion = false;
    peticion = new XMLHttpRequest();
    peticion.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (entidad == "usuario") {
                cambiarFilaUsuario(id, accion, contexto);
            } else {
                //Para los productos
            }
        }
    };

    peticion.open("GET", url, true);
    peticion.send();
}


//Funcion que actualiza los datos en el jsp, para que si hemos bloqueado, aparezca como bloqueado
function cambiarFilaUsuario(id, accion, contexto) {
    var cadena;

    if (accion == "bloquear") {
        cadena = "\<button class=\"btn btn-success btn-sm\"  title=\"Desbloquear\" onclick=\"desbloquearUsuario('" +contexto + "', '" +id + "')\" >\<span class=\"glyphicon glyphicon-ok-sign\">\</span>\</button>";
    } else {
        cadena = "\<button class=\"btn btn-danger btn-sm\" title=\"Bloquear\" onclick=\"bloquearUsuario('" +contexto + "', '" +id + "')\" >\<span class=\"glyphicon glyphicon-ban-circle\">\</span>\</button>";
    }
    document.getElementById("usuario" + id).innerHTML = cadena;
}