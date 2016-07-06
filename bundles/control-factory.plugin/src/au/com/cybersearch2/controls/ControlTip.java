/**
    Copyright (C) 2016  www.cybersearch2.com.au

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/> */
package au.com.cybersearch2.controls;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolTip;

/**
 * ControlTip
 * ToolTip which implements FocusListener to control visibility.
 * @author Andrew Bowley
 * 22 Mar 2016
 */
public class ControlTip implements FocusListener
{
    /** Instances of this class represent popup windows that are used to inform or warn the user */
    private ToolTip tip;

    /**
     * Create ControlTip with given ToolTip
     * @param tip The ToolTip
     */
    public ControlTip(ToolTip tip)
    {
        this.tip = tip;
    }
    
    /**
     * focusLost
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) 
    {
        tip.setVisible(false);
    }

    /**
     * focusGained
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) 
    {
        Control actionWidget = (Control) e.widget;
        Point loc = actionWidget.toDisplay(actionWidget.getLocation());
        tip.setLocation(loc.x + actionWidget.getSize().x - actionWidget.getBorderWidth(), loc.y);
        tip.setVisible(true);
    }
}
