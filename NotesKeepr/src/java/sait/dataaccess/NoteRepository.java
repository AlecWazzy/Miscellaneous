package sait.dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import sait.domainmodel.Note;
import sait.domainmodel.User;

public class NoteRepository {

    public int insert(Note Note)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User owner = Note.getOwner();
            owner.getNoteList().add(Note);
            
            em.getTransaction().begin();
            em.persist(Note);
            em.merge(owner);            
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }

    public int update(Note Note) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(Note);
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }
    
    public List<Note> getAll()  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Note> Notes = em.createNamedQuery("Note.findAll", Note.class).
                    getResultList();
            return Notes;
        } finally {
            em.close();
        }
    }
    
    public List<Note> getAll(User user)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Note> Notes = em.createNamedQuery("Note.findAllByOwner", Note.class).setParameter("owner", user).
                    getResultList();
            return Notes;
        } finally {
            em.close();
        }
    }

    public Note getNote(int NoteID)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, NoteID);
            return note;
        } finally {
            em.close();    
        }
    }

    public int delete(Note note)  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User owner = note.getOwner();
            owner.getNoteList().remove(note);
        
            em.getTransaction().begin();
            em.merge(owner);
            em.remove(em.merge(note));
            em.getTransaction().commit();
            return 1;
        } finally {
            em.close();
        }
    }
}
