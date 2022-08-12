package com.luko.javaspringapi.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@Builder
public class PatientDto {
    private UUID id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    private String ssn;
    private String phoneNumber;
    private Integer age;

    public PatientDto() {
    }

    public PatientDto(UUID id, String firstName, String lastName, LocalDate dob, String ssn, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        if (lastName != null){
            return  lastName.replaceAll("." ,"*");
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSsn() {
        if (ssn != null){
            return  ssn.replaceAll("\\d", "*");
        }
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        if(this.getDob()!= null)
            return Period.between(this.getDob(), LocalDate.now()).getYears();
        return null;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
