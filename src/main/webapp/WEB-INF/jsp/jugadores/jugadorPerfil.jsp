<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="perfil">
    
    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 100px;text-align: center;">
        <c:out value ="${jugador.user.username}"/>
    </div>
     <div>
        <spring:url value="/resources/images/Soldado${faccionFavorita}.png" var="img" />
        <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${img}" />
    </div>

    <c:if test = "${jugador.user.username == nombreUsuario}">
        <div style="text-align: center; ">
            <a class="btn btn-default" style="font-size: 15px; font-family: sans-serif; margin: 1%;"  href="/jugadores/editPerfil/${username}">Editar Perfil</a>
        </div>
    </c:if>
    

    <table id="JugadorPerfil" class="table table-striped" style="border: 1px solid">
        <tr>
            <th>Nombre</th>
            <th><c:out value = "${jugador.firstName}"/></th>
        </tr>
        <tr>
            <th>Apellidos</th>
            <th><c:out value = "${jugador.lastName}"/></th>
        </tr>
        <tr>
            <th>Partidas jugadas</th>
            <th><c:out value = "${numPartidasJugadas}"/></th>
        </tr>
        <tr>
            <th>Partidas ganadas</th>
            <th><c:out value = "${numPartidasGanadas}"/></th>
        </tr>
        <tr>
            <th>Victorias como leal</th>
            <th><c:out value = "${victoriasComoLeal}"/></th>
        </tr>
        <tr>
            <th>Victorias como traidor</th>
            <th><c:out value = "${victoriasComoTraidor}"/></th>
        </tr>
        <tr>
            <th>Victorias como mercader</th>
            <th><c:out value = "${victoriasComoMercader}"/></th>
        </tr>
        <tr>
            <th>Tiempo jugado</th>
            <th><c:out value = "${tiempoJugado}"/></th>
        </tr>
        <tr>
            <th>Facci&#243;n favorita</th>
            <th><c:out value = "${faccionFavorita}"/></th>
        </tr>
        <tr>
            <th>Numero de Amigos</th>
            <th><c:out value = "${amigosC}"/></th>
        </tr>
        <tr>
            <th>Numero de seguidores</th>
            <th><c:out value = "${seguidores}"/></th>
        </tr>
        <tr>
            <th>Siguiendo</th>
            <th><c:out value = "${siguiendo}"/></th>
        </tr>
    </table>

    <div style="text-align: center; justify-content: space-between;">

        <div style="position: relative; font-family: 'Dalek Pinpoint';">
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;"  href="/jugadores/perfil/${username}/amigos">Amigos</a>
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;"  href="/jugadores/partidas/${username}">Historial de Partidas</a>
                <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; margin-left: 10%; margin-right: 10%;"  href="/jugadores/logros/${username}">Mis Logros</a>
        </div>
    </div>
    
</petclinic:layout>