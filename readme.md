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
