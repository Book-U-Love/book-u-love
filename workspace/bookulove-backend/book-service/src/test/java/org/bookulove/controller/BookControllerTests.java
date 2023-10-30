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
public class BookControllerTests {

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
    @DisplayName("")
    public void testRegist_0() throws Exception {
        final var CONTENT = "{ \"isbn\": \"9788950992460\" }";

        final var mockRequest = post("/api/v1/books")
                .contentType("application/json; charset=utf-8")
                .content(CONTENT);

        mockMvc.perform(mockRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andDo(document("regist", resource(ResourceSnippetParameters.builder()
                        .description("도서 등록을 위해 ISBN을 검색합니다.")
                        .requestSchema(Schema.schema("BookController.Regist.Request"))
                        .requestFields(fieldWithPath("isbn").description("등록 시도한 ISBN입니다."))
                        .responseSchema(Schema.schema("BookController.Regist.Response"))
                        .responseFields(fieldWithPath("message").description("등록 결과입니다."))
                        .build())));


    }

}
