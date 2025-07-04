/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author Nethula
 */
public class StockBatchItem {
    private int batchId;
    private String label;
    private int remainingItems;

    public StockBatchItem(int batchId, String label, int remainingItems) {
        this.batchId = batchId;
        this.label = label;
        this.remainingItems = remainingItems;
    }

    public int getBatchId() {
        return batchId;
    }
    
    public int getRemainingItems() {
        return remainingItems;
    }

    @Override
    public String toString() {
        return label; // this will be shown in the ComboBox
    }
}
