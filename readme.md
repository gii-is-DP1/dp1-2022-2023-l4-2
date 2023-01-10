# Idus Martii 

## Proyecto Grupo 2 L4-2
Nuestro proyecto se basa en implementar el juego de mesa IDUS MARTII con el objetivo de dar facilidad para poder disfrutar del juego de forma online con varios
usuarios. En este juego los jugadores formarán parte del Senado durante una conspiración secreta para acabar con la vida del César. En esta conspiración se encuentran
enfrentados varios bandos, por un lado, tenemos a los que defienden al César y, por otro lado, los que quieren matarlo. También, tendremos un tercer bando, que es el
bando neutral de los mercaderes. Durante el desarrollo de la partida deberás escoger tu bando, según tu objetivo, e intentar descubrir quienes piensan igual que tú
para poder forzar la balanza hacia un lado u otro.

La partida durará entre 15 o 20 minutos, aproximadamente y el número de jugadores variará entre 5 a 8. Estos jugadores van a tener distintos roles mientras se 
desarrolla. Los roles serán el de Edil, Pretor, Cónsul y, también, tendremos jugadores sin rol.

Comenzaremos con la preparación del juego y, en primer lugar, se preparan tantas parejas de cartas de facción “Leal” y “Traidor” como jugadores haya menos uno, y a
estas cartas, se le añaden las dos cartas de la facción “Mercader”. Barajamos estas cartas y repartidor dos para cada jugador y estas serán las dos opciones de su
bando para toda la partida. Prepararemos también las 6 cartas de voto, las 4 cartas de rol y la carta de “Sufragio” con los marcadores de cada facción a 0.

A continuación, asignaremos los roles y el primero debe ser el rol de Cónsul que se debe asignar de forma aleatoria y, a continuación, el Pretor será el jugador de su
izquierda y los dos Cónsul que serán los siguientes de la izquierda del Pretor.
La partida la desarrollaremos en dos rondas, estas rondas tendrán tantos turnos como jugadores haya en la propia partida. Cada ronda terminará cuando el jugador que 
haya comenzado con el Cónsul le vuelva a tocar ser Cónsul y, por tanto, será cuando el jugador inicial reciba por 3ª vez esta carta.

Durante la primera ronda, los turnos se componen de los siguientes pasos. En primer lugar, procedemos a la votación en la que cada Edil cogerá una carta de voto verde 
y una roja y se decantará secretamente a favor de una u otra. La carta verde votará a la facción Leal y la roja a la facción de los traidores. A continuación, el 
Pretor realizará el veto y escogerá una de las cartas que hayan votado los Ediles y forzará a intercambiar dicha carta de voto con la que le queda en la mano a este 
Edil. Finalmente, el Cónsul recogerá estos votos, los mostrará para proceder a contabilizarlos en la carta de Sufragio. Dicho Cónsul deberá descartar una de las 2 
cartas de facción que tiene, definiendo así su bando para el final de la partida (este paso se ignora en el primer turno con el primer cónsul) y cada jugador entregará 
su carta de rol al jugador de la izquierda.

Continuaremos con la segunda ronda cuando el cónsul vuelva a llegar al jugador inicial y realizaremos los siguientes pasos durante los turnos de la segunda ronda. En 
primer lugar, el Cónsul asignará al resto las cartas de rol, sin repetir el rol de un jugador dos turnos seguidos (a excepción de partidas de 5 jugadores donde se 
podrá repetir un Edil). Ahora, en esta ronda, los Ediles añaden una posibilidad a sus votos, añadirán la carta de voto amarilla que es la que vota a los mercaderes 
(esta carta no contabilizará como ningún voto a la hora de avanzar la facción en el sufragio). El Pretor procede a vetar igual que en la ronda anterior, con la 
excepción de que si la carta de voto que escoge es amarilla la deberá mostrar a los demás, obligando al Edil a sustituir dicha carta. Finalmente, el Cónsul recogerá 
los votos y actualizará la carta de Sufragio como en la ronda anterior. Solamente en el primer turno de esta ronda, el Cónsul debe descartar una de las 2 cartas de 
facción y se le asignará Cónsul al jugador de su izquierda.

