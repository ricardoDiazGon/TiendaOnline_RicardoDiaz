<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${sessionScope.usuario != null}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Carrito | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css" />
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/migasPanCarrito.css" />
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/eliminarActualizarCarrito.js"></script>  
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/direccion.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/carritoAvanzar.js"></script>

                <!-- Con estas librerías de jquery pongo el campo fecha normal para firefox y otros navegadores donde no funciona el type date -->
                <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/jquery-ui.css"> 
                <script src="${pageContext.servletContext.contextPath}/js/jquery-ui.js"></script> 

            </head>
            <body class="container-fluid">
                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb col-md-4">
                        <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                        <li class="active">Carrito</li>
                    </ol>
                </div>

                <!-- Menú de navegación -->
                <div id="secciones" class="container row">
                    <div class="col-xs-offset-1 col-xs-11 row">
                        <div id="panel-carrito" class="panel panel-default">
                            <!-- Cargamos la página donde está el panel del carrito (haciendo un poco modulares los jsp) -->
                            <c:choose>
                                <c:when test="${requestScope.datosPer != null}">
                                    <jsp:include page="/jsp/componentes/panelCarritoDatosPer.jsp"/>
                                </c:when>
                                <c:when test="${requestScope.datosDir != null}">
                                    <jsp:include page="/jsp/componentes/panelCarritoDatosDir.jsp"/>
                                </c:when>
                                <c:otherwise>
                                    <jsp:include page="/jsp/componentes/panelCarritoIni.jsp"/>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/componentes/pie.jsp"/>
            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>

