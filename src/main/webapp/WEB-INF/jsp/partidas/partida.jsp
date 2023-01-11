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

        <c:if test = "${votoMercaderE!=null}">
            <div style="display: flex; flex-direction: row; justify-content: center; background-color: #000000; font-size: 20px; margin: 0%;">
                <div style="display: flex; flex-direction:column; align-items:center; justify-content: center;">
                    <div style="color: red;">
                        Atenci&#243;n!!
                    </div>
                    <div style = "color: rgb(255, 255, 255);">
                        <c:out value="${votoMercaderE.getJugador().getUser().getUsername()}"/><span>, ha realizado un voto neutro</span>
                        <spring:url value="/resources/images/votoNeutro.png" var="votoNeutro"/>
                    </div>
                </div>
                <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; margin-top: -1%; margin-bottom: -1%;">
                    <spring:url value="/resources/images/votoNeutro.png" var="neutro" />
                    <img style ="width: 30%;  height: 60%;" src="${neutro}" />
                </div>
            </div>
        </c:if>

        <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; margin-bottom: 1%;">
            <c:if test="${partida.ronda != 3 && partida.jugadores.size() < 7}">
                <div style="text-align:center">
                    Jugadores de la partida:
                    <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 1%; background-color: #f9f9f9; width:100%;">
                        <table>
                            <tr>
                                <c:forEach items="${partida.jugadores}" var="jugador">
                                    <td>
                                        <div>
                                            <div style="padding: 2%; text-align:center; width: 190px;">
                                                <c:out value="${jugador.user.username}"/> </br>  <c:out value="${jugador.rol}"/>
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
                <div style="text-align:center">
                    La partida ha finalizado
                </div>
                <div style="text-align:center; margin: 3%;">
                    <a class="btn btn-default" href="/partidas/final/${partida.id}">Ver resultado</a>
                </div>
            </c:if>
        </div>

        <div style="display: flex; flex-direction: row; justify-content: space-between; font-size: 19px;">
            <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%;">
                <c:if test="${faccionApoyada == null}">
                    <div style="text-align:center">
                        Tus opciones de facci&#243;n para apoyar:
                        <table>
                            <tr>
                                <c:forEach items="${elegir}" var="opcion">
                                    <td>
                                        <div>
                                            <div style="padding: 2%; text-align:center">
                                                <c:out value="${opcion.getName()}" />
                                                <div>
                                                    <spring:url value="/resources/images/${opcion.getName()}.png" var="opcion" />
                                                    <img class="img-responsive" style ="margin: auto;width: 50%;  height: 50%;" src="${opcion}" />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </c:forEach>
                            </tr>
                        </table>
                    </div>
                </c:if>
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

            <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; border: 1px solid; padding: 1%; background-color: #f9f9f9; width:47%; ">
                <div style="margin: 5%;">
                    <c:if test = "${numVotos == 0}">
                        <c:if test="${partida.getRonda() == 1}">
                            <c:if test="${jugadorLog.rol.getName() != 'Edil' && votoRT <= 1}">
                                <div>
                                    <c:out value="Esperando votaciones de los Ediles"/>
                                </div>
                            </c:if>
                            <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
                                <a class="btn btn-default" href="/partidas/jugar/edil/${partida.id}">Votar</a>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>

                <div style="margin: 5%;">
                    <c:if test = "${numVotos == 0}">
                        <c:if test="${partida.getRonda() == 2 && hayPretor && numEdil == 2}">
                            <c:if test="${jugadorLog.rol.getName() != 'Edil' && votoRT <= 1}">
                                <div>
                                    <c:out value="Esperando votaciones de los Ediles"/>
                                </div>
                            </c:if>
                            <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
                                <a class="btn btn-default" href="/partidas/jugar/edil/${partida.id}">Votar</a>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>

                <c:if test="${votoRT > 1}">
                    <c:if test = "${partida.fase==0}">
                        <c:if test="${partida.getRonda() == 1}">
                            <c:if test="${jugadorLog.rol.getName() == 'Pretor'}">
                                <a class="btn btn-default" href="/partidas/jugar/pretor/${partida.id}">Ver votacion de un jugador</a>
                            </c:if>
                            <c:if test="${jugadorLog.rol.getName() != 'Pretor'}">
                                <c:out value="Esperando el posible cambio de votacion del Pretor"/>
                            </c:if>
                        </c:if>
                    </c:if>
                </c:if>

                <c:if test="${votoRT > 1}">
                    <c:if test = "${partida.getRonda() == 2 && partida.fase==0}">
                        <c:if test="${jugadorLog.rol.getName() != 'Pretor'}">
                                <c:out value="Esperando el posible cambio de votacion del Pretor"/>
                            </c:if>
                        <c:if test="${yaE}">
                            <c:if test="${jugadorLog.rol.getName() == 'Pretor'}">
                                <a class="btn btn-default" href="/partidas/jugar/pretor/${partida.id}">Ver votacion de un jugador</a>
                            </c:if>
                        </c:if>
                    </c:if>
                </c:if>

                
                <c:if test="${partida.getRonda() == 2}">
                    <c:if test="${partida.fase !=2 && jugadorLog.user.username != votoElegido.jugador.user.username && votoElegido != null}">
                        El Edil ${votoElegido.jugador.user.username} est&#225; cambiando su voto por obligaci&#243;n del Pretor
                    </c:if>
                    <c:if test="${!votosCambio.isEmpty()}">
                        <c:if test="${voto != null && partida.fase !=2}">
                            <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
                                <a class="btn btn-default" href="/partidas/jugar/edil/edit/${partida.id}/${voto.id}">El Pretor te ha obligado a cambiar el voto</a>
                            </c:if>
                                
                        </c:if>
                    </c:if>
                </c:if>

                <c:if test = "${partida.ronda == 1 && partida.fase == 1}">
                    <c:if test = "${partida.turno != 1}">
                        <c:if test="${jugadorLog.rol.getName() == 'Consul' && faccionApoyada == null}">
                            <a class="btn btn-default" href="/partidas/jugar/consul/${partida.id}">Escoger facci&#243;n</a>
                        </c:if>
                        <c:if test="${jugadorLog.rol.getName() != 'Consul'}">
                            <c:out value="Esperando a que el Consul escoja su faccion"/>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test = "${partida.ronda == 2 && partida.turno == 1}">
                    <c:if test = "${partida.fase == 2}">
                        <c:if test="${jugadorLog.rol.getName() == 'Consul' && faccionApoyada == null}">
                            <a class="btn btn-default" href="/partidas/jugar/consul/${partida.id}">Escoger facci&#243;n</a>
                        </c:if>
                        <c:if test="${jugadorLog.rol.getName() != 'Consul'}">
                            <c:out value="Esperando a que el Consul escoja su faccion"/>
                        </c:if>
                    </c:if>
                </c:if>

                <c:if test="${partida.getRonda() == 2 && partida.fase !=2}">
                    <c:if test="${!hayPretor}">
                        <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
                            <a class="btn btn-default" href="/partidas/jugar/consul/eleccionP/${partida.id}">Elegir Pretor</a>
                        </c:if>
                    </c:if>
                    <c:if test="${(!hayPretor || numEdil < 2) && jugadorLog.rol.getName() != 'Consul'}">
                        <c:out value="Esperando a que el Consul reparta los roles"/>
                    </c:if>
                </c:if>

                <div style="margin: 3%;">
                    <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
                        <c:if test="${partida.getRonda() == 2 && partida.fase !=2 && hayPretor}">
                            <c:if test="${0 == numEdil}">
                                <a class="btn btn-default" href="/partidas/jugar/consul/eleccionE/${partida.id}">Elegir Primer Edil</a>
                            </c:if>
                        </c:if>
                    </c:if>

                    <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
                        <c:if test="${partida.getRonda() == 2 && partida.fase !=2 && hayPretor}">
                            <c:if test="${1 == numEdil}">
                                <a class="btn btn-default" href="/partidas/jugar/consul/eleccionE/${partida.id}">Elegir Segundo Edil</a>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>


                <div style="margin: 5%;">
                    <a class="btn btn-default" href="/chat/creaChat/${partida.id}" target="_blank">Chat</a>
                </div>
            </div>
        </div>

    </div>
   
</petclinic:lo2>