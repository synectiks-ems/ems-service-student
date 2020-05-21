
package com.synectiks.student.graphql.resolvers;

import java.util.List;

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
    	List<CmsInvoice> list = this.commonService.getList(feeUrl);
        return list;
    }

   
    // Needed
        public StudentFilterDataCache createStudentFilterDataCache() throws Exception{

//    	List<Branch> branchList = this.commonService.getBranchForCriteria(Long.valueOf(collegeId));
//    	List<Department> departmentList = this.commonService.getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
    	String preUrl = this.applicationProperties.getPrefSrvUrl();
        List<Batch> batchList = this.commonService.getList(preUrl+"/api/batch-by-filters");
    	List<Section> sectionList = this.commonService.getList(preUrl+"/api/section-by-filters");
//    	List<CmsStudentTypeVo> studentTypeList = this.commonService.getAllStudentTypes();
//    	List<CmsGenderVo> genderList = this.commonService.getAllGenders();

    	StudentFilterDataCache cache = new StudentFilterDataCache();
//    	cache.setBranches(branchList);
//    	cache.setDepartments(departmentList);
    	cache.setBatches(batchList);
    	cache.setSections(sectionList);
//    	cache.setStudentTypes(studentTypeList);
//    	cache.setGenders(genderList);
    	return cache;
    }


}
