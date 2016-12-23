<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container visible-lg visible-md visible-sm row center-block">
    <div id="myCarousel" class="carousel slide col-sm-12" data-ride="carousel">
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/portatil.jpg" style="height: 300px; width:1200px">
                <div class="carousel-caption">
                    <h3>Producto1</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img src="${pageContext.servletContext.contextPath}/imagenes/sobremesa.jpg" style="height: 300px; width:1200px">
                <div class="carousel-caption">
                    <h3>Producto2</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/discoduro.jpg" style="height: 300px; width:1200px">
                <div class="carousel-caption">
                    <h3>Producto3</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/logo.png" style="height: 300px; width:1200px">
                <div class="carousel-caption">
                    <h3>Producto4</h3>
                </div>
            </div>
            <!-- End Item -->
        </div>
        <!-- End Carousel Inner -->
        <ul class="nav nav-pills nav-justified">
            <li data-target="#myCarousel" data-slide-to="0" class="active"><a href="#">PRODUCTO 1
                    <small> 450 Leuros</small></a></li>
            <li data-target="#myCarousel" data-slide-to="1"><a href="#">PRODUCTO 2
                    <small> 300 Leuros</small></a></li>
            <li data-target="#myCarousel" data-slide-to="2"><a href="#">PRODUCTO 3
                    <small> 250 Leuros</small></a></li>
            <li data-target="#myCarousel" data-slide-to="3"><a href="#">PRODUCTO 4
                    <small> 500 Leuros</small></a></li>
        </ul>
    </div>
    <!-- End Carousel -->
</div>
