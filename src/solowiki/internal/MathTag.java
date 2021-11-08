/*
 * MathTag.java
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

import be.ugent.caagt.jmathtex.JMathTeXException;
import java.io.IOException;
import info.bliki.wiki.tags.NowikiTag;
import info.bliki.wiki.filter.ITextConverter;
import info.bliki.wiki.model.IWikiModel;

/**
 * This class replaces bliki MathTag in order to generate
 * local image files.
 * @author Giuseppe Profiti
 */
public class MathTag extends NowikiTag {

    /**
     * Image generator.
     */
    ITeX2Image tex2image;
    boolean absolute;

    /**
     * Constructor.
     * @param texManager the image generator
     */
    public MathTag(ITeX2Image texManager, boolean abs) {
        super("math");
        tex2image = texManager;
        absolute=abs;
    }

    @Override
    public void renderHTML(ITextConverter converter, Appendable writer, IWikiModel model) throws IOException {
        // gets the tag content
        String content = getBodyString();
        if (content != null && content.length() > 0) {
            try {
                String filename = tex2image.convert(content);
                //TODO verify if the working directory can be used here
                writer.append("<p><img src=\"");
                if (absolute) {
                    writer.append("file:///");
                } else
                {
                    filename = tex2image.getManager().getRelativePath(filename);
                }
                copyMathLTGT(filename, writer);
                writer.append("\"></p>");
            } catch (JMathTeXException e) {
                writer.append("<p><b>Error in math formula:</b> "+e.getMessage()+"</p>");
            }
        }
    }

    @Override
    public boolean isReduceTokenStack() {
        //TODO check this value
        return true;
    }
}
