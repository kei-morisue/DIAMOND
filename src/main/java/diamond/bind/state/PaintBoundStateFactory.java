package diamond.bind.state;

import java.awt.Component;
import java.awt.event.ActionListener;

import diamond.appstate.ApplicationState;
import diamond.bind.EditOutlineActionWrapper;
import diamond.bind.copypaste.CopyAndPasteActionWrapper;
import diamond.bind.copypaste.CopyPasteErrorListener;
import diamond.paint.EditMode;
import diamond.paint.addvertex.AddVertexAction;
import diamond.paint.bisector.AngleBisectorAction;
import diamond.paint.byvalue.LineByValueAction;
import diamond.paint.core.PaintContext;
import diamond.paint.deleteline.DeleteLineAction;
import diamond.paint.deletevertex.DeleteVertexAction;
import diamond.paint.line.TwoPointLineAction;
import diamond.paint.linetype.ChangeLineTypeAction;
import diamond.paint.mirror.MirrorCopyAction;
import diamond.paint.pbisec.TwoPointBisectorAction;
import diamond.paint.segment.TwoPointSegmentAction;
import diamond.paint.selectline.SelectAllLineAction;
import diamond.paint.selectline.SelectLineAction;
import diamond.paint.symmetric.SymmetricalLineAction;
import diamond.paint.triangle.TriangleSplitAction;
import diamond.paint.vertical.VerticalLineAction;
import diamond.resource.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.uipanel.ChangeOnAlterTypeButtonSelected;
import diamond.viewsetting.main.uipanel.ChangeOnByValueButtonSelected;
import diamond.viewsetting.main.uipanel.ChangeOnOtherCommandButtonSelected;
import diamond.viewsetting.main.uipanel.ChangeOnPaintInputButtonSelected;
import diamond.viewsetting.main.uipanel.ChangeOnSelectButtonSelected;

public class PaintBoundStateFactory {

	PaintContext context = PaintContext.getInstance();

	

	private ApplicationState<EditMode> createLineInputState(
			Component parent, String id){

		LocalPaintBoundStateFactory stateFactory = 
				new LocalPaintBoundStateFactory(parent, 
				new ActionListener[] {new ViewChangeListener(
						new ChangeOnPaintInputButtonSelected())} );


		ApplicationState<EditMode> state = null;
		switch(id){
		case StringID.DIRECT_V_ID:

			state = stateFactory.create(new TwoPointSegmentAction(), 
					id, null);
			break;
			
		case StringID.ON_V_ID:
			state =	stateFactory.create(new TwoPointLineAction(), 
					id, null);
			break;
		case StringID.VERTICAL_ID:
			state = stateFactory.create(new VerticalLineAction(), 
					id, null);
			break;
			
		case StringID.BISECTOR_ID:
			state = stateFactory.create(new AngleBisectorAction(), 
					id, null);
			break;
			
		case StringID.TRIANGLE_ID:
			state = stateFactory.create(new TriangleSplitAction(), 
					id, null);

			break;

		case StringID.SYMMETRIC_ID:
			state = stateFactory.create(new SymmetricalLineAction(), 
					id, null);

			break;
		case StringID.MIRROR_ID:
			state = stateFactory.create(new MirrorCopyAction(context), 
					id, null);

			break;

		case StringID.BY_VALUE_ID:
			LocalPaintBoundStateFactory byValueFactory = new LocalPaintBoundStateFactory(
					parent, new ActionListener[] {new ViewChangeListener(new ChangeOnByValueButtonSelected())});

			state = byValueFactory.create(new LineByValueAction(), 
					id,	null );

			break;

		case StringID.PERPENDICULAR_BISECTOR_ID:
			state = stateFactory.create(new TwoPointBisectorAction(), 
					id, null);

		}

		return state;
	}

	/**
	 * Create a state specified by ID
	 * @param parent
	 * @param id A member of StringID
	 * @return
	 */
	public ApplicationState<EditMode> create(Component parent, String id){

		LocalPaintBoundStateFactory stateFactory = 
				new LocalPaintBoundStateFactory(parent, null);


		ApplicationState<EditMode> state = null;

		switch(id){
		case StringID.SELECT_ID:
			state = stateFactory.create(
					new SelectLineAction(context), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnSelectButtonSelected())});
			break;
			
		case StringID.DELETE_LINE_ID:
			state =	stateFactory.create(
					new DeleteLineAction(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnOtherCommandButtonSelected())});		 
			break;

		case StringID.CHANGE_LINE_TYPE_ID:
			state = stateFactory.create(
					new ChangeLineTypeAction(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnAlterTypeButtonSelected())});
			break;
			
		case StringID.ADD_VERTEX_ID:
			state =	stateFactory.create(new AddVertexAction(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnOtherCommandButtonSelected())});
			break;
			
		case StringID.DELETE_VERTEX_ID:
			state =	stateFactory.create(new DeleteVertexAction(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnOtherCommandButtonSelected())});
			break;

		case StringID.EDIT_CONTOUR_ID:
			state = stateFactory.create(
					new EditOutlineActionWrapper(),	id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnOtherCommandButtonSelected())});
			break;
			
		case StringID.SELECT_ALL_LINE_ID:
			state = stateFactory.create(
					new SelectAllLineAction(context), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnSelectButtonSelected())});
			break;
			
		case StringID.COPY_PASTE_ID:
			state = stateFactory.create(
					new CopyAndPasteActionWrapper(false),
					new CopyPasteErrorListener(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnSelectButtonSelected())});
			break;
			
		case StringID.CUT_PASTE_ID:
			state = stateFactory.create(
					new CopyAndPasteActionWrapper(true),
					new CopyPasteErrorListener(), id, 
					new ActionListener[] {new ViewChangeListener(new ChangeOnSelectButtonSelected())});
			break;


		default:
			state = createLineInputState(parent, id);
		}

		if(state == null){
			throw new NullPointerException("Wrong ID for creating state");
		}


		return state;
	}
}
