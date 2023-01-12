<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>lista de jugadores</h2>
    </div>
    <table id="logrosTable" class="table table-striped" style="border: 1px solid">
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
        <c:forEach items="${jugadores}" var="jugador" varStatus="status">
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
                        <c:out value="${fCreado[status.index]}"/>
                    </td>
                    <td>
                        <c:out value="${jugador.creator}"/>
                    </td>
                    <td>
                        <c:out value="${fModificado[status.index]}"/>
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
    <div style="display: flex; flex-direction: row; justify-content:center; font-size: 19px;">
        <table >
            <tr>  
                <div>
                    <td><h1 style = " font-size: 20px; margin-bottom: 2%;";>P&#225;ginas:</h1></td>
                
                    <c:forEach begin="0" step="1" end="${num}" var="variable">
                        <td>
                            <div style="margin-left: 30%;">
                                <spring:url value="/jugadores?page=${variable}" htmlEscape="true" var="como"/>
                                <a class="btn btn-default" style="margin-left: 5%;" href="${como}" value = "${variable}"><c:out value = "${variable+1}"/></a>
                            </div>
                        </td>
                    </c:forEach>
                </div> 
            </tr>
        </table>
    </div>

</petclinic:layout>