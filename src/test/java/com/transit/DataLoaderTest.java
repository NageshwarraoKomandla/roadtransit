package com.transit;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.transit.service.DataLoader;

	public class DataLoaderTest extends RoadtransitApplicationTests {

		@Autowired
		private WebApplicationContext webApplicationContext;

		private MockMvc mockMvc;

		@Autowired
		private  DataLoader dl ;
		
		@Before
		public void setup() {
			mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}
/*
		@Test
		public void testConnected() throws Exception {
			mockMvc.perform(get("/connected"))
			       .andExpect(status()
			       .isOk());

		}

*/
		
		
		@Test
		public void testConnected() throws Exception {
			  Assertions.assertEquals(null, 	dl.getCity("Chicago"));
		}
		
		
}