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
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/direccion.js"></script>
            </head>
            <body class="container-fluid">

                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb col-md-4">
                        <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
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
                            <strong>Los datos de cliente han sido guardados correctamente</strong>
                        </div>
                    </c:if>
                    <!-- Si venimos de introducir una dirección y nos ha devuelto ok -->
                    <c:if test="${requestScope.errorDirec == 'ok'}">
                        <div class="alert alert-success text-center center-block alert-dismissable col-md-offset-1 col-md-6">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Los datos de cliente han sido guardados correctamente</strong>
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

                        <!-- Solo mostramos las direcciones y la posibilidad de añadir si está registrado como cliente -->
                        <c:if test="${sessionScope.usuario.cliente != null}">
                            <!-- Lista Direcciones -->
                            <c:forEach items="${sessionScope.usuario.cliente.listaDirecciones}" var="dir">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4>Direccion ${dir.nombreDireccion}</h4>
                                    </div>

                                    <form id="datos-user" class="form-inline">
                                        <div class="panel-body">

                                            <div style="margin-bottom: 25px" class="form-group col-md-4">
                                                <label for="direccion2" class="control-label">Dirección</label>
                                                <input id="direccion2" type="text" class="form-control" name="direccion2" placeholder="Direccion" value="${dir.direccion}" ${readonly}>                                        
                                            </div>

                                            <div style="margin-bottom: 25px" class="form-group col-md-4">
                                                <label for="CodigoPostal2" class="control-label">Código Postal</label>
                                                <input id="CodigoPostal2" type="text" class="form-control" name="CodigoPostal2" placeholder="Código Postal" value="${dir.codigoPostal}" ${readonly}>                                        
                                            </div>

                                            <div style="margin-bottom: 25px" class="form-group col-md-4">
                                                <label for="Provincia2" class="control-label">Provincia</label>
                                                <input id="Provincia2" type="text" class="form-control" name="Provincia2" placeholder="Provincia" ${readonly} value="${dir.nombreProvincia}">                                        
                                            </div>

                                            <div style="margin-bottom: 25px" class="form-group col-md-4">
                                                <label for="Poblacion2" class="control-label">Población</label>
                                                <input id="Poblacion2" type="text" class="form-control" name="Poblacion2" placeholder="Poblacion" ${readonly} value="${dir.nombrePueblo}" />                                        
                                            </div>

                                            <div style="margin-bottom: 25px" class="form-group col-md-4">
                                                <label for="Telefono2" class="control-label">Teléfono</label>
                                                <input id="Telefono2" type="text" class="form-control" name="Telefono2" placeholder="Teléfono "${readonly} value="${dir.telefono}"/>
                                            </div>
                                        </div>

                                    </form>     
                                </div>
                            </c:forEach>
                            <!-- Fin lista direcciones -->
                            <!-- Añadir Direccon -->
                            <a name="dir" id="a"></a>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>Añadir Dirección</h4>
                                </div>

                                <form id="datos-user" class="form-inline" role="form"  method="post" action="${pageContext.servletContext.contextPath}/direcciones">
                                    <div class="panel-body">
                                        <c:if test="${requestScope.errorDirec != null and requestScope.errorDirec != 'ok'}">
                                            <div class="alert alert-danger" role="alert">
                                                <strong><c:out value="${requestScope.errorDirec}" /></strong>
                                            </div>
                                        </c:if>
                                        
                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="NombreDireccion" class="control-label">Nombre de la dirección</label>
                                            <input id="NombreDireccion" type="text" class="form-control" name="NombreDireccion" placeholder="Ej: Casa del pueblo" value="${requestScope.direc.nombreDireccion}">                                        
                                        </div>     
                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="direccion" class="control-label">Dirección</label>
                                            <input id="direccion" type="text" class="form-control" name="Direccion" placeholder="Direccion" value="${requestScope.direc.direccion}">                                        
                                        </div>                                      

                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="CodigoPostal" class="control-label">Código Postal</label>
                                            <input id="CodigoPostal" type="text" class="form-control" name="CodigoPostal" placeholder="Código Postal" value="${requestScope.direc.codigoPostal}" onkeyup="getPueblos('${pageContext.servletContext.contextPath}')">                                        
                                        </div>

                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="Provincia" class="control-label">Provincia</label>
                                            <input id="Provincia" type="text" class="form-control" name="Provincia" placeholder="Provincia" readonly value="${requestScope.direc.nombreProvincia}">                                        
                                        </div>  

                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="Poblacion" class="control-label">Población</label>
                                            <input id="Poblacion" type="text" class="form-control" name="Poblacion" placeholder="Poblacion" readonly value="${requestScope.direc.nombrePueblo}"/>                                     
                                        </div>

                                        <div style="margin-bottom: 25px" class="form-group col-md-4">
                                            <label for="Telefono" class="control-label">Teléfono</label>
                                            <input id="Telefono" type="text" class="form-control" name="Telefono" placeholder="Teléfono " value="${requestScope.direc.telefono}"/>
                                        </div>

                                        <input type="hidden" id="IdProvincia" name="IdProvincia" value="${requestScope.direc.idProvincia}"/>
                                        <input type="hidden" id="IdPueblo" name="IdPueblo" value="${requestScope.direc.idPueblo}"/>
                                    </div>

                                    <div class="panel-footer">
                                        <div class="input-group col-md-12 text-center">
                                            <input type="submit" class="btn btn-success" name="guardarDir" value="Guardar"/>
                                        </div>
                                    </div>
                                </form>     
                            </div>
                            <!-- Añadir Direccion -->
                        </c:if>
                    </div>
                </div>
                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>

