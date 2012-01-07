package payrollcasestudy.transactions.add.employees;

import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.paymentclassifiactions.HourlyPaymentClassification;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentmethods.HoldMethod;
import payrollcasestudy.paymentschedule.WeeklyPaymentSchedule;
import payrollcasestudy.transactions.DatabaseAwareTest;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * No Listing
 */
public class AddHourlyEmployeeTransactionTest  extends DatabaseAwareTest {

    protected double FLOATING_POINT_ACCURACY = 0.000001;

    @Test
    public void testAddHourlyEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Steve", "Home", 20.0);
        addEmployeeTransaction.execute();
        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee.getName(), is(equalTo("Steve")));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(HourlyPaymentClassification.class)));
        HourlyPaymentClassification hourlyPaymentClassification =
                (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification.getHourlyRate(), is(closeTo(20.0, FLOATING_POINT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

}
