package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemXuatPhat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
