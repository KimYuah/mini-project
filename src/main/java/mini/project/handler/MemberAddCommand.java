package mini.project.handler;

import mini.project.domain.Member;
import mini.project.util.List;
import mini.project.util.Prompt;

public class MemberAddCommand implements Command {
  List<Member> memberList;

  public MemberAddCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute() throws InterruptedException {
    System.out.println("\n\n[ 회원 등록 ]\n");
    System.out.println("* 회원 등록을 합니다 *\n");

    Member member = new Member();
    // 이름 중복 검증
    String name = Prompt.inputString("이름을 입력해주세요 > ");
    for (int i = 0; i < memberList.size(); i++) {
      Member newMember = memberList.get(i);
      if (newMember.getName().equalsIgnoreCase(name)) {
        System.out.println("이미 등록된 회원입니다.");
        Thread.sleep(200);
        return;
      }
    }
    member.setName(name);
    member.setPassword(Prompt.inputString("암호를 입력해주세요 > "));
    member.setTel(Prompt.inputString("연락처를 입력해주세요 > "));
    memberList.add(member);
    System.out.println("\n* [ "+ member.getName() +" ]" + "님의 회원가입이 완료되었습니다. *");
    Thread.sleep(200);
  }
}
