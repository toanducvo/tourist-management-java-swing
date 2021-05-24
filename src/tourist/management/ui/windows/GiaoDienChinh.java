package tourist.management.ui.windows;

import tourist.management.entity.NhanVien;
import tourist.management.ui.layouts.*;

import javax.swing.*;

public class GiaoDienChinh extends JFrame {
    public GiaoDienChinh(String username) {
        setTitle("Quản Lý Đặt Tour Du Lịch");
        setSize(600, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        NhanVien nhanVien = new NhanVien(username);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Đặt Vé", null, new GiaoDienDatVe().createGiaoDienDatVe());
        tabbedPane.addTab("Lịch Sử Đặt Vé", null, new GiaoDienLichSuDatVe().createGiaoDienLichSuDatVe());
        tabbedPane.addTab("Thêm Điểm Xuất Phát", null, new GiaoDienThemDiemXuatPhat().createGiaoDienThemDiemXuatPhat());
        tabbedPane.addTab("Thêm Điểm Đến", null, new GiaoDienThemDiemDen().createGiaoDienThemDiemDen());
        tabbedPane.addTab("Thêm Chuyến Đi", null, new GiaoDienThemChuyenDi().createGiaoDienThemChuyenDi());
        add(tabbedPane);
    }
}
