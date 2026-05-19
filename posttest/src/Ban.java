public class Ban {
    private String merk;
    private int ukuranRing;

    public Ban(String merk, int ukuranRing) {
        this.merk = merk;
        this.ukuranRing = ukuranRing;
    }

    public void tampilkanBan() {
        System.out.println("Merk Ban   : " + merk);
        System.out.println("Ukuran Ring: " + ukuranRing);
    }
}