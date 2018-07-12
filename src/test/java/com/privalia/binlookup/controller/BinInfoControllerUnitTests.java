package com.privalia.binlookup.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.privalia.binlookup.model.BinInfo;
import com.privalia.binlookup.repo.BinInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, properties="classpath:test.properties")
@AutoConfigureMockMvc(secure=false)
public class BinInfoControllerUnitTests {
    @InjectMocks
    private BinInfoController controller;
    
    private MockMvc mockMvc;
    
    @MockBean
    private BinInfoRepository repository;
    
    private BinInfo mockBinInfo;
    
    @Before
    public void init() throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.mockBinInfo = new BinInfo();
        this.mockBinInfo.setId(new Long(42));
        this.mockBinInfo.setBin("touhou");
        this.mockBinInfo.setJson_full("{is_json:true}");
        this.mockBinInfo.setCreateAt(fmt.parse("18/08/2018"));
    }
    
    @Test
    public void testBinInfoControllerInsertBIN() throws Exception {
        when(this.repository.save(mockBinInfo)).thenReturn(mockBinInfo);
        
        this.mockMvc.perform(post("/insert")
                .content("{\"bin\":\"touhou\", \"json_full\":\"{is_json:true}\", \"createAt\":\"18/08/2018\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isCreated());
    }

    @Test
    public void testBinInfoControllerSearchBIN() throws Exception {
        when(repository.findByBin("touhou")).thenReturn(mockBinInfo);
        
        this.mockMvc.perform(get("/search/touhou")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
