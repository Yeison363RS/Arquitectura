package gui;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuiManager extends JFrame {


    private PanelGetNumber panelGetNumber;
    private PanelResults panelResults32;
    private PanelResults panelResults64;
    private PanelProcess panelProcess;

    public GuiManager(ActionListener listener) throws HeadlessException {
        super(Texts.MAIN_TITTLE);
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(false);
        setSize(Constants.DIMENSION_PANEL_PRINCIPAL);
        init(listener);
        setVisible(true);

    }

    private void init(ActionListener listener) {
        this.getContentPane().setBackground(MyColors.generateRandomColor(Constants.COLOR_BASE));
        this.panelGetNumber = new PanelGetNumber(listener);
        this.panelResults32 = new PanelResults(Texts.TITTLE_PANEL_PRECISION_SIMPLE, listener, Texts.VALUE_PRECISION_SIMPLE);
        this.panelResults64 = new PanelResults(Texts.TITTLE_PANEL_PRECISION_DOUBLE, listener, Texts.VALUE_PRECISION_DOUBLE);
        this.panelProcess = new PanelProcess();
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.panelResults64.setVisible(false);
        this.panelGetNumber.setBounds(20, 20, 600, 60);
        this.panelResults32.setBounds(20, 90, 600, 360);
        this.panelResults64.setBounds(20, 90, 600, 360);
        this.panelProcess.setBounds(640, 20, 600, 810);
        add(panelGetNumber);
        add(panelResults32);
        add(panelResults64);
        add(panelProcess);
    }

    public void setSignPrecisionSimple(int sign) {
        this.panelResults32.setSign(sign);
    }

    public void setSignPrecisionDouble(int sign) {
        this.panelResults64.setSign(sign);
    }

    public void setExponentPrecisionSimple(String exponent) {
        this.panelResults32.setExponent(exponent);
    }

    public void setExponentPrecisionDouble(String exponent) {
        this.panelResults64.setExponent(exponent);
    }

    public void setMantissaPrecisionSimple(String mantissa) {
        this.panelResults32.setMantissa(mantissa);
    }

    public void setMantissaPrecisionDouble(String mantissa) {
        this.panelResults64.setMantissa(mantissa);
    }

    public void setBleedSimplePrecision(int bleed) {
        this.panelResults32.setBleed(bleed);
    }

    public void setBleedDoublePrecision(int bleed) {
        this.panelResults64.setBleed(bleed);
    }

    public void setResultBleedSimplePrecision(int resultBleed) {
        this.panelResults32.setBleedResult(resultBleed);
    }

    public void setResultBleedDoublePrecision(int resultBleed) {
        this.panelResults64.setBleedResult(resultBleed);
    }

    public void setDecimalValueEquivalentSimplePrecision(int decimalValueEquivalent) {
        this.panelResults32.setValueDecimalEquivalent(decimalValueEquivalent);
    }

    public void setDecimalValueEquivalentDoublePrecision(int decimalValueEquivalent) {
        this.panelResults64.setValueDecimalEquivalent(decimalValueEquivalent);
    }

    public void setValueHexSimplePrecision(String valueHexSimplePrecision) {
        this.panelResults32.setValueHex(valueHexSimplePrecision);
    }

    public void setValueHexDoublePrecision(String valueHexDoublePrecision) {
        this.panelResults64.setValueHex(valueHexDoublePrecision);
    }

    public void setValueDecimalSimplePrecision(int valueDecimalSimplePrecision) {
        this.panelResults32.setValueDecimal(valueDecimalSimplePrecision);
    }

    public void setValueDecimalDoublePrecision(int valueDecimalDoublePrecision) {
        this.panelResults64.setValueDecimal(valueDecimalDoublePrecision);
    }


    public Double getNumberToConverter() {
        if (this.panelGetNumber.getNumberToCovert() != null) {
            return this.panelGetNumber.getNumberToCovert();
        } else {
            return null;
        }
    }

    public void showPanelPrecisionSimple(boolean visible) {
        this.panelResults32.setVisible(visible);
    }

    public void showPanelPrecisionDouble(boolean visible) {
        this.panelResults64.setVisible(visible);
    }

    public void setDevelopStep1(String developStep1) {
        this.panelProcess.setDevelopStep1(developStep1);
    }

    public void setDevelopStep2(String developStep2) {
        this.panelProcess.setDevelopStep2(developStep2);
    }

    public void setDevelopStep3(String developStep3) {
        this.panelProcess.setDevelopStep3(developStep3);
    }

    public void setDevelopStep4(String developStep4) {
        this.panelProcess.setDevelopStep4(developStep4);
    }

    public void setDevelopStep5(String developStep5) {
        this.panelProcess.setDevelopStep5(developStep5);
    }

    public void setDevelopStep6(String developStep6) {
        this.panelProcess.setDevelopStep6(developStep6);
    }

    public void setDevelopStep7(String developStep7) {
        this.panelProcess.setDevelopStep7(developStep7);
    }

    public void setDevelopStep8(String developStep8) {
        this.panelProcess.setDevelopStep8(developStep8);
    }
}
