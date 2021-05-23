package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemXuatPhat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiemXuatPhatDAO {

    /**
     * @return danh sách tất cả các điểm xuất phát
     */
    public List<DiemXuatPhat> getAllDiemXuatPhat() {
        List<DiemXuatPhat> danhSachDiemXuatPhat = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM DiemXuatPhat";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                danhSachDiemXuatPhat.add(
                        new DiemXuatPhat(
                                resultSet.getString("maDiemXuatPhat"),
                                resultSet.getString("tenDiemXuatPhat"),
                                resultSet.getString("tenTinh")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachDiemXuatPhat;
    }
    
    public boolean createDiemXuatPhat(DiemXuatPhat diemXuatPhat) throws SQLIntegrityConstraintViolationException {
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int rowEffected = 0;
        try {
            statement = connection.prepareStatement("INSERT INTO DiemXuatPhat VALUES (?,?,?)");
            statement.setString(1, diemXuatPhat.getMaDiemXuatPhat());
            statement.setString(2, diemXuatPhat.getTenDiemXuatPhat());
            statement.setString(3, diemXuatPhat.getTenTinh());
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
