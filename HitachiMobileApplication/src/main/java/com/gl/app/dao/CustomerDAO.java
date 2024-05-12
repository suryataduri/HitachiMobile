package com.gl.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;

public interface CustomerDAO {
	  public List<SIMDetails> fetchSIMDetails(Long customerId) throws SQLException, ClassNotFoundException;
	  public String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException, SQLException, ClassNotFoundException;
	  public List<Customer> getAllCustomers() throws CustomerTableEmptyException, SQLException, ClassNotFoundException;
}
