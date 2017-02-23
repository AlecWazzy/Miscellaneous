package sait.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.businesslogic.AccountService;
import sait.businesslogic.NoteService;
import sait.domainmodel.Note;
import sait.domainmodel.User;

/**
 * @author awassill
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        AccountService acs = new AccountService();
        
        if(action != null && action.equals("logout"))
        {
            acs.logout(request);
            request.setAttribute("message", "Logged out");
            response.sendRedirect("login");
            return;
        }
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("currentUser");
        
        NoteService ns = new NoteService();
        
        List noteList = null;
        List availableCollaborators = null;
        List<Note> collaboratedNotes = null;
        try {
            noteList = ns.getAll(user);
            availableCollaborators = ns.getAvailableCollaborators();
        } catch (Exception ex) {
            request.setAttribute("message", ex.toString());
        }
        
        try {
            collaboratedNotes = ns.getCollaboratedNotes(user);
        }
        catch (Exception ex) {
            request.setAttribute("message", ex.toString());
        }
        
        request.setAttribute("noteList", noteList);
        request.setAttribute("availableCollaborators", availableCollaborators);
        request.setAttribute("collaboratedNotes",collaboratedNotes);
        request.setAttribute("logoutlink","<a href=\"notes?action=logout\">Logout</a>");
        getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("currentUser");
        
        String action = request.getParameter("action");
        
        String contents = request.getParameter("contents");
        
        NoteService ns = new NoteService();
        
        if (action == null)
        {
            action = "";
        }

        switch (action) {
            case "view":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    Note selectedNote = ns.get(noteID, user);
                    request.setAttribute("selectedNote", selectedNote);
                } catch (Exception ex) {
                }   break;
            case "add":
                try {
                    if(ns.insert(user,contents) == 1)
                        request.setAttribute("message", "Note added.");
                    else
                        request.setAttribute("message", "Error: Note could not be added!"); 
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: Note could not be added!"); 
                }   break;
            case "delete":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    if(ns.delete(noteID, user) == 1)
                        request.setAttribute("message", "Note deleted.");
                    else
                        request.setAttribute("message", "Error: Note could not be deleted!");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: Note could not be deleted!");
                }   break;
            case "edit":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    if(ns.update(noteID, contents, user) == 1)
                        request.setAttribute("message", "Note updated.");
                    else
                        request.setAttribute("message", "Error: Note could not be updated!");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: Note could not be updated!");
                }   break;
            case "addcollaborator":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    String collaborator = request.getParameter("addcollaborator-datalist");
                    if(ns.addCollaborator(noteID, user, collaborator) == 1)
                        request.setAttribute("message", "Collaborator added.");
                    else
                        request.setAttribute("message", "Error: Collaborator could not be added.");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: Collaborator could not be added.");
                }   break;
            case "removecollaborator":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    String collaborator = request.getParameter("removecollaborator-option");
                    if(ns.removeCollaborator(noteID, user, collaborator) == 1)
                        request.setAttribute("message", "Collaborator removed.");
                    else
                        request.setAttribute("message", "Error: Collaborator could not be removed.");
                } catch (Exception ex) {
                    request.setAttribute("message", "Error: Collaborator could not be added.");
                }   break;
            case "viewcollaboratednote":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    Note selectedCollaboratedNote = ns.get(noteID, user);
                    request.setAttribute("selectedCollaboratedNote", selectedCollaboratedNote);
                } catch (Exception ex) {
                }   break;
            case "editcollaboratednote":
                try {
                    int noteID = Integer.parseInt(request.getParameter("noteID"));
                    if(ns.update(noteID, contents, user) == 1)
                        request.setAttribute("collaboratedmessage", "Note updated.");
                    else
                        request.setAttribute("collaboratedmessage", "Error: Note could not be updated!");
                } catch (Exception ex) {
                    request.setAttribute("collaboratedmessage", "Error: Note could not be updated!");
                }   break;
        }
        
        try {
            List<Note> noteList = ns.getAll(user);
            request.setAttribute("noteList", noteList);
            List<String> availableCollaborators = ns.getAvailableCollaborators();
            request.setAttribute("availableCollaborators", availableCollaborators);
            List<Note> collaboratedNotes = ns.getCollaboratedNotes(user);
            request.setAttribute("collaboratedNotes",collaboratedNotes);
        } catch (Exception ex) {
            
        }
        
        request.setAttribute("logoutlink","<a href=\"notes?action=logout\">Logout</a>");
        request.getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);
    }
}
