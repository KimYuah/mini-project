package mini.project.handler;

import java.util.Iterator;
import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookListCommand implements Command {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록
  List<Book> unavailableBookList; // 이미 대여된 도서 목록

  public BookListCommand(List<Book> bookList, List<Book> availableBookList,
      List<Book> unavailableBookList) {
    this.bookList = bookList;
    this.availableBookList = availableBookList;
    this.unavailableBookList = unavailableBookList;

  }

  @Override
  public void execute() throws InterruptedException {
    loop: while (true) {
      String command = Prompt.inputString("\n\t\t [ 도서 목록 ]\n\n"
          + "\t\t 1.전체 목록\n\n \t\t 2.대여 가능 목록\n\n \t\t 3.대여 도서 목록\n\n \t\t 4.이전으로\n\n"
          + "\t\t 번호를 선택하세요 => ");
      switch (command) {
        case "1":
          list1();
          Thread.sleep(200);
          break;
        case "2":
          availableList();
          Thread.sleep(200);
          break;
        case "3":
          unavailableList();
          Thread.sleep(200);
          break;
        case "4":
          System.out.println("\n\t\t* 이전으로 갑니다. *");
          Thread.sleep(200);
          break loop;
        default:
          System.out.println("\n\t\t* 실행할 수 없는 명령입니다. *");
          Thread.sleep(200);
      }
      System.out.println();
    }
  }



  // 전체목록출력하는메소드
  public void list1() throws InterruptedException {
    System.out.println("\n\t\t [도서 전체 목록]\n");

    Iterator<Book> iterator = bookList.iterator();


    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf(
          "\t\t도서 코드 : %d\n\n" + "\t\t도서 제목 : %s\n\n" + "\t\t도서 저자 : %s\n\n" + "\t\t도서 출판사 : %s\n\n"
              + "\t\t도서 입고일 : %s\n\n" + "\t\t-----------------------------\n",
          book.getNo(), book.getTitle(), book.getAuthor(), book.getPublisher(),
          book.getReceivingDate());
      Thread.sleep(200);
    }
  }

  // 이미 대여된 도서 목록을 출력하는 메서드
  public void unavailableList() throws InterruptedException {

    System.out.println("\n\t\t [대여된 도서 목록]");
    Iterator<Book> iterator = unavailableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("\n\t\t코드 : " + book.getNo() + " / 제목 : " + book.getTitle() + " / 저자 : "
          + book.getAuthor() + "/ 출판사 : " + book.getPublisher() + " / 입고일 : "
          + book.getReceivingDate() + "\n");
      Thread.sleep(200);
    }
  }

  // 대여 가능 목록을 출력하는 메소드
  private void printAvailableList(List<Book> availableList) throws InterruptedException {
    Iterator<Book> iterator = availableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("\n\t\t 코드 : " + book.getNo() + " / 제목 : " + book.getTitle() + " / 저자 : "
          + book.getAuthor() + " / 출판사 : " + book.getPublisher() + " / 입고일 : "
          + book.getReceivingDate() + "\n");
      Thread.sleep(200);
    }
    System.out.println();

  }

  public void availableList() throws InterruptedException {
    System.out.println("\t\t [대여 가능 목록]");
    printAvailableList(availableBookList);
  }
}

