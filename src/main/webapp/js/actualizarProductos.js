/* Funciones principalmente para la actualización de los productos */

function actualizarProducto(contexto, idProducto, valor, campo) {
    //Solo hacemos la accion si no introducimos caracters
    if (!isNaN(idProducto) && !isNaN(valor) && valor >= 0) {
        var url = contexto + "/actualizarProductos?idPro=" + idProducto + "&val=" + valor + "&cam=" + campo;
        var peticion = false;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                accActualizarProducto(this.responseText);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    }
}


function accActualizarProducto(respuesta) {
    var mensaje;
    if (respuesta == "error") {
        mensaje = "No se ha podido actualizar el producto";
    } else if (respuesta != "ok") {
        mensaje = "No se ha podido actualizar el producto con id " + this.responseText;
        document.getElementById(campo).value = this.responseText;
    }

    if (respuesta == "error" || respuesta != "ok") {

        document.getElementById("alerta-producto").innerHTML = "<div class=\"alert alert-warning text-center alert-dismissable\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>" + mensaje + "</strong></div>";
    }
}