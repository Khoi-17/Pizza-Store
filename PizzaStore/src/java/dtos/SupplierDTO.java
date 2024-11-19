/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */

public class SupplierDTO {
    private Long supplierId;
    private String companyName;
    private String address;
    private String phone;

    public SupplierDTO() {
    }

    public SupplierDTO(Long supplierId, String companyName, String address, String phone) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
