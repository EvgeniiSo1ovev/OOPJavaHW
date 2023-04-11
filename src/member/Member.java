package member;

import java.util.*;

public abstract class Member implements MemberInterface {
    protected String name;
    protected boolean gender;
    protected Calendar bornDate;
    protected List<? extends Member> children;
    protected List<? extends Member> parents;

    public Member(String name, boolean gender, Calendar bornDate) {
        this(name, gender, bornDate, new ArrayList<>(), new ArrayList<>());
    }

    protected Member(String name, boolean gender, Calendar bornDate, List<? extends Member> children, List<? extends Member> parents) {
        this.setName(name);
        this.gender = gender;
        this.bornDate = bornDate;
        this.children = children;
        this.parents = parents;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Calendar getBornDate() {
        return this.bornDate;
    }

    public Boolean getGender() {
        return this.gender;
    }

    public List<? extends Member> getChildren() {
        return this.children;
    }

    public List<? extends Member> getParents() {
        return this.parents;
    }

    public void addChild(Member child) {
        if (!this.hasChild(child)) {
            getChildren().add((? super Member)child);
            child.addParent(this);
        }
    }

    public void addParent(Member parent) {
        if (!this.hasParent(parent)) {
            this.parents.add(parent);
            parent.addChild(this);
        }
    }

    public void updateLinks() {
        this.updateChildren();
        this.updateParents();
    }

    private void updateChildren() {
        if (this.countChildren() > 0) {
            for (Member member : this.children) {
                member.addParent(this);
            }
        }
    }

    private void updateParents() {
        if (this.countParents() > 0) {
            for (Member member : this.parents) {
                member.addChild(this);
            }
        }
    }

    public int countChildren() {
        return this.children.size();
    }

    private int countParents() {
        return this.parents.size();
    }

    public boolean hasChild(Member child) {
        return this.children.contains(child);
    }

    private boolean hasParent(Member parent) {
        return this.parents.contains(parent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return gender == member.gender && Objects.equals(name, member.name) && Objects.equals(bornDate, member.bornDate) && Objects.equals(parents, member.parents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, bornDate, parents);
    }

    public String toString() {
        return getName();
    }

//    public String toStringBornDate() {
//        return this.toString() + " " + this.getBornDate();
//    }

    public int compareTo(Member m) {
        int result = this.getName().compareTo(m.getName());
        if (result == 0) {
            result = this.getBornDate().compareTo(m.getBornDate());
        }
        return result;
    }
}