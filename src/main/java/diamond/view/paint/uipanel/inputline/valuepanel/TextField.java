/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline.valuepanel;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import diamond.paint.byvalue.AbstractValueInputListener;
import diamond.paint.byvalue.ValueDB;

/**
 * @author long_
 *
 */
public abstract class TextField extends JFormattedTextField
        implements Observer {

    public TextField() {
        super(createNumberFormat());
        setColumns(4);
        setValue(new java.lang.Double(0.0));
        setHorizontalAlignment(SwingConstants.RIGHT);
        getDocument()
                .addDocumentListener(getValueInputListener());

        ValueDB.getInstance().addObserver(this);
    }

    private static NumberFormat createNumberFormat() {
        NumberFormat doubleValueFormat = NumberFormat
                .getNumberInstance(Locale.US);
        doubleValueFormat.setMinimumFractionDigits(3);
        return doubleValueFormat;
    }

    protected abstract double getMeasuredValue();

    protected abstract AbstractValueInputListener getValueInputListener();

    @Override
    public void update(Observable o, Object arg) {
        setValue(getMeasuredValue());
    }
}
