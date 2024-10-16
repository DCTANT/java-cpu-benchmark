package com.itdct.cbench.ui;

import com.itdct.cbench.cli.language.LangType;
import com.itdct.cbench.cli.util.Language;
import com.itdct.cbench.ui.frame.MainFrame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * @author Zhouwx
 * @date 2024/10/13 22:03:28
 * @version 1.0
 * @description
 */
public class Main {
    public static void main(String[] args) {
        Language.languageType = LangType.CHINESE;

        String javaHome = System.getProperty("java.home");
        System.out.println(javaHome);
        if (javaHome == null) {
            System.setProperty("java.home", ".");
        } else {
            System.out.println(System.getProperty("java.home"));
        }

        System.out.println(System.getProperty("java.home"));
        System.setProperty("java.awt.headless", "false");
        System.setProperty("awt.useSystemFontSettings", "false");

        System.out.println(System.getProperties());

        try {
            setFont();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }

    private static void setFont() throws IOException, FontFormatException {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("font/STKAITI.TTF");
        if (is == null) {
            throw new IOException("Font file not found.");
        }

        // 创建字体对象
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);

        // 关闭输入流
        is.close();

        // 注册字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        Font defaultFont = customFont.deriveFont(Font.PLAIN, 16);
        UIManager.put("defaultFont", defaultFont);

        // INFO: Zhouwx: 2024/10/16 设置全局字体
        FontUIResource fontRes = new FontUIResource(defaultFont);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }

    }
}