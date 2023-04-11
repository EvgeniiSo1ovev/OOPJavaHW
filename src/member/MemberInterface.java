package member;

import java.io.Serializable;
import java.util.Calendar;

public interface MemberInterface extends Serializable, Comparable<Member> {

    int countChildren();

    String getName();

    Boolean getGender();

    Calendar getBornDate();

    /*List<? extends MemberInterface> getChildren();

    List<? extends MemberInterface> getParents();*/

    void setFirstName(String name);
}
