<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Ricardo</title>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/slider.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>      
    </head>
    <body class="container-fluid" onload="carousel()">
        <!-- Cabecera -->
        <header class="row container" id="cabecera">
            <figure class="col-sm-3" id="logo">
                <img src="imagenes/logo.png" alt="logoRicardo"/>
                <figcaption>Tienda Online Ricardo Díaz</h1>
            </figure>
            <div class="col-sm-offset-1 col-sm-8 row text-right" style="padding-top: 60px;" id="toolbar">
                <form class="col-sm-8 col-xs-10">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Buscar">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <a class="col-sm-offset-2 col-sm-2 col-xs-2" href="#"><span><img class="" src="${pageContext.servletContext.contextPath}/imagenes/carritoV.svg" style="height: 40px; width: 40px;"</span></a>
            </div>
        </header>
        <!-- Navegación -->

        <nav id="navegadorPrincipal" class="container-fluid row navbar navbar-default">
            <div class="col-md-12">
                <!-- Para version movil -->
                <div class="navbar-header">
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-navegacion-1" aria-expanded="false">
                        <span class="sr-only">Menú</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a  class="navbar-brand hidden-lg hidden-md" href="#">Menú</a>
                </div>
                <!-- Resto del navbar -->
                <div class="collapse navbar-collapse" id="menu-navegacion-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Portátiles</a></li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Componentes<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Procesadores</a></li>
                                <li><a href="#">Placas Bases</a></li>
                                <li><a href="#">Tarjetas Gráficas</a></li>
                                <li><a href="#">Memorias RAM</a></li>
                                <li><a href="#">Sockets</a></li>
                                <li><a href="#">Cajas</a></li>
                                <li><a href="#">Fuentes de alimentación</a></li>
                                <li><a href="#">Tarjetas de red</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Periféricos<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Monitores</a></li>
                                <li><a href="#">Teclados</a></li>
                                <li><a href="#">Ratones</a></li>
                            </ul> 
                        </li>
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Almacenamiento<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Discos Duros HDD</a></li>
                                <li><a href="#">Discos Duros SDD</a></li>
                                <li><a href="#">Discos Duros Externos</a></li>
                                <li><a href="#">Unidades Flash</a></li>
                            </ul> 
                        </li>
                        <li><a href="#">Otros periféricos</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${sessionScope.usuario == null}">
                                <li><a href="#cuadro-registro" data-toggle="modal"><span class="glyphicon glyphicon-user"></span> Regístrate</a></li>
                                <li><a href="#cuadro-login" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span> Iniciar Sesión</a></li>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${sessionScope.usuario.tipo == 'u'}">
                                    <li><a id='bienvenido'>Bienvenido, <c:out value="${sessionScope.usuario.userName}"/></a></li>
                                    <li><a href="#" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Usuario</a></li>
                                </c:if>
                                <c:if test="${sessionScope.usuario.tipo == 'a'}">
                                    <li><a id='bienvenido'>Bienvenido, <c:out value="${sessionScope.usuario.userName}"/></a></li>
                                    <li><a href="#" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Administración</a></li>              
                                </c:if>
                                <li><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok" data-toggle="modal"><span class="glyphicon glyphicon-log-out"></span> Cerrar Sesión</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
        </nav>

        <!-- Secciones -->

        <div id="secciones">
            <section id="slider">
                <jsp:include page="/jsp/componentes/slider.jsp"/>
            </section>
            <section id="productos">
                <h2>Productos</h2>
            </section>
        </div>
        <c:import url="/jsp/componentes/cuadroLogin.jsp"/>
        <c:import url="/jsp/componentes/cuadroRegistro.jsp"/>
        <footer class="row modal-footer" id="pie">
            <p>Creado por Ricardo Díaz</p>
        </footer>
    </body>
</html>
