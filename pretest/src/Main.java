// class hardisk
class Harddisk {
    String merk;
    int kapasitas;

    // Constructor
    Harddisk(String merk, int kapasitas) {
        this.merk = merk;
        this.kapasitas = kapasitas;
    }
}

// clas monitor
class Monitor {
    String merk;
    int ukuran;

    // Constructor
    Monitor(String merk, int ukuran) {
        this.merk = merk;
        this.ukuran = ukuran;
    }
}

// class KomputerServer
class KomputerServer {
    String namaServer;

    // KOMPOSISI
    // Harddisk dibuat di dalam server dan bergantung penuh pada server
    Harddisk harddisk;

    // AGREGASI
    // Monitor dapat berdiri sendiri dan dilepas dari server
    Monitor[] monitor;

    // Constructor
    KomputerServer(String namaServer,
                    String merkHarddisk,
                    int kapasitasHarddisk,
                    Monitor[] monitor) {

        this.namaServer = namaServer;

        // Komposisi
        this.harddisk = new Harddisk(merkHarddisk, kapasitasHarddisk);

        // Agregasi
        this.monitor = monitor;
    }

    // Method menampilkan data
    void tampilkanInfo() {
        System.out.println("Nama Server : " + namaServer);

        System.out.println("Harddisk : "
                + harddisk.merk
                + " - "
                + harddisk.kapasitas
                + "GB");

        System.out.println("Daftar Monitor : ");
        for (int i = 0; i < monitor.length; i++) {
            System.out.println(
                    (i + 1) + ". "
                    + monitor[i].merk
                    + " - "
                    + monitor[i].ukuran
                    + " inch");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        // Object monitor dibuat terpisah
        Monitor m1 = new Monitor("Samsung", 24);
        Monitor m2 = new Monitor("LG", 27);

        Monitor[] daftarMonitor = {m1, m2};

        // Membuat server
        KomputerServer server =
                new KomputerServer(
                        "Server Utama",
                        "Seagate",
                        1000,
                        daftarMonitor);

        // Menampilkan informasi
        server.tampilkanInfo();
    }
}

    /*PENJELASAN RELASI OBJEK //
    ASOSIASI
   Hubungan umum antar object.
   Contoh:
   KomputerServer berhubungan dengan Monitor.
    
   AGREGASI
   Relasi "has-a" tetapi object masih bisa berdiri sendiri.
   Pada program ini:
   KomputerServer memiliki Monitor[].
   Monitor tetap bisa ada walaupun server dihapus.

3. //KOMPOSISI
   Relasi "has-a" yang sangat kuat.
   Pada program ini:
   Harddisk dibuat langsung di dalam KomputerServer.
   Jika server dihapus maka harddisk ikut hilang.

    */