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
                <td style="background: #e2861e">Votos a favor del C&#233;sar:  <c:out value="${partida.votosFavorCesar}"/></td>
                <td style="background: #cecbc8 ">Votos contra el C&#233;sar:  <c:out value="${partida.votosContraCesar}"/></td>
                <td style="background: #e2861e">L&#237;mite de votos:  <c:out value="${partida.limite}"/></td>
            </tr>
        </table>

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
                        Tu facci&#243;n Apoyada
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

        
        <c:if test="${partida.ronda == 1}">
            <form:form modelAttribute="faccionType"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${faccionType.id}"/>
            <div class="form-group has-feedback">                
                <tr>
                    <td>Voto:</td>
                            <div style="display: flex;
                            justify-content: center;
                            align-items: center;
                            flex-wrap: wrap;">
                                <div>
                                <spring:url value="/resources/images/SoldadoLeal.png" var="Leal"/>
                                <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${Leal}"/>
                                <td>Leal</td>
                                <input type="radio" name="name" value="Leal" checked/>
                                </div>
                                <div>
                                <spring:url value="/resources/images/SoldadoTraidor.jpg" var="Traidor"/>
                                <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${Traidor}"/>
                                <td>Traidor</td>
                                <input type="radio" name="name" value="Traidor"/>
                                </div>
                            </div>
                </tr>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
                     <button class="btn btn-default" type="submit">Votar</button>
                </div>
            </div>
        </form:form>
        </c:if>
        <c:if test="${partida.ronda == 2}">
            <form:form modelAttribute="faccionType"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${faccionType.id}"/>
            <div class="form-group has-feedback">                
                <tr>
                    <td>Voto:</td>
                    
                    <div style = "display: flex;
                    justify-content: center;
                    align-items: center;
                    flex-wrap: wrap;">
                        <div>
                        <spring:url value="/resources/images/SoldadoLeal.png" var="Leal"/>
                        <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${Leal}"/>
                        <td>Leal</td>
                        <input type="radio" name="name" value="Leal" checked/>
                        </div>
                        <div>
                        <spring:url value="/resources/images/SoldadoTraidor.jpg" var="Traidor"/>
                        <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${Traidor}"/>
                        <td>Traidor</td>
                        <input type="radio" name="name" value="Traidor"/>
                        </div>
                        <div>
                        <spring:url value="/resources/images/SoldadoNeutral.png" var="Mercader"/>
                        <img class="img-responsive" style ="margin: auto;width: 200px;   height: 200px;   border-radius: 50%;" src="${Mercader}"/>
                        <td>Mercader</td>
                        <input type="radio" name="name" value="Mercader"/>
                        </div>
                    </div>
                    
                </tr>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
                     <button class="btn btn-default" type="submit">Votar</button>
                </div>
            </div>
        </form:form>
        </c:if>
        
    </div>
   
</petclinic:lo2>