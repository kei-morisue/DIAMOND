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

    private static HashMap<ResourceKey, ResourceBundle> resources = load();

    private ResourceHolder() {
    }

    private static HashMap<ResourceKey, ResourceBundle> load() {
        HashMap<ResourceKey, ResourceBundle> resources = new HashMap<>();
        resources.put(ResourceKey.EXPLANATION,
                createResource(
                        resourcePackage + ".ExplanationStringResource_en"));
        resources.put(ResourceKey.LABEL,
                createResource(resourcePackage + ".LabelStringResource_en"));
        resources.put(ResourceKey.WARNING,
                createResource(resourcePackage + ".WarningStringResource_en"));
        return resources;
    }

    public static ResourceBundle getResource(ResourceKey key) {
        return resources.get(key);
    }

    public static String getString(ResourceKey key, String id) {
        return getResource(key).getString(id);
    }

}
