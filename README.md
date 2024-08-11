# Semibreve

Esta aplicación está diseñbada para que, mediante el reforzamiento continuo el usuario pueda aprender a leer las notas musicales en el pentagrama.  

El principal propósito es el de generar una serie de tarjetas (*flashcards) las cu[ales se componen de una imagen que representa una nota en el pentagrama, y una serie de 4 botones a partir de los cu[ales el usuario puede selecciona rla respuesta correcta. De estos, uno de ellos contiene la respuesta correcta y los demás se llenan de forma aleatoria con notas preconfiguradas de forma programática. Estos nunca generan respuestas duplicadas, y la posición en la que se encontrará la respuesta correcta igualmente es pseudo-aleatorio.

Para fines de escala, la aplicación está diseñada programáticamente para las notas Do4 hasta Si4 en la escala nominal, con un pentagrama que integra la clave de Sol mayor:  [https://es.wikipedia.org/wiki/Sol_mayor](https://es.wikipedia.org/wiki/Sol_mayor). Las imagenes de las notas se integran de forma manual en los recursos de la aplicación, junto con su representación como archivo de constantes, y un diccionario que relaciona el nombre de la nota con la imagen correspondiente. Esto presenta una línea de base para entrada al mundo de la música, pues corresponden a los valores más utilizados en piezas para principiantes, en instrumentos de una sola nota como el piano. Las notas pueden practicarse en la escala Americana o Europea, y el usuario es capaz de seleccionar la cantidad de rondas que compondrán su sesión. Al finalizar la sesión, se actualizan los resultados de forma local para almacenar los datos de la última sesión. Estos pueden consultarse en el menú de inicio y al final de la sesión como una lista, y se almacena de forma local en el contenedor de la aplicación.

Como plan de desarrollo, se espera que la aplicación sea capaz de descargar más paquetes de notas que puedan ser añadidos al repertorio de tarjetas/notas que el usuario pueda seleccionar. Estos pueden ser nuevas claves en el pentagrama, más tipos de notas ([https://es.wikipedia.org/wiki/Nota_(sonido)](https://es.wikipedia.org/wiki/Nota_(sonido))), y distintas escalas ([https://es.wikipedia.org/wiki/Escala_musical](https://es.wikipedia.org/wiki/Escala_musical)) Para que el hecho de descargar más paquetes de notas/escalas no afecte la dificultad de entrada, debe de existir una forma de seleccionar/delimitar la cantidad de ejercicios o restringirlos a un solo tipo de ejercicio (ej. solo clave de Sol) Debido al número finito de opciones, se puede realizar el desarrollo de forma local, pero a fin de optimizar los tiempos de descarga se pretende que el contendio adicional pueda ser obtenido posterior a la instalación como contenido adicional a través de una API. Asimismo, para evitar perder el progreso del usuario, se espera poder subir el archivo de resultados a un servicio, de modo que pueda igualmente ser recuperado a través de una API. Para ello se tiene que identificar a cada usuario de forma única con una cuenta.

El estado actual de la aplicación fue probado para estabilidad (menor cantidad de errores posibles), para que se mostrara de forma adecuada en distintos dispositivos con tamaños de pantalla variables, y que funcione tanto en modo claro como oscuro. La lógica principal del ejercicio se implementó de forma que opere completamente en el lado del cliente de modo que la carga de internet sea mínima y que el usuario pueda interactuar con la aplicación incluso cuando no esté en un ambiente de buena conectividad a Internet.

## Notas técnicas

La App no requiere de dependencias adicionales en este momento, pero debido a la funcionalidad que se planea implementar, se utilizarán los frameworks de conexión a internet.
Para Android se utilizó la librería GSON

# Funcionalidad
El punto de entrada en ambas aplicaciones es el menú principal; en este se permite que el usuario modifique los parámetros operativos generales de la aplicación acorde a sus preferencias: 
-Auto round timer (Tiempo entre rondas) cambia el tiempo que tarda en actualizarse la interfaz cuando el usuario selecciona una opción en el controlador principal 
-Modo Manual permite al usuario tener control total sobre el flujo del ejercicio al habilitar un botón en la parte superior derecha del controlador para avanzar el flujo del ejercicio
Estas preferencias se almacenan de forma local en UserDefaults para mantener persistencia.

El flujo principal inicia cuando el usuario selecciona nuevo ejercicio. La siguiente pantalla define el las opciones del ejercicio, cuantas rondas como máximo se van a jugar, y la escala que se ocupa (Europea o Americana) https://es.wikipedia.org/wiki/Sistema_de_notaci%C3%B3n_musical_anglosaj%C3%B3n

El controlador de Flashcards muestra los botones de opciones con la imagen de respuesta, además de un label que le dice al usuario si la opción fue correcta. Si no se usa el modo manual, la vista se actualiza cada intervalo de tiempo confiigurado en ajustes hasta llegar al número de rondas.

Para mantener el estado del ejercicio, como el número de rondas y la ronda actual, el puntaje y el radio de respuestas correctas a incorrectas se utiliza un objeto Singleton. Al finalizr el ejercicio funciona como la fuente de verdad para computar el resultado y almacenarlo en un objeto JSON en la carpeta de documentos del contenedor. Este objeto se inicializa con los valores del usuario, de estar disponibles, para determinar la operación y ajustes de la aplicación en tiempo de ejecución.
El usuario puede interrumpir el ejercicio en cualquier momento y se computarán sus resultados parciales


# IOS

La versión de IOS fue diseñada en XCode 15.4 y tiene como target el SDK de IOS 17.5

## Compatibilidad

El tipo de deployment son todos aquellos dispositivos con posibilidad de ejecutar IOS 17 https://support.apple.com/es-mx/guide/iphone/iphe3fa5df43/ios. 
Los parámetros de diseño son capaces de acomodar los distintos tamaños de pantalla manejados por Apple, y fue probabdo en equipo físico en el modelo SE 2 de 4.7 pulgadas y en el iPhone 13 de 6.1 pulgadas. El tamaño de los elementos se ajusta de forma dinámica.
Solamente se contempla el diseño para la orientación vertical, por lo que no se permite modificar la orientación de la pantalla. Esto con la finalidad de que el usuario tenga una mejor experiencia debido al diseño de los elementos.

## Notas técnicas

En IOS se utilizó la funcionalidad de View Controllers ordenados jerárquicamente a través de un Navigation controller. EL controlador de entrada es el menú principal; a partir de este se utilizan segues para dirigir al usuario a las distintas partes de la aplicación. Ya que el estado de la aplicación está determinado por el Singleton, no es necesario enviar datos entre los segues, lo que reduce la complejidad y la posibilidad de errores. El CRUD usa una lista de objetos Codable para convertir objetos a su representación en JSON y de regreso, y por medio de esta lista se realizan las operaciones de modificación de la base de datos.


# Android

Se usó la versión Koala 2024.1.1 y el target de desarrollo es el SDK 26 hasta 34

## Compatibilidad

El tipo de deployment son todos aquellos dispositivos en el SDK26 o superior. Algunas de las funciones implementadas no son retrocompatibles prebio a esta versión.
Los parámetros de diseño son capaces de acomodar los distintos tamaños de pantalla por medio de ConstraintLayouts, y fue probabdo en equipo físico en los dispositivos Huawei P20 Lite de 4.7 pulgadas, Android 9, y en el Moto Edge 30 de 6.7 pulgadas en Android 13.
Solamente se contempla el diseño para la orientación vertical, por lo que no se permite modificar la orientación de la pantalla. Esto con la finalidad de que el usuario tenga una mejor experiencia debido al diseño de los elementos.

## Notas técnicas

La versión de Android utiliza la misma lógica que la de IOS para determinar la respuesta correcta y asignarla a un botón en tiempo de ejecución. 
Sin embargo, para mejorar la experiencia en Android y reducir el tiempo de implementación se implementó la lógica principal por medio de Fragments. En lugar de recrear la vista en tiempo de ejecución, cada fragmento se instancia para reemplazar al anterior, lo que igual proporciona un nuevo estado y se reduce la posibilidad de un error por manejo de variables. Cada fragmento modifica el Singleton y solamente requiere contexto de sí mismo.

