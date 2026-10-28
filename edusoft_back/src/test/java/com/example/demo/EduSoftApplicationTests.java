package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EduSoftApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void rejectsInvalidGradeWithBadRequest() throws Exception {
		mockMvc.perform(post("/apiedusoft/v1/notas")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"nota\":6}"))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("nota: La nota debe estar entre 0 y 5"));
	}

	@Test
	void returnsNotFoundForMissingUser() throws Exception {
		mockMvc.perform(get("/apiedusoft/v1/usuarios/2147483647"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("No se encontró el usuario con ID 2147483647"));
	}

	@Test
	void rejectsNonPositiveResourceId() throws Exception {
		mockMvc.perform(get("/apiedusoft/v1/usuarios/0"))
				.andExpect(status().isBadRequest());
	}
}