La partida va a terminar inmediatamente y los jugadores van a mostrar sus cartas de facción cuando ocurra una de las dos siguientes condiciones:

-Idus de Marzo: El jugador inicial recibe la carta de rol de Cónsul por tercera vez (es decir, se han jugado las dos rondas completas). Si esto ocurre, 
la facción que tenga 2 o más votos que la facción rival ganará la partida, ya que consigue frustrar los planes de sus oponentes. A igualdad de votos o diferencia de 1 
voto o no hubiese jugador en la facción rival, ganará la facción de los Mercaderes, ya que han conseguido mantener el “Status Quo”.

-Conspiración Fallida: Un marcador de facción ha sobrepasado el límite de jugadores marcado en la carta de Sufragio. Si esto ocurre, la facción que 
sobrepasa es descubierta y sus integrantes son asesinados, ganando así la facción rival. En caso de que no hubiese ningún jugador de la facción rival, ganarían los 
Mercaderes.

Tras finalizar la partida, definiremos los jugadores que han vencido y se guardarán los datos con la facción que has ganado y las estadísticas de la partida.

<img width="1042" alt="petclinic-screenshot" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Vincenzo_Camuccini_-_La_morte_di_Cesare.jpg/1200px-Vincenzo_Camuccini_-_La_morte_di_Cesare.jpg">
## Understanding the Spring Petclinic application with a few diagrams
<a href="https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application">See the presentation here</a>

## Running petclinic locally
Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/gii-is-DP1/spring-petclinic.git
cd spring-petclinic
./mvnw package
java -jar target/*.jar
```

You can then access petclinic here: http://localhost:8080/

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

## In case you find a bug/suggested improvement for Spring Petclinic
Our issue tracker is available here: https://github.com/gii-is-DP1/spring-petclinic/issues


## Database configuration

In its default configuration, Petclinic uses an in-memory database (H2) which
gets populated at startup with data. 

## Working with Petclinic in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line
```
git clone https://github.com/gii-is-DP1/spring-petclinic.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA

In the main menu, choose `File -> Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources`
or right click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.

A run configuration named `PetClinicApplication` should have been created for you if you're using a recent Ultimate
version. Otherwise, run the application by right clicking on the `PetClinicApplication` main class and choosing
`Run 'PetClinicApplication'`.

4) Navigate to Petclinic

Visit [http://localhost:8080](http://localhost:8080) in your browser.


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [PetClinicApplication](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java) |
|Properties Files | [application.properties](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

## Interesting Spring Petclinic branches and forks

The Spring Petclinic master branch in the main [spring-projects](https://github.com/spring-projects/spring-petclinic)
GitHub org is the "canonical" implementation, currently based on Spring Boot and Thymeleaf. There are
[quite a few forks](https://spring-petclinic.github.io/docs/forks.html) in a special GitHub org
[spring-petclinic](https://github.com/spring-petclinic). If you have a special interest in a different technology stack
that could be used to implement the Pet Clinic then please join the community there.

# Contributing

The [issue tracker](https://github.com/gii-is-DP1/spring-petclinic/issues) is the preferred channel for bug reports, features requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. If you have not previously done so, please fill out and submit the [Contributor License Agreement](https://cla.pivotal.io/sign/spring).

# License

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).

[spring-petclinic]: https://github.com/spring-projects/spring-petclinic
[spring-framework-petclinic]: https://github.com/spring-petclinic/spring-framework-petclinic
[spring-petclinic-angularjs]: https://github.com/spring-petclinic/spring-petclinic-angularjs 
[javaconfig branch]: https://github.com/spring-petclinic/spring-framework-petclinic/tree/javaconfig
[spring-petclinic-angular]: https://github.com/spring-petclinic/spring-petclinic-angular
[spring-petclinic-microservices]: https://github.com/spring-petclinic/spring-petclinic-microservices
[spring-petclinic-reactjs]: https://github.com/spring-petclinic/spring-petclinic-reactjs
[spring-petclinic-graphql]: https://github.com/spring-petclinic/spring-petclinic-graphql
[spring-petclinic-kotlin]: https://github.com/spring-petclinic/spring-petclinic-kotlin
[spring-petclinic-rest]: https://github.com/spring-petclinic/spring-petclinic-rest
