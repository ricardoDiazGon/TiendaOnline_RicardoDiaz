<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="panel-cv" class="row modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-md row" role="document">
        <div class="modal-content">
            <div class="modal-header text-center bg-primary">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h2 class="modal-title">Trabaja con nosotros</h2>
            </div>
            <div class="modal-body">
                <form id="loginform" class="form-horizontal" role="form" method="post" action="#">
                    <label for="nombre" class="control-label">Nombre</label>
                    <div style="margin-bottom: 10px">
                        <input id="nombre" type="text" class="form-control" name="nombre" value="${sessionScope.usuario.cliente.nombre}" placeholder="Nombre">                                        
                    </div>
                    
                    <label for="apellidos" class="control-label">Apellidos</label>
                    <div style="margin-bottom: 10px">
                        <input id="apellidos" type="text" class="form-control" name="apellidos" value="${sessionScope.usuario.cliente.apellidos}" placeholder="Apellidos">
                    </div>
                    
                    <label for="dni" class="control-label">DNI</label>
                    <div style="margin-bottom: 10px">
                        <input id="dnip" type="text" class="form-control" name="dni" value="${sessionScope.usuario.cliente.nif}" placeholder="DNI/NIF">
                    </div>
                    
                    <label for="fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                    <div style="margin-bottom: 10px">
                        <input id="fechaNacimiento" type="date" class="form-control" name="fechaNacimiento" value="${sessionScope.usuario.cliente.fechaNacimiento}">
                    </div>
                    
                    <label for="email" class="control-label">Email</label>
                    <div style="margin-bottom: 10px">
                        <input id="email" type="mail" class="form-control" name="claveRep" value="${sessionScope.usuario.email}" placeholder="Email">
                    </div>
                    
                    <label for="cv" class="control-label">Curriculum Vitae (PDF/DOC)</label>
                    <div style="margin-bottom: 10px">
                        <input id="cv" type="file" class="form-control" name="cv" placeholder="Inserta el CV">
                    </div>
                    
                    <div class="modal-footer">
                        <div class="input-group col-md-12 text-center">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            <input type="submit" class="btn btn-success" name="enviarCV" value="Enviar CV"/>
                        </div>
                    </div>
                </form>     
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    <script>
        function abrirCV(){
            $("#panel-cv").modal('toggle');
        }
    </script>