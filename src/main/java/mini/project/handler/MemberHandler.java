package mini.project.handler;

import mini.project.domain.Member;
import mini.project.util.Iterator;
import mini.project.util.List;
import mini.project.util.Prompt;

public class MemberHandler {

  List<Member> memberList;

  public MemberHandler(List<Member> list) {
    this.memberList = list;
  }

  // 멤버 등록 및 관리에서 부메뉴로 이동하는 메서드
  public void member() throws InterruptedException {

  }


  //회원 전체목록
  public void memberList() throws InterruptedException {
    System.out.println("\n\n[회원 전체 목록]\n"); 

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member member = iterator.next();
      System.out.printf(" 이 름 : %s " + " / 연락처 : %s " + " / 대여정보 : ",
          member.getName(), member.getTel());
      for (int i = 0; i < member.book.size(); i++) {
        System.out.printf(member.book.get(i) + ", ");
      }
      System.out.println();
    }
    Thread.sleep(200);
  }

  // 회원 상세정보를 확인하는 메서드
  // 해당 이름의 회원이 없을 경우 상세정보확인을 할 수 없다.
  private void memberDetail() throws InterruptedException {
    System.out.println("\n\n[회원 상세정보]\n");
    System.out.println("* 회원 상세정보를 확인합니다 *");
    String findName = Prompt.inputString("\n확인할 회원의 이름을 입력해주세요 > ");
    // 이미 등록된 회원인지 확인.
    Member member = findByName(findName);

    if (member == null) {
      System.out.println("\n* 해당 이름의 회원이 없습니다. *");
      Thread.sleep(200);
      return;
    }
    System.out.printf("\n\n * [ " + member.getName() +" ] 회원님의 상세 정보입니다. * \n" 
        + " 이 름 : %s " + " / 연락처 : %s " + " / 대여정보 : ",
        member.getName(), member.getTel());
    for (int i = 0; i < member.book.size(); i++) {
      System.out.printf(member.book.get(i) + "\n");
    }
    Thread.sleep(600);

  }

  // 회원 정보를 변경하는 메서드
  // 회원 암호를 입력해야 변경할 수 있다.
  // 비밀 번호가 다르면 회원 정보 변경을 할 수 없다.
  private void memberSet() throws InterruptedException {
    System.out.println("\n\n[회원 정보 변경]\n");
    System.out.println("* 회원 정보를 변경합니다 * ");

    String findName = Prompt.inputString("\n정보를 변경할 회원의 이름을 입력해주세요 > ");
    Member member = findByName(findName);
    if (member == null) {
      System.out.println("* 해당하는 이름의 회원이 없습니다. *\n");
      Thread.sleep(200);
      return;
    }
    String pass = Prompt.inputString("회원 암호를 입력해야 변경됩니다. \n암호를 입력해주세요 > ");
    if (!member.getPassword().equalsIgnoreCase(pass)) {
      System.out.println("\n* 비밀 번호가 다릅니다.*");
      Thread.sleep(200);
      return;
    }

    String name = Prompt.inputString(
        String.format("\n* 현재 회원의 이름은 [ %s ] 입니다. *\n새로 변경할 이름을 입력해주세요 > ", member.getName()));
    String password = Prompt.inputString("새로 변경하실 암호를 입력하세요 > ");
    String tel = Prompt.inputString(
        String.format("\n* 현재 회원의 연락처는[ %s ] 입니다. *\n새로 변경할 연락처를 입력해주세요 >  ", member.getTel()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 회원 정보 변경을 취소하였습니다. *");
      Thread.sleep(200);
      return;
    }

    member.setName(name);
    member.setPassword(password);
    member.setTel(tel);

    System.out.println("\n* [ "+ member.getName() +"] 님의" + " 정보를 변경하였습니다. *");
    Thread.sleep(200);
  }

  // 회원을 등록하는 메서드
  // 이미 등록된 회원은 등록할 수 없다.
  public void memberAdd() throws InterruptedException {

  }

  public Member findByName(String name) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }
}
