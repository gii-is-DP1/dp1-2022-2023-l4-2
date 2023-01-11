<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 50px; margin-bottom: 2%;";>Partidas</h2>
    </div>

    <table id="partidasTable" class="table table-striped" style="border: 1px solid">
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
    <div style="display: flex; flex-direction: row; justify-content:center; font-size: 19px;">
        <table >
            <tr>  
                <div>
                    <td><h1 style = " font-size: 20px; margin-bottom: 2%;";>P&#225;ginas:</h1></td>
                
                    <c:forEach begin="0" step="1" end="${num}" var="variable">
                        <td>
                            <div style="margin-left: 30%;">
                                <spring:url value="/partidas?page=${variable}" htmlEscape="true" var="como"/>
                                <a class="btn btn-default" style="margin-left: 5%;" href="${como}" value = "${variable}"><c:out value = "${variable+1}"/></a>
                            </div>
                        </td>
                    </c:forEach>
                </div> 
            </tr>
        </table>
    </div>
    
</petclinic:layout>
