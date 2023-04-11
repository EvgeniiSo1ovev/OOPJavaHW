package familyTree;

import member.MemberInterface;

import java.util.Iterator;
import java.util.List;

public class MemberIterator<E extends MemberInterface> implements Iterator<E> {
    private int index;
    private List<E> members;

    public MemberIterator(List<E> members) {
        this.members = members;
    }

    @Override
    public boolean hasNext() {
        return index < this.members.size();
    }

    @Override
    public E next() {
        return this.members.get(index++);
    }
}