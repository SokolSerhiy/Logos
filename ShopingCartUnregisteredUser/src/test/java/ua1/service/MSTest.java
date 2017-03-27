package ua1.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/META-INF/applicationContext.xml")
public class MSTest {

	@Autowired
	MeasuringSystemService measuringSystemService;
	
	@Test
	public void asasas(){
		List<MeasuringSystem> all = measuringSystemService.findAll();
		Assert.assertEquals(0, all.size());
	}
}
