<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Jugdores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nickname</th>
            <th>EstaEnPartida</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <c:out value="${jugador.nickname}"/>
                </td>
                <td>
                    <c:out value="${jugador.estaEnPartida}"/>
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
