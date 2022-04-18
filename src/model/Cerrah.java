package model;

public class Cerrah extends Doktor  {

    private String unvan;

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public Cerrah(int id, String isimSoyisim, String telefonNo, String cinsiyet,String unvan) {
        super(id, isimSoyisim, telefonNo, cinsiyet);
        this.unvan = unvan;
    }


    @Override
    public String toString() {
        return getId() + "\t"+ getIsimSoyisim() + "\t"+ getTelefonNo() + "\t" + getCinsiyet() + "\t" + getUnvan();
    }

   

}
