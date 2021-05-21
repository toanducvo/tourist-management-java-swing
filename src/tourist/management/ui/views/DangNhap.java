package tourist.management.ui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class DangNhap extends JFrame {

    private final JPanel pnlDangNhap;
    private final JPanel pnlTieuDe;
    private final JPanel pnlNhap;
    private final JPanel pnlChucNang;

    private final JLabel lblTieuDe;
    private final JLabel lblTenDangNhap;
    private final JLabel lblMatKhau;
    private final JTextField txtTenDangNhap;
    private final JPasswordField txtMatKhau;
    private final JButton btnDangNhap;
    private final JButton btnThoat;

    public DangNhap() {
        setTitle("Đăng nhập | Quản Lý Đặt Tour Du Lịch");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnlDangNhap = new JPanel(new BorderLayout());

        pnlTieuDe = new JPanel();
        pnlTieuDe.setPreferredSize(new Dimension(getWidth(), 80));
        lblTieuDe = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTieuDe.setFont(new Font(lblTieuDe.getFont().getFontName(), lblTieuDe.getFont().getStyle(), 23));

        pnlTieuDe.add(lblTieuDe);

        pnlNhap = new JPanel();
        lblTenDangNhap = new JLabel("Tên đăng nhập");
        lblTenDangNhap.setFont(new Font(lblTenDangNhap.getFont().getFontName(), Font.PLAIN, 14));
        txtTenDangNhap = new JTextField(30);

        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setFont(new Font(lblMatKhau.getFont().getFontName(), Font.PLAIN, 14));
        txtMatKhau = new JPasswordField(34);

        pnlNhap.add(lblTenDangNhap);
        pnlNhap.add(txtTenDangNhap);
        pnlNhap.add(lblMatKhau);
        pnlNhap.add(txtMatKhau);

        pnlChucNang = new JPanel();
        btnDangNhap = new JButton("Đăng nhập");
        btnThoat = new JButton("Thoát");
        pnlChucNang.add(btnDangNhap);
        pnlChucNang.add(btnThoat);

        pnlDangNhap.add(pnlTieuDe, BorderLayout.NORTH);
        pnlDangNhap.add(pnlNhap, BorderLayout.CENTER);
        pnlDangNhap.add(pnlChucNang, BorderLayout.SOUTH);

        add(pnlDangNhap);

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtTenDangNhap.getText();
                char[] password = txtMatKhau.getPassword();
                if (!Pattern.matches("^(nv|NV)\\d{6}", username)) {
                    JOptionPane.showMessageDialog(pnlDangNhap, "Tên đăng nhập không hợp lệ");
                    txtMatKhau.setText("");
                    txtTenDangNhap.selectAll();
                    txtTenDangNhap.requestFocus();
                    return;
                }
                if (!Pattern.matches("(\\d|\\W|\\w){8,}", String.copyValueOf(password))) {
                    JOptionPane.showMessageDialog(pnlDangNhap, "Mật khẩu không hợp lệ");
                    txtMatKhau.setText("");
                    txtMatKhau.requestFocus();
                    return;
                }
                if (String.copyValueOf(password).equals("12345678")) {
                    new GiaoDienChinh(username.toUpperCase()).setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(pnlDangNhap, "Tên đăng nhập hoặc Mật khẩu không hợp lệ!");
                    txtTenDangNhap.setText("");
                    txtMatKhau.setText("");
                    txtTenDangNhap.requestFocus();
                }
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(pnlDangNhap, "Bạn có muốn thoát không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == 0) {
                    System.exit(0);
                }
            }
        });
    }
}
