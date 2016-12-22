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
        <header class="row page-header" id="cabecera">
            <figure class="col-md-2" id="logo">
                <img src="imagenes/logo.png" alt="logoRicardo"/>
            </figure>
            <div class="col-md-10">
                <h1>Tienda Online Ricardo Díaz</h1> 
            </div>
        </header>
        <!-- Navegación -->

        <nav id="navegadorPrincipal" class="row navbar navbar-default">
            <div class="container-fluid">

                <!-- Para version movil -->

                <div class="navbar-header">
                    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu-navegacion-1" aria-expanded="false">
                        <span class="sr-only">Menú de navegación</span>
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
                    <form class="navbar-form navbar-left">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Buscar">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#cuadro-registro" data-toggle="modal"><span class="glyphicon glyphicon-user"></span> Regístrate</a></li>
                        <li><a href="#cuadro-login" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span> Iniciar Sesión</a></li>
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
            <div class="col-md-12"><p>Creado por Ricardo Díaz</p></div>
        </footer>
    </body>
</html>
