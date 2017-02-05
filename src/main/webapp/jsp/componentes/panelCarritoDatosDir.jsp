<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
    $(document).ready(function () {
        $("#datos-direccion").css("visibility", "hidden");
        $("#select-direc").change(function () {
            if ($("#select-direc").val() != 'null') {
                $("#datos-direccion").css("visibility", "visible");
            } else {
                $("#datos-direccion").css("visibility", "hidden");
            }

        });
    });
</script>

<div class="panel-heading"><h4>TU CARRITO | DATOS DEL ENVÍO</h4></div>
<div class="panel-body">

    <div class="row">
        <ul class="breadcrumb2 visible-lg visible-md col-xs-10 col-xs-offset-1">
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosIni('${pageContext.servletContext.contextPath}')">Datos del pedido</a></li>
            <li class="completed"><a href="javascript:void(0);" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Datos personales</a></li>
            <li class="active"><a href="javascript:void(0);">Datos de envío</a></li>
            <li><a href="javascript:void(0);">Pago</a></li>
        </ul>
    </div>    

    <c:choose>
        <c:when test="${fn:length(sessionScope.usuario.cliente.listaDirecciones) == 0 or requestScope.aniadirDir != null}">
            <c:if test="${requestScope.errorDirec != null and requestScope.errorDirec != 'ok'}">
                <div class="col-md-12 alert alert-danger text-center center-block alert-dismissable col-xs-offset-1 col-xs-6">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong><c:out value="${requestScope.errorDirec}" /></strong>
                </div>
            </c:if>

            <form role="form"  method="post" action="${pageContext.servletContext.contextPath}/direcciones">

                <div style="margin-bottom: 25px" class="form-group col-md-4">
                    <label for="NombreDireccion" class="control-label">Nombre de la dirección</label>
                    <input id="NombreDireccion" type="text" class="form-control" name="NombreDireccion" placeholder="Ej: Casa del pueblo" maxlength="20" required value="${requestScope.direc.nombreDireccion}">                                        
                </div>     
                <div style="margin-bottom: 25px" class="form-group col-md-4">
                    <label for="direccion" class="control-label">Dirección</label>
                    <input id="direccion" type="text" class="form-control" name="Direccion" placeholder="Direccion" maxlength="50" required value="${requestScope.direc.direccion}">                                        
                </div>                                      

                <div style="margin-bottom: 25px" class="form-group col-md-4">
                    <label for="CodigoPostal" class="control-label">Código Postal</label>
                    <input id="CodigoPostal" type="text" class="form-control" name="CodigoPostal" placeholder="Código Postal" pattern="^[0-9]{5}" title="5 números" required value="${requestScope.direc.codigoPostal}" onkeyup="getPueblos('${pageContext.servletContext.contextPath}')">                                        
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
                    <input id="Telefono" type="text" class="form-control" name="Telefono" placeholder="Teléfono" maxlength="9" required value="${requestScope.direc.telefono}"/>
                </div>
                <label>* Todos los campos son obligatorios</label>
                <input type="hidden" id="IdProvincia" name="IdProvincia" value="${requestScope.direc.idProvincia}"/>
                <input type="hidden" id="IdPueblo" name="IdPueblo" value="${requestScope.direc.idPueblo}"/>
                <input type="hidden" name="formulario" value="carrito"/>

                <div class="text-center container-fluid">
                    <input class="btn btn-warning btn-md col-xs-offset-5 col-xs-2" type="submit" name="guardarDir" value="Guardar dirección"/>
                </div>
            </form>


        </c:when>
        <c:otherwise>
            <!-- Combo con las direcciones disponibles -->
            <div class="col-md-3" style="margin-bottom: 25px">
                <label>Nombre de la dirección</label>
                <select id="select-direc" size="1" name="direcciones" class="form-control" onchange="elegirDireccion('${pageContext.servletContext.contextPath}', this.value)">
                    <option value="null">Selecciona una dirección</option>

                    <c:forEach items="${sessionScope.usuario.cliente.listaDirecciones}" var="dir">
                        <option value="${dir.idDireccion}">${dir.nombreDireccion}</option>
                    </c:forEach>                
                </select>
                <div><h4><a href="${pageContext.servletContext.contextPath}/jsp/cliente/panelCli.jsp"><span class="glyphicon glyphicon-plus"></span>Añadir una dirección</a></h4></div>
            </div>
            <!-- Se rellena automáticamente cuando ponemos una dirección -->
            <div id="datos-direccion" class="col-md-8 col-md-offset-1">
                <div style="margin-bottom: 25px" class="form-group col-md-12">
                    <label for="direccion2" class="control-label">Dirección</label>
                    <input id="direccion2" type="text" class="form-control" name="direccion2" placeholder="Direccion" readonly />                                        
                    <input type="hidden" name="idDireccion" id="idDireccion"/>
                </div>

                <div style="margin-bottom: 25px" class="form-group col-md-6">
                    <label for="CodigoPostal2" class="control-label">Código Postal</label>
                    <input id="codigoPostal2" type="text" class="form-control" name="CodigoPostal2" placeholder="Código Postal" readonly />                                        
                </div>

                <div style="margin-bottom: 25px" class="form-group col-md-6">
                    <label for="Provincia2" class="control-label">Provincia</label>
                    <input id="provincia2" type="text" class="form-control" name="Provincia2" placeholder="Provincia" readonly />                                        
                </div>

                <div style="margin-bottom: 25px" class="form-group col-md-6">
                    <label for="Poblacion2" class="control-label">Población</label>
                    <input id="poblacion2" type="text" class="form-control" name="Poblacion2" placeholder="Poblacion" readonly />                                        
                </div>

                <div style="margin-bottom: 25px" class="form-group col-md-6">
                    <label for="Telefono2" class="control-label">Teléfono</label>
                    <input id="telefono2" type="text" class="form-control" name="Telefono2" placeholder="Teléfono" readonly />
                </div>

            </div>         
        </c:otherwise>
    </c:choose>
</div>


<div class="panel-footer text-center container-fluid">
    <a class="btn btn-primary btn-md col-xs-offset-2 col-xs-3" onclick="irCarritoDatosPer('${pageContext.servletContext.contextPath}')">Atrás</a>
    <button id="boton-confDir" class="btn btn-success btn-md col-xs-offset-2 col-xs-3" disabled onclick="irCarritoDatosPago('${pageContext.servletContext.contextPath}')">Realizar pago</button>
</div>