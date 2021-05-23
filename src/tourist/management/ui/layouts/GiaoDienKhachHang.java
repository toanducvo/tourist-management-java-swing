package tourist.management.ui.layouts;

import tourist.management.dao.KhachHangDAO;
import tourist.management.entity.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiaoDienKhachHang extends JFrame {

    private final DefaultTableModel defaultTableModel;
    private final JTable tblKhachHang;
    private final JButton btnTimKiem;
    private final KhachHangDAO khachHangDAO;
    private final JPanel pnlDanhSach;
    private final JPanel pnlChucNang;

    public GiaoDienKhachHang() {
        setTitle("Danh sách khách hàng");
        setSize(700, 468);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        pnlDanhSach = new JPanel();
        pnlDanhSach.setPreferredSize(new Dimension(getWidth(), 360));

        Object[] columnName = {
                "Mã khách hàng",
                "Họ khách hàng",
                "Tên khách hàng",
                "Giới tính",
                "Ngày sinh",
                "Số CMND",
                "Số điện thoại",
                "Email"
        };

        defaultTableModel = new DefaultTableModel(columnName, 0);
        tblKhachHang = new JTable(defaultTableModel);
        pnlDanhSach.add(new JScrollPane(tblKhachHang));
        khachHangDAO = new KhachHangDAO();
        for (KhachHang khachHang : khachHangDAO.getAllKhachHang()) {
            defaultTableModel.addRow(
                    new Object[]{
                            khachHang.getMaKhachHang(),
                            khachHang.getHoKhachHang(),
                            khachHang.getTenKhachHang(),
                            khachHang.isGioiTinh() ? "Nữ" : "Nam",
                            khachHang.getNgaySinh().toString(),
                            khachHang.getSoCMND(),
                            khachHang.getSoDienThoai(),
                            khachHang.getEmail()
                    });
        }

        pnlChucNang = new JPanel();
        pnlChucNang.setPreferredSize(new Dimension(getWidth(), 50));

        add(pnlDanhSach, BorderLayout.CENTER);
        add(pnlChucNang, BorderLayout.SOUTH);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
