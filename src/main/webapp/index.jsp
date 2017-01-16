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

        <!-- Miga de pan -->
        <div class="row">
            <ol class="breadcrumb">
                <li class="active">Inicio</li>
            </ol>
        </div>

        <!-- Secciones -->
        <div id="secciones" class="container center-block row">
            <section id="slider" class="col-sm-12 visible-lg visible-md visible-sm ">
                <jsp:include page="/jsp/componentes/slider.jsp"/>
            </section>
            <!-- Productos -->
            <section id="productos" class="col-md-12">
                <h2>Productos</h2>
                <div class="row">
                    <c:forEach begin="0" end="8" items="${productos}" var="pro">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <c:forEach begin="0" end="0" items="${pro.imagenes}" var="imag">
                                    <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" alt="${pro.denominacion}">
                                </c:forEach>
                                <div class="caption">
                                    <h3>${pro.denominacion}</h3>
                                    <p class="text-center"><a href="#" class="btn btn-success" role="button">Añadir al carrito</a> <a href="#" class="btn btn-primary" role="button">Ver</a></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>

        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
