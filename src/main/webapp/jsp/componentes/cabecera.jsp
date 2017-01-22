<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="row container" id="cabecera">
    <figure class="col-sm-3" id="logo">
        <a href="${pageContext.servletContext.contextPath}/navProductos"><img src="${pageContext.servletContext.contextPath}/imagenes/logoINFOAlbarregas1.png" alt="logoINFOAlbarregas1.png"/></a>
    </figure>
    <div class="col-sm-offset-1 col-sm-8 row text-right" style="padding-top: 60px;" id="toolbar">

        <form id="busqueda" class="col-sm-8 col-xs-12 row">
            <div class="input-group col-xs-12">
                <input type="text" id="buscar" class="form-control" placeholder="Buscar" onkeyup="buscarProd('${pageContext.servletContext.contextPath}')">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
            <div id="panelBuscar" class="panel col-xs-10">
                <table id="listaBuscar" border="0">
                </table>
            </div>
        </form>
        <c:if test="${sessionScope.carrito != null}">
            <div class="col-sm-offset-1 col-sm-3 col-xs-12 row">
                <a><span id="carrito" class=" col-xs-offset-5 col-xs-5 glyphicon glyphicon-shopping-cart"></span>
                    <span id="numCarrito" class="col-xs-2">${sessionScope.carrito.lineasPedidos.size()}</span></a>
            </div>
        </c:if>
    </div>
</header>
