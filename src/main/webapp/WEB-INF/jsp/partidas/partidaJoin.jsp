<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="partidaJoin">

    <div style="text-align: center; ">
        <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;" href="/partidas/join">Volver</a>
    </div>

    <div style="text-align: center; margin: 25px;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 30px; ";>Partida de ${partida.anfitrion}</h2>
    </div>

    <div style="text-align: center; margin: 25px;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 30px; ";>${jugadoresAct} / ${jugadoresMax}</h2>
    </div>

    <table id="partidaJoin" class="table table-striped" style="border: 1px solid;">
        <thead>
        <tr>
            <th>Jugadores en la sala</th>
            <th>&#191;Est&aacute; listo?</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
                <tr style = "text-align: left; ";>
                    <td>
                        <c:out value="${jugador.user.username}"/>
                    </td>
                    <td>
                        <c:if test="true">
                            LISTO
                        </c:if>
                    </td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:lo2>
