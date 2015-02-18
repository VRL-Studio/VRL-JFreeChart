/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcsc.vrl.jfreechart;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.JFreeChart;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name = "JFreeChartToSVG", category = "JFreeChart")
public class JFreeChartExporter implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public void save(
            @ParamInfo(name = "", style = "save-dialog",
                    options = "endings=[\"svg\",\"pdf\",\"png\",\"eps\"];description=\"Image files (svg, pdf, png, eps)\"") File file,
            @ParamInfo(name = "JFreeChart", style = "default", options = "") JFreeChart chart) throws Exception {
        JFExport exporter = new JFExport();

        exporter.export(file, chart);

    }
}
