/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.model.geom;

public class OriLine implements Comparable<OriLine> {

    public OriPoint p0;
    public OriPoint p1;
    public boolean selected;
    OriLineType type;

    public OriLine() {
    }

    public OriLine(OriPoint p0, OriPoint p1, OriLineType type) {
        this.type = type;
        this.p0 = p0;
        this.p1 = p1;
    }

    public OriLine(OriLine l) {
        selected = l.selected;
        p0.set(l.p0);
        p1.set(l.p1);
        type = l.type;
    }

    public double length() {
        return Math.hypot((p1.x - p0.x), (p1.y - p0.y));
    }

    public Line getLine() {
        return new Line(p0, new OriPoint(p1.x - p0.x, p1.y - p0.y));
    }

    public Segment getSegment() {
        return new Segment(p0, p1);
    }

    public OriLineType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "" + p0 + "" + p1;
    }

    @Override
    public int compareTo(OriLine oline) {
        int comparison00 = this.p0.compareTo(oline.p0);
        int comparison11 = this.p1.compareTo(oline.p1);
        if (comparison00 == 0) {
            return comparison11;
        }
        return comparison00;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OriLine)) {
            return false;
        }

        OriLine oline = (OriLine) obj;
        int comparison00 = this.p0.compareTo(oline.p0);
        int comparison01 = this.p0.compareTo(oline.p1);
        int comparison10 = this.p1.compareTo(oline.p0);
        int comparison11 = this.p1.compareTo(oline.p1);

        // same direction?
        if (comparison00 == 0 && comparison11 == 0) {
            return true;
        }

        // reversed direction?
        if (comparison01 == 0 && comparison10 == 0) {
            return true;
        }

        // differs
        return false;
    }

}