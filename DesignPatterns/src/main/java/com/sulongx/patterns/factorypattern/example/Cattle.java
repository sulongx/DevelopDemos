package com.sulongx.patterns.factorypattern.example;

import javax.swing.*;
import java.awt.*;

/**
 * 描述:
 * 具体产品：牛类
 *
 * @author xiongsulong
 * @create 2020-10-28 15:56
 */
public class Cattle implements Animal {

    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    public Cattle(){
        Container pane = jf.getContentPane();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 1));
        jPanel.setBorder(BorderFactory.createTitledBorder("动物:  牛"));
        sp = new JScrollPane(jPanel);
        pane.add(sp, BorderLayout.CENTER);
        JLabel jLabel = new JLabel(new ImageIcon("DesignPatterns/src/main/resources/img/cattle.jpeg"));
        jPanel.add(jLabel);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}
