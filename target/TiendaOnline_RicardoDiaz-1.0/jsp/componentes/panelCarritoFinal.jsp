<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel-heading"><h4>TU CARRITO | PEDIDO COMPRADO</h4></div>
<div class="panel-body">

    <div class="row">
        <ul class="breadcrumb2">
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosIni('${pageContext.servletContext.contextPath}')">Datos del pedido</a></li>
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Datos personales</a></li>
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosDir('${pageContext.servletContext.contextPath}')">Datos de envío</a></li>
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosPago('${pageContext.servletContext.contextPath}')">Pago</a></li>
            <li class="completed"><a href="javascript:void(0);">Factura</a></li>
        </ul>
    </div>    


    <div style="margin-bottom: 25px" class="form-group col-xs-offset-3 col-xs-7">
        <label for="nombre" class="control-label">Titular de la tarjeta</label>
        <input id="nombre" type="text" class="form-control" name="nombre" placeholder="Titular de la tarjeta" maxlength="30" required />                                        
    </div>

    <div style="margin-bottom: 25px" class="form-group col-xs-offset-3 col-xs-7">
        <label for="numTarjeta" class="control-label">Número de tarjeta</label>
        <input id="numTarjeta" type="text" class="form-control" name="numTarjeta" placeholder="Número de la tarjeta" maxlength="20" required />                                        
    </div>

    <div style="margin-bottom: 25px" class="form-group col-xs-offset-3 col-xs-3">
        <label for="mes" class="control-label">Fecha de vencimiento</label>
        <div class="form-group form-inline">
            <select name="mes" id="mes" size="1" class="form-control  col-xs-6" required>
                <c:forEach var="mes" begin="1" end="12" varStatus="loop">
                    <option value="${loop.index}">${loop.index}</option>
                </c:forEach>

            </select>     
            <select class="form-control col-xs-6" size="1" required>
                <c:forEach var="anio" begin="2017" end="2025" varStatus="loop">
                    <option value="${loop.index}">${loop.index}</option>
                </c:forEach>
            </select> 
        </div>
    </div>

    <div style="margin-bottom: 25px" class="form-group col-xs-offset-1 col-xs-3">
        <label for="codigoSeg" class="control-label">CVV2/CVC2</label>
        <input id="codigoSeg" type="text" class="form-control"  name="codigoSeg" size="3" maxlength="3" placeholder="000" required>                                        
    </div>

</div>


<div class="panel-footer text-center container-fluid">
    <a class="btn btn-primary btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Atrás</a>
    <button id="boton-confPago" class="btn btn-success btn-md col-xs-offset-2 col-xs-3" disabled >Realizar pedido</button>
</div>