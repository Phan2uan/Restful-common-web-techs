package Restful.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentSearchRequest {

    private String name;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String gender;
    private String hometown;
    private String className;
    private String major;
    private Double minMark;
    private Double maxMark;

    private int page = 0;
    private int size = 10;
}