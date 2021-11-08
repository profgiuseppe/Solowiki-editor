/*
 * TeX2Image.java
 *
 *  Copyright (C) 2010 Giuseppe Profiti
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 */

package solowiki.internal;

import be.ugent.caagt.jmathtex.TeXConstants;
import be.ugent.caagt.jmathtex.TeXFormula;
import be.ugent.caagt.jmathtex.TeXIcon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Vector;

/**
 * Helper class used to generate png images from TeX strings.
 * Uses jMathTeX library.
 * @author Giuseppe Profiti
 */
public class TeX2Image implements ITeX2Image {

    /**
     * Prefix used for file names.
     */
    private String prefix;
    /**
     * Destination folder for generated images.
     */
    private FileManager folder;
    /**
     * Image sequence number.
     */
    int seqnum = 0;

    Vector<File> files; //TODO it's used as reference to external vector, move it to filemanager

    public TeX2Image(String prefix, FileManager folder) {
        this.prefix = prefix;
        this.folder = folder;
        files = null;
    }

    public TeX2Image(FileManager folder) {
        this("formula_", folder);
    }

    public void setFilenamesV(Vector<File> v) {
        files = v;
    }

    @Override
    public String convert(String text) throws IOException {
        TeXFormula formula = new TeXFormula(text);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18.0f);

        java.awt.Label l = new java.awt.Label();
        l.setForeground(Color.black);

        BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight()+5, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = bi.createGraphics();
        icon.paintIcon(l, g, 0, 3);

        String filename = folder.getTexImagesDir() + prefix + "_" + seqnum + ".png";
        File imgdir = new File(folder.getTexImagesDir());
        imgdir.mkdir();
        // can't use File.createTmpFile, this method can be used for both temp and save
        File file = new java.io.File(filename);
        ImageIO.write(bi, "png", file);
        seqnum++;
        if (files!=null)
            files.add(file);
        return filename;
    }

    public FileManager getManager() {
        return folder;
    }
}
