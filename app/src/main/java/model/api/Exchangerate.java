package model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class Exchangerate {

@SerializedName("base")
@Expose
private String base;
@SerializedName("date")
@Expose
private String date;
@SerializedName("rates")
@Expose
private Rates rates;

/**
* 
* @return
* The base
*/
@SuppressWarnings("unused")
public String getBase() {
return base;
}

/**
* 
* @param base
* The base
*/
@SuppressWarnings("SameParameterValue")
public void setBase(String base) {
this.base = base;
}

/**
* 
* @return
* The date
*/
@SuppressWarnings("unused")
public String getDate() {
return date;
}

/**
* 
* @param date
* The date
*/
@SuppressWarnings("SameParameterValue")
public void setDate(String date) {
this.date = date;
}

/**
* 
* @return
* The rates
*/
public Rates getRates() {
return rates;
}

/**
* 
* @param rates
* The rates
*/
@SuppressWarnings("ALL")
public void setRates(Rates rates) {
this.rates = rates;
}

    @SuppressWarnings("unused")
    public static class Rates {

    @SerializedName("AUD")
    @Expose
    public String AUD;
    @SerializedName("BGN")
    @Expose
    public String BGN;
    @SerializedName("BRL")
    @Expose
    public String BRL;
    @SerializedName("CAD")
    @Expose
    public String CAD;
    @SerializedName("CHF")
    @Expose
    public String CHF;
    @SerializedName("CNY")
    @Expose
    public String CNY;
    @SerializedName("CZK")
    @Expose
    public String CZK;
    @SerializedName("DKK")
    @Expose
    public String DKK;
    @SerializedName("HKD")
    @Expose
    public String HKD;
    @SerializedName("HRK")
    @Expose
    public String HRK;
    @SerializedName("HUF")
    @Expose
    public String HUF;
    @SerializedName("IDR")
    @Expose
    public int IDR;
    @SerializedName("ILS")
    @Expose
    public String ILS;
    @SerializedName("INR")
    @Expose
    public String INR;
    @SerializedName("JPY")
    @Expose
    public String JPY;
    @SerializedName("KRW")
    @Expose
    public String KRW;
    @SerializedName("MXN")
    @Expose
    public String MXN;
    @SerializedName("MYR")
    @Expose
    public String MYR;
    @SerializedName("NOK")
    @Expose
    public String NOK;
    @SerializedName("NZD")
    @Expose
    public String NZD;
    @SerializedName("PHP")
    @Expose
    public String PHP;
    @SerializedName("PLN")
    @Expose
    public String PLN;
    @SerializedName("RON")
    @Expose
    public String RON;
    @SerializedName("RUB")
    @Expose
    public String RUB;
    @SerializedName("SEK")
    @Expose
    public String SEK;
    @SerializedName("SGD")
    @Expose
    public String SGD;
    @SerializedName("THB")
    @Expose
    public String THB;
    @SerializedName("TRY")
    @Expose
    public String TRY;
    @SerializedName("USD")
    @Expose
    public String USD;
    @SerializedName("ZAR")
    @Expose
    public String ZAR;
    @SerializedName("EUR")
    @Expose
    public String EUR;

    /**
    *
    * @return
    * The AUD
    */
    public String getAUD() {
    return AUD;
    }

    /**
    *
    * @param AUD
    * The AUD
    */
    public void setAUD(String AUD) {
    this.AUD = AUD;
    }

    /**
    *
    * @return
    * The BGN
    */
    public String getBGN() {
    return BGN;
    }

    /**
    *
    * @param BGN
    * The BGN
    */
    public void setBGN(String BGN) {
    this.BGN = BGN;
    }

    /**
    *
    * @return
    * The BRL
    */
    public String getBRL() {
    return BRL;
    }

    /**
    *
    * @param BRL
    * The BRL
    */
    public void setBRL(String BRL) {
    this.BRL = BRL;
    }

    /**
    *
    * @return
    * The CAD
    */
    public String getCAD() {
    return CAD;
    }

    /**
    *
    * @param CAD
    * The CAD
    */
    public void setCAD(String CAD) {
    this.CAD = CAD;
    }

    /**
    *
    * @return
    * The CHF
    */
    public String getCHF() {
    return CHF;
    }

    /**
    *
    * @param CHF
    * The CHF
    */
    public void setCHF(String CHF) {
    this.CHF = CHF;
    }

    /**
    *
    * @return
    * The CNY
    */
    public String getCNY() {
    return CNY;
    }

    /**
    *
    * @param CNY
    * The CNY
    */
    public void setCNY(String CNY) {
    this.CNY = CNY;
    }

    /**
    *
    * @return
    * The CZK
    */
    public String getCZK() {
    return CZK;
    }

    /**
    *
    * @param CZK
    * The CZK
    */
    public void setCZK(String CZK) {
    this.CZK = CZK;
    }

    /**
    *
    * @return
    * The DKK
    */
    public String getDKK() {
    return DKK;
    }

    /**
    *
    * @param DKK
    * The DKK
    */
    public void setDKK(String DKK) {
    this.DKK = DKK;
    }

    /**
    *
    * @return
    * The HKD
    */
    public String getHKD() {
    return HKD;
    }

    /**
    *
    * @param HKD
    * The HKD
    */
    public void setHKD(String HKD) {
    this.HKD = HKD;
    }

    /**
    *
    * @return
    * The HRK
    */
    public String getHRK() {
    return HRK;
    }

    /**
    *
    * @param HRK
    * The HRK
    */
    public void setHRK(String HRK) {
    this.HRK = HRK;
    }

    /**
    *
    * @return
    * The HUF
    */
    public String getHUF() {
    return HUF;
    }

    /**
    *
    * @param HUF
    * The HUF
    */
    public void setHUF(String HUF) {
    this.HUF = HUF;
    }

    /**
    *
    * @return
    * The IDR
    */
    public int getIDR() {
    return IDR;
    }

    /**
    *
    * @param IDR
    * The IDR
    */
    public void setIDR(int IDR) {
    this.IDR = IDR;
    }

    /**
    *
    * @return
    * The ILS
    */
    public String getILS() {
    return ILS;
    }

    /**
    *
    * @param ILS
    * The ILS
    */
    public void setILS(String ILS) {
    this.ILS = ILS;
    }

    /**
    *
    * @return
    * The INR
    */
    public String getINR() {
    return INR;
    }

    /**
    *
    * @param INR
    * The INR
    */
    public void setINR(String INR) {
    this.INR = INR;
    }

    /**
    *
    * @return
    * The JPY
    */
    public String getJPY() {
    return JPY;
    }

    /**
    *
    * @param JPY
    * The JPY
    */
    public void setJPY(String JPY) {
    this.JPY = JPY;
    }

    /**
    *
    * @return
    * The KRW
    */
    public String getKRW() {
    return KRW;
    }

    /**
    *
    * @param KRW
    * The KRW
    */
    public void setKRW(String KRW) {
    this.KRW = KRW;
    }

    /**
    *
    * @return
    * The MXN
    */
    public String getMXN() {
    return MXN;
    }

    /**
    *
    * @param MXN
    * The MXN
    */
    public void setMXN(String MXN) {
    this.MXN = MXN;
    }

    /**
    *
    * @return
    * The MYR
    */
    public String getMYR() {
    return MYR;
    }

    /**
    *
    * @param MYR
    * The MYR
    */
    public void setMYR(String MYR) {
    this.MYR = MYR;
    }

    /**
    *
    * @return
    * The NOK
    */
    public String getNOK() {
    return NOK;
    }

    /**
    *
    * @param NOK
    * The NOK
    */
    public void setNOK(String NOK) {
    this.NOK = NOK;
    }

    /**
    *
    * @return
    * The NZD
    */
    public String getNZD() {
    return NZD;
    }

    /**
    *
    * @param NZD
    * The NZD
    */
    public void setNZD(String NZD) {
    this.NZD = NZD;
    }

    /**
    *
    * @return
    * The PHP
    */
    public String getPHP() {
    return PHP;
    }

    /**
    *
    * @param PHP
    * The PHP
    */
    public void setPHP(String PHP) {
    this.PHP = PHP;
    }

    /**
    *
    * @return
    * The PLN
    */
    public String getPLN() {
    return PLN;
    }

    /**
    *
    * @param PLN
    * The PLN
    */
    public void setPLN(String PLN) {
    this.PLN = PLN;
    }

    /**
    *
    * @return
    * The RON
    */
    public String getRON() {
    return RON;
    }

    /**
    *
    * @param RON
    * The RON
    */
    public void setRON(String RON) {
    this.RON = RON;
    }

    /**
    *
    * @return
    * The RUB
    */
    public String getRUB() {
    return RUB;
    }

    /**
    *
    * @param RUB
    * The RUB
    */
    public void setRUB(String RUB) {
    this.RUB = RUB;
    }

    /**
    *
    * @return
    * The SEK
    */
    public String getSEK() {
    return SEK;
    }

    /**
    *
    * @param SEK
    * The SEK
    */
    public void setSEK(String SEK) {
    this.SEK = SEK;
    }

    /**
    *
    * @return
    * The SGD
    */
    public String getSGD() {
    return SGD;
    }

    /**
    *
    * @param SGD
    * The SGD
    */
    public void setSGD(String SGD) {
    this.SGD = SGD;
    }

    /**
    *
    * @return
    * The THB
    */
    public String getTHB() {
    return THB;
    }

    /**
    *
    * @param THB
    * The THB
    */
    public void setTHB(String THB) {
    this.THB = THB;
    }

    /**
    *
    * @return
    * The TRY
    */
    public String getTRY() {
    return TRY;
    }

    /**
    *
    * @param TRY
    * The TRY
    */
    public void setTRY(String TRY) {
    this.TRY = TRY;
    }

    /**
    *
    * @return
    * The USD
    */
    public String getUSD() {
    return USD;
    }

    /**
    *
    * @param USD
    * The USD
    */
    public void setUSD(String USD) {
    this.USD = USD;
    }

    /**
    *
    * @return
    * The ZAR
    */
    public String getZAR() {
    return ZAR;
    }

    /**
    *
    * @param ZAR
    * The ZAR
    */
    public void setZAR(String ZAR) {
    this.ZAR = ZAR;
    }

    /**
    *
    * @return
    * The EUR
    */
    public String getEUR() {
    return EUR;
    }

    /**
    *
    * @param EUR
    * The EUR
    */
    public void setEUR(String EUR) {
    this.EUR = EUR;
    }

    }
}