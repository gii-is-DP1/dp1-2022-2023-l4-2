<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="perfil">

    <c:if test = "${jugador.user.username == nombreUsuario}">
        <a class="btn btn-default" href="/jugadores/editPerfil/${username}">Editar Perfil</a>
    </c:if>
    
    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 100px;text-align: center;">
        <c:out value ="${jugador.user.username}"/>
    </div>
    <c:if test="${faccionFavorita == 'Leal'}">
        <div>
            <spring:url value="/resources/images/SoldadoLeal.png" htmlEscape="true" var="logo"/>
            <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${logo}"/>
        </div>
    </c:if>

    <c:if test="${faccionFavorita == 'Mercader'}">
        <div>
            <spring:url value="/resources/images/SoldadoNeutral.png" htmlEscape="true" var="logo"/>
            <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${logo}"/>
        </div>
    </c:if>

    <c:if test="${faccionFavorita == 'Traidor'}">
        <div>
            <spring:url value="/resources/images/SoldadoTraidor.jpg" htmlEscape="true" var="logo"/>
            <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${logo}"/>
        </div>
    </c:if>

    <table id="JugadorPerfil" class="table table-striped">
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
            <th>Faccion favorita</th>
            <th><c:out value = "${faccionFavorita}"/></th>
        </tr>
    </table>
    <table  style= "width: 100%; text-align:center;position: relative;">
        <tr>
            <th style="text-align: left;"><a class="btn btn-default" href="/jugadores/partidas/${username}">Amigos</a></th>
            <th  style="text-align: center;" ><a class="btn btn-default" href="/jugadores/partidas/${username}">Historial de Partidas</a></th>
            <th  style="text-align: right;"><a class="btn btn-default" href="/jugadores/logros/${username}">Mis Logros</a></th>
        </tr>
    </table>   
</petclinic:layout>