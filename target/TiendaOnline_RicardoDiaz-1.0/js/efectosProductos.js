/* Aquí hacemos que aparezca el botón de comprar producto cuando pasamos el ratón por encima 
 y que desaparezca. Lo hacen con efecto lento usando fade in y fade out de jquery
 */

$(document).ready(function () {

//Muestra el botón de carrito cuando pasamos por encima de un producto        
    $("div.producto").mouseover(function (event) {
        $(this).find("p.btn-carrito").css("visibility", "visible");
        $(this).find("p.btn-carrito").fadeIn(500);
    });

//Oculta el botón de carrito cuando el ratón sale del producto
    $("div.producto").mouseleave(function (event) {
        $(this).find("p.btn-carrito").fadeOut(500);
    });

});
        