<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:lo2 pageName="partidas">

    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;text-align: center; height: 200;">

        <div>
            Jugando como : <c:out value="${principal.name}"/>
        </div>
        <div style="text-align:left">
            Jugadores de la partida:
            <div>
                <c:forEach items="${jugadores}" var="jugador">
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
        <div style="font-family: 'Dalek Pinpoint'; font-size: 20px; text-align: left; vertical-align: bottom;">
            Tu rol actual es:
            <c:out value="${rolActual}"/>
        </div>
    </div>
   
</petclinic:lo2>