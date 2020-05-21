package com.synectiks.student.web.rest;

import static com.synectiks.student.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.synectiks.student.StudentApp;
import com.synectiks.student.domain.AcademicHistory;
import com.synectiks.student.repository.AcademicHistoryRepository;
import com.synectiks.student.repository.search.AcademicHistorySearchRepository;
import com.synectiks.student.service.AcademicHistoryService;
import com.synectiks.student.service.dto.AcademicHistoryDTO;
import com.synectiks.student.service.mapper.AcademicHistoryMapper;
import com.synectiks.student.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link AcademicHistoryResource} REST controller.
 */
@SpringBootTest(classes = StudentApp.class)
public class AcademicHistoryResourceIT {

    private static final String DEFAULT_QUALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_YEAR_OF_PASSING = "AAAAAAAAAA";
    private static final String UPDATED_YEAR_OF_PASSING = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSITY = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSITY = "BBBBBBBBBB";

    private static final Long DEFAULT_ENROLLMENT_NO = 1L;
    private static final Long UPDATED_ENROLLMENT_NO = 2L;

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

    private static final Integer DEFAULT_PERCENTAGE = 1;
    private static final Integer UPDATED_PERCENTAGE = 2;

    @Autowired
    private AcademicHistoryRepository academicHistoryRepository;

    @Autowired
    private AcademicHistoryMapper academicHistoryMapper;

    @Autowired
    private AcademicHistoryService academicHistoryService;

    /**
     * This repository is mocked in the com.synectiks.student.repository.search test package.
     *
     * @see com.synectiks.student.repository.search.AcademicHistorySearchRepositoryMockConfiguration
     */
    @Autowired
    private AcademicHistorySearchRepository mockAcademicHistorySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAcademicHistoryMockMvc;

