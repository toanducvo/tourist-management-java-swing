package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DatVe;
import tourist.management.entity.KhachHang;
import tourist.management.entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatVeDAO {

    /**
     * @param datVe thông tin đặt vé
     * @return true nếu đặt vé (số dòng chèn vào bảng khác 0), ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu không tồn tại các khóa ngoại liên kết
     */
    public boolean createDatVe(DatVe datVe) {
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int rowEffected = 0;
        try {
            statement = connection.prepareStatement("INSERT INTO DatVe VALUES (?,?,?,?)");
            statement.setString(1, datVe.getKhachHang().getMaKhachHang());
            statement.setString(2, datVe.getChuyenDi().getMaChuyenDi());
            statement.setString(3, datVe.getNhanVien().getMaNhanVien());
            statement.setTimestamp(4, Timestamp.valueOf(datVe.getNgayDatVe()));
            rowEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return rowEffected > 0;
    }

    /**
     * @return danh sách các khách hàng đã mua vé
     */
    public List<DatVe> getAllDatVe() {
        List<DatVe> danhSachDatVe = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM DatVe";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                danhSachDatVe.add(
                        new DatVe(
                                new KhachHang(resultSet.getString("maKhachHang")),
                                new ChuyenDi(resultSet.getString("maChuyenDi")),
                                new NhanVien(resultSet.getString("maNhanVien")),
                                resultSet.getTimestamp("ngayDatVe").toLocalDateTime()
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachDatVe;
    }

    /**
     * @param maKhachHang mã khách hàng
     * @return danh sách vé mà khách hàng đã mua
     */
    public List<DatVe> getThongTinDatVeTheoMaKH(String maKhachHang) {
        List<DatVe> danhSachVeDaDat = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "SELECT * FROM DatVe WHERE maKhachHang = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, maKhachHang);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                danhSachVeDaDat.add(
                        new DatVe(
                                new KhachHang(resultSet.getString("maKhachHang")),
                                new ChuyenDi(resultSet.getString("maChuyenDi")),
                                new NhanVien(resultSet.getString("maNhanVien")),
                                resultSet.getTimestamp("ngayDatVe").toLocalDateTime()
                        )
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachVeDaDat;
    }
}
