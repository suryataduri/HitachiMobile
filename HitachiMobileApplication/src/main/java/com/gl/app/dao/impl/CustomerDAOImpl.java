package com.gl.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gl.app.dao.CustomerDAO;
import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.util.HitachiUtil;

public class CustomerDAOImpl implements CustomerDAO{

	Connection con = null;
	@Override
	 public String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException, SQLException, ClassNotFoundException {

		// Write code to update customer address
        con = HitachiUtil.getConnection();
		String query = "update customer set address = ? where customer_id = ?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1,newAddress);
		statement.setLong(2,customerId);

		int updateResult = statement.executeUpdate();
		String str=null;
		if(updateResult>0){
			str = "Updated Successfully";
		}
		statement.close();
		con.close();
		return str;
    }

	@Override
	public List<Customer> getAllCustomers() throws CustomerTableEmptyException, SQLException, ClassNotFoundException {
	   // Write code to fetch all customers
		con = HitachiUtil.getConnection();
		List<Customer> customers = new ArrayList<>();
		String query = "select * from customer order by customer_id";
		PreparedStatement statement = con.prepareStatement(query);
		boolean result = statement.execute();
		if(result){
			ResultSet rs = statement.getResultSet();
			while (rs.next()){
				long cID = rs.getLong(1);
				String dob= rs.getString(2);
				String email= rs.getString(3);
				String frst= rs.getString(4);
				String last= rs.getString(5);
				String id= rs.getString(6);
				String addr= rs.getString(7);
				String cust_state= rs.getString(8);
				Customer cust = new Customer(cID,dob,email,frst,last,id,addr,cust_state);
				customers.add(cust);
			}
		}
		return customers;
	}

	@Override

	public List<SIMDetails> fetchSIMDetails(Long customerId) throws SQLException, ClassNotFoundException {
	    con = HitachiUtil.getConnection();
		List<SIMDetails> simDetailsArray = new ArrayList<>();
		String query = "select * from sim_details where customer_id=?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setLong(1,customerId);
		boolean result = statement.execute();
		if(result){
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				SIMDetails sim = new SIMDetails();
				sim.setSimId(rs.getLong(1));
				sim.setServiceNumber(rs.getLong(2));
				sim.setSimNumber(rs.getLong(3));
				sim.setStatus(rs.getString(4));
				sim.setUniqueIdNumber(rs.getLong(5));

				simDetailsArray.add(sim);
			}
			rs.close();
		}
		statement.close();
		con.close();
	    return simDetailsArray;
	}
}
