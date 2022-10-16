package gui;

import controllers.Commands;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelGetNumber extends JPanel {

    private JLabel labelNumberToConverter;
    private JTextField textFieldGetNumberToConverter;
    private JButton btnConvert;

    public PanelGetNumber(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder("Obtencion del numero"));
        this.init(listener);
    }

    private void init(ActionListener listener) {
        this.labelNumberToConverter = new JLabel("Numero a convertir:");
        this.textFieldGetNumberToConverter = new JTextField();
        this.btnConvert = new JButton("Convertir");
        this.btnConvert.addActionListener(listener);
        this.btnConvert.setActionCommand(Commands.BTN_CONVERT);
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        labelNumberToConverter.setBounds(5, 20, 120, 20);
        textFieldGetNumberToConverter.setBounds(135, 20, 170, 20);
        btnConvert.setBounds(310, 20, 120, 20);
        this.add(labelNumberToConverter);
        this.add(textFieldGetNumberToConverter);
        this.add(btnConvert);
    }

    public double getNumberToCovert() {
        try {
            return Double.parseDouble(this.textFieldGetNumberToConverter.getText());
        } catch (NumberFormatException numberToCovert) {
            Output.showErrorMessage("Por favor ingrese el decimal separado por coma :(");
            return -1;
        }

    }
}
