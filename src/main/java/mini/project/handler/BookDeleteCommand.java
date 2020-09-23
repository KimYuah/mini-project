package mini.project.handler;

import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;
import mini.project.util.Utils;

public class BookDeleteCommand implements Command {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록

  public BookDeleteCommand(List<Book> bookList, List<Book> availableBookList) {
    this.bookList = bookList;
    this.availableBookList = availableBookList;

  }

  @Override
  public void execute() throws InterruptedException {
    System.out.println("\n\t\t * 도서를 삭제합니다.*\n");
    if (!Utils.checkStaff(Prompt.inputInt("\t\t 관리자 번호를 입력해주세요 > "))) {
      System.out.println("\t\t * 관리자번호가 다릅니다. *");
      return;
    } ;

    String title = Prompt.inputString("\t\t 삭제할 도서 제목을 입력해주세요 > ");
    Book book = Utils.checkBook(bookList, title);

    if (book == null) {
      System.out.printf("\t\t [ " + title + " ]" + " 도서는 존재하지 않습니다. ");
      return;
    }

    String response = Prompt.inputString("\t\t 정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("\t\t * 해당 도서의 삭제를 취소하였습니다. *");
      Thread.sleep(200);
      return;
    }

    if (book.isAvailable()) {
      Utils.listDeleteBook(bookList, title);
      Utils.listDeleteBook(availableBookList, title);

    } else {
      System.out.println("\t\t * 대여된 도서는 삭제할 수 없습니다. *");
      return;
    }
    System.out.println("\t\t * 해당 도서를 삭제하였습니다. *");
    Thread.sleep(200);

  }



}
