import java.util.Scanner;

/*
====================================================
INTERFACE
====================================================
*/
interface Otorisasi {
    boolean verifikasiPIN(int pin);
}

/*
====================================================
KOMPOSISI
====================================================
*/
class BukuMutasi {

    public void catat(String aktivitas) {
        System.out.println("[MUTASI] " + aktivitas);
    }
}

/*
====================================================
ABSTRACT CLASS
====================================================
*/
abstract class Rekening implements Otorisasi {

    protected String nomorRekening;
    protected String namaPemilik;

    // ENKAPSULASI
    private double saldo;

    // KOMPOSISI
    protected BukuMutasi mutasi;

    public Rekening(String nomorRekening,
                     String namaPemilik,
                     double saldoAwal) {

        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldoAwal;

        // Objek mutasi dibuat otomatis
        this.mutasi = new BukuMutasi();
    }

    // Getter saldo
    public double getSaldo() {
        return saldo;
    }

    // Method setor
    public void setor(double jumlah) {

        saldo += jumlah;

        mutasi.catat(
                "Setor Rp" + jumlah +
                " | Saldo sekarang: Rp" + saldo
        );
    }

    // Method abstract
    public abstract void tarik(double jumlah);

    // Implementasi interface
    @Override
    public boolean verifikasiPIN(int pin) {

        return pin == 1234;
    }

    // Tampilkan info rekening
    public void tampilInfo() {

        System.out.println("No Rekening : " + nomorRekening);
        System.out.println("Nama        : " + namaPemilik);
        System.out.println("Saldo       : Rp" + getSaldo());
    }

    // Mengurangi saldo
    protected void kurangiSaldo(double jumlah) {

        saldo -= jumlah;
    }
}

/*
====================================================
PEWARISAN + POLIMORFISME
====================================================
*/
class RekeningReguler extends Rekening {

    public RekeningReguler(String nomorRekening,
                           String namaPemilik,
                           double saldoAwal) {

        super(nomorRekening, namaPemilik, saldoAwal);
    }

    // OVERRIDING
    @Override
    public void tarik(double jumlah) {

        double biayaAdmin = 5000;
        double total = jumlah + biayaAdmin;

        if (getSaldo() >= total) {

            kurangiSaldo(total);

            mutasi.catat(
                    "Tarik Rp" + jumlah +
                    " + admin Rp5000" +
                    " | Saldo sekarang: Rp" + getSaldo()
            );

        } else {

            System.out.println("Saldo tidak cukup!");
        }
    }
}

/*
====================================================
PEWARISAN + POLIMORFISME
====================================================
*/
class RekeningPrioritas extends Rekening {

    public RekeningPrioritas(String nomorRekening,
                             String namaPemilik,
                             double saldoAwal) {

        super(nomorRekening, namaPemilik, saldoAwal);
    }

    // OVERRIDING
    @Override
    public void tarik(double jumlah) {

        double saldoMinimum = 100000;

        if ((getSaldo() - jumlah) >= saldoMinimum) {

            kurangiSaldo(jumlah);

            mutasi.catat(
                    "Tarik Rp" + jumlah +
                    " | Saldo sekarang: Rp" + getSaldo()
            );

        } else {

            System.out.println(
                    "Gagal! Saldo minimum prioritas harus Rp100000"
            );
        }
    }
}

/*
====================================================
AGREGASI
====================================================
*/
class Nasabah {

    String nama;

    // Maksimal 3 rekening
    Rekening[] daftarRekening;

    int jumlahRekening = 0;

    public Nasabah(String nama) {

        this.nama = nama;

        daftarRekening = new Rekening[3];
    }

    // Tambah rekening
    public void tambahRekening(Rekening rekening) {

        if (jumlahRekening < 3) {

            daftarRekening[jumlahRekening] = rekening;

            jumlahRekening++;

            System.out.println(
                    "Rekening berhasil ditambahkan."
            );

        } else {

            System.out.println(
                    "Maksimal rekening hanya 3."
            );
        }
    }

    // Menampilkan rekening
    public void tampilkanRekening() {

        if (jumlahRekening == 0) {

            System.out.println(
                    "Belum ada rekening."
            );

            return;
        }

        for (int i = 0; i < jumlahRekening; i++) {

            System.out.println(
                    "\nRekening ke-" + (i + 1)
            );

            daftarRekening[i].tampilInfo();
        }
    }
}

/*
====================================================
ASOSIASI
====================================================
*/
class CustomerService {

    public void laporKeluhan(
            Nasabah nasabah,
            String keluhan) {

        System.out.println(
                "\n===== CUSTOMER SERVICE ====="
        );

        System.out.println(
                "Nasabah : " + nasabah.nama
        );

        System.out.println(
                "Keluhan : " + keluhan
        );

        System.out.println(
                "Keluhan berhasil dicatat."
        );
    }
}

