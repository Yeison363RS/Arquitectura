package controllers;

import gui.GuiManager;
import models.Conversor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private GuiManager guiManager;
    private Conversor conversor;

    public Controller() {
        this.guiManager = new GuiManager(this);
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
                this.showPanelPrecisionSimple();
                break;
            case Commands.PRECISION_DOUBLE:
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
        System.out.println("Double: " + guiManager.getNumberToConverter());
    }
}
