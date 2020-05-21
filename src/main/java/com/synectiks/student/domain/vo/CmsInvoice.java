package com.synectiks.student.domain.vo;

import java.time.LocalDate;

import com.synectiks.student.domain.AcademicYear;
import com.synectiks.student.domain.DueDate;
import com.synectiks.student.domain.FeeCategory;
import com.synectiks.student.domain.FeeDetails;
import com.synectiks.student.domain.PaymentRemainder;
import com.synectiks.student.domain.Student;

public class CmsInvoice {

	private Long totalInvoice = 0L;
	private Long totalPaidInvoice = 0L;
	private Long totalUnPaidInvoice = 0L;
	private Long totalCanceledInvoice = 0L;

	private Long id;
    private String invoiceNumber;
    private Long amountPaid;
    private LocalDate paymentDate;
    private LocalDate nextPaymentDate;
    private Long outStandingAmount;
    private String modeOfPayment;
    private Long chequeNumber;
    private Long demandDraftNumber;
    private String onlineTxnRefNumber;
    private String paymentStatus;
    private String comments;
    private String updatedBy;
    private LocalDate updatedOn;
    private FeeCategory feeCategory;
    private FeeDetails feeDetails;
    private DueDate dueDate;
    private PaymentRemainder paymentRemainder;
    private Long collegeId;
    private Long branchId;
    private Long academicYearId;
    private Student student;
    private AcademicYear academicYear;
    private String strPaymentDate;
    private String strNextPaymentDate;
    private String strUpdatedOn;
    private String bank;
    
	public Long getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(Long totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	public Long getTotalPaidInvoice() {
		return totalPaidInvoice;
	}
	public void setTotalPaidInvoice(Long totalPaidInvoice) {
		this.totalPaidInvoice = totalPaidInvoice;
	}
	public Long getTotalUnPaidInvoice() {
		return totalUnPaidInvoice;
	}
	public void setTotalUnPaidInvoice(Long totalUnPaidInvoice) {
		this.totalUnPaidInvoice = totalUnPaidInvoice;
	}
	public Long getTotalCanceledInvoice() {
		return totalCanceledInvoice;
	}
	public void setTotalCanceledInvoice(Long totalCanceledInvoice) {
		this.totalCanceledInvoice = totalCanceledInvoice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Long getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Long amountPaid) {
		this.amountPaid = amountPaid;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public LocalDate getNextPaymentDate() {
		return nextPaymentDate;
	}
	public void setNextPaymentDate(LocalDate nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	public Long getOutStandingAmount() {
		return outStandingAmount;
	}
	public void setOutStandingAmount(Long outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public Long getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(Long chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
	public Long getDemandDraftNumber() {
		return demandDraftNumber;
	}
	public void setDemandDraftNumber(Long demandDraftNumber) {
		this.demandDraftNumber = demandDraftNumber;
	}
	public String getOnlineTxnRefNumber() {
		return onlineTxnRefNumber;
	}
	public void setOnlineTxnRefNumber(String onlineTxnRefNumber) {
		this.onlineTxnRefNumber = onlineTxnRefNumber;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}
	public FeeCategory getFeeCategory() {
		return feeCategory;
	}
	public void setFeeCategory(FeeCategory feeCategory) {
		this.feeCategory = feeCategory;
	}
	public FeeDetails getFeeDetails() {
		return feeDetails;
	}
	public void setFeeDetails(FeeDetails feeDetails) {
		this.feeDetails = feeDetails;
	}
	public DueDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(DueDate dueDate) {
		this.dueDate = dueDate;
	}
	public PaymentRemainder getPaymentRemainder() {
		return paymentRemainder;
	}
	public void setPaymentRemainder(PaymentRemainder paymentRemainder) {
		this.paymentRemainder = paymentRemainder;
	}

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public String getStrPaymentDate() {
		return strPaymentDate;
	}
	public void setStrPaymentDate(String strPaymentDate) {
		this.strPaymentDate = strPaymentDate;
	}
	public String getStrNextPaymentDate() {
		return strNextPaymentDate;
	}
	public void setStrNextPaymentDate(String strNextPaymentDate) {
		this.strNextPaymentDate = strNextPaymentDate;
	}
	public String getStrUpdatedOn() {
		return strUpdatedOn;
	}
	public void setStrUpdatedOn(String strUpdatedOn) {
		this.strUpdatedOn = strUpdatedOn;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}

}
