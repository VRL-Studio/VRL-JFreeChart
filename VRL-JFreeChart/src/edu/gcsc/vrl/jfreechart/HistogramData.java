/* 
 * HistogramData.java
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

import eu.mihosoft.vrl.annotation.ObjectInfo;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
@ObjectInfo(serializeParam=false)
public class HistogramData {
    private transient IntervalXYDataset data;
    private PlotOrientation plotOrientation = PlotOrientation.VERTICAL;
    private boolean legendVisible;
    private boolean tooltipsVisible;
    private String plotTitle;
    private String xAxisLabel;
    private String yAxisLabel;

    public HistogramData() {
    }

    public HistogramData(IntervalXYDataset data,
            PlotOrientation plotOrientation,
            boolean legendVisible,
            boolean tooltipsVisible,
            String plotTitle,
            String xAxisLabel,
            String yAxisLabel) {
        this.data = data;
        this.plotOrientation = plotOrientation;
        this.legendVisible = legendVisible;
        this.tooltipsVisible = tooltipsVisible;
        this.plotTitle = plotTitle;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    /**
     * @return the data
     */
    public IntervalXYDataset getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(IntervalXYDataset data) {
        this.data = data;
    }

    /**
     * @return the plotOrientation
     */
    public PlotOrientation getPlotOrientation() {
        return plotOrientation;
    }

    /**
     * @param plotOrientation the plotOrientation to set
     */
    public void setPlotOrientation(PlotOrientation plotOrientation) {
        this.plotOrientation = plotOrientation;
    }

    /**
     * @return the legendVisible
     */
    public boolean isLegendVisible() {
        return legendVisible;
    }

    /**
     * @param legendVisible the legendVisible to set
     */
    public void setLegendVisible(boolean legendVisible) {
        this.legendVisible = legendVisible;
    }

    /**
     * @return the tooltipsVisible
     */
    public boolean isTooltipsVisible() {
        return tooltipsVisible;
    }

    /**
     * @param tooltipsVisible the tooltipsVisible to set
     */
    public void setTooltipsVisible(boolean tooltipsVisible) {
        this.tooltipsVisible = tooltipsVisible;
    }

    /**
     * @return the plotTitle
     */
    public String getPlotTitle() {
        return plotTitle;
    }

    /**
     * @param plotTitle the plotTitle to set
     */
    public void setPlotTitle(String plotTitle) {
        this.plotTitle = plotTitle;
    }

    /**
     * @return the xAxisLAbel
     */
    public String getxAxisLabel() {
        return xAxisLabel;
    }

    /**
     * @param xAxisLAbel the xAxisLAbel to set
     */
    public void setxAxisLabel(String xAxisLAbel) {
        this.xAxisLabel = xAxisLAbel;
    }

    /**
     * @return the yAxisLAbel
     */
    public String getyAxisLabel() {
        return yAxisLabel;
    }

    /**
     * @param yAxisLAbel the yAxisLAbel to set
     */
    public void setyAxisLabel(String yAxisLAbel) {
        this.yAxisLabel = yAxisLAbel;
    }
    
    
}
