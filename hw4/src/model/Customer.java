package model;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthDate;
    private String phone;
    private Gender gender;

    public Customer(String lastName, String firstName, String patronymic, 
                    LocalDate birthDate, String phone, Gender gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
    }

    private int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getName() {
        return firstName + " " + patronymic;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Покупатель [ФИО='" + getFullName() + "', возраст=" + getAge() + 
                ", телефон='" + phone + "', " + gender + "]";
    }

    
}
