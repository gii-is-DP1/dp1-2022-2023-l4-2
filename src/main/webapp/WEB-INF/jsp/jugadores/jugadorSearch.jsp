<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadorSearch">
    <jsp:body>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Jugadores:</h2> 
        <form:form modelAttribute="jugador" class="form-horizontal" id="jugadorForm">
            <div class="form-group has-feedback">
                <table id="jugadorSearch" class="table table-striped">
                    <c:forEach items="${jugadores}" var="jugador">
                        <c:if test="${jugador.user.username != nombreUsuario && !amigos.contains(jugador)}">
                            <tr>
                                <td>${jugador.user.username}<td>
                                <input type="radio" name="id" value="${jugador.id}"/>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${jugadores}" var="jugador">
                        <c:if test="${jugador.user.username == nombreUsuario}">
                            <input type="hidden" name="jugador_id" value="${jugador.id}"/>
                        </c:if>
                    </c:forEach>
                </div>
                <th style="text-align: left;"><button class="btn btn-default" type ="submit">Agregar como amigo</button></th>
                <th style="text-align: right;"><a class="btn btn-default" href="/jugadores/perfil/${nombreUsuario}/amigos">Mis amigos</a></th>
            </table>
        </form:form>
    </jsp:body>
</petclinic:layout>