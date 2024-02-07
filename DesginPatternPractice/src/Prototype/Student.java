package Prototype;

public class Student implements Prototype<Student>{
    private String name;
    private Long id;
    private String batchName;
    private double psp;
    private double averageBatchPsp;

    public Student(Student s){
        this.name = s.name;
        this.id = s.id;
        this.batchName = s.batchName;
        this.psp = s.psp;
        this.averageBatchPsp = s.averageBatchPsp;
    }

    public Student(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public double getPsp() {
        return psp;
    }

    public void setPsp(double psp) {
        this.psp = psp;
    }

    public double getAverageBatchPsp() {
        return averageBatchPsp;
    }

    public void setAverageBatchPsp(double averageBatchPsp) {
        this.averageBatchPsp = averageBatchPsp;
    }

    @Override
    public Student clone() {
        return new Student(this);
    }
}
