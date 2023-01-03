<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="listaPartidasEspectar">

    <button>
        <a class="btn btn-default" href="/home"> Volver</a></th>
    </button>

    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Partidas a espectar de ${nombreUsuario}</h2>

    <table id="listaPartidasEspectear" class="table table-striped">
        <thead>
        <tr>
            <th>Anfitri&#243;n</th>
            <th>Jugadores en la sala</th>
            <th>Unirse</th>
        </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${partidasAmigos}" var="partida">
            <tr>
                <td>
                <c:out value="${partida.anfitrion}"/>
                </td>
                <td>
                    <c:forEach items="${partida.jugadores}" var = "jugador"/>
                    <c:out value ="${jugador.user.username} "/>
                </td>
                <td>
                <a class="btn btn-default" href="/partidas/espectar/${partida.id}">Espectar partida</a></th>
                </td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
</petclinic:layout>