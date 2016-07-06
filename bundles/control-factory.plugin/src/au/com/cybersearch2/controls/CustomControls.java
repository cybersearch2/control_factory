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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import au.com.cybersearch2.dialogs.DialogHandler;

/**
 * CustomControls
 * The super class for classes which provide content to CustomDialog. It declares abstract method createDialogArea() 
 * which needs to be overriden with a concrete implementation.
 * concrett 
 * @author Andrew Bowley
 * 10 May 2016
 */
public abstract class CustomControls
{
    /** SWT widget factory */
    protected ControlFactory controlFactory;

    /**
     * Create CustomControls object
     * @param  controlFactory SWT widget factory
     */
    public CustomControls(ControlFactory controlFactory)
    {
        this.controlFactory = controlFactory;
    }

    /**
     * Create controls for custom dialog. 
     * This is the chief means to customise dialogs and a default implementation is not possible.
     * @param parent Parent composite
     * @param dialogHandler Handler for exiting and resizing the dialog
     * @return Control object containing all Login controls
     */
    abstract public Control createDialogArea(Composite parent, DialogHandler dialogHandler); 

    /**
     * Create Buttons For Button Bar. 
     * If not overriden, the the dialog will contain default OK and Cancel buttons
     * @param parent Parent composite
     * @param buttonFactory Creates Button Bar buttons
     * @param dialogHandler Handler for exiting and resizing the dialog
     * @return flag set true if custom buttons created
     */
    protected boolean createButtonsForButtonBar(Composite parent, CustomDialog.ButtonFactory buttonFactory, DialogHandler dialogHandler) 
    {
        return false;
    }

    /**
     * Handle default button pressed
     * @param dialogHandler Handler for exiting and resizing the dialog
     * @return flag set true if dialog should be dismissed
     */
    public boolean defaultPressed(DialogHandler dialogHandler)
    {
        return true;
    }
}
