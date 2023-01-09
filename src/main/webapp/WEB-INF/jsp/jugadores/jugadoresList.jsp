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
            <th>&#191;Est&#225; en partida?</th>
            <th>Dia de Creacion</th>
            <th>Creador</th>
            <th>Ultima modificacion</th>
            <th>Persona que lo modifico</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <a href="/jugadores/perfil/${jugador.user.username}">
                        <c:out value="${jugador.user.username}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${jugador.estaEnPartida ? 'Si, esta jugando' : 'No esta en ninguna partida'}"/>
                </td>
                <td>
                    <c:out value="${jugador.createdDate}"/>
                </td>
                <td>
                    <c:out value="${jugador.creator}"/>
                </td>
                <td>
                    <c:out value="${jugador.lastModifiedDate}"/>
                </td>
                <td>
                    <c:out value="${jugador.modifier}"/>
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