package dtos;




public class CustomerDTO {
    private Long customerId;
    private String password;
    private String contactName;
    private String address;
    private String phone;

    public CustomerDTO() {
    }

    public CustomerDTO(Long customerId, String password, String contactName, String address, String phone) {
        this.customerId = customerId;
        this.password = password;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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
