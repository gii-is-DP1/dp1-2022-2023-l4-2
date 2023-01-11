<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">

    <jsp:body>
        <div style="text-align: center; ">
            <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Nueva partida</h2>
        </div>
        <form:form modelAttribute="partida"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${partida.id}"/>
            <div class="form-group has-feedback" style="text-align: center; font-size: x-large; margin-top: 3%;">     
                <tr>
                    <spring:url value="/resources/images/logo_big.png" var="logo" />
                    <img style ="width: 25%;  height: 30%;" src="${logo}" />
                </br>
                </tr>           
                <tr>
                    <td>N&#250;mero de jugadores:</td>
                    <td>
                        <select name = "numJugadores">
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                            <option>8</option>
                        </select>
                    </td>
                </tr>
            </div>
            <div class="form-group" style="text-align: center; font-size: x-large;">
                <div style="margin: 3%;">
                     <button class="btn btn-default" style="font-size: 20px; font-family: 'Dalek Pinpoint', sans-serif; margin-left: 10%; margin-right: 10%;" type="submit" href="/partidas/new">Crear partida</button>
                </div>
                <div>
                    <a class="btn btn-default" style="font-size: 20px; font-family: 'Dalek Pinpoint', sans-serif; margin-left: 10%; margin-right: 10%;" href="/partidas/seleccionar">Volver al menu de opciones de partida</a>
                </div>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>