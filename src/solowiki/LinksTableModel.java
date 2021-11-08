/*
 * LinksTableModel.java
 *
 *  Copyright (C) 2010 Giuseppe Profiti
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 */
package solowiki;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Giuseppe Profiti
 */
public class LinksTableModel extends DefaultTableModel {

    Class[] types = new Class[]{
        java.lang.String.class, java.lang.Boolean.class};
    boolean[] canEdit = new boolean[]{
        false, false
    };

    public LinksTableModel() {
        super(null, new String[]{"Link", //TODO use a centralized name
            java.util.ResourceBundle.getBundle(
                "solowiki/resources/MainWindow").getString(
                "linkcheckdialog.exists")});
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
    public Vector<String> getTitles() {
        Vector<String> tit = new Vector<String>(this.getColumnCount());
        for (int i=0; i<getColumnCount(); i++)
            tit.add(getColumnName(i));
        return tit;
    }
}
