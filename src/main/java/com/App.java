package com;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
    public Scanner scanner = new Scanner(System.in);

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료":
                    System.out.println("명언 앱을 종료합니다.");
                return;
                case "목록":
                    actionList();
                    break;
                case "등록":
                    actionWrite();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                   actionModify(rq);
                    break;
                default:
                    System.out.println("존재하지 않는 명령입니다.");
            }
        }
    }

    private void actionWrite() {
        System.out.println("명언 :");
        String content = scanner.nextLine().trim();
        System.out.println("작가 :");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록 되었습니다.".formatted(wiseSaying.getId()));
    }

    private void actionDelete(Rq rq) {

        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("삭제 번호를 정확히 입력해주세요.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < wiseSayings.size(); i++) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            if (wiseSaying.getId() == id) {
                wiseSayings.remove(i);
                found = true;
                System.out.println("%d번 명언이 삭제되었습니다\n".formatted(id));
                break;
            }
        }
        if (!found) {
            System.out.println("%d번 명언은 존재하지 않습니다\n".formatted(id));
        }
    }
    private void actionModify (Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("수정 번호를 정확히 입력해주세요.");
        return;
        }

        boolean found = false;

        for (int i = wiseSayings.size() -1 ; i >= 0; i--) {

            WiseSaying wiseSaying = wiseSayings.get(i);
            if (wiseSaying.getId() ==id) {
                found = true;

                System.out.println("명언(기존) : %s".formatted(wiseSaying.getContent()));
                System.out.println("명언 :");
                String content = scanner.nextLine();

                System.out.println("작가(기존) :%s".formatted(wiseSaying.getAuthor()));
                System.out.println("작가 :");
                String author = scanner.nextLine();

                wiseSaying.setContent(content);
                wiseSaying.setAuthor(author);

                System.out.println("%d번 명언이 수정되었습니다.".formatted(wiseSaying.getId()));
                break;
            }
        }
        if (!found) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }
    }


    private WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.getId();
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);

            System.out.println("%d / %s / %s ".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }
}
