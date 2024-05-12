package com.gl.app.service.impl;
import com.gl.app.dao.*;
import com.gl.app.entity.*;
import com.gl.app.dao.impl.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import com.gl.app.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public List<SIMDetails> fetchCustomerList(Long customerId) throws CustomerDoesNotExistException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
        List<SIMDetails> customerList = new ArrayList<>(customerDAO.fetchSIMDetails(customerId));
		if(customerList.isEmpty()){
			throw new CustomerDoesNotExistException("Unable to fetch sim details");
		}
		return customerList;
	}

	@Override
	 public String updateCustomerAddress(Long customerId, String city) throws CustomerTableEmptyException, SQLException, ClassNotFoundException {
		String str = customerDAO.updateCustomerAddress(customerId,city);
		if(str==null){
			throw new CustomerTableEmptyException("Unable to update");
		}
        return str;
    }

	@Override
	public List<Customer> getAllCustomers() throws CustomerDoesNotExistException, SQLException, ClassNotFoundException {
        List<Customer> customerList1 = new ArrayList<>(customerDAO.getAllCustomers());
		if(customerList1.isEmpty()){
			throw new CustomerDoesNotExistException("Unable to fetch customer from table");
		}
		return customerList1;
	}
	
}
