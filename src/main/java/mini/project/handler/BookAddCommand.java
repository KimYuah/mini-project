package mini.project.handler;

import java.sql.Date;
import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookAddCommand implements Command {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록

  public BookAddCommand(List<Book> bookList, List<Book> availableBookList) {
    this.bookList = bookList;
    this.availableBookList = availableBookList;

  }

  @Override
  public void execute() throws InterruptedException {
    System.out.println("\n\n [도서 등록]");
    System.out.println("\n * 도서를 등록합니다.*\n");
    Book book = new Book();
    loop: while (true) {
      int no = Prompt.inputInt(" 도서 코드를 입력해주세요 > ");
      int count = 0;
      boolean count2 = true;
      // 도서 코드 중복 검증
      while (count2) {
        for (int i = 0; i < bookList.size(); i++) {
          if (bookList.get(i).getNo() == no) {
            count++;
            System.out.println("\n* 도서 코드가 중복됩니다. 다시 입력하세요. *\n");
            count2 = false;
          }
        }
        if (count == 0) {
          book.setNo(no);
          break loop;
        }
      }
    }
    book.setTitle(Prompt.inputString(" 제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString(" 저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString(" 출판사를 입력해주세요 > "));
    book.setAvailable(true);
    book.setReceivingDate(new Date(System.currentTimeMillis()));

    availableBookList.add(book);
    bookList.add(book);

    System.out.println("\n* 도서 등록을 완료하였습니다. *");
    Thread.sleep(200); // 다른 메뉴로 넘어가기 전에 텀을 줌.

  }
}
