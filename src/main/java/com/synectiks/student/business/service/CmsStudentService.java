package com.synectiks.student.business.service;

import java.time.LocalDate;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.synectiks.student.config.ApplicationProperties;
import com.synectiks.student.constant.CmsConstants;
import com.synectiks.student.domain.AcademicYear;
import com.synectiks.student.domain.Batch;
import com.synectiks.student.domain.Branch;
import com.synectiks.student.domain.Department;
import com.synectiks.student.domain.Facility;
import com.synectiks.student.domain.FeeDetails;
import com.synectiks.student.domain.Invoice;
import com.synectiks.student.domain.Section;
import com.synectiks.student.domain.Student;
import com.synectiks.student.domain.StudentFacilityLink;
import com.synectiks.student.domain.vo.CmsInvoice;
import com.synectiks.student.domain.vo.CmsStudentVo;
import com.synectiks.student.filter.student.StudentListFilterInput;
import com.synectiks.student.graphql.types.student.StudentInput;
import com.synectiks.student.repository.StudentFacilityLinkRepository;
import com.synectiks.student.repository.StudentRepository;
import com.synectiks.student.service.util.CommonUtil;
import com.synectiks.student.service.util.DateFormatUtil;

@Component
public class CmsStudentService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private StudentFacilityLinkRepository studentFacilityLinkRepository;

    @Autowired
    private StudentFacilityLinkService studentFacilityLinkService;



    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName) {
//        Student std = new Student();
//
//        if(studentType != null) {
//            std.setStudentType(studentType);
//        }
//
//        if(studentName != null){
//            std.setStudentName(studentName);
//        }
//
//        if(gender != null) {
//            std.setSex(gender);
//        }
//        if(departmentId != null) {
////            Department department = new Department();
////            department.setId(departmentId);
//            std.setDepartmentId(departmentId);
//        }
//
//        if(batchId != null) {
////            Batch batch = new Batch();
////            batch.setId(batchId);
//            std.setBatchId(batchId);
//        }
//
//        if(sectionId != null) {
////            Section section = new Section();
////            section.setId(sectionId);
//            std.setSectionId(sectionId);
//        }
//        if(branchId != null) {
////            Branch branch = new Branch();
////            branch.setId(branchId);
//            std.setBranchId(branchId);
//        }
//        Example<Student> example = Example.of(std);
//        List<Student> list = this.studentRepository.findAll(example);
//        return list;
//    }
//

    public List<CmsStudentVo> getStudentList(){
        List<Student> list = this.studentRepository.findAll();
        List<CmsStudentVo> ls = changeStudentToCmsStudentList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    private List<CmsStudentVo> changeStudentToCmsStudentList(List<Student> list){
        List<CmsStudentVo> ls = new ArrayList<>();
        String prefUrl = applicationProperties.getPrefSrvUrl();
        for(Student sl: list) {
            CmsStudentVo vo = CommonUtil.createCopyProperties(sl, CmsStudentVo.class);
            convertDatesAndProvideDependencies(sl, vo);
            String deurl = prefUrl+"/api/department-by-id/"+vo.getDepartmentId();
            Department d = this.commonService.getObject(deurl, Department.class);
            vo.setDepartment(d);
            String baurl = prefUrl+"/api/batch-by-id/"+vo.getBatchId();
            Batch ba = this.commonService.getObject(baurl, Batch.class);
            vo.setBatch(ba);
            String seurl = prefUrl+"/api/section-by-id/"+vo.getSectionId();
            Section se = this.commonService.getObject(seurl, Section.class);
            vo.setSection(se);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Student s1, CmsStudentVo vo) {
        if(s1.getDateOfBirth() != null) {
            vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(s1.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }


    }

    public Student getStudent(Long id) {
        Optional<Student> o = this.studentRepository.findById(id);
        if(o.isPresent()) {
            return o.get();
        }
        return null;
    }

    public List<Student> searchStudent(StudentListFilterInput filter) {
        Student student = new Student();
        if(!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
            student.setBranchId(Long.parseLong(filter.getBranchId()));
        }
        if(!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
            student.setDepartmentId(Long.parseLong(filter.getDepartmentId()));
        }
        if(!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            student.setBatchId(Long.parseLong(filter.getBatchId()));
        }
        if(!CommonUtil.isNullOrEmpty(filter.getSectionId())) {
            student.setSectionId(Long.parseLong(filter.getSectionId()));
        }
        if(!CommonUtil.isNullOrEmpty(filter.getGender())) {
            student.setSex(filter.getGender());
        }

        if(!CommonUtil.isNullOrEmpty(filter.getStudentType())) {
            student.setStudentType(filter.getStudentType());
        }

        Example<Student> example = Example.of(student);
        List<Student> list = this.studentRepository.findAll(example);
        return list;
    }

    public CmsStudentVo addStudent(StudentInput cmsStudentVo) {
        logger.info("Saving Student");
        CmsStudentVo vo = null;
        try {
            Student student = null;
            if (cmsStudentVo.getId() == null) {
                logger.debug("Adding new Student");
                student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
                student.setCreatedOn(LocalDate.now());
            } else {
                logger.debug("Updating existing Student");
                student = this.studentRepository.findById(cmsStudentVo.getId()).get();
                student.setUpdatedOn(LocalDate.now());
            }

            student.setStudentName(cmsStudentVo.getStudentName());
            student.setStudentMiddleName(cmsStudentVo.getStudentMiddleName());
            student.setStudentLastName(cmsStudentVo.getStudentLastName());
            student.setFatherName(cmsStudentVo.getFatherName());
            student.setFatherMiddleName(cmsStudentVo.getFatherMiddleName());
            student.setFatherLastName(cmsStudentVo.getFatherLastName());
            student.setMotherName(cmsStudentVo.getMotherName());
            student.setMotherMiddleName(cmsStudentVo.getMotherMiddleName());
            student.setMotherLastName(cmsStudentVo.getMotherLastName());
            student.setStudentAadharNo(cmsStudentVo.getStudentAadharNo());
            student.setStudentPanNo(cmsStudentVo.getStudentPanNo());
            student.setStudentSocialSecurityNo(cmsStudentVo.getStudentSocialSecurityNo());
            student.setStudentTaxReferenceNo(cmsStudentVo.getStudentTaxReferenceNo());
            student.setStudentBplNo(cmsStudentVo.getStudentBplNo());
            student.setStudentDrivingLicenseNo(cmsStudentVo.getStudentDrivingLicenseNo());
            student.setStudentPassportNo(cmsStudentVo.getStudentPassportNo());
            student.setPlaceOfBirth(cmsStudentVo.getPlaceOfBirth());
            student.setReligion(cmsStudentVo.getReligion());
            student.setCaste(cmsStudentVo.getCaste());
            student.setSubCaste(cmsStudentVo.getSubCaste());
            student.setAge(cmsStudentVo.getAge());
            student.setSex(cmsStudentVo.getSex());
            student.setStudentLocalAddress(cmsStudentVo.getStudentLocalAddress());
            student.setStudentPermanentAddress(cmsStudentVo.getStudentPermanentAddress());
            student.setCity(cmsStudentVo.getCity());
            student.setState(cmsStudentVo.getState());
            student.setCountry(cmsStudentVo.getCountry());
            student.setPinCode(cmsStudentVo.getPinCode());
            student.setStudentPrimaryCellNumber(cmsStudentVo.getStudentPrimaryCellNumber());
            student.setStudentAlternateCellNumber(cmsStudentVo.getStudentAlternateCellNumber());
            student.setStudentLandLinePhoneNumber(cmsStudentVo.getStudentLandLinePhoneNumber());
            student.setStudentPrimaryEmailId(cmsStudentVo.getStudentPrimaryEmailId());
            student.setStudentAlternateEmailId(cmsStudentVo.getStudentAlternateEmailId());
            student.setRelationWithStudent(cmsStudentVo.getRelationWithStudent());
            student.setEmergencyContactName(cmsStudentVo.getEmergencyContactName());
            student.setEmergencyContactMiddleName(cmsStudentVo.getEmergencyContactMiddleName());
            student.setEmergencyContactLastName(cmsStudentVo.getEmergencyContactLastName());
            student.setEmergencyContactCellNumber(cmsStudentVo.getEmergencyContactCellNumber());
            student.setEmergencyContactLandLinePhoneNumber(cmsStudentVo.getEmergencyContactLandLinePhoneNumber());
            student.setEmergencyContactEmailId(cmsStudentVo.getEmergencyContactEmailId());
            student.setStudentImagePath(cmsStudentVo.getStudentImagePath());
            student.setAdmissionNo(cmsStudentVo.getAdmissionNo());
            student.setEnrollmentNo(cmsStudentVo.getEnrollmentNo());
            student.setRollNo(cmsStudentVo.getRollNo());
            student.setStudentType(cmsStudentVo.getStudentType());
            student.setFatherCellNumber(cmsStudentVo.getFatherCellNumber());
            student.setFatherEmailId(cmsStudentVo.getFatherEmailId());
            student.setFatherOccupation(cmsStudentVo.getFatherOccupation());
            student.setFatherOfficeAddress(cmsStudentVo.getFatherOfficeAddress());
            student.setFatherOfficeCellNumber(cmsStudentVo.getFatherOfficeCellNumber());
            student.setFatherOfficeLandLinePhoneNumber(cmsStudentVo.getFatherOfficeLandLinePhoneNumber());
            student.setFatherAadharNo(cmsStudentVo.getFatherAadharNo());
            student.setFatherPanNo(cmsStudentVo.getFatherPanNo());
            student.setFatherSocialSecurityNo(cmsStudentVo.getFatherSocialSecurityNo());
            student.setFatherTaxReferenceNo(cmsStudentVo.getFatherTaxReferenceNo());
            student.setFatherBplNo(cmsStudentVo.getFatherBplNo());
            student.setFatherDrivingLicenseNo(cmsStudentVo.getFatherDrivingLicenseNo());
            student.setFatherPassportNo(cmsStudentVo.getFatherPassportNo());
            student.setFatherImagePath(cmsStudentVo.getFatherImagePath());
            student.setMotherCellNumber(cmsStudentVo.getMotherCellNumber());
            student.setMotherEmailId(cmsStudentVo.getMotherEmailId());
            student.setMotherOccupation(cmsStudentVo.getMotherOccupation());
            student.setMotherOfficeAddress(cmsStudentVo.getMotherOfficeAddress());
            student.setMotherOfficeCellNumber(cmsStudentVo.getMotherOfficeCellNumber());
            student.setMotherOfficeLandLinePhoneNumber(cmsStudentVo.getMotherOfficeLandLinePhoneNumber());
            student.setMotherAadharNo(cmsStudentVo.getMotherAadharNo());
            student.setMotherPanNo(cmsStudentVo.getMotherPanNo());
            student.setMotherSocialSecurityNo(cmsStudentVo.getMotherSocialSecurityNo());
            student.setMotherTaxReferenceNo(cmsStudentVo.getMotherTaxReferenceNo());
            student.setMotherBplNo(cmsStudentVo.getMotherBplNo());
            student.setMotherDrivingLicenseNo(cmsStudentVo.getMotherDrivingLicenseNo());
            student.setMotherPassportNo(cmsStudentVo.getMotherPassportNo());
            student.setMotherImagePath(cmsStudentVo.getMotherImagePath());
            student.setGuardianName(cmsStudentVo.getGuardianName());
            student.setGuardianMiddleName(cmsStudentVo.getGuardianMiddleName());
            student.setGuardianLastName(cmsStudentVo.getGuardianLastName());
            student.setGuardianAddress(cmsStudentVo.getGuardianAddress());
            student.setGuardianCellNumber(cmsStudentVo.getGuardianCellNumber());
            student.setGuardianLandLinePhoneNumber(cmsStudentVo.getGuardianLandLinePhoneNumber());
            student.setGuardianEmailId(cmsStudentVo.getGuardianEmailId());
            student.setGuardianOccupation(cmsStudentVo.getGuardianOccupation());
            student.setGuardianOfficeEmailId(cmsStudentVo.getGuardianOfficeEmailId());
            student.setGuardianOfficeAddress(cmsStudentVo.getGuardianOfficeAddress());
            student.setGuardianOfficeCellNumber(cmsStudentVo.getGuardianOfficeCellNumber());
            student.setGuardianOfficeLandLinePhoneNumber(cmsStudentVo.getGuardianOfficeLandLinePhoneNumber());
            student.setGuardianImagePath(cmsStudentVo.getGuardianImagePath());
            student.setIsGuardianSponsorAgency(cmsStudentVo.getIsGuardianSponsorAgency());
            student.setSponsorAgencyName(cmsStudentVo.getSponsorAgencyName());
            student.setSponsorAgencyRegistrationNo(cmsStudentVo.getSponsorAgencyRegistrationNo());
            student.setSponsorAgencyAddress(cmsStudentVo.getSponsorAgencyAddress());
            student.setSponsorAgencyCellNumber(cmsStudentVo.getSponsorAgencyCellNumber());
            student.setSponsorAgencyLandLineNumber(cmsStudentVo.getSponsorAgencyLandLineNumber());
            student.setSponsorAgencyEmailId(cmsStudentVo.getSponsorAgencyEmailId());
            student.setSponsorAgencyAppointeeName(cmsStudentVo.getSponsorAgencyAppointeeName());
            student.setSponsorAgencyAppointeeDesignation(cmsStudentVo.getSponsorAgencyAppointeeDesignation());
            student.setSponsorAgencyAppointeeCellNumber(cmsStudentVo.getSponsorAgencyAppointeeCellNumber());
            student.setSponsorAgencyAppointeeLandLineNumber(cmsStudentVo.getSponsorAgencyAppointeeLandLineNumber());
            student.setSponsorAgencyAppointeeOfficeAddress(cmsStudentVo.getSponsorAgencyAppointeeOfficeAddress());
            student.setSponsorAgencyAppointeeEmailId(cmsStudentVo.getSponsorAgencyAppointeeEmailId());
            student.setIsPhysicallyChallenged(cmsStudentVo.getIsPhysicallyChallenged());
            student.setDetailsOfDisability(cmsStudentVo.getDetailsOfDisability());
            student.setDisabilityCertificateNo(cmsStudentVo.getDisabilityCertificateNo());
            student.setDisabilityCertificateIssueAuthority(cmsStudentVo.getDisabilityCertificateIssueAuthority());
            student.setDisabilityCertificateNo(cmsStudentVo.getDisabilityCertificateNo());
            student.setPercentagOfDisability(cmsStudentVo.getPercentagOfDisability());
            student.setBloodGroup(cmsStudentVo.getBloodGroup());
            student.setVaccinationDetails(cmsStudentVo.getVaccinationDetails());
            student.setOtherMedicalDetails(cmsStudentVo.getOtherMedicalDetails());
            student.setStatus(cmsStudentVo.getStatus());
            student.setCreatedBy(cmsStudentVo.getCreatedBy());
            student.setUpdatedBy(cmsStudentVo.getUpdatedBy());
            student.setComments(cmsStudentVo.getComments());
            student.setBatchId(cmsStudentVo.getBatchId());
            student.setSectionId(cmsStudentVo.getSectionId());
            student.setDepartmentId(cmsStudentVo.getDepartmentId());
            student.setBranchId(cmsStudentVo.getBranchId());
            student.setAcademicYearId(cmsStudentVo.getAcademicYearId());

            student.setDateOfBirth(cmsStudentVo.getStrDateOfBirth() != null ? DateFormatUtil.convertStringToLocalDate(cmsStudentVo.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);

            String prefUrl = applicationProperties.getPrefSrvUrl();
            if(cmsStudentVo.getBranchId() != null) {
                String url = prefUrl+"/api/branch-by-id/"+cmsStudentVo.getBranchId();
                Branch branch = this.commonService.getObject(url, Branch.class);
                if(branch != null) {
                    student.setBranchName(branch.getBranchName());
                }
            }

            if(cmsStudentVo.getDepartmentId() != null) {
//            	Department department = this.commonService.getDepartmentById(cmsStudentVo.getDepartmentId());
                String url = prefUrl+"/api/department-by-id/"+cmsStudentVo.getDepartmentId();
                Department department = this.commonService.getObject(url, Department.class);
                if(department != null) {
                    student.setDepartmentName(department.getName());
                }
            }

            if(cmsStudentVo.getBatchId() != null) {
//            	Batch batch = this.commonService.getBatchById(cmsStudentVo.getBatchId());
                String url = prefUrl+"/api/batch-by-id/"+cmsStudentVo.getBatchId();
                Batch batch = this.commonService.getObject(url, Batch.class);
                if(batch != null) {
                    student.setBatchName(batch.getBatch().toString());
                }
            }

            if(cmsStudentVo.getSectionId() != null) {
//            	Section section = this.commonService.getSectionById(cmsStudentVo.getSectionId());
                String url = prefUrl+"/api/section-by-id/"+cmsStudentVo.getSectionId();
                Section section = this.commonService.getObject(url, Section.class);
                if(section != null) {
                    student.setSectionName(section.getSection().toString());
                }
            }

            if(cmsStudentVo.getAcademicYearId() != null) {
//            	AcademicYear ay = this.commonService.getAcademicYearById(cmsStudentVo.getAcademicYearId());
                String url = prefUrl+"/api/academic-years-by-id/"+cmsStudentVo.getAcademicYearId();
                AcademicYear ay = this.commonService.getObject(url, AcademicYear.class);

                if(ay != null) {
                    student.setAcademicYear(ay.getDescription());
                }
            }

            student = this.studentRepository.save(student);
            vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
            if(cmsStudentVo.getId() != null) {
                saveStudentFacilityMapping(cmsStudentVo, vo);
                logger.debug("Teacher and Subject mapping in Teach and AttendanceMaster is added successfully.");
            }
            vo.setStrDateOfBirth(student.getDateOfBirth() != null ? DateFormatUtil.changeLocalDateFormat(student.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrCreatedOn(student.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(student.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(student.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(student.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (cmsStudentVo.getId() == null) {
                vo.setExitDescription("student is added successfully");
                logger.debug("student is added successfully");
            } else {
                vo.setExitDescription("student is updated successfully");
                logger.debug("student is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsStudentVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, student data not be saved");
            logger.error("Student save failed. Exception : ", e);
        }
        logger.info("Student saved successfully");
        return vo;
    }

    public List<FeeDetails> getFeeDetailsList(CmsStudentVo vo){
        String url = this.applicationProperties.getFeeSrvUrl()+"/api/feedetails-by-filters";
        url = url+ "?departmentId="+vo.getDepartmentId()+"&batchId="+vo.getBatchId()+"&studentType="+vo.getStudentType()+"&gender="+vo.getSex()+"&branchId"+vo.getBranchId();
        FeeDetails[] list = this.commonService.getObject(url,FeeDetails[].class);

//    	FeeDetails feeDetails = new FeeDetails();
//    	feeDetails.setDepartmentId(vo.getDepartmentId());
//    	feeDetails.setBatchId(vo.getBatchId());
//    	feeDetails.setStudentType(vo.getStudentType());
//    	feeDetails.setGender(vo.getSex());
//    	List<FeeDetails> tempList = this.feeDetailsRepository.findAll(Example.of(feeDetails));
//    	List<FeeDetails> list = new ArrayList<>();
//    	for(FeeDetails fd: tempList) {
//    		if(fd.getFeeCategory().getBranchId().equals(vo.getBranchId())) {
//    			list.add(fd);
//    		}
//    	}
//    	Collections.sort(list, (o1, o2) -> o2.getFeeParticularsName().compareTo(o1.getFeeParticularsName()));
        return Arrays.asList(list);
    }

    public void saveStudentFacilityMapping(StudentInput input, CmsStudentVo vo) {

        StudentFacilityLink studentFacilityLink = this.studentFacilityLinkService.saveStudentFacility(input);


    }


    public Float getTotalFees(List<FeeDetails> feeDetailsList, List<StudentFacilityLink> facilityList) {
        Float total = 0F;
        for(FeeDetails fd: feeDetailsList) {
            total = total + fd.getAmount();
        }
        String prefUrl = applicationProperties.getPrefSrvUrl();
        for(StudentFacilityLink sfl: facilityList) {
            String url = prefUrl+"/api/facility-by-id/"+sfl.getFacilityId();
            Facility facility = this.commonService.getObject(url, Facility.class);
            total = total + facility.getAmount();
        }
        return total;
    }

    public Long getTotalFeePaid(CmsStudentVo vo) {
//    	Invoice invoice = new Invoice();
//    	Student student = CommonUtil.createCopyProperties(vo, Student.class);
//    	invoice.setStudent(student);
//    	invoice.setPaymentStatus(InvoicePaymentStatus.PAID);
//    	invoice.setBranchId(vo.getBranchId());
//    	invoice.setAcademicYearId(vo.getAcademicYearId());
//    	List<Invoice> tempList = this.invoiceRepository.findAll(Example.of(invoice));
        List<Invoice> tempList = getInvoiceList(vo, "PAID");
        Long total = 0L;
        for(Invoice inv: tempList) {
            total = total+ inv.getAmountPaid();
        }
        return total;
    }

    public Long getTotalFeeOverDue(CmsStudentVo vo) {
//    	Invoice invoice = new Invoice();
//    	Student student = CommonUtil.createCopyProperties(vo, Student.class);
//    	invoice.setStudent(student);
//    	invoice.setPaymentStatus(InvoicePaymentStatus.UNPAID);
//    	invoice.setBranchId(vo.getBranchId());
//    	invoice.setAcademicYearId(vo.getAcademicYearId());
//    	List<Invoice> tempList = this.invoiceRepository.findAll(Example.of(invoice));
        List<Invoice> tempList = getInvoiceList(vo, "UNPAID");
        Long total = 0L;
        for(Invoice inv: tempList) {
            total = total+ inv.getOutStandingAmount();
        }
        return total;
    }

    public List<StudentFacilityLink> getFacilityList(CmsStudentVo vo){
        StudentFacilityLink studentFacilityLink = new StudentFacilityLink();
        Student student = CommonUtil.createCopyProperties(vo, Student.class);
        studentFacilityLink.setStudent(student);
        List<StudentFacilityLink> list = this.studentFacilityLinkRepository.findAll(Example.of(studentFacilityLink));
//    Collections.sort(list, (o1, o2) -> o1.getFacility().getName().compareTo(o2.getFacility().getName()));
        return list;
    }

    public List<CmsInvoice> getPaymentHistory(CmsStudentVo vo){
        String feeUrl = applicationProperties.getFeeSrvUrl() + "/api/cmsinvoice-by-filters?studentId="+vo.getId()+"&branchId="+vo.getBranchId();
        CmsInvoice[] list = this.commonService.getObject(feeUrl,CmsInvoice[].class);

//    	Invoice invoice = new Invoice();
//    	Student student = CommonUtil.createCopyProperties(vo, Student.class);
//    	invoice.setStudent(student);
//    	invoice.setBranchId(vo.getBranchId());
//    	List<Invoice> tempList = this.invoiceRepository.findAll(Example.of(invoice), Sort.by(Direction.DESC, "id"));
//    	List<CmsInvoice> list = new ArrayList<>();
//    	for(Invoice inv: tempList) {
//    		CmsInvoice cinv = CommonUtil.createCopyProperties(inv, CmsInvoice.class);
//    		if(inv.getPaymentDate() != null) {
//    			cinv.setStrPaymentDate(DateFormatUtil.changeLocalDateFormat(inv.getPaymentDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    			cinv.setPaymentDate(null);
//    		}
//    		if(inv.getNextPaymentDate() != null) {
//    			cinv.setStrNextPaymentDate(DateFormatUtil.changeLocalDateFormat(inv.getNextPaymentDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    			cinv.setNextPaymentDate(null);
//    		}
//    		if(inv.getUpdatedOn() != null) {
//    			cinv.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(inv.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    			cinv.setUpdatedOn(null);
//    		}
//    		list.add(cinv);
//    	}
//    	Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return Arrays.asList(list);
    }

    private List<Invoice> getInvoiceList(CmsStudentVo vo, String paymentStatus){
        String feeUrl = applicationProperties.getFeeSrvUrl() + "/api/invoice-by-filters?studentId="+vo.getId()+"&branchId="+vo.getBranchId()+"&academicYearId="+vo.getAcademicYearId()+"&paymentStatus="+paymentStatus;
        Invoice[] list = this.commonService.getObject(feeUrl,Invoice[].class);
        return Arrays.asList(list);
    }

    public List<Student> getStudentListOnFilterCriteria(Map<String, String> criteriaMap){
        Student st = new Student();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            st.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if(criteriaMap.get("branchId") != null) {
            st.setBranchId(Long.parseLong(criteriaMap.get("branchId")));
            isFilter = true;
        }

        if(criteriaMap.get("departmentId") != null) {
            st.setDepartmentId(Long.parseLong(criteriaMap.get("departmentId")));
            isFilter = true;
        }

        if(criteriaMap.get("batchId") != null) {
            st.setBatchId(Long.parseLong(criteriaMap.get("batchId")));
            isFilter = true;
        }

        if(criteriaMap.get("sectionId") != null) {
            st.setSectionId(Long.parseLong(criteriaMap.get("sectionId")));
            isFilter = true;
        }

        List<Student> list = null;
        if(isFilter) {
            list = this.studentRepository.findAll(Example.of(st), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.studentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }


}

