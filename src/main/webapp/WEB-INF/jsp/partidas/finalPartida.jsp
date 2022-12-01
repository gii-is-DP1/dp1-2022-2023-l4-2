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

        <c:if test = "${faccionGanadora.equals(faccionApoyada)}">
            <div style="text-align:center">
                <c:out value="ENHORABUENA, HAS GANADO LA PARTIDA!"/>
            </div>
        </c:if>

        <c:if test = "${!faccionGanadora.equals(faccionApoyada)}">
            <div style="text-align:center">
                <c:out value="MALA SUERTE, SIGUE INTENTANDOLO!"/>
            </div>
        </c:if>

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

        <c:if test="${partida.ronda == 3}">
            
            <div style="text-align:left">
                Faccion ganadora
                <div>
                        <tr style = "text-align: center; ";>
                            <td>
                                <div>
                                    <c:out value="${faccionGanadora.getName()}"/> 
                                </div>
                            </td>
                        </tr>
                </div>
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
        
    </div>
   
</petclinic:lo2>