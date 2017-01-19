<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--<div class="container visible-lg visible-md visible-sm center-block">-->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <c:set var="centinela" value="0"/> 
        <c:forEach items="${productos}" var="pro">
            <c:if test="${pro.idProducto == requestScope.idProducto}">
                <c:forEach items="${pro.imagenes}" var="imag">
                    <c:choose>
                        <c:when test="${centinela == 0}">
                            <div class="item active">
                            </c:when>
                            <c:otherwise>
                                <div class="item">
                                </c:otherwise>
                            </c:choose>

                            <img class="img-responsive img-thumbnail" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" style="height: 400px; width:1200px">

                            <div class="carousel-caption">
                                <h3>${pro.denominacion}</h3>
                            </div>
                        </div>
                        <c:set var="centinela" value="${centinela + 1}"/>
                    </c:forEach>
                </c:if>

            </c:forEach>
        </div>
        <!-- Solo pintamos las diferentes imagenes si hay más de 1, sino no -->
        <c:if test="${centinela > 1}">
            <!-- End Carousel Inner -->
            <c:set var="centinela" value="0"/> 
            <ul id="opcionSlider" class="nav nav-pills nav-justified menu-slider visible-lg visible-md">
                <c:forEach items="${productos}" var="pro">
                    <c:if test="${pro.idProducto == requestScope.idProducto}">
                        <c:forEach items="${pro.imagenes}" var="imag">
                            <c:choose>
                                <c:when test="${centinela == 0}">
                                    <li data-target="#myCarousel" data-slide-to="${centinela}" class="active">
                                    </c:when>
                                    <c:otherwise>
                                    <li data-target="#myCarousel" data-slide-to="${centinela}" >
                                    </c:otherwise>
                                </c:choose>
                                <img class="img-responsive img-thumbnail" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" alt="${imag.imagen}"/>
                            </li>
                            <c:set var="centinela" value="${centinela+1}"/> 
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </ul>
        </c:if>
    </div>
    <!-- End Carousel -->
    <!--</div>-->
