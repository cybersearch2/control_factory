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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;

/**
 * This class provides a convienient shorthand for creating and initializing
 * GridData. This offers several benefits over creating GridData normal way:
 *
 * <ul>
 * <li>The same factory can be used many times to create several GridData instances</li>
 * <li>The setters on DefaultGridData all return "this", allowing them to be chained</li>
 * <li>DefaultGridData uses vector setters (it accepts Points), making it easy to
 *     set X and Y values together</li>
 * </ul>
 *
 * <p>
 * DefaultGridData instances are created using one of the static methods on this class.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <code><pre>
 *
 * ////////////////////////////////////////////////////////////
 * // Example 1: Typical grid data for a non-wrapping label
 *
 *     // DefaultGridData version
 *     DefaultGridData.fillDefaults().applyTo(myLabel);
 *
 *     // Equivalent SWT version
 *     GridData labelData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
 *     myLabel.setLayoutData(labelData);
 *
 * ///////////////////////////////////////////////////////////
 * // Example 2: Typical grid data for a wrapping label
 *
 *     // DefaultGridData version
 *     DefaultGridData.fillDefaults()
 *          .align(SWT.FILL, SWT.CENTER)
 *          .hint(150, SWT.DEFAULT)
 *          .grab(true, false)
 *          .applyTo(wrappingLabel);
 *
 *     // Equivalent SWT version
 *     GridData wrappingLabelData = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER);
 *     wrappingLabelData.minimumWidth = 1;
 *     wrappingLabelData.widthHint = 150;
 *     wrappingLabel.setLayoutData(wrappingLabelData);
 *
 * //////////////////////////////////////////////////////////////
 * // Example 3: Typical grid data for a scrollable control (a list box, tree, table, etc.)
 *
 *     // DefaultGridData version
 *     DefaultGridData.fillDefaults().grab(true, true).hint(150, 150).applyTo(listBox);
 *
 *     // Equivalent SWT version
 *     GridData listBoxData = new GridData(GridData.FILL_BOTH);
 *     listBoxData.widthHint = 150;
 *     listBoxData.heightHint = 150;
 *     listBoxData.minimumWidth = 1;
 *     listBoxData.minimumHeight = 1;
 *     listBox.setLayoutData(listBoxData);
 *
 * /////////////////////////////////////////////////////////////
 * // Example 4: Typical grid data for a button
 *
 *     // DefaultGridData version
 *     Point preferredSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
 *     Point hint = Geometry.max(LayoutConstants.getMinButtonSize(), preferredSize);
 *     DefaultGridData.fillDefaults().align(SWT.FILL, SWT.CENTER).hint(hint).applyTo(button);
 *
 *     // Equivalent SWT version
 *     Point preferredSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
 *     Point hint = Geometry.max(LayoutConstants.getMinButtonSize(), preferredSize);
 *     GridData buttonData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
 *     buttonData.widthHint = hint.x;
 *     buttonData.heightHint = hint.y;
 *     button.setLayoutData(buttonData);
 *
 * /////////////////////////////////////////////////////////////
 * // Example 5: Generated GridData
 *
 *     // Generates GridData a wrapping label that spans 2 columns
 *     DefaultGridData.generate(wrappingLabel, 2, 1);
 *
 *     // Generates GridData for a listbox. and adjusts the preferred size to 300x400 pixels
 *     DefaultGridData.defaultsFor(listBox).hint(300, 400).applyTo(listBox);
 *
 *     // Generates GridData equivalent to example 4
 *     DefaultGridData.generate(button, 1, 1);
 *
 * </pre></code>
 */

/**
 * DefaultGridData
 * @author Andrew Bowley
 * 12 May 2016
 */
public interface DefaultGridData
{
    /**
     * Sets the GridData span. The span controls how many cells
     * are filled by the control.
     *
     * @param hSpan number of columns spanned by the control
     * @param vSpan number of rows spanned by the control
     * @return this
     */
    public DefaultGridData span(int hSpan, int vSpan);

    /**
     * Sets the GridData span. The span controls how many cells
     * are filled by the control.
     *
     * @param span the new span. The x coordinate indicates the number of
     * columns spanned, and the y coordinate indicates the number of rows.
     * @return this
     */
    public DefaultGridData span(Point span) ;

