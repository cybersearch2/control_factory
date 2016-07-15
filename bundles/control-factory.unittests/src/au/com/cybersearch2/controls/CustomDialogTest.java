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

import static org.mockito.Mockito.*;

import static org.fest.assertions.api.Assertions.*;

import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import au.com.cybersearch2.controls.CustomDialog.ButtonFactory;

/**
 * CustomDialogTest
 * @author Andrew Bowley
 * 23 May 2016
 */
public class CustomDialogTest
{
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_createDialogArea()
    {
        Shell parentShell = mock(Shell.class);
        Composite parent = mock(Composite.class);
        Composite composite = mock(Composite.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.createControls(parent, underTest)).thenReturn(composite);
        assertThat(underTest.createDialogArea(parent)).isEqualTo(composite);
        assertThat(underTest.title).isEqualTo("Title");
    }
   
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_createButtonsForButtonBar()
    {
        Shell parentShell = mock(Shell.class);
        Composite parent = mock(Composite.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        DialogBase dialogBase = mock(DialogBase.class);
        when(dialogBase.createButton(parent, 123, "123", false)).thenReturn(mock(Button.class));
        underTest.dialogBase = dialogBase;
        when(customControls.createBarButtons(eq(parent), isA(ButtonFactory.class), eq(underTest))).thenReturn(true);
        underTest.createButtonsForButtonBar(parent);
        ArgumentCaptor<ButtonFactory> buttonFactoryCaptor = ArgumentCaptor.forClass(ButtonFactory.class);
        verify(customControls).createBarButtons(eq(parent), buttonFactoryCaptor.capture(), eq(underTest));
        assertThat(buttonFactoryCaptor.getValue().buttonInstance(parent, 123, "123", false)).isNotNull();
        verify(dialogBase).createButton(parent, 123, "123", false);
    }
   
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_createButtonsForButtonBar_default()
    {
        Shell parentShell = mock(Shell.class);
        Composite parent = mock(Composite.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        when(customControls.createBarButtons(eq(parent), isA(ButtonFactory.class), eq(underTest))).thenReturn(false);
        underTest.createButtonsForButtonBar(parent);
        verify(dialogBase).createButtonsForButtonBar(parent);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_configureShell()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        Shell newShell = mock(Shell.class);
        underTest.configureShell(newShell);
        verify(dialogBase).configureShell(newShell);
        verify(newShell).setText("Title");
       
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_okPressed()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        underTest.okPressed();
        verify(dialogBase).okPressed();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_okPressed_not()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(false);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        underTest.okPressed();
        verify(dialogBase, times(0)).okPressed();
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_dismissDialog()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        underTest.dismissDialog();
        verify(dialogBase).okPressed();
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_exitDialog()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        underTest.exitDialog(99);
        assertThat(underTest.getReturnCode()).isEqualTo(99);
        verify(dialogBase).close();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_resizeDialog()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        Shell shell = mock(Shell.class);
        when(dialogBase.getShell()).thenReturn(shell);
        Point newSize = new Point(123, 456);
        when(shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true)).thenReturn(newSize);
        underTest.dialogBase = dialogBase;
        underTest.resizeDialog();
        verify(shell).setSize(newSize); 
    }
 
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_syncOpen()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        UISynchronize sync = mock(UISynchronize.class);
        assertThat(underTest.syncOpen(sync)).isEqualTo(Window.CANCEL);
        ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(sync).syncExec(taskCaptor.capture());
        taskCaptor.getValue().run();
        verify(dialogBase).open();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void test_syncClose()
    {
        Shell parentShell = mock(Shell.class);
        CustomControls customControls = mock(CustomControls.class);
        CustomDialog underTest = new CustomDialog(parentShell, "Title", customControls);
        when(customControls.defaultSelect(underTest)).thenReturn(true);
        DialogBase dialogBase = mock(DialogBase.class);
        underTest.dialogBase = dialogBase;
        UISynchronize sync = mock(UISynchronize.class);
        underTest.syncClose(sync);
        ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(sync).syncExec(taskCaptor.capture());
        taskCaptor.getValue().run();
        verify(dialogBase).close();
    }
}
