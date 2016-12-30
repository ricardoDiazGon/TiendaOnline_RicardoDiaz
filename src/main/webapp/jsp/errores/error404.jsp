<%-- 
    Document   : error404
    Created on : 07-dic-2016, 19:13:56
    Author     : Ricardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/>
        <title>Error 404 | INFO Albarregas</title>
    </head>
    <body>
        <div id="errores">
            <h1>No se ha encontrado la página que buscaba</h1>

            <a href="${pageContext.servletContext.contextPath}">
                <img src="${pageContext.servletContext.contextPath}/imagenes/error404.jpg" alt="Error404" />
            </a>

            <h3>Pulse la imagen para continuar</h3>
        </div> 
    </body>
</html>
