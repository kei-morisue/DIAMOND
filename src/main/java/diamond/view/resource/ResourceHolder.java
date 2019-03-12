package diamond.view.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import diamond.view.resource.string.StringKey;

public class ResourceHolder {
    private static final ResourceBundle labelString = createResource(
            ".string.label.Resource");
    private static final ResourceBundle hintString = createResource(
            ".string.hint.Resource");
    private static final ResourceBundle warningString = createResource(
            ".string.warning.Resource");

    private static ResourceBundle createResource(String classPath) {
        String resourcePackage = "diamond.view.resource";
        try {
            return ResourceBundle.getBundle(resourcePackage + classPath,
                    Locale.getDefault());
        } catch (Exception e) {
            return ResourceBundle.getBundle(resourcePackage + classPath,
                    Locale.ENGLISH);
        }
    }

    private ResourceHolder() {
    }

    public static String getLabelString(StringKey.LABEL key) {
        String name = key.name();
        return labelString.getString(name);
    }

    public static String getHintString(StringKey.HINT key) {
        return hintString.getString(key.name());
    }

    public static String getWarningString(StringKey.WARNING key) {
        return warningString.getString(key.name());
    }

}
