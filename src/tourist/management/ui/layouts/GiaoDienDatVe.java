package tourist.management.ui.layouts;

import tourist.management.dao.ChuyenDiDAO;
import tourist.management.dao.DatVeDAO;
import tourist.management.dao.KhachHangDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DatVe;
import tourist.management.entity.KhachHang;
import tourist.management.entity.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GiaoDienDatVe extends JFrame implements ActionListener {
    private final JPanel pnlNor;
    private final JPanel pnlCen;
    private final DefaultTableModel model;
    private final JTable table;
    private final JPanel pnlSou;
    private final JButton btnThem;
    private final JButton btnXoaRong;
    private final JLabel lblMaKhachHang;
    private final JTextField txtMaKhachHang;
    private final JLabel lblHoKhachHang;
    private final JTextField txtHoKhachHang;
    private final JLabel lblTenKhachHang;
    private final JTextField txtTenKhachHang;
    private final JLabel lblEmail;
    private final JTextField txtEmail;
    private final JLabel lblCMND;
    private final JTextField txtCMND;
    private final JLabel lblSoDienThoai;
    private final JTextField txtSoDienThoai;
    private final JLabel lblNgaySinh;
    private final JTextField txtNgaySinh;
    private final JLabel lblGioiTinh;
    private final ButtonGroup groupGioiTinh;
    private final JRadioButton radNam;
    private final JRadioButton radNu;
    private final JPanel pnlNor_bottom;
    private final JPanel pnlNor_top;
    private final JLabel lblTieuDe;

    private final ChuyenDiDAO chuyenDiDAO;
    private final KhachHangDAO khachHangDAO;
    private final DatVeDAO datVeDAO;

    private final JPanel pnlGiaoDienDatVe = new JPanel();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public GiaoDienDatVe() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chuyenDiDAO = new ChuyenDiDAO();
        khachHangDAO = new KhachHangDAO();
        datVeDAO = new DatVeDAO();


        pnlGiaoDienDatVe.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienDatVe.setBorder(BorderFactory.createTitledBorder("Đặt vé"));


        //Phần North
        pnlGiaoDienDatVe.add(pnlNor = new JPanel());
        pnlNor.setPreferredSize(new Dimension(570, 230));
        pnlGiaoDienDatVe.add(pnlNor, BorderLayout.NORTH);

        pnlNor.add(pnlNor_top = new JPanel());
        pnlNor.add(pnlNor_top, BorderLayout.SOUTH);
        pnlNor_top.setPreferredSize(new Dimension(570, 50));
        pnlNor_top.add(lblTieuDe = new JLabel("THÔNG TIN ĐẶT VÉ", JLabel.CENTER));
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));

        pnlNor.add(pnlNor_bottom = new JPanel());
        pnlNor.add(pnlNor_bottom, BorderLayout.SOUTH);
        pnlNor_bottom.setPreferredSize(new Dimension(570, 180));
        pnlNor_bottom.add(lblMaKhachHang = new JLabel("Mã Khách Hàng"));
        pnlNor_bottom.add(txtMaKhachHang = new JTextField(57));
        pnlNor_bottom.add(lblHoKhachHang = new JLabel("Họ Khách Hàng"));
        pnlNor_bottom.add(txtHoKhachHang = new JTextField(23));
        pnlNor_bottom.add(lblTenKhachHang = new JLabel("Tên Khách Hàng"));
        pnlNor_bottom.add(txtTenKhachHang = new JTextField(22));
        pnlNor_bottom.add(lblEmail = new JLabel("Email"));
        pnlNor_bottom.add(txtEmail = new JTextField(63));
        pnlNor_bottom.add(lblCMND = new JLabel("CMND"));
        pnlNor_bottom.add(txtCMND = new JTextField(63));
        pnlNor_bottom.add(lblSoDienThoai = new JLabel("SĐT"));
        pnlNor_bottom.add(txtSoDienThoai = new JTextField(21));
        pnlNor_bottom.add(lblNgaySinh = new JLabel("Ngày sinh"));
        pnlNor_bottom.add(txtNgaySinh = new JTextField(18));
        pnlNor_bottom.add(lblGioiTinh = new JLabel("Giới tính"));
        pnlNor_bottom.add(radNam = new JRadioButton("Nam"));
        pnlNor_bottom.add(radNu = new JRadioButton("Nữ"));
        groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);
        radNam.setSelected(true);

        //Phần Center
        pnlGiaoDienDatVe.add(pnlCen = new JPanel(new BorderLayout()));
        pnlGiaoDienDatVe.add(pnlCen, BorderLayout.CENTER);
        pnlCen.setPreferredSize(new Dimension(570, 250));
        pnlCen.setBorder(BorderFactory.createTitledBorder("Danh Sách Chuyến Đi"));

        pnlCen.setLayout(new BoxLayout(pnlCen, BoxLayout.PAGE_AXIS));
        String[] columnName = {"Mã Chuyến Đi", "Điểm Xuất Phát", "Điểm Đến", "Ngày Giờ Đi", "Ngày Giờ Đến", "Biển Số Xe"};

        //table
        model = new DefaultTableModel(columnName, 0);
        table = new JTable(model);
        table.setShowGrid(false);
        table.setRowHeight(20);
        
        JScrollPane TablePane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlCen.add(TablePane);

        List<ChuyenDi> danhSachChuyenDi = chuyenDiDAO.getAllChuyenDi();


        for (ChuyenDi chuyenDi : danhSachChuyenDi) {
            model.addRow(new Object[]{
                    chuyenDi.getMaChuyenDi(),
                    chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                    chuyenDi.getDiemDen().getMaDiemDen(),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDi()),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDen()),
                    chuyenDi.getBienSoXe()
            });
        }

        //Phần South
        pnlGiaoDienDatVe.add(pnlSou = new JPanel());
        pnlSou.setPreferredSize(new Dimension(570, 35));
        pnlGiaoDienDatVe.add(pnlSou, BorderLayout.SOUTH);
        pnlSou.add(btnThem = new JButton("Đặt vé"));
        pnlSou.add(btnXoaRong = new JButton("Xóa rỗng"));

        btnThem.addActionListener(this);
        btnXoaRong.addActionListener(this);
    }

    public JPanel createGiaoDienDatVe() {
        return pnlGiaoDienDatVe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            datVeHandler();
            xoaRongHandler();
        } else if (o.equals(btnXoaRong)) {
            xoaRongHandler();
        }
    }

    private void datVeHandler() {
        String maKhachHang = txtMaKhachHang.getText();
        String hoKhachhang = txtHoKhachHang.getText();
        String tenKhachhang = txtTenKhachHang.getText();
        boolean gioiTinh = radNu.isSelected();
        LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText(), dateTimeFormatter);
        String soCMND = txtCMND.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();


        int row = table.getSelectedRow();

        if (row != -1) {
            String maNhanVien = "NV000001";
            KhachHang khachHang = new KhachHang(
                    maKhachHang,
                    hoKhachhang,
                    tenKhachhang,
                    gioiTinh,
                    ngaySinh,
                    soCMND,
                    soDienThoai,
                    email
            );
            DatVe datVe = new DatVe(
                    khachHang,
                    new ChuyenDi(table.getValueAt(row, 0).toString()),
                    new NhanVien(maNhanVien),
                    LocalDateTime.now()
            );

            try {
                khachHangDAO.createKhachHang(khachHang);
            } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "Trùng");
            } finally {
                try {
                    datVeDAO.createDatVe(datVe);
                    JOptionPane.showMessageDialog(this, "Đặt vé thành công!");
                } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Trùng");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn chuyến đi!");
        }
    }

    private void xoaRongHandler() {
        txtMaKhachHang.setText("");
        txtHoKhachHang.setText("");
        txtEmail.setText("");
        txtTenKhachHang.setText("");
        txtCMND.setText("");
        txtSoDienThoai.setText("");
        txtNgaySinh.setText("");
        txtMaKhachHang.requestFocus();
    }
}
