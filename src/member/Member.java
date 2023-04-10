package member;

import person.Person;

import java.io.Serializable;

public interface Member extends Serializable, Comparable<Member> {

    default int compareTo(Member member) {
        return 0;
    }

    String getLastName();

    int countChildren();

    ThreadLocal<Object> getChildren();

    Object getFirstName();
}
