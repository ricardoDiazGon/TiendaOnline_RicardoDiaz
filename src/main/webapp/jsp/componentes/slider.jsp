<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container visible-lg visible-md">
    <div id="myCarousel" class="carousel slide col-md-10 col-md-offset-1" data-ride="carousel">
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/portatil.jpg" style="height: 200px; width:800px">
                <div class="carousel-caption">
                    <h3>Producto1</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img src="${pageContext.servletContext.contextPath}/imagenes/sobremesa.jpg" style="height: 200px; width:800px">
                <div class="carousel-caption">
                    <h3>Producto2</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/discoduro.jpg" style="height: 200px; width:800px">
                <div class="carousel-caption">
                    <h3>Producto3</h3>
                </div>
            </div>
            <!-- End Item -->
            <div class="item">
                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/logo.png" style="height: 200px; width:800px">
                <div class="carousel-caption">
                    <h3>Producto4</h3>
                </div>
            </div>
            <!-- End Item -->
        </div>
        <!-- End Carousel Inner -->
        <ul class="nav nav-pills nav-justified">
            <li data-target="#myCarousel" data-slide-to="0" class="active"><a href="#">About<small>Lorem
                        ipsum dolor sit</small></a></li>
            <li data-target="#myCarousel" data-slide-to="1"><a href="#">Projects<small>Lorem ipsum
                        dolor sit</small></a></li>
            <li data-target="#myCarousel" data-slide-to="2"><a href="#">Portfolio<small>Lorem ipsum
                        dolor sit</small></a></li>
            <li data-target="#myCarousel" data-slide-to="3"><a href="#">Services<small>Lorem ipsum
                        dolor sit</small></a></li>
        </ul>
    </div>
    <!-- End Carousel -->
</div>
