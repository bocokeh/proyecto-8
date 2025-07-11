import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class p {

    private static String sexoSeleccionado;
    private static List<String> preguntasActuales;
    private static int preguntaActual = 0;
    private static List<String> respuestas = new ArrayList<>();
    private static JFrame ventana;
    private static JPanel panel;
    private static ButtonGroup opcionesGrupo;
    private static int puntuacionTotal = 0;  // Variable para almacenar la puntuacion

    private static final List<String> preguntasHombres = Arrays.asList(
            "¿Cuántos días a la semana realizas ejercicio de fuerza (pesas, flexiones, etc.)?",
            "¿Cuántas horas duermes en promedio por noche?",
            "¿Con qué frecuencia consumes alcohol por semana?",
            "¿Cuántas porciones de verduras consumes al día?",
            "¿Realizas chequeos médicos preventivos regularmente?",
            "¿Cuántas horas pasas frente a pantallas durante el día?",
            "¿Practicas algún deporte o actividad física intensa?",
            "¿Consumes suplementos vitamínicos o proteínas?",
            "¿Manejas bien el estrés laboral o personal?",
            "¿Mantienes un peso corporal saludable?"
    );

    private static final List<String> preguntasMujeres = Arrays.asList(
            "¿Realizas ejercicio cardiovascular regularmente?",
            "¿Cuántas horas de sueño reparador tienes por noche?",
            "¿Mantienes una dieta balanceada rica en hierro y calcio?",
            "¿Consumes suficiente agua durante el día (8 vasos o más)?",
            "¿Te realizas chequeos ginecológicos regularmente?",
            "¿Practicas técnicas de relajación o meditación?",
            "¿Incluyes alimentos ricos en ácido fólico en tu dieta?",
            "¿Limitas el consumo de azúcar y alimentos procesados?",
            "¿Mantienes un equilibrio entre trabajo y vida personal?",
            "¿Realizas actividades que fortalezcan tus huesos?"
    );

    // Opciones por pregunta
    private static final List<String[]> opcionesPorPregunta = Arrays.asList(
            new String[]{"Ninguno", "1-2 días", "3-4 días", "5-6 días", "7 días"},
            new String[]{"Menos de 4 horas", "4-5 horas", "6-7 horas", "8 horas", "Más de 8 horas"},
            new String[]{"Nunca", "1 vez por semana", "2-3 veces por semana", "4-5 veces por semana", "Más de 5 veces por semana"},
            new String[]{"Ninguna", "1 porción", "2 porciones", "3 porciones", "Más de 3 porciones"},
            new String[]{"Nunca", "Una vez al año", "Cada 6 meses", "Cada 3 meses", "Mensualmente"},
            new String[]{"Menos de 2 horas", "2-4 horas", "5-7 horas", "8-10 horas", "Más de 10 horas"},
            new String[]{"Nunca", "Una vez a la semana", "2-3 veces a la semana", "4-5 veces a la semana", "Diariamente"},
            new String[]{"Nunca", "De vez en cuando", "Mensualmente", "Semanalmente", "Diariamente"},
            new String[]{"Muy mal", "Mal", "Regular", "Bien", "Excelente"},
            new String[]{"No", "Un poco", "En proceso", "Sí", "Totalmente"}
    );

    private static final int[] puntuaciones = {1, 2, 3, 4, 5};  // Puntuaciones para cada opcion de respuesta

    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 10); // Bordes redondeados en botones
        UIManager.put("Component.arc", 10); // Bordes redondeados generales
        UIManager.put("Button.background", new Color(52, 152, 219)); // Azul plano
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Panel.background", new Color(240, 248, 255)); // Fondo azul claro
        EventQueue.invokeLater(p::iniciarAplicacion);
    }

    private static void iniciarAplicacion() {
        System.out.println("Iniciando la aplicación...");

        ventana = new JFrame("Evaluador de Hábitos Saludables");
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        ventana.setVisible(true);

        mostrarSeleccionSexo();
    }

    private static void mostrarSeleccionSexo() {
        System.out.println("Mostrando selección de sexo...");

        // Panel principal
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Más margen externo
        panel.setBackground(UIManager.getColor("Panel.background")); // Color de FlatLaf

        // Panel contenedor con borde (la cajita esa que rodea todo pues)
        JPanel cajaContenedora = new JPanel();
        cajaContenedora.setLayout(new BoxLayout(cajaContenedora, BoxLayout.Y_AXIS));
        cajaContenedora.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2), // Borde azul
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Márgenes internos
        ));
        cajaContenedora.setBackground(Color.WHITE);
        cajaContenedora.setAlignmentX(Component.CENTER_ALIGNMENT);
        cajaContenedora.setMaximumSize(new Dimension(500, 400)); // Tamaño máximo

        // Titulo con sombrita
        JLabel titulo = new JLabel("Evaluador de Hábitos Saludables");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(52, 152, 219)); // Azul
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0)); // Margen inferior
        cajaContenedora.add(titulo);

        // Subtitulo
        JLabel preguntaSexo = new JLabel("Selecciona tu sexo:");
        preguntaSexo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        preguntaSexo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cajaContenedora.add(preguntaSexo);

        cajaContenedora.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio

        // Radio buttons en horizontal (centrados)
        JPanel panelSexo = new JPanel();
        panelSexo.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 0)); // Más espacio entre botones
        panelSexo.setBackground(Color.WHITE);

        JRadioButton hombre = new JRadioButton("Hombre");
        hombre.setActionCommand("Hombre");
        hombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JRadioButton mujer = new JRadioButton("Mujer");
        mujer.setActionCommand("Mujer");
        mujer.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        opcionesGrupo = new ButtonGroup();
        opcionesGrupo.add(hombre);
        opcionesGrupo.add(mujer);

        panelSexo.add(hombre);
        panelSexo.add(mujer);
        cajaContenedora.add(panelSexo);

        cajaContenedora.add(Box.createRigidArea(new Dimension(0, 25))); // Espacio

        // Para ele stilazo del boton que parece 3D de 2007
        JButton continuarButton = new JButton("Continuar");
        continuarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        continuarButton.setBackground(new Color(52, 152, 219)); // Azul
        continuarButton.setForeground(Color.WHITE);
        continuarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), // Efecto 3D
                BorderFactory.createEmptyBorder(10, 25, 10, 25) // Padding
        ));
        continuarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuarButton.setFocusPainted(false); // Quita el borde de enfoque
        continuarButton.addActionListener(e -> {
            // Validacion if boton puchado es igual a null tons nuh huh
            if (opcionesGrupo.getSelection() == null) {
                JOptionPane.showMessageDialog(ventana,
                        "Debes seleccionar tu sexo para continuar",
                        "Seleccion obligatoria",
                        JOptionPane.WARNING_MESSAGE);
                return; // Detener la ejecucion si no hay seleccion
            }

            sexoSeleccionado = opcionesGrupo.getSelection().getActionCommand();
            System.out.println("Sexo seleccionado: " + sexoSeleccionado);
            if (sexoSeleccionado.equals("Hombre")) {
                preguntasActuales = preguntasHombres;
            } else {
                preguntasActuales = preguntasMujeres;
            }
            mostrarCuestionario();
        });
        cajaContenedora.add(continuarButton);

        // Añadir la caja al panel principal (centrada)
        panel.add(Box.createVerticalGlue()); // Centrado vertical
        panel.add(cajaContenedora);
        panel.add(Box.createVerticalGlue());

        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(panel, BorderLayout.CENTER);
        ventana.revalidate();
        ventana.repaint();
    }

    private static void mostrarCuestionario() {
        System.out.println("Mostrando cuestionario...");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(UIManager.getColor("Panel.background"));

        // Panel contenedor con borde
        JPanel cajaContenedora = new JPanel();
        cajaContenedora.setLayout(new BoxLayout(cajaContenedora, BoxLayout.Y_AXIS));
        cajaContenedora.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        cajaContenedora.setBackground(Color.WHITE);

        // Tamaño de ña cajita donde van las preguntas, titulo y eso
        //cajaContenedora.setMaximumSize(new Dimension(600, 500)); // ancho estatico
        cajaContenedora.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500)); // Ancho flexible
        cajaContenedora.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Titulo de la pregunta
        JLabel preguntaLabel = new JLabel(preguntasActuales.get(preguntaActual));
        preguntaLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        preguntaLabel.setForeground(new Color(52, 152, 219));
        preguntaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        preguntaLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        cajaContenedora.add(preguntaLabel);

        // Numero de la pregunta
        JLabel progresoLabel = new JLabel("Pregunta " + (preguntaActual + 1) + "/" + preguntasActuales.size());
        progresoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        progresoLabel.setForeground(Color.GRAY);
        progresoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cajaContenedora.add(progresoLabel);

        // Panel para opciones (ahora con GridLayout para mejor distribucionsota)
        JPanel opcionesPanel = new JPanel(new GridLayout(0, 1, 0, 10)); // 1 columna, 10px de espacio vertical
        opcionesPanel.setBackground(Color.WHITE);
        opcionesPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        opcionesGrupo = new ButtonGroup();
        String[] opciones = opcionesPorPregunta.get(preguntaActual);

        for (int i = 0; i < opciones.length; i++) {
            JRadioButton radio = new JRadioButton(opciones[i]);
            radio.setActionCommand(String.valueOf(i));
            radio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            radio.setBackground(Color.WHITE);
            opcionesGrupo.add(radio);
            opcionesPanel.add(radio);
        }

        cajaContenedora.add(opcionesPanel);

        // Boton con validacion
        JButton siguienteButton = new JButton(preguntaActual < preguntasActuales.size() - 1 ? "Siguiente" : "Finalizar");
        siguienteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        siguienteButton.setBackground(new Color(52, 152, 219));
        siguienteButton.setForeground(Color.WHITE);
        siguienteButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        siguienteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        siguienteButton.addActionListener(e -> {
            if (opcionesGrupo.getSelection() == null) { // Validacion de seleccion
                JOptionPane.showMessageDialog(ventana,
                        "Debes seleccionar una respuesta para continuar",
                        "Respuesta obligatoria",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int respuesta = Integer.parseInt(opcionesGrupo.getSelection().getActionCommand());
            respuestas.add(opciones[respuesta]);
            puntuacionTotal += puntuaciones[respuesta];

            preguntaActual++;
            if (preguntaActual < preguntasActuales.size()) {
                mostrarCuestionario();
            } else {
                mostrarMensajeFinal();
            }
        });
        cajaContenedora.add(siguienteButton);

        // Centrado en la ventana
        panel.add(Box.createVerticalGlue());
        panel.add(cajaContenedora);
        panel.add(Box.createVerticalGlue());

        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(panel, BorderLayout.CENTER);
        ventana.revalidate();
        ventana.repaint();
    }

    private static void mostrarMensajeFinal() {
        System.out.println("Mostrando mensaje final...");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(240, 248, 255)); // Fondo azul claro

        // Panel contenedor tipo "tarjeta de resultados" - gracias busqueda profunda
        JPanel tarjetaResultados = new JPanel();
        tarjetaResultados.setLayout(new BoxLayout(tarjetaResultados, BoxLayout.Y_AXIS));
        tarjetaResultados.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        tarjetaResultados.setBackground(Color.WHITE);
        tarjetaResultados.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjetaResultados.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        // Titulo principal
        JLabel titulo = new JLabel("Resultados de tu evaluacion");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(52, 152, 219)); // Azul
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 15, 0));
        tarjetaResultados.add(titulo);

        // Perfil de salud (con color dinámico)
        String perfil = obtenerPerfilPuntuacion(puntuacionTotal);
        JLabel labelPerfil = new JLabel("Perfil: " + perfil);
        labelPerfil.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Asignar color segun el resultado del perfil
        Color colorPerfil = switch (perfil) {
            case "Saludable" -> new Color(46, 204, 113); // Verde
            case "Regular" -> new Color(241, 196, 15);   // Amarillo
            case "En riesgo" -> new Color(231, 76, 60);  // Rojo
            default -> Color.BLACK;
        };
        labelPerfil.setForeground(colorPerfil);
        labelPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjetaResultados.add(labelPerfil);

        tarjetaResultados.add(Box.createRigidArea(new Dimension(0, 20)));

        // Recomendaciones en un área de texto con scroll
        JTextArea recomendacionesArea = new JTextArea(obtenerRecomendaciones(perfil));
        recomendacionesArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        recomendacionesArea.setEditable(false);
        recomendacionesArea.setLineWrap(true);
        recomendacionesArea.setWrapStyleWord(true);
        recomendacionesArea.setBackground(new Color(250, 250, 250));
        recomendacionesArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(recomendacionesArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(500, 150));
        tarjetaResultados.add(scrollPane);

        tarjetaResultados.add(Box.createRigidArea(new Dimension(0, 25)));

        // Boton de reinicio
        JButton reiniciarButton = new JButton("Realizar otro test");
        reiniciarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        reiniciarButton.setBackground(new Color(52, 152, 219)); // Azul
        reiniciarButton.setForeground(Color.WHITE);
        reiniciarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        reiniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reiniciarButton.addActionListener(e -> reiniciarTest());
        tarjetaResultados.add(reiniciarButton);

        // Centrado en la ventana
        panel.add(Box.createVerticalGlue());
        panel.add(tarjetaResultados);
        panel.add(Box.createVerticalGlue());

        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(panel, BorderLayout.CENTER);
        ventana.revalidate();
        ventana.repaint();
    }

    private static String obtenerPerfilPuntuacion(int puntuacion) {
        if (puntuacion >= 40) {
            return "Saludable";
        } else if (puntuacion >= 25) {
            return "Regular";
        } else {
            return "En riesgo";
        }
    }

    private static String obtenerRecomendaciones(String perfil) {
        switch (perfil) {
            case "Saludable":
                return "¡Excelente trabajo! Mantén tus hábitos saludables.";
            case "Regular":
                return "Estás en una buena dirección, pero podrías mejorar en algunos hábitos. Considera dormir más horas y reducir el consumo de alcohol.";
            case "En riesgo":
                return "Es importante mejorar tus hábitos. Te sugerimos hacer más ejercicio, dormir más y hacer chequeos médicos regularmente.";
            default:
                return "Recomendaciones no disponibles.";
        }
    }

    private static void reiniciarTest() {
        System.out.println("Reiniciando el test...");

        preguntaActual = 0;
        respuestas.clear();
        puntuacionTotal = 0;  // Reiniciar puntuacion
        sexoSeleccionado = null;
        preguntasActuales = null;

        mostrarSeleccionSexo();
    }
}