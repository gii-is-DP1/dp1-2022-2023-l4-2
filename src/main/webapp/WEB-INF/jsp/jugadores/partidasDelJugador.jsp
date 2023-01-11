<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

    

<petclinic:layout pageName="historial">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Historial de <c:out value ="${username}"/></h2>
    </div>

    <table id="historialTable" class="table table-striped" style="border: 1px solid;">
        <thead>
        <tr>
            <th style="text-align: center;">Ronda</th>
            <th style="text-align: center;">Turno</th>
            <th style="text-align: center;">Num. jugadores</th>
            <th style="text-align: center;">Anfitri&#243;n</th>
            <th style="text-align: center;">Votos a favor del C&#233;sar</th>
            <th style="text-align: center;">Votos en contra del C&#233;sar</th>
            <th style="text-align: center;">L&#237;mite</th>
            <th style="text-align: center;">Facci&#243;n ganadora</th>
            <th style="text-align: center;">Tiempo</th>
            <th style="text-align: center;">Jugadores de la partida</th>

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
                <td>
                    <c:forEach items="${partida.jugadores}" var="jugador">
                        <c:out value="${jugador.user.username}  "/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div style="text-align: center; justify-content: space-between;">
        <div style="position: relative; font-family: 'Dalek Pinpoint';">
            <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;" href="/jugadores/perfil/${username}">Volver al Perfil</a>
            <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;"  href="/jugadores/logros/${username}">Mis Logros</a>
        </div>
    </div>
    
</petclinic:layout>
