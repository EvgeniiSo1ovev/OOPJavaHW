package familyTree.comparators;

import member.MemberInterface;

import java.util.Comparator;

public class MemberComparatorByFirstName<E extends MemberInterface> implements Comparator<E> {
    @Override
    public int compare(E p1, E p2) {
        int result = p1.getName().compareTo(p2.getName());
        if (result == 0) {
            result = p1.getBornDate().compareTo(p2.getBornDate());
        }
        return result;
    }
}