/*
====================================================
MAIN CLASS
====================================================
*/
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Nasabah nasabah = null;

        CustomerService cs =
                new CustomerService();

        int pilihan;

        do {

            System.out.println(
                    "\n===== MENU BANK ====="
            );

            System.out.println(
                    "1. Registrasi Nasabah"
            );

            System.out.println(
                    "2. Tambah Rekening"
            );

            System.out.println(
                    "3. Lihat Rekening"
            );

            System.out.println(
                    "4. Setor"
            );

            System.out.println(
                    "5. Tarik"
            );

            System.out.println(
                    "6. Customer Service"
            );

            System.out.println(
                    "7. Tutup Akun"
            );

            System.out.println(
                    "0. Keluar"
            );

            System.out.print(
                    "Pilih menu: "
            );

            pilihan = input.nextInt();

            switch (pilihan) {

                /*
                ====================================
                REGISTRASI NASABAH
                ====================================
                */
                case 1:

                    input.nextLine();

                    System.out.print(
                            "Masukkan nama nasabah: "
                    );

                    String nama =
                            input.nextLine();

                    nasabah =
                            new Nasabah(nama);

                    System.out.println(
                            "Nasabah berhasil dibuat."
                    );

                    break;

                /*
                ====================================
                TAMBAH REKENING
                ====================================
                */
                case 2:

                    if (nasabah == null) {

                        System.out.println(
                                "Daftarkan nasabah terlebih dahulu!"
                        );

                        break;
                    }

                    input.nextLine();

                    System.out.print(
                            "Nomor rekening: "
                    );

                    String norek =
                            input.nextLine();

                    System.out.println(
                            "1. Rekening Reguler"
                    );

                    System.out.println(
                            "2. Rekening Prioritas"
                    );

                    System.out.print(
                            "Pilih jenis rekening: "
                    );

                    int jenis =
                            input.nextInt();

                    System.out.print(
                            "Saldo awal: "
                    );

                    double saldo =
                            input.nextDouble();

                    Rekening rekening;

                    if (jenis == 1) {

                        rekening =
                                new RekeningReguler(
                                        norek,
                                        nasabah.nama,
                                        saldo
                                );

                    } else {

                        rekening =
                                new RekeningPrioritas(
                                        norek,
                                        nasabah.nama,
                                        saldo
                                );
                    }

                    nasabah.tambahRekening(rekening);

                    break;

                /*
                ====================================
                LIHAT REKENING
                ====================================
                */
                case 3:

                    if (nasabah != null) {

                        nasabah.tampilkanRekening();

                    } else {

                        System.out.println(
                                "Belum ada nasabah."
                        );
                    }

                    break;

                /*
                ====================================
                SETOR
                ====================================
                */
                case 4:

                    if (nasabah == null
                            || nasabah.jumlahRekening == 0) {

                        System.out.println(
                                "Belum ada rekening."
                        );

                        break;
                    }

                    System.out.print(
                            "Pilih rekening ke-1 sampai ke-"
                                    + nasabah.jumlahRekening
                                    + ": "
                    );

                    int idxSetor =
                            input.nextInt() - 1;

                    System.out.print(
                            "Jumlah setor: "
                    );

                    double setor =
                            input.nextDouble();

                    nasabah
                            .daftarRekening[idxSetor]
                            .setor(setor);

                    break;

                /*
                ====================================
                TARIK
                ====================================
                */
                case 5:

                    if (nasabah == null
                            || nasabah.jumlahRekening == 0) {

                        System.out.println(
                                "Belum ada rekening."
                        );

                        break;
                    }

                    System.out.print(
                            "Pilih rekening ke-1 sampai ke-"
                                    + nasabah.jumlahRekening
                                    + ": "
                    );

                    int idxTarik =
                            input.nextInt() - 1;

                    System.out.print(
                            "Masukkan PIN: "
                    );

                    int pin =
                            input.nextInt();

                    if (nasabah
                            .daftarRekening[idxTarik]
                            .verifikasiPIN(pin)) {

                        System.out.print(
                                "Jumlah tarik: "
                        );

                        double tarik =
                                input.nextDouble();

                        nasabah
                                .daftarRekening[idxTarik]
                                .tarik(tarik);

                    } else {

                        System.out.println(
                                "PIN salah!"
                        );
                    }

                    break;

                /*
                ====================================
                CUSTOMER SERVICE
                ====================================
                */
                case 6:

                    if (nasabah == null) {

                        System.out.println(
                                "Belum ada nasabah."
                        );

                        break;
                    }

                    input.nextLine();

                    System.out.print(
                            "Masukkan keluhan: "
                    );

                    String keluhan =
                            input.nextLine();

                    cs.laporKeluhan(
                            nasabah,
                            keluhan
                    );

                    break;

                /*
                ====================================
                TUTUP AKUN
                ====================================
                */
                case 7:

                    // Menghapus objek nasabah
                    nasabah = null;

                    System.out.println(
                            "Akun nasabah ditutup."
                    );

                    /*
                    ANALISIS OOP:

                    1. KOMPOSISI
                    Rekening memiliki BukuMutasi.
                    Saat Rekening hilang,
                    BukuMutasi ikut hilang.

                    2. AGREGASI
                    Nasabah memiliki Rekening.
                    Saat Nasabah dihapus,
                    Rekening secara konsep
                    masih bisa tersimpan
                    di sistem bank.
                    */

                    break;

                /*
                ====================================
                KELUAR
                ====================================
                */
                case 0:

                    System.out.println(
                            "Terima kasih."
                    );

                    break;

                default:

                    System.out.println(
                            "Pilihan tidak valid."
                    );
            }

        } while (pilihan != 0);

        input.close();
    }
}