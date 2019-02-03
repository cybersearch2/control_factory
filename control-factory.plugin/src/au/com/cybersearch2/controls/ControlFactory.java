package au.com.cybersearch2.controls;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import au.com.cybersearch2.statusbar.controls.StatusItemLayoutData;


/**
 * ControlFactory
 * SWT widget factory and widget support.
 * Isolates aspects of SWT which are not easy to test with Mockito, particularly Widget object construction.
 * Also provides the shell for dialogs which are to be framed by the main window.
 * Note that compatibility for use with StatusBar plugin is ensured by implementing StatusBarControlFactory interface.
 * @author Andrew Bowley
 * 2 Mar 2016
 */
public class ControlFactory
{
    /** Default shell. Before application startup is complete, the shell centers dialogs in the primary monitor. 
     * Afterwards, dialogs are framed by the main window. */
    Shell shell;
    
    /**
     * postConstruct()
     * @param platformTools Provides shell prior to application start
     * @param eventBroker Event broker service
     * @param workbenchContext Context provided by Eclise for dependency injection
      */
    @PostConstruct
    void postConstruct(PlatformTools platformTools, IEventBroker eventBroker, final IEclipseContext workbenchContext)
    {   // Create shell for splash screen 
        shell = platformTools.getShellInstance();
        // Change shell when application startup is complete
        eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new EventHandler(){

            @Override
            public void handleEvent(Event event)
            {
                shell = workbenchContext.getActive(Shell.class);
            }});
    }

    /**
     * Returns custom dialog with content provided by given custom controls object
     * @param activeShell Currently active shell or null if default shell to be used
     * @param customControls Custom controls of specified generic type
     * @param title Dialog title
     * @return CustomDialog object
     */
    public <T extends CustomControls> CustomDialog<T> customDialogInstance(Shell activeShell, T customControls, String title)
    {
        if (activeShell == null)
            activeShell = shell;
        return new CustomDialog<T>(activeShell, title, customControls);
    }
        
    /**
     * Returns custom dialog located in default shell with content provided by given custom controls object
     * @param customControls Custom controls of specified generic type
     * @param title Dialog title
     * @return CustomDialog object
     */
    public <T extends CustomControls> CustomDialog<T> customDialogInstance(T customControls, String title)
    {
        return customDialogInstance(shell, customControls, title);
    }

    /**
     * Returns A modal dialog that displays progress during a long running operation. 
     * It is located in the default shell.
     * @return ProgressMonitorDialog object
     */
    public ProgressMonitorDialog  progressMonitorDialogInstance()
    {   
        return new ProgressMonitorDialog(shell);
    }

    /**
     * Returns a control which is capable of containing other controls
     * @param parent Parent composite
     */
    public Composite compositeInstance(Composite parent)
    {
        return new Composite(parent, SWT.NONE);
    }

    /**
     * Returns combo control
     * Instances of this class are controls that allow the user
     * to choose an item from a list of items, or optionally 
     * enter a new value by typing it into an editable text
     * field. Often, <code>Combo</code>s are used in the same place
     * where a single selection <code>List</code> widget could
     * be used but space is limited. A <code>Combo</code> takes
     * less space than a <code>List</code> widget and shows
     * similar information.
     * <p><dl>
     * <dt><b>Styles:</b></dt>
     * <dd>DROP_DOWN, READ_ONLY, SIMPLE</dd>
     * <dt><b>Events:</b></dt>
     * <dd>DefaultSelection, Modify, Selection, Verify, OrientationChange</dd>
     * </dl>
     * <p>
     * Note: Only one of the styles DROP_DOWN and SIMPLE may be specified.
     * </p>
     * @param composite Parent composite
     * @param style Style
     * @return Combo object
     */
    public Combo comboInstance(Composite composite, int style)
    {
        return new Combo(composite, style);
    }

    /**
     * Returns text control
     * Instances of this class are selectable user interface
     * objects that allow the user to enter and modify text.
     * Text controls can be either single or multi-line.
     * When a text control is created with a border, the
     * operating system includes a platform specific inset
     * around the contents of the control.  When created
     * without a border, an effort is made to remove the
     * inset such that the preferred size of the control
     * is the same size as the contents.
     * <p>
     * <dl>
     * <dt><b>Styles:</b></dt>
     * <dd>CENTER, ICON_CANCEL, ICON_SEARCH, LEFT, MULTI, PASSWORD, SEARCH, SINGLE, RIGHT, READ_ONLY, WRAP</dd>
     * <dt><b>Events:</b></dt>
     * <dd>DefaultSelection, Modify, Verify, OrientationChange</dd>
     * </dl></p>
     * @param composite Parent composite
     * @param style Style
     * @return Text object
     */
    public Text textInstance(Composite composite, int style)
    {
        return new Text(composite, style);
    }

    /**
     * Returns label control
     * Instances of this class represent a non-selectable
     * user interface object that displays a string or image.
     * <p><dl>
     * <dt><b>Styles:</b></dt>
     * <dd>SEPARATOR, HORIZONTAL, VERTICAL</dd>
     * <dd>SHADOW_IN, SHADOW_OUT, SHADOW_NONE</dd>
     * <dd>CENTER, LEFT, RIGHT, WRAP</dd>
     * </dl></p>
     * @param composite Parent composite
     * @param style Style
     * @return Label object
     */
    public Label labelInstance(final Composite composite, int style)
    {
        return  new Label(composite, style);
    }

    /**
     * Returns a label control for use as a separator
     * @param composite Parent composite
     */
    public Label separatorInstance(Composite composite)
    {
        return new Label(composite, SWT.SEPARATOR);
    }

    /**
     * Returns button control
     * Instances of this class represent a selectable user interface object that
     * issues notification when pressed and released. 
     * <p><dl>
     * <dt><b>Styles:</b></dt>
     * <dd>ARROW, CHECK, PUSH, RADIO, TOGGLE, FLAT, WRAP</dd>
     * <dd>UP, DOWN, LEFT, RIGHT, CENTER</dd>
     * <dt><b>Events:</b></dt>
     * <dd>Selection</dd>
     * </dl></p>
     * @param composite Parent composite
     * @param style Style
     * @return Button object
     */
    public Button buttonInstance(Composite composite, int style)
    {
        return new Button(composite, style);
    }

    /**
     * Returns a buttom bar component for use in a view. Based on Dialog button bar implementation, 
     * ButtonBar returns a ButtonControl instance when createButton() is called. 
     * @param composite Parent composite
     * @return ButtonBar instance
     */
    public ButtonBar buttonBarInstance(Composite composite)
    {
        return new ButtonBar(this, composite);
    }

    /**
     * Returns a dialog to select a file.  
     * @return FileDialog object
     */
    public FileDialog fileDialogInstance()
    {
        return new FileDialog(shell);
    }
 
    /**
     * Returns a List control.
     * Instances of this class represent a selectable user interface
     * object that displays a list of strings and issues notification
     * when a string is selected.  A list may be single or multi select.
     * <p><dl>
     * <dt><b>Styles:</b></dt>
     * <dd>SINGLE, MULTI</dd>
     * <dt><b>Events:</b></dt>
     * <dd>Selection, DefaultSelection</dd>
     * </dl></p>
     * @param parent a composite control which will be the parent of the new instance (cannot be null)
     * @param style the style of control to construct
     */
    public List listInstance(Composite parent, int style)
    {
        return new List(parent, style);
    }
 
    /**
     * Returns a Group control
     * Instances of this class provide an etched border with an optional title.
     * <p><dl>
     * <dt><b>Styles:</b></dt>
     * <dd>SHADOW_ETCHED_IN, SHADOW_ETCHED_OUT, SHADOW_IN, SHADOW_OUT, SHADOW_NONE</dd>
     * </dl></p>
     * @param composite Parent composite
     * @param style Style
     */
    public Group groupInstance(Composite composite, int style)
    {
        return new Group(composite, style);
    }

    /**
     * Returns a label control which supports aligned text and/or an image and different border styles.
     * <p>
     * If there is not enough space a CLabel uses the following strategy to fit the 
     * information into the available space:
     * <pre>
     *      ignores the indent in left align mode
     *      ignores the image and the gap
     *      shortens the text by replacing the center portion of the label with an ellipsis
     *      shortens the text by removing the center portion of the label
     * </pre>
     * </p><p><dl>
     * <dt><b>Styles:</b>
     * <dd>LEFT, RIGHT, CENTER, SHADOW_IN, SHADOW_OUT, SHADOW_NONE</dd>
     * </dl></p>
     * @param composite Parent composite
     * @param specification Custom Label specification
     */
    public CLabel customLabelInstance(Composite composite, CustomLabelSpec specification)
    {
        CLabel label = new CLabel(composite, SWT.SHADOW_NONE);
        label.setLayoutData(new StatusItemLayoutData(label, specification));
        label.setText(specification.getText());
        label.setImage(specification.getImage());
        return label;
    }

    /**
     * Returns status item layout data - hint width and height
     * @param label Status item Custom Label 
     * @param specification Custom Label specification
     * @return
     */
    public StatusItemLayoutData labelLayoutDataInstance(
            CLabel label, CustomLabelSpec specification)
    {
        return new StatusItemLayoutData(label, specification);
    }

    /**
     * Returns tool tip object bound to specified parent's shell
     * @param parent Parent composite
     * @return ToolTip object
     */
    public ToolTip toolTipInstance(Composite parent)
    {   // Note: ControlFactory's shell is not valid for ToolTip parameter 
        return new ToolTip(parent.getShell(), SWT.BALLOON);
    }

    /**
     * Returns a detached menu.
     * Constructs a new instance of this class given its parent,
     * and sets the style for the instance so that the instance
     * will be a popup menu on the given parent's shell.
     * <p>
     * After constructing a menu, it can be set into its parent
     * using <code>parent.setMenu(menu)</code>.  In this case, the parent may
     * be any control in the same widget tree as the parent.
     * </p>
     * @param parent A control which will be the parent of the new instance (cannot be null)
     */
    public Menu menuInstance(Control parent) 
    {
        return new Menu(parent);
    }
    
    /**
     * Returns a menu item.
     * Constructs a new instance of MenuItem given its parent
     * (which must be a <code>Menu</code>) and a style value
     * describing its behavior and appearance. The item is added
     * to the end of the items maintained by its parent.
     * <p>
     * @param parent a menu control which will be the parent of the new instance (cannot be null)
     * @param style the style of control to construct
     */
    public MenuItem menuItemInstance(Menu parent, int style)
    {
        return new MenuItem(parent, style);
    }

    /**
     * Return a tree viewer.
     * Creates a tree viewer on a newly-created tree control under the given
     * parent. The tree control is created using the given SWT style bits. The
     * viewer has no input, no content provider, a default label provider, no
     * sorter, and no filters.
     * @param parent Parent control
     * @param style  SWT style bits used to create the tree.
     */
    public TreeViewer treeViewerInstance(Composite parent, int style) 
    {
        return new TreeViewer(parent, style);
    }

    /**
     * Returns the JFace's dialog font.
     * @return Font object
     */
    public Font getDialogFont()
    {
        return JFaceResources.getDialogFont();
    }

    /**
     * Returns dialog parameters converted to pixels.
     * @param parent Parent composite
    */
    public DialogPixels getDialogPixels(Composite parent)
    {
        return new DialogPixels(getFontMetrics(parent));
    }
 
    /**
     * Returns a FontMetrics which contains information about the font currently 
     * being used by the receiver to draw and measure text.
     * @param parent Parent composite
     * @return font metrics for the receiver's font
     */
    public FontMetrics getFontMetrics(Composite parent)
    {
        GC gc = new GC(parent);
        gc.setFont(parent.getFont());
        FontMetrics fontMetrics = gc.getFontMetrics();
        gc.dispose();
        return fontMetrics;
    }
 
    /**
     * Returns wrapped default layout factory.
     * @return DefaultLayout object
     */
    public DefaultLayout getDefaultLayout()
    {
        return new DefaultLayoutImpl();
    }
    
    /**
     * Returns wrapped default grid data factory.
     * @return DefaultGridData object
     */
    public DefaultGridData getDefaultGridData()
    {
        return new DefaultGridDataImpl();
    }

    /**
     * Returns the number of pixels corresponding to the given number of horizontal dialog units.
     * @param fontMetrics Used in performing the conversion
     * @param dlus The number of horizontal dialog units
     * @return the number of pixels
     */
    public int convertHorizontalDLUsToPixels(FontMetrics fontMetrics, int dlus) 
    {
        // round to the nearest pixel
        return Dialog.convertHorizontalDLUsToPixels(fontMetrics, dlus);
    }

    /**
     * Returns the number of pixels corresponding to the given number of vertical dialog units.
     * @param fontMetrics Used in performing the conversion
     * @param dlus The number of horizontal dialog units
     * @return the number of pixels
     */
    public int convertVerticalDLUsToPixels(FontMetrics fontMetrics, int dlus) 
    {
        // round to the nearest pixel
        return Dialog.convertVerticalDLUsToPixels(fontMetrics, dlus);
    }

    /**
     * Use font metrics to convert height is characters to pixels
     * @param parent Composite
     * @param count Character count
     * @return Number of pixels
     */
    public int convertHeightInCharsToPixels(Composite parent, int count)
    {
        return getFontMetrics(parent).getHeight() * count;
    }

}