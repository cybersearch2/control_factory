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

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * DefaultLayoutImpl
 * @author Andrew Bowley
 * 12 May 2016
 */
public class DefaultLayoutImpl implements DefaultLayout
{
    GridLayoutFactory glf;
    /**
     * 
     */
    public DefaultLayoutImpl()
    {
        glf = GridLayoutFactory.fillDefaults();
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#equalWidth(boolean)
     */
    @Override
    public DefaultLayout equalWidth(boolean equal)
    {
        glf.equalWidth(equal);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#spacing(int, int)
     */
    @Override
    public DefaultLayout spacing(int hSpacing, int vSpacing)
    {
        glf.spacing(hSpacing, vSpacing);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#spacing(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultLayout spacing(Point spacing)
    {
        glf.spacing(spacing);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#margins(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultLayout margins(Point margins)
    {
        glf.margins(margins);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#margins(int, int)
     */
    @Override
    public DefaultLayout margins(int width, int height)
    {
        glf.margins(width, height);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#extendedMargins(int, int, int, int)
     */
    @Override
    public DefaultLayout extendedMargins(int left, int right, int top,
            int bottom)
    {
        glf.extendedMargins(left, right, top, bottom);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#extendedMargins(org.eclipse.swt.graphics.Rectangle)
     */
    @Override
    public DefaultLayout extendedMargins(Rectangle differenceRect)
    {
        glf.extendedMargins(differenceRect);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#numColumns(int)
     */
    @Override
    public DefaultLayout numColumns(int numColumns)
    {
        glf.numColumns(numColumns);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#create()
     */
    @Override
    public GridLayout create()
    {
        return glf.create();
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#applyTo(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void applyTo(Composite c)
    {
        glf.applyTo(c);
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultLayout#generateLayout(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void generateLayout(Composite c)
    {
        glf.generateLayout(c);
    }

}
