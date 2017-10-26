package uk.org.tombolo.core.utils;

import org.hibernate.query.Query;
import uk.org.tombolo.core.DatabaseJournalEntry;

/**
 * DatabaseJournal.java
 * The DatabaseJournal is a list of transformations that the database has undergone.
 * For example imports. This is used so we can avoid repeating the job later on,
 * as well as a description of the transforms that this database has undergone.
 * //TOASK is the class that saves the names of the importers that are already imported so we don't import them again
 * // TOASK combination od a class name and datasourceID
 */
public class DatabaseJournal {
    public static void addJournalEntry(DatabaseJournalEntry entry) {
        HibernateUtil.withSession(session -> {
            session.beginTransaction();
            session.save(entry);
            session.getTransaction().commit();
        });
    }

    public static boolean journalHasEntry(DatabaseJournalEntry entry) {
        // TOASK key is the datasourceID, might be confusing so maybe rename
        // TOASK might need to be refactored when we insert a deadline for the importers, could add a timestamp
        return HibernateUtil.withSession(session -> {
            Query query = session.createQuery("select count(*) from DatabaseJournalEntry where className = :className and key = :key");
            query.setParameter("className", entry.getClassName());
            query.setParameter("key", entry.getKey());
            Integer rowCount = ((Number) query.uniqueResult()).intValue();
            return rowCount != 0;
        });
    }
}
