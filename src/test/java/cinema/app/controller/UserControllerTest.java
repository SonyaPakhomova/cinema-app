package cinema.app.controller;

import cinema.app.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
@SpringBootTest
class UserControllerTest {
    @Autowired
    private WebApplicationContext wsc;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wsc).build();
    }

    @Test
    public void userGetByEmail() throws Exception {
        mockMvc.perform(get("/users/by-email").contentType(MediaType.APPLICATION_JSON)
                        .content("{'email': 'bob@gmail.com'}")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void userGetByBadEmail() throws Exception {
        mockMvc.perform(get("/users/by-email").contentType(MediaType.APPLICATION_JSON)
                        .content("{'email': 'bobgmail.com'}")
                )
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    public void userGetByEmailWithoutEnd() throws Exception {
        mockMvc.perform(get("/users/by-email").contentType(MediaType.APPLICATION_JSON)
                        .content("{'email': 'bob@gmail'}")
                )
                .andDo(print())
                .andExpect(status().is(400));
    }
}
