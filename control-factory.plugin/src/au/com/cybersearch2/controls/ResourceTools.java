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

import org.eclipse.jface.resource.ResourceManager;

import java.net.URL;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * ResourceTools
 * System-specific utilities to obtain and manage resources
 * @author Andrew Bowley
 * 20Oct.,2016
 */
public class ResourceTools
{
    /** Manages SWT resources */
    protected ResourceManager resourceManager;

    /** Resource Bundle containing images etc. */ 
    @Inject
    Bundle resourceBundle;

    /** Widget toolkit abstract to synchronize back into the UI-Thread from other threads */
    @Inject
    UISynchronize sync;

    /**
     * Returns local Resource Manager
     * @return ResourceManager object
     */
    public ResourceManager getResourceManager() 
    {
        if (resourceManager == null)
            sync.syncExec(new Runnable() {
                
                @Override
                public void run() 
                {
                    // getResources() returns the ResourceManager for the current display. 
                    // May only be called from a UI thread.
                    ResourceManager resources = JFaceResources.getResources();
                    resourceManager = new LocalResourceManager(resources);
                }
            });
        return resourceManager;
    }

    /**
     * Returns URL for given image path
     * @param imagePath Image path
     * @return URL object
     */
    public URL path2url(String imagePath)
    {
        Bundle bundle = null;
        if (resourceBundle != null)
            bundle = resourceBundle;
        else // Fallback if resource bundle not available
            bundle = FrameworkUtil.getBundle(this.getClass());
        return FileLocator.find(bundle, new Path(imagePath), null);
    }
    
    /**
     * Dispose all image resources
     */
    public void dispose() 
    {
        // Garbage collect system resources
        if (resourceManager != null) 
        {
            resourceManager.dispose();
            resourceManager = null;
        }
    }

    /**
     * TODO - Can this be removed?
     * Set Bundle containing images
     * @param resourceBundle Bundle
     */
    public void setResourceBundle(Bundle resourceBundle)
    {
        this.resourceBundle = resourceBundle;
    }


}
