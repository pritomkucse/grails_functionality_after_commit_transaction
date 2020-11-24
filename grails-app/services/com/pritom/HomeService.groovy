package com.pritom

import grails.transaction.Transactional
import org.codehaus.groovy.grails.orm.hibernate.TransactionStatus

@Transactional
class HomeService {
    void saveEntity() {
        TransactionStatus.current.onCommit {
            println("YES!!! TRANSACTION COMMITTED SUCCESSFULLY!!!")
            println("HOME COUNT ${Home.count()}")
        }

        Home home = new Home()
        home.name = "House #${Home.count() + 1}"
        home.addreess = "Lane ${Home.count() + 1}, Dhaka"
        home.save()
    }
}