package com.pritom

import grails.transaction.Transactional
import org.codehaus.groovy.grails.orm.hibernate.TransactionStatus

@Transactional
class HomeService {
    void saveEntity() {
        TransactionStatus.current.onCommit {
            println(" ")
            println("YES!!! TRANSACTION COMMITTED SUCCESSFULLY!!!")
            println("HOME COUNT ${Home.count()} AFTER COMMIT DONE")
        }

        Integer count = Home.count()

        println("Insert process started")

        Home home = new Home()
        home.uuid = UUID.randomUUID().toString()
        home.name = "House #${count + 1}".toString()
        home.addreess = "Lane ${count + 1}, Dhaka".toString()
        home.save()
    }
}