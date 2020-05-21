package com.synectiks.student.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A AcademicHistory.
 */
@Entity
@Table(name = "academic_history")
public class AcademicHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "year_of_passing")
    private String yearOfPassing;

    @Column(name = "institution")
    private String institution;

    @Column(name = "university")
    private String university;

    @Column(name = "enrollment_no")
    private Long enrollmentNo;

    @Column(name = "score")
    private Long score;

    @Column(name = "percentage")
    private Integer percentage;

    @ManyToOne
    @JsonIgnoreProperties("academicHistories")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public AcademicHistory qualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public AcademicHistory yearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
        return this;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getInstitution() {
        return institution;
    }

    public AcademicHistory institution(String institution) {
        this.institution = institution;
        return this;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUniversity() {
        return university;
    }

    public AcademicHistory university(String university) {
        this.university = university;
        return this;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getEnrollmentNo() {
        return enrollmentNo;
    }

    public AcademicHistory enrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
        return this;
    }

    public void setEnrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public Long getScore() {
        return score;
    }

    public AcademicHistory score(Long score) {
        this.score = score;
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public AcademicHistory percentage(Integer percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Student getStudent() {
        return student;
    }

    public AcademicHistory student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AcademicHistory)) {
            return false;
        }
        return id != null && id.equals(((AcademicHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AcademicHistory{" +
            "id=" + getId() +
            ", qualification='" + getQualification() + "'" +
            ", yearOfPassing='" + getYearOfPassing() + "'" +
            ", institution='" + getInstitution() + "'" +
            ", university='" + getUniversity() + "'" +
            ", enrollmentNo=" + getEnrollmentNo() +
            ", score=" + getScore() +
            ", percentage=" + getPercentage() +
            "}";
    }
}
