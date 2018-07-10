package com.privalia.binlookup.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.web.WebEndpointHttpMethod;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.privalia.binlookup.controller.BinInfoController;
import com.privalia.binlookup.model.BinInfo;
import com.privalia.binlookup.repo.BinInfoRepository;

import lombok.extern.slf4j.Slf4j;
import static org.mockito.Mockito.when;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, properties="classpath:values.properties")
@AutoConfigureMockMvc(secure=false)
public class BinInfoControllerTests {
    @MockBean
    private BinInfoController controller;
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BinInfoRepository repository;
    
    @Value("${greeting.message}")
    private String message;
    
    private BinInfo mockBinInfo;
    
    public BinInfoControllerTests() {
        this.mockBinInfo = new BinInfo();
        this.mockBinInfo.setId(new Long(42));
        this.mockBinInfo.setBin("touhou");
        this.mockBinInfo.setJson_full("{is_json:true}");
        this.mockBinInfo.setCreateAt(new Date());
    }
    
    @Test
    public void testBinInfoControllerRoot() throws Exception {
        log.info("Welcome message: " + message);
        this.mockMvc.perform(get("/").accept(MediaType.TEXT_PLAIN_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(message));
    }
    
    @Test
    public void testBinInfoControllerInsertBIN() throws Exception {
        //when(this.controller.insertBIN(mockBinInfo)).thenReturn(mockBinInfo);
        this.mockMvc.perform(post("/bin")
                .content("{\"bin\":\"333444\", \"json_full\":\"{is_json:true}\", \"createAt\":\"18/08/2018\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testBinInfoControllerSearchBIN() throws Exception {
        //when(this.controller.searchBIN("touhou")).thenReturn(mockBinInfo);
        this.mockMvc.perform(get("/bin/touhou"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        
    }
}
