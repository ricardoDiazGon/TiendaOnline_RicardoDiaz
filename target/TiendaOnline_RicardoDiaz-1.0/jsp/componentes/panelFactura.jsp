<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="panel-factura" class="row modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg  row" role="document">
        <div class="modal-content">
            <div class="modal-body">

                <div class="col-xs-12 row">
                    <div style="margin-bottom: 25px" class="col-xs-4" >
                        <img src="${pageContext.servletContext.contextPath}/imagenes/logoINFOAlbarregas1.png" alt="logo-factura" style="height: 150px; width: 150px;" />
                    </div>

                    <div style="margin-bottom: 25px" class="col-xs-4">
                        <p>INFO Albarregas</p>
                        <p>CIF: 1234567890A</p>
                        <p>Camino viejo de Mirandilla s/n</p>
                        <p>CP: 06800 - Mérida</p>
                        <p>(Badajoz)</p>
                    </div>

                    <div style="margin-bottom: 25px" class="col-xs-4">
                        <h3>FACTURA Nº ${sessionScope.factura.numeroFactura}</h3>
                        <p><b>Fecha de factura: </b><fmt:formatDate type="both" 
                                                            dateStyle="long" timeStyle="long"  timeZone="GMT+1"
                                                            value="${usu.ultimoAcceso}" /></p>
                    </div>
                    <hr/>
                </div>

                <div class="col-xs-12 row">
                    <div class="col-xs-6">
                        <h3>Datos del cliente</h3>
                        <p>Nombre completo: ${sessionScope.factura.pedido.cliente.nombre} ${sessionScope.factura.pedido.cliente.apellidos}</p>
                        <p>DNI: ${sessionScope.factura.pedido.cliente.NIF}</p>
                    </div>
                    <div class="col-xs-6">
                        <h3>Dirección del cliente</h3>
                        <p>${sessionScope.factura.pedido.direccion.direccion}</p>
                        <p>CP: ${sessionScope.factura.pedido.direccion.codigoPostal} - ${sessionScope.factura.pedido.direccion.nombrePueblo} </p>
                        <p>(${sessionScope.factura.pedido.direccion.nombreProvincia})</p>
                    </div>
                </div>

                <div class="col-xs-12">

                    <table class="table table-responsive table-striped">
                        <tr>
                            <th>Producto</th> <th>Precio Unitario</th> <th>Cantidad</th> <th>Total Producto</th>
                        </tr>

                        <c:forEach items="${sessionScope.factura.pedido.lineasPedidos}" var="linea">
                            <tr>
                                <td>${linea.producto.denominacion}</td>
                                <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${linea.precioUnitario}"/></td>
                                <td>${linea.cantidad}</td>
                                <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${linea.precioUnitario * linea.cantidad}"/></td>
                            </tr>
                        </c:forEach>

                        <tr><th colspan="3">Base imponible</th><td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${sessionScope.factura.pedido.baseImponible}"/></td></tr>
                        <tr><th colspan="3">IVA (${general.iva} %)</th><td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${sessionScope.factura.pedido.iva}"/></td></tr>
                        <tr><th colspan="3">Gastos de envío</th><td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${general.gastosEnvio}"/></td></tr>
                        <tr>
                            <th colspan="3">TOTAL FACTURA: </th>
                            <td><fmt:formatNumber maxFractionDigits="2" type="currency" value="${sessionScope.factura.pedido.baseImponible + sessionScope.factura.pedido.iva +
                                  general.gastosEnvio}"/></td>
                        </tr>
                    </table>

                </div>    


            </div>



            <div class="modal-footer">
                <div class="input-group col-md-12 text-center">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Imprimir</button>
                </div>
            </div>     
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

    <script>
        $("#panel-factura").modal('toggle');
    </script>
