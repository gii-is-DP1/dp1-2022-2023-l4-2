<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="logros">
    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 100px;text-align: center;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Logros de <c:out value ="${username}"/></h2>
    </div>


    <table id="logrosTable" class="table table-striped">
        <thead >
        <tr>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Tipo</th>
            <th>Limite</th>
            <th>Dificultad</th>
            <th>Conseguido</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.nombre}"/>
                </td>
                <td>
                    <c:out value="${logro.descripcion}"/>
                </td>
                <td>
                    <c:out value="${logro.tipo}"/>
                </td>
                <td>
                    <c:out value="${logro.limite}"/>
                </td>
                <td>
                    <c:out value="${logro.dificultad}"/>
                </td>
                <td>
                    <c:if test="${logro.tipo.getName() == 'Por ganar'}">
                        <c:if test="${logro.limite > partidasGanadas}">
                            <span style = "color: red;"class="glyphicon glyphicon-remove" aria-hidden="true"></span>                            
                        </c:if>
                        <c:if test="${partidasGanadas >= logro.limite}">
                            <span style = "color: green;"class="glyphicon glyphicon-ok" aria-hidden="true"></span>                            
                        </c:if>
                    </c:if>
                    <c:if test="${logro.tipo.getName() == 'Por jugar'}">
                        <c:if test="${logro.limite > partidasJugadas}">
                            <span style = "color: red;"class="glyphicon glyphicon-remove" aria-hidden="true"></span>                            
                        </c:if>
                        <c:if test="${partidasJugadas >= logro.limite}">
                            <span style = "color: green;"class="glyphicon glyphicon-ok" aria-hidden="true"></span>                            
                        </c:if>
                    </c:if>
                    <c:if test="${logro.tipo.getName() == 'Victorias leal'}">
                        <c:if test="${logro.limite > victoriasLeal}">
                            <span style = "color: red;"class="glyphicon glyphicon-remove" aria-hidden="true"></span>                            
                        </c:if>
                        <c:if test="${victoriasLeal >= logro.limite}">
                            <span style = "color: green;"class="glyphicon glyphicon-ok" aria-hidden="true"></span>                            
                        </c:if>
                    </c:if>
                    <c:if test="${logro.tipo.getName() == 'Victorias traidor'}">
                        <c:if test="${logro.limite > victoriasTraidor}">
                            <span style = "color: red;" class="glyphicon glyphicon-remove" aria-hidden="true"></span>                            
                        </c:if>
                        <c:if test="${victoriasTraidor >= logro.limite}">
                            <span style = "color: green;"class="glyphicon glyphicon-ok" aria-hidden="true"></span>                            
                        </c:if>
                    </c:if>
                    <c:if test="${logro.tipo.getName() == 'Victorias mercader'}">
                        <c:if test="${logro.limite > victoriasMercader}">
                            <span style = "color: red;"class="glyphicon glyphicon-remove" aria-hidden="true"></span>                            
                        </c:if>
                        <c:if test="${victoriasMercader >= logro.limite}">
                            <span style = "color: green;"class="glyphicon glyphicon-ok" aria-hidden="true"></span>                            
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table  style= "width: 100%; text-align:center;position: relative;">
        <tr>
            <th style="text-align: left;"> <a class="btn btn-default" href="/jugadores/perfil/${username}">Volver al Perfil</a></th>
            <th  style="text-align: right;"> <a class="btn btn-default" href="/jugadores/partidas/${username}">Mi Historial de Partidas</a></th>
        </tr>
    </table> 
</petclinic:layout>
