package org.orgname.app.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

//генерик с сущностью которую мы будем хранить в таблице
public class CustomTableModel<T> extends AbstractTableModel
{
    /*
    не путать Class и class
    class - тип данных
    Class - классс который описывает тип данных
     */
    private final Class<T> cls;

    //коллекция с значениями которые мы храним в таблице
    private final List<T> values;

    private final String[] columnNames;

    public CustomTableModel(Class<T> cls, List<T> values, String[] columnNames) {
        this.cls = cls;
        this.values = values;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        //количество строчек полчуем из коллекции со значениями
        return values.size();
    }

    @Override
    public int getColumnCount() {
        //получаем все зарегистрированные поля нашей сущности и возвраем их количество
        return cls.getDeclaredFields().length;
//        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        /*//так названием колонки будет название поля в ващей сущгости*/
        return cls.getDeclaredFields()[column].getName();
//        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //получаем все зарегистрированные поля нашей сущности
        //получаем поле по номеру (номер поля = номеру колонки)
        //возвращаем его класс
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        try {
            //получаем поле по номеру колонки
            Field field = cls.getDeclaredFields()[columnIndex];

            //делаем его доступным
            //если оно private, то при методе field.get(...) вылетит исключение
            field.setAccessible(true);

            //возвращаем значение этого поля из сущности из нужной строки
            return field.get(values.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            //если ошибка вернем строку ERROR
            return "ERROR";
        }
    }

    public List<T> getValues() {
        return values;
    }
}