<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:lo2 pageName="partidas">

    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center; height: 200;">

        <div style="font-size: 35px; justify-content: center;">
            <c:forEach items="${partida.jugadores}" var="jugador">
                <c:if test="${jugador.user.username == principal.name}">
                    <c:out value="${jugador.user.username}"/> -  <c:out value="${jugador.rol}"/>
                </c:if>
            </c:forEach>
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

        <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; margin-bottom: 1%;">
            <c:if test = "${faccionGanadora.equals(faccionApoyada)}">
                <div style="text-align:center; color: green;">
                    <c:out value="ENHORABUENA"/>
                    </br>
                    <c:out value="HAS GANADO LA PARTIDA!"/>
                </div>
            </c:if>

            <c:if test = "${!faccionGanadora.equals(faccionApoyada)}">
                <div style="text-align:center; color: red;">
                    <c:out value="VAYA! QUE MALA SUERTE"/>
                    </br>
                    <c:out value="HAS PERDIDO, SIGUE INTENTANDOLO"/>
                </div>
            </c:if>
            
            <div style="margin:3%">
                La p&#225;rtida ha durado 
                <c:out value="${partida.getTiempo()} minutos"/>
            </div>

        </div>

        <div style="display: flex; flex-direction: row; justify-content: space-between; font-size: 19px;">
            <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%;">
                <c:if test="${faccionApoyada != null}">
                    <div style="text-align:center;">
                        Tu facci&#243;n Apoyada:
                        <div>
                            <div style="padding: 1%; text-align:center;">
                                <c:out value="${faccionApoyada.getName()}" />
                                <div style="text-align: center;">
                                    <spring:url value="/resources/images/${faccionApoyada.getName()}.png" var="faccion" />
                                    <img style ="width: 30%;  height: 75%;" src="${faccion}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

            <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%;">
                <c:if test="${faccionApoyada != null}">
                    <div style="text-align:center;">
                        Facci&#243;n Ganadora:
                        <div>
                            <div style="padding: 1%; text-align:center;">
                                <c:out value="${faccionGanadora.getName()}" />
                                <div style="text-align: center;">
                                    <spring:url value="/resources/images/${faccionGanadora.getName()}.png" var="ganadora" />
                                    <img style ="width: 30%;  height: 75%;" src="${ganadora}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <div style="text-align: center; margin: 3%;">
            <a class="btn btn-default" href="/home">VOLVER AL INICIO</a>
        </div>

    </div>
   
</petclinic:lo2>