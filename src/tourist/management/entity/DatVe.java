package tourist.management.entity;

import java.time.LocalDateTime;

public class DatVe {
    private KhachHang khachHang;
    private ChuyenDi chuyenDi;
    private NhanVien nhanVien;
    private LocalDateTime ngayDatVe;

    public DatVe(KhachHang khachHang, ChuyenDi chuyenDi, NhanVien nhanVien, LocalDateTime ngayDatVe) {
        setKhachHang(khachHang);
        setChuyenDi(chuyenDi);
        setNhanVien(nhanVien);
        setNgayDatVe(ngayDatVe);
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ChuyenDi getChuyenDi() {
        return chuyenDi;
    }

    public void setChuyenDi(ChuyenDi chuyenDi) {
        this.chuyenDi = chuyenDi;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDateTime getNgayDatVe() {
        return ngayDatVe;
    }

    public void setNgayDatVe(LocalDateTime ngayDatVe) {
        this.ngayDatVe = ngayDatVe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatVe)) return false;

        DatVe datVe = (DatVe) o;

        return getKhachHang().equals(datVe.getKhachHang());
    }

    @Override
    public int hashCode() {
        return getKhachHang().hashCode();
    }

    @Override
    public String toString() {
        return "DatVe{" +
                "khachHang=" + khachHang +
                ", chuyenDi=" + chuyenDi +
                ", nhanVien=" + nhanVien +
                ", ngayDatVe=" + ngayDatVe +
                '}';
    }
}
