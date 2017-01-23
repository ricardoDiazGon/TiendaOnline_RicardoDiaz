function aniadirProducto(contexto, idProducto, cantidad) {
    if(cantidad == undefined){
        cantidad = document.getElementById("cantidadProducto").value;
    }
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

function eliminarProducto() {

}

function cambiosAniadir(idProducto, objetoJSON) {

    if(objetoJSON != null){
        var producto = JSON.parse(objetoJSON.responseText);
        document.getElementById("numCarrito").innerHTML = producto.lineasPedidos.length;
    }
    
}