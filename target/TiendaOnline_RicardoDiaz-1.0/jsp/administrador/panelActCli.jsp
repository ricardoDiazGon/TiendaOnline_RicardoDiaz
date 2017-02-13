<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panel.jsp">Datos de administrador</a></li>
                            <li class="warning active"><a href="#">Actualizar clientes</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/mostrarPedidos">Ver pedidos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm">Actualizar productos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm?stock=bajo">Productos Stock Bajo</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de usuarios -->
                    <div class="col-md-offset-1 col-md-8">
                        <h3 class="text-center">Lista de Usuarios</h3>
                        <div class="table-responsive">
                            <!-- Al table le pongo la id de tabla productos porque ya tiene asignado un css, pero no vamos a usar ese
                                id para nada más -->
                            <table id="tablaProductos" class="table table-bordred table-striped">
                                <thead>
                                <th>Id</th>
                                <th>Email (UserName)</th>
                                <th>Fecha de actividad</th>
                                <th>Bloquear</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listaUsuarios}" var="usu">
                                        <c:if test="${usu.tipo == 'u'}">
                                            <tr>
                                                <td>${usu.idUsuario}</td>
                                                <td>${usu.email}</td>
                                                <td><fmt:formatDate type="date" 
                                                                dateStyle="long" timeStyle="long"  timeZone="GMT+1"
                                                                value="${usu.ultimoAcceso}" /> <fmt:formatDate type="time" timeStyle="short"
                                                                timeZone="GMT+1"
                                                                value="${usu.ultimoAcceso}" />
                                                </td>
                                                <c:choose>
                                                    <c:when test="${usu.bloqueado == 'n'}">
                                                        <td>
                                                            <p id="usuario${usu.idUsuario}" data-placement="top" data-toggle="tooltip">
                                                                <button class="btn btn-success btn-sm" title="Bloquear" onclick="bloquearUsuario('${pageContext.servletContext.contextPath}', '${usu.idUsuario}')">                   
                                                                    <span class="glyphicon glyphicon-ok-sign"></span>
                                                                </button>
                                                            </p>
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>
                                                            <p id="usuario${usu.idUsuario}" data-placement="top" data-toggle="tooltip">
                                                                <button class="btn btn-danger btn-sm"  title="Desbloquear" onclick="desbloquearUsuario('${pageContext.servletContext.contextPath}', '${usu.idUsuario}')">
                                                                    <span class="glyphicon glyphicon-ban-circle"></span>
                                                                </button>
                                                            </p>
                                                        </td>
                                                    </c:otherwise>    
                                                </c:choose>

                                            </tr>
                                        </c:if>
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
                                            <li><a href="${pageContext.servletContext.contextPath}/actualizarUsuAdm?pag=${loop.index}">${loop.index}</a></li> 
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
