package mini.project.handler;

import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;
import mini.project.util.Utils;

public class BookUpdateCommand implements Command {

  List<Book> bookList; // 전체 도서 목록

  public BookUpdateCommand(List<Book> bookList) {
    this.bookList = bookList;

  }

  @Override
  public void execute() throws InterruptedException {
    System.out.println();
    System.out.println("\t\t * 도서 정보를 변경합니다. *");
    if (!Utils.checkStaff(Prompt.inputInt("\t\t관리자 번호를 입력해주세요 > "))) {
      System.out.println("\t\t* 관리자번호가 다릅니다. *");
      return;
    } ;
    int no = Prompt.inputInt("\t\t변경할 도서 코드를 입력해주세요 > ");
    Book book = Utils.findByNo(bookList, no);

    if (book == null) {
      System.out.println("\n\t\t* 해당 코드의 도서가 없습니다. *");
      Thread.sleep(200);
      return;
    }

    String title = Prompt.inputString(String
        .format("\t\t* 해당 코드의 도서 제목은 [ %s ] 입니다. *\n\t\t변경할 도서 제목을 입력해주세요 > ", book.getTitle()));
    String author = Prompt.inputString(String
        .format("\t\t* 해당 코드의 도서 저자는 [ %s ] 입니다. *\n\t\t변경할 도서 저자를 입력해주세요 > ", book.getAuthor()));
    String publisher = Prompt.inputString(String.format(
        "\t\t* 해당 코드의 도서 출판사는 [ %s ] 입니다. *\n\t\t변경할 도서 출판사를 입력해주세요 > ", book.getPublisher()));


    String response = Prompt.inputString("\t\t정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("\t\t* 도서 정보 변경을 취소하였습니다.*");
      Thread.sleep(200);
      return;
    }

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublisher(publisher);
    System.out.println("\n\t\t* 도서 변경을 완료하였습니다.*");
    Thread.sleep(200);

  }


}
