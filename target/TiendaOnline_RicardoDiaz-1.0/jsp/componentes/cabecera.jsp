<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="row container" id="cabecera">
    <figure class="col-sm-3" id="logo">
        <a href="${pageContext.servletContext.contextPath}/navProductos"><img src="${pageContext.servletContext.contextPath}/imagenes/logoINFOAlbarregas1.png" alt="logoRicardo"/></a>
    </figure>
    <div class="col-sm-offset-1 col-sm-8 row text-right" style="padding-top: 60px;" id="toolbar">

        <form id="busqueda" class="col-sm-8 col-xs-10 row">
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
        <a class="col-sm-offset-2 col-sm-2 col-xs-2" href="#"><span><img class="" src="${pageContext.servletContext.contextPath}/imagenes/carritoV.svg" style="height: 40px; width: 40px;"/></span></a>
    </div>
</header>
