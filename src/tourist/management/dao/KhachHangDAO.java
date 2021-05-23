package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.KhachHang;
import tourist.management.entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    /**
     * @param khachHang khách hàng mua vé
     * @return true nếu chèn thành công, ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu chèn trùng mã khách hàng
     */
    public boolean createKhachHang(KhachHang khachHang) throws SQLIntegrityConstraintViolationException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int rowEffected = 0;
        try {
            statement = con.prepareStatement("INSERT INTO KhachHang VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, khachHang.getMaKhachHang());
            statement.setString(2, khachHang.getHoKhachHang());
            statement.setString(3, khachHang.getTenKhachHang());
            statement.setBoolean(4, khachHang.isGioiTinh());
            statement.setDate(5, Date.valueOf(khachHang.getNgaySinh()));
            statement.setString(6, khachHang.getSoCMND());
            statement.setString(7, khachHang.getSoDienThoai());
            statement.setString(8, khachHang.getEmail());
            rowEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            if (sqlException instanceof SQLIntegrityConstraintViolationException)
                throw new SQLIntegrityConstraintViolationException();
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

    public List<KhachHang> getKhachHangTheoMa(String maKhachHang){
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maKhachHang);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                danhSachKhachHang.add(
                        new KhachHang(
                                resultSet.getString("maKhachHang"),
                                resultSet.getString("hoKhachHang"),
                                resultSet.getString("tenKhachHang"),
                                resultSet.getBoolean("gioiTinh"),
                                resultSet.getDate("ngaySinh").toLocalDate(),
                                resultSet.getString("SoCMND"),
                                resultSet.getString("soDienThoai"),
                                resultSet.getString("email")
                        )
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachKhachHang;
    }
}
