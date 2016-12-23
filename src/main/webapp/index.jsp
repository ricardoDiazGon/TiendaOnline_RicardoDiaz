<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Ricardo</title>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/>      
    </head>
    <body class="container-fluid" onload="carousel()">
        <!-- Cabecera -->
        <jsp:include page="/jsp/componentes/cabecera.jsp"/>

        <!-- Navegación -->
        <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>

        <!-- Miga de pan -->
        <div class="row">
            <div class="btn-group btn-breadcrumb">
                <a href="#" class="btn btn-info"><i class="glyphicon glyphicon-home"></i> Bienvenido a INFO Albarregas, tu Tienda Online de Informática</a>
            </div>
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
                    <c:forEach begin="0" end="8">
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="..." alt="...">
                                <div class="caption">
                                    <h3>Thumbnail label</h3>
                                    <p>...</p>
                                    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
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
