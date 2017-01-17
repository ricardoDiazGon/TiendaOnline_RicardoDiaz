<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--<div class="container visible-lg visible-md visible-sm center-block">-->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <c:set var="centinela" value="0"/> 
        <c:forEach items="${productos}" var="pro">
            <c:if test="${pro.oferta == 's'}">
                <c:choose>
                    <c:when test="${centinela == 0}">
                        <div class="item active cols-xs-10">
                    </c:when>
                    <c:otherwise>
                        <div class="item cols-xs-10">
                   </c:otherwise>
                </c:choose>
                        <c:forEach begin="0" end="0" items="${pro.imagenes}" var="imag">
                            <a href="${pageContext.servletContext.contextPath}/navProductos?opt=amp&param=${pro.idProducto}">
                                <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" style="height: 400px; width:1200px">
                            </a>
                        </c:forEach>
                        <div class="carousel-caption">
                            <h3>${pro.denominacion}</h3>
                            <h3 class="precioProducto"><b>${pro.precioUnitario} €</b></h3>
                        </div>
                    </div>
                    <c:set var="centinela" value="${centinela + 1}"/>
                </c:if>

            </c:forEach>
        </div>
        <!-- End Carousel Inner -->
        <c:set var="centinela" value="0"/> 
        <ul class="nav nav-pills nav-justified menu-slider cols-xs-10">
            <c:forEach items="${productos}" var="pro">
                <c:if test="${pro.oferta == 's'}">
                    <c:choose>
                        <c:when test="${centinela == 0}">
                            <li data-target="#myCarousel" data-slide-to="${centinela}" class="active">
                            </c:when>
                            <c:otherwise>
                            <li data-target="#myCarousel" data-slide-to="${centinela}" >
                            </c:otherwise>
                        </c:choose>
                                <a href="#">${pro.denominacion}</a>
                    </li>
                    <c:set var="centinela" value="${centinela+1}"/> 
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <!-- End Carousel -->
    <!--</div>-->
