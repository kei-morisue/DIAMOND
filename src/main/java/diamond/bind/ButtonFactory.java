package diamond.bind;

import java.awt.Component;

import javax.swing.AbstractButton;

import diamond.resource.ResourceKey;

/**
 * A factory which creates some clickable GUI object
 * with a state to be after the object is clicked.
 * @author Koji
 *
 */
public interface ButtonFactory {

    public abstract AbstractButton create(Component parent,
            Class<? extends AbstractButton> buttonClass, String id);

    public abstract AbstractButton create(Component parent,
            Class<? extends AbstractButton> buttonClass, ResourceKey key,
            String id);
}