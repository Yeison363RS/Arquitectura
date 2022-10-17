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
    private JRadioButton checkPrecisionSimple;
    private JRadioButton checkPrecisionDouble;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public PanelGetNumber(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2), Texts.TITTLE_PANEL_GET_NUMBER, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Arial Narrow", 2, 15))); // NOI18N
        this.init(listener);
    }

    private void init(ActionListener listener) {
        this.setBackground(MyColors.generateRandomColor(Constants.COLOR_BASE));
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
        this.checkPrecisionSimple = new JRadioButton("Simple", true);
        this.checkPrecisionDouble = new JRadioButton("Doble");
        this.buttonGroup.add(checkPrecisionSimple);
        this.buttonGroup.add(checkPrecisionDouble);
        this.checkPrecisionSimple.addActionListener(listener);
        checkPrecisionSimple.setActionCommand(Commands.PRECISION_SIMPLE);
        checkPrecisionDouble.setActionCommand(Commands.PRECISION_DOUBLE);
        this.checkPrecisionDouble.addActionListener(listener);
        this.btnConvert.setBackground(MyColors.generateRandomColor(Constants.COLOR_BASE));
        Color color = MyColors.generateRandomColor(Constants.COLOR_BASE);
        this.checkPrecisionDouble.setBackground(color);
        this.checkPrecisionSimple.setBackground(color);
        this.fill();
        this.textFieldGetNumberToConverter.setBorder(Constants.LINE_BORDER);
        this.btnConvert.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
    }

    private void fill() {
        this.setLayout(null);
        labelNumberToConverter.setBounds(10, 20, 120, 20);
        textFieldGetNumberToConverter.setBounds(135, 20, 170, 25);
        btnConvert.setBounds(310, 20, 120, 25);
        this.checkPrecisionSimple.setBounds(440, 23, 70, 20);
        this.checkPrecisionDouble.setBounds(510, 23, 70, 20);
        this.add(labelNumberToConverter);
        this.add(textFieldGetNumberToConverter);
        this.add(btnConvert);
        add(checkPrecisionSimple);
        add(checkPrecisionDouble);
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
