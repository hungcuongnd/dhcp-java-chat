/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * A file converter
 *
 * @author Tran Cuong
 */
public final class FileConverter {

    /**
     * Convert a file to Base64 string
     *
     * @param url a path of a file what converted to string
     * @return a string
     */
    public static String fileToString(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        Path path = Paths.get(url);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Convert a string to ImageIcon (swing)
     *
     * @param encode a string encoded from a file
     * @return a ImageIcon
     */
    public static ImageIcon stringToImage(String encode) {
        if (encode == null || encode.isEmpty()) {
            return null;
        }
        
        byte[] decode = Base64.getDecoder().decode(encode);
        return new ImageIcon(decode);
    }
}
