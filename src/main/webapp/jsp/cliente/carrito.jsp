<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${sessionScope.carrito != null}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel Cliente | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/>  
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>  
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
                    <c:if test="${sessionScope.usuario.cliente.nombre == 'null'}">
                        <div class="alert alert-info text-center center-block alert-dismissable col-md-offset-1 col-md-6">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <a href="${pageContext.servletContext.contextPath}/jsp/cliente/panelCli.jsp"><strong>Regístrese como cliente para realizar compras</strong></a>
                        </div>
                    </c:if>
                </div>

                <!-- Menú de navegación -->
                <div id="secciones" class="container row">
                    <div class="col-md-offset-1 col-md-11 row">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h4>Datos de tu carrito</h4></div>
                            <div class="panel-body">
                                <table class="table table-responsive">
                                    <tr><th colspan="2">ARTÍCULO</th><th>PRECIO</th> <th>IVA (%)</th> <th>PRECIO Iva incl</th> <th>UNIDADES</th><th>TOTAL</th></tr>
                                            <c:forEach items="${sessionScope.carrito.lineasPedidos}" var="lineaPed">
                                                <c:forEach items="${productos}" var="pro">
                                                    <c:if test="${lineaPed.idProducto == pro.idProducto}">
                                                        <c:set value="${(pro.precioUnitario * general.iva)/100 + pro.precioUnitario}" var="precioIva"/>
                                                        <c:set value="${precioIva * lineaPed.cantidad}" var="precioTotalLinea"/>
                                                        <c:set value="${precioTotalLinea + precioTotal}" var="precioTotal"/>
                                                <tr>
                                                    <td><img height="75" width="75" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${pro.imagenes[1].imagen}" alt="${pro.imagenes[0].imagen}" /></td>
                                                    <td><c:out value="${pro.denominacion}"/></td>
                                                    <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${pro.precioUnitario}" /></td>
                                                    <td><fmt:formatNumber pattern="##" type="percent" value="${general.iva}"/></td>
                                                    <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${precioIva}"/></td>
                                                    <td><c:out value="${lineaPed.cantidad}"/></td>
                                                    <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${precioTotalLinea}"/></td>
                                                </tr>
                                            </c:if>

                                        </c:forEach>
                                    </c:forEach>
                                                <tr><td colspan="7"><h3 class="text-right">TOTAL: <fmt:formatNumber type="currency" minFractionDigits="2" value="${precioTotal}"/> </h3></td></tr>
                                </table>
                            </div>
                            <div class="panel-footer text-center">
                                <a class="btn btn-default btn-md" href="${pageContext.servletContext.contextPath}/carrito">Limpiar carrito</a>
                                <a class="btn btn-primary btn-md" href="${pageContext.servletContext.contextPath}/carrito">Seguir comprando</a>
                                <a class="btn btn-success btn-md" href="${pageContext.servletContext.contextPath}/carrito">Realizar pedido</a>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/componentes/pie.jsp"/>
            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>

