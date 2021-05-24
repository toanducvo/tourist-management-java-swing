package tourist.management.ui.layouts;

import tourist.management.dao.KhachHangDAO;
import tourist.management.entity.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class GiaoDienKhachHang extends JFrame {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DefaultTableModel defaultTableModel;
    private final JTable tblKhachHang;
    private final JTextField txtTimKiem;
    private final JButton btnTimKiemTheoTen;
    private final JButton btnTimKiemTheoCMND;
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
        pnlDanhSach.setBorder(BorderFactory.createTitledBorder("Danh Sách khách hàng"));
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
        tblKhachHang.setShowGrid(false);
        tblKhachHang.setRowHeight(20);
        khachHangDAO = new KhachHangDAO();
        for (KhachHang khachHang : khachHangDAO.getAllKhachHang()) {
            defaultTableModel.addRow(
                    new Object[]{
                            khachHang.getMaKhachHang(),
                            khachHang.getHoKhachHang(),
                            khachHang.getTenKhachHang(),
                            khachHang.isGioiTinh() ? "Nữ" : "Nam",
                            dateTimeFormatter.format(khachHang.getNgaySinh()),
                            khachHang.getSoCMND(),
                            khachHang.getSoDienThoai(),
                            khachHang.getEmail()
                    });
        }

        pnlChucNang = new JPanel();
        pnlChucNang.setPreferredSize(new Dimension(getWidth(), 80));
        pnlChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        txtTimKiem = new JTextField(30);
        btnTimKiemTheoTen = new JButton("Tìm theo tên");
        btnTimKiemTheoCMND = new JButton("Tìm theo CMND");
        pnlChucNang.add(txtTimKiem);
        pnlChucNang.add(btnTimKiemTheoTen);
        pnlChucNang.add(btnTimKiemTheoCMND);


        add(pnlDanhSach, BorderLayout.CENTER);
        add(pnlChucNang, BorderLayout.SOUTH);


        btnTimKiemTheoTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        btnTimKiemTheoCMND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
