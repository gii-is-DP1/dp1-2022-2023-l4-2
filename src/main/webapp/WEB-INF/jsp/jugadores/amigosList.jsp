<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Lista de amigos</h2>
    </div>
    <table id="amigosTable" class="table table-striped" style="border: 1px solid">
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

    <div style="text-align: center;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;";>Estas siguiendo a:</h2>
    </div>

    <table id="amigosTable" class="table table-striped" style="border: 1px solid">
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

    <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 1%;  width:100%;">
        <table>
            <tr>
                <td>
                    <div style="text-align:center; margin-top: 5%;">
                        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;";>Encuentra a tus amigos!!</h2>
                    </div>
                    <div style="text-align:center; margin-top: 5%;">
                        <a class="btn btn-default" style="font-size: 15px; font-family: sans-serif; "  href="/jugadores/search">Ver todos los jugadores</a>
                    </div>
                    <div style="text-align:center; margin-top: 5%;">
                        <a class="btn btn-default" style="font-size: 15px; font-family: sans-serif; "  href="/jugadores/searchFilter">Buscar jugadores</a>
                    </div>
                    <div style="text-align:center; margin-top: 5%;">
                        <a class="btn btn-default" style="font-size: 15px; font-family: sans-serif; "  href="/jugadores/perfil/${username}">Volver al Perfil</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</petclinic:layout>