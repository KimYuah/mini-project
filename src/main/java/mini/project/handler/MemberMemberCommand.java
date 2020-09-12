package mini.project.handler;

import mini.project.util.Prompt;

public class MemberMemberCommand implements Command {

  @Override
  public void execute() {
    loop:
      while (true) {
        String command = Prompt.inputString("\n\n[ 회원 등록 및 관리 ]\n"+
            " 1.회원 등록\n 2.회원 정보 변경\n 3.회원 상세정보\n 4.회원 목록\n 5.이전으로" +
            "\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": memberAdd(); Thread.sleep(200); break;
          case "2": memberSet(); Thread.sleep(200); break;
          case "3": memberDetail(); Thread.sleep(200); break;
          case "4": memberList(); Thread.sleep(200); break;
          case "5":
            System.out.println("\n* 이전으로 갑니다. *");
            Thread.sleep(200);
            break loop;
          default:
            System.out.println("\n* 실행할 수 없는 명령입니다. *");
            Thread.sleep(200);
        }
        System.out.println(); 
      }
  }
}
