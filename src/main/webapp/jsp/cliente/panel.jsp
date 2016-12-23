<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Ricardo</title>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/>      
    </head>
    <body class="container-fluid" onload="carousel()">
        <!-- Miga de pan -->
        <div class="row">
            <div class="btn-group btn-breadcrumb">
                <a href="${pageContext.servletContext.contextPath}" class="btn btn-info"><i class="glyphicon glyphicon-home"></i></a>
                <a href="#" class="btn btn-info">Panel de Usuario</a>
            </div>
        </div>

        <!-- Menú de navegación -->
        <nav>
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="#">Datos de Usuario</a></li>
                <li><a href="#">Datos de Cliente</a></li>
                <li><a href="#">Pedidos</a></li>
                <li><a href="#">Facturas</a></li>
                <li><a href="#">Cerrar Sesión</a></li>
            </ul>
        </nav>

        <jsp:include page="/componentes/pie.jsp"/>