package familyTree;

import member.Member;
import member.MemberInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<E extends Member> implements Serializable, FTree<E> {
    private String name;
    private List<E> members;

    public FamilyTree(String name) {
        this.setName(name);
        this.members = new ArrayList<>();
    }

//    public FamilyTree(E member) {
//        if (member.getClass() == Person.class) this(((Person) member).getLastName());
//        this.addMember(member, true);
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<E> getMembersList() {
        return this.members;
    }

    private String getSpace(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 15 - str.length(); i++) {
            result.append(" ");
        }
        return result.toString();
    }

    private String getSpaces(int iter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < iter; i++) {
            result.append(String.format("%s|", getSpace("")));
        }
        return result.toString();
    }

    public void addMember(E member) {
        addMember(member, false);
    }

    public void addMember(E member, boolean isAddAllChildren) {
        if (!this.hasMember(member)) {
            this.members.add(member);
            if (isAddAllChildren) {
                this.addChildren(member);
            }
        }
    }

    private void addChildren(E member) {
        if (this.hasMember(member)) {
            if (member.countChildren() != 0) {
                for (MemberInterface mem : member.getChildren()) {
                    this.addMember((E) mem, true);
                }
            }
        } else {
            this.addMember(member, true);
        }
    }

    private boolean hasMember(E member) {
        boolean result = false;
        if (this.members.indexOf(member) != -1) result = true;
        return result;
    }

    public E findMember(String firstName){
        for (E member : this.members){
            if (member.getName().equals(firstName)){
                return member;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new MemberIterator(members);
    }

    public void printInfo() {
        for (E member : this.members) {
            System.out.println(member);
        }
    }

    public String toString(String firstName) {
        return toString(this.findMember(firstName));
    }

    public String toString(E member) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Members of family %s", this.name));
        return toString(result, member, 1);
    }

    private String toString(StringBuilder result, E member, int iter) {
        result.append(String.format("%s%s|", member.toString(), getSpace(member.toString())));
        if (member.countChildren() > 0) {
            for (int i = 0; i < member.countChildren(); i++) {
                if (i > 0) result.append(String.format("%s", getSpaces(iter)));
                toString(result, (E) member.getChildren().get(i), iter + 1);
            }
        } else {
            result.append("\n");
        }
        return result.toString();
    }
}