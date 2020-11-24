package com.pritom

class Home {
    Long id
    String uuid
    String name
    String addreess

    static constraints = {
        uuid unique: true, nullable: true

        name blank: false
        addreess nullable: true
    }
}