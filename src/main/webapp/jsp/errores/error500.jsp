<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>INFO Albarregas</title>
        <!-- Incluimos las etiquetas meta -->
        <jsp:include page="/jsp/componentes/meta.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/efectosProductos.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/busquedaProductos.js"></script>
    </head>
    <body class="container-fluid">
        <c:set var="opt" value="ind" />
        <c:set value="${pageContext.servletContext.contextPath}" var="contexto"/>
        <div id="contenedor-arriba">
            <!-- Cabecera -->
            <jsp:include page="/jsp/componentes/cabecera.jsp"/>

            <!-- Navegación -->
            <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        </div>
        <!-- Miga de pan -->
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="${pageContext.servletContext.contextPath}/navProductos">Inicio</a></li>
                <li class="active">Error servidor</li>
            </ol>
        </div>    

         <!-- Secciones -->
        <div id="secciones" class="container center-block row">


            <section id="contacto" class="col-md-12 row">

                <div class="col-md-8 col-md-offset-2 text-center">

                    <h3>Puede seguir comprando haciendo click <a href="${pageContext.servletContext.contextPath}/navProductos">aquí</a> </h3>
                    <div class="datosContacto" style="padding: 50px;">
                        <h2>Ha ocurrido un error inesperado en el servidor. </h2>
                        <img alt="aviso-error" src="${pageContext.servletContext.contextPath}/imagenes/advertencia.png" height="200" width="200"/>
                        <h2>Estamos trabajando para solucionarlo.</h2>
                    </div>
                    <h3>Puede seguir comprando haciendo click <a href="${pageContext.servletContext.contextPath}/navProductos">aquí</a> </h3>
                </div>  
            </section>
        </div>



        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
