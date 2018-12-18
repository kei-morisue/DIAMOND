package diamond.paint.copypaste;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.JOptionPane;

import diamond.bind.state.ErrorListener;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class CopyPasteErrorListener implements ErrorListener {

    private void showErrorMessage(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Select target lines",
                "Copy and Paste", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean isError(ActionEvent e) {
        Doc document = DocHolder.getDoc();
        Collection<OriLine> creasePattern = document.getCreasePattern();

        Painter painter = new Painter();
        return (painter.countSelectedLines(creasePattern) == 0);
    }

    @Override
    public void onError(ActionEvent e) {
        showErrorMessage(e);
    }

}
