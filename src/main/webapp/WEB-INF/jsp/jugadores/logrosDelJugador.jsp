<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="logros">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Logros de <c:out value ="${username}"/></h2>
    </div>


    <table id="logrosTable" class="table table-striped" style="border: 1px solid;">
        <thead >
        <tr>
            <th>Nombre</th>
            <th>Descripci&#243;n</th>
            <th>Tipo</th>
            <th>L&#237;mite</th>
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
    <div style="text-align: center; justify-content: space-between;">

        <div style="position: relative; font-family: 'Dalek Pinpoint';">
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;" href="/jugadores/perfil/${username}">Volver al Perfil</a>
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;"  href="/jugadores/partidas/${username}">Mi Historial de Partidas</a>
        </div>
    </div>
</petclinic:layout>
