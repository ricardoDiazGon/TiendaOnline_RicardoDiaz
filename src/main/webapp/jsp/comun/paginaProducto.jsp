<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>INFO Albarregas</title>
        <jsp:include page="/jsp/componentes/meta.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>   
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/aniadirAlCarrito.js"></script>
    </head>
    <body class="container-fluid" onload="carousel()">
        <c:set var="opt" value="cat" />
        <!-- Cabecera -->
        <jsp:include page="/jsp/componentes/cabecera.jsp"/>

        <!-- Navegación -->
        <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        <c:forEach items="${productos}" var="pro">
            <c:if test="${pro.idProducto == requestScope.idProducto}">
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                        <li> <a href="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&param=${pro.idCategoria}">${pro.nombreCategoria}</a></li>
                        <li class="active">${pro.denominacion}</li>
                    </ol>
                </div>

                <!-- Secciones -->
                <div id="secciones" class="container center-block row">

                    <!-- Producto individual -->
                    <section id="producto" class="col-sm-12 datosContacto">

                        <div class="col-sm-5">
                            <article id="foto" class=" producto container-fluid row">
                                <jsp:include page="/jsp/componentes/sliderProd.jsp"/>
                            </article>

                        </div>

                        <div class="col-md-offset-1 col-md-6">
                            <article id="caracteristicas" class=" producto text-center container-fluid row">
                                <h1><b>${pro.denominacion}</b></h1>
                                <h3 class="precioProducto"><fmt:formatNumber value="${pro.precioUnitario}" type="currency"/></h3>
                                <h1><c:forEach begin="1" end="${pro.rating}"><span class="estrella glyphicon glyphicon-star"></span></c:forEach></h1>
                                </article>

                                <article class="producto container-fluid row">
                                    <h3><b>Marca: </b>${pro.denoMarca}</h3>
                                <h3><b>Disponibilidad: </b>${pro.stock} Unidades</h3>

                                <c:if test="${pro.oferta == 's'}">
                                    <h3>Producto en oferta. ¡Aproveche!</h3>
                                </c:if>
                            </article>
                            <div id="alerta-cantidad"></div>
                            <article class="producto container-fluid row">
                                <div class="col-md-5">
                                    <h4>Cantidad: </h4>
                                    <h4><input id="cantidadProducto" class="form-control" type="number" min="1" max="100" value="1" size="5"/></h4>
                                </div>

                                <!-- Si no hay usuario registrado botones comprar disabled -->    
                                <c:set value="btn-success" var="tipoBoton" />
                                <c:if test="${sessionScope.usuario.cliente == null}">
                                    <c:set var="dis" value="disabled"/>
                                    <c:set value="btn-default" var="tipoBoton" />
                                </c:if>  

                                <div class="text-center  col-md-offset-1  col-md-6">
                                    <h3 class="btn-carrito"><button ${dis} class="btn ${tipoBoton} btn-lg" role="button" onclick="aniadirProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', '${pro.stock}')">Añadir al carrito <span class="glyphicon glyphicon-shopping-cart"></span></button></h3>
                                </div>                 
                            </article>  


                        </div>

                        <div class="col-sm-12">
                            <article class="producto row col-sm-6">
                                <h2>Descripción</h3>
                                    <p>${pro.descripcion}</p>
                            </article>
                            <article class="producto col-md-5 col-sm-offset-1">
                                <h2>Características</h3>
                                    <table class="table table-responsive table-striped">
                                        <c:forEach items="${pro.caracteristicas}" var="carac">
                                            <tr><th>${carac.nombre}</th> <td>${carac.descripcion}</td></tr>
                                                </c:forEach>
                                    </table>
                            </article>

                        </div>
                    </section>


                </div>
            </c:if>
        </c:forEach>
        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
