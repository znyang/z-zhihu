package com.zen.android.zz.sdk;

import org.codehaus.plexus.util.IOUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zen yang - 2016/12/11
 */
public class FileUtils {

    public static String getFileContent(String path) throws IOException {
        InputStream is = FileUtils.class.getClassLoader()
                .getResourceAsStream(path);
        if (is != null) {
            return IOUtil.toString(is, "UTF-8");
        }
        return null;
    }
}
