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

package diamond.doc.exporter.cp;

import diamond.Version;
import diamond.doc.Doc;
import diamond.paint.creasepattern.CreasePattern;
import diamond.value.OriLine;

public class DataSet {

    private int mainVersion;
    private double paperSize;
    private int subVersion;
    public String editorName;
    public OriLineProxy[] lines;
    public String memo;
    public String originalAuthorName;
    public String reference;
    public String title;

    public DataSet() {
    }

    public DataSet(Doc doc) {
        mainVersion = Version.FILE_MAJOR_VERSION;
        subVersion = Version.FILE_MINOR_VERSION;

        CreasePattern creasePattern = doc.getCreasePattern();
        int lineNum = creasePattern.size();
        lines = new OriLineProxy[lineNum];
        OriLine[] docLines = new OriLine[lineNum];
        creasePattern.toArray(docLines);
        for (int i = 0; i < lineNum; i++) {
            lines[i] = new OriLineProxy(docLines[i]);
        }
        paperSize = doc.getPaperSize();

        title = doc.getTitle();
        editorName = doc.getEditorName();
        originalAuthorName = doc.getOriginalAuthorName();
        reference = doc.getReference();
        memo = doc.getMemo();
    }

    public String getEditorName() {
        return editorName;
    }

    public OriLineProxy[] getLines() {
        return lines;
    }

    public int getMainVersion() {
        return mainVersion;
    }

    public String getMemo() {
        return memo;
    }

    public String getOriginalAuthorName() {
        return originalAuthorName;
    }

    public double getPaperSize() {
        return paperSize;
    }

    public String getReference() {
        return reference;
    }

    public int getSubVersion() {
        return subVersion;
    }

    public String getTitle() {
        return title;
    }

    public void recover(Doc doc) {
    	CreasePattern creasePattern = doc.getCreasePattern();
        creasePattern.clear();
        for (int i = 0; i < lines.length; i++) {
            creasePattern.add(lines[i].getLine());
        }
        doc.setPaperSize(paperSize);
        doc.setTitle(title);
        doc.setEditorName(editorName);
        doc.setOriginalAuthorName(originalAuthorName);
        doc.setReference(reference);
        doc.setMemo(memo);;
    }

    public void setEditorName(String s) {
        editorName = s;
    }

    public void setLines(OriLineProxy[] l) {
        lines = l;
    }

    public void setMainVersion(int i) {
        mainVersion = i;
    }

    public void setMemo(String s) {
        memo = s;
    }

    public void setOriginalAuthorName(String s) {
        originalAuthorName = s;
    }

    public void setPaperSize(double d) {
        paperSize = d;
    }

    public void setReference(String s) {
        reference = s;
    }

    public void setSubVersion(int i) {
        subVersion = i;
    }

    public void setTitle(String s) {
        title = s;
    }
}
