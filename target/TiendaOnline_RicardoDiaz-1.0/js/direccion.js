/* 
 Funciones donde ejecutamos la llamada para buscar la localidad y provincia que concuerde con el 
 codigo postal que le pasamos. Es una busqueda dinámica con ajax
 */

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
    } else {
        vaciar();
    }
}

//Función para tratar el fichero json que se nos devuelve con la direccion
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

//Vaciamos los campos si no se pulsa algo correcto, para garantizar la integridad de los datos
function vaciar() {
    document.getElementById("Provincia").value = "";
    document.getElementById("Poblacion").value = "";
    document.getElementById("IdPueblo").value = "";
    document.getElementById("IdProvincia").value = "";
}

