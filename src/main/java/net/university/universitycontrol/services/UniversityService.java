package net.university.universitycontrol.services;

import java.util.List;

public interface UniversityService {
    String getHeadOfDepartment(String departmentName);
    String getDepartmentStatistics(String departmentName);
    String getDepartmentAverageSalary(String departmentName);
    String getDepartmentEmployeeCount(String departmentName);
    String search(String title);
}
