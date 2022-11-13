<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Lista de amigos:</h2> 

    <table id="amigosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre de usuario</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${amigos}" var="amigo">
            <c:if test = "${amigo.user.username != username}">
                <tr>
                    <td>
                        <c:out value="${amigo.user.username}"/>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <table>
        <tr>
            <th><a class="btn btn-default" href="/jugadores/search">Buscar jugadores</a></th>
        </tr>
        <tr>
            <th><a class="btn btn-default" href="/jugadores/perfil/${username}">Volver al Perfil</a></th>
        </tr>
    </table>    
    

</petclinic:layout>