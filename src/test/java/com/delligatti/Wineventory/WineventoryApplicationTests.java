package com.delligatti.Wineventory;

import com.delligatti.Wineventory.entities.Wine;
import com.delligatti.Wineventory.services.UserRepository;
import com.delligatti.Wineventory.services.WineRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WineventoryApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	UserRepository users;

	@Autowired
	WineRepository wines;

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.param("username", "Michael")
						.param("password", "Nitro")
		);
		Assert.assertTrue(users.count() == 1 && users.findOne(1).getUsername().equals("Michael"));
	}

	@Test
	public void testAddWine() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/add-wine")
				.param("wineName", "annabella")
				.param("cost", "10")
				.param("minimum", "6")
				.param("stock", "5")
		);
		Assert.assertTrue(wines.count() == 1 && wines.findOne(1).getWineName().equals("annabella"));
		Wine wine = wines.findOne(1);
		Assert.assertTrue(wine.getCost() == 10 && wine.getMinimum() == 6 && wine.getStock() ==5);
	}

	@Test
	public void testUpdateWine() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/update-wine")
				.param("id", "1")
				.param("stock", "10")
		);
		Wine wine = wines.findOne(1);
		Assert.assertTrue(wine.getWineName().equals("annabella") && wine.getStock() == 10);
	}

	@Test
	public void testDeleteWine() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/delete-wine")
				.param("id", "1")
		);
		Wine wine = wines.findOne(1);
		Assert.assertTrue(wine == null);
	}
}
