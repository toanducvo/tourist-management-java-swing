package tourist.management.entity;

public class DiemXuatPhat {
    private String maDiemXuatPhat;
    private String tenDiemXuatPhat;
    private String tenTinh;

    public DiemXuatPhat(String maDiemXuatPhat) {
        setMaDiemXuatPhat(maDiemXuatPhat);
        setTenDiemXuatPhat("Không xác định");
        setTenTinh("Không xác định");
    }

    public DiemXuatPhat(String maDiemXuatPhat, String tenDiemXuatPhat) {
        setMaDiemXuatPhat(maDiemXuatPhat);
        setTenDiemXuatPhat(tenDiemXuatPhat);
    }

    public DiemXuatPhat(String maDiemXuatPhat, String tenDiemXuatPhat, String tenTinh) {
        setMaDiemXuatPhat(maDiemXuatPhat);
        setTenDiemXuatPhat(tenDiemXuatPhat);
        setTenTinh(tenTinh);
    }

    public String getMaDiemXuatPhat() {
        return maDiemXuatPhat;
    }

    public void setMaDiemXuatPhat(String maDiemXuatPhat) {
        this.maDiemXuatPhat = maDiemXuatPhat;
    }

    public String getTenDiemXuatPhat() {
        return tenDiemXuatPhat;
    }

    public void setTenDiemXuatPhat(String tenDiemXuatPhat) {
        this.tenDiemXuatPhat = tenDiemXuatPhat;
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
        if (!(o instanceof DiemXuatPhat)) return false;

        DiemXuatPhat that = (DiemXuatPhat) o;

        return getMaDiemXuatPhat().equals(that.getMaDiemXuatPhat());
    }

    @Override
    public int hashCode() {
        return getMaDiemXuatPhat().hashCode();
    }

    @Override
    public String toString() {
        return "DiemXuatPhat{" +
                "maDiemXuatPhat='" + maDiemXuatPhat + '\'' +
                ", tenDiemXuatPhat='" + tenDiemXuatPhat + '\'' +
                ", tenTinh='" + tenTinh + '\'' +
                '}';
    }
}
