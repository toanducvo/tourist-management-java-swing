package tourist.management.ui.layouts;

import tourist.management.dao.ChuyenDiDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GiaoDienThemChuyenDi implements ActionListener {
    private final JTextField txtMaChuyenDi;
    private final JTextField txtDiemXuatPhat;
    private final JTextField txtDiemDen;
    private final JTextField txtBienSo;
    private final JTextField txtNgayGioDi;
    private final JTextField txtNgayGioDen;
    private final JTable tableChuyenDi;
    private final DefaultTableModel modelChuyenDi;
    private final JButton btnThemChuyenDi;
    private final JButton btnXoaRongChuyenDi;
    private final JPanel pnlGiaoDienThemChuyenDi = new JPanel(new BorderLayout());

    private final ChuyenDiDAO chuyenDiDAO;

    public GiaoDienThemChuyenDi() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chuyenDiDAO = new ChuyenDiDAO();


        pnlGiaoDienThemChuyenDi.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienThemChuyenDi.setBorder(BorderFactory.createTitledBorder("Chuyến đi"));

        JPanel pnlGiaoDienThemChuyenDiNourth = new JPanel();
        pnlGiaoDienThemChuyenDiNourth.setPreferredSize(new Dimension(600, 200));
        pnlGiaoDienThemChuyenDi.add(pnlGiaoDienThemChuyenDiNourth, BorderLayout.NORTH);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Mã chuyến đi"));
        txtMaChuyenDi = new JTextField(43);
        pnlGiaoDienThemChuyenDiNourth.add(txtMaChuyenDi);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Điểm xuất phát"));
        txtDiemXuatPhat = new JTextField(42);
        pnlGiaoDienThemChuyenDiNourth.add(txtDiemXuatPhat);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Điểm đến"));
        txtDiemDen = new JTextField(45);
        pnlGiaoDienThemChuyenDiNourth.add(txtDiemDen);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Biển số xe"));
        txtBienSo = new JTextField(45);
        pnlGiaoDienThemChuyenDiNourth.add(txtBienSo);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Ngày Giờ Đi"));
        txtNgayGioDi = new JTextField(44);
        pnlGiaoDienThemChuyenDiNourth.add(txtNgayGioDi);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Ngày Giờ Đến"));
        txtNgayGioDen = new JTextField(43);
        pnlGiaoDienThemChuyenDiNourth.add(txtNgayGioDen);


        JPanel pnlGiaoDienThemChuyenDiCenter = new JPanel(new BorderLayout());
        pnlGiaoDienThemChuyenDiCenter.setPreferredSize(new Dimension(600, 350));
        pnlGiaoDienThemChuyenDiCenter.setBorder(BorderFactory.createTitledBorder("Danh sách chuyến đi"));
        pnlGiaoDienThemChuyenDi.add(pnlGiaoDienThemChuyenDiCenter, BorderLayout.CENTER);
        String[] header = {"Mã Chuyến Đi", "Điểm Xuất Phát", "Điểm Đến", "Ngày Giờ Đi", "Ngày Giờ Đến", "Biển Số Xe"};
        modelChuyenDi = new DefaultTableModel(header, 0);
        tableChuyenDi = new JTable(modelChuyenDi);
        pnlGiaoDienThemChuyenDiCenter.add(new JScrollPane(tableChuyenDi), BorderLayout.CENTER);

        List<ChuyenDi> danhSachChuyenDi = chuyenDiDAO.getAllChuyenDi();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (ChuyenDi chuyenDi : danhSachChuyenDi) {
            modelChuyenDi.addRow(new Object[]{
                    chuyenDi.getMaChuyenDi(),
                    chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                    chuyenDi.getDiemDen().getMaDiemDen(),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDi()),
                    dateTimeFormatter.format(chuyenDi.getNgayGioDen()),
                    chuyenDi.getBienSoXe()
            });
        }


        JPanel pnlGiaoDienThemChuyenDiSouth = new JPanel();
        pnlGiaoDienThemChuyenDiSouth.setPreferredSize(new Dimension(600, 50));
        pnlGiaoDienThemChuyenDi.add(pnlGiaoDienThemChuyenDiSouth, BorderLayout.SOUTH);
        btnThemChuyenDi = new JButton("Thêm Chuyến Đi");
        pnlGiaoDienThemChuyenDiSouth.add(btnThemChuyenDi);
        btnXoaRongChuyenDi = new JButton("Xóa rỗng");
        pnlGiaoDienThemChuyenDiSouth.add(btnXoaRongChuyenDi);
    }

    public JPanel createGiaoDienThemChuyenDi() {
        return pnlGiaoDienThemChuyenDi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
