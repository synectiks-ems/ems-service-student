package com.synectiks.student.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.student.service.AcademicHistoryService;
import com.synectiks.student.service.dto.AcademicHistoryDTO;
import com.synectiks.student.web.rest.errors.BadRequestAlertException;
import com.synectiks.student.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.student.domain.AcademicHistory}.
 */
@RestController
@RequestMapping("/api")
public class AcademicHistoryResource {

    private final Logger log = LoggerFactory.getLogger(AcademicHistoryResource.class);

    private static final String ENTITY_NAME = "studentAcademicHistory";

    private String applicationName;

    private final AcademicHistoryService academicHistoryService;

    public AcademicHistoryResource(AcademicHistoryService academicHistoryService) {
        this.academicHistoryService = academicHistoryService;
    }

    /**
     * {@code POST  /academic-histories} : Create a new academicHistory.
     *
     * @param academicHistoryDTO the academicHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new academicHistoryDTO, or with status {@code 400 (Bad Request)} if the academicHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/academic-histories")
    public ResponseEntity<AcademicHistoryDTO> createAcademicHistory(@RequestBody AcademicHistoryDTO academicHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicHistory : {}", academicHistoryDTO);
        if (academicHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcademicHistoryDTO result = academicHistoryService.save(academicHistoryDTO);
        return ResponseEntity.created(new URI("/api/academic-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /academic-histories} : Updates an existing academicHistory.
     *
     * @param academicHistoryDTO the academicHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated academicHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the academicHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the academicHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/academic-histories")
    public ResponseEntity<AcademicHistoryDTO> updateAcademicHistory(@RequestBody AcademicHistoryDTO academicHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicHistory : {}", academicHistoryDTO);
        if (academicHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicHistoryDTO result = academicHistoryService.save(academicHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, academicHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /academic-histories} : get all the academicHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of academicHistories in body.
     */
    @GetMapping("/academic-histories")
    public List<AcademicHistoryDTO> getAllAcademicHistories() {
        log.debug("REST request to get all AcademicHistories");
        return academicHistoryService.findAll();
    }

    /**
     * {@code GET  /academic-histories/:id} : get the "id" academicHistory.
     *
     * @param id the id of the academicHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the academicHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/academic-histories/{id}")
    public ResponseEntity<AcademicHistoryDTO> getAcademicHistory(@PathVariable Long id) {
        log.debug("REST request to get AcademicHistory : {}", id);
        Optional<AcademicHistoryDTO> academicHistoryDTO = academicHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicHistoryDTO);
    }

    /**
     * {@code DELETE  /academic-histories/:id} : delete the "id" academicHistory.
     *
     * @param id the id of the academicHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/academic-histories/{id}")
    public ResponseEntity<Void> deleteAcademicHistory(@PathVariable Long id) {
        log.debug("REST request to delete AcademicHistory : {}", id);
        academicHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/academic-histories?query=:query} : search for the academicHistory corresponding
     * to the query.
     *
     * @param query the query of the academicHistory search.
     * @return the result of the search.
     */
    @GetMapping("/_search/academic-histories")
    public List<AcademicHistoryDTO> searchAcademicHistories(@RequestParam String query) {
        log.debug("REST request to search AcademicHistories for query {}", query);
        return academicHistoryService.search(query);
    }

}
