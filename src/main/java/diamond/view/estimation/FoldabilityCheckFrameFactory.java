package diamond.view.estimation;

import java.util.Collection;

import javax.swing.JFrame;

import diamond.fold.OrigamiModel;
import diamond.value.OriLine;

public class FoldabilityCheckFrameFactory {
    private static FoldabilityCheckFrame frame = null;

    public JFrame createFrame(
            OrigamiModel origamiModel,
            Collection<OriLine> creasePattern //, FoldedModelInfo foldedModelInfo
    ) {

        if (frame == null) {
            frame = new FoldabilityCheckFrame();
        }

        frame.setModel(origamiModel, creasePattern //, foldedModelInfo
        );
        return frame;
    }
}
