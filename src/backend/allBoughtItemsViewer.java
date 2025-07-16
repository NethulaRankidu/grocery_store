/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author Nethula
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.util.JRLoader;

public class allBoughtItemsViewer {
    public static void generateBoughtItems(int billId) {
        try {
            InputStream reportStream = allBoughtItemsViewer.class.getResourceAsStream("/reports/CustomerBoughtItems.jasper");

            if (reportStream == null) {
                throw new FileNotFoundException("Report file not found in resources: /reports/CustomerBoughtItems.jasper");
            }

            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);

            // Set up parameters
            HashMap<String, Object> params = new HashMap<>();
            params.put("CUS_ID", billId);

            // Get connection from your existing connection manager
            Connection conn = ConnectionManager.getConnection();

            // Fill report
            JasperPrint filledReport = JasperFillManager.fillReport(report, params, conn);

            // Show or Export
            JasperViewer viewer = new JasperViewer(filledReport, false); // false prevents default exit
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // <== THIS LINE
            viewer.setVisible(true);

            // export to PDF
            // JasperExportManager.exportReportToPdfFile(filledReport, "invoice_" + billId + ".pdf");

            System.out.println("Invoice for Bill ID " + billId + " generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

