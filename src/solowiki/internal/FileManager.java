/*
 * FileManager.java
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Giuseppe Profiti
 */
public class FileManager {

    private String texImagesDir;
    Vector<File> texImages; //TODO move images from mainwindot to here
    private String cwd;
    Map<String, File> images;

    public FileManager(String img) {
        this("", img);
    }

    public FileManager(String base, String img) {
        cwd = base;
        texImagesDir = img;
        images = new HashMap<String, File>();
    }

    /**
     * @return the texImagesDir
     */
    public String getTexImagesDir() {
        return texImagesDir;
    }

    /**
     * @param texImagesDir the texImagesDir to set
     */
    public void setTexImagesDir(String basePath) {
        texImagesDir = basePath;
    }

    public String getFullPath(String filename) {
        File f = images.get(filename);
        if (f == null) {
            return cwd+File.separator+filename;
        } else {
            return f.getPath();
        }
    }

    public String getRelativePath(String filename) {
        File f = images.get(filename);
        if (f == null) {
            return filename;
        } else {
            //TODO may use canonical form, but it requires exception handling
            String filepath = f.getPath();
            if (filepath.startsWith(cwd)) //TODO this considers only subdirectories, not relative paths like ../image.jpg
            {
                return filepath.substring(cwd.length());
            } else {
                return "/"+filepath;
            }
        }
        //return filename.substring(texImagesDir.length());
    }

    /**
     * @return the cwd
     */
    public String getCwd() {
        return cwd;
    }

    /**
     * @param cwd the cwd to set
     */
    public void setCwd(String cwd) {
        this.cwd = cwd;
    }

    public void setCwd(File f) {
        if (f != null) {
            String filepath = getPath(f);
            cwd = filepath.substring(0, filepath.lastIndexOf(f.getName()));
        }
    }

    public void addImage(File f) {
        if (f != null) {
            images.put(f.getName(), f);
        }
    }

    /**
     * @deprecated
     * @param names
     */
    public void checkImageSet(Set<String> names) {
        Set<String> toBeRemoved = images.keySet();
        if (toBeRemoved.removeAll(names)) {
            //TODO to be implemented: must consider a remove followed by an undo
        }
    }

    /**
     * Returns the path in a form used by all the classes to enforce consistency.
     * @param f the file
     * @return the file path
     */
    public static String getPath(File f) {
        String path = f.getPath();
        return path;
    }
}
