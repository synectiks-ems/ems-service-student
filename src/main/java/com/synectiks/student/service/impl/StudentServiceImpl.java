package com.synectiks.student.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.student.domain.Student;
import com.synectiks.student.repository.StudentRepository;
import com.synectiks.student.repository.search.StudentSearchRepository;
import com.synectiks.student.service.StudentService;
import com.synectiks.student.service.dto.StudentDTO;
import com.synectiks.student.service.mapper.StudentMapper;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final StudentSearchRepository studentSearchRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, StudentSearchRepository studentSearchRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentSearchRepository = studentSearchRepository;
    }

    /**
     * Save a student.
     *
     * @param studentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        StudentDTO result = studentMapper.toDto(student);
        studentSearchRepository.save(student);
        return result;
    }

    /**
     * Get all the students.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        log.debug("Request to get all Students");
        return studentRepository.findAll().stream()
            .map(studentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one student by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id)
            .map(studentMapper::toDto);
    }

    /**
     * Delete the student by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
        studentSearchRepository.deleteById(id);
    }

    /**
     * Search for the student corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> search(String query) {
        log.debug("Request to search Students for query {}", query);
        return null;
    }
}
