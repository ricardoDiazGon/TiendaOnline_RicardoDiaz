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
                <li class="active">Contacto</li>
            </ol>
        </div>    

        <!-- Secciones -->
        <div id="secciones" class="container center-block row">

            <!-- OFERTAS DEL MES -->
            <section id="contacto" class="col-md-12 row">

                <div class="col-md-6">
                    <div style="margin-bottom: 50px;">
                        <h2>Localización</h2>
                        <div class="center-block text-center">
                            <iframe  src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d3104.023402899694!2d-6.336369144347554!3d38.92344258078297!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2ses!4v1486250487231" frameborder="0" height="400" width="400 "style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                    <div>
                        <h2>Redes sociales</h2>
                        <div class="row">
                            <a href="https://www.facebook.com/" title="Facebook"><img class="img-responsive col-md-3 col-md-offset-3" src="${pageContext.servletContext.contextPath}/imagenes/facebook-icon.png"></a>
                            <a href="https://twitter.com/?lang=es" title="Twitter"><img class="img-responsive col-md-3 " src="${pageContext.servletContext.contextPath}/imagenes/twitter-icon.png"></a>
                        </div>
                    </div>                     

                </div>


                <div class="col-md-6">
                    <div style="margin-bottom: 50px;">
                        <h2>Datos de contacto</h2>
                        <p><b>Nombre de la empresa:</b> INFO Albarregas</p>
                        <p><b>Dirección:</b> Camino viejo de Mirandilla s/n - 06800 (Badajoz) - Población: Mérida</p>
                        <p><b>Persona de contacto: </b>Ricardo Díaz González</p>
                        <p><b>Teléfono de contacto: </b>902202122</p>
                    </div>
                    <div>

                        <form class="form-horizontal" role="form" method="post" action="#">
                            <caption><h2>Formulario de contacto</h2></caption>
                            <div style="margin-bottom: 10px">
                                <label for="nombre" class="control-label">Nombre y Apellidos</label>
                                <input id="nombre" type="text" class="form-control" name="nombre" value="${sessionScope.usuario.cliente.nombre} ${sessionScope.usuario.cliente.apellidos}" placeholder="Nombre">                                        
                            </div>

                            <div style="margin-bottom: 10px">
                                <label for="dni" class="control-label">DNI</label>
                                <input id="dnip" type="text" class="form-control" name="dni" value="${sessionScope.usuario.cliente.NIF}" placeholder="DNI/NIF">
                            </div>

                            <div style="margin-bottom: 10px">
                                <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                                <input id="fechaNacimiento" type="date" class="form-control" name="fechaNacimiento" value="<fmt:formatDate type='date' value='${sessionScope.usuario.cliente.fechaNacimiento}'/>">
                            </div>

                            <div style="margin-bottom: 10px">
                                <label for="email" class="control-label">Email</label>
                                <input id="email" type="mail" class="form-control" name="claveRep" value="${sessionScope.usuario.email}" placeholder="Email">
                            </div>

                            <div style="margin-bottom: 10px">
                                <label for="informacion" class="control-label">Curriculum Vitae (PDF/DOC)</label>
                                <textarea id="informacion" name="informacion" cols="70" rows="5" placeholder="Escriba su información aquí"></textarea>
                            </div>

                            <div class="modal-footer">
                                <div class="input-group col-md-12 text-center">
                                    <input type="submit" class="btn btn-success" name="enviarDatos" value="Enviar"/>
                                </div>
                            </div>
                        </form>
                    </div> 

                </div>


            </section>

        </div>



        <jsp:include page="/jsp/componentes/cuadroLogin.jsp"/>
        <jsp:include page="/jsp/componentes/cuadroRegistro.jsp"/>   
        <jsp:include page="/jsp/componentes/pie.jsp"/>
    </body>
</html>
