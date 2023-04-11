package familyTree;

import member.MemberInterface;

import java.util.List;

public interface FTree<E extends MemberInterface> extends Iterable<E> {
    void addMember(E member);

    List<E> getMembersList();
}