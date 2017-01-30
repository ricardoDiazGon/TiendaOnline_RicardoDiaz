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
    document.getElementById("boton-confDir").disabled = false;
}


function validarPago(contexto, idPedido){
    //Con esta función conseguimos validar en html5 el formulario sin que haga el action
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoFinal.jsp");
    alert(idPedido);
    return false;
}