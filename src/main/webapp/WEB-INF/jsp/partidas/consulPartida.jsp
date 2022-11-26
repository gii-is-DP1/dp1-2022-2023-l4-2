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
            </div>
        </div>


        <div>
            <c:if test= "${partida.fase == 1}">
                <form:form modelAttribute="faccionType"
                   class="form-horizontal">
                   <input type="hidden" name="id" value="${faccionType.id}"/>
                    <div class="form-group has-feedback">                
                        <tr>
                            <td>Faccion apoyada:</td>
                                <c:forEach items="${opciones}" var="opcion">
                                    <td>${opcion}<td>
                                    <input type="radio" name="name" value="${opcion}"/>
                                </c:forEach>
                        </tr>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10"></div>
                            <button class="btn btn-default" type="submit">Apoyar</button>
                        </div>
                    </div>
                </form:form>
            </c:if>
        </div>
        
    </div>
   
</petclinic:lo2>