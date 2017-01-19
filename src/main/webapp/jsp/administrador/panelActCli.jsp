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
                            <li class="warning active"><a href="${pageContext.servletContext.contextPath}/actClientes">Actualizar clientes</a></li>
                            <li><a href="#">Ver pedidos</a></li>
                            <li><a href="#">Ver productos</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de usuarios -->
                    <div class="col-md-offset-1 col-md-8">
                        <h3 class="text-center">Lista de Usuarios</h3>
                        <div class="table-responsive">
                            <table id="mytable" class="table table-bordred table-striped">
                                <thead>
                                <th>Id</th>
                                <th>User Name</th>
                                <th>Fecha de actividad</th>
                                <th>Bloquear</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listaUsuarios}" var="usu">
                                        <c:if test="${usu.tipo == 'u'}">
                                            <tr>
                                                <td>${usu.idUsuario}</td>
                                                <td>${usu.userName}</td>
                                                <td>${usu.ultimoAcceso}</td>
                                                <c:choose>
                                                    <c:when test="${usu.bloqueado == 'n'}">
                                                        <td><p id="usuario${usu.idUsuario}" data-placement="top" data-toggle="tooltip"><button class="btn btn-danger btn-sm" title="Bloquear" onclick="bloquearUsuario('${pageContext.servletContext.contextPath}', '${usu.idUsuario}')"><span class="glyphicon glyphicon-ban-circle"></span></button></p></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><p id="usuario${usu.idUsuario}" data-placement="top" data-toggle="tooltip"><button class="btn btn-success btn-sm"  title="Desbloquear" onclick="desbloquearUsuario('${pageContext.servletContext.contextPath}', '${usu.idUsuario}')"><span class="glyphicon glyphicon-ok-sign"></span></button></p></td>
                                                    </c:otherwise>    
                                                </c:choose>

                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>

                            </table>

                            <div class="clearfix"></div>
                            <ul class="pagination pull-right">
                                <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
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
