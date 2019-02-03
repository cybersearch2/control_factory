/**
    Copyright (C) 2015  www.cybersearch2.com.au

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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * ButtonBar
 * Button bar for views
 * @author Andrew Bowley
 * 14 Dec 2015
 */
public class ButtonBar
{
    /** SWT widget factory */
    ControlFactory controlFactory;
    /** Parent composite */
    private Composite parent;
    /** Button bar composite */
    private Composite bar;
    /** Dialog constants converted to pixels */
    DialogPixels dialogPixels;

    /**
     * Construct ButtonBar object
     * @param controlFactory SWT widget factory
     * @param parent Parent composite
     */
    public ButtonBar(ControlFactory controlFactory, Composite parent)
    {
        this.controlFactory = controlFactory;
        this.parent = parent;
        dialogPixels = controlFactory.getDialogPixels(parent);
        bar = controlFactory.compositeInstance(parent);
        // Create a layout with spacing and margins appropriate for the font size.
        GridLayout buttonLayout = new GridLayout();
        buttonLayout.numColumns = 0; // This is incremented by createButton
        buttonLayout.makeColumnsEqualWidth = true;
        buttonLayout.marginWidth = dialogPixels.getMarginWidth();
        buttonLayout.marginHeight = dialogPixels.getMarginHeight();
        buttonLayout.horizontalSpacing = dialogPixels.getHorizontalSpacing();
        buttonLayout.verticalSpacing = dialogPixels.getVerticalSpacing();
        bar.setLayout(buttonLayout);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END |
                                      GridData.VERTICAL_ALIGN_CENTER);
        bar.setLayoutData(data);
        bar.setFont(parent.getFont());
    }

    /**
     * Create button
     * @param id The id of the button (see <code>IDialogConstants.*_ID</code>
     *            constants for standard dialog button ids)
     * @param label The label from the button
     * @param selectionAdapter Optional listener
     * @param defaultButton
     *            <code>true</code> if the button is to be the default button,
     *            and <code>false</code> otherwise
     * @return Button object
     */
    public Button createButton(int id, String label, SelectionAdapter selectionAdapter, boolean defaultButton)
    {
        Button button = buttonInstance(id, label, selectionAdapter, defaultButton);
        return button;
    }

    /**
     * Adds new button to button bar and returns button
     * @param id The id of the button (see <code>IDialogConstants.*_ID</code>
     *            constants for standard dialog button ids)
     * @param label The label from the button
     * @param selectionAdapter Optional listener
     * @param defaultButton
     *            <code>true</code> if the button is to be the default button,
     *            and <code>false</code> otherwise
     * @return Button object
     */
    Button buttonInstance(int id, String label, SelectionAdapter selectionAdapter, boolean defaultButton) 
    {
        // increment the number of columns in the button bar
        ((GridLayout) bar.getLayout()).numColumns++;
        Button button = controlFactory.buttonInstance(bar, SWT.PUSH);
        button.setText(label);
        button.setFont(controlFactory.getDialogFont());
        button.setData(Integer.valueOf(id));
        if (selectionAdapter != null)
            button.addSelectionListener(selectionAdapter);
        if (defaultButton) 
             parent.getShell().setDefaultButton(button);
        setButtonLayoutData(button);
        return button;
    }

    /**
     * Set button data for explicit width hint and horizontal fill alignment
     * @param button The button to set
     */
    void setButtonLayoutData(Button button) 
    {
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        data.widthHint = Math.max(dialogPixels.getButtonWidth(), minSize.x);
        button.setLayoutData(data);
    }
}
