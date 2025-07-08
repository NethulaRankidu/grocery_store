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
import net.sf.jasperreports.engine.util.JRLoader;

public class InvoicePrinter {
    public static void generateInvoice(int billId) {
        try {
            InputStream reportStream = InvoicePrinter.class.getResourceAsStream("/reports/BillingReport.jasper");

            if (reportStream == null) {
                throw new FileNotFoundException("Report file not found in resources: /reports/BillingReport.jasper");
            }

            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);

            // 2. Set up parameters
            HashMap<String, Object> params = new HashMap<>();
            params.put("BILL_ID", billId);

            // 3. Get connection from your existing connection manager
            Connection conn = ConnectionManager.getConnection();

            // 4. Fill report
            JasperPrint filledReport = JasperFillManager.fillReport(report, params, conn);

            // 5. Show or Export
            JasperViewer.viewReport(filledReport, true); // Show preview

            // OR export to PDF
            // JasperExportManager.exportReportToPdfFile(filledReport, "invoice_" + billId + ".pdf");

            System.out.println("Invoice for Bill ID " + billId + " generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

