/* 
 * JFUtils.java
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2012 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2012 by Michael Hoffer
 * 
 * This file is part of Visual Reflection Library (VRL).
 *
 * VRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
 * 
 * Second, the copyright notice must remain. It must be reproduced in any
 * program that uses VRL.
 *
 * Third, add an additional notice, stating that you modified VRL. In addition
 * you must cite the publications listed below. A suitable notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Publications:
 *
 * M. Hoffer, C.Poliwoda, G.Wittum. Visual Reflection Library -
 * A Framework for Declarative GUI Programming on the Java Platform.
 * Computing and Visualization in Science, 2011, in press.
 */

package edu.gcsc.vrl.jfreechart;


import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.io.File;
import org.jfree.data.xy.XYSeries;

/** Utility functions for JFileChooser, XYSeries and Shape creation.
 */

public class JFUtils {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

/**
 * Get the extension of a file.
 * @param f
 * @return The extension
 */
public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
}


/**
  * Trim string to given length. Append spaces if too short.
  * @param string 
  * @param length
  * @return
  */
public static String trimString(String string,int length) {
    	if (string.length() > length) {
    	string = string.substring(0,length);
    	}
    	else 
    	{
    	while (string.length() < length) {
    		string = string+" ";
    	}
    	}
    	return string;
}
    
    
/**
  * Create a triangle pointing upwards.
  * @param size Side length
  * @return The triangle shape.
  */
public static Shape createUpTriangle(int size) {
        final GeneralPath p0 = new GeneralPath();

        p0.moveTo(0.0f, -size);
        p0.lineTo(size, size);
        p0.lineTo(-size, size);
        p0.closePath();
        return p0;
}


/**
  * Create a triangle pointing downwards.
  * @param size Side length
  * @return The triangle shape.
  */

public static Shape createDownTriangle(int size) {
        final GeneralPath p0 = new GeneralPath();

        p0.moveTo(0.0f, size);
        p0.lineTo(size, -size);
        p0.lineTo(-size, -size);
        p0.closePath();
        return p0;
    }

/**
 * Create a diamond shape.
 * @param size Side length
 * @return The diamond shape.
 */
public static Shape createDiamond(int size) {
        final GeneralPath p0 = new GeneralPath();

        p0.moveTo(0.0f, -size);
        p0.lineTo(size, 0.0f);
        p0.lineTo(0.0f, size);
        p0.lineTo(-size, 0.0f);
        p0.closePath();
        return p0;
    }
 
/**
 * Create a XYSeries from y values only.
 * @param y_values y-values
 * @param id Description
 * @param stepsize_x Stepsize for x-values
 * @return Return a new jfreechart XYSeries.
 * 
 */
public static XYSeries createXYSeries(Double [] y_values, String id, Double stepsize_x) {
	
	Double x = 0.0;
	
	// id is of (java.lang.Comparable key) 
	XYSeries temp = new XYSeries(id);
	
	// Generate x values and add (x,y) pairs to XYSeries
	for (int i = 0; i < y_values.length;i++) {
	temp.add(x,y_values[i]);
	x += stepsize_x;
	//System.out.println("x: "+x+" yval: "+y_values[i]);
	}
	// Return series
	return temp;
}
  
/**
 * Demo method shows how to create a XYSeries.
 * @return The created XYSeries
 */
public static XYSeries createDemoXYSeries() {

    // Create a XYSeries
    final XYSeries series1 = new XYSeries("Demo Series");
    series1.add(10.0, 0.323533);
    series1.add(20.0, 0.137344);
    series1.add(30.0, 0.145253);
    series1.add(40.0, 0.439843);
    series1.add(50.0, 0.129994);
    series1.add(60.0, 0.142743);
    series1.add(70.0, 0.559435);
    series1.add(80.0, 0.148453);
    series1.add(90.0, 0.646454);
    series1.add(100.0, 0.162346);
    series1.add(110.0, 0.772323);
    series1.add(120.0, 0.842322);
    series1.add(130.0, 0.931022);
    series1.add(140.0, 0.942302);
    series1.add(150.0, 0.912352);

return series1;
}
}
