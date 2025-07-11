# Evaluador de habitos saludables

Este proyecto es una aplicación de escritorio desarrollada en Java Swing con FlatLaf para un diseño moderno, que permite evaluar los hábitos de salud de los usuarios mediante un cuestionario personalizado. Con base en las respuestas, el sistema calcula una puntuación y ofrece recomendaciones para mejorar la salud.

Descripción General:
Este evaluador consta de 10 preguntas adaptadas según el género (hombre o mujer). Cada respuesta suma puntos que se acumulan para generar un perfil de salud y recomendaciones personalizadas.

Flujo de la aplicación:

Selección de género: El usuario elige entre Hombre o Mujer.

Cuestionario: Se presentan 10 preguntas específicas para el género seleccionado. Cada respuesta aporta entre 1 y 5 puntos.

Resultados: Se calcula el perfil de salud según la puntuación total:

Saludable: 40–50 puntos

Regular: 25–39 puntos

En riesgo: menos de 25 puntos

Recomendaciones: Consejos motivacionales y prácticos según el perfil.

Reinicio: Opción para volver a realizar el test.

Características Principales:

Interfaz gráfica moderna con FlatLightLaf.

Cuestionarios personalizados para hombres y mujeres.

Sistema de puntuación de 10 a 50 puntos.

Recomendaciones dinámicas basadas en el perfil.

Validación de entrada y progreso visible.

Tecnologías Utilizadas:

Java 11 o superior

Java Swing

FlatLaf (FlatLightLaf)

BorderLayout y BoxLayout para disposición de componentes

ButtonGroup para opciones exclusivas

Estructura del Proyecto:
src/main/java/p.java    (Clase principal con toda la lógica)
README.txt              (Este archivo)

Instalación y Ejecución:

Clonar el repositorio:
git clone https://github.com/bocokeh/proyecto-8.git
cd evaluador-habitos-saludables

Compilar:
javac -cp path/to/flatlaf.jar src/main/java/p.java

Ejecutar:
java -cp .:path/to/flatlaf.jar p

Uso:

Selecciona tu sexo y haz clic en Continuar.

Responde las 10 preguntas.

Al finalizar, se mostrará tu perfil y recomendaciones.

Para reiniciar, selecciona Realizar otro test.

Detalles de Implementación:

Variables globales: sexoSeleccionado, preguntasActuales, preguntaActual, respuestas, puntuacionTotal.

Métodos clave: main, iniciarAplicacion, mostrarSeleccionSexo, mostrarCuestionario, mostrarMensajeFinal, reiniciarTest, obtenerPerfilPuntuacion, obtenerRecomendaciones.

Contribuciones:
Abre un issue o envía un pull request.

Licencia: bocokeh
