package net.university.universitycontrol.dao;

import java.util.List;

public interface UniversityDao {
    String getHeadOfDepartment(String departmentName);
    List<String> getDepartmentStatistics(String departmentName);
    Double getDepartmentAverageSalary(String departmentName);
    Integer getDepartmentEmployeeCount(String departmentName);
    List<String> search(String title);
}
