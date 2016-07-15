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

import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import au.com.cybersearch2.dialogs.DialogHandler;

/**
 * CustomDialog
 * @author Andrew Bowley
 * 10 May 2016
 */
public class CustomDialog<T extends CustomControls>  extends Dialog implements DialogHandler
{
    /**
     * ButtonFactory
     * Allows CustomControl to create buttons for the button bar
     */
    public interface ButtonFactory
    {
        /**
         * Returns button control with given id
         * @param parent The parent composite
         * @param id The id of the button (see <code>IDialogConstants.*_ID</code>
         *            constants for standard dialog button ids)
         * @param label The label from the button
         * @param defaultButton
         *            <code>true</code> if the button is to be the default button,
         *            and <code>false</code> otherwise
         * @return ButtonControl object
         */
        Button buttonInstance(Composite parent, int id, String label, boolean defaultButton);
    }

    /** Dialog title */
    String title;
    /** Creates dialog content */
    T customControls;
    /** Super class - accessed as a field to allow testing */
    DialogBase dialogBase;

    /**
     * Create CustomDialog object
     * @param parentShell Parent shell
     * @param title Dialog title
     * @param customControls Dialog content
     */
    public CustomDialog(Shell parentShell, String title, T customControls)
    {
        super(parentShell);
        this.title = title;
        this.customControls = customControls;
        dialogBase = new DialogBase(){

            @Override
            public void configureShell(Shell newShell)
            {
                CustomDialog.super.configureShell(newShell);
            }

            @Override
            public Button createButton(Composite parent, int id, String label,
                    boolean defaultButton)
            {
                return CustomDialog.super.createButton(parent, id, label, defaultButton);
            }

            @Override
            public void createButtonsForButtonBar(Composite parent)
            {
                CustomDialog.super.createButtonsForButtonBar(parent);
            }

            @Override
            public void okPressed()
            {
                CustomDialog.super.okPressed();
            }

            @Override
            public int open()
            {
                return CustomDialog.super.open();
            }
            
            @Override
            public boolean close()
            {
                return CustomDialog.super.close();
            }

            @Override
            public Shell getShell()
            {
                return CustomDialog.super.getShell();
            }
        };
    }

    /**
     * Returns object which creates dialog content
     * @return CustomControls object
     */
    public T getCustomControls()
    {
        return customControls;
    }
    
    /**
     * Delegate createDialogArea()
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) 
    {   // Creates a composite containing controls to display above the Button Bar.
        // The DialogHandler interface allows for exiting and resizing the dialog
        return customControls.createControls(parent, this);
    }

    /**
     * Create buttons for Button Bar
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) 
    {
        ButtonFactory buttonFactory = new ButtonFactory(){

            @Override
            public Button buttonInstance(Composite parent, int id,
                    String label, boolean defaultButton)
            {
                return dialogBase.createButton(parent, id, label, defaultButton);
            }
        };
        if (!customControls.createBarButtons(parent, buttonFactory, this))
            dialogBase.createButtonsForButtonBar(parent);
    }

    /**
     * Set Dialog title
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) 
    {
        dialogBase.configureShell(newShell);
        newShell.setText(title);
    }
    
    /**
     * Default button pressed. 
     * Let custom controls decide whether to dismiss dialog.
     * The dismissDialog() method is available for dialog behaviour customisation. 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() 
    {
        if (customControls.defaultSelect(this))
            dialogBase.okPressed();
    }

    /**
     * dismissDialog
     * @see au.com.cybersearch2.dialogs.DialogHandler#dismissDialog()
     */
    @Override
    public void dismissDialog()
    {   // Dismiss dialog and return OK code (at end of processing initiated by default button pressed)
        dialogBase.okPressed();
    }

    /**
     * exitDialog
     * @see au.com.cybersearch2.dialogs.DialogHandler#exitDialog(int)
     */
    @Override
    public void exitDialog(int returnCode)
    {   // Dismiss dialog and return supplied value
        setReturnCode(returnCode);
        dialogBase.close();
    }

    /**
     * resizeDialog
     * @see au.com.cybersearch2.dialogs.DialogHandler#resizeDialog()
     */
    @Override
    public void resizeDialog()
    {   // Resize dialog boiler plate code
        Shell shell = dialogBase.getShell();
        shell.layout(true, true);
        Point newSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);  
        shell.setSize(newSize);    
    }

    /**
     * Open dialog synchronously in UI thread
     * @param sync UISynchronize object
     * @return the return code
     */
    public int syncOpen(UISynchronize sync)
    {
        final int[] returnCode = new int[]{Window.CANCEL};
        sync.syncExec(new Runnable(){

            @Override
            public void run()
            {
                returnCode[0] = dialogBase.open();
            }});
        return returnCode[0];
    }

    /**
     * Open dialog synchronously in UI thread
     * @param sync UISynchronize object
     */
    public void syncClose(UISynchronize sync)
    {
        sync.syncExec(new Runnable(){

            @Override
            public void run()
            {
                dialogBase.close();
            }});
    }
}
