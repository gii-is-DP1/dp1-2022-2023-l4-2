<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>
            Nueva partida
        </h2>
        <form:form modelAttribute="partida"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${partida.id}"/>
            <div class="form-group has-feedback">                
                <tr>
                    <td>Numero de jugadores:</td>
                    <td>
                        <select name = "numJugadores">
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                            <option>8</option>
                        </select>
                    </td>
                </tr>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
                     <button class="btn btn-default" type="submit" href="/partidas/new">Crear partida</button>
                </div>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>