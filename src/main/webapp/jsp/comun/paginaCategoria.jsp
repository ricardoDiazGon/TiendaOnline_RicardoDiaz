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
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/efectosProductos.js"></script>
    </head>
    <body class="container-fluid" onload="carousel()">
        <div id="contenedor-arriba">
            <!-- Cabecera -->
            <jsp:include page="/jsp/componentes/cabecera.jsp"/>

            <!-- Navegación -->
            <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        </div>

        <c:forEach items="${categorias}" var="cat">
            <c:if test="${cat.idCategoria == requestScope.idCategoria}">
                <c:set var="nombreCat" value="${cat.nombre}"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li>Inicio</li>
                        <li class="active">${cat.nombre}</li>
                    </ol>
                </div>   
            </c:if>
        </c:forEach>
        <!-- Secciones -->
        <div id="secciones" class="container center-block row">
            <!-- Productos -->
            <section id="productos" class="col-md-12">
                <c:if test="${nombreCat != null}">
                    <h2>Sección de ${nombreCat}</h2>
                </c:if>
                <c:if test="${nombreCat == null}">
                    <h2>ESTA PÁGINA NO EXISTE</h2>
                </c:if>
                <div class="row">
                    <c:forEach items="${productos}" var="pro">
                        <c:if test="${pro.idCategoria == requestScope.idCategoria}">
                            <div class="col-sm-6 col-md-4 col-lg-3">
                                <div class="producto thumbnail text-center">
                                    <a href="${pageContext.servletContext.contextPath}/navProductos?opt=amp&param=${pro.idProducto}">
                                    <c:forEach begin="0" end="0" items="${pro.imagenes}" var="imag">

                                        <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" alt="${pro.denominacion}">

                                    </c:forEach>
                                    <div class="caption">
                                        <h4 class="deno">${pro.denominacion}</h4>
                                        <c:forEach begin="1" end="${pro.rating}"><span class="estrella glyphicon glyphicon-star"></span></c:forEach>
                                        <h3 class="precio">${pro.precioUnitario} €</h3>                                   
                                    </div>
                                </a>
                                <div class="caption">
                                    <p class="text-center btn-carrito"><a href="#" class="btn btn-success btn-md btn-block" role="button">Añadir al carrito <span class="glyphicon glyphicon-shopping-cart"></span></a></p> 
                                </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </section>
        </div>

        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
