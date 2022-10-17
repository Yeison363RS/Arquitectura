package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuiManager extends JFrame {

    private PanelGetNumber panelGetNumber;
    private PanelResults panelResults32;
    private PanelResults panelResults64;

    public GuiManager(ActionListener listener) throws HeadlessException {
        super(Texts.MAIN_TITTLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(false);
        setSize(Constants.DIMENSION_PANEL_PRINCIPAL);
        init(listener);
        setVisible(true);

    }

    private void init(ActionListener listener) {
        this.panelGetNumber = new PanelGetNumber(listener);
        this.panelResults32 = new PanelResults("Precision simple", listener, "127");
        this.panelResults64 = new PanelResults("Precision doble", listener, "1023");
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.panelGetNumber.setBounds(20, 20, 440, 50);
        this.panelResults32.setBounds(20,80,600,210);
        this.panelResults64.setBounds(20,300,600,210);
        add(panelGetNumber);
        add(panelResults32);
        add(panelResults64);

    }


    public double getNumberToConverter() {
        return this.panelGetNumber.getNumberToCovert();
    }
}
