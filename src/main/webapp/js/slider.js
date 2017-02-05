/* 
 Esta es la función para nuestro slider, ya que tiene que controlar el movimiento de 
 los productos y que vaya de uno a otro...
 */

$(document).ready(function () {
    $('#myCarousel').carousel({
        interval: 2000
    });

    var clickEvent = false;
    $('#myCarousel').on('click', '.nav a', function () {
        clickEvent = true;
        $('.nav li').removeClass('active');
        $(this).parent().addClass('active');
    }).on('slid.bs.carousel', function (e) {
        if (!clickEvent) {
            var count = $('.menu-slider').children().length - 1;
            var current = $('.menu-slider li.active');
            current.removeClass('active').next().addClass('active');
            var id = parseInt(current.data('slide-to'));

            if (count == id) {
                $('.menu-slider li').first().addClass('active');
            }
        }
        clickEvent = false;
    });
});