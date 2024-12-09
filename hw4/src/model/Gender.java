package model;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский");

    private String title;

    Gender(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "пол='" + title + "'";
    }
}
