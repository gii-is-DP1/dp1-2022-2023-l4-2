<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Jugadores:</h2>

    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre de usuario</th>
            <th>Esta en partida?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <c:out value="${jugador.nickname}"/>
                </td>
                <td>
                    <c:out value="${jugador.estaEnPartida ? 'Si, esta jugando' : 'No esta en ninguna partida'}"/>
                </td>
                <td> 
                    <a href="/jugadores/delete/${jugador.id}"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>