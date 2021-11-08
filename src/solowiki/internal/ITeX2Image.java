/*
 * ITeX2Image.java
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

/**
 * Interface for classes used to save to file an image
 * generated from a TeX string.
 * @author Giuseppe Profiti
 */
public interface ITeX2Image {
    /**
     * Generates a file from a TeX string.
     * @param text the TeX formula
     * @return the filename
     */
    public abstract String convert(String text) throws java.io.IOException;
    public abstract FileManager getManager();
}
