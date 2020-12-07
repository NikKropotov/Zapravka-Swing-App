package org.orgname.app.util;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class ObjectTableModel<T> extends DefaultTableModel
{
    protected abstract T getEntityFromRowData(Object[] rowData);

    protected abstract Object[] getRowDataFromEntity(T entity);

    public void addRowEntity(T entity)
    {
        addRow(getRowDataFromEntity(entity));
    }

    public void addRowEntities(List<T> entities)
    {
        entities.forEach(e -> addRowEntity(e));
    }

    public T getRowEntity(int row)
    {
        return getEntityFromRowData(getRowData(row));
    }

    public void setRowEntity(int row, T entity)
    {
        setRowData(row, getRowDataFromEntity(entity));
    }

    private Object[] getRowData(int row)
    {
        Object[] rowValues = new Object[getColumnCount()];
        for(int i=0; i<rowValues.length; i++) {
            rowValues[i] = getValueAt(row, i);
        }
        return rowValues;
    }

    private void setRowData(int row, Object[] rowData)
    {
        for(int i=0; i<rowData.length; i++) {
            setValueAt(rowData[i], row, i);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}