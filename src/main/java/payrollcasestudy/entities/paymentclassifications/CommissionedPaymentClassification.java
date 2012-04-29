package payrollcasestudy.entities.paymentclassifications;

import payrollcasestudy.entities.SalesReceipt;

import java.util.HashMap;
import java.util.Map;

public class CommissionedPaymentClassification implements PaymentClassification {
    private Map<Integer, SalesReceipt> salesReceiptMap = new HashMap<Integer, SalesReceipt>();
    private double commissionRate;
    private double monthlySalary;

    public CommissionedPaymentClassification(double monthlySalary, double commissionRate) {
        this.commissionRate = commissionRate;
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public SalesReceipt getSalesReceipt(int date) {
        return salesReceiptMap.get(date);
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceiptMap.put(salesReceipt.getDate(), salesReceipt);
    }
}
