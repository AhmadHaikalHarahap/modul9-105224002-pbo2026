public class Mobil {
    private String merkMobil;
    private String warna;

    private Mesin mesin;
    private Ban[] setBan;

    public Mobil(String merkMobil, String warna,
                 String nomorSeriMesin, int kapasitasCC) {

        this.merkMobil = merkMobil;
        this.warna = warna;

        // Mesin dibuat langsung di constructor
        this.mesin = new Mesin(nomorSeriMesin, kapasitasCC);

        // maksimal 4 ban
        setBan = new Ban[4];
    }

    public void pasangSetBan(Ban[] setBan) {
        if (setBan.length <= 4) {
            this.setBan = setBan;
        } else {
            System.out.println("Ban melebihi kapasitas!");
        }
    }

    public void tampilkanSpesifikasi() {
        System.out.println("===== SPESIFIKASI MOBIL =====");
        System.out.println("Merk Mobil : " + merkMobil);
        System.out.println("Warna      : " + warna);

        System.out.println("\n--- Detail Mesin ---");
        if (mesin != null) {
            mesin.tampilkanMesin();
        } else {
            System.out.println("Mesin sudah hancur.");
        }

        System.out.println("\n--- Detail Ban ---");
        for (int i = 0; i < setBan.length; i++) {
            if (setBan[i] != null) {
                System.out.println("Ban ke-" + (i + 1));
                setBan[i].tampilkanBan();
                System.out.println();
            }
        }
    }

    // mobil dihancurkan
    public void hancurkanMobil() {
        System.out.println("\nMobil dihancurkan...");
        mesin = null; // mesin ikut hilang
    }
}