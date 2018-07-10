package com.privalia.binlookup.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.privalia.binlookup.model.BinInfo;
import com.privalia.binlookup.repo.BinInfoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Test class for repository database operations. Due to H2 in-memory database restriction,
 * we cannot persist data and pass ID to another method with a field: the field will be cleared(!);
 * and, {@link TestEntityManager} has limited function, so we have to test in one method.
 * 
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
    
    /** 
     * Entity manager for Test purpose to insert in in-memory db(H2)
     */
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private BinInfoRepository repository;
    
    private Date now;
    
    
    public BinInfoRepositoryTests() {
        now = new Date();
    }
    @Test
    public void whenInsert_thenSaveBinInfo() throws ParseException {
        // given
        BinInfo binInfo = new BinInfo();
        binInfo.setBin(bin_num_val);
        binInfo.setJson_full(json_full_val);
        binInfo.setCreateAt(now);
        log.info("BinInfo details: " + binInfo.toString());
        
        // when
        // persist, flush to ensure, and retrieve with ID. Used to ensure the entity is persisted. 
        BinInfo inserted = (BinInfo)entityManager.persistFlushFind(binInfo);
        
        // then
        /* the entity is inserted and assigned ID */
        Long id = inserted.getId();
        assertThat(id).isNotEqualTo(null);
        assertThat(entityManager.find(BinInfo.class, id)).isNotEqualTo(null);
        
        /* the inserted has the same data as the obj */
        
        /* when inserted to H2 db and retrieved, the Date will be converted to java.sql.Timestamp. So "isEqualToComparingFieldByFieldRecursively" will fail. */
        //assertThat(inserted).isEqualToComparingFieldByFieldRecursively(binInfo);
        assertThat(inserted.getBin()).isEqualTo(bin_num_val);
        assertThat(inserted.getJson_full()).isEqualTo(json_full_val);
        assertThat(inserted.getCreateAt()).isEqualToIgnoringMillis(now);
    }
    
    @Test
    public void whenInsert_thenFindByBIN() throws ParseException {
        // given
        BinInfo binInfo = new BinInfo();
        binInfo.setBin(bin_num_val);
        binInfo.setJson_full(json_full_val);
        binInfo.setCreateAt(now);
        log.info("BinInfo details: " + binInfo.toString());
        
        // when
        // persist, flush to ensure, and retrieve with ID. Used to ensure the entity is persisted. 
        BinInfo inserted = repository.saveAndFlush(binInfo);
        // then
        
        /* when inserted to H2 db and retrieved, the Date will be converted to java.sql.Timestamp. So "isEqualToComparingFieldByFieldRecursively" will fail. */
        //assertThat(inserted).isEqualToComparingFieldByFieldRecursively(binInfo);
        log.info("Begin repository operations");
        BinInfo found = repository.findByBin(bin_num_val);
        log.info("Repo found: " + found.toString());
        assertThat(found).isNotEqualTo(null);
        assertThat(found.getBin()).isEqualTo(bin_num_val);
        assertThat(found.getJson_full()).isEqualTo(json_full_val);
        assertThat(found.getCreateAt()).isEqualToIgnoringMillis(now);
        
        List<BinInfo> founds = repository.findAllByBin(bin_num_val);
        assertThat(founds).isNotEqualTo(null);
        assertThat(founds.size()).isEqualTo(1);
        assertThat(founds.get(0).getBin()).isEqualTo(bin_num_val);
        assertThat(founds.get(0).getJson_full()).isEqualTo(json_full_val);
        assertThat(founds.get(0).getCreateAt()).isEqualToIgnoringMillis(now);
        
    }
    
}
