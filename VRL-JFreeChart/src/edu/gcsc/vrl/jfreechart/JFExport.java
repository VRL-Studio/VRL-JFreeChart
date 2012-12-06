/* 
 * JFExport.java
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

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.DefaultFontMapper;

import java.awt.geom.Rectangle2D;

//import pflp.util.epsgraphics.EpsGraphics2D;
import net.sf.epsgraphics.*;

import org.w3c.dom.*;
//import org.w3c.dom.svg.*;

import org.apache.batik.dom.*;
import org.apache.batik.dom.svg.*;
import org.apache.batik.svggen.*;
import org.apache.batik.ext.awt.*;

/** Class with functions to export a JFreeChart object to various output formats including jpg, png, pdf, svg, eps.
 */
public class JFExport {

    /** 
     * Export jfreechart to image format. File must have an extension like
     * jpg, png, pdf, svg, eps. Otherwise export will fail.
     * @param file Destination Filedescriptor
     * @param chart JFreechart
     * @throws Exception
     */
    public void export(File file, JFreeChart chart) throws Exception {

        /*Get extension from file - File must have one extension of
        jpg,png,pdf,svg,eps. Otherwise the export will fail.
         */
        String ext = JFUtils.getExtension(file);


        //  TODO - Make x,y variable
        int x, y;
        // Set size for image (jpg)
        x = 550;
        y = 470;

        int found = 0;

        try {

            // JPEG
            if (ext.equalsIgnoreCase("jpg")) {
                ChartUtilities.saveChartAsJPEG(file, chart, x, y);
                found++;
            }
            // PNG
            if (ext.equalsIgnoreCase("png")) {
                ChartUtilities.saveChartAsPNG(file, chart, x, y);
                found++;
            }

            // PDF
            if (ext.equalsIgnoreCase("pdf")) {

                //JRAbstractRenderer jfcRenderer = new JFreeChartRenderer(chart);

                // Use here size of r2d2 (see below, Rectangel replaced by Rectangle2D !)

                Rectangle pagesize = new Rectangle(x, y);

                Document document = new Document(pagesize, 50, 50, 50, 50);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                PdfContentByte cb = writer.getDirectContent();
                PdfTemplate tp = cb.createTemplate(x, y);

                Graphics2D g2 = tp.createGraphics(x, y, new DefaultFontMapper());

                // Draw doesn't works with Rectangle argument - use rectangle2D instead !
                //chart.draw(g2, (java.awt.geom.Rectangle2D) new Rectangle(x,y));
                Rectangle2D r2d2 = new Rectangle2D.Double(0, 0, x, y);
                chart.draw(g2, r2d2);

                g2.dispose();
                cb.addTemplate(tp, 0, 0);
                document.close();

                found++;

            }

            // SVG
            if (ext.equalsIgnoreCase("svg")) {
// When exporting to SVG don't forget this VERY important line:
// svgGenerator.setSVGCanvasSize(new Dimension(width, height));
// Otherwise renderers will not know the size of the image. It will be drawn to the correct rectangle, but the image will not have a correct default siz
                // Get a DOMImplementation
                DOMImplementation domImpl =
                        SVGDOMImplementation.getDOMImplementation();
                org.w3c.dom.Document document = domImpl.createDocument(null, "svg", null);
                SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
                //chart.draw(svgGenerator,new Rectangle(x,y));
                chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, x, y));

                boolean useCSS = true; // we want to use CSS style attribute

                Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                svgGenerator.stream(out, useCSS);
                out.close();
                found++;
            }

            if (ext.equalsIgnoreCase("eps")) {

                //Graphics2D g = new EpsGraphics2D();
                FileOutputStream out = new FileOutputStream(file);
                //Writer out=new FileWriter(file);
                Graphics2D g = new EpsGraphics(file.getName(), out, 0, 0, x, y, ColorMode.COLOR_RGB);

                chart.draw(g, new Rectangle2D.Double(0, 0, x, y));
                //Writer out=new FileWriter(file);
                out.write(g.toString().getBytes());
                out.close();
                found++;
            }

            if (found == 0) {
                JOptionPane.showMessageDialog(null, "Target file type not recognized !", "Error !", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Target file type not recognized !", "Error !", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    /**
     * Show dialog for exporting JFreechart object.
     */
    public void openExportDialog(JFreeChart jfreechart) {

        JFileChooser chooser = new JFileChooser();
        String path0;
        File file;

        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Files", "jpg"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files", "png"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("EPS Files", "eps"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("SVG Files", "svg"));

        chooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));


        int returnVal = chooser.showSaveDialog(null);
        String fd = chooser.getFileFilter().getDescription();

        // Get selected FileFilter 
        String filter_extension = null;
        if (fd.equals("PNG Files")) {
            filter_extension = "png";
        }
        if (fd.equals("SVG Files")) {
            filter_extension = "svg";
        }
        if (fd.equals("JPEG Files")) {
            filter_extension = "jpg";
        }
        if (fd.equals("PDF Files")) {
            filter_extension = "pdf";
        }
        if (fd.equals("EPS Files")) {
            filter_extension = "eps";
        }

        // Cancel
        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return;
        }

        // OK
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            path0 = chooser.getSelectedFile().getAbsolutePath();
            //System.out.println(path0);
            try {
                file = new File(path0);
                // Get extension (if any)
                String ext = JFUtils.getExtension(file);
                // No extension -> use selected Filter
                if (ext == null) {
                    file = new File(path0 + "." + filter_extension);
                    ext = filter_extension;
                }

                // File exists - overwrite ?
                if (file.exists()) {
                    Object[] options = {"OK", "CANCEL"};
                    int answer = JOptionPane.showOptionDialog(null, "File exists - Overwrite ?",
                            "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    if (answer != JOptionPane.YES_OPTION) {
                        return;
                    }
                    //System.out.println(answer+"");
                }

                // Export file
                export(file, jfreechart);
            } catch (Exception f) {
                // I/O - Error
            }
        }
    }
}