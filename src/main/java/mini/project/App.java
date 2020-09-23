package mini.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
  static File bookFile = new File("./bookFile.data");
  static List<Book> bookList = new ArrayList<>();
  static List<Book> availableBookList = new ArrayList<>();
  static List<Book> unavailableBookList = new ArrayList<>();

  static File memberFile = new File("./memberFile.data");
  static List<Member> memberList = new ArrayList<>();

  public static void main(String[] args) throws InterruptedException {
    loadObjects(bookList, bookFile);
    loadObjects(memberList, memberFile);
    setAvaAvailableBook();
    // loadAvailableBooks();
    // loadunavailableBooks();

    // Member
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
      String[] arr = new String[5];
      arr[0] = "__        __   _";
      arr[1] = "\\ \\      / /__| | ___ ___  _ __ ___   ___ ";
      arr[2] = " \\ \\ /\\ / / _ \\ |/ __/ _ \\|  _   _ \\ / _ \\";
      arr[3] = "  \\ V  V /  __/ | (_| (_) | | | | | |  __/";
      arr[4] = "   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|";
      for (int i = 0; i < arr.length; i++) {
        System.out.println(arr[i]);
      }
      String[] arr2 = new String[11];
      arr2[0] = "\t\t---------------------------";
      arr2[1] = "\t\t\b [ 도서 관리 프로그램 ] \b";
      arr2[2] = "\t\t---------------------------";
      arr2[3] = "\t\t   1. 도서 등록 ";
      arr2[4] = "\t\t   2. 도서 목록 ";
      arr2[5] = "\t\t   3. 도서 삭제";
      arr2[6] = "\t\t   4. 도서 정보 변경 ";
      arr2[7] = "\t\t   5. 도서 대여 및 반납 ";
      arr2[8] = "\t\t   6. 회원 등록 및 관리 ";
      arr2[9] = "\t\t   7. 종료 ";
      arr2[10] = "\t\t--------------------------- ";
      for (int i = 0; i < arr2.length; i++) {
        System.out.println(arr2[i]);
        System.out.println();
      }
      int input = Prompt.inputInt("\t\t 번호를 선택하세요 => ");

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
    saveObjects(bookList, bookFile);
    saveObjects(memberList, memberFile);
  }

  private static void setAvaAvailableBook() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.isAvailable()) {
        availableBookList.add(book);
      } else {
        unavailableBookList.add(book);
      }
    }

  }
  //
  // private static void loadAvailableBooks() {
  // for (int i = 0; i < bookList.size(); i++) {
  // Book book = bookList.get(i);
  // if (book.isAvailable()) {
  // availableBookList.add(book);
  // }
  // }
  // }
  //
  // private static void loadunavailableBooks() {
  // for (int i = 0; i < bookList.size(); i++) {
  // Book book = bookList.get(i);
  // if (!book.isAvailable()) {
  // unavailableBookList.add(book);
  // }
  // }
  // }

  @SuppressWarnings("unchecked")
  private static <E> void loadObjects(Collection<E> list, File file) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
      int size = in.readInt();

      for (int i = 0; i < size; i++) {
        list.add((E) in.readObject());
      }

    } catch (Exception e) {
      System.out.println("도서 정보 로딩에 실패하였습니다.");
      e.printStackTrace();
    }
  }

  private static <E extends Serializable> void saveObjects(Collection<E> list, File file) {
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(list.size());
      for (E obj : list) {
        out.writeObject(obj);
      }
    } catch (Exception e) {
      System.out.println("정보 저장 중에 오류 발생");
      e.printStackTrace();
    }
  }



}
