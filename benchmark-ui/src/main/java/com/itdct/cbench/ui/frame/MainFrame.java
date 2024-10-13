package com.itdct.cbench.ui.frame;

/**
 * @author Zhouwx
 * @date 2024/10/13 22:09:24
 * @version 1.0
 * @description
 */

import com.itdct.cbench.core.Benchmark;
import com.itdct.cbench.model.CpuBenchmarkResultModel;
import com.itdct.cbench.util.CpuBenchmarkResultUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JButton stopButton;

    public MainFrame() {
        // 设置窗口默认关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DCT的CPU性能测试工具");

        createMenuBar();

        JPanel centerTextPanel = createTextArea();

        JPanel buttonPanel = createButtonPanel();

        // 创建主面板并设置布局为 BorderLayout
        Container mainPanel = getContentPane();
        mainPanel.setLayout(new BorderLayout());

        // 添加文本区域到主面板的 CENTER 区域
        mainPanel.add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        mainPanel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        mainPanel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        mainPanel.add(centerTextPanel, BorderLayout.CENTER);

        // 添加按钮面板到主面板的 SOUTH 区域
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 设置默认大小
        setSize(600, 400);
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        // 创建一个“开始”按钮
        startButton = new JButton("开始");
        stopButton = new JButton("停止");

        // 创建一个面板来容纳按钮，并设置布局管理器为 FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(Box.createHorizontalStrut(50),BorderLayout.WEST);

        JPanel centerButtonPanel = new JPanel();
        buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);
        centerButtonPanel.setLayout(new FlowLayout());
        centerButtonPanel.add(startButton);
        centerButtonPanel.add(Box.createHorizontalStrut(30));
        centerButtonPanel.add(stopButton);

        buttonPanel.add(Box.createHorizontalStrut(50),BorderLayout.EAST);
        buttonPanel.add(Box.createVerticalStrut(10),BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            textArea.setText("");
            startButton.setText("执行中……");
            startButton.setEnabled(false);

            Thread thread = new Thread(() -> {
                Benchmark benchmark = new Benchmark();
                benchmark.setOnPrint(s -> textArea.append(s + "\n"));
                CpuBenchmarkResultModel cpuBenchmarkResultModel = benchmark.benchmark();
                String cpuBenchmarkResult = CpuBenchmarkResultUtil.getCpuBenchmarkResult(cpuBenchmarkResultModel);
                textArea.append(cpuBenchmarkResult);

                startButton.setText("开始");
                startButton.setEnabled(true);
            });
            thread.start();
        });
        return buttonPanel;
    }

    private JPanel createTextArea() {
        // 创建一个不可编辑的文本区域
        textArea = new JTextArea("尚未开始……");
        textArea.setEditable(false); // 设置为只读

        JScrollPane scrollPane = new JScrollPane(textArea);
        // 在CENTER区域添加垂直空隙
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(Box.createVerticalStrut(10), BorderLayout.PAGE_END); // 添加垂直间距
        return centerPanel;
    }

    private void createMenuBar() {
        // 创建一个菜单栏
        JMenuBar menuBar = new JMenuBar();

        // 创建“文件”菜单及其子项
        JMenu fileMenu = new JMenu("文件");
        JMenuItem exportItem = new JMenuItem("导出结果");
        fileMenu.add(exportItem);

        // 创建“帮助”菜单及其子项
        JMenu helpMenu = new JMenu("帮助");
        JMenuItem aboutItem = new JMenuItem("关于软件");
        helpMenu.add(aboutItem);

        // 将菜单添加到菜单栏中
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // 设置JFrame的菜单栏
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}