import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class User {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final LocalDate dateBirth;
    private final int age;
    private final Gender gender;

    public User(String name, String surname, String patronymic, LocalDate dateBirth, int age, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateBirth = dateBirth;
        this.age = age;
        this.gender = gender;
    }

    public User(String FIO, String dateBirth) {
        checkFIO(FIO);
        String[] FIO_splitted = FIO.trim().split("\\s+");
        this.dateBirth = getDate(dateBirth);
        age = getAge();
        surname = FIO_splitted[0];
        name = FIO_splitted[1];
        patronymic = FIO_splitted[2];
        gender = getGender();
    }

    private LocalDate getDate(String stringDate){
        LocalDate date = null;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try{
            date = LocalDate.parse(stringDate, dateFormat);
        }catch (DateTimeParseException e){
            System.out.println("invalid date format");
            Runtime.getRuntime().exit(1);
        }
        LocalDate nowDate = LocalDate.now();
        if(!(date.isAfter(nowDate.minusYears(150)) && date.isBefore(nowDate))){
            System.out.println("invalid date range");
            Runtime.getRuntime().exit(1);
        }
        return date;
    }

    private int getAge(){
        int age = 0;
        if(dateBirth!=null){
            age = Period.between(dateBirth, LocalDate.now()).getYears();
        }
        return age;
    }

    private Gender getGender() {
        Gender gender = Gender.UNKNOWN;
        if(patronymic.endsWith("вич")){
            gender = Gender.MALE;
        }
        if(patronymic.endsWith("вна")){
            gender = Gender.FEMALE;
        }
        return gender;
    }

    private void checkFIO(String FIO){
        if (!FIO.replaceAll("\\s+", "").matches("[А-Яа-яЁё]+")){
            System.out.println("invalid characters in FIO");
            Runtime.getRuntime().exit(1);
        }
        if (FIO.trim().split("\\s+").length != 3){
            System.out.println("incorrect input format in FIO");
            Runtime.getRuntime().exit(1);
        }
    }

    private String getAgeEnd(){
        String ageEnd = "лет";
        if(age%10 == 1){
            ageEnd = "год";
        }
        else if (age%10 >=2 && age%10 <= 4){
            ageEnd = "года";
        }
        return ageEnd;
    }

    public enum Gender {
        MALE("Мужской"),
        FEMALE("Женский"),
        UNKNOWN("Неизвестно");
        private final String name;
        Gender(String name){
            this.name = name;
        }
    }

    @Override
    public String toString(){
        return "ФИО: " + surname + " " + name.charAt(0) + ". " + patronymic.charAt(0) + ".\n" +
                "Пол: " + gender.name + "\n" +
                "Возраст: " + age + " " + getAgeEnd();
    }
}