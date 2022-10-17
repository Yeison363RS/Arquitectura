package gui;

import javax.swing.*;
import java.awt.*;

public class PanelProcess extends JPanel {


    private JLabel step1;
    private JLabel step2;
    private JLabel step3;
    private JLabel step4;
    private JLabel step5;
    private JLabel step6;
    private JLabel step7;
    private JLabel step8;
    private JTextArea developStep1;
    private JTextArea developStep2;
    private JTextArea developStep3;
    private JTextArea developStep4;
    private JTextArea developStep5;
    private JTextArea developStep6;
    private JTextArea developStep7;
    private JTextArea developStep8;

    public PanelProcess() {
        setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), Texts.TITLE_PANEL_PROCESS, 0, 0, new Font("Arial Narrow", Font.ITALIC, 15))); // NOI18N
        this.init();
    }

    private void init() {
        this.step1 = new JLabel(Texts.TEXT_STEP1);
        this.step2 = new JLabel(Texts.TEXT_STEP2);
        this.step3 = new JLabel(Texts.TEXT_STEP3);
        this.step4 = new JLabel(Texts.TEXT_STEP4);
        this.step5 = new JLabel(Texts.TEXT_STEP5);
        this.step6 = new JLabel(Texts.TEXT_STEP6);
        this.step7 = new JLabel(Texts.TEXT_STEP7);
        this.step8 = new JLabel(Texts.TEXT_STEP8);

        this.developStep1 = new JTextArea();
        this.developStep2 = new JTextArea();
        this.developStep3 = new JTextArea();
        this.developStep4 = new JTextArea();
        this.developStep5 = new JTextArea();
        this.developStep6 = new JTextArea();
        this.developStep7 = new JTextArea();
        this.developStep8 = new JTextArea();
        this.developStep1.setBorder(Constants.LINE_BORDER);
        this.developStep2.setBorder(Constants.LINE_BORDER);
        this.developStep3.setBorder(Constants.LINE_BORDER);
        this.developStep4.setBorder(Constants.LINE_BORDER);
        this.developStep5.setBorder(Constants.LINE_BORDER);
        this.developStep6.setBorder(Constants.LINE_BORDER);
        this.developStep7.setBorder(Constants.LINE_BORDER);
        this.developStep8.setBorder(Constants.LINE_BORDER);
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.step1.setBounds(10, 20, 560, 25);
        this.developStep1.setBounds(15, 50, 560, 120);
        this.step2.setBounds(10, 180, 560, 25);
        this.developStep2.setBounds(15, 210, 560, 120);
        this.step3.setBounds(10, 340, 200, 25);
        this.developStep3.setBounds(230, 340, 345, 25);
        this.step4.setBounds(10, 370, 200, 25);
        this.developStep4.setBounds(230, 370, 50, 25);
        this.step5.setBounds(10, 400, 200, 25);
        this.developStep5.setBounds(230, 400, 100, 25);
        this.step6.setBounds(10, 430, 200, 25);
        this.developStep6.setBounds(15, 460, 560, 120);
        this.step7.setBounds(10, 590, 250, 25);
        this.developStep7.setBounds(15, 620, 560, 25);
        this.step8.setBounds(10, 650, 250, 25);
        this.developStep8.setBounds(15, 680, 560, 25);
        add(step1);
        add(developStep1);
        add(step2);
        add(developStep2);
        add(step3);
        add(developStep3);
        add(step4);
        add(developStep4);
        add(step5);
        add(developStep5);
        add(step6);
        add(developStep6);
        add(step7);
        add(developStep7);
        add(step8);
        add(developStep8);
    }

    public void setDevelopStep1(String developStep1) {
        this.developStep1.setText(developStep1);
    }

    public void setDevelopStep2(String developStep2) {
        this.developStep2.setText(developStep2);
    }

    public void setDevelopStep3(String developStep3) {
        this.developStep3.setText(developStep3);
    }

    public void setDevelopStep4(String developStep4) {
        this.developStep4.setText(developStep4);
    }

    public void setDevelopStep5(String developStep5) {
        this.developStep5.setText(developStep5);
    }

    public void setDevelopStep6(String developStep6) {
        this.developStep6.setText(developStep6);
    }

    public void setDevelopStep7(String developStep7) {
        this.developStep7.setText(developStep7);
    }

    public void setDevelopStep8(String developStep8) {
        this.developStep8.setText(developStep8);
    }
}
