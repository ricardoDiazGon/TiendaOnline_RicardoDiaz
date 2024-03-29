<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${sessionScope.usuario != null and sessionScope.usuario.tipo == 'a'}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel admin | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bloquearDesbloquear.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/actualizarProductos.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/carritoAvanzar.js"></script>
            </head>
            <body class="container-fluid">
                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                        <li class="active">Panel de admin</li>
                    </ol>
                </div>
                <div id="secciones" class="container row">
                    <!-- Menú de navegación -->
                    <nav id="panel-control" class="col-md-3">
                        <h3>Panel de admin</h3>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panel.jsp">Datos de Administrador</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarUsuAdm">Actualizar clientes</a></li>
                            <li class="warning active"><a href="#">Ver pedidos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm">Actualizar Productos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panelActPrecios.jsp">Actualizar precios</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm?stock=bajo">Productos Stock Bajo</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de productos -->
                    <div class="col-md-offset-1 col-md-8">
                        <c:choose>
                            <c:when test="${fn:length(requestScope.listaPedidos) > 0}">
                                <h3 class="text-center">Lista de pedidos</h3>
                                <div>
                                    <div id="alerta-producto">

                                    </div>

                                    <table id="tablaProductos" class="table table-condensed table-responsive table-striped text-center">
                                        <thead>
                                        <th  class="text-center">Id</th>
                                        <th  class="text-center">Fecha y hora</th>
                                        <th class="text-center">Nombre y Apellidos del cliente</th>
                                        <th  class="text-center">DNI Cliente</th>
                                        <th  class="text-center">Base Imponible</th>
                                        <th  class="text-center">IVA</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Ver factura</th>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listaPedidos}" var="ped">

                                                <c:if test="${ped.estado == 'p'}"> 
                                                    <c:set var="marcarFila" value="bg-danger"/>
                                                    <c:set var="estado" value="PENDIENTE"/>
                                                </c:if>
                                                <c:if test="${ped.estado == 'x'}">
                                                    <c:set var="marcarFila" value="bg-info"/>
                                                    <c:set var="estado" value="ENVIADO"/>
                                                </c:if>
                                                <c:if test="${ped.estado == 'r'}">
                                                    <c:set var="marcarFila" value="bg-success"/>
                                                    <c:set var="estado" value="RECIBIDO"/>
                                                </c:if>
                                                <tr class="${marcarFila}">
                                                    <td class="${marcarFila}">${ped.idPedido}</td>
                                                    <td class="${marcarFila}"><fmt:formatDate type="date" 
                                                                                   dateStyle="long" timeStyle="long"  timeZone="GMT+1"
                                                                                   value="${ped.fecha}" /> <fmt:formatDate type="time" timeStyle="short"
                                                                                   timeZone="GMT+1"
                                                                                   value="${ped.fecha}" /></td>
                                                    <td class="${marcarFila}">${ped.cliente.nombre} ${ped.cliente.apellidos}</td>
                                                    <td class="${marcarFila}">${ped.cliente.NIF}</td>
                                                    <td class="${marcarFila} text-right"><fmt:formatNumber value="${ped.baseImponible}" 
                                                                                                type="currency"/></td>
                                                    <td class="${marcarFila} text-right"><fmt:formatNumber value="${ped.iva}" 
                                                                                                type="currency"/></td>
                                                    <td class="${marcarFila}">
                                                        ${estado}
                                                    </td> 
                                                    <td class="${marcarFila}"><button class="btn btn-md btn-success" onclick="mostrarFactura('${pageContext.servletContext.contextPath}', '${ped.idPedido}')">Ver factura <span class="glyphicon glyphicon-save-file"></span></button></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>

                                    </table>
                                    <label>*Los pedidos que están pendientes aparecen sombreados de rojo y los enviados de azul</label>
                                    <!-- Paginación -->
                                    <div class=" container col-md-12 text-center center-block">
                                        <ul class="pagination">
                                            <c:forEach begin="1" end="${requestScope.pag}" varStatus="loop">
                                                <c:choose>
                                                    <c:when test="${loop.index == requestScope.actual}">
                                                        <li class="active"><a href="#">${loop.index}</a></li> 
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="${pageContext.servletContext.contextPath}/mostrarPedidos?pag=${loop.index}">${loop.index}</a></li> 
                                                        </c:otherwise>
                                                    </c:choose>

                                            </c:forEach>
                                        </ul>

                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h2 class="text-center">No existen pedidos disponibles.</h2>
                            </c:otherwise>
                        </c:choose>


                    </div>

                </div>

                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>

        <div id="mostrar-factura">

        </div>