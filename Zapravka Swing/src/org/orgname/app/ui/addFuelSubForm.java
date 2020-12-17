package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.FuelEntity;
import org.orgname.app.database.manager.FuelEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.BaseSubForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

import static org.orgname.app.database.manager.FuelEntityManager.isIsFuelExists;

public class addFuelSubForm extends BaseSubForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final FuelEntity fuelEntity;
    private JPanel mainPanel;
    private JButton addFuelButton;
    private JTextField fuel_codeField;
    private JTextField fuel_typeField;
    private JTextField unitField;
    private JTextField priceField;
    private JTextField amountField;
    private JTextField firmField;
    private JLabel marginLabel;
    private JComboBox<String> comboBox1;

    public addFuelSubForm(BaseForm mainForm, FuelEntity fuelEntity) {
        super(mainForm);
        this.fuelEntity = fuelEntity;
        setContentPane(mainPanel);
        initButtons();
        initProperties();
        initComboBox();
        setVisible(true);
    }

    private void initComboBox() {
        comboBox1.addItem("Nesle");
        comboBox1.addItem("Лукойл");
        comboBox1.addItem("ПКТ КЛ");
        comboBox1.addItem("ПТК");
        comboBox1.addItem("РосНефть");
    }

    private void initButtons() {
        addFuelButton.addActionListener(e -> {
            int fuel_code = Integer.parseInt(fuel_codeField.getText());
            if (FuelEntity.isCodeIncorrect(String.valueOf(fuel_code))) {
                DialogUtil.showError(this, "Код введен некорректно");
                return;
            }
            String fuel_type = fuel_typeField.getText();
            if (FuelEntity.isTypeIncorrect(String.valueOf(fuel_type))) {
                DialogUtil.showError(this, "Марка введена некорректно");
                return;
            }
            String unit = unitField.getText();
            if (FuelEntity.isUnitIncorrect(String.valueOf(unit))) {
                DialogUtil.showError(this, "Единица измерения введена некорректно");
                return;
            }
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            int firm = Integer.parseInt(firmField.getText());
            if (FuelEntity.isFirmIncorrect(String.valueOf(firm))) {
                DialogUtil.showError(this, "Номер Поставщика введен некорректно");
                return;
            }
            FuelEntity fuel = new FuelEntity(
                    fuel_code,
                    fuel_type,
                    unit,
                    price,
                    amount,
                    firm
            );

            try {
                String gas_name = String.valueOf(comboBox1.getSelectedItem());
                fuelEntityManager.add_fuel(fuel);
                FuelEntity id_fuel = fuelEntityManager.getIDfuel(fuel);
                System.out.println(id_fuel);
                FuelEntity gas = fuelEntityManager.getStaionByName(gas_name);
                System.out.println(gas);
                fuelEntityManager.add_fuel_station(fuel, gas);
                closeSubForm();
                if (isIsFuelExists()) {
                    setVisible(false);
                }
            } catch (SQLException throwables) {
                DialogUtil.showError(this, "Проверьте правильность введенных данных!");
                throwables.printStackTrace();
            }
        });
    }

    private void initProperties() {
        addFuelButton.setBorder(null);
        addFuelButton.setBackground(new Color(39, 193, 167));
        addFuelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        addFuelButton.setBorderPainted(false);

        addFuelButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        addFuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addFuelButton.setBackground(new Color(32, 153, 129));
                addFuelButton.setText("<html><font color='white'>Добавить топливо</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addFuelButton.setBackground(new Color(39, 193, 167));
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
