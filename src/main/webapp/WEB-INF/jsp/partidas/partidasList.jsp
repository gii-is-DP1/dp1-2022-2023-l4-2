<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Partidas</h2>

    <table id="partidasTable" class="table table-striped">
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
            <th>Jugadores</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
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
    <table>
        <tr>   
            <td>Paginas:</td>
            
            <c:forEach begin="0" step="1" end="${num}" var="variable">
                <td>
                    <spring:url value="/partidas?page=${variable}" htmlEscape="true" var="como"/>
                    <a class="btn btn-default"  href="${como}" value = "${variable}"><c:out value = "${variable}"/></a>
                </td>
            </c:forEach>
        </tr>
    </table>
</petclinic:layout>
