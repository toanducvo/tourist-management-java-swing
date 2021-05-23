package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.ChuyenDi;
import tourist.management.entity.DiemDen;
import tourist.management.entity.DiemXuatPhat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    
    public boolean createChuyenDi(ChuyenDi chuyenDi) throws SQLIntegrityConstraintViolationException {
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int rowEffected = 0;
        try {
            statement = connection.prepareStatement("INSERT INTO ChuyenDi VALUES (?,?,?,?,?,?)");
            statement.setString(1, chuyenDi.getMaChuyenDi());
            statement.setString(2, chuyenDi.getDiemXuatPhat().getMaDiemXuatPhat());
            statement.setString(3, chuyenDi.getDiemDen().getMaDiemDen());
            statement.setTimestamp(4, Timestamp.valueOf(chuyenDi.getNgayGioDi()));
            statement.setTimestamp(5, Timestamp.valueOf(chuyenDi.getNgayGioDen()));
            statement.setString(6, chuyenDi.getBienSoXe());
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
    
    public ArrayList<ChuyenDi> getChuyenDiTheoTen(String TenDiemDenCanTim) {
		ArrayList<ChuyenDi> dsChuyenDi = new ArrayList<ChuyenDi>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from ChuyenDi where maDiemDen=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, TenDiemDenCanTim);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				dsChuyenDi.add(
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
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return dsChuyenDi;
	}
    
}
