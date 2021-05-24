package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemDen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiemDenDAO {

    /**
     * @return danh sách các điểm du lịch
     */
    public List<DiemDen> getAllDiemDen() {
        List<DiemDen> danhSachDiemDen = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM DiemDen";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                danhSachDiemDen.add(
                        new DiemDen(
                                resultSet.getString("maDiemDen"),
                                resultSet.getString("tenDiemDen"),
                                resultSet.getString("tenTinh")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachDiemDen;
    }

    /**
     * @param diemDen địa điểm du lịch
     * @return true nếu chèn điểm đến thành công, ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu mã điểm đến bị trùng
     */
    public boolean createDiemDen(DiemDen diemDen) throws SQLIntegrityConstraintViolationException {
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int rowEffected = 0;
        try {
            statement = connection.prepareStatement("INSERT INTO DiemDen VALUES (?,?,?)");
            statement.setString(1, diemDen.getMaDiemDen());
            statement.setString(2, diemDen.getTenDiemDen());
            statement.setString(3, diemDen.getTenTinh());
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

    public ArrayList<DiemDen> getDiemDenTheoTen(String TenDiemDenCanTim) {
        ArrayList<DiemDen> dsDiemDen = new ArrayList<DiemDen>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from DiemDen where tenDiemDen=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, TenDiemDenCanTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDiemDen = rs.getString(1);
                String tenDiemDen = rs.getString(2);
                String tenTinh = rs.getString(3);

                DiemDen diemDen = new DiemDen(maDiemDen, tenDiemDen, tenTinh);
                dsDiemDen.add(diemDen);
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
        return dsDiemDen;
    }

    public ArrayList<DiemDen> getDiemDenTheoTinh(String tenTinhCanTim) {
        ArrayList<DiemDen> dsDiemDen = new ArrayList<DiemDen>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from DiemDen where tenTinh=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, tenTinhCanTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDiemDen = rs.getString(1);
                String tenDiemDen = rs.getString(2);
                String tenTinh = rs.getString(3);

                DiemDen diemDen = new DiemDen(maDiemDen, tenDiemDen, tenTinh);
                dsDiemDen.add(diemDen);
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
        return dsDiemDen;
    }
}