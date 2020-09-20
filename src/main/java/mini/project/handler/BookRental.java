package mini.project.handler;

import java.sql.Date;
import java.util.List;
import mini.project.domain.Book;
import mini.project.domain.Member;
import mini.project.util.Prompt;

public class BookRental {
  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록
  List<Book> unavailableBookList; // 이미 대여된 도서 목록
  MemberHandler memberHandler;

  public BookRental(MemberHandler memberHandler, List<Book> bookList, List<Book> availableBookList,
      List<Book> unavailableBookList) {
    this.memberHandler = memberHandler;
    this.bookList = bookList;
    this.availableBookList = availableBookList;
    this.unavailableBookList = unavailableBookList;
  }

  // 도서 대여하는 메서드
  public void rental() throws InterruptedException {
    String title = Prompt.inputString("\n 대여할 도서 제목을 입력해주세요 > ");
    // 도서 정보를 확인
    Book book = checkBook(title);

    if (book == null) {
      System.out.printf("[ " + title + " ]" + " 도서는 존재하지 않습니다. ");
      return;
    }

    if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
      System.out.printf("[ " + title + " ]" + " 도서는 현재 대여 가능합니다.");
      String response = Prompt.inputString(" 대여 하시겠습니까? (y/N) ");

      if (response.equalsIgnoreCase("y")) {
        String name = Prompt.inputString("\n 이름를 입력해주세요 > ");
        Member member = memberHandler.findByName(name);


        // 대여완료
        System.out.println("* [ " + name + " ]님은 " + "[ " + title + " ]" + " 도서를 대여하셨습니다. *");
        System.out.println("\n* 대여일자는 " + new Date(System.currentTimeMillis()) + " 입니다. *\n");

        // 대여자 검증
        checkMember(member);
        // 대여자 정보에 대여도서 추가
        member.book.add(title);
        // 도서 대여 가능여부 변경, 대여된 도서 목록에 저장
        borrowBook(book);
        // 대여가능 도서 목록에서 삭제
        RemoveAvailableBookList(title);
        Thread.sleep(200);

      } else if (response.equalsIgnoreCase("n")) {
        System.out.println("\n* 도서 대여를 종료합니다. *");
        Thread.sleep(200);

      } else {
        System.out.println("잘못 입력하셨습니다. ");

      }
    } else {
      System.out.println("[ " + title + " ]" + " 도서는 현재 대여 불가능합니다.");

    }

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

  // 대여된 도서의 대여가능 여부를 변경하고 대여 불가 목록에 추가.
  public void borrowBook(Book book) {
    book.setAvailable(false);
    unavailableBookList.add(book);
  }

  // 대여 된 도서를 대여가능 목록에서 삭제
  private void RemoveAvailableBookList(String title) {
    for (int index = 0; index < availableBookList.size(); index++) {
      Book rentalBook = availableBookList.get(index);
      if (rentalBook.getTitle().equalsIgnoreCase(title)) {
        availableBookList.remove(index);
      }
    }
  }

}
