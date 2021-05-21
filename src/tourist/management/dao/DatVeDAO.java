package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.DatVe;
import tourist.management.entity.KhachHang;

import java.sql.*;

public class DatVeDAO {

    /**
     * @param datVe thông tin đặt vé
     * @return true nếu đặt vé (số dòng chèn vào bảng khác 0), ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu không tồn tại các khóa ngoại liên kết
     */
    public boolean createDatVe(DatVe datVe) throws SQLIntegrityConstraintViolationException {
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
