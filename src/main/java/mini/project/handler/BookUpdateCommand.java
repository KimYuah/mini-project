package mini.project.handler;

import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookUpdateCommand implements Command{

  List<Book> bookList; // 전체 도서 목록

  public BookUpdateCommand(List<Book> bookList) {
    this.bookList = bookList;

  }
  @Override
  public void execute() throws InterruptedException {
    System.out.println("\n\n[도서 정보 변경]\n");
    int no = Prompt.inputInt("변경할 도서 코드를 입력해주세요 > ");
    Book book = findByNo(no);

    if (book == null) {
      System.out.println("\n* 해당 코드의 도서가 없습니다. *");
      Thread.sleep(200);
      return;
    }

    String title = Prompt.inputString(
        String.format("* 해당 코드의 도서 제목은 [ %s ] 입니다. *\n변경할 도서 제목을 입력해주세요 > ", book.getTitle()));
    String author = Prompt.inputString(
        String.format("* 해당 코드의 도서 저자는 [ %s ] 입니다. *\n변경할 도서 저자를 입력해주세요 > ", book.getAuthor()));
    String publisher = Prompt.inputString(
        String.format("* 해당 코드의 도서 출판사는 [ %s ] 입니다. *\n변경할 도서 출판사를 입력해주세요 > ", book.getPublisher()));


    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 도서 정보 변경을 취소하였습니다.*");
      Thread.sleep(200);
      return;
    }

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublisher(publisher);
    System.out.println("\n* 도서 변경을 완료하였습니다.*");
    Thread.sleep(200);

  }
  private Book findByNo(int no) {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getNo() == no) {
        return book;
      }
    }
    return null;
  }
}
