package mini.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import mini.project.domain.Book;
import mini.project.domain.Member;
import mini.project.handler.BookAddCommand;
import mini.project.handler.BookDeleteCommand;
import mini.project.handler.BookListCommand;
import mini.project.handler.BookRental;
import mini.project.handler.BookRentalCommand;
import mini.project.handler.BookReturn;
import mini.project.handler.BookUpdateCommand;
import mini.project.handler.Command;
import mini.project.handler.MemberHandler;
import mini.project.util.Prompt;

public class App {
  public static void main(String[] args) throws InterruptedException {

    // Member
    List<Member> memberList = new ArrayList<>();
    MemberHandler memberHandler = new MemberHandler(memberList);

    // Book
    List<Book> bookList = new LinkedList<>();
    List<Book> availableBookList = new ArrayList<>();
    List<Book> unavailableBookList = new ArrayList<>();
    BookRental bookRental =
        new BookRental(memberHandler, bookList, availableBookList, unavailableBookList);
    BookReturn bookReturn =
        new BookReturn(memberHandler, bookList, availableBookList, unavailableBookList);
    Map<Integer, Command> commandMap = new HashMap<>();
    commandMap.put(1, new BookAddCommand(bookList, availableBookList));
    commandMap.put(2, new BookListCommand(bookList, availableBookList, unavailableBookList));
    commandMap.put(3, new BookDeleteCommand(bookList, availableBookList));
    commandMap.put(4, new BookUpdateCommand(bookList));
    commandMap.put(5, new BookRentalCommand(bookRental, bookReturn));



    loop: while (true) {
      System.out.println();
      System.out.println("\t\t---------------------------");
      int input = Prompt.inputInt("\t\t\b [ 도서 관리 프로그램 ] \b\n" + "\t\t---------------------------"
          + "\n\t\t   1. 도서 등록  \n\n" + "\t\t   2. 도서 목록 \n\n" + "\t\t   3. 도서 삭제\n\n"
          + "\t\t   4. 도서 정보 변경\n\n" + "\t\t   5. 도서 대여 및 반납\n\n" + "\t\t   6. 회원 등록 및 관리\n\n"
          + "\t\t   7. 종료\n" + "\t\t---------------------------\n" + "\t\t 번호를 선택하세요 => ");

      switch (input) {
        case 6:
          memberHandler.member();
          break;
        case 7:
          System.out.println("\n\t\t * 도서 관리 프로그램을 종료합니다. *");
          break loop;
        default:
          Command command = commandMap.get(input);
          command.execute();
          Thread.sleep(200);
      }
      System.out.println();
    }

    Prompt.close();

  }
}
