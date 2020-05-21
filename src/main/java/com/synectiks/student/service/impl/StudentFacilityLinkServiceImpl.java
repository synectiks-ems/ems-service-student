package com.synectiks.student.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.student.domain.StudentFacilityLink;
import com.synectiks.student.repository.StudentFacilityLinkRepository;
import com.synectiks.student.repository.search.StudentFacilityLinkSearchRepository;
import com.synectiks.student.service.StudentFacilityLinkService;
import com.synectiks.student.service.dto.StudentFacilityLinkDTO;
import com.synectiks.student.service.mapper.StudentFacilityLinkMapper;

/**
 * Service Implementation for managing {@link StudentFacilityLink}.
 */
@Service
@Transactional
public class StudentFacilityLinkServiceImpl implements StudentFacilityLinkService {

    private final Logger log = LoggerFactory.getLogger(StudentFacilityLinkServiceImpl.class);

    private final StudentFacilityLinkRepository studentFacilityLinkRepository;

    private final StudentFacilityLinkMapper studentFacilityLinkMapper;

    private final StudentFacilityLinkSearchRepository studentFacilityLinkSearchRepository;

    public StudentFacilityLinkServiceImpl(StudentFacilityLinkRepository studentFacilityLinkRepository, StudentFacilityLinkMapper studentFacilityLinkMapper, StudentFacilityLinkSearchRepository studentFacilityLinkSearchRepository) {
        this.studentFacilityLinkRepository = studentFacilityLinkRepository;
        this.studentFacilityLinkMapper = studentFacilityLinkMapper;
        this.studentFacilityLinkSearchRepository = studentFacilityLinkSearchRepository;
    }

    /**
     * Save a studentFacilityLink.
     *
     * @param studentFacilityLinkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StudentFacilityLinkDTO save(StudentFacilityLinkDTO studentFacilityLinkDTO) {
        log.debug("Request to save StudentFacilityLink : {}", studentFacilityLinkDTO);
        StudentFacilityLink studentFacilityLink = studentFacilityLinkMapper.toEntity(studentFacilityLinkDTO);
        studentFacilityLink = studentFacilityLinkRepository.save(studentFacilityLink);
        StudentFacilityLinkDTO result = studentFacilityLinkMapper.toDto(studentFacilityLink);
        studentFacilityLinkSearchRepository.save(studentFacilityLink);
        return result;
    }

    /**
     * Get all the studentFacilityLinks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentFacilityLinkDTO> findAll() {
        log.debug("Request to get all StudentFacilityLinks");
        return studentFacilityLinkRepository.findAll().stream()
            .map(studentFacilityLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one studentFacilityLink by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentFacilityLinkDTO> findOne(Long id) {
        log.debug("Request to get StudentFacilityLink : {}", id);
        return studentFacilityLinkRepository.findById(id)
            .map(studentFacilityLinkMapper::toDto);
    }

    /**
     * Delete the studentFacilityLink by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentFacilityLink : {}", id);
        studentFacilityLinkRepository.deleteById(id);
        studentFacilityLinkSearchRepository.deleteById(id);
    }

    /**
     * Search for the studentFacilityLink corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentFacilityLinkDTO> search(String query) {
        log.debug("Request to search StudentFacilityLinks for query {}", query);
        return null;
    }
}
