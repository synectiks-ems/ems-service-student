
package com.synectiks.student.graphql.resolvers;

import java.util.Arrays;
import java.util.List;

import com.synectiks.student.domain.Facility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.synectiks.student.business.service.CommonService;
import com.synectiks.student.config.ApplicationProperties;
import com.synectiks.student.domain.Batch;
import com.synectiks.student.domain.Section;
import com.synectiks.student.domain.vo.CmsInvoice;
import com.synectiks.student.domain.vo.StudentFilterDataCache;

/**
 * Query Resolver for Student Queries
 *
 */

@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);

//	@Autowired
//	private StudentRepository studentRepository;

	@Autowired
    private CommonService commonService;

	@Autowired
    private ApplicationProperties applicationProperties;

    //Needed
    public List<CmsInvoice>  getInvoices(Long studentId, Long branchId){
    	String feeUrl = applicationProperties.getFeeSrvUrl() + "/api/cmsinvoice-by-filters?studentId="+studentId+"&branchId="+branchId;
    	CmsInvoice[] list = this.commonService.getObject(feeUrl,CmsInvoice[].class);
        return Arrays.asList(list);
    }


    // Needed
        public StudentFilterDataCache createStudentFilterDataCache() throws Exception{

//    	List<Branch> branchList = this.commonService.getBranchForCriteria(Long.valueOf(collegeId));
//    	List<Department> departmentList = this.commonService.getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
    	String preUrl = this.applicationProperties.getPrefSrvUrl();
    	String url = preUrl+"/api/batch-by-filters/";
    	Batch[] batchList = this.commonService.getObject(url,Batch[].class);
    	url = preUrl+"/api/section-by-filters/";
    	Section[] sectionList = this.commonService.getObject(url,Section[].class);
//    	List<CmsStudentTypeVo> studentTypeList = this.commonService.getAllStudentTypes();
//    	List<CmsGenderVo> genderList = this.commonService.getAllGenders();

    	StudentFilterDataCache cache = new StudentFilterDataCache();
//    	cache.setBranches(branchList);
//    	cache.setDepartments(departmentList);
    	cache.setBatches(Arrays.asList(batchList));
    	cache.setSections(Arrays.asList(sectionList));
//    	cache.setStudentTypes(studentTypeList);
//    	cache.setGenders(genderList);
    	return cache;
    }

    public List<Facility> getFacilities(){
        String url = this.applicationProperties.getPrefSrvUrl()+"/api/facility-by-filters";
        Facility[] list = this.commonService.getObject(url,Facility[].class);
        return Arrays.asList(list);
    }

}
