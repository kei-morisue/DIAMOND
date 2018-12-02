package diamond.bind.copypaste;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.JOptionPane;

import diamond.bind.state.ErrorListener;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class CopyPasteErrorListener implements ErrorListener {

    @Override
    public boolean isError(ActionEvent e) {
        Doc document = DocHolder.getInstance().getDoc();
        Collection<OriLine> creasePattern = document.getCreasePattern();

        Painter painter = new Painter();
        return (painter.countSelectedLines(creasePattern) == 0);
    }

    @Override
    public void onError(Component parent, ActionEvent e) {
        showErrorMessage(parent, e);
    }

    private void showErrorMessage(Component parent, ActionEvent e) {
        JOptionPane.showMessageDialog(parent, "Select target lines",
                "Copy and Paste", JOptionPane.WARNING_MESSAGE);
    }

}
