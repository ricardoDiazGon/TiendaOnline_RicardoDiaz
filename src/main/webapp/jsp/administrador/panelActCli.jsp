<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.usuario != null and sessionScope.usuario.tipo == 'a'}">
        <!DOCTYPE html>
        <html lang="es">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Panel Administrador | INFO Albarregas</title>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/estilo.css"/> 
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
                <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/slider.js"></script>     
            </head>
            <body class="container-fluid" onload="carousel()">

                <jsp:include page="/jsp/componentes/cabecera.jsp"/>
                <jsp:include page="/jsp/componentes/navegadorPrincipal.jsp"/>
                <!-- Miga de pan -->
                <div class="row">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.servletContext.contextPath}">Inicio</a></li>
                        <li class="active">Panel de Administrador</li>
                    </ol>
                </div>
                <div id="secciones">
                    <!-- Menú de navegación -->
                    <nav id="panel-control" class="col-md-3">
                        <ul class="nav nav-pills nav-stacked">
                            <li class="warning active"><a href="#">Datos de Administrador</a></li>
                            <li><a href="${pageContext.servletContext.contextPath}/actClientes">Actualizar clientes</a></li>
                            <li><a href="#">Ver pedidos</a></li>
                            <li><a href="#">Ver productos</a></li>
                            <li><a href="#">Cerrar Sesión</a></li>
                        </ul>
                    </nav>
<!-- Listado de usuarios -->
                    <div class="col-md-offset-1 col-md-8">
                        <h4>Listado de Usuarios</h4>
                        <div class="table-responsive">
                            <table id="mytable" class="table table-bordred table-striped">
                                <thead>
                                <th>User Name</th>
                                <th>Fecha de actividad</th>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>NIF</th>
                                <th>Email</th>
                                <th>Fecha Nacimiento</th>
                                <th>Editar</th>
                                <th>Bloquear</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input type="checkbox" class="checkthis" /></td>
                                        <td>Mohsin</td>
                                        <td>Irshad</td>
                                        <td>CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan</td>
                                        <td>isometric.mohsin@gmail.com</td>
                                        <td>+923335586757</td>
                                        <td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                                        <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                                    </tr>
                                </tbody>

                            </table>

                            <div class="clearfix"></div>
                            <ul class="pagination pull-right">
                                <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                            </ul>

                        </div>

                    </div>

                    <!-- Empieza el modal -->
                    <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                    <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input class="form-control " type="text" placeholder="Mohsin">
                                    </div>
                                    <div class="form-group">

                                        <input class="form-control " type="text" placeholder="Irshad">
                                    </div>
                                    <div class="form-group">
                                        <textarea rows="2" class="form-control" placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


                                    </div>
                                </div>
                                <div class="modal-footer ">
                                    <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Acaba el modal -->

                    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                    <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                                </div>
                                <div class="modal-footer ">
                                    <button type="button" class="btn btn-success" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                                </div>
                            </div>
                            <!-- /.modal-content --> 
                        </div>
                        <!-- /.modal-dialog --> 
                    </div>
<!-- Fin del listado -->
                </div>

                <jsp:include page="/jsp/componentes/pie.jsp"/>

            </c:when>

            <c:otherwise>
                <c:redirect url="/index.jsp"/>
            </c:otherwise>
        </c:choose>
