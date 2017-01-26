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
                            <li class="warning active"><a href="#">Datos de Administrador</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actualizarUsuAdm">Actualizar clientes</a></li>
                            <li><a href="#">Ver pedidos</a></li>
                            <li><a href="#">Ver productos</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>

                    <div class="col-md-offset-1 col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h4>Datos de administrador</h4></div>
                            <form id="datos-user" class="form-inline" role="form">
                                <div class="panel-body">
                                    <div style="margin-bottom: 25px" class="form-group col-xs-4">
                                        <label for="email" class="control-label">Email (UserName)</label>
                                        <input id="email" type="text" class="form-control" name="email" value="${sessionScope.usuario.email}" placeholder="Email (UserName)" readonly="">                                        
                                    </div>
                                </div>
                            </form>     
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Cambiar la contraseña</h4>
                            </div>

                            <form id="user-clave" class="form-inline" role="form"  method="post" action="${pageContext.servletContext.contextPath}/actualizarUsuCli">
                                <div class="panel-body">

                                    <!-- Controlamos si nos llega algún error -->
                                    <c:if test="${requestScope.errorClave != null and requestScope.errorClave != 'ok'}">
                                        <div class="alert alert-danger text-center" role="alert">
                                            <strong><c:out value="${requestScope.errorClave}" /></strong>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.errorClave != null and requestScope.errorClave == 'ok'}">
                                        <div class="alert alert-success text-center" role="alert">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <strong><c:out value="La contraseña se ha cambiado correctamente"/></strong>
                                        </div>
                                    </c:if>                      

                                    <div style="margin-bottom: 25px" class="form-group col-xs-4">
                                        <label for="claveAnt" class="control-label">Contraseña antigua</label>
                                        <input id="claveAnt" type="password" class="form-control" name="claveAnt" required placeholder="Contraseña antigua">                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-xs-4">
                                        <label for="claveNue" class="control-label">Contraseña nueva</label>
                                        <input id="claveNue" type="password" class="form-control" name="claveNue" required placeholder="Contraseña nueva">                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-xs-4">
                                        <label for="claveNueRep" class="control-label">Repetir contraseña</label>
                                        <input id="claveNueRep" type="password" class="form-control" name="claveNueRep" required placeholder="Repetir contraseña" >                                        
                                    </div> 
                                </div>
                                <div class="panel-footer">
                                    <div class="input-group col-xs-12 text-center">
                                        <input type="submit" class="btn btn-success" name="updClave" value="Actualizar"/>
                                    </div>
                                </div>

                            </form>     

                        </div>
                    </div>
                </div>

                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>
