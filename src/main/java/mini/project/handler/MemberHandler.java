package mini.project.handler;

import mini.project.domain.Member;
import mini.project.util.List;
import mini.project.util.Prompt;

public class MemberHandler {

  List<Member> memberList;

  public MemberHandler(List<Member> list) {
    this.memberList = list;
  }

  public void member() throws InterruptedException {
    loop:
      while (true) {
        String command = Prompt.inputString("\n\n[ 회원 등록 및 관리 ]\n"+
            " 1.회원 등록 2.회원 정보 변경 3.회원 상세정보 4.이전으로\n\n" +
            "\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": memberAdd(); Thread.sleep(700); break;
          case "2": memberSet(); Thread.sleep(700); break;
          case "3": memberDetail(); Thread.sleep(700); break;
          case "4":
            System.out.println("* 이전으로 갑니다. *");
            Thread.sleep(700);
            break loop;
          default:
            System.out.println("* 실행할 수 없는 명령입니다. *");
            Thread.sleep(700);
        }
        System.out.println(); 
      }
  }

  private void memberDetail() throws InterruptedException {
    System.out.println("\n\n[회원 상세정보]\n");
    System.out.println("* 회원 상세정보입니다 *");
    String findName = Prompt.inputString("확인할 회원의 이름을 입력해주세요 > ");
    Member member = findByName(findName);

    if (member == null) {
      System.out.println("* 해당 이름의 회원이 없습니다. *");
      Thread.sleep(700);
      return;
    }
    System.out.printf("회원 상세 정보 : \n" 
        + "이 름 : %s\n" + "tel : %s\n" + "book : ",
        member.getName(), member.getTel());
    for (int i = 0; i < member.book.size(); i++) {
      System.out.printf(member.book.get(i));
      System.out.printf(member.book.get(i) + ", ");
      Thread.sleep(700);
    }

  }

  private void memberSet() throws InterruptedException {
    System.out.println("\n\n[회원 정보 변경]\n");
    System.out.println("* 회원 정보 변경입니다 * ");
    String findName = Prompt.inputString("\n정보를 변경할 회원의 이름을 입력해주세요 > ");
    Member member = findByName(findName);
    if (member == null) {
      System.out.println("* 해당 이름의 회원이 없습니다. *\n");
      Thread.sleep(700);
      return;
    }
    String pass = Prompt.inputString("암호를 입력해주세요 >");
    if (!member.getPassword().equalsIgnoreCase(pass)) {
      System.out.println("* 비밀 번호가 다릅니다.*");
      Thread.sleep(700);
      return;
    }

    String name = Prompt.inputString(
        String.format("이름(%s)? ", member.getName()));
    String password = Prompt.inputString("암호? ");
    String tel = Prompt.inputString(
        String.format("전화(%s)? ", member.getTel()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 회원 변경을 취소하였습니다. *");
      Thread.sleep(700);
      return;
    }

    member.setName(name);
    member.setPassword(password);
    member.setTel(tel);

    System.out.println("[ "+ member.getName() +"] 님의" + " 정보를 변경하였습니다.");
    Thread.sleep(700);
  }

  public void memberAdd() throws InterruptedException {
    System.out.println("/n/n[ 회원 등록 ]\n");
    System.out.println("* 회원 등록을 합니다 *\n");

    Member member = new Member();
    member.setName(Prompt.inputString("이름을 입력해주세요 > "));
    member.setPassword(Prompt.inputString("패스워드를 입력해주세요 > "));
    member.setTel(Prompt.inputString("연락처를 입력해주세요 > "));
    memberList.add(member);
    System.out.println("* [ "+ member.getName() +"님 ]" + " 회원가입 감사합니다. *");
    Thread.sleep(700);
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
