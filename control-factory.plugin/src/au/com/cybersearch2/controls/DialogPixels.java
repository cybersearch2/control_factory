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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.FontMetrics;

/**
 * DialogPixels
 * Dialog constants evaulated using font metrics to nearest pixels
 * @author Andrew Bowley
 * 26 May 2016
 */
public class DialogPixels
{
    /**
     * marginWidth specifies the number of pixels of horizontal margin
     * that will be placed along the left and right edges of the layout.
     *
     * The GridLayout default value is 5.
     */
    public int marginWidth;
    
    /**
     * marginHeight specifies the number of pixels of vertical margin
     * that will be placed along the top and bottom edges of the layout.
     *
     * The GridLayout default value is 5.
     */
    public int marginHeight;

    /**
     * horizontalSpacing specifies the number of pixels between the right
     * edge of one cell and the left edge of its neighbouring cell to
     * the right.
     *
     * The GridLayout default value is 5.
     */
    public int horizontalSpacing;

    /**
     * verticalSpacing specifies the number of pixels between the bottom
     * edge of one cell and the top edge of its neighbouring cell underneath.
     *
     * The GridLayout default value is 5.
     */
    public int verticalSpacing;

    /**
     * Minimum button width
     */
    public int buttonWidth;
    
    public DialogPixels(FontMetrics fontMetrics)
    {
        marginWidth = 
                Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_MARGIN);
        marginHeight = 
                Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_MARGIN);
        horizontalSpacing = 
                Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_SPACING);
        verticalSpacing = 
                Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_SPACING);
        buttonWidth =
                Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.BUTTON_WIDTH);
    }

    /**
     * @return the marginWidth
     */
    public int getMarginWidth()
    {
        return marginWidth;
    }

    /**
     * @return the marginHeight
     */
    public int getMarginHeight()
    {
        return marginHeight;
    }

    /**
     * @return the horizontalSpacing
     */
    public int getHorizontalSpacing()
    {
        return horizontalSpacing;
    }

    /**
     * @return the verticalSpacing
     */
    public int getVerticalSpacing()
    {
        return verticalSpacing;
    }

    /**
     * @return the buttonWidth
     */
    public int getButtonWidth()
    {
        return buttonWidth;
    }

}
