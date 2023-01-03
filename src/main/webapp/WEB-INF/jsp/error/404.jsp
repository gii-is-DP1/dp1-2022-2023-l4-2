<petclinic:layout pageName="error">

    <body style="background-image: url(/resources/images/idusDeMarzo.jpg); background-repeat: 
    no-repeat; background-attachment: fixed; background-size: cover;">

        <table  style= "width: 100%; text-align:center;position: relative;">

            <tr>
                <th style="text-align: center;">
                    <spring:url value="/resources/images/errorconsul.png" var="errorImage"/>
                    <img src="${errorImage}"/>
                    <h2 style="font-family: 'Dalek Pinpoint', sans-serif;width: 50%; text-align:center;position: relative;color: black;background-color: rgb(236, 182, 96);border-color: rgb(121, 102, 6); border-width: 2mm; ;">Ha ocurrido un error por que no existe el recurso que has solicitado</h2>
                </th>
            </tr>

            <tr>
                <spring:url value="/resources/images/logo_big.png" var="errorImage"/>
                <img src="${errorImage}"/>
            </tr>

            <tr>
                <th style="text-align: center;">
                    <button style="width: 10%;position: relative; height: 50;background-color: rgb(236, 182, 96); font-size: x-large; border-color: rgb(121, 102, 6); border-width: 2mm;">
                        <a class="btn btn-default" href="/home"> Volver</a>
                    </button>
                </th>
            </tr>

        </table>

    </body> 

</petclinic:layout>