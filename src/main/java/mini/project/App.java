package mini.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import mini.project.domain.Book;
import mini.project.domain.Member;
import mini.project.handler.BookHandler;
import mini.project.handler.MemberHandler;
import mini.project.util.Prompt;

public class App {
  public static void main(String[] args) throws InterruptedException {

    //Member
    List<Member> memberList = new ArrayList<>();
    MemberHandler memberHandler = new MemberHandler(memberList);
    //Book
    List<Book> bookList = new LinkedList<>();
    List<Book> availableBookList = new ArrayList<>();
    List<Book> unavailableBookList = new ArrayList<>();
    BookHandler bookHandler = new BookHandler(
        memberHandler, bookList, availableBookList, unavailableBookList);


    loop:
      while (true) {
        System.out.println();
        System.out.println("\t\t---------------------------");
        String command = Prompt.inputString("\t\t\b [ 도서 관리 프로그램 ] \b\n" +
            "\t\t---------------------------"+
            "\n\t\t   1. 도서 등록  \n\n" + 
            "\t\t   2. 도서 목록 \n\n" + 
            "\t\t   3. 도서 삭제\n\n" +  
            "\t\t   4. 도서 정보 변경\n\n" + 
            "\t\t   5. 도서 대여 및 반납\n\n" +
            "\t\t   6. 회원 등록 및 관리\n\n" +
            "\t\t   7. 종료\n"+
            "\t\t---------------------------\n"+
            "\t\t 번호를 선택하세요 => ");

        switch (command) {
          case "1": bookHandler.add(); break;
          case "2": bookHandler.list(); break;
          case "3": bookHandler.delete(); break;
          case "4": bookHandler.update(); break;
          case "5": bookHandler.rental(); break;
          case "6": memberHandler.member(); break;
          case "7":
            System.out.println("\n\t\t * 도서 관리 프로그램을 종료합니다. *");
            break loop;
          default:
            System.out.println("\n\t\t * 실행할 수 없는 명령입니다. *");
            Thread.sleep(500);
        }
        System.out.println(); 
      }

    Prompt.close();

  }
}
