/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.htn.pojo.Constants;
import com.htn.utils.Utils;
import com.htn.pojo.LendingDetail;
import com.htn.pojo.Payment;
import com.htn.services.AccountServices;
import com.htn.services.LendingDetailServices;
import com.htn.services.PaymentServices;
import com.htn.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
        
/**
 *
 * @author n
 */
public class TestPayment {
    private static Connection conn;
    private static final PaymentServices s = new PaymentServices();
    private static final LendingDetailServices lds = new LendingDetailServices();
    
    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = JdbcUtils.getConn(); 
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null)
            conn.close();
    }

    @Test
    public void testGetPaymentInfo() throws SQLException, ParseException {
        int ACCOUNT_ID_TEST = 1;
        
        List<LendingDetail> lendingDetails = lds.getLendingDetailListByAccountID(ACCOUNT_ID_TEST);
        
        double totalBookPrice = 0;
        double totalCheckout = 0;
        double fine = 0;
        
        for (LendingDetail lendingDetail : lendingDetails) {
            // Get Total bookprice
            totalBookPrice += lendingDetail.getAmount();
            // Get Total Fine
            long datediff = Utils.findDateDifference(Utils.getCurrentDate2().toString(), lendingDetail.getDueDate().toString());
            if (datediff < 0) {
                fine += -datediff * Constants.FINE_IN_1_DAY; 
            }
        }
        // Get Total Checkout
        totalCheckout = totalBookPrice + fine;
        
        Payment payment = new Payment(totalBookPrice, totalCheckout, fine);
        
        System.err.println(payment.getTotalBookPrice());
        System.err.println(payment.getFine());
        System.err.println(payment.getTotalCheckout());
        
        Assertions.assertEquals(2000, payment.getTotalCheckout());
    }
}
