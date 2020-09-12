package mini.project.domain;

import mini.project.util.ArrayList;

public class Member {
  private String tel;
  private String name;
  private String password;
  public ArrayList<String> book = new ArrayList<>();

  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  @Override
  public String toString() {
    return "Member [tel=" + tel + ", name=" + name + ", password=" + password + ", book=" + book
        + "]";
  }

}

