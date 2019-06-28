package com.lndc.sender;

import com.lndc.sender.exceptions.NotNaturalNumberException;
import com.lndc.sender.exceptions.TimeException;
import com.lndc.sender.models.MeasurementsUnit;
import com.lndc.sender.utils.MailSender;
import com.lndc.sender.utils.MessageMaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class App {
    private final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private JPanel panelMain;
    private JTextField rateField;
    private JTextField intervalToField;
    private JTextField intervalFromField;
    private JTextField lvlTempToField;
    private JTextField lvlTempFromField;
    private JComboBox lvlLevelComboBox;
    private JComboBox lvlTempComboBox;
    private JTextField lvlLevelFromField;
    private JTextField lvlLevelToField;
    private JComboBox lvlCondComboBox;
    private JButton readFromFileButton;
    private JButton sendButton;
    private JCheckBox lvlCondCheckBox;
    private JCheckBox baroCondCheckBox;
    private JCheckBox baroCheckBox;
    private JTextField eMailField;
    private JTextField lvlCondFromField;
    private JTextField lvlCondToField;
    private JTextField baroTempFromField;
    private JTextField baroTempToField;
    private JTextField baroLevelFromField;
    private JTextField baroCondFromField;
    private JTextField baroCondToField;
    private JTextField baroLevelToField;
    private JComboBox baroTempComboBox;
    private JComboBox baroLevelComboBox;
    private JComboBox baroCondComboBox;
    private JTextField serialField;

    public App(){
        sendButton.addActionListener((ActionEvent event) -> {
            sendMessage();
        });
        lvlCondCheckBox.addActionListener((ActionEvent event) ->{
            lvlCondComboBox.setEnabled(lvlCondCheckBox.isSelected());
            lvlCondFromField.setEnabled(lvlCondCheckBox.isSelected());
            lvlCondToField.setEnabled(lvlCondCheckBox.isSelected());
        });
        baroCheckBox.addActionListener((ActionEvent event) -> {
            baroTempComboBox.setEnabled(baroCheckBox.isSelected());
            baroLevelComboBox.setEnabled(baroCheckBox.isSelected());
            baroTempFromField.setEnabled(baroCheckBox.isSelected());
            baroTempToField.setEnabled(baroCheckBox.isSelected());
            baroLevelFromField.setEnabled(baroCheckBox.isSelected());
            baroLevelToField.setEnabled(baroCheckBox.isSelected());
            baroCondCheckBox.setEnabled(baroCheckBox.isSelected());
            baroCondCheckBox.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
            baroCondFromField.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
            baroCondToField.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
        });
        baroCondCheckBox.addActionListener((ActionEvent event) ->{
            baroCondCheckBox.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
            baroCondFromField.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
            baroCondToField.setEnabled(baroCheckBox.isSelected() && baroCondCheckBox.isSelected());
        });
        lvlLevelComboBox.addItem(MeasurementsUnit.METER.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.CENTIMETER.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.FOOT.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.PSI.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.KPA.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.BAR.toString());
        lvlLevelComboBox.addItem(MeasurementsUnit.MILI_BAR.toString());
        lvlTempComboBox.addItem(MeasurementsUnit.DEGREE_CELSIUS.toString());
        lvlTempComboBox.addItem(MeasurementsUnit.DEGREE_FAHREHEIT.toString());
        lvlCondComboBox.addItem(MeasurementsUnit.MICROSIEMENS_PER_CENTIMETER.toString());
        lvlCondComboBox.addItem(MeasurementsUnit.MILLISIEMENS_PER_CENTIMETER.toString());

        baroLevelComboBox.addItem(MeasurementsUnit.METER.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.CENTIMETER.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.FOOT.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.PSI.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.KPA.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.BAR.toString());
        baroLevelComboBox.addItem(MeasurementsUnit.MILI_BAR.toString());
        baroTempComboBox.addItem(MeasurementsUnit.DEGREE_CELSIUS.toString());
        baroTempComboBox.addItem(MeasurementsUnit.DEGREE_FAHREHEIT.toString());
        baroCondComboBox.addItem(MeasurementsUnit.MICROSIEMENS_PER_CENTIMETER.toString());
        baroCondComboBox.addItem(MeasurementsUnit.MILLISIEMENS_PER_CENTIMETER.toString());
    }

    public static void main(String[] args) {
        MailSender sender = new MailSender("leonmore8@gmail.com", "paleto81");
        sender.send("LS Report" + 22, "asdsad", "lndcsender@gmail.com");
        JFrame jFrame = new JFrame("Sender");
        jFrame.setContentPane(new App().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void sendMessage(){
        if(validateValues()){
            MessageMaker maker = new MessageMaker();
            maker.setIntervalFrom(LocalDateTime.parse(intervalFromField.getText(), formatter));
            maker.setIntervalTo(LocalDateTime.parse(intervalToField.getText(), formatter));
            maker.setRate(Integer.parseInt(rateField.getText()));
            maker.setNumber(Integer.parseInt(serialField.getText()));

            maker.setLvlCondFrom(Double.parseDouble(lvlCondFromField.getText()));
            maker.setLvlCondTo(Double.parseDouble(lvlCondToField.getText()));
            maker.setLvlLevelFrom(Double.parseDouble(lvlLevelFromField.getText()));
            maker.setLvlLevelTo(Double.parseDouble(lvlLevelToField.getText()));
            maker.setLvlTempFrom(Double.parseDouble(lvlTempFromField.getText()));
            maker.setLvlTempTo(Double.parseDouble(lvlTempToField.getText()));
            maker.setHasLevelLoggerConduction(lvlCondCheckBox.isSelected());
            maker.setLvlTempUnit(MeasurementsUnit.fromString(lvlTempComboBox.getSelectedItem().toString()));
            maker.setLvlLevelUnit(MeasurementsUnit.fromString(lvlLevelComboBox.getSelectedItem().toString()));
            maker.setLvlCondUnit(MeasurementsUnit.fromString(lvlCondComboBox.getSelectedItem().toString()));

            maker.setHasBarologger(baroCheckBox.isSelected());
            maker.setBaroCondFrom(Double.parseDouble(baroCondFromField.getText()));
            maker.setBaroCondTo(Double.parseDouble(baroCondToField.getText()));
            maker.setBaroLevelFrom(Double.parseDouble(baroLevelFromField.getText()));
            maker.setBaroLevelTo(Double.parseDouble(baroLevelToField.getText()));
            maker.setBaroTempFrom(Double.parseDouble(baroTempFromField.getText()));
            maker.setBaroTempTo(Double.parseDouble(baroTempToField.getText()));
            maker.setHasBaroLoggerConduction(baroCondCheckBox.isSelected());
            maker.setBaroTempUnit(MeasurementsUnit.fromString(baroTempComboBox.getSelectedItem().toString()));
            maker.setBaroLevelUnit(MeasurementsUnit.fromString(baroLevelComboBox.getSelectedItem().toString()));
            maker.setBaroCondUnit(MeasurementsUnit.fromString(baroCondComboBox.getSelectedItem().toString()));
            MailSender sender = new MailSender("leonmore8@gmail.com", "paleto81");
            sender.send(serialField.getText() + "LS Report" + 22, maker.createMessage(), eMailField.getText());
        }

    }

    private boolean validateValues(){
        if(!eMailField.getText().matches(EMAIL_REGEX)){
            JOptionPane.showMessageDialog(null, "Введен некоректный адресс.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            if(Integer.parseInt(serialField.getText()) < 0 || Integer.parseInt(serialField.getText()) > 999999) throw new NotNaturalNumberException(-2);
            if(Integer.parseInt(rateField.getText()) < 1) throw new NotNaturalNumberException(-1);
            if(Double.parseDouble(lvlTempFromField.getText()) > Double.parseDouble(lvlTempToField.getText())) throw new NotNaturalNumberException(0);
            if(Double.parseDouble(lvlLevelFromField.getText()) > Double.parseDouble(lvlLevelToField.getText())) throw new NotNaturalNumberException(1);
            if(Double.parseDouble(lvlCondFromField.getText()) > Double.parseDouble(lvlCondToField.getText())) throw new NotNaturalNumberException(2);
            if(Double.parseDouble(baroLevelFromField.getText()) > Double.parseDouble(baroLevelToField.getText())) throw new NotNaturalNumberException(3);
            if(Double.parseDouble(baroTempFromField.getText()) > Double.parseDouble(baroTempToField.getText())) throw new NotNaturalNumberException(4);
            if(Double.parseDouble(baroCondFromField.getText()) > Double.parseDouble(baroCondToField.getText())) throw new NotNaturalNumberException(5);
            LocalDateTime from = LocalDateTime.parse(intervalFromField.getText(), formatter);
            LocalDateTime to = LocalDateTime.parse(intervalToField.getText(), formatter);
            if(from.isAfter(to)) throw new TimeException();
        } catch (NotNaturalNumberException e) {
            switch (e.getCode()){
                case -2: showError("Введен некоректный серийный номер.");
                    break;
                case -1: showError("Введена некоректная частота.");
                    break;
                case 0: showError("Введен некоректный интервал значений темпиратуры levellogger'а.");
                    break;
                case 1: showError("Введен некоректный интервал значений уровня levellogger'а.");
                    break;
                case 2: showError("Введен некоректный интервал значений проводимости levellogger'а.");
                    break;
                case 3: showError("Введен некоректный интервал значений темпиратуры barologger'а.");
                    break;
                case 4: showError("Введен некоректный интервал значений уровня barologger'а.");
                    break;
                case 5: showError("Введен некоректный интервал значений проводимости barologger'а.");
                    break;
            }
            return false;
        } catch (TimeException | DateTimeParseException ex) {
            showError("Введен некоректный временной диапозон.");
            return false;
        }
        catch (Exception e){
            showError("Введены некоректные данные.");
            return false;
        }
        return true;
    }

    private void showError(String msg){
        JOptionPane.showMessageDialog(null, msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}