    /**
     * Sets the width and height hints. The width and height hints override
     * the control's preferred size. If either hint is set to SWT.DEFAULT,
     * the control's preferred size is used.
     *
     * @param xHint horizontal hint (pixels), or SWT.DEFAULT to use the control's preferred size
     * @param yHint vertical hint (pixels), or SWT.DEFAULT to use the control's preferred size
     * @return this
     */
    public DefaultGridData hint(int xHint, int yHint);

    /**
     * Sets the width and height hints. The width and height hints override
     * the control's preferred size. If either hint is set to SWT.DEFAULT,
     * the control's preferred size is used.
     *
     * @param hint size (pixels) to be used instead of the control's preferred size. If
     * the x or y values are set to SWT.DEFAULT, the control's computeSize() method will
     * be used to obtain that dimension of the preferred size.
     * @return this
     */
    public DefaultGridData hint(Point hint);

    /**
     * Sets the alignment of the control within its cell.
     *
     * @param hAlign horizontal alignment. One of SWT.BEGINNING, SWT.CENTER, SWT.END, or SWT.FILL.
     * @param vAlign vertical alignment. One of SWT.BEGINNING, SWT.CENTER, SWT.END, or SWT.FILL.
     * @return this
     */
    public DefaultGridData align(int hAlign, int vAlign);

    /**
     * Sets the indent of the control within the cell. Moves the position of the control
     * by the given number of pixels. Positive values move toward the lower-right, negative
     * values move toward the upper-left.
     *
     * @param hIndent distance to move to the right (negative values move left)
     * @param vIndent distance to move down (negative values move up)
     * @return this
     */
    public DefaultGridData indent(int hIndent, int vIndent);
    /**
     * Sets the indent of the control within the cell. Moves the position of the control
     * by the given number of pixels. Positive values move toward the lower-right, negative
     * values move toward the upper-left.
     *
     * @param indent offset to move the control
     * @return this
     */
    public DefaultGridData indent(Point indent);

    /**
     * Determines whether extra horizontal or vertical space should be allocated to
     * this control's column when the layout resizes. If any control in the column
     * is set to grab horizontal then the whole column will grab horizontal space.
     * If any control in the row is set to grab vertical then the whole row will grab
     * vertical space.
     *
     * @param horizontal true if the control's column should grow horizontally
     * @param vertical true if the control's row should grow vertically
     * @return this
     */
    public DefaultGridData grab(boolean horizontal, boolean vertical);

    /**
     * Sets the minimum size for the control. The control will not be permitted
     * to shrink below this size. Note: GridLayout treats a minimum size of 0
     * as an undocumented special value, so the smallest possible minimum size
     * is a size of 1. A minimum size of SWT.DEFAULT indicates that the result
     * of computeSize(int, int, boolean) should be used as the control's minimum
     * size.
     *
     *
     * @param minX minimum a value of 1 or more is a horizontal size of the control (pixels).
     *        SWT.DEFAULT indicates that the control's preferred size should be used. A size
     *        of 0 has special semantics defined by GridLayout.
     * @param minY minimum a value of 1 or more is a vertical size of the control (pixels). SWT.DEFAULT
     *        indicates that the control's preferred size should be used. A size
     *        of 0 has special semantics defined by GridLayout.
     * @return this
     */
    public DefaultGridData minSize(int minX, int minY);

    /**
     * Sets the minimum size for the control. The control will not be permitted
     * to shrink below this size. Note: GridLayout treats a minimum size of 0
     * as an undocumented special value, so the smallest possible minimum size
     * is a size of 1. A minimum size of SWT.DEFAULT indicates that the result
     * of computeSize(int, int, boolean) should be used as the control's minimum
     * size.
     *
     * @param min minimum size of the control
     * @return this
     */
    public DefaultGridData minSize(Point min);

    /**
     * Instructs the GridLayout to ignore this control when performing layouts.
     *
     * @param shouldExclude true iff the control should be excluded from layouts
     * @return this
     */
    public DefaultGridData exclude(boolean shouldExclude);

    /**
     * Creates a new GridData instance. All attributes of the GridData instance
     * will be initialized by the factory.
     *
     * @return a new GridData instance
     */
    public GridData create();

    /**
     * Creates a copy of the receiver.
     *
     * @return a copy of the receiver
     */
    public DefaultGridData copy();

    /**
     * Sets the layout data on the given control. Creates a new GridData instance and
     * assigns it to the control by calling control.setLayoutData.
     *
     * @param control control whose layout data will be initialized
     */
    public void applyTo(Control control);


}
