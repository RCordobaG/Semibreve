Semibreve

Esta aplicación est;a diseñbada para que, mediante el reforzamiento continuo el usuario pueda aprender a leer las notas musicales en el pentagrama.\
La aplicación se compone de multiples ViewControllers unidos jerarquicamente por un NavigationController para facilidad de navegación.

El principal propósito es el de generar una serie de tarjetas (*flashcards) las cu[ales se componen de una imagen que representa una nota en el pentagrama, y una serie de 4 botones a partir de los cu[ales el usuario puede selecciona rla respuesta correcta.
De estos, uno de ellos contiene la respuesta correcta y los demás se llenan de forma aleatoria con notas preconfiguradas de forma programática. Estos nunca generan respuestas duplicadas, y la posición en la que se encontrará la respuesta correcta igualmente es pseudo-aleatorio.

Para fines de escala, la aplicación está diseñada programáticamente para las notas Do4 hasta Si4 en la escala nominal, con un pentagrama que integra la clave de Sol mayor: https://es.wikipedia.org/wiki/Sol_mayor.
Las imagenes de las notas se integran de forma manual en los recursos de la aplicación, junto con su representación como archivo de constantes, y un diccionario que relaciona el nombre de la nota con la imagen correspondiente.
Esto presenta una línea de base para entrada al mundo de la música, pues corresponden a los valores más utilizados en piezas para principiantes, en instrumentos de una sola nota como el piano.
Las notas pueden practicarse en la escala Americana o Europea, y el usuario es capaz de seleccionar la cantidad de rondas que compondrán su sesión.
Al finalizar la sesión, se actualizan los resultados de forma local para almacenar los datos de la última sesión. Estos pueden consultarse en el menú de inicio y al final de la sesión como una lista, y se almacena de forma local en el contenedor de la aplicación.

Como plan de desarrollo, se espera que la aplicación sea capaz de descargar más paquetes de notas que puedan ser añadidos al repertorio de tarjetas/notas que el usuario pueda seleccionar.
Estos pueden ser nuevas claves en el pentagrama, más tipos de notas (https://es.wikipedia.org/wiki/Nota_(sonido)), y distintas escalas (https://es.wikipedia.org/wiki/Escala_musical)
Para que el hecho de descargar más paquetes de notas/escalas no afecte la dificultad de entrada, debe de existir una forma de seleccionar/delimitar la cantidad de ejercicios o restringirlos a un solo tipo de ejercicio (ej. solo clave de Sol)
Debido al número finito de opciones, se puede realizar el desarrollo de forma local, pero a fin de optimizar los tiempos de descarga se pretende que el contendio adicional pueda ser obtenido posterior a la instalación como contenido adicional a través de una API.
Asimismo, para evitar perder el progreso del usuario, se espera poder subir el archivo de resultados a un servicio, de modo que pueda igualmente ser recuperado a través de una API. Para ello se tiene que identificar a cada usuario de forma única con una cuenta.

El estado actual de la aplicación fue probado para estabilidad (menor cantidad de errores posibles), para que se mostrara de forma adecuada en distintos dispositivos con tamaños de pantalla variables, y que funcione tanto en modo claro como oscuro.
La lógica principal del ejercicio se implementó de forma que opere completamente en el lado del cliente de modo que la carga de internet sea mínima y que el usuario pueda interactuar con la aplicación incluso cuando no esté en un ambiente de buena conexión.
