package com;

public class Main {
    public static void main(String[] args) {
//        testrq1();
        App app = new App();
        app.run();
    }
    private static void testrq1(){
        Rq rq = new Rq("목록?searchKeywordType=content&searchKeyword=자바&page=2");
        String searchKeyword = rq.getParam("searchKeyword","");
        String searchKeywordType =rq.getParam("searchKeywordType","");
        int page = rq.getParamAsInt ("page", -1);
        int id = rq.getParamAsInt ("id", -1);

        System.out.println("actionName : " + rq.getActionName());
        System.out.println("searchKeyword : " + searchKeyword);
        System.out.println("searchKeywordType : " + searchKeywordType);
        System.out.println("param page : " + page);
        System.out.println("param id : " + id);
    }
}
