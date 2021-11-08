/*
 * ConfigurableBundle.java
 *
 *  Copyright (C) 2008-2009 Giuseppe Profiti
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

package solowiki;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
//TODO check the new namespace class in bliki
/**
 *
 * @author Giuseppe Profiti
 */
public class ConfigurableBundle extends ResourceBundle {

        protected HashMap<String,String> values;

        public ConfigurableBundle() {
            ResourceBundle messages = null;
            values = new HashMap();
            try {
                // Try to load settings for the current locale
                messages = ResourceBundle.getBundle("Messages", Locale.getDefault());
            } catch (java.util.MissingResourceException e) {
                // if the locale is missing, use english settings
                messages = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }
            
            Enumeration<String> keys = messages.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                values.put(key, messages.getString(key));
            }
        }

        @Override
        protected Object handleGetObject(String key) {
            return values.get(key);
        }

        @Override
        public Enumeration<String> getKeys() {
            return java.util.Collections.enumeration(values.keySet());
        }

        public Vector<Vector<String>> getData() {
            Vector<Vector<String>> res = new Vector(values.size());
            for (java.util.Map.Entry<String,String> entry : values.entrySet()) {
                Vector<String> sub = new Vector(2);
                sub.add(entry.getKey());
                sub.add(entry.getValue());
                res.add(sub);
            }
            return res;
        }

        public void setValue(String key, String value) {
            values.put(key, value);
        }

        public void setValues(java.util.Properties properties) {
            values = new HashMap(properties);
        }

    }
