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
        </div>

        <div style="display: flex; flex-direction: row; justify-content: space-between; font-size: 19px;">
            <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%;">
                <c:if test= "${partida.fase == 1 || (partida.ronda == 2 && partida.turno == 1)}">
                    <form:form modelAttribute="faccionType" class="form-horizontal">
                        <input type="hidden" name="id" value="${faccionType.id}"/>
                        <table>
                            <tr>
                                &#191;Qu&#233; facci&#243;n desea apoyar?:
                            </tr>
                            <tr>
                                <c:forEach items="${opciones}" var="opcion">
                                    <td>
                                        <div>
                                            <div style="padding: 2%; text-align:center">
                                                <c:out value="${opcion}" />
                                                <input type="radio" name="name" value="${opcion}" checked/>
                                                <div>
                                                    <spring:url value="/resources/images/${opcion}.png" var="opcion" />
                                                    <img class="img-responsive" style ="margin: auto;width: 50%;  height: 50%;" src="${opcion}" />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </c:forEach>
                            </tr>
                        </table>
                        <div style="margin: 1%;">
                            <button class="btn btn-default" type="submit">Escoger facci&#243;n</button>
                        </div>
                    </form:form>
                </c:if>
            </div>

            <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%; ">

                <div style="margin: 5%;">
                    Escoja su facci&#243;n
                </div>

                <div style="margin: 5%;">
                    <a class="btn btn-default" href="/chat/creaChat/${partida.id}" target="_blank">Chat</a>
                </div>
            </div>
        </div>
    </div>
   
</petclinic:lo2>