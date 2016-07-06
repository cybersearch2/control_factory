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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
//import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * ButtonBarTest
 * @author Andrew Bowley
 * 26 May 2016
 */
public class ButtonBarTest
{
    static final int BUTTON_ID = 3;
    static final String BUTTON_TEXT = "Push me";
    
    @Test
    public void test_constructor()
    {
        ControlFactory controlFactory = mock(ControlFactory.class); 
        Composite parent = mock(Composite.class);
        // Following works on Windows only for final class Font
        //Font font = new Font(mock(Device.class), new FontData());
        // Therefore use null which is ok since the value is not referenced in the code
        Font font = null;
        when(parent.getFont()).thenReturn(font);
        when(controlFactory.getDialogFont()).thenReturn(font);
        DialogPixels dilogPixels = mock(DialogPixels.class);
        when(dilogPixels.getMarginHeight()).thenReturn(5);
        when(dilogPixels.getMarginWidth()).thenReturn(5);
        when(dilogPixels.getHorizontalSpacing()).thenReturn(5);
        when(dilogPixels.getVerticalSpacing()).thenReturn(5);
        when(dilogPixels.getButtonWidth()).thenReturn(50);
        when(controlFactory.getDialogPixels(parent)).thenReturn(dilogPixels);
        Composite bar = mock(Composite.class);
        Shell shell = mock(Shell.class);
        when(parent.getShell()).thenReturn(shell);
        when(controlFactory.compositeInstance(parent)).thenReturn(bar);
         ButtonBar underTest = new ButtonBar(controlFactory, parent);
        //verify(bar).setFont(font);
        ArgumentCaptor<GridData> gridDataCaptor = ArgumentCaptor.forClass(GridData.class);
        verify(bar).setLayoutData(gridDataCaptor.capture());
        assertThat(gridDataCaptor.getValue().horizontalAlignment).isEqualTo(GridData.END );
        assertThat(gridDataCaptor.getValue().verticalAlignment).isEqualTo(GridData.CENTER);
        ArgumentCaptor<GridLayout> gridLayoutCaptor = ArgumentCaptor.forClass(GridLayout.class);
        verify(bar).setLayout(gridLayoutCaptor.capture());
        GridLayout gridLayout = gridLayoutCaptor.getValue();
        assertThat(gridLayout.horizontalSpacing).isEqualTo(5);
        assertThat(gridLayout.verticalSpacing).isEqualTo(5);
        assertThat(gridLayout.marginHeight).isEqualTo(5);
        assertThat(gridLayout.marginWidth).isEqualTo(5);
        assertThat(gridLayout.numColumns).isEqualTo(0);
        assertThat(gridLayout.makeColumnsEqualWidth).isTrue();
        SelectionAdapter selectionAdapter = mock(SelectionAdapter.class);
        when(bar.getLayout()).thenReturn(gridLayout);
        Button button = mock(Button.class);
        Button ok = mock(Button.class);
        when(button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true)).thenReturn(new Point(20, 10));
        when(ok.computeSize(SWT.DEFAULT, SWT.DEFAULT, true)).thenReturn(new Point(10, 10));
        when(controlFactory.buttonInstance(bar, SWT.PUSH)).thenReturn(button, ok);
        underTest.createButton(BUTTON_ID, BUTTON_TEXT, selectionAdapter, false);
        assertThat(gridLayout.numColumns).isEqualTo(1);
        verify(button).setText(BUTTON_TEXT);
        //verify(button).setFont(font);
        verify(button).setData(BUTTON_ID);
        verify(button).addSelectionListener(selectionAdapter);
        ArgumentCaptor<GridData> buttonDataCaptor = ArgumentCaptor.forClass(GridData.class);
        verify(button).setLayoutData(buttonDataCaptor.capture());
        GridData buttonData = buttonDataCaptor.getValue();
        assertThat(buttonData.widthHint).isEqualTo(50);
        assertThat(buttonData.horizontalAlignment).isEqualTo(SWT.FILL);
        underTest.createButton(1, "OK", null, true);
        assertThat(gridLayout.numColumns).isEqualTo(2);
        verify(ok).setText("OK");
        verify(ok).setData(1);
        verify(shell).setDefaultButton(ok);
    }
}
