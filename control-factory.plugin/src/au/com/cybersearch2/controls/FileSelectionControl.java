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

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.swt.widgets.FileDialog;

/**
 * FileSelectionControl
 * Standard file selection dialog with file filter
 * @author Andrew Bowley
 * 9 Mar 2016
 */
@Creatable
public class FileSelectionControl
{
    /** Standard file selection dialog */
    FileDialog fileDialog;
    /** File filter */
    FileFilter fileFilter;
 
    /**
     * Construct FileSelectionControl object
     */
    public FileSelectionControl()
    {
        fileFilter = new FileFilter();
    }

    /**
     * postConstruct
     * @param controlFactory SWT widget factory
     */
    @PostConstruct
    void postConstruct(ControlFactory controlFactory)
    {
        // File standard dialog
        fileDialog = controlFactory.fileDialogInstance();
    }

    /**
     * Returns file filter
     * @return FileFilter object
     */
    public FileFilter getFileFilter()
    {
        return fileFilter;
    }

    /**
     * Open dialog with given prompt and return selected file path
     * @param prompt Prompt to user
     * @return a string describing the absolute path of the first selected file,
     *          or null if the dialog was cancelled or an error occurred
     */
    public String getFilePath(String prompt)
    {
        // Set the text
        fileDialog.setText(prompt);
        // Set filter on .txt files
        fileDialog.setFilterExtensions(fileFilter.getExtensions());
        // Put in a readable name for the filter
        fileDialog.setFilterNames(fileFilter.getNames());
        // Open Dialog and save result of selection
        return fileDialog.open();
    }
}
