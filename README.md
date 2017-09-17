# Table of Contents
1. [Description](#description)
2. [The Library](#the-library)
   1. [Logger](#logger)
   2. [Data-structure](#data-structure)
       1. [AVL Tree](#avl-tree)
       2. [ObjectTable](#objecttable)
6. [License](#license)

# Description

[![Build Status](https://travis-ci.org/An7ar35/eadjlib.svg?branch=master)](https://travis-ci.org/An7ar35/eadjlib)

Personal Java library or reusable components.


# The Library


___
## Logger

`eadjlib.logger`

Customisable logging system with different formatting and output options.

##### Important note

It is recommended to write a ```toString()``` method to all classes. 
This is what the logger will output when you pass it instances of those
classes.

##### Interface:

    void log( Object... objects ); //Standard message

    void log_Fatal( Object... objects ); //Fatal event

    void log_Error( Object... objects ); //Error

    void log_Warning( Object... objects ); //Warning

    void log_Debug( Object... objects ); //Debug

    void log_Trace( Object... objects ); //For tracing

    void log_Exception( Exception e ); //For pushing exception to the log after an error

#### Usage

##### Logging messages:

```Logger log = Logger.getLoggerInstance( Myclass.class.getName() );```

```log.log( "Testing ", 1, ", " , 2, ", ", 3, ", and some more..." );```

##### Logging an exception:

When catching an exception and logging it is required the 
"log_Exception" call can be used to add the stacktrace to the log after 
a previous message detailing the event. 

e.g.:

    try {
        /** some stuff that raises an exception **/ 
    } catch ( Exception e ) { 
        log.log_error( "An exception was raise because..." ),
        log.log_Exception( e )
    }

##### Configuration

The `log_config.cfg` file is created during the first run of the logger 
if it isn't there already. Inside you can set your options for the logging 
like the log level and outputs you want to see the log messages in. 

##### GUI Viewer

To create a viewer for the log:

 1. Add `OUTPUT=<WINDOW,window_name>` to the log's config file,
 2. Create a class that implements `Log_Window_Interface` to pass the messages 
    from the log to your viewer,
 3. Call `connectView( Log_Window_Interface )` with your implemented class as an argument 
    to connect the logger to the class. 

An example implementation of a GUI log viewer using JavaFX is available [here](https://github.com/An7ar35/guiLoggerView).

## Data-structure

#### AVL Tree

`AVLTree<K,V>`

Balanced binary tree where the heights of every node differ at most by +/- 1.

#### ObjectTable

`ObjectTabe`

Object table for caching/transporting SQL result sets. Elemtents are added 
in sequential order left-to-right, row-by-row from the top.
## License

This software is released under the GNU General Public License 2 license.

Please reference when used in project and/or research and/or papers and/or integrated in production code (i.e.: DBAD).

Copyright E. A. Davison 2016-17.