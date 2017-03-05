<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.usuario != null and sessionScope.usuario.tipo == 'a'}">
        <!DOCTYPE html>
        <html lang="es" ng-app="mi-aplicacion">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel admin | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/angular.min.js"></script>                
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bloquearDesbloquear.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/actualizarProductos.js"></script>
                <script type="text/javascript">
                    var app = angular.module('mi-aplicacion', []);
                    function controlador($scope) {
                        $scope.inicializar = function () {
                            $scope.forma = "marca";
                            $scope.marca = "todas";
                            $scope.categoria = "todas";
                            $scope.oferta = "";
                            $scope.porcentaje = "porcentaje";
                            $scope.signo = "disminuir";
                        };
                        $scope.inicializar();
                    }
                    app.controller('micontrolador', controlador);
                </script>   
            </head>
            <body class="container-fluid" ng-controller="micontrolador">
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
                            <li><a href="${pageContext.servletContext.contextPath}/mostrarPedidos">Ver pedidos</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm">Actualizar Productos</a></li>
                            <li  class="warning active"><a href="#">Actualizar precios</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarProAdm?stock=bajo">Productos Stock Bajo</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
                    <!-- Listado de productos -->
                    <div class="col-md-offset-1 col-md-8">
                        <h3 class="text-center">Actualizar precios</h3>
                        <section>
                            <c:if test="${requestScope.error != null and  requestScope.error == 'ok'}">
                                <div class="alert alert-success text-center center-block alert-dismissable col-xs-offset-1">
                                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    <strong>Los precios han sido actualizados correctamente</strong>
                                </div>
                            </c:if>
                            <c:if test="${requestScope.error != null and  requestScope.error != 'ok'}">
                                <div class="alert alert-danger text-center center-block alert-dismissable col-xs-offset-1">
                                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    <strong><c:out value="${requestScope.error}" /></strong>
                                </div>
                            </c:if>
                            <form role="form" class="form-horizontal row" name="formulario" id=formulario" method="post" action="${pageContext.servletContext.contextPath}/actualizarPrecios">

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block">
                                    Por marca: <input type="radio" name="forma" id="forma" ng-model="forma" value="marca" checked="true"/>
                                    Por categoría: <input type="radio" name="forma" id="forma" ng-model="forma" value="categoria"/>
                                </div>

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block" ng-hide="forma === 'categoria'">
                                    <label for="marca" class="control-label">Marca</label>
                                    <select id="marca" name="marca" ng-model="marca" class="form-control">
                                        <option value="todas" selected>Todas</option>
                                        <c:forEach items="${marcas}" var="marca">
                                            <option value="${marca.idMarca}">${marca.denominacion}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block" ng-hide="forma === 'marca'">
                                    <label for="categoria" class="control-label">Categoría</label>
                                    <select id="categoria" name="categoria" ng-model="categoria" class="form-control" selected>
                                        <option value="todas">Todas</option>
                                        <c:forEach items="${categorias}" var="categoria">
                                            <option value="${categoria.idCategoria}">${categoria.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block">
                                    <label for="oferta" class="control-label">Oferta</label>
                                    <select id="oferta" name="oferta" size="3" class="form-control">
                                        <option value="todos">Todos</option>
                                        <option value="n" selected>No</option>
                                        <option value="s">Si</option>
                                    </select>
                                </div>

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block">
                                    <label for="porcentaje" class="control-label">Porcentaje</label>
                                    <select id="porcentaje" name="porcentaje" ng-model="porcentaje" size="1" class="form-control">
                                        <option value="porcentaje">Elegir porcentaje</option>
                                        <c:forEach begin="1" end="100" var="por" varStatus="loop">
                                            <c:if test="${loop.index % 5 == 0}">
                                                <option value="${loop.index}">${loop.index}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div style="margin-bottom: 10px" class="input-group col-sm-4 center-block">
                                    Disminuir: <input type="radio" name="signo" ng-model="signo" value="disminuir" checked/>
                                    Aumentar: <input type="radio" name="signo" ng-model="signo" value="aumentar"/>
                                </div>

                                <div>
                                    <div class="input-group col-md-12 text-center">
                                        <input type="submit" class="btn btn-success" name="actualizar" value="Actualizar"/>
                                    </div>
                                </div>
                            </form>

                        </section>

                    </div>
                </div>
                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>
