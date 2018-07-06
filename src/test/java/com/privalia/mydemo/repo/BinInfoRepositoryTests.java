package com.privalia.mydemo.repo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.privalia.mydemo.controller.MyDemoController;
import com.privalia.mydemo.model.BinInfo;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.*;

/**
 * Test class for repository database operations.
 * <br /><br />
 * CAUTION:
 * <ul>
 * <li> the properties file must be annotated with <code>locations</code> to be read; </li>
 * <li> must be <code>.properties</code> or <code>.xml</code> files. Cannot be <code>.yml</code> file. </li>
 * <li> Remember to add H2 dependency and set properties (See {@link src/test/resources} for details) </li>
 * </ul>
 * <br />
 * @author yangliang.ding
 */
@RunWith(SpringRunner.class)
@Slf4j
@TestPropertySource(locations="classpath:values.properties")
@DataJpaTest
public class BinInfoRepositoryTests {
    
    @Value("${test.repository.bean.bin}")
    private String bin_num_val;
    
    @Value("${test.repository.bean.json_full}")
    private String json_full_val;
    
    @Value("${test.repository.bean.create_at}")
    private String create_at_val;
    
    /** 
     * Entity manager for Test purpose to insert in in-memory db(H2)
     */
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void whenInsert_thenSaveBinInfo() throws ParseException {
        // given
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        BinInfo binInfo = new BinInfo();
        binInfo.setBin(bin_num_val);
        binInfo.setJson_full(json_full_val);
        binInfo.setCreateAt(fmt.parse(create_at_val));
        log.info("BinInfo details: " + binInfo.toString());
        
        // when
        // persist, flush to ensure, and retrieve with ID. Used to ensure the entity is persisted. 
        BinInfo inserted = (BinInfo)entityManager.persistFlushFind(binInfo);
        
        // then
        /* when inserted to H2 db and retrieved, the Date will be converted to java.sql.Timestamp. So "isEqualToComparingFieldByFieldRecursively" will fail. */
        //assertThat(inserted).isEqualToComparingFieldByFieldRecursively(binInfo);
        
        assertThat(inserted.getBin()).isEqualTo(binInfo.getBin());
        assertThat(inserted.getJson_full()).isEqualTo(binInfo.getJson_full());
        assertThat(inserted.getCreateAt()).isEqualToIgnoringMillis(binInfo.getCreateAt());
    }
    
}
