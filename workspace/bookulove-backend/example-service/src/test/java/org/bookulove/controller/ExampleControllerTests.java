package org.bookulove.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest
public class ExampleControllerTests {

    private MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc(final WebApplicationContext webApplicationContext,
                            final RestDocumentationContextProvider restDocumentationContextProvider) {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    @Test
    @DisplayName("모든 요청 인자가 정상적으로 주어지면, 환영 메시지를 반환한다.")
    public void testGreet_0() throws Exception {
        final var CONTENT = "{ \"myName\": \"Mr. java\" }";

        final var mockRequest = post("/api/v1/example/greet")
                .contentType("application/json; charset=utf-8")
                .content(CONTENT);
        mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andDo(document("greet", resource(ResourceSnippetParameters.builder()
                        .description("모두에게 따스한 환영의 말을 건네 주세요!")
                        .requestSchema(Schema.schema("ExampleController.Greet.Request"))
                        .requestFields(fieldWithPath("myName").description("당신의 이름이에요."))
                        .responseSchema(Schema.schema("ExampleController.Greet.Response"))
                        .responseFields(fieldWithPath("message").description("따스한 환영 메시지에요. 마음에 드셨나요?"))
                        .build())));
    }
}
