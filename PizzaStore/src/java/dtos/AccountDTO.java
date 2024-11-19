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

public class AccountDTO {
    private String accountId;
    private String username;
    private String password;
    private String fullName;
    private int type;   //1: staff - allowed to perform all actions
                        //2: user - allowed to view/create/update the profile, order, and view their orders history
     private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AccountDTO() {
    }

    public AccountDTO(String accountId, String username, String password, String fullName, int type) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
        
    }

    public AccountDTO(String accountId, String username, String password, String fullName, int type, Long customerId) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    

   
    
    
}
