<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="listaPartidasEspectear">

    <button>
        <a class="btn btn-default" href="/partidas/join"> Volver</a></th>
    </button>

    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Partidas a espectear de ${nombreUsuario}</h2>

    <table id="listaPartidasEspectear" class="table table-striped">
        <thead>
        <tr>
            <th>Anfitrion</th>
            <th>Jugadores en la sala</th>
            <th>Unirse</th>
        </tr>
        </thead>

        <tbody>
        
        </tbody>
        <c:forEach items="${partidasAmigos}" var="partida">
            <tr>
                <c:out value="${partida.anfitrion}"/>
            </tr>
            <tr>
                <td>
                    <c:out value="${partida.jugadores}"/>
                </td>
            </tr>
            <tr>
                <a class="btn btn-default" href="#">Espectear partida</a></th>
            </tr>
        </c:forEach>
    </table>
</petclinic:layout>