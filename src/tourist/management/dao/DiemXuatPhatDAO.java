package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.DiemXuatPhat;

import java.sql.*;
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

    /**
     *
     * @param diemXuatPhat điểm xuất phát
     * @return true nếu thêm thành công, ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu thêm trùng mã
     */
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

    /**
     *
     * @param tenTinhCanTim tên tỉnh
     * @return danh sách Điểm xuất phát
     */
    public List<DiemXuatPhat> getDiemXuatPhatTheoTinh(String tenTinhCanTim) {
        List<DiemXuatPhat> dsDiemXuatPhat = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from DiemXuatPhat where tenTinh=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tenTinhCanTim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maDiemXuatPhat = rs.getString(1);
                String tenDiemXuatPhat = rs.getString(2);
                String tenTinh = rs.getString(3);
                DiemXuatPhat diemXuatPhat = new DiemXuatPhat(maDiemXuatPhat, tenDiemXuatPhat, tenTinh);
                dsDiemXuatPhat.add(diemXuatPhat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsDiemXuatPhat;
    }

    /**
     * @param timMaDiemXuatPhat mã điểm xuất phát
     * @return true nếu xóa thành công, ngược lại false
     */
    public boolean xoaMaDiemXuatPhat(String timMaDiemXuatPhat) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String sql = "delete from DatVe where maChuyenDi =?"
                    + "delete from ChuyenDi where maDiemXuatPhat ='?"
                    + "delete from DiemXuatPhat where maDiemXuatPhat=? ";
            statement.setString(1, timMaDiemXuatPhat);
            statement.setString(2, timMaDiemXuatPhat);
            statement.setString(3, timMaDiemXuatPhat);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
}
