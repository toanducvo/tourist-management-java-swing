package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.KhachHang;

import java.sql.*;

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
}
