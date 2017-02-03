function eliminarLineaCarrito(contexto, idPedido, numLinea) {
    realizarEliminacionCarrito(contexto, idPedido, numLinea);
}

function eliminarTodoCarrito(contexto, idPedido) {
    realizarEliminacionCarrito(contexto, idPedido, "null");
}

function realizarEliminacionCarrito(contexto, idPedido, numLinea) {

    var url = contexto + "/eliminarCarrito?npe=" + idPedido + "&nli=" + numLinea;
    var peticion = false;
    peticion = new XMLHttpRequest();
    peticion.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            actualizarCarrito(contexto);
        }
    };

    peticion.open("GET", url, true);
    peticion.send();

}

function actualizarCantidad(contexto, idPedido, numLinea, cantidad) {
    //Solo hacemos la accion si no introducimos caracters
    if (!isNaN(numLinea) && !isNaN(cantidad) && cantidad > 0) {
        var url = contexto + "/modificarCantidadCarrito?npe=" + idPedido + "&nli=" + numLinea + "&can=" + cantidad;
        var peticion = false;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                actualizarCarrito(contexto);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    }
}


function actualizarCarrito(contexto) {
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoIni.jsp");
}