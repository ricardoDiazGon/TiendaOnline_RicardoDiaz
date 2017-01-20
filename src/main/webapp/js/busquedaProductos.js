function buscarProd(contexto) {
    var texto = document.getElementById("buscar").value;
    if (texto.trim().length > 0) {
        var url = contexto + "/busquedaProdAjax?cad=" + texto;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                aniadirFilasProductos(this, contexto);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    }else{
        vaciarOcultar();
    }
}


function aniadirFilasProductos(objetoJSON, contexto) {

    var prodEncontrados = JSON.parse(objetoJSON.responseText)
    var contenido = "";
    if (prodEncontrados != null && prodEncontrados.length > 0) {

        $("#panelBuscar").css("visibility", "visible");
        for (var i = 0; i < prodEncontrados.length; i++) {
            contenido +=  "<tr><td><img src='" +contexto +"/imagenes/imagenesProductos/" +prodEncontrados[i].Imagenes[0].Imagen +"'/></td>" + 
                    "<td class='nombre'>" 
                    +"<a href=" +contexto + "/navProductos?opt=amp&param=" +prodEncontrados[i].IdProducto +">"
                    +prodEncontrados[i].Denominacion +"</a></td></tr>";
        }

        document.getElementById("listaBuscar").innerHTML = contenido;
    } else {
        vaciarOcultar();
    }

}

function vaciarOcultar() {
    document.getElementById("listaBuscar").innerHTML = "";
    $("#panelBuscar").css("visibility", "hidden");
}