<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="cuadro-login" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-md row" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h2 class="modal-title">Iniciar Sesión</h2>
                <c:if test="${requestScope.login != null && requestScope.login == 'ok'}">
                    <div class="alert alert-success" role="alert">
                        <strong>El usuario ha sido registrado correctamente</strong>
                    </div>
                </c:if>
                <c:if test="${requestScope.login != null && requestScope.login != 'ok'}">
                    <div class="alert alert-danger" role="alert">
                        <strong><c:out value="${requestScope.login}"/></strong>
                    </div>
                </c:if>                
            </div>
            <div class="modal-body">
                <form id="loginform" class="form-horizontal" role="form"  method="post" action="${pageContext.servletContext.contextPath}/login">
                    <label for="userName" class="control-label">Nombre de usuario</label>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="userName" type="text" class="form-control" name="userName" value="${requestScope.userName}" placeholder="Nombre de usuario">                                        
                    </div>
                    
                    <label for="clave" class="control-label">Contraseña</label>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="clave" type="password" class="form-control" name="clave" value="${requestScope.clave}" placeholder="Contraseña">
                    </div>

                    <div class="modal-footer">
                        <div class="input-group col-md-12 text-center">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            <input type="submit" class="btn btn-success" name="login" value="Iniciar Sesión"/>
                        </div>
                    </div>

                </form>     
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${requestScope.login != null}">
    <script>
        $("#cuadro-login").modal('toggle');
    </script>
</c:if>