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

    public StockBatchItem(int batchId, String label) {
        this.batchId = batchId;
        this.label = label;
    }

    public int getBatchId() {
        return batchId;
    }

    @Override
    public String toString() {
        return label; // this will be shown in the ComboBox
    }
}
