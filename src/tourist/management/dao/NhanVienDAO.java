package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    /**
     * @param maNhanVien mã nhân viên
     * @return
     */
    public List<NhanVien> getNhanVienTheoMa(String maNhanVien) {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maNhanVien);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                danhSachNhanVien.add(
                        new NhanVien(
                                resultSet.getString("maNhanVien"),
                                resultSet.getString("tenNhanVien"),
                                resultSet.getDate("ngaySinh").toLocalDate(),
                                resultSet.getBoolean("gioiTinh"),
                                resultSet.getString("cmnd"),
                                resultSet.getString("soDienThoai"),
                                resultSet.getString("email")
                        )
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachNhanVien;
    }
}
