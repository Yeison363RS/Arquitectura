package gui;

import controllers.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGetNumber extends JPanel {


    private JLabel labelNumberToConverter;
    private JTextField textFieldGetNumberToConverter;
    private JButton btnConvert;

    public PanelGetNumber(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder(Texts.TITTLE_PANEL_GET_NUMBER));
        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(1), Texts.TITTLE_PANEL_GET_NUMBER, 0, 0, new java.awt.Font("Arial Narrow", Font.ITALIC, 15))); // NOI18N
        this.init(listener);
    }

    private void init(ActionListener listener) {
        this.labelNumberToConverter = new JLabel(Texts.LABEL_NUMBER_TO_CONVERT);
        this.textFieldGetNumberToConverter = new JTextField();
        this.btnConvert = new JButton(Texts.BTN_CONVERT);
        this.btnConvert.addActionListener(listener);
        this.btnConvert.setActionCommand(Commands.BTN_CONVERT);
        this.textFieldGetNumberToConverter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean exceptions = (key >= 48 && key <= 57) || key == 46 || key == 45;
                if (!exceptions) {
                    evt.consume();
                }

            }
        });

        InputMap map2 = textFieldGetNumberToConverter.getInputMap(JTextField.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "null");
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        labelNumberToConverter.setBounds(10, 20, 120, 20);
        textFieldGetNumberToConverter.setBounds(135, 20, 170, 20);
        btnConvert.setBounds(310, 20, 120, 20);
        this.add(labelNumberToConverter);
        this.add(textFieldGetNumberToConverter);
        this.add(btnConvert);
    }

    public Double getNumberToCovert() {
        try {
            return Double.parseDouble(this.textFieldGetNumberToConverter.getText());
        } catch (NumberFormatException numberToCovert) {
            Output.showErrorMessage(Texts.ERROR_GET_NUMBER);
            return null;
        }

    }
}
