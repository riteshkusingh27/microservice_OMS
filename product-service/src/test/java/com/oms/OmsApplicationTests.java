package com.oms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
class OmsApplicationTests {

//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//
//	@Test
//	void shouldCreateProduct() throws Exception {
//		ProductRequest pr = getProductRequest();
//		String request = objectMapper.writeValueAsString(pr);
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(request))
//				.andExpect(status().isCreated());
//	}
//
//	private ProductRequest getProductRequest() {
//		return ProductRequest.builder()
//				.name("iphone 13")
//				.description("fkajdf")
//				.price(new BigDecimal("13.99"))
//				.build();
//	}
@Test
void contextLoads() {
}

}
