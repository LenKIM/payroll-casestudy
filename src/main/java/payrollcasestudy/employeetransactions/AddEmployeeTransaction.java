package payrollcasestudy.employeetransactions;

import payrollcasestudy.*;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentmethods.HoldMethod;
import payrollcasestudy.paymentmethods.PaymentMethod;
import payrollcasestudy.paymentschedule.PaymentSchedule;

/**
 * Listing 19-5
 * Listing 19-6
 */
public abstract class AddEmployeeTransaction implements Transaction {
    private int employeeId;
    private String employeeName;
    private String employeeAddress;

    public AddEmployeeTransaction(int employeeId, String employeeName, String employeeAddress){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }

    @Override
    public void execute() {
        PaymentClassification paymentClassification = getPaymentClassification();
        PaymentSchedule paymentSchedule = getPaymentSchedule();
        PaymentMethod paymentMethod = new HoldMethod();
        Employee employee = new Employee(employeeId, employeeName, employeeAddress);
        employee.setPaymentClassification(paymentClassification);
        employee.setPaymentSchedule(paymentSchedule);
        employee.setPaymentMethod(paymentMethod);
        PayrollDatabase.globalPayrollDatabase.addEmployee(employeeId, employee);
    }

    protected abstract PaymentSchedule getPaymentSchedule();

    protected abstract PaymentClassification getPaymentClassification();
}
