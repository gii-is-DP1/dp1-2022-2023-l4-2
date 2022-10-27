<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="estadisticas">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Estadisticas generales</h2>

    <table id="estadisticasGeneralesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Numero de partidas</th>
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
            <th>Maximo votos a favor Cesar</th>
            <th><c:out value="${maxVotosAFavorCesar}"/></th>
        </tr>
        <tr>
            <th>Maximo votos en contra Cesar</th>
            <th><c:out value="${maxVotosEnContraCesar}"/></th>
        </tr>
        </thead>
        <tbody>
            
        </tbody>
    </table>
</petclinic:layout>
