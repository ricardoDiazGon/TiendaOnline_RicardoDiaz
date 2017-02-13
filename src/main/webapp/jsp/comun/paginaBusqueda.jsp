<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFO Albarregas</title>
        <jsp:include page="/jsp/componentes/meta.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/efectosProductos.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/aniadirAlCarrito.js"></script>
    </head>
    <body class="container-fluid" onload="carousel()">
        <c:set var="opt" value="busqueda"/>
        <div id="contenedor-arriba">
            <!-- Cabecera -->
            <jsp:include page="/jsp/componentes/cabecera.jsp"/>

            <!-- Navegación -->
            <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        </div>
        <!-- Miga de pan -->
        <div class="row">
            <ol class="breadcrumb">           
                <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                <li class="active">Busqueda avanzada</li>
            </ol>
        </div>    

        <!-- Secciones -->
        <div id="secciones" class="container center-block row">

            <!-- OFERTAS DEL MES -->
            <section id="productos" class="col-md-12">
                <h2>RESULTADO DE LA BÚSQUEDA</h2>

                <div class="row col-md-12">
                    <c:choose>
                        <c:when test="${fn:length(requestScope.productosBus) > 0}">

                            <c:forEach items="${requestScope.productosBus}" var="pro">
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="producto thumbnail text-center">
                                        <a href="${pageContext.servletContext.contextPath}/navProductos?opt=amp&param=${pro.idProducto}">
                                            <c:forEach begin="0" end="0" items="${pro.imagenes}" var="imag">
                                                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" alt="${pro.denominacion}">
                                            </c:forEach>
                                            <div class="caption">
                                                <h4 class="deno">${pro.denominacion}</h4>
                                                <c:forEach begin="1" end="${pro.rating}"><span class="estrella glyphicon glyphicon-star"></span></c:forEach>
                                                <h3 class="precio"><fmt:formatNumber value="${pro.precioUnitario}" type="currency"/></h3>                                   
                                            </div>
                                        </a>

                                        <!-- Si no hay usuario registrado botones comprar disabled -->    
                                        <c:set value="btn-success" var="tipoBoton" />
                                        <c:if test="${sessionScope.usuario.cliente == null}">
                                            <c:set var="dis" value="disabled"/>
                                            <c:set value="btn-default" var="tipoBoton" />
                                        </c:if>                                           

                                        <div class="caption">
                                            <p class="text-center btn-carrito"><button ${dis} class="btn ${tipoBoton} btn-md btn-block" role="button" onclick="aniadirProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', '${pro.stock}', '1')">Añadir al carrito <span class="glyphicon glyphicon-shopping-cart"></span></button></p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </c:when>
                        <c:otherwise>
                            <div class="datosContacto" style="margin: 50px 0px;">
                                <h2 class="text-center">No se han encontrado resultados</h2>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </section>

        </div>



        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
