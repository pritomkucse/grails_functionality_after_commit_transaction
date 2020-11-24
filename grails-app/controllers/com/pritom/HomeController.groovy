package com.pritom

class HomeController {
    HomeService homeService

    def index() {
        homeService.saveEntity()
        render(text: "Completed")
    }
}