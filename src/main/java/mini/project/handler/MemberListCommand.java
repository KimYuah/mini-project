package mini.project.handler;

import mini.project.domain.Member;
import mini.project.util.Iterator;
import mini.project.util.List;

public class MemberListCommand implements Command {
  List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute() throws InterruptedException {
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
}

