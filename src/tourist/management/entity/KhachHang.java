package tourist.management.entity;

import java.time.LocalDate;

public class KhachHang {
    private String maKhachHang;
    private String hoKhachHang;
    private String tenKhachHang;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String soCMND;
    private String soDienThoai;
    private String email;

    public KhachHang() {
        this("KH000000", "Không xác định", "Không xác định", false, LocalDate.now(), "Không xác định", "Không xác định", "Không xác định");
    }

    public KhachHang(String maKhachHang) {
        setMaKhachHang(maKhachHang);
        setHoKhachHang("Không xác định");
        setTenKhachHang("Không xác định");
        setGioiTinh(false);
        setNgaySinh(LocalDate.now());
        setSoCMND("Không xác định");
        setSoDienThoai("Không xác định");
        setEmail("Không xác định");
    }

    public KhachHang(String maKhachHang, String hoKhachHang, String tenKhachHang, boolean gioiTinh, LocalDate ngaySinh, String soCMND, String soDienThoai, String email) {
        setMaKhachHang(maKhachHang);
        setHoKhachHang(hoKhachHang);
        setTenKhachHang(tenKhachHang);
        setGioiTinh(gioiTinh);
        setNgaySinh(ngaySinh);
        setSoCMND(soCMND);
        setSoDienThoai(soDienThoai);
        setEmail(email);
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoKhachHang() {
        return hoKhachHang;
    }

    public void setHoKhachHang(String hoKhachHang) {
        this.hoKhachHang = hoKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhachHang)) return false;

        KhachHang khachHang = (KhachHang) o;

        return getMaKhachHang().equals(khachHang.getMaKhachHang());
    }

    @Override
    public int hashCode() {
        return getMaKhachHang().hashCode();
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", hoKhachHang='" + hoKhachHang + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", ngaySinh=" + ngaySinh +
                ", soCMND='" + soCMND + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
