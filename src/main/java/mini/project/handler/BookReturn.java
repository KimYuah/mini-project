package mini.project.handler;

import java.sql.Date;
import java.util.List;
import mini.project.domain.Book;
import mini.project.domain.Member;
import mini.project.util.Prompt;

public class BookReturn {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록
  List<Book> unavailableBookList; // 이미 대여된 도서 목록
  MemberHandler memberHandler;

  public BookReturn(MemberHandler memberHandler, List<Book> bookList, List<Book> availableBookList,
      List<Book> unavailableBookList) {
    this.memberHandler = memberHandler;
    this.bookList = bookList;
    this.availableBookList = availableBookList;
    this.unavailableBookList = unavailableBookList;
  }



  // 도서 반납하는 메서드
  public void returnBook() throws InterruptedException {
    String title = Prompt.inputString("반납할 도서 제목을 입력해주세요 > ");
    Book book = checkBook(title);
    if (book == null) {
      System.out.printf("[ " + title + " ]" + " 도서는 존재하지 않습니다. ");
      return;
    }

    String name = Prompt.inputString("반납하시는 분 이름을 입력해주세요 > ");

    Member member = memberHandler.findByName(name);
    checkMember(member);
    if (member.book.contains(title)) {
      for (int i = 0; i < member.book.size(); i++) {
        if (member.book.get(i).equalsIgnoreCase(name)) {
          member.book.remove(i);
        }
      }
    } else {
      System.out.println("해당 도서를 대여하지 않았습니다.");
      return;
    }

    if (title.equalsIgnoreCase(book.getTitle()) && !book.isAvailable()) {
      System.out.println("\n* [ " + title + " ]" + " 도서가 반납되었습니다. *");
      System.out.println("\n* 반납일자는 " + new Date(System.currentTimeMillis()) + " 입니다. *\n");
    }
    retrunBook(book);
    RemoveUnavailableBookList(title);

    System.out.println("\n* [ " + title + " ]" + " 도서는 존재하지 않거나 현재 대여되지 않았습니다. *");
    Thread.sleep(200);
  }

  // 리스트에 있는 도서 반환
  public Book checkBook(String title) {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle().equalsIgnoreCase(title)) {
        return book;
      }
    }
    return null;
  }

  // 회원 검증
  private void checkMember(Member member) throws InterruptedException {
    if (member == null) {
      System.out.println("\n* 등록된 회원이 아닙니다. *");
      Thread.sleep(200);
      return;
    }
    if (!member.getPassword().equalsIgnoreCase(Prompt.inputString("암호를 입력해주세요 > "))) {
      System.out.println("\n* 비밀 번호가 다릅니다.*");
      Thread.sleep(200);
      return;
    }
  }

  public void retrunBook(Book book) {
    book.setAvailable(true);
    availableBookList.add(book);
  }

  public void RemoveUnavailableBookList(String title) {
    for (int index = 0; index < unavailableBookList.size(); index++) {
      Book returnBook = unavailableBookList.get(index);
      if (returnBook.getTitle().equalsIgnoreCase(title)) {
        unavailableBookList.remove(index);
      }
    }
  }
}
