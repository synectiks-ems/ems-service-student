package com.synectiks.student.domain.vo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.student.domain.*;

/**
 * A Student value object.
 */

public class CmsStudentVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String studentName;
    private String studentMiddleName;
    private String studentLastName;
    private String fatherName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherName;
    private String motherMiddleName;
    private String motherLastName;
    private String studentAadharNo;
    private String studentPanNo;
    private String studentSocialSecurityNo;
    private String studentTaxReferenceNo;
    private String studentBplNo;
    private String studentDrivingLicenseNo;
    private String studentPassportNo;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String religion;
    private String caste;
    private String subCaste;
    private Integer age;
    private String sex;
    private String studentLocalAddress;
    private String studentPermanentAddress;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String studentPrimaryCellNumber;
    private String studentAlternateCellNumber;
    private String studentLandLinePhoneNumber;
    private String studentPrimaryEmailId;
    private String studentAlternateEmailId;
    private String relationWithStudent;
    private String emergencyContactName;
    private String emergencyContactMiddleName;
    private String emergencyContactLastName;
    private String emergencyContactCellNumber;
    private String emergencyContactLandLinePhoneNumber;
    private String emergencyContactEmailId;
    private String studentImagePath;
    private String admissionNo;
    private String enrollmentNo;
    private String rollNo;
    private String studentType;
    private String fatherCellNumber;
    private String fatherEmailId;
    private String fatherOccupation;
    private String fatherOfficeEmailId;
    private String fatherOfficeAddress;
    private String fatherOfficeCellNumber;
    private String fatherOfficeLandLinePhoneNumber;
    private String fatherAadharNo;
    private String fatherPanNo;
    private String fatherSocialSecurityNo;
    private String fatherTaxReferenceNo;
    private String fatherBplNo;
    private String fatherDrivingLicenseNo;
    private String fatherPassportNo;
    private String fatherImagePath;
    private String motherCellNumber;
    private String motherEmailId;
    private String motherOccupation;
    private String motherOfficeEmailId;
    private String motherOfficeAddress;
    private String motherOfficeCellNumber;
    private String motherOfficeLandLinePhoneNumber;
    private String motherAadharNo;
    private String motherPanNo;
    private String motherSocialSecurityNo;
    private String motherTaxReferenceNo;
    private String motherBplNo;
    private String motherDrivingLicenseNo;
    private String motherPassportNo;
    private String motherImagePath;
    private String guardianName;
    private String guardianMiddleName;
    private String guardianLastName;
    private String guardianAddress;
    private String guardianCellNumber;
    private String guardianLandLinePhoneNumber;
    private String guardianEmailId;
    private String guardianOccupation;
    private String guardianOfficeEmailId;
    private String guardianOfficeAddress;
    private String guardianOfficeCellNumber;
    private String guardianOfficeLandLinePhoneNumber;
    private String guardianImagePath;
    private String isGuardianSponsorAgency;
    private String sponsorAgencyName;
    private String sponsorAgencyRegistrationNo;
    private String sponsorAgencyAddress;
    private String sponsorAgencyCellNumber;
    private String sponsorAgencyLandLineNumber;
    private String sponsorAgencyEmailId;
    private String sponsorAgencyAppointeeName;
    private String sponsorAgencyAppointeeDesignation;
    private String sponsorAgencyAppointeeCellNumber;
    private String sponsorAgencyAppointeeLandLineNumber;
    private String sponsorAgencyAppointeeEmailId;
    private String sponsorAgencyAppointeeOfficeAddress;
    private String isPhysicallyChallenged;
    private String detailsOfDisability;
    private String disabilityCertificateNo;
    private String disabilityCertificateIssueAuthority;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate disabilityCertificateIssueDate;
    private Integer percentagOfDisability;
    private String bloodGroup;
    private String vaccinationDetails;
    private String otherMedicalDetails;
    private String comments;
    private Long departmentId;
    private Long batchId;
    private Long sectionId;
    private Long branchId;
    private Branch branch;
    private Department department;
    private Section section;
    private Batch batch;
    private String strCreatedOn;
    private String strUpdatedOn;
    private String strDisabilityCertificateIssueDate;
    private String strDateOfBirth;
    private Long academicYearId;
    private AcademicYear academicYear;
    private Long facilityId;
    private Long studentId;
    private Facility facility;

    private FeeCategory feeCategory;
    private Long feeCategoryId;
    private List<FeeDetails> feeDetailsList;
    private Float totalFee;
    private String strDueDate;
    private Long totalFeePaid;
    private Long totalFeeOverDue;
    private List<StudentFacilityLink> facilityList;
    private List<CmsInvoice> paymentHistory;
    private String strNextPaymentDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentMiddleName() {
		return studentMiddleName;
	}
	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}
	public String getFatherLastName() {
		return fatherLastName;
	}
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherMiddleName() {
		return motherMiddleName;
	}
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	public String getStudentAadharNo() {
		return studentAadharNo;
	}
	public void setStudentAadharNo(String studentAadharNo) {
		this.studentAadharNo = studentAadharNo;
	}
	public String getStudentPanNo() {
		return studentPanNo;
	}
	public void setStudentPanNo(String studentPanNo) {
		this.studentPanNo = studentPanNo;
	}
	public String getStudentSocialSecurityNo() {
		return studentSocialSecurityNo;
	}
	public void setStudentSocialSecurityNo(String studentSocialSecurityNo) {
		this.studentSocialSecurityNo = studentSocialSecurityNo;
	}
	public String getStudentTaxReferenceNo() {
		return studentTaxReferenceNo;
	}
	public void setStudentTaxReferenceNo(String studentTaxReferenceNo) {
		this.studentTaxReferenceNo = studentTaxReferenceNo;
	}
	public String getStudentBplNo() {
		return studentBplNo;
	}
	public void setStudentBplNo(String studentBplNo) {
		this.studentBplNo = studentBplNo;
	}
	public String getStudentDrivingLicenseNo() {
		return studentDrivingLicenseNo;
	}
	public void setStudentDrivingLicenseNo(String studentDrivingLicenseNo) {
		this.studentDrivingLicenseNo = studentDrivingLicenseNo;
	}
	public String getStudentPassportNo() {
		return studentPassportNo;
	}
	public void setStudentPassportNo(String studentPassportNo) {
		this.studentPassportNo = studentPassportNo;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getSubCaste() {
		return subCaste;
	}
	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStudentLocalAddress() {
		return studentLocalAddress;
	}
	public void setStudentLocalAddress(String studentLocalAddress) {
		this.studentLocalAddress = studentLocalAddress;
	}
	public String getStudentPermanentAddress() {
		return studentPermanentAddress;
	}
	public void setStudentPermanentAddress(String studentPermanentAddress) {
		this.studentPermanentAddress = studentPermanentAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getStudentPrimaryCellNumber() {
		return studentPrimaryCellNumber;
	}
	public void setStudentPrimaryCellNumber(String studentPrimaryCellNumber) {
		this.studentPrimaryCellNumber = studentPrimaryCellNumber;
	}
	public String getStudentAlternateCellNumber() {
		return studentAlternateCellNumber;
	}
	public void setStudentAlternateCellNumber(String studentAlternateCellNumber) {
		this.studentAlternateCellNumber = studentAlternateCellNumber;
	}
	public String getStudentLandLinePhoneNumber() {
		return studentLandLinePhoneNumber;
	}
	public void setStudentLandLinePhoneNumber(String studentLandLinePhoneNumber) {
		this.studentLandLinePhoneNumber = studentLandLinePhoneNumber;
	}
	public String getStudentPrimaryEmailId() {
		return studentPrimaryEmailId;
	}
	public void setStudentPrimaryEmailId(String studentPrimaryEmailId) {
		this.studentPrimaryEmailId = studentPrimaryEmailId;
	}
	public String getStudentAlternateEmailId() {
		return studentAlternateEmailId;
	}
	public void setStudentAlternateEmailId(String studentAlternateEmailId) {
		this.studentAlternateEmailId = studentAlternateEmailId;
	}
	public String getRelationWithStudent() {
		return relationWithStudent;
	}
	public void setRelationWithStudent(String relationWithStudent) {
		this.relationWithStudent = relationWithStudent;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactMiddleName() {
		return emergencyContactMiddleName;
	}
	public void setEmergencyContactMiddleName(String emergencyContactMiddleName) {
		this.emergencyContactMiddleName = emergencyContactMiddleName;
	}
	public String getEmergencyContactLastName() {
		return emergencyContactLastName;
	}
	public void setEmergencyContactLastName(String emergencyContactLastName) {
		this.emergencyContactLastName = emergencyContactLastName;
	}
	public String getEmergencyContactCellNumber() {
		return emergencyContactCellNumber;
	}
	public void setEmergencyContactCellNumber(String emergencyContactCellNumber) {
		this.emergencyContactCellNumber = emergencyContactCellNumber;
	}
	public String getEmergencyContactLandLinePhoneNumber() {
		return emergencyContactLandLinePhoneNumber;
	}
	public void setEmergencyContactLandLinePhoneNumber(String emergencyContactLandLinePhoneNumber) {
		this.emergencyContactLandLinePhoneNumber = emergencyContactLandLinePhoneNumber;
	}
	public String getEmergencyContactEmailId() {
		return emergencyContactEmailId;
	}
	public void setEmergencyContactEmailId(String emergencyContactEmailId) {
		this.emergencyContactEmailId = emergencyContactEmailId;
	}
	public String getStudentImagePath() {
		return studentImagePath;
	}
	public void setStudentImagePath(String studentImagePath) {
		this.studentImagePath = studentImagePath;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getEnrollmentNo() {
		return enrollmentNo;
	}
	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	public String getFatherCellNumber() {
		return fatherCellNumber;
	}
	public void setFatherCellNumber(String fatherCellNumber) {
		this.fatherCellNumber = fatherCellNumber;
	}
	public String getFatherEmailId() {
		return fatherEmailId;
	}
	public void setFatherEmailId(String fatherEmailId) {
		this.fatherEmailId = fatherEmailId;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getFatherOfficeEmailId() {
		return fatherOfficeEmailId;
	}
	public void setFatherOfficeEmailId(String fatherOfficeEmailId) {
		this.fatherOfficeEmailId = fatherOfficeEmailId;
	}
	public String getFatherOfficeAddress() {
		return fatherOfficeAddress;
	}
	public void setFatherOfficeAddress(String fatherOfficeAddress) {
		this.fatherOfficeAddress = fatherOfficeAddress;
	}
	public String getFatherOfficeCellNumber() {
		return fatherOfficeCellNumber;
	}
	public void setFatherOfficeCellNumber(String fatherOfficeCellNumber) {
		this.fatherOfficeCellNumber = fatherOfficeCellNumber;
	}
	public String getFatherOfficeLandLinePhoneNumber() {
		return fatherOfficeLandLinePhoneNumber;
	}
	public void setFatherOfficeLandLinePhoneNumber(String fatherOfficeLandLinePhoneNumber) {
		this.fatherOfficeLandLinePhoneNumber = fatherOfficeLandLinePhoneNumber;
	}
	public String getFatherAadharNo() {
		return fatherAadharNo;
	}
	public void setFatherAadharNo(String fatherAadharNo) {
		this.fatherAadharNo = fatherAadharNo;
	}
	public String getFatherPanNo() {
		return fatherPanNo;
	}
	public void setFatherPanNo(String fatherPanNo) {
		this.fatherPanNo = fatherPanNo;
	}
	public String getFatherSocialSecurityNo() {
		return fatherSocialSecurityNo;
	}
	public void setFatherSocialSecurityNo(String fatherSocialSecurityNo) {
		this.fatherSocialSecurityNo = fatherSocialSecurityNo;
	}
	public String getFatherTaxReferenceNo() {
		return fatherTaxReferenceNo;
	}
	public void setFatherTaxReferenceNo(String fatherTaxReferenceNo) {
		this.fatherTaxReferenceNo = fatherTaxReferenceNo;
	}
	public String getFatherBplNo() {
		return fatherBplNo;
	}
	public void setFatherBplNo(String fatherBplNo) {
		this.fatherBplNo = fatherBplNo;
	}
	public String getFatherDrivingLicenseNo() {
		return fatherDrivingLicenseNo;
	}
	public void setFatherDrivingLicenseNo(String fatherDrivingLicenseNo) {
		this.fatherDrivingLicenseNo = fatherDrivingLicenseNo;
	}
	public String getFatherPassportNo() {
		return fatherPassportNo;
	}
	public void setFatherPassportNo(String fatherPassportNo) {
		this.fatherPassportNo = fatherPassportNo;
	}
	public String getFatherImagePath() {
		return fatherImagePath;
	}
	public void setFatherImagePath(String fatherImagePath) {
		this.fatherImagePath = fatherImagePath;
	}
	public String getMotherCellNumber() {
		return motherCellNumber;
	}
	public void setMotherCellNumber(String motherCellNumber) {
		this.motherCellNumber = motherCellNumber;
	}
	public String getMotherEmailId() {
		return motherEmailId;
	}
	public void setMotherEmailId(String motherEmailId) {
		this.motherEmailId = motherEmailId;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getMotherOfficeEmailId() {
		return motherOfficeEmailId;
	}
	public void setMotherOfficeEmailId(String motherOfficeEmailId) {
		this.motherOfficeEmailId = motherOfficeEmailId;
	}
	public String getMotherOfficeAddress() {
		return motherOfficeAddress;
	}
	public void setMotherOfficeAddress(String motherOfficeAddress) {
		this.motherOfficeAddress = motherOfficeAddress;
	}
	public String getMotherOfficeCellNumber() {
		return motherOfficeCellNumber;
	}
	public void setMotherOfficeCellNumber(String motherOfficeCellNumber) {
		this.motherOfficeCellNumber = motherOfficeCellNumber;
	}
	public String getMotherOfficeLandLinePhoneNumber() {
		return motherOfficeLandLinePhoneNumber;
	}
	public void setMotherOfficeLandLinePhoneNumber(String motherOfficeLandLinePhoneNumber) {
		this.motherOfficeLandLinePhoneNumber = motherOfficeLandLinePhoneNumber;
	}
	public String getMotherAadharNo() {
		return motherAadharNo;
	}
	public void setMotherAadharNo(String motherAadharNo) {
		this.motherAadharNo = motherAadharNo;
	}
	public String getMotherPanNo() {
		return motherPanNo;
	}
	public void setMotherPanNo(String motherPanNo) {
		this.motherPanNo = motherPanNo;
	}
	public String getMotherSocialSecurityNo() {
		return motherSocialSecurityNo;
	}
	public void setMotherSocialSecurityNo(String motherSocialSecurityNo) {
		this.motherSocialSecurityNo = motherSocialSecurityNo;
	}
	public String getMotherTaxReferenceNo() {
		return motherTaxReferenceNo;
	}
	public void setMotherTaxReferenceNo(String motherTaxReferenceNo) {
		this.motherTaxReferenceNo = motherTaxReferenceNo;
	}
	public String getMotherBplNo() {
		return motherBplNo;
	}
	public void setMotherBplNo(String motherBplNo) {
		this.motherBplNo = motherBplNo;
	}
	public String getMotherDrivingLicenseNo() {
		return motherDrivingLicenseNo;
	}
	public void setMotherDrivingLicenseNo(String motherDrivingLicenseNo) {
		this.motherDrivingLicenseNo = motherDrivingLicenseNo;
	}
	public String getMotherPassportNo() {
		return motherPassportNo;
	}
	public void setMotherPassportNo(String motherPassportNo) {
		this.motherPassportNo = motherPassportNo;
	}
	public String getMotherImagePath() {
		return motherImagePath;
	}
	public void setMotherImagePath(String motherImagePath) {
		this.motherImagePath = motherImagePath;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}
	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}
	public String getGuardianLastName() {
		return guardianLastName;
	}
	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}
	public String getGuardianAddress() {
		return guardianAddress;
	}
	public void setGuardianAddress(String guardianAddress) {
		this.guardianAddress = guardianAddress;
	}
	public String getGuardianCellNumber() {
		return guardianCellNumber;
	}
	public void setGuardianCellNumber(String guardianCellNumber) {
		this.guardianCellNumber = guardianCellNumber;
	}
	public String getGuardianLandLinePhoneNumber() {
		return guardianLandLinePhoneNumber;
	}
	public void setGuardianLandLinePhoneNumber(String guardianLandLinePhoneNumber) {
		this.guardianLandLinePhoneNumber = guardianLandLinePhoneNumber;
	}
	public String getGuardianEmailId() {
		return guardianEmailId;
	}
	public void setGuardianEmailId(String guardianEmailId) {
		this.guardianEmailId = guardianEmailId;
	}
	public String getGuardianOccupation() {
		return guardianOccupation;
	}
	public void setGuardianOccupation(String guardianOccupation) {
		this.guardianOccupation = guardianOccupation;
	}
	public String getGuardianOfficeEmailId() {
		return guardianOfficeEmailId;
	}
	public void setGuardianOfficeEmailId(String guardianOfficeEmailId) {
		this.guardianOfficeEmailId = guardianOfficeEmailId;
	}
	public String getGuardianOfficeAddress() {
		return guardianOfficeAddress;
	}
	public void setGuardianOfficeAddress(String guardianOfficeAddress) {
		this.guardianOfficeAddress = guardianOfficeAddress;
	}
	public String getGuardianOfficeCellNumber() {
		return guardianOfficeCellNumber;
	}
	public void setGuardianOfficeCellNumber(String guardianOfficeCellNumber) {
		this.guardianOfficeCellNumber = guardianOfficeCellNumber;
	}
	public String getGuardianOfficeLandLinePhoneNumber() {
		return guardianOfficeLandLinePhoneNumber;
	}
	public void setGuardianOfficeLandLinePhoneNumber(String guardianOfficeLandLinePhoneNumber) {
		this.guardianOfficeLandLinePhoneNumber = guardianOfficeLandLinePhoneNumber;
	}
	public String getGuardianImagePath() {
		return guardianImagePath;
	}
	public void setGuardianImagePath(String guardianImagePath) {
		this.guardianImagePath = guardianImagePath;
	}
	public String getIsGuardianSponsorAgency() {
		return isGuardianSponsorAgency;
	}
	public void setIsGuardianSponsorAgency(String isGuardianSponsorAgency) {
		this.isGuardianSponsorAgency = isGuardianSponsorAgency;
	}
	public String getSponsorAgencyName() {
		return sponsorAgencyName;
	}
	public void setSponsorAgencyName(String sponsorAgencyName) {
		this.sponsorAgencyName = sponsorAgencyName;
	}
	public String getSponsorAgencyRegistrationNo() {
		return sponsorAgencyRegistrationNo;
	}
	public void setSponsorAgencyRegistrationNo(String sponsorAgencyRegistrationNo) {
		this.sponsorAgencyRegistrationNo = sponsorAgencyRegistrationNo;
	}
	public String getSponsorAgencyAddress() {
		return sponsorAgencyAddress;
	}
	public void setSponsorAgencyAddress(String sponsorAgencyAddress) {
		this.sponsorAgencyAddress = sponsorAgencyAddress;
	}
	public String getSponsorAgencyCellNumber() {
		return sponsorAgencyCellNumber;
	}
	public void setSponsorAgencyCellNumber(String sponsorAgencyCellNumber) {
		this.sponsorAgencyCellNumber = sponsorAgencyCellNumber;
	}
	public String getSponsorAgencyLandLineNumber() {
		return sponsorAgencyLandLineNumber;
	}
	public void setSponsorAgencyLandLineNumber(String sponsorAgencyLandLineNumber) {
		this.sponsorAgencyLandLineNumber = sponsorAgencyLandLineNumber;
	}
	public String getSponsorAgencyEmailId() {
		return sponsorAgencyEmailId;
	}
	public void setSponsorAgencyEmailId(String sponsorAgencyEmailId) {
		this.sponsorAgencyEmailId = sponsorAgencyEmailId;
	}
	public String getSponsorAgencyAppointeeName() {
		return sponsorAgencyAppointeeName;
	}
	public void setSponsorAgencyAppointeeName(String sponsorAgencyAppointeeName) {
		this.sponsorAgencyAppointeeName = sponsorAgencyAppointeeName;
	}
	public String getSponsorAgencyAppointeeDesignation() {
		return sponsorAgencyAppointeeDesignation;
	}
	public void setSponsorAgencyAppointeeDesignation(String sponsorAgencyAppointeeDesignation) {
		this.sponsorAgencyAppointeeDesignation = sponsorAgencyAppointeeDesignation;
	}
	public String getSponsorAgencyAppointeeCellNumber() {
		return sponsorAgencyAppointeeCellNumber;
	}
	public void setSponsorAgencyAppointeeCellNumber(String sponsorAgencyAppointeeCellNumber) {
		this.sponsorAgencyAppointeeCellNumber = sponsorAgencyAppointeeCellNumber;
	}
	public String getSponsorAgencyAppointeeLandLineNumber() {
		return sponsorAgencyAppointeeLandLineNumber;
	}
	public void setSponsorAgencyAppointeeLandLineNumber(String sponsorAgencyAppointeeLandLineNumber) {
		this.sponsorAgencyAppointeeLandLineNumber = sponsorAgencyAppointeeLandLineNumber;
	}
	public String getSponsorAgencyAppointeeEmailId() {
		return sponsorAgencyAppointeeEmailId;
	}
	public void setSponsorAgencyAppointeeEmailId(String sponsorAgencyAppointeeEmailId) {
		this.sponsorAgencyAppointeeEmailId = sponsorAgencyAppointeeEmailId;
	}
	public String getSponsorAgencyAppointeeOfficeAddress() {
		return sponsorAgencyAppointeeOfficeAddress;
	}
	public void setSponsorAgencyAppointeeOfficeAddress(String sponsorAgencyAppointeeOfficeAddress) {
		this.sponsorAgencyAppointeeOfficeAddress = sponsorAgencyAppointeeOfficeAddress;
	}
	public String getIsPhysicallyChallenged() {
		return isPhysicallyChallenged;
	}
	public void setIsPhysicallyChallenged(String isPhysicallyChallenged) {
		this.isPhysicallyChallenged = isPhysicallyChallenged;
	}
	public String getDetailsOfDisability() {
		return detailsOfDisability;
	}
	public void setDetailsOfDisability(String detailsOfDisability) {
		this.detailsOfDisability = detailsOfDisability;
	}
	public String getDisabilityCertificateNo() {
		return disabilityCertificateNo;
	}
	public void setDisabilityCertificateNo(String disabilityCertificateNo) {
		this.disabilityCertificateNo = disabilityCertificateNo;
	}
	public String getDisabilityCertificateIssueAuthority() {
		return disabilityCertificateIssueAuthority;
	}
	public void setDisabilityCertificateIssueAuthority(String disabilityCertificateIssueAuthority) {
		this.disabilityCertificateIssueAuthority = disabilityCertificateIssueAuthority;
	}
	public LocalDate getDisabilityCertificateIssueDate() {
		return disabilityCertificateIssueDate;
	}
	public void setDisabilityCertificateIssueDate(LocalDate disabilityCertificateIssueDate) {
		this.disabilityCertificateIssueDate = disabilityCertificateIssueDate;
	}
	public Integer getPercentagOfDisability() {
		return percentagOfDisability;
	}
	public void setPercentagOfDisability(Integer percentagOfDisability) {
		this.percentagOfDisability = percentagOfDisability;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getVaccinationDetails() {
		return vaccinationDetails;
	}
	public void setVaccinationDetails(String vaccinationDetails) {
		this.vaccinationDetails = vaccinationDetails;
	}
	public String getOtherMedicalDetails() {
		return otherMedicalDetails;
	}
	public void setOtherMedicalDetails(String otherMedicalDetails) {
		this.otherMedicalDetails = otherMedicalDetails;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}
	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}
	public String getStrDisabilityCertificateIssueDate() {
		return strDisabilityCertificateIssueDate;
	}
	public void setStrDisabilityCertificateIssueDate(String strDisabilityCertificateIssueDate) {
		this.strDisabilityCertificateIssueDate = strDisabilityCertificateIssueDate;
	}
	public String getStrDateOfBirth() {
		return strDateOfBirth;
	}
	public void setStrDateOfBirth(String strDateOfBirth) {
		this.strDateOfBirth = strDateOfBirth;
	}
	public Long getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public FeeCategory getFeeCategory() {
		return feeCategory;
	}
	public void setFeeCategory(FeeCategory feeCategory) {
		this.feeCategory = feeCategory;
	}
	public Long getFeeCategoryId() {
		return feeCategoryId;
	}
	public void setFeeCategoryId(Long feeCategoryId) {
		this.feeCategoryId = feeCategoryId;
	}
	public List<FeeDetails> getFeeDetailsList() {
		return feeDetailsList;
	}
	public void setFeeDetailsList(List<FeeDetails> feeDetailsList) {
		this.feeDetailsList = feeDetailsList;
	}
	public Float getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}
	public String getStrDueDate() {
		return strDueDate;
	}
	public void setStrDueDate(String strDueDate) {
		this.strDueDate = strDueDate;
	}
	public Long getTotalFeePaid() {
		return totalFeePaid;
	}
	public void setTotalFeePaid(Long totalFeePaid) {
		this.totalFeePaid = totalFeePaid;
	}
	public Long getTotalFeeOverDue() {
		return totalFeeOverDue;
	}
	public void setTotalFeeOverDue(Long totalFeeOverDue) {
		this.totalFeeOverDue = totalFeeOverDue;
	}
	public List<StudentFacilityLink> getFacilityList() {
		return facilityList;
	}
	public void setFacilityList(List<StudentFacilityLink> facilityList) {
		this.facilityList = facilityList;
	}
	public List<CmsInvoice> getPaymentHistory() {
		return paymentHistory;
	}
	public void setPaymentHistory(List<CmsInvoice> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
	public String getStrNextPaymentDate() {
		return strNextPaymentDate;
	}
	public void setStrNextPaymentDate(String strNextPaymentDate) {
		this.strNextPaymentDate = strNextPaymentDate;
	}

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}
