<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.carrito != null}">
        <div class="panel-heading"><h4>TU CARRITO | DATOS DE ENVÍO</h4></div>
        <div class="panel-body">
            <!-- Combo con las direcciones disponibles -->
            <div class="col-md-3" style="margin-bottom: 25px">
                <label>Nombre de la dirección</label>
                <select size="1" name="direcciones" class="form-control">
                    <option>Selecciona una dirección</option>
                    <c:forEach items="${sessionScope.usuario.cliente.listaDirecciones}" var="dir">
                        <option value="${dir.idDireccion}">${dir.nombreDireccion}</option>
                    </c:forEach>                
                </select>
            </div>
            <!-- Se rellena automáticamente cuando ponemos una dirección -->
            <div class="col-md-8 col-md-offset-1">
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

            <div class="panel-footer text-center container-fluid">
                <button class="btn btn-default btn-md col-xs-offset-2 col-xs-2" onclick="eliminarTodoCarrito('${pageContext.servletContext.contextPath}', '${carrito.idPedido}')" >Limpiar carrito</button>
                <a class="btn btn-primary btn-md col-xs-offset-1 col-xs-2" href="${pageContext.servletContext.contextPath}/navProductos">Seguir comprando</a>
                <button class="btn btn-success btn-md col-xs-offset-1 col-xs-2" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Realizar pedido</button>
            </div>

        </c:when>
        <c:otherwise>
            <div class="panel-heading"><h4>TU CARRITO | DATOS DE ENVÍO</h4></div>
            <div class="panel-body">
                <h2>Su carrito de la compra está vacío.</h2>
                <h3>Puede seguir comprando haciendo click <a href="${pageContext.servletContext.contextPath}/navProductos">aquí</a></h3>
            </div>       
        </div>
    </c:otherwise>
</c:choose>