package org.codehaus.groovy.grails.orm.hibernate

import groovy.transform.CompileDynamic
import org.hibernate.FlushMode
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.DefaultTransactionStatus

class GrailsHibernateTransactionManager extends HibernateTransactionManager {
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        super.doBegin transaction, definition
        if (definition.isReadOnly()) {
            setFlushModeManual(transaction)
        }
    }

    @CompileDynamic
    protected void setFlushModeManual(transaction) {
        transaction.sessionHolder?.session?.flushMode = FlushMode.MANUAL
    }

    @Override
    protected DefaultTransactionStatus newTransactionStatus(TransactionDefinition definition, Object transaction, boolean newTransaction, boolean newSynchronization, boolean debug, Object suspendedResources) {
        return new TransactionStatus(super.newTransactionStatus(definition, transaction, newTransaction, newSynchronization, debug, suspendedResources))
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status)
        status.triggerCommitHandlers()
    }
}