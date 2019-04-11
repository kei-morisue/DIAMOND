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

package diamond.model.geom.element;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

public class Line {

    public OriPoint dir; // Unit direction vector
    public OriPoint p; // Passing through point

    public Line(OriPoint p, OriPoint dir) {
        this.p = p;
        this.dir = dir;
        dir.normalize();
    }

    public Line(OriLine l) {
        this.p = l.p0;
        this.dir = new OriPoint(l.p1.x - l.p0.x, l.p1.y - l.p0.y);
        dir.normalize();
    }
}
