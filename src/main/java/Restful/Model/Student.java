package Restful.Model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Student {
    private int id;
    private String fullName;
    private LocalDate birthday;
    private String className;
    private String major;
    private String hometown;
    private String gender;
    private double averageMark;
}