/* Es este fichero llevamos la gestión del avance del carrito implementando las cosas necesarias
 Y además también llevamos la función para mostrar factura que también será usada en el panel de
 cliente y administrador para ver la factura. Además en el carrito también se ve la factura al finalizar
 */

function irCarritoDatosIni(contexto) {
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoIni.jsp");
}

function irCarritoDatosPer(contexto) {
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoDatosPer.jsp");
}

function irCarritoDatosDir(contexto) {
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoDatosDir.jsp");
}

function irCarritoDatosPago(contexto) {
    var idDireccion = document.getElementById("idDireccion").value;
    var url = contexto + "/aniadirDireccionCarrito?idDir=" + idDireccion;
    $.ajax({url: url, success: function (result) {
        }});
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoPago.jsp");
}

//Función para sacar los datos de una direccion elegida en el carrito
function elegirDireccion(contexto, idDireccion) {
    if (!isNaN(idDireccion)) {
        var url = contexto + "/direccionAjax?idDir=" + idDireccion;
        var peticion = false;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                tratarElegirDireccion(this);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    } else {
        document.getElementById("boton-confDir").disabled = true;
    }
}

function tratarElegirDireccion(direccion) {

    var direc = JSON.parse(direccion.responseText);

    document.getElementById("direccion2").value = direc.Direccion;
    document.getElementById("codigoPostal2").value = direc.CodigoPostal;
    document.getElementById("poblacion2").value = direc.NombrePueblo;
    document.getElementById("provincia2").value = direc.NombreProvincia;
    document.getElementById("telefono2").value = direc.Telefono;
    document.getElementById("idDireccion").value = direc.IdDireccion;
    document.getElementById("boton-confDir").disabled = false;
}


function validarPago(contexto, idPedido) {
    //Con esta función conseguimos validar en html5 el formulario sin que haga el action

    var url = contexto + "/finalizarCarrito?idPed=" + idPedido;

//Esto es ajax pero con jquery. 
    $.ajax({url: url, success: function (result) {
            if (result != "" && result != "ok") {
                $("#panel-carrito").html(result);
            }
        }});

    return false;
}

function mostrarFactura(contexto, idPedido) {
    var url = contexto + "/mostrarFactura?idPed=" + idPedido;

//Esto es ajax pero con jquery. 
    $.ajax({url: url, success: function (result) {

        }});

    document.getElementById("mostrar-factura").innerHTML = "";

    //Dormimos un poco el proceso para que de tiempo a ejecutarse el servlet (1 seg)
    //sino puede ser que se cargue antes la ventana de que el servlet termine de ejecutarse y haya fallos
    setTimeout(function () {
        $("#mostrar-factura").load(contexto + "/jsp/componentes/panelFactura.jsp");
        $('#panel-factura').modal('toogle');
        $('#panel-factura').modal('show');
        document.getElementById("numCarrito").innerHTML = "0";
    }, 1000);

}

function ocultarFactura() {
    document.getElementById("mostrar-factura").innerHTML = "";
}

//En esta función el usuario nos dice que ya ha recibido su producto
//Por tanto, este pasaría de enviado a recibido.
function recibirProducto(contexto, idPedido) {
    var url = contexto + "/mostrarPedidos?idPed=" + idPedido;

//Esto es ajax pero con jquery. 
    $.ajax({url: url, success: function (result) {

        }});
}