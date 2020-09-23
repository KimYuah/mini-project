package mini.project.util;

import java.util.List;
import mini.project.domain.Book;
import mini.project.domain.Member;

public class Utils {
  static final int STAFFNUMBER = 7777;

  public static boolean checkStaff(int num) {
    return num == STAFFNUMBER ? true : false;
  }

  public static void listDeleteBook(List<Book> list, String title) {
    for (int i = 0; i < list.size(); i++) {
      Book book = list.get(i);
      if (book.getTitle().equalsIgnoreCase(title)) {
        list.remove(i);
      }
    }
  }

  public static Book checkBook(List<Book> list, String title) {
    for (int i = 0; i < list.size(); i++) {
      Book book = list.get(i);
      if (book.getTitle().equalsIgnoreCase(title)) {
        return book;
      }
    }
    return null;
  }


  public static Member checkMember(Member member) throws InterruptedException {
    if (member == null) {
      return null;
    }

    if (!member.getPassword().equalsIgnoreCase(Prompt.inputString("\t\t암호를 입력해주세요 > "))) {
      System.out.println("\n\t\t* 비밀 번호가 다릅니다.*");
      Thread.sleep(200);
      return null;
    }
    return member;
  }

  public static Book findByNo(List<Book> list, int no) {
    for (int i = 0; i < list.size(); i++) {
      Book book = list.get(i);
      if (book.getNo() == no) {
        return book;
      }
    }
    return null;
  }
}
