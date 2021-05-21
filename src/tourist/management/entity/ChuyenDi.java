package tourist.management.entity;

import java.time.LocalDateTime;

public class ChuyenDi {
    private String maChuyenDi;
    private DiemXuatPhat diemXuatPhat;
    private DiemDen diemDen;
    private LocalDateTime ngayGioDi;
    private LocalDateTime ngayGioDen;
    private String bienSoXe;

    public ChuyenDi(String maChuyenDi) {
        setMaChuyenDi(maChuyenDi);
        setDiemXuatPhat(null);
        setDiemDen(null);
        setNgayGioDi(LocalDateTime.now());
        setNgayGioDen(LocalDateTime.now());
        setBienSoXe("Không xác định");
    }

    public ChuyenDi(String maChuyenDi, DiemXuatPhat diemXuatPhat, DiemDen diemDen, LocalDateTime ngayGioDi, LocalDateTime ngayGioDen, String bienSoXe) {
        setMaChuyenDi(maChuyenDi);
        setDiemXuatPhat(diemXuatPhat);
        setDiemDen(diemDen);
        setNgayGioDi(ngayGioDi);
        setNgayGioDen(ngayGioDen);
        setBienSoXe(bienSoXe);
    }

    public String getMaChuyenDi() {
        return maChuyenDi;
    }

    public void setMaChuyenDi(String maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    public DiemXuatPhat getDiemXuatPhat() {
        return diemXuatPhat;
    }

    public void setDiemXuatPhat(DiemXuatPhat diemXuatPhat) {
        this.diemXuatPhat = diemXuatPhat;
    }

    public DiemDen getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(DiemDen diemDen) {
        this.diemDen = diemDen;
    }

    public LocalDateTime getNgayGioDi() {
        return ngayGioDi;
    }

    public void setNgayGioDi(LocalDateTime ngayGioDi) {
        this.ngayGioDi = ngayGioDi;
    }

    public LocalDateTime getNgayGioDen() {
        return ngayGioDen;
    }

    public void setNgayGioDen(LocalDateTime ngayGioDen) {
        this.ngayGioDen = ngayGioDen;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChuyenDi)) return false;

        ChuyenDi chuyenDi = (ChuyenDi) o;

        return getMaChuyenDi().equals(chuyenDi.getMaChuyenDi());
    }

    @Override
    public int hashCode() {
        return getMaChuyenDi().hashCode();
    }

    @Override
    public String toString() {
        return "ChuyenDi{" +
                "maChuyenDi='" + maChuyenDi + '\'' +
                ", diemXuatPhat=" + diemXuatPhat +
                ", diemDen=" + diemDen +
                ", ngayGioDi=" + ngayGioDi +
                ", ngayGioDen=" + ngayGioDen +
                ", bienSoXe='" + bienSoXe + '\'' +
                '}';
    }
}
