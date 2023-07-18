# PequenhisimaAplicacion
1) Cómo compilar la solución y cómo configurar el API KEY para acceder a openweathermap.org
El repositorio se encuentra disponible sin restricciones para su acceso. para su compilación es necesario previamente tener su ambiente de trabajo preparado, esto incluye:
- Tener instalado GIT en su SO para poder clonar el proyecto. Una vez instalado simplemente debes posicionarte en la dirección que quieras que se encuentre el proyecto, dar clic derecho y abrir la terminal Git Bash, luego escribir el comando git clone seguido de la dirección HTTPS del repositorio.
- El android studio o el IDE de su preferencia.
- Para obtener un API KEY del API openweathermap.org simplemente debes registrarse como miembro, una vez creado tu usuario, por defecto también se creará un API KEY que lo podrás encontrar en las opciones de tu usuario, y si no se genera por defecto el API KEY también tienes la opción de generar uno en ese mismo apartado.
2) Lista de los features que deberían funcionar.
* Pantalla 1:
- Listado de Todas las ciudades soportadas en openweathermap.org en orden alfabetico.
- Al seleccionar una de las ciudades debe redirigirte a la Pantalla 2.
* Pantalla 2:
- Se encuentra los detalles del clima de la ciudad seleccionada en la lista de la pantalla 1: temperatura, sensación térmica, temperatura mínima, temperatura máxima.
- Selector de unidades de medidas de temperatura, este selector cambia las unidades de medidas de los detalles de la ciudad seleccionada en la pantalla 1: temperatura, sensación térmica, temperatura mínima, temperatura máxima.
3) Lista de problemas conocidos.
- El item seleccionado en el searchview (filtro) no es el corrrecto. Esto último tiene que ver con el índice. 
