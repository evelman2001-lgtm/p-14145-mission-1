package com;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
    Scanner scanner = new Scanner(System.in);

    List<WiseSaying> wiseSayings = new ArrayList<>();
    int lastId = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd); // cmd에 담아서 데이터 전달 (Parameter)
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd); // "삭제와 마찬가지로 cmd에 담아서 데이터 전달
            }
        }

        scanner.close();
    }

    void actionWrite() {
        System.out.println("명언 :");
        String content = scanner.nextLine().trim();
        System.out.println("작가 :");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록 되었습니다.".formatted(wiseSaying.id));
    }

    void actionDelete(String cmd) {
        if (!cmd.contains("?")) {
            System.out.println("삭제 번호를 정확히 입력해주세요.");
            return;
        }
        String[] cmdBits = cmd.split("\\?", 2);
        String[] paramBits = cmdBits[1].split("=", 2);
        int id = Integer.parseInt(paramBits[1]);

        boolean found = false;

        for (int i = 0; i < wiseSayings.size(); i++) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            if (wiseSaying.id == id) {
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
    void actionModify (String cmd) {
        if (!cmd.contains("?")) {
            System.out.println("수정 번호를 정확히 입력해주세요.");
            return;
        }
        String[] cmdBits = cmd.split("\\?", 2);
        String[] paramBits = cmdBits[1].split("=", 2);
        int id = Integer.parseInt(paramBits[1]);

        boolean found = false;

        for (int i = wiseSayings.size() -1 ; i >= 0; i--) {

            WiseSaying wiseSaying = wiseSayings.get(i);
            if (wiseSaying.id ==id) {
                found = true;

                System.out.println("명언(기존) : %s".formatted(wiseSaying.content));
                System.out.println("명언 :");
                String content = scanner.nextLine();

                System.out.println("작가(기존) :%s".formatted(wiseSaying.author));
                System.out.println("작가 :");
                String author = scanner.nextLine();

                wiseSaying.content = content;
                wiseSaying.author = author;

                System.out.println("%d번 명언이 수정되었습니다.".formatted(wiseSaying.id));
                break;
            }
        }
        if (!found) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }
    }


    WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);

            System.out.println("%d / %s / %s ".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
        }
    }
}
