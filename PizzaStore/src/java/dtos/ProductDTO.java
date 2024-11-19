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
public class ProductDTO {
    private  Long productId;
    private String productName;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private int quantityPerUnit;
    private double unitPrice;
    private String productImage;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String productName, CategoryDTO category, SupplierDTO supplier, int quantityPerUnit, double unitPrice, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.supplier = supplier;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.productImage = productImage;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public int getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    
}
