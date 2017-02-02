//$(document).ready(function () {
//    $("#alerta-cantidad").fadeOut(6000);
//});

function aniadirProducto(contexto, idProducto, stock, cantidad) {

    if (cantidad == undefined) {
        cantidad = document.getElementById("cantidadProducto").value;
    }

    if (isNaN(cantidad) || cantidad == "" || cantidad % 1 != 0) {

        $("#alerta-cantidad").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>Por favor, introduzca un número entero en la cantidad</strong></div>");
        $("#alerta-cantidad").fadeIn(1000);
        $("#alerta-cantidad").fadeOut(3000);
    } else if ((stock - cantidad) < 0) {
        $("#alerta-cantidad").html(
                "<div class=\"alert alert-warning text-center alert-dismissable aviso-stock-usuario\">"
                + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
                + "<strong>La cantidad de producto introducida (" + cantidad + ") es mayor que el stock (" + stock + ") </strong></div>");
        $("#alerta-cantidad").fadeIn(1000);
        $("#alerta-cantidad").fadeOut(3000);

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