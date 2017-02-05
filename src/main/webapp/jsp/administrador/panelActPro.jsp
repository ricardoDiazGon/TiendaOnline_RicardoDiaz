<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.usuario != null and sessionScope.usuario.tipo == 'a'}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel Administrador | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bloquearDesbloquear.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/actualizarProductos.js"></script>
            </head>
            <body class="container-fluid">
                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                        <li class="active">Panel de Administrador</li>
                    </ol>
                </div>
                <div id="secciones" class="container row">
                    <!-- Menú de navegación -->
                    <nav id="panel-control" class="col-md-3">
                        <h3>Panel de Control</h3>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panel.jsp">Datos de Administrador</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarUsuAdm">Actualizar clientes</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/mostrarPedidos">Ver pedidos</a></li>
                            <li  class="warning active"><a href="#">Actualizar Productos</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de productos -->
                    <div class="col-md-offset-1 col-md-8">
                        <h3 class="text-center">Lista de productos</h3>
                        <div>
                            <div id="alerta-producto">

                            </div>

                            <table id="tablaProductos" class="table table-condensed table-responsive table-striped">
                                <thead>
                                <th  class="text-center">Id</th>
                                <th class="text-center">Denominacion</th>
                                <th class="text-center">Marca</th>
                                <th class="text-center">Stock</th>
                                <th class="text-center">Stock Mínimo</th>
                                <th class="text-center">Precio Unitario</th>
                                <th class="text-center">Descatalogar</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listaProductos}" var="pro">
                                        <c:set var="marcarFila" value=""></c:set>
                                        <c:if test="${pro.stock < pro.stockMinimo}">
                                            <c:set var="marcarFila" value="bg-danger"></c:set>
                                        </c:if>
                                        <tr class="${marcarFila}">
                                            <td class="${marcarFila}">${pro.idProducto}</td>
                                            <td class="${marcarFila}">${pro.denominacion}</td>
                                            <td class="${marcarFila}">${pro.denoMarca}</td>
                                            <td class="${marcarFila}"><input type="number" name="stock" id="stock" min="0" max="9999" value="${pro.stock}" onchange="actualizarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', this.value, 'stock')"></td>
                                            <td class="${marcarFila}"><input type="number" name="stockMinimo" min="0" max="9999" id="stockMinimo" size="4"  maxlength="4" value="${pro.stockMinimo}" onchange="actualizarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', this.value, 'stockMinimo')"></td>
                                            <td class="${marcarFila}"><input type="number" name="precio" min="1" max="10000" id="precio"  value="${pro.precioUnitario}" onchange="actualizarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', this.value, 'precio')"></td>
                                                <c:choose>
                                                    <c:when test="${pro.fueraCatalogo == 'n'}">
                                                    <td class="${marcarFila}">
                                                        <p id="producto${pro.idProducto}" data-placement="top" data-toggle="tooltip">
                                                            <button class="btn btn-success btn-sm" title="Descatalogar" onclick="descatalogarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}')">                   
                                                                <span class="glyphicon glyphicon-ok-sign"></span>
                                                            </button>
                                                        </p>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="${marcarFila}">
                                                        <p id="producto${pro.idProducto}" data-placement="top" data-toggle="tooltip">
                                                            <button class="btn btn-danger btn-sm"  title="Catalogar" onclick="catalogarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}')">
                                                                <span class="glyphicon glyphicon-ban-circle"></span>
                                                            </button>
                                                        </p>
                                                    </td>
                                                </c:otherwise>    
                                            </c:choose>

                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>

                        </div>
                        <label>*Los productos con menos stock del mínimo aparecen sombreados</label>
                        <!-- Paginación -->
                        <div class=" container col-md-12 text-center center-block">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${requestScope.pag}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index == requestScope.actual}">
                                            <li class="active"><a href="#">${loop.index}</a></li> 
                                            </c:when>
                                            <c:otherwise>
                                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm?pag=${loop.index}">${loop.index}</a></li> 
                                            </c:otherwise>
                                        </c:choose>

                                </c:forEach>
                            </ul>

                        </div>

                    </div>

                </div>

                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>
