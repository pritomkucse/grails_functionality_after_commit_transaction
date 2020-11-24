package org.codehaus.groovy.grails.orm.hibernate

import org.springframework.transaction.support.DefaultTransactionStatus

class TransactionStatus extends DefaultTransactionStatus {
    List<Closure> handlers = []
    private static ThreadLocal<List<TransactionStatus>> _currentStatus = new ThreadLocal<>()

    TransactionStatus(DefaultTransactionStatus status) {
        super(status.transaction, status.newTransaction, status.newSynchronization, status.readOnly, status.debug, status.suspendedResources)
        List statuses = _currentStatus.get()
        if(statuses) {
            statuses << this
        }
        else {
            _currentStatus.set([this])
        }
    }

    void onCommit(Closure handler) {
        handlers << handler
    }

    void triggerCommitHandlers() {
        handlers*.call()
        clearCurrent(current)
    }

    static TransactionStatus getCurrent() {
        _currentStatus.get()?.last()
    }

    static clearCurrent(TransactionStatus currentTop) {
        if(current == currentTop) {
            _currentStatus.remove()
        }
        else {
            _currentStatus.get().remove(currentTop)
        }
    }
}