package com.gl.app.test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gl.app.dao.impl.SIMDetailsDAOImpl;
import com.gl.app.entity.SIMDetails;



import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.service.impl.SIMDetailsServiceImpl;
import org.junit.jupiter.api.Test;

import com.gl.app.service.SIMDetailsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class SIMDetailsServiceTest {
	@Mock
	SIMDetailsDAOImpl simDetailsDAO = mock(SIMDetailsDAOImpl.class);
	@InjectMocks
	SIMDetailsServiceImpl simDetailsService = new SIMDetailsServiceImpl(simDetailsDAO);

	 @Test
	    public void testFetchSIMDetailsWithActiveStatus() throws SQLException, ClassNotFoundException {
		 List<SIMDetails> list = new ArrayList<>();
		 list.add(new SIMDetails(3L,987653210L,1234567890L,"Active",2L));
		 list.add(new SIMDetails(102L,234543L,543432L,"InActive",4L));
	      //write your code here
		 when(simDetailsDAO.fetchSIMDetailsWithActiveStatus()).thenReturn(list);
		 List<SIMDetails> list1 = simDetailsDAO.fetchSIMDetailsWithActiveStatus();
		 assertEquals(list,list1);

		 
	    }
}
