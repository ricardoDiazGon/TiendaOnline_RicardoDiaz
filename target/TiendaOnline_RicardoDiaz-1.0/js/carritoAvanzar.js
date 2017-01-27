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
    $("#panel-carrito").load(contexto + "/jsp/componentes/panelCarritoDatosPago.jsp");
}

function aniadirDireccion(contexto) {
    var url = contexto + "/bloqDesbloqAjax?entidad=" + entidad + "&id=" + id + "&accion=" + accion;
    var peticion = false;
    peticion = new XMLHttpRequest();
    peticion.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            cambiarFilaUsuario(id, accion, contexto);
        }
    };

    peticion.open("GET", url, true);
    peticion.send();
}