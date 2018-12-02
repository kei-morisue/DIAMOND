package diamond.resource;

import diamond.resource.StringID.Main;

public class Version {
    public static final String VERSION = Main.class.getPackage()
            .getSpecificationVersion();

    public static final int FILE_MAJOR_VERSION = 1;
    public static final int FILE_MINOR_VERSION = 1;


}
