<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Inicializamos los datos para saber si tenemos que dar la oportunidad de registro del cliente o no -->
<c:set var="nameSubmit" value="addCliente"/>
<c:set var="valueSubmit" value="Registrar datos"/> 
<c:set var="nombre" value="${requestScope.nombre}"/>
<c:set var="apellidos" value="${requestScope.apellidos}"/>
<c:set var="nif" value="${requestScope.nif}"/>
<c:set var="fechaNacimiento" value="${requestScope.fechaNacimiento}"/>

<c:if test="${sessionScope.usuario.cliente.nombre != 'null'}">
    <c:set var="readonly" value="readonly" />
    <c:set var="nombre" value="${sessionScope.usuario.cliente.nombre}"/>
    <c:set var="apellidos" value="${sessionScope.usuario.cliente.apellidos}"/>
    <c:set var="nif" value="${sessionScope.usuario.cliente.NIF}"/>
    <c:set var="fechaNacimiento" value="${sessionScope.usuario.cliente.fechaNacimiento}"/>
</c:if>


<div class="panel-heading"><h4>TU CARRITO | DATOS DEL CLIENTE</h4></div>
<div class="panel-body">

    <div class="row">
        <ul class="breadcrumb2">
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosIni('${pageContext.servletContext.contextPath}')">Datos del pedido</a></li>
            <li class="active"><a href="javascript:void(0);">Datos personales</a></li>
            <li><a href="javascript:void(0);">Datos de envío</a></li>
            <li><a href="javascript:void(0);">Pago</a></li>
            <li><a href="javascript:void(0);">Factura</a></li>
        </ul>
    </div>    

    <c:if test="${requestScope.errorCliente != null and requestScope.errorCliente != 'ok'}">
        <div class="col-md-12 alert alert-danger text-center center-block alert-dismissable col-xs-offset-1 col-xs-6">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong><c:out value="${requestScope.errorCliente}" /></strong>
        </div>
    </c:if>

    <c:if test="${requestScope.errorCliente != null and requestScope.errorCliente == 'ok'}">
        <div class="col-md-12 alert alert-success text-center center-block alert-dismissable col-xs-offset-1 col-xs-6">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Los datos de cliente han sido guardados correctamente</strong>
        </div>
    </c:if>
    <form id="datos-user" role="form"  method="post" action="${pageContext.servletContext.contextPath}/registro">
        <!-- Controlamos si nos llega algún error -->                 
        <div style="margin-bottom: 25px" class="form-group col-xs-6">
            <label for="nombre" class="control-label">Nombre</label>
            <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Nombre" maxlength="25" pattern="\S{1-25}" value="${nombre}" required ${readonly}>                                        
        </div>

        <div style="margin-bottom: 25px" class="form-group col-xs-6">
            <label for="apellidos" class="control-label">Apellidos</label>
            <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" maxlength="40" value="${apellidos}" required ${readonly}>                                        
        </div>

        <div style="margin-bottom: 25px" class="form-group col-xs-6">
            <label for="NIF" class="control-label">NIF</label>
            <input id="NIF" type="text" class="form-control" name="NIF" placeholder="NIF/DNI" maxlength="9" ${readonly} required value="${nif}">                                        
        </div>
        <c:if test="${readonly != null}">
            <div style="margin-bottom: 25px" class="form-group col-xs-6">
                <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                <input id="fechaNacimiento" type="text" class="form-control" name="fechaNacimiento" required value="<fmt:formatDate type="date" 
                                dateStyle="long" timeStyle="long"  timeZone="GTM+1"
                                value="${fechaNacimiento}" />" readonly placeholder="dd/mm/aaaa"/>
            </div> 
        </c:if>


        <input type="hidden" name="formulario" value="carrito"/>
        <c:set var="disabled" value=""/>
        <c:if test="${readonly == null}">
            <div style="margin-bottom: 25px" class="form-group col-xs-6">
                <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                <input id="fecha" type="text" class="form-control" name="fechaNacimiento" required placeholder="dd/mm/aaaa" pattern="\d{2}/\d{2}/\d{4}"/>
            </div> 

            <div class="panel-footer">
                <div class="input-group col-xs-12 text-center">
                    <input type="submit" class="btn btn-success" name="${nameSubmit}" value="${valueSubmit}"/>
                </div>
            </div>
            <c:set var="disabled" value="disabled"/>
        </c:if>
    </form>   

</div>

<div class="panel-footer text-center container-fluid">
    <a class="btn btn-primary btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosIni('${pageContext.servletContext.contextPath}')" >Atrás</a>      
    <button class="btn btn-success btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosDir('${pageContext.servletContext.contextPath}')"  ${disabled}>Avanzar</button>
</div>

<!-- Pongo aquí el javascript de la fecha en vez de en la cabecera porque como esta pagina es un include se ve que no funciona... -->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/campoFecha.js"></script>