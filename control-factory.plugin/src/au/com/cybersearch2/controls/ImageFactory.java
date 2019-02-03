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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;


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
    
    Map<Class<?>, TypeMappingFactory<?>> customFactoryMap;

    @Inject
    ResourceTools resourceTools;

    /**
     * Construct ImageFactory object
     */
    public ImageFactory()
    {
        customFactoryMap = new HashMap<Class<?>, TypeMappingFactory<?>>();
    }

    /**
     * Returns image for given image file path
     * @param presence A valid file system path on the local file system
     * @return Image object
     */
    public Image getImage(String imagePath)
    {
        // An image descriptor is an object that knows how to create an SWT image.
        ImageDescriptor descriptor = createImageDescriptor(imagePath);
        return resourceTools.getResourceManager().createImage(descriptor);
    }

    /**
     * Register custom image factory which maps objects to images
     * @param customFactory The custom factory object
     */
    public void registerCustomFactory(TypeMappingFactory<?> customFactory)
    {
        customFactoryMap.put(customFactory.getFactoryClass(), customFactory);
    }

    /**
     * Returns image mapped to given objecgt
     * @param object The object to map
     * @return Image object
     */
    public Image getMappedImage(Object object)
    {
        TypeMappingFactory<?> customFactory = customFactoryMap.get(object.getClass());
        if (customFactory != null)
            return customFactory.getMappedImage(this, object);
        return null;
    }
    
    /**
     * Dispose all image resources
     */
    public void dispose() 
    {
        customFactoryMap.clear();
    }

    /**
     * Returns impage descriptor for given image path
     * @param imagePath Image path
     * @return ImageDescriptor object
     */
    protected ImageDescriptor createImageDescriptor(String imagePath) 
    {
        return ImageDescriptor.createFromURL(resourceTools.path2url(imagePath));
    }
}
