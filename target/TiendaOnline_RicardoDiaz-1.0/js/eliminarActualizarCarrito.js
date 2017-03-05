/* 
    Funciones principalmente para eliminar las lineas de pedidos del carrito o 
    para actualizar la cantidad de los productos que están en el mismo
 */

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

function actualizarCantidad(contexto, idPedido, numLinea, cantidad, stock) {
    //Solo hacemos la accion si no introducimos caracters
    if ((stock - cantidad) < 0) { //NUEVO
        $("#alerta-cantidad").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>La cantidad de producto introducida (" + cantidad + ") es mayor que el stock (" + stock + ") </strong></div>");
        $("#alerta-cantidad").fadeIn(500);
        $("#alerta-cantidad").fadeOut(5000);
//Si no hay problemas detectado llamamos al controlador con ajax
    }else if (!isNaN(numLinea) && !isNaN(cantidad) && cantidad > 0) {
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