public class MainClass {
    public static void main(String[] args) {

        // membuat 4 ban independen
        Ban ban1 = new Ban("Bridgestone", 17);
        Ban ban2 = new Ban("Bridgestone", 17);
        Ban ban3 = new Ban("Bridgestone", 17);
        Ban ban4 = new Ban("Bridgestone", 17);

        Ban[] kumpulanBan = {ban1, ban2, ban3, ban4};

        // membuat mobil
        Mobil mobil = new Mobil(
                "Toyota Supra",
                "Merah",
                "MSN-001",
                2000
        );

        // memasang ban ke mobil
        mobil.pasangSetBan(kumpulanBan);

        // menampilkan spesifikasi
        mobil.tampilkanSpesifikasi();

        // membuat montir
        Montir montir = new Montir("M001", "Budi");

        // quality control
        montir.lakukanQualityControl(mobil);

        // mobil gagal uji dihancurkan
        mobil.hancurkanMobil();

        // membuktikan mesin hilang
        System.out.println("\nSETELAH MOBIL DIHANCURKAN");
        mobil.tampilkanSpesifikasi();

        // membuktikan ban masih ada
        System.out.println("\nBAN MASIH TERSIMPAN:");
        ban1.tampilkanBan();
    }
}