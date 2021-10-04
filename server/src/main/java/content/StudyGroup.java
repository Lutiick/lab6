package content;

import tools.CoordinatesAdapter;
import tools.ZonedDateTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, объекты которого будут храниться в коллекции
 */
@XmlType(name = "studyGroup")
public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private static Integer static_id = 0;

    public StudyGroup(String name, Coordinates coordinates, int studentsCount, int expelledStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = getNewId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now(); //LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss"))
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup() {
        this.id = getNewId();
        this.creationDate = ZonedDateTime.now();
    }
    @XmlAttribute(name = "id")
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlAttribute(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlJavaTypeAdapter(CoordinatesAdapter.class)
    @XmlAttribute
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    @XmlAttribute
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlAttribute(name = "studentsCount")
    private int studentsCount; //Значение поля должно быть больше 0
    @XmlAttribute(name = "expelledStudents")
    private int expelledStudents; //Значение поля должно быть больше 0
    @XmlAttribute(name = "formOfEducation")
    private FormOfEducation formOfEducation; //Поле не может быть null
    @XmlAttribute(name = "semesterEnum")
    private Semester semesterEnum; //Поле не может быть null
    @XmlElement(name = "groupAdmin")
    private Person groupAdmin; //Поле не может быть null

    public static Integer getNewId() {
        static_id += 1;
        return static_id;
    }

    @XmlTransient
    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return this.studentsCount - studyGroup.getStudentsCount();
    }

    public String NiceToString() {
        return "\t\t\tГРУППА " + id + "\n" +
                "Номер группы: " + id +
                ", название группы: " + name  +
                ", координаты группы: (" + coordinates.getX() + ", " + coordinates.getY() + ")" +
                ",\nвремя добавления группы в коллекцию: " + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")) +
                ",\nкол.во студентов: " + studentsCount + ", кол.во исключенных студентов: " + expelledStudents +
                ",\nформа обучения: " + formOfEducation + ", семестр: " + semesterEnum +
                ",\nДанные админа { " +
                "Имя - " + groupAdmin.getName() + ", рост - " + groupAdmin.getHeight() + ", цвет глаз - " + groupAdmin.getEyeColor() +
                ",\nцвет волос - " + groupAdmin.getHairColor() + ", национальность - " + groupAdmin.getNationality() +
                ",\nлокация - " + groupAdmin.getLocation().getName() + ", X локации - " + groupAdmin.getLocation().getX() + ", Y локации - " + groupAdmin.getLocation().getY() +
                " }";
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", expelledStudents=" + expelledStudents +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    /**
     * Проверка, правильно ли был заполнен объект из файла
     * @return true/false
     */
    public boolean isEmpty(){
        return name == null || name.equals("") || coordinates == null || coordinates.getX() > 610 || coordinates.getX() == null || coordinates.getY() < -237 || studentsCount <= 0 || expelledStudents <= 0 || formOfEducation == null || semesterEnum == null || groupAdmin == null || groupAdmin.getName() == null || groupAdmin.getName().equals("") || groupAdmin.getHeight() <= 0 || groupAdmin.getEyeColor() == null || groupAdmin.getNationality() == null || groupAdmin.getLocation() == null;
    }


}