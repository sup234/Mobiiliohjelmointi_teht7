package com.example.teht7_1;

public class RowItem {

    private String ownertext;
    private String licensetext;
    private int pic_id;

    public RowItem(String ownertext, String licensetext, int pic_id) {
        this.ownertext = ownertext;
        this.licensetext = licensetext;
        this.pic_id = pic_id;
    }

    public String getOwnertext() { return ownertext; }
    public void setOwnertext(String ownertext) { this.ownertext = ownertext;}
    public String getLicensetexttext() { return licensetext; }
    public void setLicensetext(String licensetext) { this.licensetext = licensetext;}
    public int getPic_id() { return pic_id; }
    public void setPic_id(int pic_id) { this.pic_id = pic_id;}
}
