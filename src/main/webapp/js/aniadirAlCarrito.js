//Funciones para añadir productos al carrito

function aniadirProducto(contexto, idProducto, stock, cantidad) {
    if (cantidad == undefined) {
        cantidad = document.getElementById("cantidadProducto").value;
    }

//Controlamos que el numero metido sea conrrecto
    if (isNaN(cantidad) || cantidad == "" || cantidad % 1 != 0) {

        $("#alerta-cantidad").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>Por favor, introduzca un número entero en la cantidad</strong></div>");
        $("#alerta-cantidad").fadeIn(500);
        $("#alerta-cantidad").fadeOut(6000);
//Controlamos que la cantidad no sea mayor que el stock existente
    } else if ((stock - cantidad) < 0) {
        $("#alerta-cantidad").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>La cantidad de producto introducida (" + cantidad + ") es mayor que el stock (" + stock + ") </strong></div>");
        $("#alerta-cantidad").fadeIn(500);
        $("#alerta-cantidad").fadeOut(6000);
//Si no hay problemas detectado llamamos al controlador con ajax
    } else {
        var url = contexto + "/aniadirAlCarrito?pro=" + idProducto + "&can=" + cantidad;
        var peticion = false;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                cambiosAniadir(idProducto, this);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    }
}


function cambiosAniadir(idProducto, objetoJSON) {

    if (objetoJSON != null) {
        var producto = JSON.parse(objetoJSON.responseText);
        document.getElementById("numCarrito").innerHTML = producto.lineasPedidos.length;
    }

}