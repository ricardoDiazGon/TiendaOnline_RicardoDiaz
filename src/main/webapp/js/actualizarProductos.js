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


/* Funciones para poner o quitar productos de oferta */
function ofertarProducto(contexto, idProducto) {
    accOfertar(contexto, idProducto, "ofertar");
}

function noOfertarProducto(contexto, idProducto) {
    accOfertar(contexto, idProducto, "noOfertar");
}

function accOfertar(contexto, id, accion) {
    var url = contexto + "/ponerOferta?id=" + id + "&accion=" + accion;
    var peticion = false;
    peticion = new XMLHttpRequest();
    peticion.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            cambiarFilaOferta(id, accion, contexto);
        }
    };

    peticion.open("GET", url, true);
    peticion.send();
}


function cambiarFilaOferta(id, accion, contexto) {
    var cadena;

        if (accion == "noOfertar") {
            cadena = "\<button class=\"btn btn-danger btn-sm\"  title=\"Poner en oferta\" onclick=\"ofertarProducto('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ban-circle\">\</span>\</button>";
        } else {
            cadena = "\<button class=\"btn btn-success btn-sm\" title=\"Quitar de ofertas\" onclick=\"noOfertarProducto('" + contexto + "', '" + id + "')\" >\<span class=\"glyphicon glyphicon-ok-sign\">\</span>\</button>";
        }
        document.getElementById("productoOfe" + id).innerHTML = cadena;

}