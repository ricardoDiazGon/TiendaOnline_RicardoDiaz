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
            <c:if test="${requestScope.errorCliente != null and requestScope.errorCliente != 'ok'}">
                <div class="col-md-12 alert alert-danger text-center center-block alert-dismissable col-xs-offset-1 col-xs-6">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong><c:out value="${requestScope.errorCliente}" /></strong>
                </div>
            </c:if>

            <c:if test="${requestScope.errorDirec != null and requestScope.errorDirec == 'ok'}">
                <div class="col-md-12 alert alert-success text-center center-block alert-dismissable col-xs-offset-1 col-xs-6">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Los datos de cliente han sido guardados correctamente</strong>
                </div>
            </c:if>
            <form id="datos-user" role="form"  method="post" action="${pageContext.servletContext.contextPath}/registro">
                <div class="panel-body">
                    <c:if test="${requestScope.errorCliente != null and requestScope.errorCliente != 'ok'}">
                        <div class="alert alert-danger" role="alert">
                            <strong><c:out value="${requestScope.errorCliente}" /></strong>
                        </div>
                    </c:if>
                    <!-- Controlamos si nos llega algún error -->                 
                    <div style="margin-bottom: 25px" class="form-group col-xs-6">
                        <label for="nombre" class="control-label">Nombre</label>
                        <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Nombre" pattern="\S{1-25}" value="${nombre}" required ${readonly}>                                        
                    </div>

                    <div style="margin-bottom: 25px" class="form-group col-xs-6">
                        <label for="apellidos" class="control-label">Apellidos</label>
                        <input id="apellidos" type="text" class="form-control" name="apellidos" placeholder="Apellidos" value="${apellidos}" required ${readonly}>                                        
                    </div>

                    <div style="margin-bottom: 25px" class="form-group col-xs-6">
                        <label for="NIF" class="control-label">NIF</label>
                        <input id="NIF" type="text" class="form-control" name="NIF" placeholder="NIF/DNI" ${readonly} required value="${nif}">                                        
                    </div>
                    <c:if test="${readonly != null}">
                        <div style="margin-bottom: 25px" class="form-group col-xs-6">
                            <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                            <input id="fechaNacimiento" type="text" class="form-control" name="fechaNacimiento" required value="<fmt:formatDate type="date" 
                                            dateStyle="long" timeStyle="long"  timeZone="GTM+1"
                                            value="${fechaNacimiento}" />" readonly placeholder="dd/mm/aaaa"/>
                        </div> 
                    </c:if>
                    <c:if test="${readonly == null}">
                        <div style="margin-bottom: 25px" class="form-group col-xs-6">
                            <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                            <input id="fecha" type="text" class="form-control" name="fechaNacimiento" required value="<fmt:formatDate type="date" 
                                            dateStyle="long" timeStyle="long"  timeZone="GTM+1"
                                            value="${fechaNacimiento}" />" placeholder="dd/mm/aaaa" pattern="\d{2}/\d{2}/\d{4}"/>
                        </div> 
                    </c:if>
                </div>

                <c:if test="${readonly == null}">     
                    <div class="panel-footer">
                        <div class="input-group col-xs-12 text-center">
                            <input type="submit" class="btn btn-success" name="${nameSubmit}" value="${valueSubmit}"/>
                        </div>
                    </div>
                </c:if>
            </form>   



    <div class="panel-footer text-center container-fluid">
        <a class="btn btn-primary btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosIni('${pageContext.servletContext.contextPath}')" >Atrás</a>
        <button class="btn btn-success btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosDir('${pageContext.servletContext.contextPath}')">Realizar pedido</button>
    </div>