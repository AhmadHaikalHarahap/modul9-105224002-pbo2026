public class StudiKasusRumahSakit {
    public static void main(String[] args) {
        SistemPusat sistemPusat = new SistemPusat();

        Dokter dokter1 = new Dokter("Dr. Rina", "Anak");
        Dokter dokter2 = new Dokter("Dr. Andi", "Penyakit Dalam");
        Pasien pasien1 = new Pasien("Budi", 12);
        Pasien pasien2 = new Pasien("Siti", 35);

        sistemPusat.tambahDokter(dokter1);
        sistemPusat.tambahDokter(dokter2);
        sistemPusat.tambahPasien(pasien1);
        sistemPusat.tambahPasien(pasien2);

        System.out.println("=== Data Sistem Pusat ===");
        sistemPusat.tampilkanDokter();
        sistemPusat.tampilkanPasien();

        System.out.println();
        dokter1.periksaPasien(pasien1);

        System.out.println();
        System.out.println("========================");
        System.out.println();

        RumahSakit rumahSakit = new RumahSakit("RS Sehat Selalu");
        rumahSakit.tugaskanDokter(dokter1);
        rumahSakit.tugaskanDokter(dokter2);

        rumahSakit.tampilkanDaftarRuangan();
        System.out.println();
        rumahSakit.tampilkanDokterBertugas();

        System.out.println();
        System.out.println("=== Uji Lifecycle Objek ===");
        rumahSakit = null;
        System.out.println("Objek rumahSakit sudah di-null-kan.");

        System.out.println();
        System.out.println("Dokter dan pasien dari sistem pusat masih bisa diakses:");
        System.out.println("- Dokter 1: " + dokter1.getNama() + " (" + dokter1.getSpesialisasi() + ")");
        System.out.println("- Dokter 2: " + dokter2.getNama() + " (" + dokter2.getSpesialisasi() + ")");
        System.out.println("- Pasien 1: " + pasien1.getNama() + ", umur " + pasien1.getUmur() + " tahun");
        System.out.println("- Pasien 2: " + pasien2.getNama() + ", umur " + pasien2.getUmur() + " tahun");
    }
}

class SistemPusat {
    private Dokter[] daftarDokter;
    private Pasien[] daftarPasien;
    private int jumlahDokter;
    private int jumlahPasien;

    public SistemPusat() {
        daftarDokter = new Dokter[10];
        daftarPasien = new Pasien[10];
        jumlahDokter = 0;
        jumlahPasien = 0;
    }

    public void tambahDokter(Dokter dokter) {
        if (jumlahDokter < daftarDokter.length) {
            daftarDokter[jumlahDokter] = dokter;
            jumlahDokter++;
        }
    }

    public void tambahPasien(Pasien pasien) {
        if (jumlahPasien < daftarPasien.length) {
            daftarPasien[jumlahPasien] = pasien;
            jumlahPasien++;
        }
    }

    public void tampilkanDokter() {
        System.out.println("Daftar Dokter:");
        for (int i = 0; i < jumlahDokter; i++) {
            System.out.println("- " + daftarDokter[i].getNama() + " (" + daftarDokter[i].getSpesialisasi() + ")");
        }
    }

    public void tampilkanPasien() {
        System.out.println("Daftar Pasien:");
        for (int i = 0; i < jumlahPasien; i++) {
            System.out.println("- " + daftarPasien[i].getNama() + ", umur " + daftarPasien[i].getUmur() + " tahun");
        }
    }
}

class Dokter {
    private String nama;
    private String spesialisasi;

    public Dokter(String nama, String spesialisasi) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }

    public String getNama() {
        return nama;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void periksaPasien(Pasien pasien) {
        System.out.println("=== Proses Pemeriksaan ===");
        System.out.println("Dokter       : " + nama);
        System.out.println("Spesialisasi : " + spesialisasi);
        System.out.println("Pasien       : " + pasien.getNama());
        System.out.println("Umur Pasien  : " + pasien.getUmur() + " tahun");
        System.out.println(nama + " sedang memeriksa pasien " + pasien.getNama());
    }
}

class Pasien {
    private String nama;
    private int umur;

    public Pasien(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }
}

class RumahSakit {
    private String namaRumahSakit;
    private Ruangan[] daftarRuangan;
    private Dokter[] dokterBertugas;
    private int jumlahDokterBertugas;

    public RumahSakit(String namaRumahSakit) {
        this.namaRumahSakit = namaRumahSakit;
        this.daftarRuangan = new Ruangan[2];
        this.dokterBertugas = new Dokter[5];
        this.jumlahDokterBertugas = 0;
        bangunRuangan();
    }

    private void bangunRuangan() {
        daftarRuangan[0] = new Ruangan("R-01", 10);
        daftarRuangan[1] = new Ruangan("R-02", 20);
    }

    public void tugaskanDokter(Dokter dokter) {
        if (jumlahDokterBertugas < dokterBertugas.length) {
            dokterBertugas[jumlahDokterBertugas] = dokter;
            jumlahDokterBertugas++;
        }
    }

    public void tampilkanDaftarRuangan() {
        System.out.println("=== Daftar Ruangan di " + namaRumahSakit + " ===");
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan != null) {
                ruangan.tampilkanInfo();
            }
        }
    }

    public void tampilkanDokterBertugas() {
        System.out.println("=== Dokter Bertugas di " + namaRumahSakit + " ===");
        for (int i = 0; i < jumlahDokterBertugas; i++) {
            System.out.println("- " + dokterBertugas[i].getNama() + " (" + dokterBertugas[i].getSpesialisasi() + ")");
        }
    }

    private class Ruangan {
        private String nomorRegistrasi;
        private int kapasitasMaksimal;

        public Ruangan(String nomorRegistrasi, int kapasitasMaksimal) {
            this.nomorRegistrasi = nomorRegistrasi;
            this.kapasitasMaksimal = kapasitasMaksimal;
        }

        public void tampilkanInfo() {
            System.out.println("Nomor Ruangan     : " + nomorRegistrasi);
            System.out.println("Kapasitas Maksimal: " + kapasitasMaksimal + " pasien");
            System.out.println("---------------------------");
        }
    }
}
