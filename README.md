# Control Factory

Eclipse plugin SWT widget factory

For information on Control Factory, go to our [support site] (www.cybersearch2.com.au/eclipse).

This plugin is a spin off from the development of the Cybertete Rich Client Platform (RCP) Instant Messaging Client
which is a research project into how to utilize the latest E4 technologies. The plugin facilitates unit testing
where code uses the Standard Widget Toolkit (SWT) - the Eclipse portable Graphics library. 
Testing such code is facilitated by using a SWT factory class so the Graphics libary classes can be readily mocked. 
Otherwise, most, if not all of the issues that hinder mocking, such as the use of final classes and final methods
have to be worked around.

The Control Factory is designed for use in Cybertete rather than as a general purpose SWT factory class.
The Open Source project has been made available so it can be adapted to meet particular needs. It's usefulness
is demonstrated in the Cybertete project, where greater than 80% code coverage in unit tests is achieved despite the
predominance in the application of dialogs and views based on SWT. 

Control Factory features

 *   A single SWT factory class suitable for dependency injection 
 *   Avoids static Graphics Library calls which impede unit testing
 *   Includes image factory with resource manager
 *   Includes dialog customisation where controls are separately testable
 *   Open Source GPLv3 license
 *   Project automation with Maven and Tycho
 *   Supports development with Eclipse
 *   StatusBar project provides usage example
  

