package org.orgname.app.util;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class BaseSubForm<T extends BaseForm> extends BaseForm
{
    protected T mainForm;

    public BaseSubForm(T mainForm)
    {
        this.mainForm = mainForm;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeSubForm();
            }
        });

        mainForm.setEnabled(false);
    }

    public void closeSubForm()
    {
        dispose();
        mainForm.setEnabled(true);

        //костыль чтобы окно не уходило за другие окна
        mainForm.setVisible(false);
        mainForm.setVisible(true);
    }
}
