<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <body style="background-image: url(/resources/images/idusDeMarzo.jpg); background-repeat: 
    no-repeat; background-attachment: fixed; background-size: cover;">

        <div>
            <spring:url value="/resources/images/errorconsul.png" var="errorImage"/>
            <img src="${errorImage}"/>
        </div>
        <div>
            <h2 style="font-family: 'Dalek Pinpoint', sans-serif;width: 50%; text-align:center;position: relative;color: black;background-color: rgb(236, 182, 96);border-color: rgb(121, 102, 6); border-width: 2mm; ;">Ha ocurrido un error</h2>
        </div>
        <tr>
            <th style="text-align: center;">
                <button style="width: 10%;position: relative; height: 50;background-color: rgb(236, 182, 96); font-size: x-large; border-color: rgb(121, 102, 6); border-width: 2mm;">
                    <a class="btn btn-default" href="/home"> Volver</a>
                </button>
            </th>
        </tr>

    </body>

</petclinic:layout>
