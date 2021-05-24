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
import java.util.ArrayList;
import java.util.List;

public class GiaoDienThemDiemDen extends JFrame implements MouseListener, ActionListener {
    private final JTextField txtmaDiemDen;
    private final JTextField txttenDiemDen;
    private final JTextField txttenTinh;
    private final JTable tableDiemDen;
    private final DefaultTableModel modelDiemDen;
    private final JButton btnCapNhat;
    private final DiemDenDAO diemDenDAO;
    private final JLabel lblTimKiem;
    private final JTextField txtTimKiem;
    private final JButton btnTimKiem;
    private final JComboBox<String> cboTimKiem;
    private final JButton btnThemDiemDen;
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
        pnlGiaoDienThemDiemDenSouth.setPreferredSize(new Dimension(600, 70));
        pnlGiaoDienThemDiemDen.add(pnlGiaoDienThemDiemDenSouth, BorderLayout.SOUTH);
        txtTimKiem = new JTextField(15);
        lblTimKiem = new JLabel("Tìm kiếm theo");
        cboTimKiem = new JComboBox<>();
        cboTimKiem.addItem("Điểm đến");
        cboTimKiem.addItem("Theo tỉnh");
        btnTimKiem = new JButton("Tìm kiếm");
        btnThemDiemDen = new JButton("Thêm điểm đến");
        btnCapNhat = new JButton("Cập nhật điểm đến");

        pnlGiaoDienThemDiemDenSouth.add(txtTimKiem);
        pnlGiaoDienThemDiemDenSouth.add(lblTimKiem);
        pnlGiaoDienThemDiemDenSouth.add(cboTimKiem);
        pnlGiaoDienThemDiemDenSouth.add(btnTimKiem);
        pnlGiaoDienThemDiemDenSouth.add(btnThemDiemDen);
        pnlGiaoDienThemDiemDenSouth.add(btnCapNhat);


        tableDiemDen.addMouseListener(this);
        btnTimKiem.addActionListener(this);
        btnThemDiemDen.addActionListener(this);
        btnCapNhat.addActionListener(this);
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
            if (ktRangBuoc()) {
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

        } else if (o.equals(btnTimKiem)) {

            int select = cboTimKiem.getSelectedIndex();
            switch (select) {
                case 0: {
                    String name = txtTimKiem.getText();

                    ArrayList<DiemDen> dsdiemDen = new ArrayList<DiemDen>();
                    try {
                        if (name.length() > 0) {
                            dsdiemDen = diemDenDAO.getDiemDenTheoTen(name);
                            DefaultTableModel tableModel = (DefaultTableModel) tableDiemDen.getModel();
                            tableModel.setRowCount(0);

                            for (DiemDen diemDen : dsdiemDen) {
                                tableModel.addRow(new Object[]{
                                        diemDen.getMaDiemDen(),
                                        diemDen.getTenDiemDen(),
                                        diemDen.getTenTinh()
                                });
                            }

                            tableModel.fireTableDataChanged();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");
                    }
                    break;
                }
                case 1: {
                    String name = txtTimKiem.getText();
                    List<DiemDen> dsdiemDen = new ArrayList<>();
                    try {
                        if (name.length() > 0) {
                            dsdiemDen = diemDenDAO.getDiemDenTheoTinh(name);
                            DefaultTableModel tableModel = (DefaultTableModel) tableDiemDen.getModel();
                            tableModel.setRowCount(0);

                            for (DiemDen diemDen : dsdiemDen) {
                                tableModel.addRow(new Object[]{
                                        diemDen.getMaDiemDen(),
                                        diemDen.getTenDiemDen(),
                                        diemDen.getTenTinh()
                                });
                            }

                            tableModel.fireTableDataChanged();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");
                    }
                    break;
                }
            }


        } else if (o.equals(btnCapNhat)) {
            modelDiemDen.setRowCount(0);
            for (DiemDen diemDen : diemDenDAO.getAllDiemDen()) {
                modelDiemDen.addRow(new Object[]{
                        diemDen.getMaDiemDen(),
                        diemDen.getTenDiemDen(),
                        diemDen.getTenTinh()
                });
                txtTimKiem.setText("");
            }
        }

    }


    private boolean ktRangBuoc() {
        String maDiemDen = txtmaDiemDen.getText().trim();
        String tenDiemDen = txttenDiemDen.getText().trim();
        String tenTinh = txttenTinh.getText().trim();


        if (!(maDiemDen.length() > 0 && maDiemDen.matches("^(DD)[0-9]{6}"))) {
            JOptionPane.showMessageDialog(this, " Mã điểm đến bắt đầu bằng 2 ký tự “DD”, theo sau là 6 ký tự là số");
            return false;
        }

        if (!(tenDiemDen.length() > 0 && tenDiemDen.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
            JOptionPane.showMessageDialog(this, "Tên điểm đến không chứa các ký tự số và ký tự đặc biệt.");
            return false;
        }
        if (!(tenTinh.length() > 0 && tenTinh.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
            JOptionPane.showMessageDialog(this, "Tên tỉnh không chứa các ký tự số và ký tự đặc biệt.");
            return false;
        }


        return true;

    }

}
