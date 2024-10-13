package com.itdct.cbench.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Zhouwx
 * @date 2024/10/13 23:58:28
 * @version 1.0
 * @description
 */
public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner, String title) {
        super(owner, title, false); // 设置为非模态对话框
        setLayout(new BorderLayout());

        JTextArea aboutText = new JTextArea(
                "该软件由DecentAnt（CSDN：DCTANT）编写，使用类似于SuperPI算法计算CPU的性能分数，且采用递进式，不会让老旧CPU卡死，且能够在任意平台执行，甚至让手机CPU与电脑CPU同台竞技。\n" +
                        "项目地址：https://gitee.com/decentant/java-cpu-benchmark"
        );
        aboutText.setEditable(false); // 设置为只读
        aboutText.setLineWrap(true); // 自动换行
        aboutText.setWrapStyleWord(true); // 按单词换行
        aboutText.setBackground(new Color(0, 0, 0, 0));

        add(aboutText, BorderLayout.CENTER);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(20), BorderLayout.WEST);
        add(Box.createHorizontalStrut(20), BorderLayout.EAST);
        add(Box.createHorizontalStrut(20), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(owner); // 居中显示
        setVisible(true);
    }

}
