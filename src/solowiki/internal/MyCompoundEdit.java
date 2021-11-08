/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solowiki.internal;

import javax.swing.undo.CompoundEdit;

/**
 *
 * @author Giuseppe Profiti
 */
public class MyCompoundEdit extends CompoundEdit {

    String editName;

    public MyCompoundEdit(String name) {
        super();
        editName = name;
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public String getPresentationName() {
        return editName;
    }

    @Override
    public String getUndoPresentationName() {
        return javax.swing.UIManager.getString("AbstractDocument.undoText") + " "
                + getPresentationName();
    }

    @Override
    public String getRedoPresentationName() {
        return javax.swing.UIManager.getString("AbstractDocument.redoText") + " "
                + getPresentationName();
    }
}
