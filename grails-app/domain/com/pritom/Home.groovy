package com.pritom

class Home {
    Long id
    String name
    String addreess

    static constraints = {
        name blank: false
        addreess nullable: true
    }
}