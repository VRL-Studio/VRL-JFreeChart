/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcsc.vrl.jfreechart;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ObjectInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import java.io.Serializable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author ablaumeiser
 */
@ComponentInfo(name = "MyPlotter", category = "JFreeChart")
@ObjectInfo(name = "MyPlotter")
public class MyPlotter implements Serializable {
    private static final long serialVersionUID = 1L;

    public JFreeChart plot(
            @ParamInfo(name = "Data") HistogramData dataset) {

        boolean urls = false;
        JFreeChart chart = ChartFactory.createHistogram(
                dataset.getPlotTitle(),
                dataset.getxAxisLabel(), dataset.getyAxisLabel(),
                dataset.getData(), dataset.getPlotOrientation(),
                dataset.isLegendVisible(), dataset.isTooltipsVisible(), urls);

        return chart;
    }
}

