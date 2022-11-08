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
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Historial de  </h2>
        <c:out value ="${jugador.user.username}"/>
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
        <c:forEach items="${jugador}" var="jugador">
            <tr style = "text-align: center; ";>
                <td>
                    <c:out value="${jugador.partidas.ronda}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.turno}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.numJugadores}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.anfitrion}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.votosFavorCesar}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.votosContraCesar}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.limite}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.faccionGanadora}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidas.tiempo}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
