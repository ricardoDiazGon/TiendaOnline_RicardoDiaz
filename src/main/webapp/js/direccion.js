function getPueblos(contexto) {
    var cp = document.getElementById("CodigoPostal").value;
    if (cp.length == 5) {
        var url = contexto + "/direccionAjax?cp=" + cp;
        var peticion = false;
        peticion = new XMLHttpRequest();
        peticion.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                tratar_fichero(this);
            }
        };

        peticion.open("GET", url, true);
        peticion.send();
    }else{
        vaciar();
    }
}

function tratar_fichero(json) {
    var listaPueblos = JSON.parse(json.responseText)

    if (listaPueblos == null) {
        vaciar();
    } else {
        for (var i = 0; i < listaPueblos.length; i++) {
            if (i == 0) {
                document.getElementById("Provincia").value = listaPueblos[i].provincia.nombre;
                document.getElementById("Poblacion").value = listaPueblos[i].nombre;
                document.getElementById("IdPueblo").value = listaPueblos[i].IdPueblo;
                document.getElementById("IdProvincia").value = listaPueblos[i].provincia.IdProvincia;
                break;
            }
        }
    }
}

function vaciar() {
    document.getElementById("Provincia").value = "";
    document.getElementById("Poblacion").value = "";
    document.getElementById("IdPueblo").value = "";
    document.getElementById("IdProvincia").value = "";
}