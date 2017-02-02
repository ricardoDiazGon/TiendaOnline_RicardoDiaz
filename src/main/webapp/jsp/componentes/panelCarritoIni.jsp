<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.carrito != null}">
        <div class="panel-heading bg-primary">
            <h4>TU CARRITO | DATOS DEL PEDIDO</h4>
        </div>
        <div class="panel-body">
            <div class="center-block text-center">
                <div class="row">
                    <ul class="breadcrumb2  visible-lg visible-md">
                        <li class="active"><a href="javascript:void(0);">Datos del pedido</a></li>
                        <li><a href="javascript:void(0);">Datos personales</a></li>
                        <li><a href="javascript:void(0);">Datos de envío</a></li>
                        <li><a href="javascript:void(0);">Pago</a></li>
                    </ul>
                </div>
            </div>        
            <table id="tabla-carrito" class="table table-responsive table-striped text-center">
                <!--Al precio le quitamos el IVA, no se lo sumamos-->
                <tr><th colspan="2">ARTÍCULO</th><th>PRECIO</th> <th>IVA (%)</th> <th>IVA (€)</th> <th>UNIDADES</th><th>TOTAL</th>
                    <th>ELIMINAR</th></tr>
                        <c:forEach items="${sessionScope.carrito.lineasPedidos}" var="lineaPed">
                            <c:forEach items="${productos}" var="pro">
                                <c:if test="${lineaPed.producto.idProducto == pro.idProducto}">
                            <!-- Es 121% porque es el 100% del producto más el 21% del iva -->
                            <c:set value="${(pro.precioUnitario * general.iva)/121}" var="precioIva"/>
                            <c:set value="${pro.precioUnitario * lineaPed.cantidad}" var="precioTotalLinea"/>
                            <c:set value="${precioTotalLinea + precioTotal}" var="precioTotal"/>
                            <tr id="filaCarrito${lineaPed.numeroLinea}">
                                <td><img height="75" width="75" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${pro.imagenes[0].imagen}" alt="${pro.imagenes[0].imagen}" /></td>
                                <td><c:out value="${pro.denominacion}"/></td>
                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${pro.precioUnitario}" /></td>
                                <td><fmt:formatNumber pattern="##" type="percent" value="${general.iva}"/></td>
                                <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${precioIva}"/></td>
                                <td><input type="number" class="form-control" min="1" max="100" value="${lineaPed.cantidad}" onchange="actualizarCantidad('${pageContext.servletContext.contextPath}', '${lineaPed.idPedido}', '${lineaPed.numeroLinea}', this.value)"/></td>
                                <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${precioTotalLinea}"/></td>
                                <td><button onclick="eliminarLineaCarrito('${pageContext.servletContext.contextPath}', '${lineaPed.idPedido}', '${lineaPed.numeroLinea}')"><span class="glyphicon glyphicon-trash"></span></button></td>
                            </tr>
                        </c:if>

                    </c:forEach>
                </c:forEach>
                <c:set value="${precioTotal + general.gastosEnvio}" var="precioTotal"/>
                <tr><td colspan="8"><h4><b>Gastos de envío: <fmt:formatNumber maxFractionDigits="2" type="currency" value="${general.gastosEnvio}"/></b></h4></td></td></tr>
                <tr id="resul-carrito"><td colspan="8"><h3 class="text-right">TOTAL: <fmt:formatNumber type="currency" minFractionDigits="2" value="${precioTotal}"/> </h3></td></tr>
            </table>
        </div>
        <div class="panel-footer text-center container-fluid">
            <button class="btn btn-default btn-md col-xs-offset-2 col-xs-2" onclick="eliminarTodoCarrito('${pageContext.servletContext.contextPath}', '${carrito.idPedido}')" >Limpiar carrito</button>
            <a class="btn btn-primary btn-md col-xs-offset-1 col-xs-2" href="${pageContext.servletContext.contextPath}/navProductos">Seguir comprando</a>
            <button class="btn btn-success btn-md col-xs-offset-1 col-xs-2" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Realizar pedido</button>
        </div>

    </c:when>
    <c:otherwise>
        <div class="panel-heading"><h4>Datos de tu carrito</h4></div>
        <div class="panel-body">
            <h2>Su carrito de la compra está vacío.</h2>
            <h3>Puede seguir comprando haciendo click <a href="${pageContext.servletContext.contextPath}/navProductos">aquí</a></h3>
        </div>       
    </div>
</c:otherwise>
</c:choose>