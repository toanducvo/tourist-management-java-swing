package tourist.management.entity;

public class DiemDen {
    private String maDiemDen;
    private String tenDiemDen;
    private String tenTinh;

    public DiemDen(String maDiemDen) {
        setMaDiemDen(maDiemDen);
        setTenDiemDen("Không xác định");
    }

    public DiemDen(String maDiemDen, String tenDiemDen) {
        setMaDiemDen(maDiemDen);
        setTenDiemDen(tenDiemDen);
    }

    public DiemDen(String maDiemDen, String tenDiemDen, String tenTinh) {
        setMaDiemDen(maDiemDen);
        setTenDiemDen(tenDiemDen);
        setTenTinh(tenTinh);
    }

    public String getMaDiemDen() {
        return maDiemDen;
    }

    public void setMaDiemDen(String maDiemDen) {
        this.maDiemDen = maDiemDen;
    }

    public String getTenDiemDen() {
        return tenDiemDen;
    }

    public void setTenDiemDen(String tenDiemDen) {
        this.tenDiemDen = tenDiemDen;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiemDen)) return false;

        DiemDen diemDen = (DiemDen) o;

        return getMaDiemDen().equals(diemDen.getMaDiemDen());
    }

    @Override
    public int hashCode() {
        return getMaDiemDen().hashCode();
    }

    @Override
    public String toString() {
        return "DiemDen{" +
                "maDiemDen='" + maDiemDen + '\'' +
                ", tenDiemDen='" + tenDiemDen + '\'' +
                ", tenTinh='" + tenTinh + '\'' +
                '}';
    }
}
