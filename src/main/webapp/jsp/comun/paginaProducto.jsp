<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFO Albarregas</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>   
    </head>
    <body class="container-fluid" onload="carousel()">
        <!-- Cabecera -->
        <jsp:include page="/jsp/componentes/cabecera.jsp"/>

        <!-- Navegación -->
        <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        <c:forEach items="${productos}" var="pro">
            <c:if test="${pro.idProducto == requestScope.idProducto}">
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.servletContext.contextPath}">Inicio</a></li>
                        <li class="active">${pro.nombreCategoria}</li>
                    </ol>
                </div>

                <!-- Secciones -->
                <div id="secciones" class="container center-block row">

                    <!-- Producto individual -->
                    <section id="producto" class="col-sm-12">

                        <article id="foto" class="col-sm-5 producto">
                            <jsp:include page="/jsp/componentes/sliderProd.jsp"/>
                        </article>



                        <article id="caracteristicas" class="col-md-offset-1 col-md-6 producto text-center">
                            <h3><b>${pro.denominacion}</b></h3>
                            <h3 class="precioProducto">${pro.precioUnitario} €</h3>
                            <p>Rating: <c:forEach begin="1" end="${pro.rating}"><span class="estrella glyphicon glyphicon-star"></span></c:forEach></p>
                            </article>

                            <article class="col-md-offset-1 col-md-6 producto">
                                <h3>Marca: ${pro.denoMarca}</h3>
                            <h3>Disponibilidad: ${pro.stock} Unidades</h3>

                            <c:if test="${pro.oferta == 's'}">
                                <h3>Producto en oferta. ¡Aproveche!</h3>
                            </c:if>
                        </article>
                        <article class="col-md-offset-1 col-md-6 producto container-fluid row">
                            <div class="col-md-5">
                                <h3><input class="form-control" type="number" min="1" max="100" value="1" size="5"/></h3>
                            </div>
                            <div class="text-center  col-md-offset-1  col-md-6">
                                <h3 class="btn-carrito"><a href="#" class="btn btn-success btn-lg" role="button">Añadir al carrito <span class="glyphicon glyphicon-shopping-cart"></span></a></h3>
                            </div>                 
                        </article>                        
                        <article class="col-md-12 producto">
                            <h2>Descripción</h3>
                            <p>${pro.descripcion}</p>
                        </article>

                        <article class="col-md-12 producto">
                            <h2>Características</h3>
                            <p>Aquí van las características</p>
                        </article>


                    </section>

                </div>
            </c:if>
        </c:forEach>
        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
