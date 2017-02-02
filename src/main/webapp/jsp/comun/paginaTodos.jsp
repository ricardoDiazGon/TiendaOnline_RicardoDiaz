<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/aniadirAlCarrito.js"></script>
    </head>
    <body class="container-fluid" onload="carousel()">
        <c:set var="opt" value="tod" />
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
                <li class="active">Todos los productos</li>
            </ol>
        </div>    

        <!-- Secciones -->
        <div id="secciones" class="container center-block row">

            <!-- OFERTAS DEL MES -->
            <section id="productos" class="col-md-12">
                <h2>TODOS LOS PRODUCTOS</h2>


                <!-- Hacemos el número de productos por página -->
                <div id="filtros" class="col-md-12 row">
                    <c:set value="8,10,12,14,16,18,20" var="productosPorPagina"/>
                    <div class="col-md-3 col-md-offset-6" onChange="location = document.getElementById('prodxpag').value;">
                        <select id="prodxpag" class="form-control " size="1" name="npr">
                            <c:forTokens delims="," items="${productosPorPagina}" var="pxp">
                                <c:if test="${pxp == requestScope.proxpag}">
                                    <option selected value="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&param=${idCat}&ord=${requestScope.orden}&pxp=${pxp}">Productos por página: ${pxp}</option>
                                </c:if>
                                <c:if test="${pxp != requestScope.proxpag}">
                                    <option value="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&param=${idCat}&ord=${requestScope.orden}&pxp=${pxp}">${pxp}</option>
                                </c:if>
                            </c:forTokens>
                        </select>
                    </div>

                    <!-- Método para hacer la ordenacion y sepamos donde poner el option seleccionado para saber que orden hay -->
                    <c:set value="Nombre, Precio ascendente, Precio descendente, Popularidad" var="ordenacion"/>              
                        <div class="col-md-3">
                            <select id="ordenar" class="form-control" onChange="location = document.getElementById('ordenar').value;">
                                <c:forTokens delims="," items="${ordenacion}" varStatus="loop" var="forma">
                                    <c:if test="${requestScope.orden == loop.index +1}">
                                        <option selected value="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&ord=${loop.index +1}&pxp=${requestScope.proxpag}">Ordenado por ${fn:toLowerCase(forma)}</option>
                                    </c:if>
                                    <c:if test="${requestScope.orden != loop.index +1}">
                                        <option value="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&ord=${loop.index +1}&pxp=${requestScope.proxpag}">${forma}</option>
                                    </c:if>
                                </c:forTokens>
                            </select>
                        </div>
                    </div>      


                    <div class="row col-md-12">
                        <c:forEach items="${productosCat}" var="pro">
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
                    </div>


                    <!-- Paginación -->
                    <div class=" container col-md-12 text-center center-block">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${requestScope.pag}" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.index == requestScope.actual}">
                                        <li class="active"><a href="#">${loop.index}</a></li> 
                                        </c:when>
                                        <c:otherwise>
                                        <li><a href="${pageContext.servletContext.contextPath}/navProductos?opt=${opt}&pag=${loop.index}&ord=${requestScope.orden}&pxp=${requestScope.proxpag}">${loop.index}</a></li> 
                                        </c:otherwise>
                                    </c:choose>

                            </c:forEach>
                        </ul>
                        
                    </div>
                        </section>
                    </div>



                    <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
                    <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
                    <jsp:include page="/jsp/componentes/pie.jsp"/>
                    </body>
                    </html>
