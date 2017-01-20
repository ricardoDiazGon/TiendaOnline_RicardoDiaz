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
            <a  class="navbar-brand visible-xs" href="#">Menú</a>
        </div>
        <!-- Resto del navbar -->
        <div class="collapse navbar-collapse" id="menu-navegacion-1">
            <ul class="nav navbar-nav">
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">PRODUCTOS<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${categorias}" var="cat">
                            <li><a href="${pageContext.servletContext.contextPath}/navProductos?opt=cat&param=${cat.idCategoria}&pag=1">${cat.nombre}</a></li>
                        </c:forEach>                      
                    </ul>
                </li>
                <li><a href="${pageContext.servletContext.contextPath}/navProductos?opt=ofe&pag=1">OFERTAS</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/navProductos?opt=tod&pag=1">TODOS</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.usuario == null}">
                        <li><a href="#cuadro-registro" data-toggle="modal"><span class="glyphicon glyphicon-user"></span> Regístrate</a></li>
                        <li><a href="#cuadro-login" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span> Iniciar Sesión</a></li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.usuario.tipo == 'u'}">
                            <li><a id='bienvenido'>Bienvenido, <c:out value="${sessionScope.usuario.email}"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/cliente/panel.jsp" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Usuario</a></li>
                        </c:if>
                        <c:if test="${sessionScope.usuario.tipo == 'a'}">
                            <li><a id='bienvenido'>Bienvenido, <c:out value="${sessionScope.usuario.email}"/></a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/jsp/administrador/panel.jsp" data-toggle="modal"><span class="glyphicon glyphicon-home"></span> Panel Administración</a></li>              
                        </c:if>
                        <li><a href="${pageContext.servletContext.contextPath}/login?cerrar=ok" data-toggle="modal"><span class="glyphicon glyphicon-log-out"></span> Cerrar Sesión</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
</nav>