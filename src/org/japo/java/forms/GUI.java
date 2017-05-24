/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import org.japo.java.events.AEM;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Campos
    private int hor;
    private int min;

    // Componentes
    private JLabel lblDisplay;
    private JRadioButton rbtHor;
    private JRadioButton rbtMin;
    private JButton btnMas;
    private JButton btnMen;

    // Constructor
    public GUI() {
        // Inicializar GUI - PREVIA
        beforeInit();

        // Construcción - GUI
        initComponents();

        // Inicializar GUI - POSTERIOR
        afterInit();
    }

    // Inicializar GUI - PREVIA
    private void beforeInit() {

    }

    // Inicializar GUI - POSTERIOR
    private void afterInit() {

    }

    // Construcción - GUI
    private void initComponents() {
        // Fuentes
        Font fntLBL = new Font("Courier", Font.PLAIN, 100);
        Font fntCTR = new Font("Consolas", Font.PLAIN, 26);

        // Colores
        Color c = new Color(184, 244, 244);

        // Bordes
        BevelBorder brdLBL = new BevelBorder(BevelBorder.LOWERED);
        EmptyBorder brdPNL = new EmptyBorder(10, 10, 10, 10);

        // Gestor Eventos Acción
        AEM aem = new AEM(this);

        // Display
        lblDisplay = new JLabel("00:00");
        lblDisplay.setFont(fntLBL);
        lblDisplay.setOpaque(true);
        lblDisplay.setBackground(c);
        lblDisplay.setBorder(brdLBL);
        lblDisplay.setHorizontalAlignment(JLabel.CENTER);

        // Selector - Horas
        rbtHor = new JRadioButton("Horas");
        rbtHor.setFont(fntCTR);
        rbtHor.setSelected(true);

        // Selector - Minutos
        rbtMin = new JRadioButton("Minutos");
        rbtMin.setFont(fntCTR);
        rbtMin.setSelected(true);

        // Coordinador Botones
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtHor);
        bg.add(rbtMin);

        // Botón - MÁS
        btnMas = new JButton("+");
        btnMas.setFont(fntCTR);
        btnMas.addActionListener(aem);

        // Botón - MENOS
        btnMen = new JButton("-");
        btnMen.setFont(fntCTR);
        btnMen.addActionListener(aem);

        // Panel Selector
        JPanel pnlSelector = new JPanel();
        pnlSelector.setBorder(brdPNL);
        pnlSelector.setLayout(new GridLayout(2, 1));
        pnlSelector.add(rbtHor);
        pnlSelector.add(rbtMin);

        // Panel Ajuste
        JPanel pnlAjuste = new JPanel();
        pnlAjuste.setBorder(brdPNL);
        pnlAjuste.setLayout(new GridLayout(1, 2));
        pnlAjuste.add(btnMas);
        pnlAjuste.add(btnMen);

        // Panel de Control
        JPanel pnlControl = new JPanel();
        pnlControl.setBorder(brdPNL);
        pnlControl.setLayout(new GridLayout(1, 2));
        pnlControl.add(pnlSelector);
        pnlControl.add(pnlAjuste);

        // Panel Principal
        JPanel pnlPpal = new JPanel();
        pnlPpal.setBorder(brdPNL);
        pnlPpal.setLayout(new GridLayout(2, 1));
        pnlPpal.add(lblDisplay);
        pnlPpal.add(pnlControl);

        // Icono Ventana - Recurso
        URL urlICN = getClass().getResource("/img/favicon.png");
        Image imgICN = new ImageIcon(urlICN).getImage();

        // Ventana principal
        setTitle("Ajuste Hora");
        setContentPane(pnlPpal);
        setResizable(false);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setIconImage(imgICN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Respuesta - Evento Acción
    public void procesarAccion(ActionEvent e) {
        if (e.getSource().equals(btnMas)) { // ---> MAS
            if (rbtHor.isSelected()) {      // Horas
                if (hor < 23) {
                    hor++;
                } else {
                    hor = 0;
                }
            } else {                        // Minutos
                if (min < 59) {
                    min++;
                } else {
                    min = 0;
                }
            }
        } else {                            // ---> MENOS
            if (rbtHor.isSelected()) {      // Horas
                if (hor > 0) {
                    hor--;
                } else {
                    hor = 23;
                }
            } else {                        // Minutos
                if (min > 0) {
                    min--;
                } else {
                    min = 59;
                }
            }
        }

        // Mostrar Display
        lblDisplay.setText(String.format("%02d:%02d", hor, min));
    }
}
