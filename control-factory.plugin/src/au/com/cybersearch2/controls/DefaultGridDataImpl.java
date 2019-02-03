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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;

/**
 * DefaultGridDataImpl
 * @author Andrew Bowley
 * 12 May 2016
 */
public class DefaultGridDataImpl implements DefaultGridData
{
    GridDataFactory gdf;
    
    /**
     * 
     */
    public DefaultGridDataImpl()
    {
        gdf = GridDataFactory.fillDefaults();
    }

    public DefaultGridDataImpl(GridDataFactory copy)
    {
        gdf = copy;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#span(int, int)
     */
    @Override
    public DefaultGridData span(int hSpan, int vSpan)
    {
        gdf.span(hSpan, vSpan);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#span(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultGridData span(Point span)
    {
        gdf.span(span);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#hint(int, int)
     */
    @Override
    public DefaultGridData hint(int xHint, int yHint)
    {
        gdf.hint(xHint, yHint);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#hint(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultGridData hint(Point hint)
    {
        gdf.hint(hint);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#align(int, int)
     */
    @Override
    public DefaultGridData align(int hAlign, int vAlign)
    {
        gdf.align(hAlign, vAlign);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#indent(int, int)
     */
    @Override
    public DefaultGridData indent(int hIndent, int vIndent)
    {
        gdf.indent(hIndent, vIndent);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#indent(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultGridData indent(Point indent)
    {
        gdf.indent(indent);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#grab(boolean, boolean)
     */
    @Override
    public DefaultGridData grab(boolean horizontal, boolean vertical)
    {
        gdf.grab(horizontal, vertical);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#minSize(int, int)
     */
    @Override
    public DefaultGridData minSize(int minX, int minY)
    {
        gdf.minSize(minX, minY);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#minSize(org.eclipse.swt.graphics.Point)
     */
    @Override
    public DefaultGridData minSize(Point min)
    {
        gdf.minSize(min);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#exclude(boolean)
     */
    @Override
    public DefaultGridData exclude(boolean shouldExclude)
    {
        gdf.exclude(shouldExclude);
        return this;
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#create()
     */
    @Override
    public GridData create()
    {
        return gdf.create();
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#copy()
     */
    @Override
    public DefaultGridData copy()
    {
        return new DefaultGridDataImpl(gdf.copy());
    }

    /**
     * @see au.com.cybersearch2.controls.DefaultGridData#applyTo(org.eclipse.swt.widgets.Control)
     */
    @Override
    public void applyTo(Control control)
    {
        gdf.applyTo(control);
    }

}
