package controllers;

import gui.GuiManager;
import models.IEEEConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private GuiManager guiManager;
    private IEEEConverter IEEEConverter;

    public Controller() {
        this.guiManager = new GuiManager(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch (option) {
            case Commands.BTN_CONVERT:
                this.convert();
                break;
        }
    }

    private void convert() {
        System.out.println("Convertir");
        System.out.println("Double: " + guiManager.getNumberToConverter());
    }
}
