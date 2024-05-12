package com.gl.app;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gl.app.dao.impl.CustomerDAOImpl;
import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.*;
import com.gl.app.service.*;
import com.gl.app.service.impl.*;

public class HitachiMobileApplication {

	
public static void main(String[] args) throws Exception {
	CustomerService customerService = new CustomerServiceImpl();
    SIMDetailsService simDetailsService = new SIMDetailsServiceImpl();

   
	
	 Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("1. Fetch Sim Details by Customer ID");
        System.out.println("2. Update customer address");
        System.out.println("3. Get all customers");
        System.out.println("4. Fetch active SIM details");
        System.out.println("5. Get SIM status");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
           // write code to fetch sim details by customer id
                System.out.println("Enter the customer id :");
                Long customerId = scanner.nextLong();
                List<SIMDetails> simDetails = new ArrayList<>(customerService.fetchCustomerList(customerId));
                System.out.println(simDetails);
                break;
            case 2:
               //update customer address by customer id
                System.out.println("Enter the customer id :");
                Long customerId2 = scanner.nextLong();
                System.out.println("Enter new Address :");
                String newAddress = scanner.next();
                String updateResult = customerService.updateCustomerAddress(customerId2,newAddress);
                System.out.println(updateResult);
                break;
            case 3:
               //write code to fetch all customers
                List<Customer> allCustomers = new ArrayList<>(customerService.getAllCustomers());
                System.out.println(allCustomers);
                break;


            case 4:
              //Write code to fetch active sim details
                List<SIMDetails> activeUsers = new ArrayList<>(simDetailsService.fetchSIMDetailsWithActiveStatus());
                System.out.println(activeUsers);
                break;
            case 5:
               //Write code to fetch sim status
                System.out.println("Enter SIM number :");
                Long simNumber = scanner.nextLong();
                System.out.println("Enter service number :");
                Long serviceNumber = scanner.nextLong();
                String simStatus = simDetailsService.getSimStatus(simNumber,serviceNumber);
                System.out.println(simStatus);
                break;

            case 6:
                System.out.println("Exiting...");
			try {
				scanner.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }
}
}
