<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="partidasDisponibles">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Partidas disponibles</h2>
    </div>
        <c:choose>
            <c:when test = "${empty partidas}">
                <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center;">
                    


                No se encontraron partidas disponibles




                </div>
            </c:when>
            <c:otherwise>
                <table id="partidasDisponibles" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Jugadores</th>
                        <th>Anfitri&#243;n</th>
                        <th>Participantes</th>
                        <th>Unirse</th>
                        

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${partidas}" var="partida">
                        <c:if test="${partida.ronda == 0}">
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
                                    <a class="btn btn-default" href="/partidas/join/${partida.id}">Unirse como jugador</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <div class="form-group" style="text-align: center; font-size: x-large;">
            <div>
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;" href="/partidas/seleccionar">Volver al menu de opciones de partida</a>
            </div>
        </div>

</petclinic:layout>
