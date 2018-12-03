package diamond.resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A singleton for resources.
 * All resources are loaded at the beginning.
 *
 * @author koji
 *
 */
public class ResourceHolder {


    //----------------------------------------------------------
    private static ResourceHolder instance = null;

    private static final String resourcePackage = "diamond.resource";

    public static ResourceBundle createResource(String classPath) {
        ResourceBundle bundle;

        // get a resource for the location
        try {
            bundle = ResourceBundle.getBundle(classPath,
                    Locale.getDefault());
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(classPath,
                    Locale.ENGLISH);
        }

        // sorry for forcing English...
        bundle = ResourceBundle.getBundle(classPath, Locale.ENGLISH);


        return bundle;
    }

    public static ResourceHolder getInstance() {
        if (instance == null) {
            instance = new ResourceHolder();
            instance.load();
        }

        return instance;
    }
    //----------------------------------------------------------

    private HashMap<ResourceKey, ResourceBundle> resources = new HashMap<>();

    private ResourceHolder() {
    }

    private void load() {
        ResourceHolder resources = ResourceHolder.getInstance();
        resources.addResource(ResourceKey.EXPLANATION,
                createResource(
                        resourcePackage + ".ExplanationStringResource_en"));
        resources.addResource(ResourceKey.LABEL,
                createResource(resourcePackage + ".LabelStringResource_en"));
        resources.addResource(ResourceKey.WARNING,
                createResource(resourcePackage + ".WarningStringResource_en"));

    }

    public void addResource(ResourceKey key, ResourceBundle resource) {
        resources.put(key, resource);
    }

    public ResourceBundle getResource(ResourceKey key) {
        return resources.get(key);
    }

    public String getString(ResourceKey key, String id) {
        return getResource(key).getString(id);
    }

}
