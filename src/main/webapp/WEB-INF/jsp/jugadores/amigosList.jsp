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
            <th>Eliminar amigo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${amigos}" var="amigo">
            <c:if test = "${amigo.user.username != username}">
                <c:forEach items="${amigo.amigoDe}" var="amigoDos">
                    <c:if test = "${amigoDos.user.username == username}">
                        <tr>
                            <td>
                                <c:out value="${amigo.user.username}"/>  
                            </td>
                            <td>
                                <a href="/jugadores/perfil/${username}/amigos/delete/${amigo.user.username}"> 
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </a> 
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
        </tbody>
    </table>


    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Estas siguiendo a:</h2> 
    <table id="amigosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre de usuario</th>
            <th>Dejar de seguir</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${amigos}" var="amigo">
            <c:if test = "${amigo.user.username != username}">
                <tr>
                    <td>
                        <c:out value="${amigo.user.username}"/>  
                    </td>
                    <td>
                        <a href="/jugadores/perfil/${username}/amigos/delete/${amigo.user.username}"> 
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a> 
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <table>
        <tr>
            <th><a class="btn btn-default" href="/jugadores/search">Todos los jugadores</a></th>
        </tr>
        <tr>
            <th><a class="btn btn-default" href="/jugadores/searchFilter">Buscar jugadores</a></th>
        </tr>
        <tr>
            <th><a class="btn btn-default" href="/jugadores/perfil/${username}">Volver al Perfil</a></th>
        </tr>
    </table>    
    

</petclinic:layout>