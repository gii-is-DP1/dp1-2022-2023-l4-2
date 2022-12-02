<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:lo2 pageName="partidas">

    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center; height: 200;">

        <div style="font-size: 35px">
            <c:out value="${principal.name}"/>
        </div>

        <table class="table table-striped">
            <tr>
                <td style="background: #e2861e">Votos a favor del cesar:  <c:out value="${partida.votosFavorCesar}"/></td>
                <td style="background: #cecbc8 ">Votos contra el cesar:  <c:out value="${partida.votosContraCesar}"/></td>
                <td style="background: #e2861e">Limite de votos:  <c:out value="${partida.limite}"/></td>
            </tr>
        </table>

        <c:if test = "${votoMercaderE!=null}">
            <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;color: yellow; background-color: black;">
                <c:out value="${votoMercaderE.getJugador().getUser().getUsername()}"/><span>, ha realizado un voto neutro</span>
            </div>
        </c:if>

        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;">Ronda:  <c:out value="${partida.ronda}"/></h2>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;">Turno:  <c:out value="${partida.turno}"/></h2>

        <div style="text-align:left">
            Jugadores de la partida:
            <div>
                <c:forEach items="${partida.jugadores}" var="jugador">
                    <tr style = "text-align: left; ";>
                        <td>
                            <div>
                                <c:out value="${jugador.user.username}"/> -  <c:out value="${jugador.rol}"/>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <div style="height:100px; width: 100px;">

                </div>
                <c:if test ="${faccionApoyada == null}">
                <div style="text-align:left">
                    Tus opciones: 
                    <div>
                        <c:forEach items="${elegir}" var="opcion">
                            <tr style = "text-align: left; ";>
                                <td>
                                    <div>
                                        <c:out value="${opcion.getName()}"/> 
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </div>
                </div>
                </c:if>
                <div style="height:100px; width: 100px;">
        
                </div>
                
                <c:if test ="${faccionApoyada != null}">
                    <div style="text-align:left">
                        Tu faccion Apoyada
                        <div>
                                <tr style = "text-align: left; ";>
                                    <td>
                                        <div>
                                            <c:out value="${faccionApoyada.getName()}"/> 
                                        </div>
                                    </td>
                                </tr>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
            <c:if test = "${faccionApoyada == null}">
                <c:if test = "${partida.ronda == 1}">
                    <c:if test = "${partida.turno != 1}">
                        <a class="btn btn-default" href="/partidas/jugar/consul/${partida.id}">Siguiente</a>
                    </c:if>
                </c:if> 
                <c:if test = "${partida.ronda == 2 && partida.turno == 1}">
                    <c:if test = "${partida.fase == 2}">
                        <a class="btn btn-default" href="/partidas/jugar/consul/${partida.id}">Siguiente</a>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
        
        <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
            <c:if test = "${numVotos == 0}">
                <c:if test="${partida.getRonda() == 1}">
                    <a class="btn btn-default" href="/partidas/jugar/edil/${partida.id}">Siguiente</a>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${jugadorLog.rol.getName() == 'Pretor'}">
            <c:if test="${votoRT > 1}">
                <c:if test = "${partida.fase==0}">
                    <c:if test="${partida.getRonda() == 1}">
                    <a class="btn btn-default" href="/partidas/jugar/pretor/${partida.id}">Siguiente</a>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
            <c:if test="${partida.getRonda() == 2 && partida.fase !=2}">
                <c:if test="${!hayConsul}">
                    <a class="btn btn-default" href="/partidas/jugar/consul/eleccionP/${partida.id}">Escoger Pretor</a>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${partida.ronda == 3}">
            <div style="text-align:center">
                <a class="btn btn-default" href="/partidas/final/${partida.id}">Ver resultado</a>
            </div>
        </c:if>

        <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: right;">
            <button>
                <a class="btn btn-default" href="/chat/creaChat/${partida.id}" target="_blank">Chat</a></th>
            </button>
        </div>
        
        <c:if test="${jugadorLog.rol.getName() == 'Consul'}">
            <c:if test="${partida.getRonda() == 2 && partida.fase !=2}">
                <c:if test="${2 > numEdil}">
                    <a class="btn btn-default" href="/partidas/jugar/consul/eleccionE/${partida.id}">Escoger Edil</a>
                </c:if>
            </c:if>
        </c:if>

        <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
            <c:if test="${partida.getRonda() == 2}">
                <c:if test="${!votosCambio.isEmpty()}">
                    <c:if test="${voto != null && partida.fase !=2}">
                        <a class="btn btn-default" href="/partidas/jugar/edil/edit/${partida.id}/${voto.id}">Cambiar voto</a>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${jugadorLog.rol.getName() == 'Edil'}">
            <c:if test = "${numVotos == 0}">
                <c:if test="${partida.getRonda() == 2 && yaE}">
                    <a class="btn btn-default" href="/partidas/jugar/edil/${partida.id}">Siguiente</a>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${jugadorLog.rol.getName() == 'Pretor'}">
            <c:if test="${votoRT > 1}">
                <c:if test = "${partida.fase==0}">
                    <c:if test="${partida.getRonda() == 2 && yaE}">
                    <a class="btn btn-default" href="/partidas/jugar/pretor/${partida.id}">Siguiente</a>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
    </div>
   
</petclinic:lo2>