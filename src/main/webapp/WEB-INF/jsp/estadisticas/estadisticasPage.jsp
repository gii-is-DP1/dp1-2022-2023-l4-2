<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="estadisticas">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Estad&#237;sticas generales</h2>

    <table id="estadisticasGeneralesTable" class="table table-striped">
        <thead>
        <tr>
            <th>N&#250;mero de partidas</th>
            <th><c:out value="${numPartidas}"/></th>

        </tr>
        <tr>
            <th>Total victorias leal</th>
            <th><c:out value="${victoriasLeal}"/></th>
        </tr>
        <tr>
            <th>Total victorias traidor</th>
            <th><c:out value="${victoriasTraidor}"/></th>
        </tr>
        <tr>
            <th>Total victorias mercader</th>
            <th><c:out value="${victoriasMercader}"/></th>
        </tr>
        <tr>
            <th>Media de minutos partida</th>
            <th><c:out value="${mediaMinutosPartida}"/></th>
        </tr>
        <tr>
            <th>M&#225;ximo votos a favor C&#233;sar</th>
            <th><c:out value="${maxVotosAFavorCesar}"/></th>
        </tr>
        <tr>
            <th>M&#225;ximo votos en contra C&#233;sar</th>
            <th><c:out value="${maxVotosEnContraCesar}"/></th>
        </tr>
        <tr>
            <th>M&#225;xima diferencia de votos en una partida</th>
            <th><c:out value="${maxDiferenciaDeVotos}"/></th>
        </tr>
        <tr>
            <th>Facci&#243;n que ha ganado el menor n&#250;mero de veces</th>
            <th><c:out value="${faccionPerdedora}"/></th>
        </tr>
        </thead>
    </table>

    <h2>Top 5 jugadores con m&#225;s victorias</h2>
    <table id="jugadoresMasVictoriasTable" class="table table-striped">
        <thead>
        <c:forEach items="${topJugadoresConVictoria.entrySet()}" var="top">
            <tr>
                <th><c:out value="${top.getKey()}"/></th>
                <th><c:out value="${top.getValue()}"/></th>
            </tr>
        </c:forEach>
        </thead>
    </table>

    <h2>Top 5 jugadores con m&#225;s partidas</h2>
    <table id="jugadoresMasPartidasTable" class="table table-striped">
        <thead>
        <c:forEach items="${topJugadoresConPartida}" var="top">
            <tr>
                <th><c:out value="${top.getUser().getUsername()}"/></th>
                <th><c:out value="${top.getPartidasJugadas()}"/></th>
            </tr>
        </c:forEach>
        </thead>
    </table>
</petclinic:layout>
