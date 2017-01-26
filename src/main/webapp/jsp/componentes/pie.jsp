<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer class="row" id="pie">
    <div class="col-xs-3 col-xs-offset-1">
        <ul>
            <li>Políticas de la empresa</li>
        </ul>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <ul>
            <li>Creado por Ricardo Díaz</li>
        </ul>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <ul>
            <li><a href="#panel-cv" data-toogle="modal" onclick="abrirCV()">Trabaja con nosotros</a></li>
        </ul>
    </div>  

    <jsp:include page="/jsp/componentes/trabajaConNosotros.jsp"/>
</footer>


</html>