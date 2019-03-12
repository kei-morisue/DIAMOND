package diamond.view.resource.string.hint;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.HINT;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { HINT.AXIOM1.name(),
                    "Specify two distinct points p1 and p2."
                            + " The Line passes through both of them" },
            { HINT.AXIOM2.name(),
                    "Specify two distinct points p1 and p2."
                            + " The Line places p1 onto p2" },
            { HINT.AXIOM3.name(),
                    "Specify two lines l1 and l2."
                            + "The Line places l1 onto l2" },
            { HINT.AXIOM4.name(),
                    "Specify a point p1 and a line l1."
                            + "The Line is perpendicular to l1 that passes through point p1" },

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
