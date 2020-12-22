package net.university.universitycontrol.services.impl;

import net.university.universitycontrol.dao.UniversityDao;
import net.university.universitycontrol.services.UniversityService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private UniversityDao universityDao;

    private static final String NOT_FOUND = "Not found";

    @Autowired
    public UniversityServiceImpl(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {

        return universityDao.getHeadOfDepartment(departmentName);
    }

    @Override
    public String getDepartmentStatistics(String departmentName) {
        List<String> result = universityDao.getDepartmentStatistics(departmentName);

        if(result.size() > 0){
            return StringUtils.join(result,'\n');
        }else
        {
            return NOT_FOUND;
        }

    }

    @Override
    public String getDepartmentAverageSalary(String departmentName) {
        Double result = universityDao.getDepartmentAverageSalary(departmentName);

        if(result != null){
            return String.valueOf(result);
        }else {
            return NOT_FOUND;
        }
    }

    @Override
    public String getDepartmentEmployeeCount(String departmentName) {
        Integer result = universityDao.getDepartmentEmployeeCount(departmentName);

        if(result != null){
            return String.valueOf(result);
        }else{
            return NOT_FOUND;
        }

    }

    @Override
    public String search(String title) {
        List<String> result = universityDao.search("%" + title + "%");

        if(result.size() > 0){
            return StringUtils.join(result,'\n');
        }else{
            return NOT_FOUND;
        }
    }
}
