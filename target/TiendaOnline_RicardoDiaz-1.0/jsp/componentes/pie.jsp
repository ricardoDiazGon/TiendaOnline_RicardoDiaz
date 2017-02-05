<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer class="row" id="pie">
    <div class="col-xs-3">
        <ul>
            <li><a href="${pageContext.servletContext.contextPath}/jsp/comun/contacto.jsp">Contacto y localización</a></li>            
            <li><a href="#panel-cv" data-toogle="modal" onclick="abrirCV()">Trabaja con nosotros</a></li>
            <li>
                <a class="enlace-mano" data-container="body" data-toggle="popover" data-placement="top" data-content="Éste sitio web usa cookies, si permanece aquí acepta su uso.">
                    Uso de cookies
                </a>
            </li>
        </ul>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <ul>
            <li>Creado por Ricardo Díaz © 2017</li>
        </ul>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <ul>
            <li> <b>Estamos en las redes sociales:<br/>                       
                    <a href="https://www.facebook.com/" title="Facebook"><img  src="${pageContext.servletContext.contextPath}/imagenes/facebook-icon.png" style="height: 50px; width:50px;"></a>
                    <a href="https://twitter.com/?lang=es" title="Twitter"><img  src="${pageContext.servletContext.contextPath}/imagenes/twitter-icon.png" style="height: 50px; width:50px;"></a>
            </li>
        </ul>
    </div>  

    <jsp:include page="/jsp/componentes/trabajaConNosotros.jsp"/>
</footer>

<script type="text/javascript">
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>

</html>
