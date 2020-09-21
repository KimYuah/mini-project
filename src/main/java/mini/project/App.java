package mini.project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
  static List<Book> bookList = new LinkedList<>();
  static File bookFile = new File("./bookFile.csv");
  static List<Book> availableBookList = new ArrayList<>();
  static List<Book> unavailableBookList = new ArrayList<>();

  public static void main(String[] args) throws InterruptedException {
    loadBooks();
    loadAvailableBooks();
    loadunavailableBooks();
    // Member
    List<Member> memberList = new ArrayList<>();
    MemberHandler memberHandler = new MemberHandler(memberList);

    // Book
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
    saveBooks();
  }

  private static void loadAvailableBooks() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.isAvailable()) {
        availableBookList.add(book);
      }
    }
  }

  private static void loadunavailableBooks() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (!book.isAvailable()) {
        unavailableBookList.add(book);
      }
    }
  }

  private static void loadBooks() {
    try (FileReader in = new FileReader(bookFile); Scanner sc = new Scanner(in);) {
      int count = 0;

      while (true) {
        try {
          bookList.add(Book.valueOfCsv(sc.nextLine()));
          count++;
        } catch (NoSuchElementException e) {
          break;
        }
      }

      System.out.printf("%d 개의 도서정보를 불러왔습니다 !", count);
    } catch (Exception e) {
      System.out.println("도서 정보 로딩에 실패하였습니다.");
    }
  }

  private static void saveBooks() {
    try (FileWriter out = new FileWriter(bookFile)) {
      int count = 0;
      for (Book book : bookList) {
        out.write(book.toCsvString());
        count++;
      }
      System.out.printf(" %d 개의 도서정보를 저장하였습니다 !", count);
    } catch (Exception e) {
      System.out.println("도서 정보 저장 중에 오류 발생");
    }
  }
}
