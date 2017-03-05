<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div id="busqueda" class="col-sm-6 col-xs-12 ">
        <div class="input-group" id="adv-search">
            <input type="search" id="buscar" class="form-control" placeholder="Buscar"  onkeyup="buscarProd('${pageContext.servletContext.contextPath}')" />

            <div class="input-group-btn">
                <div class="btn-group" role="group">
                    <div class="dropdown dropdown-lg">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false" title="Búsqueda avanzada"><span class="caret"></span></button>
                        <div class="dropdown-menu dropdown-menu-right" role="menu">
                            <form role="form" class="form-horizontal row" role="form" method="post" action="busquedaAvanzada">
                                <h3 class="text-center">Búsqueda avanzada</h3>
                                <div style="margin-bottom: 10px" class="input-group col-sm-12">
                                    <label for="descripcion" class="control-label">Descripción</label>
                                    <input id="descripcion"  name="descripcion"  type="text" class="form-control" placeholder="Descripcion">                                        
                                </div>


                                <div style="margin-bottom: 10px" class="input-group col-sm-12">
                                    <label for="marca" class="control-label">Marca</label>
                                    <select id="marca" name="marca"  class="form-control" name="marca">
                                        <option value="todas">Todas</option>
                                        <c:forEach items="${marcas}" var="marca">
                                            <option value="${marca.idMarca}">${marca.denominacion}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div style="margin-bottom: 10px" class="input-group col-sm-4">
                                    <label for="precio" class="control-label">Rango de precio</label>
                                    <select id="precio" name="precio" size="1" class="form-control">
                                        <option value="15-50">15-50 €</option>
                                        <option value="51-100">51-100 €</option>
                                        <option value="101-250">101-250 €</option>
                                        <option value="251-500" selected>251-500 €</option>
                                        <option value="501-1000">501-1000 €</option>
                                        <option value="1001-1500">1001-1500 €</option> 
                                        <option value="1501-2000">1501-2000 €</option>
                                        <option value="2001-2501">2001-2501 €</option>
                                    </select>
                                </div>
                                <div style="margin-bottom: 10px" class="input-group col-sm-4">
                                    <label for="oferta" class="control-label">Oferta</label>
                                    <select id="oferta" name="oferta" size="2" class="form-control">
                                        <option value="n" selected>No</option>
                                        <option value="s">Si</option>
                                    </select>
                                </div>
                                <div>
                                    <div class="input-group col-md-12 text-center">
                                        <input type="submit" class="btn btn-success" name="buscar" value="Buscar"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                </div>


            </div>

        </div>

        <!-- Tabla de resultados en búsqueda dinámica -->
        <div id="panelBuscar" class="panel col-xs-12">
            <table id="listaBuscar" border="0">
            </table>
        </div>
    </div>
