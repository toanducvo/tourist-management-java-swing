package tourist.management.dao;

import tourist.management.database.ConnectDB;
import tourist.management.entity.KhachHang;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    /**
     * @param khachHang khách hàng mua vé
     * @return true nếu chèn thành công, ngược lại false
     * @throws SQLIntegrityConstraintViolationException nếu chèn trùng mã khách hàng
     */
    public boolean createKhachHang(KhachHang khachHang)  {
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
     * @return danh sách tất cả khách hàng
     */
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM KhachHang";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    /**
     * @param maKhachHang mã khách hàng
     * @return danh sách khách hàng theo mã
     */
    public List<KhachHang> getKhachHangTheoMa(String maKhachHang) {
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
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachKhachHang;
    }

    /**
     *
     * @param tenKhachHang tên khách hàng
     * @return danh sách khách hàng
     */
    public List<KhachHang> getKhachHangTheoTen(String tenKhachHang) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE tenKhachHang LIKE ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenKhachHang);
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
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachKhachHang;
    }

    /**
     *
     * @param ngaySinh ngày sinh
     * @return danh sách khách hàng đã được lọc
     */
    public List<KhachHang> getKhachHangTheoNgaySinh(LocalDate ngaySinh) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE DAY(ngaySinh) = ? AND MONTH(ngaySinh) = ? AND YEAR(ngaySinh) = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ngaySinh.getDayOfMonth());
            preparedStatement.setInt(2, ngaySinh.getMonth().getValue());
            preparedStatement.setInt(3, ngaySinh.getYear());
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
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return danhSachKhachHang;
    }

    /**
     *
     * @param cmnd chứng minh nhân dân
     * @return danh sách Khách hàng
     */
    public List<KhachHang> getKhachHangTheoCMND(String cmnd) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE soCMND = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cmnd);
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
        } finally {
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
