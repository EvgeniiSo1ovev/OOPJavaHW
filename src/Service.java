import familyTree.FamilyTree;
import familyTree.comparators.MemberComparatorByBornDate;
import familyTree.comparators.MemberComparatorByFirstName;
import familyTree.comparators.MemberComparatorByGender;
import handler.FileHandler;
import member.Member;
import person.Person;
import trees.TOTrees;
import trees.TreeOfTrees;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Service {
    private TOTrees<FamilyTree> treeOfTrees;

    public Service(TOTrees<FamilyTree> treeOfTrees) {
        this.treeOfTrees = treeOfTrees;
    }

    public void addFamilyTree(String newName) {
        this.treeOfTrees.addFamilyTree(new FamilyTree<>(newName));
    }

    public void addFamilyTree(Member member) {
        this.treeOfTrees.addFamilyTree(new FamilyTree<>(member));
    }

    public void addFamilyTree(FamilyTree<Member> familyTree) {
        this.treeOfTrees.addFamilyTree(familyTree);
    }

    public void addMember(Member member) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addMember(familyTree, member);
        }
    }

    public void addMember(String familyTreeName, Member member) {
        addMember(this.findFamilyTree(familyTreeName), member);
    }

    private void addMember(FamilyTree<Member> familyTree, Member member) {
        familyTree.addMember(member);
    }

    public void addMember(String firstName, String lastName, boolean isMan) {
        addMember(firstName, lastName, isMan, new GregorianCalendar());
    }

    public void addMember(String firstName, String lastName, boolean isMan, Calendar bornDate) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addMember(familyTree, firstName, lastName, isMan, bornDate);
        }
    }

    public void addPerson(String familyTreeName, String firstName, String lastName, boolean isMan) {
        addMember(familyTreeName, firstName, lastName, isMan, new GregorianCalendar());
    }

    public void addPerson(String familyTreeName, String firstName, String lastName, boolean isMan, Calendar bornDate) {
        addPerson(this.findFamilyTree(familyTreeName), firstName, lastName, isMan, bornDate);
    }

    private Member addPerson(FamilyTree<Member> familyTree, String firstName, String lastName, boolean isMan, Calendar bornDate) {
        Member member = new Person(firstName, lastName, isMan, bornDate);
        familyTree.addMember(member);
        return member;
    }

    public void addPerson(String firstName, String firstNameSample) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addPerson(familyTree, firstName, firstNameSample);
        }
    }

    public void addPerson(String familyTreeName, String firstName, String firstNameSample) {
        addPerson(this.findFamilyTree(familyTreeName), firstName, firstNameSample);
    }

    private void addPerson(FamilyTree<Member> familyTree, String firstName, String firstNameSample) {
        Member member = new Person((Person) findMember(familyTree, firstNameSample));
        member.setName(firstName);
        familyTree.addMember(member);
    }

    public void addChild(String firstNameParent, String firstNameChild, String lastNameChild, boolean isMan) {
        addChild(firstNameParent, firstNameChild, lastNameChild, isMan, new GregorianCalendar());
    }

    public void addChild(String firstNameParent, String firstNameChild, String lastNameChild, boolean isMan, Calendar bornDate) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addChild(familyTree, firstNameParent, firstNameChild, lastNameChild, isMan, bornDate);
        }
    }

    private void addChild(FamilyTree<Member> familyTree, String firstNameParent, String firstNameChild, String lastNameChild, boolean isMan, Calendar bornDate) {
        findMember(familyTree, firstNameParent).addChild(this.addMember(familyTree, firstNameChild, lastNameChild, isMan, bornDate));
    }

    public void addChild(String firstNameParent, String firstNameChild) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addChild(familyTree, firstNameParent, firstNameChild);
        }
    }

    public void addChild(String familyTreeName, String firstNameParent, String firstNameChild) {
        addChild(this.findFamilyTree(familyTreeName), firstNameParent, firstNameChild);
    }

    private void addChild(FamilyTree<Member> familyTree, String firstNameParent, String firstNameChild) {
        familyTree.findMember(firstNameParent).addChild(familyTree.findMember(firstNameChild));
    }

    public void addMarried(String firstNameMarried1, String firstNameMarried2, boolean isMarried) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            addMarried(familyTree, firstNameMarried1, firstNameMarried2, isMarried);
        }
    }

    public void addMarried(String familyTreeName, String firstNameMarried1, String firstNameMarried2, boolean isMarried) {
        addMarried(this.findFamilyTree(familyTreeName), firstNameMarried1, firstNameMarried2, isMarried);
    }

    private void addMarried(FamilyTree<Person> familyTree, String firstNameMarried1, String firstNameMarried2, boolean isMarried) {
        findMember(familyTree, firstNameMarried1).addMarried(findMember(familyTree, firstNameMarried2), isMarried);
    }

    public FamilyTree<Member> findFamilyTree(String name) {
        return this.treeOfTrees.findFamilyTree(name);
    }

    public Member findMember(String firstName) {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            return findMember(familyTree, firstName);
        }
        return null;
    }

    private Member findMember(FamilyTree<Member> familyTree, String firstName) {
        return familyTree.findMember(firstName);
    }

    public void sortByFirstName() {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            this.sortByFirstName(familyTree);
        }
    }

    public void sortByFirstName(String familyTreeName) {
        this.sortByFirstName(this.findFamilyTree(familyTreeName));
    }

    private void sortByFirstName(FamilyTree<Member> familyTree) {
        List<Member> members = familyTree.getMembersList();
        members.sort(new MemberComparatorByFirstName<>());
        for (Member member : members) {
            member.getChildren().sort(new MemberComparatorByFirstName<>());
            member.getParents().sort(new MemberComparatorByFirstName<>());
        }
    }

    public void sortByLastName() {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            this.sortByLastName(familyTree);
        }
    }

    public void sortByLastName(String familyTreeName) {
        this.sortByLastName(this.findFamilyTree(familyTreeName));
    }

    private void sortByLastName(FamilyTree<Member> familyTree) {
        List<Member> members = familyTree.getMembersList();
        members.sort(new MemberComparatorByLastName<>());
        for (Member member : members) {
            member.getChildren().sort(new MemberComparatorByLastName<>());
            member.getParents().sort(new MemberComparatorByLastName<>());
        }
    }

    public void sortByGender() {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            this.sortByGender(familyTree);
        }
    }

    public void sortByGender(String familyTreeName) {
        this.sortByGender(this.findFamilyTree(familyTreeName));
    }

    private void sortByGender(FamilyTree<Member> familyTree) {
        List<Member> members = familyTree.getMembersList();
        members.sort(new MemberComparatorByGender<>());
        for (Member member : members) {
            member.getChildren().sort(new MemberComparatorByGender<>());
            member.getParents().sort(new MemberComparatorByGender<>());
        }
    }

    public void sortByBornDate() {
        for (FamilyTree<Member> familyTree : treeOfTrees) {
            this.sortByBornDate(familyTree);
        }
    }

    public void sortByBornDate(String familyTreeName) {
        this.sortByBornDate(this.findFamilyTree(familyTreeName));
    }

    private void sortByBornDate(FamilyTree<Member> familyTree) {
        List<Member> members = familyTree.getMembersList();
        members.sort(new MemberComparatorByBornDate<>());
        for (Member member : members) {
            member.getChildren().sort(new MemberComparatorByBornDate<>());
            member.getParents().sort(new MemberComparatorByBornDate<>());
        }
    }

    public void printInfo() {
        this.treeOfTrees.printAllInfo();
    }

    public void save(String fileName) {
        FileHandler<TreeOfTrees<FamilyTree>> fileHandler = new FileHandler<>();
        this.treeOfTrees.save(fileHandler, fileName);
    }

    public TreeOfTrees<FamilyTree> read(String fileName) {
        FileHandler<TreeOfTrees> fileHandler = new FileHandler<>();
        return fileHandler.read(fileName);
    }
}