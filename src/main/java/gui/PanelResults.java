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
    private JTextField textFieldBleed;
    private JLabel labelPrecision;
    private JTextField textFieldBleedResult;
    private JLabel labelEquivalentDecimalValue;
    private JTextField textFieldEquivalentDecimalValue;
    private JLabel labelHexValue;
    private JTextField textFieldHexValue;
    private JLabel labelDecimalValue;
    private JTextField textFieldDecimalValue;

    public PanelResults(String tittle, ActionListener listener, String textPrecision) {
        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(1), tittle, 0, 0, new java.awt.Font("Arial Narrow", Font.ITALIC, 15))); // NOI18N
        this.init(textPrecision);

    }

    private void init(String textPrecision) {
        this.labelSign = new JLabel(Texts.LABEL_SIGN);
        this.textFieldSign = new TextField();
        this.labelExponent = new JLabel(Texts.LABEL_EXPONENT);
        this.labelMantissa = new JLabel(Texts.LABEL_MANTISSA);
        this.textFieldExponent = new TextField();
        this.textFieldMantissa = new TextField();
        this.labelDecimalEquivalentValueAndExponent = new JLabel(Texts.LABEL_VALUE_DECIMAL_EQUIVALENT);
        this.textFieldBleed = new JTextField();
        this.textFieldBleed = new JTextField();
        this.labelPrecision = new JLabel("- " + textPrecision + " =");
        this.textFieldBleedResult = new JTextField();
        this.labelEquivalentDecimalValue = new JLabel(Texts.LABEL_VALUE_DECIMAL_EQUIVALENT2);
        this.textFieldEquivalentDecimalValue = new JTextField();
        this.labelHexValue = new JLabel(Texts.LABEL_VALUE_HEX);
        this.textFieldHexValue = new JTextField();
        this.labelDecimalValue = new JLabel(Texts.VALUE_DECIMAL);
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
        this.labelDecimalEquivalentValueAndExponent.setBounds(10, 110, 200, 15);
        this.textFieldBleed.setBounds(10, 135, 50, 22);
        this.labelPrecision.setBounds(70, 140, 50, 15);
        this.textFieldBleedResult.setBounds(120, 135, 80, 25);
        this.labelEquivalentDecimalValue.setBounds(10, 170, 150, 15);
        this.textFieldEquivalentDecimalValue.setBounds(10, 190, 300, 25);
        this.labelHexValue.setBounds(10, 230, 150, 15);
        this.textFieldHexValue.setBounds(10, 250, 300, 25);
        this.labelDecimalValue.setBounds(10, 285, 150, 25);
        this.textFieldDecimalValue.setBounds(10, 310, 300, 25);
        this.add(labelSign);
        this.add(textFieldSign);
        this.add(labelExponent);
        this.add(textFieldExponent);
        add(labelMantissa);
        add(textFieldMantissa);
        add(labelDecimalEquivalentValueAndExponent);
        add(textFieldBleed);
        add(labelPrecision);
        add(textFieldBleedResult);
        add(labelEquivalentDecimalValue);
        add(textFieldEquivalentDecimalValue);
        add(labelHexValue);
        add(textFieldHexValue);
        add(labelDecimalValue);
        add(textFieldDecimalValue);
    }

    public int getSign() {
        return Integer.parseInt(this.textFieldSign.getText());
    }

    public void setSign(int sign) {
        this.textFieldSign.setText(String.valueOf(sign));
    }

    public String getExponent() {
        return this.textFieldExponent.getText();
    }

    public void setExponent(String exponent) {
        this.textFieldExponent.setText(exponent);
    }

    public String getMantissa() {
        return this.textFieldMantissa.getText();
    }

    public void setMantissa(String mantissa) {
        this.textFieldMantissa.setText(mantissa);
    }

    public String getBleed() {
        return this.textFieldBleed.getText();
    }

    public void setBleed(int bleed) {
        this.textFieldBleed.setText(String.valueOf(bleed));
    }

    public int getResultBleed() {
        return Integer.parseInt(this.textFieldBleedResult.getText());
    }

    public void setBleedResult(int bleedResult) {
        this.textFieldBleedResult.setText(String.valueOf(bleedResult));
    }

    public int getValueDecimalEquivalent() {
        return Integer.parseInt(this.textFieldEquivalentDecimalValue.getText());
    }

    public void setValueDecimalEquivalent(int valueDecimalEquivalent) {
        this.textFieldEquivalentDecimalValue.setText(String.valueOf(valueDecimalEquivalent));
    }

    public String getValueHex() {
        return this.textFieldHexValue.getText();
    }

    public void setValueHex(String valueHex) {
        this.textFieldHexValue.setText(valueHex);
    }

    public int getValueDecimal() {
        return Integer.parseInt(this.textFieldDecimalValue.getText());
    }

    public void setValueDecimal(int valueDecimal) {
        this.textFieldDecimalValue.setText(String.valueOf(valueDecimal));
    }

}
