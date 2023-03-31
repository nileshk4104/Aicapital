package com.tech.aicapital.datalist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IfscResponse {
    @SerializedName("BRANCH")
    @Expose
    private String bRANCH;
    @SerializedName("CENTRE")
    @Expose
    private String cENTRE;
    @SerializedName("NEFT")
    @Expose
    private boolean nEFT;
    @SerializedName("RTGS")
    @Expose
    private boolean rTGS;
    @SerializedName("MICR")
    @Expose
    private String mICR;
    @SerializedName("CITY")
    @Expose
    private String cITY;
    @SerializedName("ADDRESS")
    @Expose
    private String aDDRESS;
    @SerializedName("DISTRICT")
    @Expose
    private String dISTRICT;
    @SerializedName("CONTACT")
    @Expose
    private String cONTACT;
    @SerializedName("IMPS")
    @Expose
    private boolean iMPS;
    @SerializedName("STATE")
    @Expose
    private String sTATE;
    @SerializedName("BANK")
    @Expose
    private String bANK;
    @SerializedName("BANKCODE")
    @Expose
    private String bANKCODE;
    @SerializedName("IFSC")
    @Expose
    private String iFSC;

    public String getBRANCH() {
        return bRANCH;
    }

    public void setBRANCH(String bRANCH) {
        this.bRANCH = bRANCH;
    }

    public String getCENTRE() {
        return cENTRE;
    }

    public void setCENTRE(String cENTRE) {
        this.cENTRE = cENTRE;
    }

    public boolean isNEFT() {
        return nEFT;
    }

    public void setNEFT(boolean nEFT) {
        this.nEFT = nEFT;
    }

    public boolean isRTGS() {
        return rTGS;
    }

    public void setRTGS(boolean rTGS) {
        this.rTGS = rTGS;
    }

    public String getMICR() {
        return mICR;
    }

    public void setMICR(String mICR) {
        this.mICR = mICR;
    }

    public String getCITY() {
        return cITY;
    }

    public void setCITY(String cITY) {
        this.cITY = cITY;
    }

    public String getADDRESS() {
        return aDDRESS;
    }

    public void setADDRESS(String aDDRESS) {
        this.aDDRESS = aDDRESS;
    }

    public String getDISTRICT() {
        return dISTRICT;
    }

    public void setDISTRICT(String dISTRICT) {
        this.dISTRICT = dISTRICT;
    }

    public String getCONTACT() {
        return cONTACT;
    }

    public void setCONTACT(String cONTACT) {
        this.cONTACT = cONTACT;
    }

    public boolean isIMPS() {
        return iMPS;
    }

    public void setIMPS(boolean iMPS) {
        this.iMPS = iMPS;
    }

    public String getSTATE() {
        return sTATE;
    }

    public void setSTATE(String sTATE) {
        this.sTATE = sTATE;
    }

    public String getBANK() {
        return bANK;
    }

    public void setBANK(String bANK) {
        this.bANK = bANK;
    }

    public String getBANKCODE() {
        return bANKCODE;
    }

    public void setBANKCODE(String bANKCODE) {
        this.bANKCODE = bANKCODE;
    }

    public String getIFSC() {
        return iFSC;
    }

    public void setIFSC(String iFSC) {
        this.iFSC = iFSC;
    }
}
