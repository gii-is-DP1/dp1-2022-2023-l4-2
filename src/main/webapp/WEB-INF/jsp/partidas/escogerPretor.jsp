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
            <div style="text-align:center">
                Jugadores de la partida:
            </div>
            <div style="margin-left: 5%;">
                <table>
                    <tr>
                        <c:forEach items="${partida.jugadores}" var="jugador">
                            <td>
                                <c:out value="${jugador.user.username}"/> -  <c:out value="${jugador.rol}"/>
                                <c:if test="${jugador.rol == 'Consul'}">
                                    <div>
                                        <spring:url value="/resources/images/${jugador.rol.getName()}.png" var="rol"/>
                                        <img width="70%" height="70%" src="${rol}"/>
                                    </div>
                                </c:if>
                                <c:if test="${jugador.rol != 'Consul'}">
                                    <div>
                                        <spring:url value="/resources/images/Sin rol.png" var="srol"/>
                                        <img width="70%" height="70%" src="${srol}"/>
                                    </div>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </table>
            </div>
        </div>

        <div style="display: flex; flex-direction: row; justify-content: space-between; font-size: 19px;">
            <div style="border: 1px solid; padding: 1%; background-color: #f9f9f9; width: 47%;">
                <c:if test="${faccionApoyada == null}">
                    <div style="text-align:center">
                        Tus opciones de facci&#243;n para apoyar:
                        <table>
                            <tr>
                                <c:forEach items="${opciones}" var="opcion">
                                    <td>
                                        <div>
                                            <div style="padding: 2%; text-align:center">
                                                <c:out value="${opcion}" />
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
                                    <img style ="width: 30%;  height: 55%;" src="${faccion}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

            <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; border: 1px solid; padding: 0%; background-color: #f9f9f9; width:47%; ">
                <div style="padding: 1%; ">
                    <div style="text-align: center; margin-bottom: -2%;">
                            &#191;Qui&#233;n quieres que sea el Pretor?
                            <div style="text-align: center; margin: 1%;">
                                <spring:url value="/resources/images/Pretor.png" var="pretor" />
                                <img style ="width: 45%;  height: 50%;" src="${pretor}" />
                            </div>
                            <div>
                                <form:form modelAttribute="jugador" class="form-horizontal">
                                    <div class="form-group has-feedback">   
                                        <c:forEach items="${jugFilt}" var="jug">
                                            <tr>
                                                <td>${jug.user.username}</td>
                                                <td>
                                                    <input type="radio" name="id" value="${jug.id}" checked/></br>
                                                </td>
                                            </tr>
                                        </c:forEach>    
                                    </div>
                                    <div class="form-group">
                                        <div style="margin-bottom: 4%;">
                                            <button class="btn btn-default" type="submit">Seleccionar Pretor</button>
                                        </div>
                                    </div>
                                </form:form> 
                            </div>
                    </div>
                </div>
            </div>
        </div>  

        <div style = "margin: 1%; text-align: left;">
            <button>
                <a class="btn btn-default" href="/chat/creaChat/${partida.id}" target="_blank">Chat</a></th>
            </button>
        </div>
    </div>
   
</petclinic:lo2>