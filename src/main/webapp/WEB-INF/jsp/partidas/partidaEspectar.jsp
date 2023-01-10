<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:lo2 pageName="partidas">

    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center; height: 200;">

        <div style="font-size: 35px">
            <c:out value="${principal.name}"/>
            <div>
                (Espectador)
            </div>
        </div>

        <table class="table table-striped">
            <tr>
                <td style="background: #e2861e">Votos a favor del C&#233;sar:  <c:out value="${partida.votosFavorCesar}"/></td>
                <td style="background: #cecbc8 ">Votos contra el C&#233;sar:  <c:out value="${partida.votosContraCesar}"/></td>
                <td style="background: #e2861e">L&#237;mite de votos:  <c:out value="${partida.limite}"/></td>
            </tr>
        </table>

        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;">Ronda:  <c:out value="${partida.ronda}"/></h2>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;">Turno:  <c:out value="${partida.turno}"/></h2>

        
        <div style="text-align:left">
            Jugadores de la partida:
        </div>

        <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; margin-bottom: 1%;">
            <c:if test="${partida.ronda != 3 && partida.jugadores.size() < 7}">
                <div style="text-align:center">
                    Jugadores de la partida:
                    <table>
                        <tr>
                            <c:forEach items="${partida.jugadores}" var="jugador">
                                <td>
                                    <div>
                                        <div style="padding: 2%; text-align:center">
                                            <c:out value="${jugador.user.username}"/> -  <c:out value="${jugador.rol}"/>
                                            <div>
                                                <spring:url value="/resources/images/${jugador.rol.getName()}.png" var="rol"/>
                                                <img width="70%" height="70%" src="${rol}"/>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${partida.ronda != 3 && partida.jugadores.size() >= 7}">
                <div style="text-align:center">
                    Jugadores de la partida:
                </div>
                <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 1%; background-color: #f9f9f9; width:100%;">
                    <table>
                        <tr>
                            <c:forEach items="${partida.jugadores}" var="jugador">
                                <td>
                                    <div style="margin: 5%;">
                                        <div style="text-align:center; margin: 5%;">
                                            <c:out value="${jugador.user.username}"/> </br>  <c:out value="${jugador.rol}"/>
                                        </div>

                                        <div style="text-align:center;">
                                            <spring:url value="/resources/images/${jugador.rol.getName()}.png" var="rol"/>
                                            <img width="135px" height="185px" src="${rol}"/>
                                        </div>
                                    </div>

                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${partida.ronda == 3}">
                <div style="text-align:center;">
                    La partida ha terminado</br>Facci&#243;n Ganadora:
                    <div>
                        <div style="padding: 1%; text-align:center;">
                            <c:out value="${faccionGanadora.getName()}" />
                            <div style="text-align: center;">
                                <spring:url value="/resources/images/${faccionGanadora.getName()}.png" var="ganadora" />
                                <img style ="width: 20%;  height: 140%;" src="${ganadora}" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>

        <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center;">
            <button>
                <a class="btn btn-default" href="/home"> Inicio </a></th>
            </button>
        </div>
        
    </div>
   
</petclinic:lo2>