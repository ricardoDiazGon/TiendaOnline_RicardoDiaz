<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Inicializamos los datos para saber si tenemos que dar la oportunidad de registro del cliente o no -->
<c:set var="nameSubmit" value="addCliente"/>
<c:set var="valueSubmit" value="Registrar datos"/> 
<c:set var="nombre" value="${requestScope.nombre}"/>
<c:set var="apellidos" value="${requestScope.apellidos}"/>
<c:set var="nif" value="${requestScope.nif}"/>
<c:set var="email" value="${requestScope.email}"/>
<c:set var="fechaNacimiento" value="${requestScope.fechaNacimiento}"/>

<c:if test="${sessionScope.usuario.cliente != null}">
    <c:set var="readonly" value="readonly" />
    <c:set var="nombre" value="${sessionScope.usuario.cliente.nombre}"/>
    <c:set var="apellidos" value="${sessionScope.usuario.cliente.apellidos}"/>
    <c:set var="nif" value="${sessionScope.usuario.cliente.NIF}"/>
    <c:set var="email" value="${sessionScope.usuario.cliente.email}"/>
    <c:set var="fechaNacimiento" value="${sessionScope.usuario.cliente.fechaNacimiento}"/>
</c:if>


<c:choose>
    <c:when test="${sessionScope.usuario != null}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel Cliente | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/>  
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>    
            </head>
            <body class="container-fluid" onload="carousel()">

                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb col-md-4">
                        <li><a href="${pageContext.servletContext.contextPath}">Inicio</a></li>
                        <li class="active">Panel de Usuario</li>
                    </ol>

                    <!-- Si los usuarios no han dado de alta el cliente aun -->
                    <c:if test="${sessionScope.usuario.cliente == null}">
                        <div class="alert alert-info text-center center-block alert-dismissable col-md-offset-1 col-md-6">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Regístrese como cliente para realizar compras</strong>
                        </div>
                    </c:if>
                    <!-- Si cuando venimos de registrar el cliente nos ha devuelto ok -->
                    <c:if test="${requestScope.errorCliente == 'ok'}">
                        <div class="alert alert-success text-center center-block alert-dismissable col-md-offset-1 col-md-6">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Los datos han sido guardados correctamente</strong>
                        </div>
                    </c:if>
                </div>

                <!-- Menú de navegación -->
                <div id="secciones" class="container row">
                    <nav id="panel-control" class="col-md-3">
                        <h3>Panel de Control</h3>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/cliente/panel.jsp">Datos de Usuario</a></li>
                            <li class="warning active"><a href="#">Datos de Cliente</a></li>
                            <li><a href="#">Pedidos</a></li>
                            <li><a href="#">Facturas</a></li>
                            <li class="ultimo"><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok">Cerrar Sesión</a></li>
                        </ul>
                    </nav>

                    <div class="col-md-offset-1 col-md-8 row">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Datos de cliente</h4>
                            </div>

                            <form id="datos-user" class="form-inline" role="form"  method="post" action="${pageContext.servletContext.contextPath}/registro">
                                <div class="panel-body">
                                    <c:if test="${requestScope.errorCliente != null and requestScope.errorCliente != 'ok'}">
                                        <div class="alert alert-danger" role="alert">
                                            <strong><c:out value="${requestScope.errorCliente}" /></strong>
                                        </div>
                                    </c:if>
                                    <!-- Controlamos si nos llega algún error -->                 
                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="nombre" class="control-label">Nombre</label>
                                        <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Nombre" value="${nombre}" ${readonly}>                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="apellidos" class="control-label">Apellidos</label>
                                        <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" value="${apellidos}" ${readonly}>                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="NIF" class="control-label">NIF</label>
                                        <input id="NIF" type="text" class="form-control" name="NIF" placeholder="NIF/DNI" ${readonly} value="${nif}">                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="claveNueRep" class="control-label">Email</label>
                                        <input id="claveNueRep" type="text" class="form-control" name="email" placeholder="Email" ${readonly} value="${email}" />                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                                        <input id="fechaNacimiento" type="date" class="form-control" name="fechaNacimiento" ${readonly} value="${fechaNacimiento}"/>
                                    </div> 
                                </div>

                                <c:if test="${readonly == null}">     
                                    <div class="panel-footer">
                                        <div class="input-group col-md-12 text-center">
                                            <input type="submit" class="btn btn-success" name="${nameSubmit}" value="${valueSubmit}"/>
                                        </div>
                                    </div>
                                </c:if>
                            </form>     
                        </div>

                        <!-- Direcciones -->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Datos de cliente</h4>
                            </div>

                            <form id="datos-user" class="form-inline" role="form"  method="post" action="${pageContext.servletContext.contextPath}/registro">
                                <div class="panel-body">
              
                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="nombre" class="control-label">Nombre</label>
                                        <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Nombre" value="${nombre}" ${readonly}>                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="apellidos" class="control-label">Apellidos</label>
                                        <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" value="${apellidos}" ${readonly}>                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="NIF" class="control-label">NIF</label>
                                        <input id="NIF" type="text" class="form-control" name="NIF" placeholder="NIF/DNI" ${readonly} value="${nif}">                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="claveNueRep" class="control-label">Email</label>
                                        <input id="claveNueRep" type="text" class="form-control" name="email" placeholder="Email" ${readonly} value="${email}" />                                        
                                    </div>

                                    <div style="margin-bottom: 25px" class="form-group col-md-4">
                                        <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                                        <input id="fechaNacimiento" type="date" class="form-control" name="fechaNacimiento" ${readonly} value="${fechaNacimiento}"/>
                                    </div> 
                                </div>

                                <c:if test="${readonly == null}">     
                                    <div class="panel-footer">
                                        <div class="input-group col-md-12 text-center">
                                            <input type="submit" class="btn btn-success" name="${nameSubmit}" value="${valueSubmit}"/>
                                        </div>
                                    </div>
                                </c:if>
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

