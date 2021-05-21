package tourist.management.entity;

import java.time.LocalDate;

public class NhanVien {
    private String maNhanVien;
    private String tenNhanVien;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String cmnd;
    private String soDienThoai;
    private String email;

    public NhanVien() {
        this("NV000000", "Không xác định", LocalDate.now(), false, "Không xác định", "Không xác định", "Không xác định");
    }

    public NhanVien(String maNhanVien) {
        setMaNhanVien(maNhanVien);
        setTenNhanVien("Không xác định");
        setNgaySinh(LocalDate.now());
        setGioiTinh(false);
        setCmnd("Không xác định");
        setSoDienThoai("Không xác định");
        setEmail("Không xác định");
    }

    public NhanVien(String maNhanVien, String tenNhanVien, LocalDate ngaySinh, boolean gioiTinh, String cmnd, String soDienThoai, String email) {
        setMaNhanVien(maNhanVien);
        setTenNhanVien(tenNhanVien);
        setNgaySinh(ngaySinh);
        setGioiTinh(gioiTinh);
        setCmnd(cmnd);
        setSoDienThoai(soDienThoai);
        setEmail(email);
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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
        if (!(o instanceof NhanVien)) return false;

        NhanVien nhanVien = (NhanVien) o;

        return getMaNhanVien().equals(nhanVien.getMaNhanVien());
    }

    @Override
    public int hashCode() {
        return getMaNhanVien().hashCode();
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", cmnd='" + cmnd + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
