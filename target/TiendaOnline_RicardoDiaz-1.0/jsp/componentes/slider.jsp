<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--<div class="container visible-lg visible-md visible-sm center-block">-->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <c:set var="centinela" value="0"/> 
        <c:forEach items="${proSlider}" var="pro">
            <c:choose>
                <c:when test="${centinela == 0}">
                    <div class="item active cols-xs-10">
                    </c:when>
                    <c:otherwise>
                        <div class="item cols-xs-10">
                        </c:otherwise>
                    </c:choose>
                    <a href="${pageContext.servletContext.contextPath}/navProductos?opt=amp&param=${pro.idProducto}">
                        <c:forEach begin="0" end="0" items="${pro.imagenes}" var="imag">
                            <img class="img-responsive" src="${pageContext.servletContext.contextPath}/imagenes/imagenesProductos/${imag.imagen}" style="height: 400px; width:1200px">
                        </c:forEach>
                    </a>
                        <div class="carousel-caption">
                            <h3>${pro.denominacion}</h3>
                            <h3 class="precioProducto"><b><fmt:formatNumber value="${pro.precioUnitario}" type="currency"/></b></h3>
                            <c:set value="btn-success" var="tipoBoton" />
                            <c:if test="${sessionScope.usuario.cliente == null}">
                                <c:set var="dis" value="disabled"/>
                                <c:set value="btn-default" var="tipoBoton" />
                            </c:if>


                            <p class="text-center"><button ${dis} class="btn ${tipoBoton} btn-lg " role="button" onclick="aniadirProducto('${pageContext.servletContext.contextPath}', '${pro.idProducto}', '${pro.stock}', '1')">Añadir al carrito <span class="glyphicon glyphicon-shopping-cart"></span></button></p> 

                        </div>
                </div>
                <c:set var="centinela" value="${centinela + 1}"/>
            </c:forEach>
            
        </div>
        <!-- End Carousel Inner -->
        <c:set var="centinela" value="0"/> 
        <ul class="nav nav-pills nav-justified menu-slider cols-xs-10">
            <c:forEach items="${proSlider}" var="pro">
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
            </c:forEach>
        </ul>
    </div>
    <!-- End Carousel -->
    <!--</div>-->
