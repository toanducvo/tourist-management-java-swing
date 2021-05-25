package tourist.management.ui.layouts;

import tourist.management.dao.DiemXuatPhatDAO;
import tourist.management.database.ConnectDB;
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
import java.util.ArrayList;
import java.util.List;


public class GiaoDienThemDiemXuatPhat extends JFrame implements MouseListener, ActionListener {
    private final JTextField txtmaDiemXuatPhat;
    private final JTextField txttenDiemXuatPhat;
    private final JTextField txttenTinh;
    private final JButton btnThemDiemXuatPhat;
    private final JTable tableDiemXuatPhat;
    private final DefaultTableModel modelDiemXuatPhat;
    private final JTextField txtTenTinhCanTim;
    private final JButton btnTimKiem;
    private final JButton btnCapNhat;

    private final DiemXuatPhatDAO diemXuatPhatDAO;
    JPanel pnlGiaoDienThemDiemXuatPhat = new JPanel(new BorderLayout());

    public GiaoDienThemDiemXuatPhat() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        diemXuatPhatDAO = new DiemXuatPhatDAO();

        pnlGiaoDienThemDiemXuatPhat.setPreferredSize(new Dimension(600, 600));
        pnlGiaoDienThemDiemXuatPhat.setBorder(BorderFactory.createTitledBorder("Địa điểm xuất phát"));


        JPanel pnlGiaoDienThemDiemXuatPhatNourth = new JPanel();
        pnlGiaoDienThemDiemXuatPhatNourth.setPreferredSize(new Dimension(600, 100));
        pnlGiaoDienThemDiemXuatPhat.add(pnlGiaoDienThemDiemXuatPhatNourth, BorderLayout.NORTH);
        pnlGiaoDienThemDiemXuatPhatNourth.add(new JLabel("Mã điểm xuất phát"));
        txtmaDiemXuatPhat = new JTextField(57);
        pnlGiaoDienThemDiemXuatPhatNourth.add(txtmaDiemXuatPhat);
        pnlGiaoDienThemDiemXuatPhatNourth.add(new JLabel("Tên điểm xuất phát"));
        txttenDiemXuatPhat = new JTextField(57);
        pnlGiaoDienThemDiemXuatPhatNourth.add(txttenDiemXuatPhat);
        pnlGiaoDienThemDiemXuatPhatNourth.add(new JLabel("Tên tỉnh"));
        txttenTinh = new JTextField(63);
        pnlGiaoDienThemDiemXuatPhatNourth.add(txttenTinh);

        JPanel pnlGiaoDienThemDiemXuatPhatCenter = new JPanel(new BorderLayout());
        pnlGiaoDienThemDiemXuatPhatCenter.setPreferredSize(new Dimension(600, 450));
        pnlGiaoDienThemDiemXuatPhatCenter.setBorder(BorderFactory.createTitledBorder("Danh sách điểm xuất phát"));
        pnlGiaoDienThemDiemXuatPhat.add(pnlGiaoDienThemDiemXuatPhatCenter, BorderLayout.CENTER);
        String[] header = {"Mã Điểm Xuất Phát", "Tên Điểm Xuất Phát", "Tên tỉnh"};
        modelDiemXuatPhat = new DefaultTableModel(header, 0);
        tableDiemXuatPhat = new JTable(modelDiemXuatPhat);
        pnlGiaoDienThemDiemXuatPhatCenter.add(new JScrollPane(tableDiemXuatPhat), BorderLayout.CENTER);
        tableDiemXuatPhat.setShowGrid(false);
        tableDiemXuatPhat.setRowHeight(20);

        for (DiemXuatPhat diemXuatPhat : diemXuatPhatDAO.getAllDiemXuatPhat()) {
            modelDiemXuatPhat.addRow(new Object[]{
                    diemXuatPhat.getMaDiemXuatPhat(),
                    diemXuatPhat.getTenDiemXuatPhat(),
                    diemXuatPhat.getTenTinh()
            });
        }


        JPanel pnlGiaoDienThemDiemXuatPhatSouth = new JPanel();
        pnlGiaoDienThemDiemXuatPhatSouth.setPreferredSize(new Dimension(600, 70));
        pnlGiaoDienThemDiemXuatPhat.add(pnlGiaoDienThemDiemXuatPhatSouth, BorderLayout.SOUTH);

