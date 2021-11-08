/*
 * SolowikiModel.java
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

import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.model.WikiModel;

/**
 *
 * @author Giuseppe Profiti
 */
public class SolowikiModel extends WikiModel {

    private boolean saving;
    private FileManager fileManager;

    public SolowikiModel(Configuration configuration,
            java.util.ResourceBundle resourceBundle,
            java.lang.String imageBaseURL,
            java.lang.String linkBaseURL,
            FileManager fileMan,
            boolean saving) {
        super(configuration, resourceBundle, imageBaseURL, linkBaseURL);
        this.saving = saving;
        fileManager = fileMan;
    }

    /**
     * Append the internal wiki image link to this model.
     *
     * <br/><br/><b>Note</b>: the pipe symbol (i.e. &quot;|&quot;) splits the
     * <code>rawImageLink</code> into different segments. The first segment is
     * used as the <code>&lt;image-name&gt;</code> and typically ends with
     * extensions like <code>.png</code>, <code>.gif</code>, <code>.jpg</code> or
     * <code>.jpeg</code>.
     *
     * @param imageNamespace
     *          the image namespace
     * @param rawImageLink
     *          the raw image link text without the surrounding
     *          <code>[[...]]</code>
     * @see info.bliki.wiki.model.WikiModel.parseInternalImageLink
     */
    @Override
    public void parseInternalImageLink(String imageNamespace, String rawImageLink) {
        if (fExternalImageBaseURL != null) {
            String imageHref = fExternalWikiBaseURL;
            String imageSrc = fExternalImageBaseURL;
            ImageFormat imageFormat = ImageFormat.getImageFormat(rawImageLink, imageNamespace);

            String imageName = imageFormat.getFilename();
            //String sizeStr = imageFormat.getWidthStr();
            if (!saving) {
                // while showing in the output pane, Java requires the full path
                imageName = "file:///" + fileManager.getFullPath(imageName);
            } else {
                // while saving to HTML, relavite path can be used
                imageName = fileManager.getRelativePath(imageName);
                imageName = imageName.replace("\\", "/");
            }
            //TODO check if it's needed before using it again, if needed change the behaviour for \ and /
            //imageName = Encoder.encodeUrl(imageName); //Uses solowiki.internal.Encoder
            if (replaceColon()) {
                imageName = imageName.replaceAll(":", "/");
            }
            String link = imageFormat.getLink(); //TODO see what this mathod may return
            if (link != null) {
                if (link.length() == 0) {
                    imageHref = "";
                } else {
                    String encodedTitle = encodeTitleToUrl(link, true);
                    imageHref = imageHref.replace("${title}", encodedTitle);
                }

            } else {
                if (replaceColon()) { //TODO see what replaceColon does
                    imageHref = imageHref.replace("${title}", '/' + imageName);
                } else {
                    imageHref = imageHref.replace("${title}", imageName);
                }
            }
            imageSrc = imageSrc.replace("${image}", imageName);

            appendInternalImageLink(imageHref, imageSrc, imageFormat);
        }
    }
}
