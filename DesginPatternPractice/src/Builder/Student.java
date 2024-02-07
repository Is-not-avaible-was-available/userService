package Builder;

import java.time.Duration;
import java.util.Date;

public class Student {
    private String name;
    private Long id;
    private String batchName;
    private double psp;
    private int age;
    private String email;
    private Date birthDate;

    private Student(Builder builder){
        if(builder.age>100){
            throw new RuntimeException();
        }
        if (builder.psp>100 || builder.psp<0){
            throw new RuntimeException();
        }
        this.name = builder.getName();
        this.age = builder.getAge();
        this.birthDate = builder.getBirthDate();
        this.psp = builder.getPsp();
        this.email= builder.getEmail();
        this.id = builder.getId();
        this.batchName =  builder.getBatchName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getBatchName() {
        return batchName;
    }

    public double getPsp() {
        return psp;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Long id;
        private String batchName;
        private double psp;
        private int age;
        private String email;
        private Date birthDate;


        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Long getId() {
            return id;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public String getBatchName() {
            return batchName;
        }

        public Builder setBatchName(String batchName) {
            this.batchName = batchName;
            return this;
        }

        public double getPsp() {
            return psp;
        }

        public Builder setPsp(double psp) {
            this.psp = psp;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }

}
