<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

    

<petclinic:layout pageName="historial">
    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 100px;text-align: center;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Historial de <c:out value ="${username}"/></h2>
    </div>


    <table id="historialTable" class="table table-striped">
        <thead>
        <tr>
            <th>Ronda</th>
            <th>Turno</th>
            <th>Num. jugadores</th>
            <th>Anfitrion</th>
            <th>Votos a favor del Cesar</th>
            <th>Votos en contra del Cesar</th>
            <th>Limite</th>
            <th>Faccion ganadora</th>
            <th>Tiempo</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${historial}" var="partida">
            <tr style = "text-align: center; ";>
                <td>
                    <c:out value="${partida.ronda}"/>
                </td>
                <td>
                    <c:out value="${partida.turno}"/>
                </td>
                <td>
                    <c:out value="${partida.numJugadores}"/>
                </td>
                <td>
                    <c:out value="${partida.anfitrion}"/>
                </td>
                <td>
                    <c:out value="${partida.votosFavorCesar}"/>
                </td>
                <td>
                    <c:out value="${partida.votosContraCesar}"/>
                </td>
                <td>
                    <c:out value="${partida.limite}"/>
                </td>
                <td>
                    <c:out value="${partida.faccionGanadora}"/>
                </td>
                <td>
                    <c:out value="${partida.tiempo}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table  style= "width: 100%; text-align:center;position: relative;">
        <tr>
            <th style="text-align: left;"> <a class="btn btn-default" href="/jugadores/perfil/${username}">Volver al Perfil</a></th>
            <th  style="text-align: right;"><a class="btn btn-default" href="/jugadores/logros/${username}">Mis Logros</a></th>
        </tr>
    </table> 
</petclinic:layout>
