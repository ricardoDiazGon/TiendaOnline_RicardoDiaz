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
                            <li><a href="#">Ver pedidos</a></li>
                            <li  class="warning active"><a href="#">Descatalogar Productos</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de productos -->
                    <div class="col-md-offset-1 col-md-8">
                        <h3 class="text-center">Lista de productos</h3>
                        <div class="table-responsive">
                            <table id="mytable" class="table table-bordred table-striped">
                                <thead>
                                <th>Id</th>
                                <th>Denominacion</th>
                                <th>Marca</th>
                                <th>Descatalogar</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listaProductos}" var="pro">
                                        <tr>
                                            <td>${pro.idProducto}</td>
                                            <td>${pro.denominacion}</td>
                                            <td>${pro.denoMarca}</td>
                                            <c:choose>
                                                <c:when test="${pro.fueraCatalogo == 'n'}">
                                                    <td>
                                                        <p id="producto${pro.idProducto}" data-placement="top" data-toggle="tooltip">
                                                            <button class="btn btn-success btn-sm" title="Descatalogar" onclick="descatalogarProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}')">                   
                                                                <span class="glyphicon glyphicon-ok-sign"></span>
                                                            </button>
                                                        </p>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
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
