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

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import au.com.cybersearch2.controls.CustomLabelSpec;
import au.com.cybersearch2.controls.LabelLayoutData;

/**
 * StatusItemLayoutData
 * Customises Status Line layout data to adjust height and width hints according to font
 * @author Andrew Bowley
 * 25 May 2016
 */
public class StatusItemLayoutData extends LabelLayoutData
{

    /**
     * Create default StatusItemLayoutData object
     */
    public StatusItemLayoutData()
    {
        super();
    }
    
    /**
     * Create StatusItemLayoutData object
     * @param label Custom Label
     * @param specification Custom Label specification
     */
    public StatusItemLayoutData(CLabel label, CustomLabelSpec specification)
    {
        Composite parent = label.getParent();
        GC gc = new GC(parent);
        gc.setFont(parent.getFont());
        FontMetrics fontMetrics = gc.getFontMetrics();
        String text = specification.getText();
        Image image = specification.getImage();
        if (specification.getWidth() > 0) 
            this.widthHint = (int) (specification.getWidth() * fontMetrics.getAverageCharacterWidth());
        else 
        {
            this.widthHint = label.getLeftMargin() + label.getRightMargin(); 
            if ((text != null) && !text.isEmpty())
                this.widthHint += gc.textExtent(text).x;
        }
        if (image != null)
            this.widthHint += image.getBounds().width + 5;
        gc.dispose();
        heightHint = fontMetrics.getHeight();
    }

}
