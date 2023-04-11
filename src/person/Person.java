package person;

import member.Member;

import java.util.*;

public class Person extends Member {
    private Map<Person, Boolean> married;

    public Person(String firstName, String lastName, boolean gender, Calendar bornDate) {
        this(firstName, lastName, gender, bornDate, new HashMap<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Person(Person person) {
        this(person.name, person.gender, person.bornDate, person.married, person.children, person.parents);
        this.updateLinks();
    }

    private Person(String firstName, boolean gender, Calendar bornDate, Map<Person, Boolean> married, List<? extends Member> children, List<? extends Member> parents) {
        super(firstName, gender, bornDate, children, parents);
        this.setLastName(lastName);
        this.married = married;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getName() {
        return super.getName() + " " + this.getLastName();
    }

    public Calendar getBornDate() {
        return this.bornDate;
    }

    public Boolean getGender() {
        return this.gender;
    }

    /*public List<Person> getChildren() {
        return this.children;
    }

    public List<Person> getParents() {
        return this.parents;
    }*/

    public Person getMarried() {
        if (this.married.containsValue(true)) {
            for (Map.Entry<Person, Boolean> entry : this.married.entrySet())
                if (entry.getValue()) return (Person) entry.getKey();
        }
        return null;
    }

    public void addMarried(Person married, boolean isMarried) {
        if (!this.hasMarried(married)) {
            this.married.put(married, isMarried);
            married.addMarried(this, isMarried);
        }
    }

    public int countChildren() {
        return this.children.size();
    }

    private int countParents() {
        return this.parents.size();
    }

    public boolean hasMarried(Person married) {
        return this.married.containsKey(married);
    }

//    public boolean hasChild(Person child) {
//        return this.children.contains(child);
//    }
//
//    private boolean hasParent(Person parent) {
//        return this.parents.contains(parent);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, gender, bornDate);
    }

    public String toStringBornDate() {
        return this.toString() + " " + this.getBornDate();
    }

//    public int compareTo(Person m) {
//        int result = this.getFirstName().compareTo(m.getFirstName());
//        if (result == 0) result = this.getLastName().compareTo(m.getLastName());
//        if (result == 0) {
//            /*if (this.bornDate.after(p.bornDate)) {
//                result = 1;
//            } else if (this.bornDate.before(p.bornDate)) {
//                result = -1;
//            }*/
//            result = this.getBornDate().compareTo(m.getBornDate());
//        }
//        return result;
//    }
}