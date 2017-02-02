<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel-heading"><h4>TU CARRITO | DATOS FINAL</h4></div>

<div class="panel-header">
    <h2>Pedido finalizado</h2>
</div>
<div class="panel-body">

    <div class="row center-block text-center" style="margin-bottom: 50px;">
        <h2>Su pedido se ha gestionado satisfactoriamente</h2>
        <c:if test="${requestScope.excesoCantidad != null and requestScope.excesoCantidad != 'ok'}">
            <div id="aviso">
                <div class="alert alert-danger" style="color:rgb(200,0,0); margin: 20px 0px;">Los siguientes productos no están en stock actualmente, les serán enviados lo antes posible: ${requestScope.excesoCantidad}
                </div>
            </div>
        </c:if>
        <a class="btn btn-md btn-info" onclick="mostrarFactura('${pageContext.servletContext.contextPath}', '${sessionScope.idPedidoAct}')" >Ver factura</a>
        <a class="btn btn-md btn-default" href="${pageContext.servletContext.contextPath}/navProductos" >Volver a inicio</a>
    </div>

</div>

<div id="mostrar-factura">

</div>
