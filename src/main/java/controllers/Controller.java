package controllers;

import gui.GuiManager;
import models.IEEEConverter;
import models.PrecisionEnum;
import models.Procedure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private GuiManager guiManager;
    private IEEEConverter ieeeConverter;

    public Controller() {
        this.guiManager = new GuiManager(this);
        ieeeConverter = new IEEEConverter(PrecisionEnum.SIMPLE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        System.out.println(option);
        switch (option) {
            case Commands.BTN_CONVERT:
                this.convert();
                break;
            case Commands.PRECISION_SIMPLE:
                ieeeConverter.setPrecision(PrecisionEnum.SIMPLE);
                this.showPanelPrecisionSimple();
                break;
            case Commands.PRECISION_DOUBLE:
                ieeeConverter.setPrecision(PrecisionEnum.DOUBLE);
                this.showPanelPrecisionDouble();
                break;
        }
    }

    private void showPanelPrecisionSimple() {
        this.guiManager.showPanelPrecisionSimple(true);
        this.guiManager.showPanelPrecisionDouble(false);
    }

    private void showPanelPrecisionDouble() {
        this.guiManager.showPanelPrecisionDouble(true);
        this.guiManager.showPanelPrecisionSimple(false);
    }

    private void convert() {
        System.out.println("Convertir");
        double number = guiManager.getNumberToConverter();
        System.out.println("Double: " + number);
        ieeeConverter.convertToIEEE(number);
        if(ieeeConverter.getPrecision() == PrecisionEnum.SIMPLE){
            System.out.println("SIMPLE");
            guiManager.setSignPrecisionSimple(ieeeConverter.getSign());
            guiManager.setExponentPrecisionSimple(ieeeConverter.getExponent());
            guiManager.setMantissaPrecisionSimple(ieeeConverter.getMantisa());
            guiManager.setValueHexSimplePrecision(ieeeConverter.getHexadecimal());
            guiManager.setDevelopStep1(Procedure.INSTANCE.getStep1WholeToBinary());
            guiManager.setDevelopStep2(Procedure.INSTANCE.getStep2DecimalPartToBinary());
            System.out.println(Procedure.INSTANCE.getStep3JoinWholeAndDecimal());
            guiManager.setDevelopStep3(Procedure.INSTANCE.getStep3JoinWholeAndDecimal());
            guiManager.setDevelopStep6(Procedure.INSTANCE.getStep6ExpToBinary());
            guiManager.setDevelopStep4(Procedure.INSTANCE.getStep4Slipping()+"");
        }else{
            System.out.println("DOBLE");
        }
    }
}
