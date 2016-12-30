<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel Administrador | INFO Albarregas</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>     
    </head>
    <body class="container-fluid" onload="carousel()">

        <jsp:include page="/jsp/componentes/cabecera.jsp"/>
        <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
        <!-- Miga de pan -->
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="${pageContext.servletContext.contextPath}">Inicio</a></li>
                <li class="active">Panel de Administrador</li>
            </ol>
        </div>

        <!-- Menú de navegación -->
        <nav>
            <ul class="nav nav-pills nav-stacked">
                <li class="warning"><a href="#">Datos de Administrador</a></li>
                <li><a href="#">Datos de Cliente</a></li>
                <li><a href="#">Ver pedidos</a></li>
                <li><a href="#">Ver productos</a></li>
                <li><a href="#">Cerrar Sesión</a></li>
            </ul>
        </nav>
        <jsp:include page="/jsp/componentes/pie.jsp"/>
