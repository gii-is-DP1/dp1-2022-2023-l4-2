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

        <c:if test="${partida.ronda != 3}">

        <div style="text-align:left">
            Jugadores de la partida:
        </div>


        <div>
            <table class="table table-striped">
                <tr>
                    <c:forEach items="${partida.jugadores}" var="jugador">
                        <td>
                            <div>
                                <c:out value="${jugador.user.username}"/> -  <c:out value="${jugador.rol}"/>
                                <div>
                                    <spring:url value="/resources/images/${jugador.rol.getName()}.png" var="rol"/>
                                    <img width="70%" height="70%" src="${rol}"/>
                                </div>
                            </div>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </c:if>

        <c:if test="${partida.ronda == 3}">
            
            <div style="text-align:left">
                ¡Ha ganado la facci&#243;n:
                <div>
                        <tr style = "text-align: center; ";>
                            <td>
                                <div>
                                    <c:out value="${faccionGanadora.getName()}"/> 

                                </div>
                            </td>
                        </tr>
                </div>
                !
            </div>
            <div style="text-align:center">
                <spring:url value="/resources/images/${faccionGanadora.getName()}.png" var="faccion"/>
                <img src="${faccion}"/>
            </div>
            <div style="text-align:right">
                Tiempo jugado
                <div>
                        <tr style = "text-align: center; ";>
                            <td>
                                <div>
                                    <c:out value="${partida.getTiempo()} MIN"/>
                                </div>
                            </td>
                        </tr>
                </div>
            </div>
        </c:if>
        <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: right;">
            <button>
                <a class="btn btn-default" href="/home"> Inicio </a></th>
            </button>
        </div>
        
    </div>
   
</petclinic:lo2>