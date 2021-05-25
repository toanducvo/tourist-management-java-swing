package tourist.management.ui.layouts;

import tourist.management.dao.ChuyenDiDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DiemDen;
import tourist.management.entity.DiemXuatPhat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GiaoDienThemChuyenDi extends JFrame implements ActionListener, MouseListener {
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
    private final JButton btnXoaChuyenDi;

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
        txtMaChuyenDi = new JTextField(60);
        pnlGiaoDienThemChuyenDiNourth.add(txtMaChuyenDi);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Điểm xuất phát"));
        txtDiemXuatPhat = new JTextField(58);
        pnlGiaoDienThemChuyenDiNourth.add(txtDiemXuatPhat);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Điểm đến"));
        txtDiemDen = new JTextField(62);
        pnlGiaoDienThemChuyenDiNourth.add(txtDiemDen);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Biển số xe"));
        txtBienSo = new JTextField(62);
        pnlGiaoDienThemChuyenDiNourth.add(txtBienSo);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Ngày giờ Đi"));
        txtNgayGioDi = new JTextField(26);
        pnlGiaoDienThemChuyenDiNourth.add(txtNgayGioDi);
        pnlGiaoDienThemChuyenDiNourth.add(new JLabel("Ngày giờ Đến"));
        txtNgayGioDen = new JTextField(25);
        pnlGiaoDienThemChuyenDiNourth.add(txtNgayGioDen);


        JPanel pnlGiaoDienThemChuyenDiCenter = new JPanel(new BorderLayout());
        pnlGiaoDienThemChuyenDiCenter.setPreferredSize(new Dimension(600, 350));
        pnlGiaoDienThemChuyenDiCenter.setBorder(BorderFactory.createTitledBorder("Danh sách chuyến đi"));
        pnlGiaoDienThemChuyenDi.add(pnlGiaoDienThemChuyenDiCenter, BorderLayout.CENTER);
        String[] header = {"Mã Chuyến Đi", "Điểm Xuất Phát", "Điểm Đến", "Ngày Giờ Đi", "Ngày Giờ Đến", "Biển Số Xe"};
        modelChuyenDi = new DefaultTableModel(header, 0);
        tableChuyenDi = new JTable(modelChuyenDi);
        pnlGiaoDienThemChuyenDiCenter.add(new JScrollPane(tableChuyenDi), BorderLayout.CENTER);
        tableChuyenDi.setShowGrid(false);
        tableChuyenDi.setRowHeight(20);

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
        btnThemChuyenDi = new JButton("Thêm chuyến Đi");
        pnlGiaoDienThemChuyenDiSouth.add(btnThemChuyenDi);
        btnXoaRongChuyenDi = new JButton("Xóa rỗng");
        btnXoaChuyenDi = new JButton("Xóa");
        pnlGiaoDienThemChuyenDiSouth.add(btnXoaRongChuyenDi);
        pnlGiaoDienThemChuyenDiSouth.add(btnXoaChuyenDi);


        btnThemChuyenDi.addActionListener(this);
        btnXoaRongChuyenDi.addActionListener(this);
        btnXoaChuyenDi.addActionListener(this);
        tableChuyenDi.addMouseListener(this);
    }

    public JPanel createGiaoDienThemChuyenDi() {
        return pnlGiaoDienThemChuyenDi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThemChuyenDi)) {
            if (ktRangBuoc()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

//            	LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

                String maChuyenDi = txtMaChuyenDi.getText();
                String maDiemXuatPhat = txtDiemXuatPhat.getText();
                String maDiemDen = txtDiemDen.getText();
                LocalDateTime ngayGioDi = LocalDateTime.parse(txtNgayGioDi.getText(), formatter);
                LocalDateTime ngayGioDen = LocalDateTime.parse(txtNgayGioDen.getText(), formatter);
                String bienSo = txtBienSo.getText();

                ChuyenDi chuyenDi = new ChuyenDi(maChuyenDi, new DiemXuatPhat(maDiemXuatPhat), new DiemDen(maDiemDen), ngayGioDi, ngayGioDen, bienSo);

                try {
                    chuyenDiDAO.createChuyenDi(chuyenDi);
                    modelChuyenDi.addRow(new Object[]{
                            chuyenDi.getMaChuyenDi(),
                            chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat(),
                            chuyenDi.getDiemDen().getMaDiemDen(),
                            formatter.format(chuyenDi.getNgayGioDi()),
                            formatter.format(chuyenDi.getNgayGioDen()),
                            chuyenDi.getBienSoXe()
                    });
                } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Trùng");
                }
               xoaRong();
            }


        } else if (o.equals(btnXoaChuyenDi)) {
            int row = tableChuyenDi.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá chuyến đi không!", "Cảnh Báo", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        String maChuyenDi = txtMaChuyenDi.getText();
                        chuyenDiDAO.xoaMaChuyenDi(tableChuyenDi.getValueAt(row, 0).toString());
                        modelChuyenDi.removeRow(row);

                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            xoaRong();
        }
        else if (o.equals(btnXoaRongChuyenDi)) {
            xoaRong();
        }
    }

    private boolean ktRangBuoc() {
        String maChuyenDi = txtMaChuyenDi.getText().trim();
        String bienSoXe = txtBienSo.getText().trim();

        if (!(maChuyenDi.length() > 0 && maChuyenDi.matches("^(CD)[0-9]{6}"))) {
            JOptionPane.showMessageDialog(this, " Mã chuyến đi bắt đầu bằng 2 ký tự “CD”, theo sau là 6 ký tự là số");
            return false;
        }
        return true;
    }

    private void xoaRong(){
        txtMaChuyenDi.setText("");
        txtDiemXuatPhat.setText("");
        txtDiemDen.setText("");
        txtNgayGioDen.setText("");
        txtNgayGioDi.setText("");
        txtBienSo.setText("");
        txtMaChuyenDi.requestFocus();
        tableChuyenDi.clearSelection();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        int row = tableChuyenDi.getSelectedRow();
        txtMaChuyenDi.setText(String.valueOf(tableChuyenDi.getValueAt(row, 0)));
        txtDiemXuatPhat.setText(String.valueOf(tableChuyenDi.getValueAt(row, 1)));
        txtDiemDen.setText(String.valueOf(tableChuyenDi.getValueAt(row, 2)));
        txtNgayGioDi.setText(String.valueOf(tableChuyenDi.getValueAt(row, 3)));
        txtNgayGioDen.setText(String.valueOf(tableChuyenDi.getValueAt(row, 4)));
        txtBienSo.setText(String.valueOf(tableChuyenDi.getValueAt(row, 5)));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


}
