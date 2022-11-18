<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="searchFilter">
    <jsp:body>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Buscar amigos:</h2> 
        <form:form modelAttribute="String" class="form-horizontal" id="jugadorForm" method="GET">
            <div class="form-group has-feedback">
                <table id="searchFilter" class="table table-striped">
                    <tr>
                        <th>
                            <input 
                                style="width: 50%;"
                                placeholder="Buscar jugador"
                                type="text"
                                value="${username}"
                                name="username"
                            />
                        </th>
                        <th></th>
                    </tr>
                    <c:if test="${error!=null}">
                        <tr>
                            <td colspan="2" style="color:rgb(247, 11, 11)">${error}<td>
                        </tr>
                    </c:if>
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
                            <input style="display:none" type="hidden" name="jugador_id" value="${jugador.id}"/>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td style="text-align: left;"><button class="btn btn-default" type ="submit">Agregar como amigo</button></td>
                        <td style="text-align: right;"><a class="btn btn-default" href="/jugadores/perfil/${nombreUsuario}/amigos">Volver a amigos</a></td>
                    </tr>
                </table>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
