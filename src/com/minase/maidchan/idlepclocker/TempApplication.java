package com.minase.maidchan.idlepclocker;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import javax.swing.JFrame;

public class TempApplication {
    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().addAWTEventListener(new Listener(), AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class Listener implements AWTEventListener {
        public void eventDispatched(AWTEvent event) {
            System.out.println(MouseInfo.getPointerInfo().getLocation());
//            System.out.println(event);
            
            System.out.println(event.toString());
        }
    }
}