    private AcademicHistory academicHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AcademicHistoryResource academicHistoryResource = new AcademicHistoryResource(academicHistoryService);
        this.restAcademicHistoryMockMvc = MockMvcBuilders.standaloneSetup(academicHistoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcademicHistory createEntity(EntityManager em) {
        AcademicHistory academicHistory = new AcademicHistory()
            .qualification(DEFAULT_QUALIFICATION)
            .yearOfPassing(DEFAULT_YEAR_OF_PASSING)
            .institution(DEFAULT_INSTITUTION)
            .university(DEFAULT_UNIVERSITY)
            .enrollmentNo(DEFAULT_ENROLLMENT_NO)
            .score(DEFAULT_SCORE)
            .percentage(DEFAULT_PERCENTAGE);
        return academicHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcademicHistory createUpdatedEntity(EntityManager em) {
        AcademicHistory academicHistory = new AcademicHistory()
            .qualification(UPDATED_QUALIFICATION)
            .yearOfPassing(UPDATED_YEAR_OF_PASSING)
            .institution(UPDATED_INSTITUTION)
            .university(UPDATED_UNIVERSITY)
            .enrollmentNo(UPDATED_ENROLLMENT_NO)
            .score(UPDATED_SCORE)
            .percentage(UPDATED_PERCENTAGE);
        return academicHistory;
    }

    @BeforeEach
    public void initTest() {
        academicHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcademicHistory() throws Exception {
        int databaseSizeBeforeCreate = academicHistoryRepository.findAll().size();

        // Create the AcademicHistory
        AcademicHistoryDTO academicHistoryDTO = academicHistoryMapper.toDto(academicHistory);
        restAcademicHistoryMockMvc.perform(post("/api/academic-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the AcademicHistory in the database
        List<AcademicHistory> academicHistoryList = academicHistoryRepository.findAll();
        assertThat(academicHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        AcademicHistory testAcademicHistory = academicHistoryList.get(academicHistoryList.size() - 1);
        assertThat(testAcademicHistory.getQualification()).isEqualTo(DEFAULT_QUALIFICATION);
        assertThat(testAcademicHistory.getYearOfPassing()).isEqualTo(DEFAULT_YEAR_OF_PASSING);
        assertThat(testAcademicHistory.getInstitution()).isEqualTo(DEFAULT_INSTITUTION);
        assertThat(testAcademicHistory.getUniversity()).isEqualTo(DEFAULT_UNIVERSITY);
        assertThat(testAcademicHistory.getEnrollmentNo()).isEqualTo(DEFAULT_ENROLLMENT_NO);
        assertThat(testAcademicHistory.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testAcademicHistory.getPercentage()).isEqualTo(DEFAULT_PERCENTAGE);

        // Validate the AcademicHistory in Elasticsearch
//        verify(mockAcademicHistorySearchRepository, times(1)).save(testAcademicHistory);
    }

    @Test
    @Transactional
    public void createAcademicHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = academicHistoryRepository.findAll().size();

        // Create the AcademicHistory with an existing ID
        academicHistory.setId(1L);
        AcademicHistoryDTO academicHistoryDTO = academicHistoryMapper.toDto(academicHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcademicHistoryMockMvc.perform(post("/api/academic-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicHistory in the database
        List<AcademicHistory> academicHistoryList = academicHistoryRepository.findAll();
        assertThat(academicHistoryList).hasSize(databaseSizeBeforeCreate);

        // Validate the AcademicHistory in Elasticsearch
//        verify(mockAcademicHistorySearchRepository, times(0)).save(academicHistory);
    }


    @Test
    @Transactional
    public void getAllAcademicHistories() throws Exception {
        // Initialize the database
        academicHistoryRepository.saveAndFlush(academicHistory);

        // Get all the academicHistoryList
        restAcademicHistoryMockMvc.perform(get("/api/academic-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualification").value(hasItem(DEFAULT_QUALIFICATION.toString())))
            .andExpect(jsonPath("$.[*].yearOfPassing").value(hasItem(DEFAULT_YEAR_OF_PASSING.toString())))
            .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION.toString())))
            .andExpect(jsonPath("$.[*].university").value(hasItem(DEFAULT_UNIVERSITY.toString())))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE)));
    }
    
    @Test
    @Transactional
    public void getAcademicHistory() throws Exception {
        // Initialize the database
        academicHistoryRepository.saveAndFlush(academicHistory);

        // Get the academicHistory
        restAcademicHistoryMockMvc.perform(get("/api/academic-histories/{id}", academicHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(academicHistory.getId().intValue()))
            .andExpect(jsonPath("$.qualification").value(DEFAULT_QUALIFICATION.toString()))
            .andExpect(jsonPath("$.yearOfPassing").value(DEFAULT_YEAR_OF_PASSING.toString()))
            .andExpect(jsonPath("$.institution").value(DEFAULT_INSTITUTION.toString()))
            .andExpect(jsonPath("$.university").value(DEFAULT_UNIVERSITY.toString()))
            .andExpect(jsonPath("$.enrollmentNo").value(DEFAULT_ENROLLMENT_NO.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.percentage").value(DEFAULT_PERCENTAGE));
    }

    @Test
    @Transactional
    public void getNonExistingAcademicHistory() throws Exception {
        // Get the academicHistory
        restAcademicHistoryMockMvc.perform(get("/api/academic-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcademicHistory() throws Exception {
        // Initialize the database
        academicHistoryRepository.saveAndFlush(academicHistory);

        int databaseSizeBeforeUpdate = academicHistoryRepository.findAll().size();

        // Update the academicHistory
        AcademicHistory updatedAcademicHistory = academicHistoryRepository.findById(academicHistory.getId()).get();
        // Disconnect from session so that the updates on updatedAcademicHistory are not directly saved in db
        em.detach(updatedAcademicHistory);
        updatedAcademicHistory
            .qualification(UPDATED_QUALIFICATION)
            .yearOfPassing(UPDATED_YEAR_OF_PASSING)
            .institution(UPDATED_INSTITUTION)
            .university(UPDATED_UNIVERSITY)
            .enrollmentNo(UPDATED_ENROLLMENT_NO)
            .score(UPDATED_SCORE)
            .percentage(UPDATED_PERCENTAGE);
        AcademicHistoryDTO academicHistoryDTO = academicHistoryMapper.toDto(updatedAcademicHistory);

        restAcademicHistoryMockMvc.perform(put("/api/academic-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the AcademicHistory in the database
        List<AcademicHistory> academicHistoryList = academicHistoryRepository.findAll();
        assertThat(academicHistoryList).hasSize(databaseSizeBeforeUpdate);
        AcademicHistory testAcademicHistory = academicHistoryList.get(academicHistoryList.size() - 1);
        assertThat(testAcademicHistory.getQualification()).isEqualTo(UPDATED_QUALIFICATION);
        assertThat(testAcademicHistory.getYearOfPassing()).isEqualTo(UPDATED_YEAR_OF_PASSING);
        assertThat(testAcademicHistory.getInstitution()).isEqualTo(UPDATED_INSTITUTION);
        assertThat(testAcademicHistory.getUniversity()).isEqualTo(UPDATED_UNIVERSITY);
        assertThat(testAcademicHistory.getEnrollmentNo()).isEqualTo(UPDATED_ENROLLMENT_NO);
        assertThat(testAcademicHistory.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testAcademicHistory.getPercentage()).isEqualTo(UPDATED_PERCENTAGE);

        // Validate the AcademicHistory in Elasticsearch
//        verify(mockAcademicHistorySearchRepository, times(1)).save(testAcademicHistory);
    }

    @Test
    @Transactional
    public void updateNonExistingAcademicHistory() throws Exception {
        int databaseSizeBeforeUpdate = academicHistoryRepository.findAll().size();

        // Create the AcademicHistory
        AcademicHistoryDTO academicHistoryDTO = academicHistoryMapper.toDto(academicHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcademicHistoryMockMvc.perform(put("/api/academic-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(academicHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AcademicHistory in the database
        List<AcademicHistory> academicHistoryList = academicHistoryRepository.findAll();
        assertThat(academicHistoryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AcademicHistory in Elasticsearch
//        verify(mockAcademicHistorySearchRepository, times(0)).save(academicHistory);
    }

    @Test
    @Transactional
    public void deleteAcademicHistory() throws Exception {
        // Initialize the database
        academicHistoryRepository.saveAndFlush(academicHistory);

        int databaseSizeBeforeDelete = academicHistoryRepository.findAll().size();

        // Delete the academicHistory
        restAcademicHistoryMockMvc.perform(delete("/api/academic-histories/{id}", academicHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AcademicHistory> academicHistoryList = academicHistoryRepository.findAll();
        assertThat(academicHistoryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AcademicHistory in Elasticsearch
//        verify(mockAcademicHistorySearchRepository, times(1)).deleteById(academicHistory.getId());
    }

    @Test
    @Transactional
    public void searchAcademicHistory() throws Exception {
        // Initialize the database
        academicHistoryRepository.saveAndFlush(academicHistory);
//        when(mockAcademicHistorySearchRepository.search(queryStringQuery("id:" + academicHistory.getId())))
//            .thenReturn(Collections.singletonList(academicHistory));
        // Search the academicHistory
        restAcademicHistoryMockMvc.perform(get("/api/_search/academic-histories?query=id:" + academicHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(academicHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualification").value(hasItem(DEFAULT_QUALIFICATION)))
            .andExpect(jsonPath("$.[*].yearOfPassing").value(hasItem(DEFAULT_YEAR_OF_PASSING)))
            .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION)))
            .andExpect(jsonPath("$.[*].university").value(hasItem(DEFAULT_UNIVERSITY)))
            .andExpect(jsonPath("$.[*].enrollmentNo").value(hasItem(DEFAULT_ENROLLMENT_NO.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicHistory.class);
        AcademicHistory academicHistory1 = new AcademicHistory();
        academicHistory1.setId(1L);
        AcademicHistory academicHistory2 = new AcademicHistory();
        academicHistory2.setId(academicHistory1.getId());
        assertThat(academicHistory1).isEqualTo(academicHistory2);
        academicHistory2.setId(2L);
        assertThat(academicHistory1).isNotEqualTo(academicHistory2);
        academicHistory1.setId(null);
        assertThat(academicHistory1).isNotEqualTo(academicHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcademicHistoryDTO.class);
        AcademicHistoryDTO academicHistoryDTO1 = new AcademicHistoryDTO();
        academicHistoryDTO1.setId(1L);
        AcademicHistoryDTO academicHistoryDTO2 = new AcademicHistoryDTO();
        assertThat(academicHistoryDTO1).isNotEqualTo(academicHistoryDTO2);
        academicHistoryDTO2.setId(academicHistoryDTO1.getId());
        assertThat(academicHistoryDTO1).isEqualTo(academicHistoryDTO2);
        academicHistoryDTO2.setId(2L);
        assertThat(academicHistoryDTO1).isNotEqualTo(academicHistoryDTO2);
        academicHistoryDTO1.setId(null);
        assertThat(academicHistoryDTO1).isNotEqualTo(academicHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(academicHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(academicHistoryMapper.fromId(null)).isNull();
    }
}
