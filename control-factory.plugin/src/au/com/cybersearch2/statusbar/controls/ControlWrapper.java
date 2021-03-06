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
package au.com.cybersearch2.statusbar.controls;

import org.eclipse.swt.widgets.Control;

import au.com.cybersearch2.controls.LabelLayoutData;

/**
 * ControlWrapper
 * Wrapper for a child of the status line. Assists in dynamically laying out items 
 * according to a strategy that fills the status line when there are 3 or more seperator-
 * delimited application controls.
 * @author Andrew Bowley
 * 22 Mar 2016
 */
public class ControlWrapper
{
    /** Seperator width. */
    public static final int GAP = 3;
    /** Default layout if a control returns null for getLayoutData() (not expected) */
    private final static StatusItemLayoutData DEFAULT_DATA;
 
    static
    {
        DEFAULT_DATA = new StatusItemLayoutData();
    }
    
    /** The control being wrapped */
    Control widget;
    /** Width calculated according to control layout data */
    int width;
    /** Vertical relative position */
    int top;
    /** Parent client area height */
    int height;
  
    /**
     * Create ControlWrapper object
     * @param widget The control being wrapped
     * @param top Vertical relative position
     * @param height Parent client area height
     * @param changed <code>true</code> if the control's contents have changed
     */
    public ControlWrapper(Control widget, int top, int height, boolean changed)
    {
        this.widget = widget;
        this.top = top;
        this.height = height;
        LabelLayoutData data = (LabelLayoutData) widget.getLayoutData();
        if (data == null) 
            // Not expected. Default hint width and height both SWT.DEFAULT
            data = DEFAULT_DATA;
        // Note: Width will be 0 for separators
        width = widget.computeSize(data.widthHint, height, changed).x;
    }

    /**
     * Returns computed width of control
     * @return width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Set width of control
     * @param width int value
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Position control at specified position 
     * @param x Horizontal relative position
     * @return Position of next control
     */
    public int position(int x)
    {
        widget.setBounds(x, top, width, height);
        return width > 0 ? x + width + GAP : x;
    }

    /**
     * Position control according to specified position and width 
     * @param x Horizontal relative position
     * @param width Revised control width
     */
    public void position(int x, int width)
    {
        this.width = width;
        widget.setBounds(x, top, width, height);
    }
 
    /**
     * Position control aligned with the right of the status line client area
     * @param clientLeft Position of the left hand side of the client area
     * @return  Position of next control
     */
    public int alignRight(int clientLeft)
    {
        int x = clientLeft - width;
        widget.setBounds(x, top, width, height);
        return x - GAP;
    }

}
