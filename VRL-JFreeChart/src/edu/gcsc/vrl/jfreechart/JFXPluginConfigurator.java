/* 
 * JFXPluginConfigurator.java
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

import eu.mihosoft.vrl.system.*;

public class JFXPluginConfigurator extends VPluginConfigurator implements java.io.Serializable{
private static final long serialVersionUID=1;
    public JFXPluginConfigurator() {
        // specifiy the plugin name, version
        setIdentifier(new PluginIdentifier("VRL-JFreeChart", "0.2.5.1"));

        // add dependencies
        // e.g.: addDependency(new PluginDependency("VRL-UG4", "0.1","0.2"));
        
        addDependency(new PluginDependency("VRL", "0.4.2.6", "0.4.x"));

        exportPackage("org.jfree");
        exportPackage("edu.gcsc.vrl.jfreechart");
        
        setCopyrightInfoAsPlainText("(c) 2015 Steinbeis Forschungszentrum "
                + "(STZ Ölbronn)",
                "(c) 2015 Steinbeis Forschungszentrum "
                + "(STZ Ölbronn)\n\n"
                + "Same license as VRL: LGPL v3\n"
                + "(access detailed license information via Plugins->VRL->Copyright Information)\n\n");
        
    }

    @Override
    public void register(PluginAPI papi) {
        
        VParamUtil.validate(VParamUtil.VALIDATOR_INSTANCEOF,
                VPluginAPI.class, papi);
        
        VPluginAPI vapi = (VPluginAPI) papi;
        vapi.addTypeRepresentation(TrajectoryArrayType.class);
        vapi.addTypeRepresentation(JFXPlotContainerType.class);

        // register component.
        // components must:
        // a) provide a public constructor without parameters
        // b) implement serializable interface:
        //      all fields must be serializable
        //      or marked as transient
        //      (see Java documentation for detailed instructions)
                    
        vapi.addComponent(TrajectoryPlotter.class);
        vapi.addComponent(HistogramPlotter.class);
        
        vapi.addComponent(JFreeChartExporter.class);
        
    }

    @Override
    public void unregister(PluginAPI papi) {
        // currently not necessary
    }

    @Override
    public void init(InitPluginAPI iApi) {
        // currently not necessary
    }
    
    @Override
    public void install(InitPluginAPI iApi) {
        //
    }
}

