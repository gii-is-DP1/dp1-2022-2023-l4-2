<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="listaPartidasEspectar">

    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Partidas a espectar en las que estan jugando los amigos de ${nombreUsuario}</h2>
    </div>
    <table id="listaPartidasEspectear" class="table table-striped">
        <thead>
        <tr>
            <th>Jugadores</th>
            <th>Anfitri&#243;n</th>
            <th>Participantes</th>
            <th>Ronda</th>
            <th>Turno</th>
            <th>Observar</th>
            

        </tr>
        </thead>

        <tbody>
        <c:forEach items="${partidasAmigos}" var="partida">
            <tr style = "text-align: left; ";>
                <td>
                    <c:out value="${partida.jugadores.size()}"/>/<c:out value="${partida.numJugadores}"/>
                </td>
                <td>
                    <c:out value="${partida.anfitrion}"/>
                </td>
                <td>
                    <c:forEach items="${partida.jugadores}" var="jugador">
                        <c:out value="${jugador.user.username},"/>
                    </c:forEach>
                </td>
                <td>
                    <c:out value="${partida.ronda}"/>
                </td>
                <td>
                    <c:out value="${partida.turno}"/>
                </td>
                <td>
                    <a class="btn btn-default" href="/partidas/espectar/${partida.id}">Espectar partida</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>