package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelResults extends JPanel {

    private JLabel labelSign;
    private JLabel labelExponent;
    private JLabel labelMantissa;
    private TextField textFieldSign;
    private TextField textFieldExponent;
    private TextField textFieldMantissa;
    private JLabel labelDecimalEquivalentValueAndExponent;
    private JTextField bleed;
    private JLabel labelPrecision;
    private JTextField textFieldBleedResult;
    private JLabel labelEquivalentDecimalValue;
    private JTextField textFieldEquivalentDecimalValue;
    private JLabel labelHexValue;
    private JTextField textFieldHaxValue;
    private JLabel labelDecimalValue;
    private JTextField textFieldDecimalValue;

    public PanelResults(String tittle, ActionListener listener, String textPrecision) {
        setBorder(BorderFactory.createTitledBorder(tittle));
        this.init(textPrecision);

    }

    private void init(String textPrecision) {
        this.labelSign = new JLabel("Signo");
        this.textFieldSign = new TextField();
        this.labelExponent = new JLabel("Exponente");
        this.labelMantissa = new JLabel("Mantisa");
        this.textFieldExponent = new TextField();
        this.textFieldMantissa = new TextField();
        this.labelDecimalEquivalentValueAndExponent = new JLabel("Valor decimal y su equivalente");
        this.bleed = new JTextField();
        this.bleed = new JTextField();
        this.labelPrecision = new JLabel("- " + textPrecision + " =");
        this.textFieldBleedResult = new JTextField();
        this.labelEquivalentDecimalValue = new JLabel("Valor decimal equivalente");
        this.textFieldEquivalentDecimalValue = new JTextField();
        this.labelHexValue = new JLabel("Valor hexadecimal");
        this.textFieldHaxValue = new JTextField();
        this.labelDecimalValue = new JLabel("Valor decimal");
        this.textFieldDecimalValue = new JTextField();
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.labelSign.setBounds(10, 50, 50, 15);
        this.textFieldSign.setBounds(10, 70, 50, 25);
        this.labelExponent.setBounds(80, 50, 100, 15);
        this.textFieldExponent.setBounds(80, 70, 180, 25);
        this.labelMantissa.setBounds(280, 50, 100, 15);
        this.textFieldMantissa.setBounds(280, 70, 300, 25);
        this.labelDecimalEquivalentValueAndExponent.setBounds(80, 110, 200, 15);
        this.bleed.setBounds(80, 135, 50, 22);
        this.labelPrecision.setBounds(135, 140, 50, 15);
        this.textFieldBleedResult.setBounds(190, 135, 70, 25);
        this.labelEquivalentDecimalValue.setBounds(280, 110, 200, 15);
        this.textFieldEquivalentDecimalValue.setBounds(280, 135, 300, 25);
        this.labelHexValue.setBounds(10, 170, 150, 15);
        this.textFieldHaxValue.setBounds(125, 170, 150, 25);
        this.labelDecimalValue.setBounds(285, 170, 150, 25);
        this.textFieldDecimalValue.setBounds(370, 170, 210, 25);
        this.add(labelSign);
        this.add(textFieldSign);
        this.add(labelExponent);
        this.add(textFieldExponent);
        add(labelMantissa);
        add(textFieldMantissa);
        add(labelDecimalEquivalentValueAndExponent);
        add(bleed);
        add(labelPrecision);
        add(textFieldBleedResult);
        add(labelEquivalentDecimalValue);
        add(textFieldEquivalentDecimalValue);
        add(labelHexValue);
        add(textFieldHaxValue);
        add(labelDecimalValue);
        add(textFieldDecimalValue);
    }

}
