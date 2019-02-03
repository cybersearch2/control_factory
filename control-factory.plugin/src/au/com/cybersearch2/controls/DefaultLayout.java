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
/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stefan Xenos, IBM - initial implementation, bug 178888
 *     Karsten Stoeckmann - bug 156982
 *******************************************************************************/

package au.com.cybersearch2.controls;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * DefaultLayout creates and initializes grid layouts. There are two ways to use DefaultLayout.
 * Normally, it is used as a shorthand for writing "new GridLayout()" and initializing a bunch
 * of fields. In this case the main benefit is a more concise syntax and the ability to create more
 * than one identical GridLayout from the same factory. Changing a property of the factory will affect
 * future layouts created by the factory, but has no effect on layouts that have already been created.
 *
 * <p>
 * DefaultLayout can also generate grid data for all the controls in a layout. This is done with
 * the generateLayout method. To use this feature:
 * </p>
 *
 * <ol>
 * <li>Create the composite</li>
 * <li>Create all the controls in the composite</li>
 * <li>Call generateLayout</li>
 * </ol>
 *
 * <p>
 * The order here is important. generateLayout must be called after all the child controls have
 * been created. generateLayout will not change any layout data that has already been attached
 * to a child control and it will not recurse into nested composites.
 * </p>
 **/

/**
 * DefaultLayout
 * 
 * @author Andrew Bowley
 * 12 May 2016
 */
public interface DefaultLayout
{
    /**
     * Sets whether the columns should be forced to be equal width
     *
     * @param equal true iff the columns should be forced to be equal width
     * @return this
     */
    public DefaultLayout equalWidth(boolean equal);

    /**
     * Sets the spacing for layouts created with this factory. The spacing
     * is the distance between cells within the layout.
     *
     * @param hSpacing horizontal spacing (pixels)
     * @param vSpacing vertical spacing (pixels)
     * @return this
     */
    public DefaultLayout spacing(int hSpacing, int vSpacing);
    
    /**
     * Sets the spacing for layouts created with this factory. The spacing
     * is the distance between cells within the layout.
     *
     * @param spacing space between controls in the layout (pixels)
     * @return this
     */
    public DefaultLayout spacing(Point spacing);
    
    /**
     * Sets the margins for layouts created with this factory. The margins
     * are the distance between the outer cells and the edge of the layout.
     *
     * @param margins margin size (pixels)
     * @return this
     */
    public DefaultLayout margins(Point margins);
    
    /**
     * Sets the margins for layouts created with this factory. The margins
     * specify the number of pixels of horizontal and vertical margin that will
     * be placed along the left/right and top/bottom edges of the layout. Note
     * that thes margins will be added to the ones specified by
     * {@link #extendedMargins(int, int, int, int)}.
     *
     * @param width
     *            margin width (pixels)
     * @param height
     *            margin height (pixels)
     * @return this
     */
    public DefaultLayout margins(int width, int height);
    
    /**
     * Sets the margins for layouts created with this factory. The margins
     * specify the number of pixels of horizontal and vertical margin that will
     * be placed along the left, right, top, and bottom edges of the layout.
     * Note that thes margins will be added to the ones specified by
     * {@link #margins(int, int)}.
     *
     * @param left
     *            left margin size (pixels)
     * @param right
     *            right margin size (pixels)
     * @param top
     *            top margin size (pixels)
     * @param bottom
     *            bottom margin size (pixels)
     * @return this
     */
    public DefaultLayout extendedMargins(int left, int right, int top, int bottom);
    
    /**
     * Sets the margins for layouts created with this factory. The margins
     * specify the number of pixels of horizontal and vertical margin that will
     * be placed along the left, right, top, and bottom edges of the layout.
     * Note that thes margins will be added to the ones specified by
     * {@link #margins(int, int)}.
     *
     * <code><pre>
     *     // Construct a GridLayout whose left, right, top, and bottom
     *     // margin sizes are 10, 5, 0, and 15 respectively
     *
     *     Rectangle margins = Geometry.createDiffRectangle(10,5,0,15);
     *     DefaultLayout.fillDefaults().extendedMargins(margins).applyTo(composite1);
     * </pre></code>
     *
     * @param differenceRect rectangle which, when added to the client area of the
     *        layout, returns the outer area of the layout. The x and y values of
     *        the rectangle correspond to the position of the bounds of the
     *        layout with respect to the client area. They should be negative.
     *        The width and height correspond to the relative size of the bounds
     *        of the layout with respect to the client area, and should be positive.
     * @return this
     */
    public DefaultLayout extendedMargins(Rectangle differenceRect);
    
    /**
     * Sets the number of columns in the layout
     *
     * @param numColumns number of columns in the layout
     * @return this
     */
    public DefaultLayout numColumns(int numColumns);

    /**
     * Creates a new GridLayout, and initializes it with values from the factory.
     *
     * @return a new initialized GridLayout.
     * @see #applyTo
     */
    public GridLayout create();

    /**
     * Creates a new GridLayout and attaches it to the given composite.
     * Does not create the GridData of any of the controls in the composite.
     *
     * @param c composite whose layout will be set
     */
    public void applyTo(Composite c);

    /**
     * Applies this layout to the given composite, and attaches default GridData
     * to all immediate children that don't have one. The layout is generated using
     * heuristics based on the widget types. In most cases, it will create exactly the same
     * layout that would have been hardcoded by the programmer. In any situation
     * where it does not produce the desired layout, the GridData for any child can be
     * overridden by attaching the layout data before calling this method. In these cases,
     * the special-case layout data can be hardcoded and the algorithm can supply defaults
     * to the rest.
     *
     * <p>
     * This must be called <b>AFTER</b> all of the child controls have been created and their
     * layouts attached. This method will attach a layout to the given composite. If any new
     * children are created after calling this method, their GridData must be created manually.
     * The algorithm does not recurse into child composites. To generate all the layouts in
     * a widget hierarchy, the method must be called bottom-up for each Composite.
     * </p>
     *
     * <p>
     * All controls are made to span a single cell. The algorithm tries to classify controls into one
     * of the following categories:
     * </p>
     *
     * <ul>
     * <li>Pushbuttons: Set to a constant size large enough to fit their text and no smaller
     * than the default button size.</li>
     * <li>Wrapping with text (labels, read-only text boxes, etc.): override the preferred horizontal
     *     size with the default wrapping point, fill horizontally, grab horizontal space, keep the
     *     preferred vertical size</li>
     * <li>Wrapping without text (toolbars, coolbars, etc.): fill align, don't grab, use the preferred size</li>
     * <li>Horizontally scrolling controls (anything with horizontal scrollbars or where the user edits
     *     text and can cursor through it from left-to-right): override the preferred horizontal size with
     *     a constant, grab horizontal, fill horizontal.</li>
     * <li>Vertically scrolling controls (anything with vertical scrollbars or where the user edits
     *     text and can cursor through it up and down): override the preferred vertical size with a constant,
     *     grab vertical, fill vertical</li>
     * <li>Nested layouts: fill align both directions, grab along any dimension if the layout would
     *     be able to expand along that dimension.</li>
     * <li>Non-wrapping non-scrollable read-only text: fill horizontally, center vertically, default size, don't grab </li>
     * <li>Non-wrapping non-scrollable non-text: fill both, default size, don't grab</li>
     * </ul>
     *
     * @param c composite whose layout will be generated
     */
    public void generateLayout(Composite c);
}
