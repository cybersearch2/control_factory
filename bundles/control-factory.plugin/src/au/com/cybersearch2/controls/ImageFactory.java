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

import org.eclipse.jface.resource.ResourceManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;


/**
 * ImageFactory
 * Image creation and resource management
 * @author Andrew Bowley
 * 21 Oct 2015
 */
public class ImageFactory
{
    public interface TypeMappingFactory <T>
    {
        Image getMappedImage(ImageFactory imageFactory, Object object);
        Class<T> getFactoryClass();
    }
    
    /** Manages SWT resources. It manages reference-counted instances of resources
     * such as Fonts, Images, and Colors, and allows them to be accessed using descriptors.
     * Everything allocated through the registry should also be disposed through the registry.
     * Since the resources are shared and reference counted, they should never be disposed
     * directly. */
    private ResourceManager resourceManager;
    Map<Class<?>, TypeMappingFactory<?>> customFactoryMap;

    /** Widget toolkit abstract to synchronize back into the UI-Thread from other threads */
    @Inject
    UISynchronize sync;

    public ImageFactory()
    {
        customFactoryMap = new HashMap<Class<?>, TypeMappingFactory<?>>();
    }
    
    /**
     * Returns image for given image file path
     * @param imagePath A valid file system path on the local file system
     * @return Image object
     */
    public Image getImage(String imagePath)
    {
        // An image descriptor is an object that knows how to create an SWT image.
        ImageDescriptor descriptor = createImageDescriptor(imagePath);
        return getResourceManager().createImage(descriptor);
    }

    public void registerCustomFactory(TypeMappingFactory<?> customFactory)
    {
        customFactoryMap.put(customFactory.getFactoryClass(), customFactory);
    }
 
    public Image getMappedImage(Object object)
    {
        TypeMappingFactory<?> customFactory = customFactoryMap.get(object.getClass());
        if (customFactory != null)
            return customFactory.getMappedImage(this, object);
        return null;
    }
    
    /**
     * Returns image for given chat presence
     * @param presence User presence, online, away etc
     * @return Image object
     */
    /*
    public Image getImage(Presence presence)
    {
        String imagePath = "";
        switch (presence)
        {
        case online: imagePath = "icons/online.gif"; break;
        case away: imagePath = "icons/away.gif"; break;
        case dnd: imagePath = "icons/dnd.gif"; break;
        case offline: imagePath = "icons/offline.gif"; break;
        case deleted: imagePath = "icons/deleted.gif"; break;
        }
        return getImage(imagePath);
    }
*/
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
        customFactoryMap.clear();
    }

    /**
     * Returns local Resource Manager
     * @return ResourceManager object
     */
    protected ResourceManager getResourceManager() 
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
     * Returns impage descriptor for given image path
     * @param imagePath Image path
     * @return ImageDescriptor object
     */
    private ImageDescriptor createImageDescriptor(String imagePath) 
    {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        URL url = FileLocator.find(bundle, new Path(imagePath), null);
        return ImageDescriptor.createFromURL(url);
    }
}
