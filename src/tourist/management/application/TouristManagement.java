package tourist.management.application;

import tourist.management.ui.windows.DangNhap;

import javax.swing.*;

public class TouristManagement {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }
        new DangNhap().setVisible(true);
    }
}
