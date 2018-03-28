package com.example.mayank.medishare;

/**
 * Created by MAYANK on 25-Feb-18.
 */

public class Blog {

    String Medicine_name1,Medicine_Quantity1,Medicine_name2,Medicine_Quantity2,Medicine_name3,Medicine_Quantity3,Medicine_name4,Medicine_Quantity4,Medicine_name5,Medicine_Quantity5;
    String description,email,password,organisation_name,phone,location;
    String image;
    public Blog()
    {

    }
    public Blog(String image,String medicine_name1, String medicine_Quantity1, String medicine_name2, String medicine_Quantity2, String medicine_name3, String medicine_Quantity3, String medicine_name4, String medicine_Quantity4, String medicine_name5, String medicine_Quantity5, String description, String email, String password, String organisation_name, String phone, String location) {
       this.image=image;
        Medicine_name1 = medicine_name1;
        Medicine_Quantity1 = medicine_Quantity1;
        Medicine_name2 = medicine_name2;
        Medicine_Quantity2 = medicine_Quantity2;
        Medicine_name3 = medicine_name3;
        Medicine_Quantity3 = medicine_Quantity3;
        Medicine_name4 = medicine_name4;
        Medicine_Quantity4 = medicine_Quantity4;
        Medicine_name5 = medicine_name5;
        Medicine_Quantity5 = medicine_Quantity5;
        this.description = description;
        this.email = email;
        this.password = password;
        this.organisation_name = organisation_name;
        this.phone = phone;
        this.location = location;

        }

        public String getImage() {return image;}

        public void setImage(String image)
        {this.image=image;}

    public String getMedicine_name1() {
        return Medicine_name1;
    }

    public void setMedicine_name1(String medicine_name1) {
        Medicine_name1 = medicine_name1;
    }

    public String getMedicine_Quantity1() {
        return Medicine_Quantity1;
    }

    public void setMedicine_Quantity1(String medicine_Quantity1) {
        Medicine_Quantity1 = medicine_Quantity1;
    }

    public String getMedicine_name2() {
        return Medicine_name2;
    }

    public void setMedicine_name2(String medicine_name2) {
        Medicine_name2 = medicine_name2;
    }

    public String getMedicine_Quantity2() {
        return Medicine_Quantity2;
    }

    public void setMedicine_Quantity2(String medicine_Quantity2) {
        Medicine_Quantity2 = medicine_Quantity2;
    }

    public String getMedicine_name3() {
        return Medicine_name3;
    }

    public void setMedicine_name3(String medicine_name3) {
        Medicine_name3 = medicine_name3;
    }

    public String getMedicine_Quantity3() {
        return Medicine_Quantity3;
    }

    public void setMedicine_Quantity3(String medicine_Quantity3) {
        Medicine_Quantity3 = medicine_Quantity3;
    }

    public String getMedicine_name4() {
        return Medicine_name4;
    }

    public void setMedicine_name4(String medicine_name4) {
        Medicine_name4 = medicine_name4;
    }

    public String getMedicine_Quantity4() {
        return Medicine_Quantity4;
    }

    public void setMedicine_Quantity4(String medicine_Quantity4) {
        Medicine_Quantity4 = medicine_Quantity4;
    }

    public String getMedicine_name5() {
        return Medicine_name5;
    }

    public void setMedicine_name5(String medicine_name5) {
        Medicine_name5 = medicine_name5;
    }

    public String getMedicine_Quantity5() {
        return Medicine_Quantity5;
    }

    public void setMedicine_Quantity5(String medicine_Quantity5) {
        Medicine_Quantity5 = medicine_Quantity5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganisation_name() {
        return organisation_name;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
