package com;

import com.domain.system.controller.SystemController;
import com.domain.wiseSaying.cotroller.WiseSayingController;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class App {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("== 명언 앱 ==");
        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingcontroller = new WiseSayingController(scanner);

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료":
                    systemController.ActionExit();
                    return;
                case "목록":
                    wiseSayingcontroller.actionList();
                    break;
                case "등록":
                    wiseSayingcontroller.actionWrite();
                    break;
                case "삭제":
                    wiseSayingcontroller.actionDelete(rq);
                    break;
                case "수정":
                    wiseSayingcontroller.actionModify(rq);
                    break;
                default:
                    System.out.println("존재하지 않는 명령입니다.");
            }
        }
    }


}

