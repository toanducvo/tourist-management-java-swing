package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DiemDen;
import tourist.management.entity.DiemXuatPhat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDiDAO {

    /**
     * @return danh sách các chuyến đi
     */
    public List<ChuyenDi> getAllChuyenDi() {
        List<ChuyenDi> danhSachChuyenDi = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM ChuyenDi";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                danhSachChuyenDi.add(
                        new ChuyenDi(
                                resultSet.getString("maChuyenDi"),
                                new DiemXuatPhat(resultSet.getString("maDiemXuatPhat")),
                                new DiemDen(resultSet.getString("maDiemDen")),
                                resultSet.getTimestamp("ngayGioDi").toLocalDateTime(),
                                resultSet.getTimestamp("ngayGioDen").toLocalDateTime(),
                                resultSet.getString("bienSoXe")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachChuyenDi;
    }
}
