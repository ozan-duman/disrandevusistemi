/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RecepOrkun
 */
public class Ortodonti extends Doktor {

    private String unvan;

    public Ortodonti(int id, String isimSoyisim, String telefonNo, String cinsiyet,String unvan) {
        super(id, isimSoyisim, telefonNo, cinsiyet);
        this.unvan = unvan;
    }

    @Override
    public String toString() {
       return getId() + "\t"+ getIsimSoyisim() + "\t"+ getTelefonNo() + "\t" + getCinsiyet() + "\t" + getUnvan();
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    
   
}
