public class Montir {
    private String idMontir;
    private String nama;

    public Montir(String idMontir, String nama) {
        this.idMontir = idMontir;
        this.nama = nama;
    }

    public void lakukanQualityControl(Mobil m) {
        System.out.println("\n===== QUALITY CONTROL =====");
        System.out.println("Montir : " + nama);
        System.out.println("Melakukan pengecekan mobil...");
        System.out.println("Hasil : GAGAL UJI KELAYAKAN");
    }
}