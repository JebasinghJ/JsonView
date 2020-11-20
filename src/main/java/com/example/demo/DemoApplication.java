package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		Staff staff = createStaff();

		try {


			// manager
			String managerView = mapper.writerWithView(CompanyViews.Manager.class).writeValueAsString(staff);

			System.out.format("Manager views\n%s\n", managerView);

			// hr
			String hrView = mapper.writerWithView(CompanyViews.HR.class).writeValueAsString(staff);

			System.out.format("HR views\n%s\n", hrView);

		} catch (IOException e) {
			e.printStackTrace();
		}

		SpringApplication.run(DemoApplication.class, args);
	}

	private static Staff createStaff() {

		Staff staff = new Staff();

		staff.setName("mkyong");
		staff.setAge(38);
		staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
		Map<String, BigDecimal> salary = new HashMap() {{
			put("2010", new BigDecimal(10000));
			put("2012", new BigDecimal(12000));
			put("2018", new BigDecimal(14000));
		}};
		staff.setSalary(salary);
		staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

		return staff;

	}

}
