package sait.businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sait.dataaccess.NoteRepository;
import sait.dataaccess.UserRepository;
import sait.domainmodel.Note;
import sait.domainmodel.User;

public class NoteService {
    public Note get(int noteID, User user) throws Exception {
        if(ownerCheck(noteID, user) || collaboratorCheck(noteID, user)) {
            NoteRepository nr = new NoteRepository();
            return nr.getNote(noteID);
        }
        return null;
    }
    
    public List<Note> getAll() throws Exception {
        NoteRepository nr = new NoteRepository();
        return nr.getAll();
    }
    
    public List<Note> getAll(User user) throws Exception {
        NoteRepository nr = new NoteRepository();
        return nr.getAll(user);
    }
                                                       
    public int update(int noteID, String contents, User user) throws Exception {
        if(ownerCheck(noteID, user) || collaboratorCheck(noteID, user)) {
            NoteRepository nr = new NoteRepository();
            Note note = nr.getNote(noteID);
            note.setContents(contents);
            return nr.update(note);
        }
        return -1;
    }
    
    public int delete(int noteID, User user) throws Exception {
        if(ownerCheck(noteID, user)) {
            NoteRepository nr = new NoteRepository();
            Note note = nr.getNote(noteID);
            return nr.delete(note); 
        }
        return -1;
    }
    
    public int insert(User user, String contents) throws Exception {
        NoteRepository nr = new NoteRepository();
        UserRepository us = new UserRepository();
        user = us.getUser(user.getUserName());
    	Note note = new Note(0, new Date(), contents, user);
    	return nr.insert(note);
    }
    
    public List<String> getAvailableCollaborators() throws Exception { 
        AdminService as = new AdminService();
        List<User> users = as.getAll();
        List<String> availableCollaborators = new ArrayList<>();
        
        users.stream().forEach((user) -> {
            availableCollaborators.add(user.getUserName());         //gets all the usernames
        });
        
        return availableCollaborators;
    }
    
    public int addCollaborator(int noteID, User user, String collaboratorName) throws Exception {
        if(ownerCheck(noteID, user) && !user.getUserName().equals(collaboratorName)) {
            NoteRepository nr = new NoteRepository();
            Note note = nr.getNote(noteID);
            UserRepository ur = new UserRepository();
            User collaborator = ur.getUser(collaboratorName);
            List<User> collaborators = note.getUserList();
            collaborators.add(collaborator);
            note.setUserList(collaborators);
            return nr.update(note);
        }
        return -1;
    }
    
    public int removeCollaborator(int noteID, User user, String collaboratorName) throws Exception {
        if(ownerCheck(noteID, user) && !user.getUserName().equals(collaboratorName)) {
            NoteRepository nr = new NoteRepository();
            Note note = nr.getNote(noteID);
            UserRepository ur = new UserRepository();
            User collaborator = ur.getUser(collaboratorName);
            List<User> collaborators = note.getUserList();
            collaborators.remove(collaborator);
            note.setUserList(collaborators);
            return nr.update(note);
        }
        return -1;
    }
    
    public List<Note> getCollaboratedNotes(User user) throws Exception {
        NoteRepository nr = new NoteRepository();
        List<Note> allNotes = nr.getAll();
        List<Note> collaboratedNotes = new ArrayList<>();
        
        for(Note note : allNotes)
        {
            List<User> compareUserList = note.getUserList();
            for(int i = 0; i < compareUserList.size(); i++)
            {
                User compareUser = compareUserList.get(i);
                
                if(compareUser.getUserName().equals(user.getUserName()))
                    collaboratedNotes.add(note);
            }
        }
        
        return collaboratedNotes;
    }
    
    private boolean ownerCheck(int noteID, User requestUser) {
        UserRepository ur = new UserRepository();
        NoteRepository nr = new NoteRepository();
        User user = null;
        
        try {
            user = ur.getUser(requestUser.getUserName());
        }
        catch(Exception e) {
            return false;
        }
        
        Note note = nr.getNote(noteID);
        
        return user != null && user.getUserName().equals(note.getOwner().getUserName());
    }
    
    private boolean collaboratorCheck(int noteID, User requestUser) {
        UserRepository ur = new UserRepository();
        NoteRepository nr = new NoteRepository();
        User user = null;
        
        try {
            user = ur.getUser(requestUser.getUserName());
        }
        catch(Exception e) {
            return false;
        }
        
        Note note = nr.getNote(noteID);
        List<User> collaboratorList = note.getUserList();
        
        return user != null && collaboratorList.contains(user);
    }
}
