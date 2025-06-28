/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Nethula
 */
public class Product {
    private int productId;
    private String barcode;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal unitCost;
    private boolean isPerishable;

    public Product() { }

    public Product(int productId, String barcode, String name,
                   BigDecimal unitPrice, BigDecimal unitCost, boolean isPerishable) {
        this.productId = productId;
        this.barcode = barcode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.isPerishable = isPerishable;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public boolean isPerishable() {
        return isPerishable;
    }
    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId &&
               Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, barcode);
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + productId +
               ", barcode='" + barcode + '\'' +
               ", name='" + name + '\'' +
               ", price=" + unitPrice +
               (unitCost != null ? ", cost=" + unitCost : "") +
               ", perishable=" + isPerishable +
               '}';
    }
}
