<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="partidasActivas">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Partidas activas</h2>
        <c:choose>
            <c:when test = "${empty partidas}">
                <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center;">
                    


                No se encontraron partidas activas




                </div>
            </c:when>
            <c:otherwise>
                <table id="partidasDisponibles" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Ronda</th>
                        <th>Turno</th>
                        <th>Num. jugadores</th>
                        <th>Anfitri&#243;n</th>
                        <th>Votos a favor del C&#233;sar</th>
                        <th>Votos en contra del C&#233;sar</th>
                        <th>L&#237;mite</th>
                        <th>Facci&#243;n ganadora</th>
                        <th>Tiempo</th>
                        <th>Jugadores</th>
                        

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${partidas}" var="partida">
                        <c:if test="${partida.ronda == 0}">
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
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    <a class="btn btn-default" href="/partidas?page=0">VOLVER AL MEN&#218; DE PARTIDAS</a>

</petclinic:layout>