        btnThemDiemXuatPhat = new JButton("Thêm Điểm Xuất Phát");
        txtTenTinhCanTim = new JTextField(20);
        btnTimKiem = new JButton("Tìm kiếm theo tỉnh");
        btnCapNhat = new JButton("Cập nhật điểm xuất phát");
        pnlGiaoDienThemDiemXuatPhatSouth.add(txtTenTinhCanTim);
        pnlGiaoDienThemDiemXuatPhatSouth.add(btnTimKiem);
        pnlGiaoDienThemDiemXuatPhatSouth.add(btnThemDiemXuatPhat);
        pnlGiaoDienThemDiemXuatPhatSouth.add(btnCapNhat);

        tableDiemXuatPhat.addMouseListener(this);
        btnThemDiemXuatPhat.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnCapNhat.addActionListener(this);
    }

    public JPanel createGiaoDienThemDiemXuatPhat() {
        return pnlGiaoDienThemDiemXuatPhat;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tableDiemXuatPhat.getSelectedRow();
        txtmaDiemXuatPhat.setText(String.valueOf(tableDiemXuatPhat.getValueAt(row, 0)));
        txttenDiemXuatPhat.setText(String.valueOf(tableDiemXuatPhat.getValueAt(row, 1)));
        txttenTinh.setText(String.valueOf(tableDiemXuatPhat.getValueAt(row, 2)));
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
        if (o.equals(btnThemDiemXuatPhat)) {
            if (ktRangBuoc()) {
                String maDiemXuatPhat = txtmaDiemXuatPhat.getText();
                String tenDiemXuatPhat = txttenDiemXuatPhat.getText();
                String tenTinh = txttenTinh.getText();

                DiemXuatPhat diemXuatPhat = new DiemXuatPhat(maDiemXuatPhat, tenDiemXuatPhat, tenTinh);

                try {
                    diemXuatPhatDAO.createDiemXuatPhat(diemXuatPhat);
                    modelDiemXuatPhat.addRow(new Object[]{
                            diemXuatPhat.getMaDiemXuatPhat(),
                            diemXuatPhat.getTenDiemXuatPhat(),
                            diemXuatPhat.getTenTinh()
                    });
                } catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Trùng");
                }
            }
        } else if (o.equals(btnTimKiem)) {
            String name = txtTenTinhCanTim.getText();
            List<DiemXuatPhat> dsDiemXuatPhat = new ArrayList<>();
            try {
                if (name.length() > 0) {
                    dsDiemXuatPhat = diemXuatPhatDAO.getDiemXuatPhatTheoTinh(name);
                    DefaultTableModel tableModel = (DefaultTableModel) tableDiemXuatPhat.getModel();
                    tableModel.setRowCount(0);

                    for (DiemXuatPhat diemXuatPhat : dsDiemXuatPhat) {
                        tableModel.addRow(new Object[]{
                                diemXuatPhat.getMaDiemXuatPhat(),
                                diemXuatPhat.getTenDiemXuatPhat(),
                                diemXuatPhat.getTenTinh()
                        });
                    }

                    tableModel.fireTableDataChanged();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");

            }
        } else if (o.equals(btnCapNhat)) {
            modelDiemXuatPhat.setRowCount(0);
            for (DiemXuatPhat diemXuatPhat : diemXuatPhatDAO.getAllDiemXuatPhat()) {
                modelDiemXuatPhat.addRow(new Object[]{
                        diemXuatPhat.getMaDiemXuatPhat(),
                        diemXuatPhat.getTenDiemXuatPhat(),
                        diemXuatPhat.getTenTinh()
                });
            }
        }
    }

    private boolean ktRangBuoc() {
        String maDiemXuatPhat = txtmaDiemXuatPhat.getText().trim();
        String tenDiemXuatPhat = txttenDiemXuatPhat.getText().trim();
        String tenTinh = txttenTinh.getText().trim();

        if (!(maDiemXuatPhat.length() > 0 && maDiemXuatPhat.matches("^(DXP)[0-9]{5}"))) {
            JOptionPane.showMessageDialog(this, " Mã điểm xuất phát bắt đầu bằng 2 ký tự “DXP”, theo sau là 5 ký tự là số");
            return false;
        }
        if (!(tenDiemXuatPhat.length() > 0 && tenDiemXuatPhat.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
            JOptionPane.showMessageDialog(this, "  Tên điểm xuất không chứa các ký tự số và ký tự đặc biệt");
            return false;
        }
        if (!(tenTinh.length() > 0 && tenTinh.matches("^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"))) {
            JOptionPane.showMessageDialog(this, " tên tỉnh không chứa các ký tự số và ký tự đặc biệt");
            return false;
        }

        return true;

    }

}
