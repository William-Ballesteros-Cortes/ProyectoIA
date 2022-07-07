import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Map;

import static java.awt.Font.PLAIN;

public class menu extends JFrame {
    JPanel panel;

    public menu(int n) throws FileNotFoundException {

        initPanel();
        initPantalla();
        initBotones();
    }

    void initPanel() {

        panel = new JPanel();
        add(panel); // Añado el panel al JFrame
        panel.setPreferredSize(new Dimension(600, 400)); // Dimensiones del panel

    }

    private void initPantalla() {

        setTitle("Main");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void initBotones() {
        JButton botonAmplitud = new JButton();
        botonAmplitud.setBounds(200, 100, 100, 40);
        panel.add(botonAmplitud);
    }
}
