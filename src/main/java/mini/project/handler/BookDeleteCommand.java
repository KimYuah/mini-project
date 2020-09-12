package mini.project.handler;

import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookDeleteCommand implements Command{

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록

  public BookDeleteCommand(List<Book> bookList ,List<Book> availableBookList) {
    this.bookList = bookList;
    this.availableBookList = availableBookList;

  }
  @Override
  public void execute() throws InterruptedException {
    System.out.println("\n\n [도서 삭제]");
    System.out.println("\n * 도서를 삭제합니다.*\n");

    int no = Prompt.inputInt("도서 코드를 입력해주세요 > ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("\n* 해당 코드의 도서가 없습니다. *");
      Thread.sleep(200);
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 해당 도서의 삭제를 취소하였습니다. *");
      Thread.sleep(200);
      return;
    }

    bookList.remove(index);
    System.out.println("* 해당 도서를 삭제하였습니다. *");
    Thread.sleep(200);

  }

  private int indexOf(int no) {
    for (int i = 0; i < bookList.size(); i++) {
      Book board = bookList.get(i);
      if (board.getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
