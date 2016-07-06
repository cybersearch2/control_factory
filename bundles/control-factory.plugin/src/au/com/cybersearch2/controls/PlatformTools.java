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

import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

/**
 * PlatformTools
 * Functions which interact with the operating system
 * @author Andrew Bowley
 * 27 May 2016
 */
public class PlatformTools
{
    /** Image creation and resource management */
    @Inject 
    ImageFactory imageFactory;
    /** A Display object is responsible for managing the
     * connection between SWT and the underlying operating system.  */
    @Inject
    Display display;
 
    /**
     * Sets the array of default images to use for newly opened windows. It is
     * expected that the array will contain the same icon rendered at different
     * resolutions.
     * @param images List of image paths
     */
    public void setDefaultImages(List<String> images)
    {
        // Window static method used to get logo in top left Cybertete windows 
        Image[] imageArray = new Image[images.size()];
        int index = 0;
        for (String image: images)
            imageArray[index++] = imageFactory.getImage(image);
        Window.setDefaultImages(imageArray);
    }

    /**
     * Get shell before application launch
     * @return Shell object
     */
    public Shell getShellInstance()
    {
        // Create new top level shell and place it in center of primary monitor
        Shell shell = new Shell(SWT.SHELL_TRIM);
        // Position the shell in the center of the primary monitor
        Monitor monitor = display.getPrimaryMonitor();
        Rectangle monitorRect = monitor.getBounds();
        Rectangle shellRect = shell.getBounds();
        int x = monitorRect.x + (monitorRect.width - shellRect.width) / 2;
        int y = monitorRect.y + (monitorRect.height - shellRect.height) / 2;
        shell.setLocation(x, y);
        return shell;
    }

}
