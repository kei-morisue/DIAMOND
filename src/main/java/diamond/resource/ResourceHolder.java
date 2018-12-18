package diamond.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceHolder {
    private static final ResourceBundle labelString = createResource(
            ".string.LabelStringResource");
    private static final ResourceBundle hintString = createResource(
            ".string.HintStringResource");
    private static final ResourceBundle warningString = createResource(
            ".string.WarningStringResource");

    public static ResourceBundle createResource(String classPath) {
        String resourcePackage = "diamond.resource";
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

    public static String getLabelString(String key) {
        return labelString.getString(key);
    }

    public static String getHintString(String key) {
        return hintString.getString(key);
    }

    public static String getWarningString(String key) {
        return warningString.getString(key);
    }

}
