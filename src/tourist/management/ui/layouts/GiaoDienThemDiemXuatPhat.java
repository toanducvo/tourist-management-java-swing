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


public class GiaoDienThemDiemXuatPhat implements MouseListener, ActionListener {
    private final JTextField txtmaDiemXuatPhat;
    private final JTextField txttenDiemXuatPhat;
    private final JTextField txttenTinh;
    private final JButton btnThemDiemXuatPhat;
    private final JTable tableDiemXuatPhat;
    private final DefaultTableModel modelDiemXuatPhat;
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
        pnlGiaoDienThemDiemXuatPhatSouth.setPreferredSize(new Dimension(600, 50));
        pnlGiaoDienThemDiemXuatPhat.add(pnlGiaoDienThemDiemXuatPhatSouth, BorderLayout.SOUTH);
        btnThemDiemXuatPhat = new JButton("Thêm Điểm Xuất Phát");
        pnlGiaoDienThemDiemXuatPhatSouth.add(btnThemDiemXuatPhat);


        tableDiemXuatPhat.addMouseListener(this);
        btnThemDiemXuatPhat.addActionListener(this);
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
    }
}
