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

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * DialogBase
 * @author Andrew Bowley
 * 24 May 2016
 */
public interface DialogBase
{
    /**
     * Configures the given shell in preparation for opening this window in it.
     * @param newShell The shell
     */
    void configureShell(Shell newShell);

    /**
     * Creates a new button with the given id.
     * @param parent The parent composite
     * @param id The id of the button
     * @param label The label from the button
     * @param defaultButton Flag set true if the button is to be the default button
     * @return the new button
     */
    Button createButton(Composite parent, int id, String label, boolean defaultButton);

    /**
     * Adds buttons to this dialog's button bar.
     * <p>
     * The <code>Dialog</code> implementation of this framework method adds
     * standard ok and cancel buttons using the <code>createButton</code>
     * framework method. These standard buttons will be accessible from
     * <code>getCancelButton</code>, and <code>getOKButton</code>.
     * </p>
     * @param parent The button bar composite
     */
    void createButtonsForButtonBar(Composite parent);

    /**
     * Sets this dialog's return code to <code>Window.OK</code> and closes the
     * dialog.
     */
    void okPressed();

    /**
     * Opens this window, creating it first if it has not yet been created.
     * @return the return code
     */
    int open();

    /**
     * @see org.eclipse.jface.window.Window#close()
     */
    boolean close();

    /**
     * Returns this window's shell.
     *
     * @return this window's shell
     */
     Shell getShell();

}
