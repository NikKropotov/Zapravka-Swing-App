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

public class editFuelSubFrom extends BaseSubForm {
    private final FuelEntityManager fuelEntityManager = new FuelEntityManager(Application.getInstance().getDatabase());
    private final FuelEntity fuelEntity;
    private JPanel mainPanel;
    private JButton editFuelButton;
    private JTextField fuel_codeField;
    private JTextField fuel_typeField;
    private JTextField unitField;
    private JTextField priceField;
    private JTextField amountField;
    private JTextField firmField;
    private JLabel marginLabel;
    private JComboBox<String> comboBox1;
    private JTextField idFuelField;

    public editFuelSubFrom(BaseForm mainForm, FuelEntity fuelEntity) {
        super(mainForm);
        this.fuelEntity = fuelEntity;
        setContentPane(mainPanel);
        initFields();
        initButtons();
        initProperties();
        setVisible(true);
    }

    private void initButtons() {
        editFuelButton.addActionListener(e -> {
            fuelEntity.setFuel_code(Integer.parseInt(fuel_codeField.getText()));
            fuelEntity.setFuel_type(fuel_typeField.getText());
            fuelEntity.setUnit(unitField.getText());
            fuelEntity.setPrice_one_litr(Integer.parseInt(priceField.getText()));
            fuelEntity.setFuel_amount(Integer.parseInt(amountField.getText()));
            fuelEntity.setFirm_id_firm(Integer.parseInt(firmField.getText()));

            try {
                String gas_name = String.valueOf(comboBox1.getSelectedItem());
                FuelEntity gas = fuelEntityManager.getStaionByName(gas_name);
                fuelEntityManager.update_fuel(fuelEntity);
                fuelEntityManager.update_fuel_station(gas, fuelEntity.getId_fuel());
                closeSubForm();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                DialogUtil.showError(this, "Не удалось обновить топливо");
            }
        });
    }

    private void initFields() {
        idFuelField.setEditable(false);
        try {
            FuelEntity fuelEntity_by_id = fuelEntityManager.getFuelById(fuelEntity.getId_fuel());
            fuel_codeField.setText(String.valueOf(fuelEntity_by_id.getFuel_code()));
            unitField.setText(fuelEntity_by_id.getUnit());
            firmField.setText(String.valueOf(fuelEntity_by_id.getFirm_id_firm()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        idFuelField.setText(String.valueOf(fuelEntity.getId_fuel()));
        fuel_typeField.setText(fuelEntity.getFuel_type());
        priceField.setText(String.valueOf(fuelEntity.getPrice_one_litr()));
        amountField.setText(String.valueOf(fuelEntity.getFuel_amount()));
        comboBox1.addItem("Nesle");
        comboBox1.addItem("Лукойл");
        comboBox1.addItem("ПКТ КЛ");
        comboBox1.addItem("ПТК");
        comboBox1.addItem("РосНефть");
        comboBox1.setSelectedItem(fuelEntity.getGas_station_address());
    }

    private void initProperties() {
        editFuelButton.setBorder(null);
        editFuelButton.setBackground(new Color(39, 193, 167));
        editFuelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        editFuelButton.setBorderPainted(false);

        editFuelButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        editFuelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editFuelButton.setBackground(new Color(32, 153, 129));
                editFuelButton.setText("<html><font color='white'>Изменить топливо</font></html>");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editFuelButton.setBackground(new Color(39, 193, 167));
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
