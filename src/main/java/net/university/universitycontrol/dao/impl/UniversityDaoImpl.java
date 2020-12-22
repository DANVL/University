package net.university.universitycontrol.dao.impl;

import net.university.universitycontrol.dao.UniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UniversityDaoImpl implements UniversityDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniversityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {
        String headOfDepartmentSQL =
                "SELECT full_name FROM lectors WHERE department_id = " +
                        "(SELECT department_id FROM departments WHERE name = ?) AND " +
                        "head_of_department = 1";

        try {
            return jdbcTemplate.queryForObject(headOfDepartmentSQL,
                    String.class,
                    departmentName);
        } catch (Exception e) {
            return "Not found";
        }

    }

    @Override
    public List<String> getDepartmentStatistics(String departmentName) {
        String statisticsSQL =
                "SELECT d.title, COUNT(lector_id) as count " +
                        "FROM lectors INNER JOIN degrees d on lectors.degree_id = d.degree_id " +
                        "WHERE department_id = " +
                        "(SELECT department_id FROM departments WHERE name = ?) " +
                        "GROUP BY d.title ";

        return jdbcTemplate.query(statisticsSQL,
                (rs, rowNum) -> rs.getString(1) + " - " + rs.getInt(2),
                departmentName);

    }

    @Override
    public Double getDepartmentAverageSalary(String departmentName) {

        String averageSalarySQL =
                "SELECT AVG(salary) as average " +
                        "FROM lectors " +
                        "WHERE department_id = " +
                        "      (SELECT department_id FROM departments WHERE name = ?)";


        return jdbcTemplate.queryForObject(averageSalarySQL,
                Double.class,
                departmentName);


    }

    @Override
    public Integer getDepartmentEmployeeCount(String departmentName) {

        String employeeCountSQL = "SELECT COUNT(lector_id) as count " +
                "FROM lectors " +
                "WHERE department_id = " +
                "      (SELECT department_id FROM departments WHERE name = ?)";

        return jdbcTemplate.queryForObject(employeeCountSQL,
                Integer.class,
                departmentName);
    }

    @Override
    public List<String> search(String title) {

        String searchSQL =
                "SELECT full_name as name FROM lectors WHERE full_name LIKE ?" +
                        "UNION " +
                        "SELECT name FROM departments WHERE name LIKE ?";


        return jdbcTemplate.query(searchSQL, (rs, rowNum) -> rs.getString(1), title, title);

    }
}
