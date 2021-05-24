package tourist.management.ui.layouts;

import tourist.management.dao.KhachHangDAO;
import tourist.management.entity.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class GiaoDienKhachHang extends JFrame {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DefaultTableModel defaultTableModel;
    private final JTable tblKhachHang;
    private final JLabel lblLoaiTimKiem;
    private final JTextField txtTimKiem;
    private final JButton btnTimKiem;
    private final JButton btnXoaTimKiem;
    private final JComboBox<String> cboLocTheoDieuKien;
    private final KhachHangDAO khachHangDAO;
    private final JPanel pnlDanhSach;
    private final JPanel pnlTraCuu;

    public GiaoDienKhachHang() {
        setTitle("Tra cứu khách hàng");
        setSize(700, 468);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        pnlDanhSach = new JPanel();
        pnlDanhSach.setPreferredSize(new Dimension(getWidth(), 360));
        pnlDanhSach.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
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
            defaultTableModel.addRow(parseKhachHang(khachHang));
        }

        pnlTraCuu = new JPanel();
        pnlTraCuu.setPreferredSize(new Dimension(getWidth(), 80));
        pnlTraCuu.setBorder(BorderFactory.createTitledBorder("Tra cứu khách hàng"));
        txtTimKiem = new JTextField(20);
        txtTimKiem.requestFocus();
        lblLoaiTimKiem = new JLabel("Tìm kiếm theo");
        cboLocTheoDieuKien = new JComboBox<>();
        cboLocTheoDieuKien.setSize(50,15);

        cboLocTheoDieuKien.addItem("Tên");
        cboLocTheoDieuKien.addItem("Số CMND");
        cboLocTheoDieuKien.addItem("Ngày sinh");

        btnTimKiem = new JButton("Tìm kiếm");
        btnXoaTimKiem = new JButton("Xóa tìm kiếm");

        pnlTraCuu.add(txtTimKiem);
        pnlTraCuu.add(lblLoaiTimKiem);
        pnlTraCuu.add(cboLocTheoDieuKien);
        pnlTraCuu.add(btnTimKiem);
        pnlTraCuu.add(btnXoaTimKiem);


        add(pnlDanhSach, BorderLayout.CENTER);
        add(pnlTraCuu, BorderLayout.SOUTH);


        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected = cboLocTheoDieuKien.getSelectedIndex();
                switch (selected) {
                    case 0:
                    {
                        String timKiem = txtTimKiem.getText().trim();
                        defaultTableModel.setRowCount(0);
                        for (KhachHang khachHang : khachHangDAO.getKhachHangTheoTen(timKiem)) {
                            defaultTableModel.addRow(parseKhachHang(khachHang));
                        }
                        break;
                    }


                    case 1:
                    {
                        String cmnd = txtTimKiem.getText().trim();
                        if (!Pattern.matches("\\d{12}|\\d{9}", cmnd)) {
                            JOptionPane.showMessageDialog(pnlTraCuu, "CMND không hợp lệ!");
                        }
                        else {
                            defaultTableModel.setRowCount(0);
                            for (KhachHang khachHang : khachHangDAO.getKhachHangTheoCMND(cmnd)) {
                                defaultTableModel.addRow(parseKhachHang(khachHang));
                            }
                        }
                        break;
                    }

                    case 2:
                    {
                        try {
                            LocalDate ngaySinh = LocalDate.parse(txtTimKiem.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            defaultTableModel.setRowCount(0);
                            for (KhachHang khachHang : khachHangDAO.getKhachHangTheoNgaySinh(ngaySinh)) {
                                defaultTableModel.addRow(parseKhachHang(khachHang));
                            }
                        }
                        catch (DateTimeParseException dateTimeParseException){
                            JOptionPane.showMessageDialog(pnlTraCuu, "Ngày sinh không hợp lệ");
                        }
                        break;
                    }

                }
            }
        });
    }

    private Object[] parseKhachHang(KhachHang khachHang){
        return new Object[] {
                khachHang.getMaKhachHang(),
                khachHang.getHoKhachHang(),
                khachHang.getTenKhachHang(),
                khachHang.isGioiTinh() ? "Nữ" : "Nam",
                dateTimeFormatter.format(khachHang.getNgaySinh()),
                khachHang.getSoCMND(),
                khachHang.getSoDienThoai(),
                khachHang.getEmail()
        };
    }
}