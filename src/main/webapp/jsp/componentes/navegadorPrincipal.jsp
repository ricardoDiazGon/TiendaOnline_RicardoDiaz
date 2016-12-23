<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navegador Principal -->
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
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/cliente/panel.jsp" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Usuario</a></li>
                        </c:if>
                        <c:if test="${sessionScope.usuario.tipo == 'a'}">
                            <li><a id='bienvenido'>Bienvenido, <c:out value="${sessionScope.usuario.userName}"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panel.jsp" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Administración</a></li>              
                        </c:if>
                        <li><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok" data-toggle="modal"><span class="glyphicon glyphicon-log-out"></span> Cerrar Sesión</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
</nav>