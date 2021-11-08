/*
 * ToolbarButton.java
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

import javax.swing.Action;
import javax.swing.JButton;

/**
 *
 * @author Giuseppe Profiti
 */
public class ToolbarButton extends JButton {
    public ToolbarButton() {
        super();
    }

    public ToolbarButton(Action a) {
        super(a);
        setText(null);
    }

    @Override
    public void setAction(Action a) {
        super.setAction(a);
        setText(null);
    }
}
