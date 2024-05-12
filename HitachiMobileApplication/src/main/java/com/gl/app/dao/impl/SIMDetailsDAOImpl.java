package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gl.app.dao.SIMDetailsDAO;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.util.HitachiUtil;

public class SIMDetailsDAOImpl implements SIMDetailsDAO{
HitachiUtil hitachiUtil = new HitachiUtil();
	
	
	@Override
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() throws SQLException, ClassNotFoundException {
		
		// Write code to fetch SIM details with active status
		Connection con = HitachiUtil.getConnection();
		List<SIMDetails> simDetails = new ArrayList<>();
//		String query = "select * from sim_details where status='Active'";
		String query = "select * from sim_details";
		PreparedStatement statement = con.prepareStatement(query);
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
				simDetails.add(sim);
			}
		}

	        return simDetails;
	    }


	@Override
	 public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException, SQLException, ClassNotFoundException {
           // Write code to get SIM status
		Connection con = HitachiUtil.getConnection();
		String query = "select status from sim_details where sim_number=? and service_number=?";
		PreparedStatement statement = con.prepareStatement(query);
		statement.setLong(1,simNumber);
		statement.setLong(2,serviceNumber);
		boolean result = statement.execute();
		String str = null;
		if(result){
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				str = rs.getString("status");
			}
		}
		return str;
    }
	

	
}
