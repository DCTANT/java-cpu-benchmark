package com.itdct.cbench.ui;

import com.itdct.cbench.ui.frame.MainFrame;

import javax.swing.SwingUtilities;

/**
 * @author Zhouwx
 * @date 2024/10/13 22:03:28
 * @version 1.0
 * @description
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}