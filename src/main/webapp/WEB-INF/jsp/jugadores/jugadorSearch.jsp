<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadorSearch">
    <jsp:body>
        <div style="text-align: center; ">
            <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;">Lista de jugadores</h2>
        </div>
        <form:form modelAttribute="jugador" class="form-horizontal" id="jugadorForm">
            <div class="form-group has-feedback">
                <table id="jugadorSearch" class="table table-striped" style="border: 1px solid">
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
                    <tr>
                        <td style="text-align: left; padding-left: 22%;"><button class="btn btn-default" style="font-size: 20px; font-family: sans-serif;" type ="submit">Agregar como amigo</button></td>
                        <td style="text-align: right; padding-right: 22%;"><a class="btn btn-default" style="font-size: 20px; font-family: sans-serif;" href="/jugadores/perfil/${nombreUsuario}/amigos">Volver a amigos</a></td>
                    </tr>
            </table>
        </form:form>
    </jsp:body>
</petclinic:layout>