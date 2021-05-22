package tourist.management.ui.layouts;

import tourist.management.dao.DiemDenDAO;
import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemDen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class GiaoDienThemDiemDen extends JFrame implements MouseListener, ActionListener {
    private final JTextField txtmaDiemDen;
    private final JTextField txttenDiemDen;
    private final JTextField txttenTinh;
    private final JButton btnThemDiemDen;
    private final JTable tableDiemDen;
    private final DefaultTableModel modelDiemDen;
    private final DiemDenDAO diemDenDAO;
    JPanel pnlGiaoDienThemDiemDen = new JPanel(new BorderLayout());

    public GiaoDienThemDiemDen() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        diemDenDAO = new DiemDenDAO();

        pnlGiaoDienThemDiemDen.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienThemDiemDen.setBorder(BorderFactory.createTitledBorder("Địa điểm đến"));


        JPanel pnlGiaoDienThemDiemDenNourth = new JPanel();
        pnlGiaoDienThemDiemDenNourth.setPreferredSize(new Dimension(600, 100));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenNourth, BorderLayout.NORTH);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Mã điểm đến"));
        txtmaDiemDen = new JTextField(60);
        pnlGiaoDienThemDiemDenNourth.add(txtmaDiemDen);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Tên điểm đến"));
        txttenDiemDen = new JTextField(60);
        pnlGiaoDienThemDiemDenNourth.add(txttenDiemDen);
        pnlGiaoDienThemDiemDenNourth.add(new JLabel("Tên tỉnh"));
        txttenTinh = new JTextField(63);
        pnlGiaoDienThemDiemDenNourth.add(txttenTinh);

        JPanel pnlGiaoDienThemDiemDenCenter = new JPanel(new BorderLayout());
        pnlGiaoDienThemDiemDenCenter.setPreferredSize(new Dimension(600, 450));
        pnlGiaoDienThemDiemDenCenter.setBorder(BorderFactory.createTitledBorder("Danh sách điểm đến"));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenCenter, BorderLayout.CENTER);
        String[] header = {"Mã Điểm Đến", "Tên Điểm Đến", "Tên tỉnh"};
        modelDiemDen = new DefaultTableModel(header, 0);
        tableDiemDen = new JTable(modelDiemDen);
        pnlGiaoDienThemDiemDenCenter.add(new JScrollPane(tableDiemDen), BorderLayout.CENTER);
        tableDiemDen.setShowGrid(false);
        tableDiemDen.setRowHeight(20);

        for (DiemDen diemDen : diemDenDAO.getAllDiemDen()) {
            modelDiemDen.addRow(new Object[]{
                    diemDen.getMaDiemDen(),
                    diemDen.getTenDiemDen(),
                    diemDen.getTenTinh()
            });
        }


        JPanel pnlGiaoDienThemDiemDenSouth = new JPanel();
        pnlGiaoDienThemDiemDenSouth.setPreferredSize(new Dimension(600, 50));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenSouth, BorderLayout.SOUTH);
        btnThemDiemDen = new JButton("Thêm Điểm Xuất Phát");
        pnlGiaoDienThemDiemDenSouth.add(btnThemDiemDen);


        tableDiemDen.addMouseListener(this);
        btnThemDiemDen.addActionListener(this);
    }

    public JPanel createGiaoDienThemDiemDen() {
        return pnlGiaoDienThemDiemDen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tableDiemDen.getSelectedRow();
        txtmaDiemDen.setText(String.valueOf(tableDiemDen.getValueAt(row, 0)));
        txttenDiemDen.setText(String.valueOf(tableDiemDen.getValueAt(row, 1)));
        txttenTinh.setText(String.valueOf(tableDiemDen.getValueAt(row, 2)));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThemDiemDen)) {

            String maDiemDen = txtmaDiemDen.getText();
            String tenDiemDen = txttenDiemDen.getText();
            String tenTinh = txttenTinh.getText();

            DiemDen diemDen = new DiemDen(maDiemDen, tenDiemDen, tenTinh);

            try {
                diemDenDAO.createDiemDen(diemDen);
                modelDiemDen.addRow(new Object[]{
                        diemDen.getMaDiemDen(),
                        diemDen.getTenDiemDen(),
                        diemDen.getTenTinh()
                });
            } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(this, "Trùng");
            }

        }

    }
}
