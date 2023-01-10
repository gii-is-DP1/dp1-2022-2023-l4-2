<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <body style="background-image: url(/resources/images/fondo.jpg); background-repeat: 
    no-repeat; background-attachment: fixed; background-size: cover;">

        <div style="text-align: center;">
            <div>
                <spring:url value="/resources/images/errorconsul2.png" var="errorImage"/>
                <img width="30%" height="50%" src="${errorImage}"/>
            </div>
            
            <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 1%;  width:100%;">
                <table>
                    <tr>
                        <td>
                            <div style="text-align:center; margin-top: 5%; opacity: 0.9;">
                                <h2 style="font-size: 30px; font-family: 'Dalek Pinpoint', sans-serif; background-color: brown; padding: 1%;">ERROR 403: El servidor ha denegado tu solicitud</h2>
                            </div>
                            <div style="text-align:center; margin-top: 5%;">
                                <a class="btn btn-default" style="font-size: 30px; font-family: 'Dalek Pinpoint', sans-serif; background-color: brown;"  href="/home">INICIO</a>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </body>

</petclinic:layout>
