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
package au.com.cybersearch2.dialogs;

/**
 * DialogHandler
 * Public interface of CustomDialog allows a CustomControl to perform dialog operations 
 * dismiss, exit with return code and resize.
 * @author Andrew Bowley
 * 10 May 2016
 */
public interface DialogHandler
{
    /**
     * Notifies that the ok button of this dialog has been pressed.
     * @see org.eclipse.jface.dialogs.Dialog#okPressed 
     */
    void dismissDialog();
    
    /**
     * Sets this window's return code and then closes it
     * @param returnCode The return code
     */
    void exitDialog(int returnCode);
    
    /**
     * Resize dialog. Involves calling this window's shell setSize() method.
     */
    void resizeDialog();
}
