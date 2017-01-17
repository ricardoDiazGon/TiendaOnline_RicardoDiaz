$(document).ready(function () {

//Muestra el botón de carrito cuando pasamos por encima de un producto        
    $("div.producto").mouseover(function (event) {
        $(this).find("p.btn-carrito").css("visibility", "visible");
    });

//Oculta el botón de carrito cuando el ratón sale del producto
    $("div.producto").mouseleave(function (event) {
        $(this).find("p.btn-carrito").css("visibility", "hidden");
    });

});
        