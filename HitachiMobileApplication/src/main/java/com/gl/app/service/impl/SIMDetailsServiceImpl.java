package com.gl.app.service.impl;
import java.util.List;
import com.gl.app.dao.*;
import com.gl.app.dao.impl.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.service.SIMDetailsService;
import com.gl.app.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class SIMDetailsServiceImpl implements SIMDetailsService{
	SIMDetailsDAO simDetailsDAO = new SIMDetailsDAOImpl();
	public SIMDetailsServiceImpl() {

	}
	public SIMDetailsServiceImpl(SIMDetailsDAOImpl simDetailsDAO) {
		this.simDetailsDAO = simDetailsDAO;
	}

	@Override
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() throws SQLException, ClassNotFoundException,CustomerTableEmptyException {
    List<SIMDetails> list = new ArrayList<>(simDetailsDAO.fetchSIMDetailsWithActiveStatus());
	list = list.stream().filter(element->element.getStatus().equals("Active")).collect(Collectors.toList());
		if(list.isEmpty()){
			throw new CustomerTableEmptyException("Unable to fetch details with active status");
		}
		return list;
	}

	@Override
	 public String getSimStatus(long simId, long simNumber) throws SIMDoesNotExistsException, SQLException, ClassNotFoundException {
		  String str = simDetailsDAO.getSimStatus(simId,simNumber);
		  if(str==null){
			  throw new SIMDoesNotExistsException("SIM does not exist");
		  }
          return str;
    }
	
		
	}


