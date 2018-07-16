package com.thoughtworks.tdd.shell;

public class Router {
    private String currentPage;
    private Controler controler;

    public Router(Controler controler,String currentPage){
        this.controler = controler;
        this.currentPage = currentPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void rocessRequest(Request request){
        switch (currentPage){
            case "main":
                handleMainPage(currentPage,request.getCommand());
                break;
            case "park":
                setCurrentPage(controler.handleParkPage());
                break;
            case "unpark":
                setCurrentPage(controler.handleunparkPage());
                break;
            case "root":
                handleRootPage(currentPage,request.getCommand());
                break;
            case "parkinglot":
                handleParkinglotPage(currentPage,request.getCommand());
        }
    }

    public void handleParkinglotPage(String currentPage, String command) {
        switch (command){
            case "1":
                setCurrentPage(controler.parkinglotPage());
                break;
        }
    }

    public void handleMainPage(String status,String command) {
        switch (command){
            case "1":
                setCurrentPage(controler.parkPage());
                break;
            case "2":
                setCurrentPage(controler.unparkPage());
                break;
            default:
                setCurrentPage(controler.wrongSelect());
                break;
        }
    }

    private void handleRootPage(String status, String command) {
        switch (command){
            case "1":
                controler.getMainPage();
                setCurrentPage("main");
                break;
            case "2":
                controler.showParkinglotPage();
                setCurrentPage("parkinglot");
                break;
            default:
                setCurrentPage(controler.wrongSelect());
                break;

        }
    }
}
